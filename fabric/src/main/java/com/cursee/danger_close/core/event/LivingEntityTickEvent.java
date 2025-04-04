package com.cursee.danger_close.core.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;

public class LivingEntityTickEvent {

    public static final Event<Update> ON_TICK = EventFactory.createArrayBacked(Update.class, callbacks -> (livingEntity) -> {
        for (Update callback : callbacks) {
            callback.onUpdate(livingEntity);
        }
    });

    @FunctionalInterface
    public interface Update {
        void onUpdate(LivingEntity livingEntity);
    }
}
