package com.cursee.danger_close;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.optional.SoulFired;
import com.cursee.danger_close.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Predicate;

public class DangerClose {

    public static final TagKey<Block> TORCH_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "torch_burn_danger"));
    public static final TagKey<Block> SOUL_TORCH_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "soul_torch_burn_danger"));
    public static final TagKey<Block> CAMPFIRE_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "campfire_burn_danger"));
    public static final TagKey<Block> SOUL_CAMPFIRE_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "soul_campfire_burn_danger"));
    public static final TagKey<Block> MAGMA_BURN_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "magma_burn_danger"));
    public static final TagKey<Block> STONECUTTER_DANGER = TagKey.create(Registries.BLOCK, new ResourceLocation(Constants.MOD_ID, "stonecutter_danger"));

    public static final Predicate<TagKey<Block>> TORCH_MATCH = tag -> tag == TORCH_BURN_DANGER;
    public static final Predicate<TagKey<Block>> SOUL_TORCH_MATCH = tag -> tag == SOUL_TORCH_BURN_DANGER;
    public static final Predicate<TagKey<Block>> CAMPFIRE_MATCH = tag -> tag == CAMPFIRE_BURN_DANGER;
    public static final Predicate<TagKey<Block>> SOUL_CAMPFIRE_MATCH = tag -> tag == SOUL_CAMPFIRE_BURN_DANGER;
    public static final Predicate<TagKey<Block>> MAGMA_MATCH = tag -> tag == MAGMA_BURN_DANGER;
    public static final Predicate<TagKey<Block>> STONECUTTER_MATCH = tag -> tag == STONECUTTER_DANGER;

    public static boolean isSoulFiredLoaded = false;

    public static void init() {
        isSoulFiredLoaded = Services.PLATFORM.isModLoaded("soul_fire_d");
    }

    public static ResourceLocation identifier(String value) {
        return new ResourceLocation(Constants.MOD_ID, value);
    }

    public static void detect(LivingEntity entity) {
        if (!CommonConfigValues.shouldDetect) return;
        Level level = entity.level();
        if (level.isClientSide()) return;

        // block detection first
        if (entity.onGround()) {
            BlockPos pos = entity.blockPosition();
            BlockState stateInside = level.getBlockState(pos);
            BlockState stateBelow = level.getBlockState(pos.below());

            boolean hasFrostWalker = EnchantmentHelper.hasFrostWalker(entity);

            if (CommonConfigValues.shouldTorchImmolate && !hasFrostWalker) {
                if (stateInside.getTags().anyMatch(TORCH_MATCH)) immolate(entity);
                else if (stateBelow.getTags().anyMatch(TORCH_MATCH)) immolate(entity);
            }
            if (CommonConfigValues.shouldSoulTorchImmolate && !hasFrostWalker) {
                if (stateInside.getTags().anyMatch(SOUL_TORCH_MATCH)) immolateSoul(entity);
                else if (stateBelow.getTags().anyMatch(SOUL_TORCH_MATCH)) immolateSoul(entity);
            }

            if (CommonConfigValues.shouldCampfireImmolate && !hasFrostWalker) {
                if (stateInside.getTags().anyMatch(CAMPFIRE_MATCH) && stateInside.getValue(CampfireBlock.LIT)) immolate(entity);
                else if (stateBelow.getTags().anyMatch(CAMPFIRE_MATCH) && stateBelow.getValue(CampfireBlock.LIT)) immolate(entity);
            }
            if (CommonConfigValues.shouldSoulCampfireImmolate && !hasFrostWalker) {
                if (stateInside.getTags().anyMatch(SOUL_CAMPFIRE_MATCH) && stateInside.getValue(CampfireBlock.LIT)) immolateSoul(entity);
                else if (stateBelow.getTags().anyMatch(SOUL_CAMPFIRE_MATCH) && stateBelow.getValue(CampfireBlock.LIT)) immolateSoul(entity);
            }

            if (CommonConfigValues.shouldMagmaBlockImmolate && !hasFrostWalker && !entity.isShiftKeyDown()) {
                if (stateInside.getTags().anyMatch(MAGMA_MATCH)) immolateSoul(entity);
                else if (stateBelow.getTags().anyMatch(MAGMA_MATCH)) immolateSoul(entity);
            }

            if (CommonConfigValues.shouldStonecutterCut && !entity.isShiftKeyDown()) {
                if (stateInside.getTags().anyMatch(STONECUTTER_MATCH)) entity.hurt(level.damageSources().generic(), 4.0F);
                else if (stateBelow.getTags().anyMatch(STONECUTTER_MATCH)) entity.hurt(level.damageSources().generic(), 4.0F);
            }
        }

        // entity detection last
        List<LivingEntity> nearby = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, entity, entity.getBoundingBox());
        for (LivingEntity living : nearby) {
            spreadFire(entity, living);
        }
    }

    public static void immolate(LivingEntity entity) {
        entity.setRemainingFireTicks(20 * 2);
    }

    public static void immolateSoul(LivingEntity entity) {
        if (!isSoulFiredLoaded) immolate(entity);
        else SoulFired.immolateSoul(entity);
    }

    public static void spreadFire(LivingEntity entityA, LivingEntity entityB) {

        if (entityA.isOnFire() && !entityB.isOnFire()) {
            if (isSoulFiredLoaded) {
                SoulFired.spreadTypedFire(entityA, entityB);
                return;
            }

            immolate(entityB);
        }
        else if (!entityA.isOnFire() && entityB.isOnFire()) {
            if (isSoulFiredLoaded) {
                SoulFired.spreadTypedFire(entityA, entityB);
                return;
            }

            immolate(entityA);
        }

        if (CommonConfigValues.shouldBlazeImmolate && entityB instanceof Blaze) immolate(entityA);
        if (CommonConfigValues.shouldMagmaCubeImmolate && !entityA.isOnFire() && (entityB instanceof MagmaCube || entityB.getType() == EntityType.MAGMA_CUBE)) immolate(entityA);
    }
}