package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin extends ProjectileEntity {
    @Mutable
    @Shadow @Final private int luckBonus;

    @Mutable
    @Shadow @Final private int waitTimeReductionTicks;

    @Shadow public abstract @Nullable PlayerEntity getPlayerOwner();

    @Shadow protected abstract boolean removeIfInvalid(PlayerEntity player);

    public FishingBobberEntityMixin(EntityType<? extends FishingBobberEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;II)V",at = @At("TAIL"))
    public void Reworked(EntityType<FishingBobberEntity> type, World world, int luckBonus, int waitTimeReductionTicks, CallbackInfo ci){
        this.luckBonus = luckBonus;
        this.waitTimeReductionTicks = waitTimeReductionTicks;
    }

    @Inject(method = "use", at = @At("HEAD"))
    private void SpawnEntities(ItemStack usedItem, CallbackInfoReturnable<Integer> cir){
        Registry<Enchantment> manager = getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntry<Enchantment> Cures = manager.getEntry(manager.get(DEE_Enchantments.CURSE_GUARDIAN_LURE.getValue()));
        PlayerEntity playerEntity = this.getPlayerOwner();
        int amount = usedItem.getEnchantments().getLevel(Cures);

        if (!this.getWorld().isClient && playerEntity != null && !this.removeIfInvalid(playerEntity)) {
            for (int k = 0; k < amount; k++){
                GuardianEntity entity = new GuardianEntity(EntityType.GUARDIAN, getWorld());
                entity.setPos(this.getX(), this.getY(), this.getZ());
                entity.setHealth(3.5f);
                entity.setVelocity(
                        -MathHelper.sin((k/(float) amount) * MathHelper.TAU)*0.1,
                        0.25,
                        MathHelper.cos((k/(float) amount) * MathHelper.TAU)*0.1);
                this.getWorld().spawnEntity(entity);
                usedItem.setDamage(usedItem.getDamage() - k);
            }
        }
    }
}
