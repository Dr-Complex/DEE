package com.dr_complex.double_edged_enchantments.item;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import com.dr_complex.double_edged_enchantments.item.custom.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class DEE_Items {


    public static final Item REVERED_ENDER_PEARL = register("reverted_ender_pearl", Reverted_Ender_Pearl::new,new Item.Settings().maxCount(16).useCooldown(1.5f));
    public static final Item EXP_NEEDLE = register("exp_needle", EXP_Needle::new,new Item.Settings().maxDamage(256));
    public static final Item COPPER_NUGGET = register("copper_nugget");
    public static final Item WOODEN_SPEAR = register("wooden_spear", WoodenSpearItem::new,new Item.Settings().maxDamage(16).attributeModifiers(WoodenSpearItem.createAttributeModifiers()).enchantable(1));
    public static final Item STONE_SPEAR = register("stone_spear", StoneSpearItem::new,new Item.Settings().maxDamage(32).attributeModifiers(StoneSpearItem.createAttributeModifiers()).enchantable(1));
    public static final Item GOLDEN_SPEAR = register("golden_spear", GoldenSpearItem::new,new Item.Settings().maxDamage(16).attributeModifiers(GoldenSpearItem.createAttributeModifiers()).enchantable(1));
    public static final Item IRON_SPEAR = register("iron_spear", IronSpearItem::new,new Item.Settings().maxDamage(128).attributeModifiers(IronSpearItem.createAttributeModifiers()).enchantable(1));
    public static final Item DIAMOND_SPEAR = register("diamond_spear", DiamondSpearItem::new,new Item.Settings().maxDamage(512).attributeModifiers(DiamondSpearItem.createAttributeModifiers()).enchantable(1));
    public static final Item NETHERITE_SPEAR = register("netherite_spear", NetheriteSpearItem::new,new Item.Settings().maxDamage(2048).attributeModifiers(NetheriteSpearItem.createAttributeModifiers()).enchantable(1));
    public static final Item COPPER_SPEAR = register("copper_spear", CopperSpearItem::new,new Item.Settings().maxDamage(80).attributeModifiers(CopperSpearItem.createAttributeModifiers()).enchantable(1));
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
        return register(block,((blockx, settings) -> new BlockItem(blockx, settingsOperator.apply(settings))));
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

    public static Item register(@NotNull Block block, BiFunction<Block, Item.Settings, Item> factory, Item.@NotNull Settings settings) {
        return register(
                keyOf(block.getRegistryEntry().registryKey()), itemSettings -> factory.apply(block, itemSettings), settings.useBlockPrefixedTranslationKey()
        );
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
