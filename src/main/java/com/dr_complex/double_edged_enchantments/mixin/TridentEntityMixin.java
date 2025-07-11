package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentEntity.class)
public class TridentEntityMixin extends PersistentProjectileEntity {

    @Unique
    private boolean DidTelePort = false;

    protected TridentEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getDefaultItemStack() {
        return new ItemStack(Items.TRIDENT);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void DuplicateYourself(CallbackInfo ci){
        World world = getWorld();
        Registry<Enchantment> manager = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntry<Enchantment> Curse = manager.getEntry(manager.get(DEE_Enchantments.CURSE_DECEITFULNESS.getValue()));

        if(getItemStack().hasEnchantments()){
            if(getItemStack().getEnchantments().getLevel(Curse) > 0 && this.isInGround() && !this.DidTelePort){
                int level = getItemStack().getEnchantments().getLevel(Curse);
                this.setPos(
                        this.getX() + level - world.random.nextFloat() * 2 * level,
                        this.getY() + level - world.random.nextFloat() * 2 * level,
                        this.getZ() + level - world.random.nextFloat() * 2 * level
                );
                this.DidTelePort = true;
            }
        }

    }
}
