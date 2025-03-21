package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

public record Curse_Evaporation() implements EnchantmentEntityEffect {

    public static final MapCodec<Curse_Evaporation> CODEC = MapCodec.unit(Curse_Evaporation::new);

    @Override
    public void apply(ServerWorld world, int level, @NotNull EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if(context.owner() != null){
            int radius = MathHelper.ceilLog2(level + 1);
            for(int x = -radius; x < radius; x++){
                for (int y = -radius; y < radius ; y++) {
                    for (int z = -radius; z < radius; z++) {
                       BlockState blockState = world.getBlockState(context.owner().getBlockPos().add(x,y,z));
                       if(blockState.equals(Blocks.WATER.getDefaultState())){
                           world.setBlockState(context.owner().getBlockPos().add(x,y,z),Blocks.AIR.getDefaultState());
                       }
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
