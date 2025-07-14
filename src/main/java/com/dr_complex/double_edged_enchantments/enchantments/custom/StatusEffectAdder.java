package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record StatusEffectAdder(boolean good) implements EnchantmentEntityEffect {
    static StatusEffectInstance instance;

    public static final MapCodec<StatusEffectAdder> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.BOOL.fieldOf("good").forGetter(StatusEffectAdder::good)
    ).apply(instance, StatusEffectAdder::new));

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        float luck;
        if(user instanceof LivingEntity living && world.random.nextFloat() > 1f/level){
            luck = world.random.nextFloat();
            if(this.good){
                if(luck >= 0.015 && luck <=0.016){
                    instance = new StatusEffectInstance(StatusEffects.RESISTANCE,20*6,level-1);
                } else if (luck >= 0.025 && luck <= 0.033) {
                    instance = new StatusEffectInstance(StatusEffects.HEALTH_BOOST,20*3,level-1);
                }else if(luck >= 0.05 && luck<= 0.07){
                    instance = new StatusEffectInstance(StatusEffects.REGENERATION,20*10,level-1);
                }
            } else {
                if(luck >= 0.00 && luck <=0.025){
                    instance = new StatusEffectInstance(StatusEffects.BLINDNESS,20*30,level-1);
                } else if (luck >= 0.025 && luck <= 0.033) {
                    instance = new StatusEffectInstance(StatusEffects.LEVITATION,20*10,level-1);
                }else if(luck >= 0.05 && luck<= 0.07){
                    instance = new StatusEffectInstance(StatusEffects.NAUSEA,60*20,level-1);
                }
            }
            if(instance != null){
                living.addStatusEffect(instance);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
