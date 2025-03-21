package com.dr_complex.double_edged_enchantments.mixin;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {

    @Shadow private @Nullable ItemStack weapon;

    @Inject(method = "knockback",at = @At("HEAD"))
    private void ReworkKnockBack(LivingEntity target, DamageSource source, CallbackInfo ci){
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        double d = (
                this.weapon != null && projectile.getWorld() instanceof ServerWorld serverWorld
                        ? EnchantmentHelper.modifyKnockback(serverWorld, this.weapon, target, source, 0.0F)
                        : 0.0F
        );
        if (d != 0.0) {
            double e = Math.max(0.0, 1.0 - target.getAttributeValue(EntityAttributes.KNOCKBACK_RESISTANCE));
            Vec3d vec3d = projectile.getVelocity().multiply(1.0, 0.0, 1.0).normalize().multiply(d * 0.6 * e);
            if (vec3d.lengthSquared() > 0.0) {
                target.addVelocity(vec3d.x, 0.1, vec3d.z);
            }
        }
    }
}
