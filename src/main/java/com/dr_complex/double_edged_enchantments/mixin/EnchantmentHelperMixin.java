package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    @Inject(method = "getAmmoUse", at = @At("HEAD"), cancellable = true)
    private static void RevertToFloat(ServerWorld world, @NotNull ItemStack rangedWeaponStack, ItemStack projectileStack, int baseAmmoUse, CallbackInfoReturnable<Integer> cir){
        float ShootChance = (float)baseAmmoUse;
        if(rangedWeaponStack.hasEnchantments()){
            var ItemEnchantments = rangedWeaponStack.getEnchantments().getEnchantments().stream().map(RegistryEntry::getIdAsString).toList();
            var ItemLevels = rangedWeaponStack.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList();
            var Enchantment = Enchantments.INFINITY.getValue().toString();
            var Curse = DEE_Enchantments.CURSE_CONSUMPTION.getValue().toString();
            for (int i = 0; i < ItemEnchantments.size(); i++) {
                if(ItemEnchantments.get(i).matches(Enchantment)){
                    ShootChance += ItemLevels.get(i)/25f;
                }
                if(ItemEnchantments.get(i).matches(Curse)){
                    ShootChance -= ItemLevels.get(i)/16f;
                }
            }
        }
        int returnable = 1;
        if(ShootChance > 1){
            returnable = (world.random.nextFloat() >= 2 - ShootChance ? 0:1);
        } else if (ShootChance < 1) {
            returnable = (world.random.nextFloat() >= ShootChance ? 2:1);
        }
        cir.setReturnValue(returnable);
    }

}
