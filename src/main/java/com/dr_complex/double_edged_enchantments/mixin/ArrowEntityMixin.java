package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends PersistentProjectileEntity {

    @Unique
    private static final TrackedData<Integer> level_of_smoke = DataTracker.registerData(ArrowEntityMixin.class, TrackedDataHandlerRegistry.INTEGER);

    @Unique
    private static final TrackedData<Integer> level_of_redirect = DataTracker.registerData(ArrowEntityMixin.class, TrackedDataHandlerRegistry.INTEGER);

    @Unique
    private float turn = 0.0f;

    protected ArrowEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initDataTracker", at =@At("TAIL"))
    protected void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(level_of_smoke,0);
        builder.add(level_of_redirect,0);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V",at = @At("TAIL"))
    private void Reworked(World world, LivingEntity owner, ItemStack stack, @NotNull ItemStack shotFrom, CallbackInfo ci){
        if(shotFrom.hasEnchantments()){
            var Enchantments = shotFrom.getEnchantments().getEnchantments().stream().map(RegistryEntry::getIdAsString).toList();
            var SL = DEE_Enchantments.CURSE_SMOKE.getValue().toString();
            var RL = DEE_Enchantments.CURSE_REDIRECT.getValue().toString();
            for (int i = 0; i < Enchantments.size(); i++) {
                if(Enchantments.get(i).matches(SL)){
                    this.dataTracker.set(level_of_smoke,shotFrom.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(i));
                }
                if(Enchantments.get(i).matches(RL)){
                    this.dataTracker.set(level_of_redirect,shotFrom.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(i));
                }
            }
        }
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)V",at = @At("TAIL"))
    private void Reworked(World world, double x, double y, double z, ItemStack stack, @NotNull ItemStack shotFrom, CallbackInfo ci){
        if(shotFrom.hasEnchantments()){
            var Enchantments = shotFrom.getEnchantments().getEnchantments().stream().map(RegistryEntry::getIdAsString).toList();
            var SL = DEE_Enchantments.CURSE_SMOKE.getValue().toString();
            var RL = DEE_Enchantments.CURSE_REDIRECT.getValue().toString();
            for (int i = 0; i < Enchantments.size(); i++) {
                if(Enchantments.get(i).matches(SL)){
                    this.dataTracker.set(level_of_smoke,shotFrom.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(i));
                }
                if(Enchantments.get(i).matches(RL)){
                    this.dataTracker.set(level_of_redirect,shotFrom.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList().get(i));
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"), cancellable = true)
    private void spawnParticles(CallbackInfo ci) {
        if(!this.getWorld().isClient){
            ci.cancel();
        }else if(this.getWorld().random.nextFloat() >= 1f/(this.dataTracker.get(level_of_smoke) + 1)){
            if(!this.isInGround()){
                this.getWorld().addParticle(
                        ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                        this.getParticleX(0.25),
                        this.getRandomBodyY(),
                        this.getParticleZ(0.25),
                        0, 0.01f, 0
                );
            }else {
                this.turn += 75f;
                this.getWorld().addParticle(
                        ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
                        this.getParticleX(0.25),
                        this.getRandomBodyY(),
                        this.getParticleZ(0.25),
                        -MathHelper.sin((this.turn * MathHelper.TAU) / 360.0f)/50f, 0.375f, MathHelper.cos((this.turn * MathHelper.TAU) / 360.0f)/50f
                );
                if(turn >= 360f){
                    this.turn = this.turn % 360f;
                }
            }
        }
    }

    @Inject(method = "onHit",at = @At("TAIL"))
    private void Redirecting(LivingEntity target, CallbackInfo ci){
        target.timeUntilRegen = 0;
        if(this.dataTracker.get(level_of_redirect) > 0){
            float power = 1f/(this.dataTracker.get(level_of_redirect) + 1f);
            this.deflect(ProjectileDeflection.SIMPLE,target,this.getOwner(),false);
            this.setVelocity(this.getVelocity().multiply(1 - power));
            this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.0F);
        }
    }
}
