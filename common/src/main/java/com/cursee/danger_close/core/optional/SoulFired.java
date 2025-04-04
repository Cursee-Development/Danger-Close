package com.cursee.danger_close.core.optional;

import it.crystalnest.soul_fire_d.api.FireManager;
import it.crystalnest.soul_fire_d.api.type.FireTyped;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class SoulFired {

    public static void immolateSoul(LivingEntity entity) {
        FireManager.setOnFire(entity, 2, FireManager.SOUL_FIRE_TYPE);
    }

    public static void spreadTypedFire(LivingEntity entityA, LivingEntity entityB) {
        if (entityA.isOnFire() && !entityB.isOnFire()) {
            ResourceLocation fireType = ((FireTyped) entityA).getFireType();
            FireManager.setOnFire(entityB, 2, fireType);
        }
        else if (!entityA.isOnFire() && entityB.isOnFire()) {
            ResourceLocation fireType = ((FireTyped) entityB).getFireType();
            FireManager.setOnFire(entityB, 2, fireType);
        }
    }
}
