package com.dr_complex.double_edged_enchantments.datagen;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import com.dr_complex.double_edged_enchantments.utils.DEE_Tags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class DEE_EnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
    public DEE_EnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(EnchantmentTags.CURSE)
               .add(DEE_Enchantments.CURSE_UNLUCKY)
               .add(DEE_Enchantments.CURSE_CRUELNESS)
               .add(DEE_Enchantments.CURSE_ENTOMOPHOBIA)
               .add(DEE_Enchantments.CURSE_FUMBLING)
               .add(DEE_Enchantments.CURSE_RESONATING)
               .add(DEE_Enchantments.CURSE_REBUILDING)
               .add(DEE_Enchantments.CURSE_DULLNESS)
               .add(DEE_Enchantments.CURSE_FRAGILE)
               .add(DEE_Enchantments.CURSE_NARROW)
               .add(DEE_Enchantments.CURSE_IRON_MAIDEN)
               .add(DEE_Enchantments.CURSE_LOSS)
               .add(DEE_Enchantments.CURSE_UNENDING)
               .add(DEE_Enchantments.CURSE_IMPALED)
               .add(DEE_Enchantments.CURSE_THALASSOPHOBIA)
               .add(DEE_Enchantments.CURSE_JUPITER)
               .add(DEE_Enchantments.CURSE_STONES)
               .add(DEE_Enchantments.CURSE_SOULLESS)
               .add(DEE_Enchantments.CURSE_NECROPHOBIA)
               .add(DEE_Enchantments.CURSE_DROWNING)
               .add(DEE_Enchantments.CURSE_ASHES)
               .add(DEE_Enchantments.CURSE_HIDDEN)
               .add(DEE_Enchantments.CURSE_TIDES)
               .add(DEE_Enchantments.CURSE_FLAMMABLE)
               .add(DEE_Enchantments.CURSE_SMOKE)
               .add(DEE_Enchantments.CURSE_HOOK)
               .add(DEE_Enchantments.CURSE_SOFTNESS)
               .add(DEE_Enchantments.CURSE_VULNERABILITY)
               .add(DEE_Enchantments.CURSE_STUNNED)
               .add(DEE_Enchantments.CURSE_JAMMING)
               .add(DEE_Enchantments.CURSE_UNFIT)
               .add(DEE_Enchantments.CURSE_WORSEN)
               .add(DEE_Enchantments.CURSE_CONSUMPTION)
               .add(DEE_Enchantments.CURSE_VACUUM)
               .add(DEE_Enchantments.CURSE_SPREAD)
               .add(DEE_Enchantments.CURSE_DRAG)
               .add(DEE_Enchantments.CURSE_WEIGHTLESSNESS)
               .add(DEE_Enchantments.CURSE_REDIRECT)
               .add(DEE_Enchantments.CURSE_IMPLOSION)
               .add(DEE_Enchantments.CURSE_MISFORTUNE)
               .add(DEE_Enchantments.CURSE_DEEP)
               .add(DEE_Enchantments.CURSE_EVAPORATION)
               .add(DEE_Enchantments.CURSE_CONDUCTIVENESS);

        getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(DEE_Enchantments.ENCHANTMENT_LUCKY)
                .add(DEE_Enchantments.ENCHANTMENT_CRITICALITY)
                .add(DEE_Enchantments.ENCHANTMENT_REVEALING)
                .add(DEE_Enchantments.NM_SHRUNKEN)
                .add(DEE_Enchantments.NM_GROWTH)
                .add(DEE_Enchantments.ENCHANTMENT_ACCURATE)
                .add(DEE_Enchantments.ENCHANTMENT_AERODYNAMIC);

        getOrCreateTagBuilder(DEE_Tags.Enchantments.ALL_GOOD)
                .add(Enchantments.WIND_BURST)
                .add(Enchantments.UNBREAKING)
                .add(Enchantments.THORNS)
                .add(Enchantments.SWIFT_SNEAK)
                .add(Enchantments.SWEEPING_EDGE)
                .add(Enchantments.SOUL_SPEED)
                .add(Enchantments.SMITE)
                .add(Enchantments.SILK_TOUCH)
                .add(Enchantments.SHARPNESS)
                .add(Enchantments.RIPTIDE)
                .add(Enchantments.RESPIRATION)
                .add(Enchantments.QUICK_CHARGE)
                .add(Enchantments.PUNCH)
                .add(Enchantments.PROTECTION)
                .add(Enchantments.PROJECTILE_PROTECTION)
                .add(Enchantments.POWER)
                .add(Enchantments.PIERCING)
                .add(Enchantments.MULTISHOT)
                .add(Enchantments.MENDING)
                .add(Enchantments.LURE)
                .add(Enchantments.LUCK_OF_THE_SEA)
                .add(Enchantments.LOYALTY)
                .add(Enchantments.LOOTING)
                .add(Enchantments.KNOCKBACK)
                .add(Enchantments.INFINITY)
                .add(Enchantments.IMPALING)
                .add(Enchantments.FROST_WALKER)
                .add(Enchantments.FORTUNE)
                .add(Enchantments.FLAME)
                .add(Enchantments.FIRE_PROTECTION)
                .add(Enchantments.FEATHER_FALLING)
                .add(Enchantments.EFFICIENCY)
                .add(Enchantments.DEPTH_STRIDER)
                .add(Enchantments.DENSITY)
                .add(Enchantments.BREACH)
                .add(Enchantments.BLAST_PROTECTION)
                .add(Enchantments.BANE_OF_ARTHROPODS)
                .add(Enchantments.AQUA_AFFINITY)
                .add(DEE_Enchantments.NM_GROWTH)
                .add(DEE_Enchantments.NM_SHRUNKEN)
                .add(DEE_Enchantments.ENCHANTMENT_CRITICALITY)
                .add(DEE_Enchantments.ENCHANTMENT_AERODYNAMIC)
                .add(DEE_Enchantments.ENCHANTMENT_LUCKY)
                .add(DEE_Enchantments.ENCHANTMENT_ACCURATE)
                .add(DEE_Enchantments.ENCHANTMENT_MOON)
                .add(DEE_Enchantments.ENCHANTMENT_REVEALING)
                .add(DEE_Enchantments.ENCHANTMENT_RETURN)
                .add(DEE_Enchantments.ENCHANTMENT_ABSORBING);
    }
}
