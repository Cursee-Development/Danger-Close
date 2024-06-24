package com.cursee.danger_close;

import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DangerClose {

    public static final TagKey<Block> TORCH_BURN_DANGER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "torch_burn_danger"));
    public static final TagKey<Block> SOUL_TORCH_BURN_DANGER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "soul_torch_burn_danger"));
    public static final TagKey<Block> CAMPFIRE_BURN_DANGER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "campfire_burn_danger"));
    public static final TagKey<Block> SOUL_CAMPFIRE_BURN_DANGER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "soul_campfire_burn_danger"));
    public static final TagKey<Block> MAGMA_BURN_DANGER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "magma_burn_danger"));
    public static final TagKey<Block> STONECUTTER_DANGER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "stonecutter_danger"));

    public static boolean shouldDetect = false;
    public static boolean shouldTorchImmolate = false;
    public static boolean shouldSoulTorchImmolate = false;
    public static boolean shouldCampfireImmolate = false;
    public static boolean shouldSoulCampfireImmolate = false;
    public static boolean shouldStonecutterCut = false;
    public static boolean shouldMagmaBlockImmolate = false;
    public static boolean shouldBlazeImmolate = false;
    public static boolean shouldMagmaCubeImmolate = false;

    public static void init() {

        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
    }

    public static void immolate(LivingEntity entity) {
        entity.setRemainingFireTicks(20 * 2); // for 40 ticks, or 2 seconds
    }

    private static void spreadFire(LivingEntity pLiving0, LivingEntity pLiving1) {

        if (pLiving1.isOnFire() && !pLiving0.isOnFire()) {
            immolate(pLiving0);
        }
        else if (pLiving0.isOnFire() && !pLiving1.isOnFire()) {
            immolate(pLiving1);
        }

        if ((shouldBlazeImmolate && pLiving1 instanceof Blaze) || (shouldMagmaCubeImmolate && pLiving1 instanceof MagmaCube)) {
            immolate(pLiving0);
        }
    }

    public static void detect(ServerLevel level, LivingEntity entity) {

        if (shouldDetect && (level != null && entity != null)) {

            List<LivingEntity> nearby = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, entity, entity.getBoundingBox());

            for (LivingEntity pLiving1 : nearby) {
                spreadFire(entity, pLiving1);
            }

            if (entity.onGround()) {

                BlockPos pos = entity.blockPosition();

                BlockState insideBlockState = level.getBlockState(pos);

                Stream<TagKey<Block>> insideBlockTagStream = insideBlockState.getTags();
                Stream<TagKey<Block>> belowBlockTagStream = level.getBlockState(pos.below()).getTags();

                List<TagKey<Block>> insideReStreamable = new ArrayList<>();
                List<TagKey<Block>> belowReStreamable = new ArrayList<>();

                insideBlockTagStream.forEach(insideReStreamable::add);
                belowBlockTagStream.forEach(belowReStreamable::add);

                if (shouldTorchImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.TORCH_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.TORCH_BURN_DANGER)))) {
                    immolate(entity);
                }
                else if (shouldSoulTorchImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_TORCH_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_TORCH_BURN_DANGER)))) {
                    immolate(entity);
                }


                if (shouldCampfireImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.CAMPFIRE_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.CAMPFIRE_BURN_DANGER)))) {
                    if (insideBlockState.hasProperty(CampfireBlock.LIT) && insideBlockState.getValue(CampfireBlock.LIT)) {
                        immolate(entity);
                    }
                }
                else if (shouldSoulCampfireImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_CAMPFIRE_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.SOUL_CAMPFIRE_BURN_DANGER)))) {
                    if (insideBlockState.hasProperty(CampfireBlock.LIT) && insideBlockState.getValue(CampfireBlock.LIT)) {
                        immolate(entity);
                    }
                }

                if (shouldStonecutterCut && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.STONECUTTER_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.STONECUTTER_DANGER)))) {
                    entity.hurt(level.damageSources().cactus(), 4.0F);
                }

                if (shouldMagmaBlockImmolate && (insideReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.MAGMA_BURN_DANGER)) || belowReStreamable.stream().anyMatch(Predicate.isEqual(DangerClose.MAGMA_BURN_DANGER)))) {
                    if (!insideBlockState.getBlock().getDescriptionId().toLowerCase().contains("cauldron")) {
                        immolate(entity);
                    }
                }
            }
        }
    }
}