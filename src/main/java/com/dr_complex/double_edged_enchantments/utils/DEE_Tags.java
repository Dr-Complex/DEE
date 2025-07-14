package com.dr_complex.double_edged_enchantments.utils;


import com.dr_complex.double_edged_enchantments.DEE_Common;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class DEE_Tags {
    public static class Blocks{

        private static TagKey<Block> blockTagKey(String name){
            return TagKey.of(RegistryKeys.BLOCK, DEE_Common.id(name));
        }
    }

    public static class Items {

        public static final TagKey<Item> REPAIR_JATTATIUM = itemTagKey("repair_jattatium");
        public static final TagKey<Item> JATTATIUM_ARMOR = itemTagKey("jattatium_armor");
        public static final TagKey<Item> ARROW_SHOOT_ENCHANTABLE = itemTagKey("arrow_shoot_enchantable");
        public static final TagKey<Item> SHOOT_ENCHANTABLE = itemTagKey("shoot_enchantable");
        public static final TagKey<Item> SPEAR_WEAPONS = itemTagKey("spear_weapon");

        private static TagKey<Item> itemTagKey(String name){
            return TagKey.of(RegistryKeys.ITEM,DEE_Common.id(name));
        }
    }

    public static class Enchantments {

        public static final TagKey<Enchantment> ALL_GOOD = enchantmentTagKey("all_good");

        private static TagKey<Enchantment> enchantmentTagKey(String name){
            return TagKey.of(RegistryKeys.ENCHANTMENT,DEE_Common.id(name));
        }
    }
}
