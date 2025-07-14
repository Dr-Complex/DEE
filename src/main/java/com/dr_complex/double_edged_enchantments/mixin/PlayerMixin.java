package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import com.dr_complex.double_edged_enchantments.utils.DEE_Tags;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.util.collection.IndexedIterable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity{

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public abstract PlayerInventory getInventory();

    @Shadow public abstract @NotNull ItemStack getWeaponStack();

    @Shadow public abstract float getAttackCooldownProgressPerTick();

    @Unique
    float CursePower = 0f;

    @Unique
    private static final TrackedData<Integer> double_edged_enchantments_StunTime = DataTracker.registerData(
            PlayerMixin.class,TrackedDataHandlerRegistry.INTEGER);

    @Inject(method = "initDataTracker",at = @At("TAIL"))
    private void init(DataTracker.@NotNull Builder builder, CallbackInfo ci){
        builder.add(double_edged_enchantments_StunTime,0);

    }

    @Inject(method = "tick",at = @At("HEAD"))
    private void re_tick(CallbackInfo ci){
        CursePower = 0f;

        if(this.dataTracker.get(double_edged_enchantments_StunTime) > 0) {
            this.dataTracker.set(double_edged_enchantments_StunTime,this.dataTracker.get(double_edged_enchantments_StunTime) - 1);
            this.movementMultiplier = new Vec3d(0,-2 * this.getVelocity().y,0);
        }else {
            this.movementMultiplier = new Vec3d(0,0,0);
        }

        List<EquipmentSlot> slotList = List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
        IndexedIterable<RegistryEntry<Enchantment>> entries = this.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getIndexedEntries();
        if(entries != null){
            entries.forEach(map -> map.isIn(EnchantmentTags.CURSE));

            for(EquipmentSlot slot : slotList){
                if(this.getEquippedStack(slot).hasEnchantments() && this.getEquippedStack(slot).isIn(DEE_Tags.Items.JATTATIUM_ARMOR)) {
                    for (int index = 0; index < entries.size(); index++) {
                        CursePower += this.getMainHandStack().getEnchantments().getLevel(entries.get(index));
                    }
                }
            }

        }else {
            DEE_Common.LOGGER.error("Curses are null!");
        }

        this.setMovementSpeed(this.getMovementSpeed() + CursePower/100);
    }

    @Inject(method = "attack", at = @At("HEAD"))
    private void setStunTime(Entity target, CallbackInfo ci){
        if(this.getInventory().getSelectedStack().hasEnchantments() && this.dataTracker.get(double_edged_enchantments_StunTime) <= 0){
            var test1 = this.getInventory().getSelectedStack().getEnchantments().getEnchantments().stream().map(RegistryEntry::getIdAsString).toList();
            var test2 = DEE_Enchantments.CURSE_STUNNED.getValue().toString();
            var level = this.getInventory().getSelectedStack().getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList();
            for (int i = 0; i < test1.size(); i++) {
                if(test1.get(i).matches(test2)){
                    this.dataTracker.set(double_edged_enchantments_StunTime,
                            level.get(i) * 12
                    );
                }
            }
        }
    }

    @Inject(method = "getBlockInteractionRange",at =@At("TAIL"), cancellable = true)
    private void RangeBlock(CallbackInfoReturnable<Double> cir){
        ItemStack ITEM_STACK = this.getMainHandStack();
        double AddedRange = 0;
        World world = getWorld();
        Registry<Enchantment> manager = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntry<Enchantment> Curse = manager.getEntry(manager.get(DEE_Enchantments.CURSE_SHORTNESS.getValue()));
        RegistryEntry<Enchantment> Enchantment = manager.getEntry(manager.get(DEE_Enchantments.ENCHANTMENT_RANGE.getValue()));

        if(ITEM_STACK.isIn(DEE_Tags.Items.SPEAR_WEAPONS)){
            AddedRange = 3.5;
        }

        AddedRange += ITEM_STACK.getEnchantments().getLevel(Enchantment) * 0.75;
        AddedRange -= ITEM_STACK.getEnchantments().getLevel(Curse) * 0.5;

        cir.setReturnValue(this.getAttributeValue(EntityAttributes.BLOCK_INTERACTION_RANGE) + AddedRange + CursePower);
    }

    @Inject(method = "getEntityInteractionRange",at =@At("TAIL"), cancellable = true)
    private void RangeEntity(CallbackInfoReturnable<Double> cir){
        ItemStack ITEM_STACK = this.getMainHandStack();
        double AddedRange = 0;
        World world = getWorld();
        Registry<Enchantment> manager = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntry<Enchantment> Curse = manager.getEntry(manager.get(DEE_Enchantments.CURSE_SHORTNESS.getValue()));
        RegistryEntry<Enchantment> Enchantment = manager.getEntry(manager.get(DEE_Enchantments.ENCHANTMENT_RANGE.getValue()));

        if(ITEM_STACK.isIn(DEE_Tags.Items.SPEAR_WEAPONS)){
            AddedRange = 3.5;
        }

        AddedRange += ITEM_STACK.getEnchantments().getLevel(Enchantment) * 0.75;
        AddedRange -= ITEM_STACK.getEnchantments().getLevel(Curse) * 0.5;

        cir.setReturnValue(this.getAttributeValue(EntityAttributes.ENTITY_INTERACTION_RANGE) + AddedRange + CursePower);
    }

    @Inject(method = "getAttackCooldownProgressPerTick", at = @At("HEAD"), cancellable = true)
    private void ShorterAttackCooldown(@NotNull CallbackInfoReturnable<Float> cir){
       cir.setReturnValue((float) ((1.0f / this.getAttributeValue(EntityAttributes.ATTACK_SPEED) * 20.0f) + (CursePower/2000f)));
    }
}