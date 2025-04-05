package it.crystalnest.soul_fire_d.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/** Lazy hack to allow calls to this in Common, as Soul Fire'd is not developed on Forge for 1.21+ */
@Deprecated
public class FireManager {

    @Deprecated
    public static final ResourceLocation SOUL_FIRE_TYPE = ResourceLocation.withDefaultNamespace("soul");

    @Deprecated
    public static void setOnFire(Entity entity, float seconds, ResourceLocation fireType) {
        entity.setRemainingFireTicks((int)(20.0f * seconds));
    }
}
