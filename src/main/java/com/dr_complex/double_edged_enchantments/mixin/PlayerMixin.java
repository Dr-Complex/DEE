package com.dr_complex.double_edged_enchantments.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity {

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack",at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/player/PlayerEntity;getKnockbackAgainst(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;)F"))
    private void ReworkKnockBack(Entity target, CallbackInfo ci){
        PlayerEntity player = (PlayerEntity) (Object) this;
        DamageSource damageSource = player.getDamageSources().playerAttack(player);
        float KB = getKnockbackAgainst(target,damageSource) + (player.isSprinting() ? 1.0f:0.0f);
        if(KB < 0.0f){
            if(target instanceof LivingEntity living){
                living.takeKnockback(
                        KB * 0.5,
                        MathHelper.sin((float) (player.getYaw() * (MathHelper.PI / 180.0))),
                        -MathHelper.cos((float) (player.getYaw() * (MathHelper.PI / 180.0)))
                );
            }else {
                target.addVelocity(
                        -MathHelper.sin((float) (player.getYaw() * (MathHelper.PI / 180.0) * KB * 0.5f)),
                        0.1,
                        MathHelper.cos((float) ((player.getYaw()) * (MathHelper.PI / 180.0) * KB * 0.5f))
                );
            }
            this.setVelocity(this.getVelocity().multiply(0.6, 1.0, 0.6));
            this.setSprinting(false);
        }
    }

}
