package com.cursee.danger_close.core.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;

public class LivingEntityTickEvent {

    public static final Event<LivingEntityTickEvent.Update> ON_TICK = EventFactory.createArrayBacked(LivingEntityTickEvent.Update.class, callbacks -> (livingEntity) -> {
        for (LivingEntityTickEvent.Update callback : callbacks) {
            callback.onUpdate(livingEntity);
        }
    });

    @FunctionalInterface
    public interface Update {
        void onUpdate(LivingEntity livingEntity);
    }
}
