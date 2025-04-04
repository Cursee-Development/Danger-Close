package com.cursee.danger_close.mixin;

import com.cursee.danger_close.core.event.LivingEntityTickEvent;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class FabricLivingEntityMixin {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V"))
    private void onTick(CallbackInfo ci) {
        LivingEntity instance = (LivingEntity) (Object) this;
        if (instance.level() == null || instance.level().isClientSide()) return;
        LivingEntityTickEvent.ON_TICK.invoker().onUpdate(instance);
    }
}
