package com.dr_complex.double_edged_enchantments.enchantments;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.enchantments.custom.*;
import com.dr_complex.double_edged_enchantments.utils.DEE_Tags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.ApplyMobEffectEnchantmentEffect;
import net.minecraft.enchantment.effect.entity.SummonEntityEnchantmentEffect;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.enchantment.effect.value.RemoveBinomialEnchantmentEffect;
import net.minecraft.enchantment.effect.value.SetEnchantmentEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluids;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.provider.number.EnchantmentLevelLootNumberProvider;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.NotNull;

public class DEE_Enchantments {

    public static final RegistryKey<Enchantment> ENCHANTMENT_LUCKY = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_lucky"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_CRITICALITY = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_criticality"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_MOON = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_moon"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_RETURN = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_return"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_ACCURATE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_accurate"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_AERODYNAMIC = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_aerodynamic"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_ABSORBING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_absorbing"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_CLEAVING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_cleaving"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_RANGE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_range"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_SPEED = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_speed"));
    public static final RegistryKey<Enchantment> ENCHANTMENT_HOPE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("enchantment_hope"));

    public static final RegistryKey<Enchantment> CURSE_UNLUCKY = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_unlucky"));
    public static final RegistryKey<Enchantment> CURSE_CRUELNESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_cruelness"));
    public static final RegistryKey<Enchantment> CURSE_ENTOMOPHOBIA = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_entomophobia"));
    public static final RegistryKey<Enchantment> CURSE_FUMBLING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_fumbling"));
    public static final RegistryKey<Enchantment> CURSE_RESONATING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_resonating"));
    public static final RegistryKey<Enchantment> CURSE_REBUILDING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_rebuilding"));
    public static final RegistryKey<Enchantment> CURSE_DULLNESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_dullness"));
    public static final RegistryKey<Enchantment> CURSE_FRAGILE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_fragile"));
    public static final RegistryKey<Enchantment> CURSE_NARROW = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_narrow"));
    public static final RegistryKey<Enchantment> CURSE_IRON_MAIDEN = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_iron_maiden"));
    public static final RegistryKey<Enchantment> CURSE_LOSS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_loss"));
    public static final RegistryKey<Enchantment> CURSE_UNENDING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_unending"));
    public static final RegistryKey<Enchantment> CURSE_IMPALED = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_impaled"));
    public static final RegistryKey<Enchantment> CURSE_THALASSOPHOBIA = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_thalassophobia"));
    public static final RegistryKey<Enchantment> CURSE_JUPITER = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_jupiter"));
    public static final RegistryKey<Enchantment> CURSE_SOULLESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_soulless"));
    public static final RegistryKey<Enchantment> CURSE_STONES = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_stones"));
    public static final RegistryKey<Enchantment> CURSE_DROWNING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_drowning"));
    public static final RegistryKey<Enchantment> CURSE_ASHES = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_ashes"));
    public static final RegistryKey<Enchantment> CURSE_NECROPHOBIA = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_necrophobia"));
    public static final RegistryKey<Enchantment> CURSE_SMOKE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_smoke"));
    public static final RegistryKey<Enchantment> CURSE_FLAMMABLE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_flammable"));
    public static final RegistryKey<Enchantment> CURSE_TIDES = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_tides"));
    public static final RegistryKey<Enchantment> CURSE_HOOK = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_hook"));
    public static final RegistryKey<Enchantment> CURSE_VULNERABILITY  = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_vulnerability"));
    public static final RegistryKey<Enchantment> CURSE_SOFTNESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_softness"));
    public static final RegistryKey<Enchantment> CURSE_STUNNED = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_stunned"));
    public static final RegistryKey<Enchantment> CURSE_UNFIT = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_unfit"));
    public static final RegistryKey<Enchantment> CURSE_JAMMING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_jamming"));
    public static final RegistryKey<Enchantment> CURSE_WORSEN = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_worsen"));
    public static final RegistryKey<Enchantment> CURSE_CONSUMPTION = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_consumption"));
    public static final RegistryKey<Enchantment> CURSE_VACUUM = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_vacuum"));
    public static final RegistryKey<Enchantment> CURSE_SPREAD = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_spread"));
    public static final RegistryKey<Enchantment> CURSE_DRAG = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_drag"));
    public static final RegistryKey<Enchantment> CURSE_WEIGHTLESSNESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_weightlessness"));
    public static final RegistryKey<Enchantment> CURSE_REDIRECT = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_redirect"));
    public static final RegistryKey<Enchantment> CURSE_IMPLOSION = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_implosion"));
    public static final RegistryKey<Enchantment> CURSE_MISFORTUNE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_misfortune"));
    public static final RegistryKey<Enchantment> CURSE_DEEP = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_deep"));
    public static final RegistryKey<Enchantment> CURSE_EVAPORATION = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_evaporation"));
    public static final RegistryKey<Enchantment> CURSE_CONDUCTIVENESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_conductiveness"));
    public static final RegistryKey<Enchantment> CURSE_THIEVING = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_thieving"));
    public static final RegistryKey<Enchantment> CURSE_DECEITFULNESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_deceitfulness"));
    public static final RegistryKey<Enchantment> CURSE_POLLUTED_OCEANS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_polluted_oceans"));
    public static final RegistryKey<Enchantment> CURSE_GUARDIAN_LURE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_guardian_lure"));
    public static final RegistryKey<Enchantment> CURSE_BLUNT = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_blunt"));
    public static final RegistryKey<Enchantment> CURSE_SHORTNESS = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_shortness"));
    public static final RegistryKey<Enchantment> CURSE_FATIGUE = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_fatigue"));
    public static final RegistryKey<Enchantment> CURSE_DESPAIR = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("curse_despair"));

    public static final RegistryKey<Enchantment> NM_GROWTH = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("neutral_magic_growth"));
    public static final RegistryKey<Enchantment> NM_SHRUNKEN = RegistryKey.of(RegistryKeys.ENCHANTMENT, DEE_Common.id("neutral_magic_shrunken"));

    public static void bootstrap(@NotNull Registerable<Enchantment> registerable){
        var itemsLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);
        var entryLookup = registerable.getRegistryLookup(RegistryKeys.ENTITY_TYPE);
        var entityTypeLookup = registerable.getRegistryLookup(RegistryKeys.ENTITY_TYPE);

        register(registerable,ENCHANTMENT_LUCKY,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.EQUIPPABLE_ENCHANTABLE),
                        itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                        10,10,
                        Enchantment.leveledCost(5,2),
                        Enchantment.leveledCost(10,2),2, AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(
                DEE_Common.id("enchantment_lucky"),EntityAttributes.LUCK,
                EnchantmentLevelBasedValue.linear(1,0.5f), EntityAttributeModifier.Operation.ADD_VALUE))
        );

        register(registerable, ENCHANTMENT_CRITICALITY,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                        itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        10,10,
                        Enchantment.leveledCost(5,2),
                        Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE,new AddEnchantmentEffect(EnchantmentLevelBasedValue.constant(8.5f)),
                RandomChanceLootCondition.builder(EnchantmentLevelLootNumberProvider.create(EnchantmentLevelBasedValue.linear(0.1f,0.09f))))
        );

        register(registerable, CURSE_UNLUCKY,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.EQUIPPABLE_ENCHANTABLE),
                        itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                        1,10,
                        Enchantment.leveledCost(5,2),
                        Enchantment.leveledCost(10,2),2, AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(
                DEE_Common.id("curse_unlucky"),EntityAttributes.LUCK,
                EnchantmentLevelBasedValue.linear(-0.125f,-0.625f), EntityAttributeModifier.Operation.ADD_VALUE))
        );

        register(registerable, CURSE_CRUELNESS,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                        itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        1,10,
                        Enchantment.leveledCost(5,2),
                        Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE,
                new AddEnchantmentEffect(EnchantmentLevelBasedValue.constant(-9.5f)),
                RandomChanceLootCondition.builder(EnchantmentLevelLootNumberProvider.create(
                EnchantmentLevelBasedValue.linear(0.144f,0.095f))))
        );

        register(registerable,CURSE_ENTOMOPHOBIA,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                        itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        1,10,
                        Enchantment.leveledCost(5,2),
                        Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE,
                     new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.625f)),
                     EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS,EntityPredicate.Builder.create().type(EntityTypePredicate.create(entryLookup, EntityTypeTags.ARTHROPOD)))
        ).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.VICTIM,
                    new ApplyMobEffectEnchantmentEffect(
                    RegistryEntryList.of(StatusEffects.INFESTED),
                    EnchantmentLevelBasedValue.linear(10f,10f),
                    EnchantmentLevelBasedValue.linear(30f, 15f),
                    EnchantmentLevelBasedValue.linear(0f,1f),
                    EnchantmentLevelBasedValue.linear(0f,1f)))
        );
        
        register(registerable, CURSE_FUMBLING,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ANY
        )).addEffect(EnchantmentEffectComponentTypes.TICK,new Curse_Fumbling()));

        register(registerable, CURSE_RESONATING,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.TICK,new Curse_Resonating()));

        register(registerable, CURSE_REBUILDING, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MACE_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.ARMOR_EFFECTIVENESS,
                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.0125f,0.2375f)))
        );

        register(registerable, CURSE_DULLNESS, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE,
                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.75f,-0.85f)))
        );

        register(registerable, CURSE_FRAGILE, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ANY
        )).addEffect(EnchantmentEffectComponentTypes.ITEM_DAMAGE,
                new RemoveBinomialEnchantmentEffect(new EnchantmentLevelBasedValue.Fraction(EnchantmentLevelBasedValue.linear(-8.0F),
                EnchantmentLevelBasedValue.linear(10.0F, 5.0F))),
                MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemsLookup, ItemTags.ARMOR_ENCHANTABLE))
        ).addEffect(EnchantmentEffectComponentTypes.ITEM_DAMAGE,
                new RemoveBinomialEnchantmentEffect(new EnchantmentLevelBasedValue.Fraction(EnchantmentLevelBasedValue.linear(-2.0F),
                EnchantmentLevelBasedValue.linear(2.0F, 1.0F))),
                InvertedLootCondition.builder(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(itemsLookup, ItemTags.ARMOR_ENCHANTABLE))))
        );

        register(registerable, CURSE_NARROW, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,
                new AttributeEnchantmentEffect(DEE_Common.id("curse_narrow"),
                EntityAttributes.SWEEPING_DAMAGE_RATIO,
                EnchantmentLevelBasedValue.linear(-0.3375f,-0.0625f),
                EntityAttributeModifier.Operation.ADD_VALUE))
        );

        register(registerable, CURSE_IRON_MAIDEN, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                itemsLookup.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                EnchantmentEffectTarget.VICTIM,EnchantmentEffectTarget.VICTIM,
                new CursedDamageBacklash(0.75f)
        ).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                EnchantmentEffectTarget.VICTIM,EnchantmentEffectTarget.VICTIM,
                new CursedDamageBacklash(0.000001f))
        );

        register(registerable, CURSE_LOSS, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.BLOCK_EXPERIENCE,
                new SetEnchantmentEffect(EnchantmentLevelBasedValue.constant(0.0f)))
        );

        register(registerable,CURSE_UNENDING,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addNonListEffect(EnchantmentEffectComponentTypes.CROSSBOW_CHARGE_TIME,
             new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.325f,0.625f)))
        );

        register(registerable,CURSE_IMPALED,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,EnchantmentEffectTarget.VICTIM,EnchantmentEffectTarget.VICTIM,
                new CursedDamageBacklash(0.85f),
                DamageSourcePropertiesLootCondition.builder(
                DamageSourcePredicate.Builder.create()
                .tag(TagPredicate.expected(DamageTypeTags.IS_PROJECTILE))
                .tag(TagPredicate.unexpected(DamageTypeTags.BYPASSES_INVULNERABILITY))))
        );

        register(registerable, CURSE_THALASSOPHOBIA, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2, AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(DEE_Common.id("curse_thalassophobia"),
                EntityAttributes.SUBMERGED_MINING_SPEED,
                EnchantmentLevelBasedValue.linear(-1.35f,-2.1f),
                EntityAttributeModifier.Operation.ADD_VALUE))
        );

        register(registerable,ENCHANTMENT_MOON, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.FEET
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(DEE_Common.id("enchantment_moon"),
                EntityAttributes.GRAVITY,EnchantmentLevelBasedValue.linear(-0.015f,-0.005f),
                EntityAttributeModifier.Operation.ADD_VALUE))
        );

        register(registerable,CURSE_JUPITER, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.FEET
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(DEE_Common.id("curse_jupiter"),
                EntityAttributes.GRAVITY,EnchantmentLevelBasedValue.linear(0.0125f,0.0075f),
                EntityAttributeModifier.Operation.ADD_VALUE))
        );

        register(registerable, ENCHANTMENT_RETURN,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.VANISHING_ENCHANTABLE), 10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ANY
        )).addEffect(EnchantmentEffectComponentTypes.TICK,
                new Enchantment_Return())
                .addEffect(EnchantmentEffectComponentTypes.PREVENT_EQUIPMENT_DROP)
        );

        register(registerable, CURSE_SOULLESS, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.FEET
        )).addEffect(EnchantmentEffectComponentTypes.TICK,new ApplyMobEffectEnchantmentEffect(
                RegistryEntryList.of(StatusEffects.SLOWNESS),
                EnchantmentLevelBasedValue.linear(0.75f,0.5f),
                EnchantmentLevelBasedValue.linear(1,0.5f),
                EnchantmentLevelBasedValue.constant(1),
                EnchantmentLevelBasedValue.constant(2)),
                RandomChanceLootCondition.builder(EnchantmentLevelLootNumberProvider.create(EnchantmentLevelBasedValue.linear(0.0375f,0.0625f))))
        );

        register(registerable, CURSE_STONES, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.FEET
        )).addEffect(EnchantmentEffectComponentTypes.TICK,new Curse_Stones()));

        register(registerable, CURSE_DROWNING, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.HEAD_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.HEAD
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES, new AttributeEnchantmentEffect(
                DEE_Common.id("curse_drowning"),
                EntityAttributes.OXYGEN_BONUS,
                EnchantmentLevelBasedValue.linear(-0.75f,-0.25f),
                EntityAttributeModifier.Operation.ADD_VALUE))
        );

        register(registerable, CURSE_ASHES, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.ATTACKER,
                new ApplyMobEffectEnchantmentEffect(
                RegistryEntryList.of(StatusEffects.BLINDNESS),
                EnchantmentLevelBasedValue.linear(1,0.5f),
                EnchantmentLevelBasedValue.linear(2,0.75f),
                EnchantmentLevelBasedValue.constant(0),
                EnchantmentLevelBasedValue.constant(0)))
        );

        register(registerable, CURSE_NECROPHOBIA, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE,
                        new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-1.75f,-1.25f)),
                        EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS,EntityPredicate.Builder.create().type(EntityTypePredicate.create(entryLookup, EntityTypeTags.UNDEAD)))
        ).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.VICTIM,
                        new ApplyMobEffectEnchantmentEffect(
                        RegistryEntryList.of(StatusEffects.SPEED),
                        EnchantmentLevelBasedValue.linear(0.5f, 0.75f),
                        EnchantmentLevelBasedValue.linear(0.5f, 1.25f),
                        EnchantmentLevelBasedValue.constant(0.0F),
                        EnchantmentLevelBasedValue.constant(1.0F)),
                        EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS,EntityPredicate.Builder.create().type(EntityTypePredicate.create(entryLookup, EntityTypeTags.UNDEAD))))
        );

        register(registerable,NM_GROWTH,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),1,10,
                Enchantment.leveledCost(5,2), Enchantment.leveledCost(10,2),2,
                AttributeModifierSlot.ARMOR)
        ).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(
                DEE_Common.id("neutral_magic_growth"),EntityAttributes.SCALE,
                EnchantmentLevelBasedValue.linear(0.175f), EntityAttributeModifier.Operation.ADD_VALUE)
        ));

        register(registerable,NM_SHRUNKEN,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),10,10,
                Enchantment.leveledCost(5,2), Enchantment.leveledCost(10,2),2,
                AttributeModifierSlot.ARMOR)
        ).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(
                DEE_Common.id("neutral_magic_shrunken"),EntityAttributes.SCALE,
                EnchantmentLevelBasedValue.linear(-0.19f), EntityAttributeModifier.Operation.ADD_VALUE)
        ));

        register(registerable,CURSE_SMOKE, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,CURSE_FLAMMABLE,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(
                DEE_Common.id("curse_flammable"),
                EntityAttributes.BURNING_TIME,EnchantmentLevelBasedValue.linear(1f,0.5f),
                EntityAttributeModifier.Operation.ADD_VALUE
        )));

        register(registerable,CURSE_TIDES,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.TICK,new Curse_Tides()));

        register(registerable,CURSE_HOOK,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE),
                itemsLookup.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.KNOCKBACK,
                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.05f,-0.125f)))
        );

        register(registerable,CURSE_VACUUM,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SHARP_WEAPON_ENCHANTABLE),
                itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.KNOCKBACK,
                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.05f,-0.125f)))
        );

        register(registerable,CURSE_VULNERABILITY,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,EnchantmentEffectTarget.VICTIM,EnchantmentEffectTarget.VICTIM,
                new CursedDamageBacklash(0.85f)
        ));

        register(registerable,CURSE_SOFTNESS,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2, AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE,
                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.05f,-0.55f)),
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.DIRECT_ATTACKER,
                        EntityPredicate.Builder.create().type(entityTypeLookup, EntityTypeTags.ARROWS).build()
                ))
        );

        register(registerable,CURSE_STUNNED,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MACE_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,CURSE_UNFIT,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.FEET
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES, new AttributeEnchantmentEffect(
                DEE_Common.id("curse_unfit"),EntityAttributes.WATER_MOVEMENT_EFFICIENCY,
                EnchantmentLevelBasedValue.linear(-0.085f,-0.09625f), EntityAttributeModifier.Operation.ADD_VALUE
        )));

        register(registerable,CURSE_JAMMING, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,CURSE_WORSEN, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ANY
        )).addEffect(EnchantmentEffectComponentTypes.TICK,new Curse_Worsen()));

        register(registerable,CURSE_CONSUMPTION,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE),
                itemsLookup.getOrThrow(ItemTags.BOW_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,ENCHANTMENT_ACCURATE,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.SHOOT_ENCHANTABLE),
                10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.PROJECTILE_SPREAD,new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(-3.75f,-4.25f))));

        register(registerable,CURSE_SPREAD,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.SHOOT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.PROJECTILE_SPREAD,new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(2.5f,1.5f))));


        register(registerable,ENCHANTMENT_AERODYNAMIC,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.SHOOT_ENCHANTABLE),
                10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,CURSE_DRAG,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(DEE_Tags.Items.SHOOT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,CURSE_WEIGHTLESSNESS,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MACE_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.SMASH_DAMAGE_PER_FALLEN_BLOCK,new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(-0.2f,-0.67f))));

        register(registerable,CURSE_REDIRECT,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.PROJECTILE_PIERCING,new AddEnchantmentEffect(
                EnchantmentLevelBasedValue.linear(1f))));

        register(registerable,CURSE_IMPLOSION,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,EnchantmentEffectTarget.VICTIM,EnchantmentEffectTarget.VICTIM,
                new CursedDamageBacklash(1.0f),DamageSourcePropertiesLootCondition.builder(
                        DamageSourcePredicate.Builder.create().tag(TagPredicate.expected(DamageTypeTags.IS_EXPLOSION))
                                .tag(TagPredicate.unexpected(DamageTypeTags.BYPASSES_INVULNERABILITY))
                )
        ).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,
                new AttributeEnchantmentEffect(
                        DEE_Common.id("curse_implosion"),
                        EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE,
                        EnchantmentLevelBasedValue.linear(-0.0025F,-0.09975f),
                        EntityAttributeModifier.Operation.ADD_VALUE
                ))
        );

        register(registerable,CURSE_MISFORTUNE,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MINING_LOOT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,CURSE_DEEP,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.VICTIM,
                new SummonEntityEnchantmentEffect(entityTypeLookup.getOrThrow(EntityTypeTags.AQUATIC),false),
                new AnyOfLootCondition.Builder(
                        LocationCheckLootCondition.builder(LocationPredicate.Builder.create().fluid(FluidPredicate.Builder.create().fluid(Fluids.WATER))),
                        new AllOfLootCondition.Builder(
                                LocationCheckLootCondition.builder(LocationPredicate.Builder.create().canSeeSky(true)),
                                new AnyOfLootCondition.Builder(
                                        new WeatherCheckLootCondition.Builder().raining(true),
                                        new WeatherCheckLootCondition.Builder().thundering(true)
                                        )
                        )
                )
        ));

        register(registerable,CURSE_EVAPORATION,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(5,2),2,AttributeModifierSlot.FEET
        )).addEffect(EnchantmentEffectComponentTypes.TICK,new Curse_Evaporation()));

        register(registerable,CURSE_CONDUCTIVENESS,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.HIT_BLOCK,
                                new Curse_Conductiveness(),
                        new AllOfLootCondition.Builder(
                                LocationCheckLootCondition.builder(LocationPredicate.Builder.create().canSeeSky(true)),
                                new AnyOfLootCondition.Builder(
                                        new WeatherCheckLootCondition.Builder().raining(true),
                                        new WeatherCheckLootCondition.Builder().thundering(true))
                )).addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,
                        EnchantmentEffectTarget.VICTIM,
                        new Curse_Conductiveness(),
                        new AllOfLootCondition.Builder(
                                LocationCheckLootCondition.builder(LocationPredicate.Builder.create().canSeeSky(true)),
                                new AnyOfLootCondition.Builder(
                                        new WeatherCheckLootCondition.Builder().raining(true),
                                        new WeatherCheckLootCondition.Builder().thundering(true)
                                )
                        )
                )
        );

        register(registerable,ENCHANTMENT_ABSORBING,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.EQUIPPABLE_ENCHANTABLE),
                10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.ARMOR
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES, new AttributeEnchantmentEffect(
                DEE_Common.id("enchantment_absorbing"),
                EntityAttributes.MAX_HEALTH,
                EnchantmentLevelBasedValue.linear(3f,1.5f),
                EntityAttributeModifier.Operation.ADD_VALUE
        )));

        register(registerable, CURSE_THIEVING,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(
                EnchantmentEffectComponentTypes.EQUIPMENT_DROPS,
                EnchantmentEffectTarget.ATTACKER,
                EnchantmentEffectTarget.VICTIM,
                new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.05f,-0.15f)),
                EntityPropertiesLootCondition.builder(
                        LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.create().type(EntityTypePredicate.create(entityTypeLookup, EntityType.PLAYER))
                ))
        );

        register(registerable, CURSE_DECEITFULNESS, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable, CURSE_POLLUTED_OCEANS, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.FISHING_LUCK_BONUS,new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-0.5f,-1.25f))));

        register(registerable, CURSE_GUARDIAN_LURE, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.FISHING_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable, ENCHANTMENT_CLEAVING, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.AXES),
                10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(2,1))));

        register(registerable, CURSE_BLUNT, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.AXES),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )).addEffect(EnchantmentEffectComponentTypes.DAMAGE, new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-1,-1.25f))));

        register(registerable,ENCHANTMENT_RANGE,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable,CURSE_SHORTNESS,Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.MAINHAND
        )));

        register(registerable, ENCHANTMENT_SPEED, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.LEGS
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(
                DEE_Common.id("enchantment_speed"),
                EntityAttributes.MOVEMENT_SPEED,
                EnchantmentLevelBasedValue.linear(0.15f,0.015f), EntityAttributeModifier.Operation.ADD_VALUE
        )));

        register(registerable, CURSE_FATIGUE, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.LEG_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.LEGS
        )).addEffect(EnchantmentEffectComponentTypes.ATTRIBUTES,new AttributeEnchantmentEffect(
                DEE_Common.id("enchantment_speed"),
                EntityAttributes.MOVEMENT_SPEED,
                EnchantmentLevelBasedValue.linear(-0.05f,-0.025f), EntityAttributeModifier.Operation.ADD_VALUE
        )));

        register(registerable, ENCHANTMENT_HOPE, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                10,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.CHEST
        )).addEffect(EnchantmentEffectComponentTypes.TICK, new StatusEffectAdder(true)));

        register(registerable, CURSE_DESPAIR, Enchantment.builder(Enchantment.definition(
                itemsLookup.getOrThrow(ItemTags.CHEST_ARMOR_ENCHANTABLE),
                1,10,
                Enchantment.leveledCost(5,2),
                Enchantment.leveledCost(10,2),2,AttributeModifierSlot.CHEST
        )).addEffect(EnchantmentEffectComponentTypes.TICK, new StatusEffectAdder(false)));
    }

    private static void register(@NotNull Registerable<Enchantment> registerable, RegistryKey<Enchantment> key, Enchantment.@NotNull Builder builder){
        registerable.register(key,builder.build(key.getValue()));
    }
}
