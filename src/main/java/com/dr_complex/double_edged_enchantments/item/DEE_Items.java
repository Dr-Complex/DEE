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
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class DEE_Items {

    public static final ToolMaterial JattatiumToolMaterial = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL,4013,0.5f,7,2, DEE_Tags.Items.REPAIR_JATTATIUM);
    public static final ToolMaterial CopperToolMaterial = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL,193,5,2,18, DEE_Tags.Items.REPAIR_COPPER);

    public static final Item REVERED_ENDER_PEARL = register("reverted_ender_pearl", Reverted_Ender_Pearl::new,new Item.Settings().maxCount(16).useCooldown(1.25f));
    public static final Item EXP_NEEDLE = register("exp_needle", EXP_Needle::new,new Item.Settings().maxDamage(256));
    public static final Item COPPER_NUGGET = register("copper_nugget");
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

    public static final Item JETTATIUM_SWORD = register("jettatium_sword", new Item.Settings().sword(JattatiumToolMaterial,12, -3.2f));
    public static final Item JETTATIUM_AXE = register("jettatium_axe", new Item.Settings().axe(JattatiumToolMaterial,32, -3.5f));
    public static final Item JETTATIUM_PICKAXE = register("jettatium_pickaxe", new Item.Settings().pickaxe(JattatiumToolMaterial,2f, -3.2f));
    public static final Item JETTATIUM_HOE = register("jettatium_hoe", new Item.Settings().hoe(JattatiumToolMaterial,0, -3.8f));
    public static final Item JETTATIUM_SHOVEL = register("jettatium_shovel", new Item.Settings().shovel(JattatiumToolMaterial,3f, -3.5f));

    public static final Item COPPER_SWORD = register("copper_sword", new Item.Settings().sword(CopperToolMaterial,2, -2.4f)); // [base atk:3] {base spd:4}
    public static final Item COPPER_AXE = register("copper_axe", new Item.Settings().axe(CopperToolMaterial,5, -3.2f));
    public static final Item COPPER_PICKAXE = register("copper_pickaxe", new Item.Settings().pickaxe(CopperToolMaterial,-1f, -2.8f));
    public static final Item COPPER_HOE = register("copper_hoe", new Item.Settings().hoe(CopperToolMaterial,-2f, -1));
    public static final Item COPPER_SHOVEL = register("copper_shovel", new Item.Settings().shovel(CopperToolMaterial,-0.5f, -3));

    public static final Item HEXING_TABLE = register(DEE_Blocks.HEXING_TABLE);

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
