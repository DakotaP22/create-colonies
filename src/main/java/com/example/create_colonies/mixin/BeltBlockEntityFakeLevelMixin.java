package com.example.create_colonies.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Fixes a StackOverflowError when Create belts appear inside a MineColonies/blockui FakeLevel
 * (used to preview schematics). BeltBlockEntity.getControllerBE() calls level.getBlockEntity(),
 * which in a FakeLevel triggers another belt initialization, causing infinite recursion.
 *
 * Fix: if we are not in a real ServerLevel or ClientLevel, return null immediately.
 * ClientLevel is checked by class name to avoid referencing a client-only class in common code.
 */
@Mixin(targets = "com.simibubi.create.content.kinetics.belt.BeltBlockEntity")
public abstract class BeltBlockEntityFakeLevelMixin {

    private static final String CLIENT_LEVEL_CLASS = "net.minecraft.client.multiplayer.ClientLevel";

    @Inject(method = "getControllerBE", at = @At("HEAD"), cancellable = true, remap = false)
    private void create_colonies$skipControllerLookupInFakeLevel(CallbackInfoReturnable<?> cir) {
        Level level = ((BlockEntity) (Object) this).getLevel();
        if (level != null
                && !(level instanceof ServerLevel)
                && !CLIENT_LEVEL_CLASS.equals(level.getClass().getName())) {
            cir.setReturnValue(null);
        }
    }
}
