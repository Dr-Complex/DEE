package com.dr_complex.double_edged_enchantments.item.custom;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.IndexedIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JettatuimToolItem extends Item {
    float CursePower = 0.0f;
    public JettatuimToolItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, @NotNull ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        CursePower = 0f;
        if(entity.getWeaponStack() == (stack)){
            IndexedIterable<RegistryEntry<Enchantment>> entries = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getIndexedEntries();
            if(entries != null && stack.hasEnchantments()) {
                entries.forEach(map -> map.isIn(EnchantmentTags.CURSE));
                for (int index = 0; index < entries.size(); index++) {
                    CursePower += stack.getEnchantments().getLevel(entries.get(index));
                }
            }
        }

        DEE_Common.LOGGER.info(String.valueOf(CursePower));

        super.inventoryTick(stack, world, entity, slot);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return super.getBonusAttackDamage(target, baseAttackDamage * (CursePower + 1), damageSource);
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return super.getMiningSpeed(stack, state) * (CursePower + 1);
    }
}
