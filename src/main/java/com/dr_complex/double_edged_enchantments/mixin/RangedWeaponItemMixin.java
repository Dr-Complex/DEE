package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(RangedWeaponItem.class)
public abstract class RangedWeaponItemMixin {
    @Shadow protected abstract void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target);

    @Shadow protected abstract ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical);

    @Shadow protected abstract int getWeaponStackDamage(ItemStack projectile);

    @Inject(method = "shootAll",at = @At("HEAD"), cancellable = true)
    private void ReworkShooting(ServerWorld world, LivingEntity shooter, Hand hand, ItemStack stack, @NotNull List<ItemStack> projectiles, float speed, float divergence, boolean critical, @Nullable LivingEntity target, CallbackInfo ci) {

        float f = EnchantmentHelper.getProjectileSpread(world, stack, shooter, 0.0F);
        float g = projectiles.size() == 1 ? 0.0F : 2.0F * f / (float) (projectiles.size() - 1);
        float h = (float) ((projectiles.size() - 1) % 2) * g / 2.0F;
        float i = 1.0F;
        float good = 0.0f;
        float bad = 0.0f;
        int Level;

        if(stack.hasEnchantments() && !world.isClient){
            List<String> enchantments = stack.getEnchantments().getEnchantments().stream().map(RegistryEntry::getIdAsString).toList();
            String CSpread = DEE_Enchantments.CURSE_SPREAD.getValue().toString();
            String CDrag = DEE_Enchantments.CURSE_DRAG.getValue().toString();
            String EAccurate = DEE_Enchantments.ENCHANTMENT_ACCURATE.getValue().toString();
            String EAero = DEE_Enchantments.ENCHANTMENT_AERODYNAMIC.getValue().toString();
            for (int j = 0; j< enchantments.size(); j++) {
                if (Objects.equals(enchantments.get(j), CSpread)) {
                    Level = stack.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(j);
                    divergence += Level/5.5f;
                }
                if (Objects.equals(enchantments.get(j), EAccurate)) {
                    Level = stack.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(j);
                    divergence -= Level/7f;
                }
                if (Objects.equals(enchantments.get(j), CDrag)) {
                    Level = stack.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(j);
                    bad = Level;
                }
                if (Objects.equals(enchantments.get(j), EAero)) {
                    Level = stack.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(j);
                    good = Level;
                }

            }
        }

        for (int j = 0; j < projectiles.size(); j++) {
            ItemStack itemStack = projectiles.get(j);
            if (!itemStack.isEmpty()) {
                float k = h + i * (float) ((j + 1) / 2) * g;
                i = -i;
                int l = j;
                float finalDivergence = divergence;
                float finalSpeed = speed * ((good + 10f)/(1.35f*bad + 10f));
                ProjectileEntity.spawn(
                        this.createArrowEntity(world, shooter, stack, itemStack, critical),
                        world,
                        itemStack,
                        projectile -> this.shoot(shooter, projectile, l, finalSpeed, finalDivergence, k, target));
                stack.damage(this.getWeaponStackDamage(itemStack), shooter, LivingEntity.getSlotForHand(hand));
                if (stack.isEmpty()) {
                    break;
                }
            }
        }
        ci.cancel();
    }
}
