package com.cursee.danger_close.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlock.class)
public class NeoForgeCampfireBlockMixin {

    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    private void injected(BlockState $$0, Level $$1, BlockPos $$2, Entity $$3, CallbackInfo ci) {
        ci.cancel();
    }
}
