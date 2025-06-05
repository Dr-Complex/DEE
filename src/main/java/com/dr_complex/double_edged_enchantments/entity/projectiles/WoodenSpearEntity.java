package com.dr_complex.double_edged_enchantments.entity.projectiles;

import com.dr_complex.double_edged_enchantments.entity.DEE_Entities;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import com.dr_complex.double_edged_enchantments.item.custom.WoodenSpearItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WoodenSpearEntity extends AbstractSpearEntity {

    public WoodenSpearEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world, WoodenSpearItem.damage, 0);
    }

    public WoodenSpearEntity(double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(DEE_Entities.WOODEN_SPEAR_ENTITY_TYPE, x, y, z, world, stack, weapon,WoodenSpearItem.damage, 0);
    }

    public WoodenSpearEntity(World world, LivingEntity player, ItemStack itemStack) {
        super(DEE_Entities.WOODEN_SPEAR_ENTITY_TYPE, world, player, itemStack,WoodenSpearItem.damage, 0);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(DEE_Items.WOODEN_SPEAR);
    }

}
