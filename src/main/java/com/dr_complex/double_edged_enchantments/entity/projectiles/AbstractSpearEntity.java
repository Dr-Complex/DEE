package com.dr_complex.double_edged_enchantments.entity.projectiles;

import com.dr_complex.double_edged_enchantments.utils.SpearType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class AbstractSpearEntity extends PersistentProjectileEntity {

    private static final TrackedData<Byte> LOYALTY = DataTracker.registerData(AbstractSpearEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(AbstractSpearEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Float> DAMAGE = DataTracker.registerData(AbstractSpearEntity.class,TrackedDataHandlerRegistry.FLOAT);
    private static final TrackedData<Integer> SpearMaterial = DataTracker.registerData(AbstractSpearEntity.class,TrackedDataHandlerRegistry.INTEGER);
    private boolean dealtDamage;
    private int returnTimer;

    public AbstractSpearEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world, float damage, int spearMaterial) {
        super(entityType, world);
        this.dataTracker.set(SpearMaterial, spearMaterial);
        this.dataTracker.set(DAMAGE, damage);
    }

    public AbstractSpearEntity(EntityType<? extends PersistentProjectileEntity> type, double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon, float damage, int spearMaterial) {
        super(type, x, y, z, world, stack, weapon);
        this.dataTracker.set(ENCHANTED, stack.hasGlint());
        this.dataTracker.set(SpearMaterial, spearMaterial);
        this.dataTracker.set(DAMAGE, damage);
        this.dataTracker.set(LOYALTY, this.getLoyalty(stack));
    }

    public AbstractSpearEntity(EntityType<? extends PersistentProjectileEntity> type, World world, LivingEntity owner, ItemStack itemstack, float damage, int spearMaterial){
        super(type,owner,world,itemstack, null);
        this.dataTracker.set(ENCHANTED, itemstack.hasGlint());
        this.dataTracker.set(SpearMaterial, spearMaterial);
        this.dataTracker.set(DAMAGE, damage);
        this.dataTracker.set(LOYALTY, this.getLoyalty(itemstack));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ENCHANTED,false);
        builder.add(LOYALTY, (byte) 0);
        builder.add(DAMAGE, 0.1f);
        builder.add(SpearMaterial, 0);
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        int i = this.dataTracker.get(LOYALTY);
        if (i > 0 && (this.dealtDamage || this.isNoClip()) && entity != null) {
            if (!this.isOwnerAlive()) {
                if (this.getWorld() instanceof ServerWorld serverWorld && this.pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(serverWorld, this.asItemStack(), 0.1F);
                }

                this.discard();
            } else {
                if (!(entity instanceof PlayerEntity) && this.getPos().distanceTo(entity.getEyePos()) < (double)entity.getWidth() + 1.0) {
                    this.discard();
                    return;
                }

                this.setNoClip(true);
                Vec3d vec3d = entity.getEyePos().subtract(this.getPos());
                this.setPos(this.getX(), this.getY() + vec3d.y * 0.015 * (double)i, this.getZ());
                double d = 0.05 * (double)i;
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));
                if (this.returnTimer == 0) {
                    this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                }

                this.returnTimer++;
            }
        }

        super.tick();
    }

    public SpearType getTypeSpear(){
        return SpearType.byId(this.dataTracker.get(SpearMaterial));
    }

    public boolean isEnchanted(){
        return this.dataTracker.get(ENCHANTED);
    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayerEntity) || !entity.isSpectator());
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player) || this.isNoClip() && this.isOwner(player) && player.getInventory().insertStack(this.asItemStack());
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.BLOCK_SCAFFOLDING_HIT;
    }

    @Override
    protected void age() {
        int i = this.dataTracker.get(LOYALTY);
        if (this.pickupType != PickupPermission.ALLOWED || i <= 0) {
            super.age();
        }
    }

    private byte getLoyalty(ItemStack stack) {
        return this.getWorld() instanceof ServerWorld serverWorld
                ? (byte) MathHelper.clamp(EnchantmentHelper.getTridentReturnAcceleration(serverWorld, stack, this), 0, 127)
                : 0;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dealtDamage = nbt.getBoolean("DealtDamage");
        this.dataTracker.set(LOYALTY, this.getLoyalty(this.getItemStack()));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }
    }


    @Override
    protected void onBlockHitEnchantmentEffects(ServerWorld world, BlockHitResult blockHitResult, ItemStack weaponStack) {
        Vec3d vec3d = blockHitResult.getBlockPos().clampToWithin(blockHitResult.getPos());
        EnchantmentHelper.onHitBlock(
                world,
                weaponStack,
                this.getOwner() instanceof LivingEntity livingEntity ? livingEntity : null,
                this,
                null,
                vec3d,
                world.getBlockState(blockHitResult.getBlockPos()),
                item -> this.kill(world)
        );
    }

    @Override
    public ItemStack getWeaponStack() {
        return this.getItemStack();
    }

    @Nullable
    @Override
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = this.dataTracker.get(DAMAGE);
        Entity Owner = this.getOwner();
        DamageSource damageSource = this.getDamageSources().trident(this, (Owner == null ? this : Owner));
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            f = EnchantmentHelper.getDamage(serverWorld, Objects.requireNonNull(this.getWeaponStack()), entity, damageSource, f);
            this.dealtDamage = true;
            if(entity.damage(serverWorld,damageSource,f)){
                if (entity.getType() == EntityType.ENDERMAN) {
                    return;
                }

                EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource, this.getWeaponStack(), item -> this.kill(serverWorld));

                if (entity instanceof LivingEntity livingEntity) {
                    this.knockback(livingEntity, damageSource);
                    this.onHit(livingEntity);
                }
            }
        }


        this.deflect(ProjectileDeflection.SIMPLE, entity, Owner, false);
        this.setVelocity(this.getVelocity().multiply(0.02, 0.2, 0.02));
        this.playSound(SoundEvents.BLOCK_SCAFFOLDING_HIT, 1.0F, 1.0F);
    }

}
