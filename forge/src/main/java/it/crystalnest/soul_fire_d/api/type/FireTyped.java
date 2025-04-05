package it.crystalnest.soul_fire_d.api.type;

import net.minecraft.resources.ResourceLocation;

/** Lazy hack to allow calls to this in Common, as Soul Fire'd is not developed on Forge for 1.21+ */
@Deprecated
public interface FireTyped {

    @Deprecated
    ResourceLocation getFireType();
}
