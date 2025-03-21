package com.dr_complex.double_edged_enchantments.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobMixin extends LivingMixin {

    @Shadow private @Nullable LivingEntity target;

    public MobMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tryAttack", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/mob/MobEntity;getKnockbackAgainst(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;)F"))
    private void ReworkKnockBack(ServerWorld world, Entity target, CallbackInfoReturnable<Boolean> cir){
        MobEntity mob = (MobEntity) (Object) this;
        DamageSource damageSource = mob.getDamageSources().mobAttack(mob);
        float KB = getKnockbackAgainst(target,damageSource) + (mob.isSprinting() ? 1.0f:0.0f);
        if(KB < 0.0f){
            if (target instanceof LivingEntity living){
                living.takeKnockback(
                        KB * 0.5,
                        MathHelper.sin((float) (mob.getYaw() * (MathHelper.PI / 180.0))),
                        -MathHelper.cos((float) (mob.getYaw() * (MathHelper.PI / 180.0)))
                );
            }
        }
        this.setVelocity(this.getVelocity().multiply(0.6, 1.0, 0.6));
        this.setSprinting(false);
    }
}
