package com.dr_complex.double_edged_enchantments.item;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import com.dr_complex.double_edged_enchantments.item.custom.*;
import com.dr_complex.double_edged_enchantments.utils.DEE_Tags;
import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class DEE_Items {

    public static final RegistryKey<EquipmentAsset> JettatuimEquipmentAssetKey = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, DEE_Common.id("jettatuim_asset_key"));

    public static final ArmorMaterial JettatuimArmorMaterial = new ArmorMaterial(44, Map.of(
            EquipmentType.HELMET, 2,
            EquipmentType.CHESTPLATE, 6,
            EquipmentType.LEGGINGS, 4,
            EquipmentType.BOOTS, 5),75,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.25f, 0.01f, DEE_Tags.Items.REPAIR_JATTATIUM, JettatuimEquipmentAssetKey);

    public static final ToolMaterial JattatiumToolMaterial = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,4013,1.5f,7,2, DEE_Tags.Items.REPAIR_JATTATIUM);

    public static final Item REVERED_ENDER_PEARL = register("reverted_ender_pearl", Reverted_Ender_Pearl::new,new Item.Settings().maxCount(16).useCooldown(1.25f));
    public static final Item EXP_NEEDLE = register("exp_needle", EXP_Needle::new,new Item.Settings().maxDamage(256));

    public static final Item RAW_JATTATIUM = register("raw_jattatium");
    public static final Item CUT_JATTATIUM = register("cut_jattatium");

    public static final Item WOODEN_SPEAR = register("wooden_spear", WoodenSpearItem::new,new Item.Settings().maxDamage(59).attributeModifiers(WoodenSpearItem.createAttributeModifiers()).enchantable(15).component(DataComponentTypes.WEAPON,new WeaponComponent(1)));
    public static final Item STONE_SPEAR = register("stone_spear", StoneSpearItem::new,new Item.Settings().maxDamage(131).attributeModifiers(StoneSpearItem.createAttributeModifiers()).enchantable(5).component(DataComponentTypes.WEAPON,new WeaponComponent(1)));
    public static final Item GOLDEN_SPEAR = register("golden_spear", GoldenSpearItem::new,new Item.Settings().maxDamage(32).attributeModifiers(GoldenSpearItem.createAttributeModifiers()).enchantable(22).component(DataComponentTypes.WEAPON,new WeaponComponent(1)));
    public static final Item IRON_SPEAR = register("iron_spear", IronSpearItem::new,new Item.Settings().maxDamage(250).attributeModifiers(IronSpearItem.createAttributeModifiers()).enchantable(14).component(DataComponentTypes.WEAPON,new WeaponComponent(1)));
    public static final Item DIAMOND_SPEAR = register("diamond_spear", DiamondSpearItem::new,new Item.Settings().maxDamage(1561).attributeModifiers(DiamondSpearItem.createAttributeModifiers()).enchantable(10).component(DataComponentTypes.WEAPON,new WeaponComponent(1)));
    public static final Item NETHERITE_SPEAR = register("netherite_spear", NetheriteSpearItem::new,new Item.Settings().maxDamage(2031).attributeModifiers(NetheriteSpearItem.createAttributeModifiers()).enchantable(20).fireproof().component(DataComponentTypes.WEAPON,new WeaponComponent(1)));
    public static final Item COPPER_SPEAR = register("copper_spear", CopperSpearItem::new,new Item.Settings().maxDamage(193).attributeModifiers(CopperSpearItem.createAttributeModifiers()).enchantable(15).component(DataComponentTypes.WEAPON,new WeaponComponent(1)));
    public static final Item JETTATIUM_SPEAR = register("jettatium_spear", JettatiumSpearItem::new,new Item.Settings().maxDamage(4003).attributeModifiers(CopperSpearItem.createAttributeModifiers()).enchantable(2).component(DataComponentTypes.WEAPON,new WeaponComponent(1)));

    public static final Item JETTATIUM_SWORD = register("jettatium_sword", JettatuimToolItem::new, new Item.Settings().sword(JattatiumToolMaterial, 4, -3.2f));
    public static final Item JETTATIUM_AXE = register("jettatium_axe", JettatuimToolItem::new, new Item.Settings().axe(JattatiumToolMaterial, 8, -3.5f));
    public static final Item JETTATIUM_PICKAXE = register("jettatium_pickaxe", JettatuimToolItem::new, new Item.Settings().pickaxe(JattatiumToolMaterial,0, -3.2f));
    public static final Item JETTATIUM_HOE = register("jettatium_hoe", JettatuimToolItem::new, new Item.Settings().hoe(JattatiumToolMaterial, -5, -1f));
    public static final Item JETTATIUM_SHOVEL = register("jettatium_shovel", JettatuimToolItem::new, new Item.Settings().shovel(JattatiumToolMaterial, -0.25f, -3.5f));

    public static final Item JETTATUIM_HELMET = register("jettatuim_helmet",new Item.Settings().armor(JettatuimArmorMaterial, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(44)));
    public static final Item JETTATUIM_CHEST_PLATE = register("jettatuim_chestplate",new Item.Settings().armor(JettatuimArmorMaterial, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(44)));
    public static final Item JETTATUIM_LEGGINGS = register("jettatuim_leggings",new Item.Settings().armor(JettatuimArmorMaterial, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(44)));
    public static final Item JETTATUIM_BOOTS = register("jettatuim_boots",new Item.Settings().armor(JettatuimArmorMaterial, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(44)));

    public static final Item HEXING_TABLE = register(DEE_Blocks.HEXING_TABLE);
    public static final Item JETTATUIM_BLOCK = register(DEE_Blocks.JETTATUIM);
    public static final Item JETTATUIM_BLOCK_ORE = register(DEE_Blocks.JETTATUIM_ORE);

    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, DEE_Common.id(id));
    }

    private static RegistryKey<Item> keyOf(RegistryKey<Block> blockKey) {
        return RegistryKey.of(RegistryKeys.ITEM, blockKey.getValue());
    }

    public static Item register(Block block) {
        return register(block, BlockItem::new);
    }

    public static Item register(Block block, Item.Settings settings) {
        return register(block, BlockItem::new, settings);
    }

    public static Item register(Block block, UnaryOperator<Item.Settings> settingsOperator) {
        return register(block, (blockx, settings) -> new BlockItem(blockx, settingsOperator.apply(settings)));
    }

    public static Item register(Block block, Block... blocks) {
        Item item = register(block);

        for (Block block2 : blocks) {
            Item.BLOCK_ITEMS.put(block2, item);
        }

        return item;
    }

    public static Item register(Block block, BiFunction<Block, Item.Settings, Item> factory) {
        return register(block, factory, new Item.Settings());
    }

    public static Item register(Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        return register(keyOf(block.getRegistryEntry().registryKey()), (itemSettings) -> factory.apply(block, itemSettings), settings.useBlockPrefixedTranslationKey());
    }

    public static Item register(String id, Function<Item.Settings, Item> factory) {
        return register(keyOf(id), factory, new Item.Settings());
    }

    public static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return register(keyOf(id), factory, settings);
    }

    public static Item register(String id, Item.Settings settings) {
        return register(keyOf(id), Item::new, settings);
    }

    public static Item register(String id) {
        return register(keyOf(id), Item::new, new Item.Settings());
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> factory) {
        return register(key, factory, new Item.Settings());
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings.registryKey(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, key, item);
    }

    public static void LoadItems(){
        DEE_Common.LOGGER.info("Items are now cursed");
    }
}
