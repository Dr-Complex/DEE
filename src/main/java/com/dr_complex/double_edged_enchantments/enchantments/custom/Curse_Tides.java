package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.consume.UseAction;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

public record Curse_Tides() implements EnchantmentEntityEffect {

    public static final MapCodec<Curse_Tides> CODEC = MapCodec.unit(Curse_Tides::new);

    @Override
    public void apply(ServerWorld world, int level, @NotNull EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if(context.owner() != null){
            if(context.owner() instanceof LivingEntity living) {
                if(context.stack().getUseAction().equals(UseAction.SPEAR)){
                    if(context.owner().isUsingItem()){
                        living.teleport(world,
                                0,
                                0,
                                0,
                                PositionFlag.VALUES,
                                (world.random.nextFloat() - 0.5f) * MathHelper.sqrt(level * level + level * level),
                                (world.random.nextFloat() - 0.5f) * (level),
                                false);
                    }
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
