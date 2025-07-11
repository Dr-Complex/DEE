package com.dr_complex.double_edged_enchantments.enchantments;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.enchantments.custom.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class DEE_Enchantment_Effects {

    public static final MapCodec<? extends EnchantmentEntityEffect> ENCHANTMENT_RETURN = registerEntityEffect("enchantment_return", Enchantment_Return.CODEC);

    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_FUMBLING = registerEntityEffect("curse_fumbling", Curse_Fumbling.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_RESONATING = registerEntityEffect("curse_resonating", Curse_Resonating.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_STONES = registerEntityEffect("curse_stones", Curse_Stones.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_TIDES = registerEntityEffect("curse_tides", Curse_Tides.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_WORSEN = registerEntityEffect("curse_worsen", Curse_Worsen.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_EVAPORATION = registerEntityEffect("curse_evaporation", Curse_Evaporation.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_Conductiveness = registerEntityEffect("curse_conductiveness", Curse_Conductiveness.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> STATUS_EFFECT_ADDER = registerEntityEffect("status_effect_adder", StatusEffectAdder.CODEC);

    public static final MapCodec<? extends EnchantmentEntityEffect> CURSE_DAMAGE_BACKLASH = registerEntityEffect("curse_damage_backlash", CursedDamageBacklash.CODEC);

    public static void LoadEnchantmentEffects(){
        DEE_Common.LOGGER.info("Enchantments are Loaded");
    }

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec){
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,DEE_Common.id(name),codec);
    }
}
