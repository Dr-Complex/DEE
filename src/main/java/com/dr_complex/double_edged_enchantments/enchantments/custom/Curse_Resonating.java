package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;

public record Curse_Resonating() implements EnchantmentEntityEffect {

    public static final MapCodec<Curse_Resonating> CODEC = MapCodec.unit(Curse_Resonating::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if(context.owner() != null){
            LivingEntity living = context.owner();
            int rng = world.random.nextBetween(1,15);
            if(living.getMovement().length() > 0.1 && world.random.nextFloat() >= ((float) 1 /level)){
                if(rng == 1) world.emitGameEvent(user, GameEvent.RESONATE_1,pos);
                if(rng == 2) world.emitGameEvent(user, GameEvent.RESONATE_2,pos);
                if(rng == 3) world.emitGameEvent(user, GameEvent.RESONATE_3,pos);
                if(rng == 4) world.emitGameEvent(user, GameEvent.RESONATE_4,pos);
                if(rng == 5) world.emitGameEvent(user, GameEvent.RESONATE_5,pos);
                if(rng == 6) world.emitGameEvent(user, GameEvent.RESONATE_6,pos);
                if(rng == 7) world.emitGameEvent(user, GameEvent.RESONATE_7,pos);
                if(rng == 8) world.emitGameEvent(user, GameEvent.RESONATE_8,pos);
                if(rng == 9) world.emitGameEvent(user, GameEvent.RESONATE_9,pos);
                if(rng == 10) world.emitGameEvent(user, GameEvent.RESONATE_10,pos);
                if(rng == 11) world.emitGameEvent(user, GameEvent.RESONATE_11,pos);
                if(rng == 12) world.emitGameEvent(user, GameEvent.RESONATE_12,pos);
                if(rng == 13) world.emitGameEvent(user, GameEvent.RESONATE_13,pos);
                if(rng == 14) world.emitGameEvent(user, GameEvent.RESONATE_14,pos);
                if(rng == 15) world.emitGameEvent(user, GameEvent.RESONATE_15,pos);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
