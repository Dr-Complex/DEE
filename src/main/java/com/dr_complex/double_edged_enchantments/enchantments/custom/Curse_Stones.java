package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public record Curse_Stones() implements EnchantmentEntityEffect {

    public static final MapCodec<Curse_Stones> CODEC = MapCodec.unit(Curse_Stones::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if(context.owner() != null){
            LivingEntity living = context.owner();
            if(MathHelper.floorMod(living.fallDistance,15.0f) > 15 - level){
                living.fallDistance += level/2f;
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
