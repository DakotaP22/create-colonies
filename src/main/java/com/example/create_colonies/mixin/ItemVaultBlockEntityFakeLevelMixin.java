package com.example.create_colonies.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fixes a StackOverflowError when Create item vaults appear inside a MineColonies/blockui
 * FakeLevel (used to preview schematics). ItemVaultBlockEntity.initCapability() calls
 * level.getBlockEntity() to find connected vault blocks, which in a FakeLevel triggers
 * another vault's initCapability(), causing infinite recursion.
 *
 * Fix: skip initCapability entirely when not in a real ServerLevel or ClientLevel.
 * ClientLevel is checked by class name to avoid referencing a client-only class in common code.
 */
@Mixin(targets = "com.simibubi.create.content.logistics.vault.ItemVaultBlockEntity")
public abstract class ItemVaultBlockEntityFakeLevelMixin {

    private static final String CLIENT_LEVEL_CLASS = "net.minecraft.client.multiplayer.ClientLevel";

    @Inject(method = "initCapability", at = @At("HEAD"), cancellable = true, remap = false)
    private void create_colonies$skipCapabilityInitInFakeLevel(CallbackInfo ci) {
        Level level = ((BlockEntity) (Object) this).getLevel();
        if (level != null
                && !(level instanceof ServerLevel)
                && !CLIENT_LEVEL_CLASS.equals(level.getClass().getName())) {
            ci.cancel();
        }
    }
}
