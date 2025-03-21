package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public record Curse_Conductiveness() implements EnchantmentEntityEffect {
    
    public static final MapCodec<Curse_Conductiveness> CODEC = MapCodec.unit(Curse_Conductiveness::new);
    
    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if(context.owner() != null){
            for(int i = 0; i < level + 2; i ++) {
                EntityType.LIGHTNING_BOLT.spawn(world, context.owner().getBlockPos().add(
                        MathHelper.ceil((world.random.nextFloat() - 0.5f)*level),
                        MathHelper.ceil((world.random.nextFloat() - 0.5f)*level),
                        MathHelper.ceil((world.random.nextFloat() - 0.5f)*level)
                ), SpawnReason.COMMAND);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
