package com.dr_complex.double_edged_enchantments.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RangedWeaponItem.class)
public class RangedWeaponMixin {

    @Inject(method = "getProjectile",at = @At("HEAD"), cancellable = true)
    private static void ReworkedCloning(ItemStack stack, @NotNull ItemStack projectileStack, @NotNull LivingEntity shooter, boolean multishot, CallbackInfoReturnable<ItemStack> cir){
        int i = !shooter.isInCreativeMode() && shooter.getWorld() instanceof ServerWorld serverWorld
                ? EnchantmentHelper.getAmmoUse(serverWorld, stack, projectileStack, 1)
                : 0;

        if (i > projectileStack.getCount()) {
            cir.setReturnValue(ItemStack.EMPTY);
        } else if (i == 0) {
            ItemStack itemStack = projectileStack.copyWithCount(1);
            itemStack.set(DataComponentTypes.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
            cir.setReturnValue(itemStack);
        } else if (i == 2) {
            ItemStack itemStack = projectileStack.split(i);
            if (projectileStack.isEmpty() && shooter instanceof PlayerEntity playerEntity) {
                playerEntity.getInventory().removeOne(projectileStack);
                playerEntity.getInventory().removeOne(projectileStack);
            }
            cir.setReturnValue(itemStack);
        } else {
            ItemStack itemStack = projectileStack.split(i);
            if (projectileStack.isEmpty() && shooter instanceof PlayerEntity playerEntity) {
                playerEntity.getInventory().removeOne(projectileStack);
            }
            cir.setReturnValue(itemStack);
        }
        cir.cancel();
    }

}
