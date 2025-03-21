package com.dr_complex.double_edged_enchantments.enchantments.custom;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.include.com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public record Enchantment_Return() implements EnchantmentEntityEffect {

    public static ArrayList<ItemStack> returnable = new ArrayList<>(64);
    private static int cooldown = 25;
    public static final MapCodec<Enchantment_Return> CODEC = MapCodec.unit(Enchantment_Return::new);

    @Override
    public void apply(ServerWorld world, int level,EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if(context.owner() != null){
            if(context.owner() instanceof PlayerEntity player){
                if(cooldown < 0) {
                    if(!returnable.isEmpty()){
                        for(int L = 0; L < returnable.size(); L++){
                            returnable.removeFirst();
                        }
                    }

                    for (int i = 0; i < player.getInventory().size(); i++) {
                        if (!player.getInventory().getStack(i).getEnchantments().isEmpty()) {
                            List<String> enchantments = player.getInventory().getStack(i).getEnchantments().getEnchantments().stream().map(RegistryEntry::getIdAsString).toList();
                            for (String enchantment : enchantments) {
                                if (Objects.equal(enchantment, DEE_Enchantments.ENCHANTMENT_RETURN.getValue().toString())) {
                                    returnable.add(player.getInventory().getStack(i));
                                }
                            }
                        }
                    }
                    cooldown = 25;
                }else {
                   cooldown--;
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
