package com.cursee.danger_close.mixin;

import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slime.class)
public class ForgeSlimeMixin {

    @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Slime;dealDamage(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void danger_close$onPlayerTouch(Player player, CallbackInfo ci) {
        Slime instance = (Slime) (Object) this;
        if (instance instanceof MagmaCube) player.setRemainingFireTicks(2 * 20);
    }
}
