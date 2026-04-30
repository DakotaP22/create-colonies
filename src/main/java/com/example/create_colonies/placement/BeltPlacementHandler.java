package com.example.create_colonies.placement;

import java.util.ArrayList;
import java.util.List;

import com.ldtteam.structurize.placement.IPlacementContext;
import com.ldtteam.structurize.placement.handlers.placement.IPlacementHandler;
import com.ldtteam.structurize.placement.handlers.placement.PlacementHandlers;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltPart;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Custom placement handler for Create's {@link BeltBlock}.
 *
 * <p>Structurize's default {@code GeneralBlockPlacementHandler} calls
 * {@code BlockUtils.getItemStackFromBlockState()} which maps every belt block state to
 * a single belt-connector item. This means a 3-block belt (START + MIDDLE + END) is
 * counted as three belt connectors instead of the correct two shafts + one belt connector.
 *
 * <p>The correct per-part requirements are:
 * <ul>
 *   <li>START   – 1 shaft + 1 belt connector</li>
 *   <li>MIDDLE  – (nothing)</li>
 *   <li>END     – 1 shaft</li>
 *   <li>PULLEY  – 1 shaft</li>
 * </ul>
 * We replicate that logic here so MineColonies builders request the correct materials.
 */
public class BeltPlacementHandler implements IPlacementHandler {

    private static final ResourceLocation SHAFT_ID          = ResourceLocation.fromNamespaceAndPath("create", "shaft");
    private static final ResourceLocation BELT_CONNECTOR_ID = ResourceLocation.fromNamespaceAndPath("create", "belt_connector");

    /**
     * Insert at position 0 in the handlers list so we run before every other handler,
     * including MineColonies' GeneralBlockPlacementHandler (which catches all blocks).
     * Since {@link #canHandle} only returns true for belt blocks, this is safe.
     */
    public static void register() {
        PlacementHandlers.handlers.add(0, new BeltPlacementHandler());
    }

    @Override
    public boolean canHandle(Level level, BlockPos pos, BlockState state) {
        return state.getBlock() instanceof BeltBlock;
    }

    @Override
    public ActionProcessingResult handle(Level level, BlockPos pos, BlockState state,
            CompoundTag tileEntityData, IPlacementContext context) {
        return PlacementHandlers.simplePlacement(level, pos, state, context.getRotationMirror(), tileEntityData);
    }

    @Override
    public List<ItemStack> getRequiredItems(Level level, BlockPos pos, BlockState state,
            CompoundTag tileEntityData, IPlacementContext context) {

        List<ItemStack> items = new ArrayList<>();
        BeltPart part = state.getValue(BeltBlock.PART);

        // START, END, and PULLEY parts each sit on a shaft.
        if (part != BeltPart.MIDDLE) {
            Item shaft = BuiltInRegistries.ITEM.get(SHAFT_ID);
            if (shaft != null && shaft != Items.AIR) {
                items.add(new ItemStack(shaft));
            }
        }

        // Only the START part also needs the belt connector item.
        if (part == BeltPart.START) {
            Item beltConnector = BuiltInRegistries.ITEM.get(BELT_CONNECTOR_ID);
            if (beltConnector != null && beltConnector != Items.AIR) {
                items.add(new ItemStack(beltConnector));
            }
        }

        return items;
    }

    @Override
    public boolean doesWorldStateMatchBlueprintState(BlockState worldState, BlockState blueprintState,
            Tuple<BlockEntity, CompoundTag> blockEntityData, IPlacementContext context) {
        return worldState.equals(blueprintState);
    }
}
