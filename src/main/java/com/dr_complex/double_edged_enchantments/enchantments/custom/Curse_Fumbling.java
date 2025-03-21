package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public record Curse_Fumbling() implements EnchantmentEntityEffect {

    public static final MapCodec<Curse_Fumbling> CODEC = MapCodec.unit(Curse_Fumbling::new);
    private static int cooldown = 80;

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (context.owner() != null){
            if(context.owner() instanceof PlayerEntity player){
                if(cooldown < 0){
                    player.dropStack(world,player.getInventory().getMainHandStack());
                    player.getInventory().removeStack(player.getInventory().selectedSlot);
                    cooldown = world.random.nextBetween(200 - level * 10,250 - level*12);
                    player.sendMessage(Text.literal("oops"),true);
                }else {
                    cooldown --;
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
