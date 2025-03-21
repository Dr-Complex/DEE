package com.dr_complex.double_edged_enchantments.entity.projectiles;

import com.dr_complex.double_edged_enchantments.entity.DEE_Entities;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NetheriteSpearEntity extends AbstractSpearEntity {

    public NetheriteSpearEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world, 11f, 5);
    }

    public NetheriteSpearEntity(double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(DEE_Entities.NETHERITE_SPEAR_ENTITY_TYPE, x, y, z, world, stack, weapon,11f, 5);
    }

    public NetheriteSpearEntity(World world, LivingEntity player, ItemStack itemStack) {
        super(DEE_Entities.NETHERITE_SPEAR_ENTITY_TYPE, world, player, itemStack,11f, 5);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(DEE_Items.NETHERITE_SPEAR);
    }

}
