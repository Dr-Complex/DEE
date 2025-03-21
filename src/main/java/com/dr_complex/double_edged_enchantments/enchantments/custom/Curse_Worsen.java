package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record Curse_Worsen() implements EnchantmentEntityEffect {

    public static float Exp = 0;
    public static float PrevExp = 0;

    public static final MapCodec<Curse_Worsen> CODEC =MapCodec.unit(Curse_Worsen::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if(context.owner() != null){
            if(context.owner() instanceof PlayerEntity player){
                Exp = player.experienceProgress;
                if(PrevExp != Exp){
                    Exp = PrevExp;
                    context.stack().damage(level,player);
                }
                player.experienceProgress = Exp;
                PrevExp = player.experienceProgress;
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
