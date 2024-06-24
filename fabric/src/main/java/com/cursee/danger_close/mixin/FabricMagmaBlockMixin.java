package com.cursee.danger_close.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MagmaBlock.class)
public class FabricMagmaBlockMixin {
	
	@Inject(method = "stepOn", at = @At(("HEAD")), cancellable = true)
	public void injected(Level $$0, BlockPos $$1, BlockState $$2, Entity $$3, CallbackInfo ci) {
		ci.cancel();
	}
}
