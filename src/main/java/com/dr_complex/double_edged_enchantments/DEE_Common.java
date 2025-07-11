package com.dr_complex.double_edged_enchantments;

import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantment_Effects;
import com.dr_complex.double_edged_enchantments.entity.DEE_Entities;
import com.dr_complex.double_edged_enchantments.block.entity.DEE_BlockEntityTypes;
import com.dr_complex.double_edged_enchantments.item.DEE_ItemGroup;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import com.dr_complex.double_edged_enchantments.screen.DEE_ScreenHandlers;
import com.dr_complex.double_edged_enchantments.utils.DEE_DataComponentTypes;
import com.dr_complex.double_edged_enchantments.utils.DEE_ItemToolTips;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DEE_Common implements ModInitializer {
    public static final String MOD_ID = "double_edged_enchantments";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Contract("_ -> new")
    public static @NotNull Identifier id(String path){
        return Identifier.of(MOD_ID,path);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Started with mod : {}", MOD_ID);
        DEE_Enchantment_Effects.LoadEnchantmentEffects();
        DEE_DataComponentTypes.LoadDataComponents();
        DEE_Blocks.LoadBlocks();
        DEE_Entities.LoadEntities();
        DEE_Items.LoadItems();
        DEE_ScreenHandlers.LoadScreens();
        DEE_ItemGroup.LoadItemGroups();
        DEE_BlockEntityTypes.LoadBlockEntityTypes();
        DEE_ItemToolTips.LoadItemToolTips();
        LOGGER.info("Ended with mod : {}", MOD_ID);
    }
}
