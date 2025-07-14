package com.dr_complex.double_edged_enchantments.datagen;

import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.property.bool.DamagedProperty;
import org.jetbrains.annotations.NotNull;


public class DEE_ModelProvider extends FabricModelProvider {

    public DEE_ModelProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generateBlockStateModels(@NotNull BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(DEE_Blocks.HEXING_TABLE);
        blockStateModelGenerator.registerSimpleCubeAll(DEE_Blocks.JETTATUIM);
        blockStateModelGenerator.registerSimpleCubeAll(DEE_Blocks.JETTATUIM_ORE);
    }

    @Override
    public void generateItemModels(@NotNull ItemModelGenerator itemModelGenerator) {

        ItemModel.Unbaked unbakedFalse = ItemModels.basic(ModelIds.getItemModelId(DEE_Items.EXP_NEEDLE));
        ItemModel.Unbaked unbakedTrue = ItemModels.basic(ModelIds.getItemSubModelId(DEE_Items.EXP_NEEDLE,"_empty"));

        itemModelGenerator.registerCondition(DEE_Items.EXP_NEEDLE, new DamagedProperty(), unbakedTrue, unbakedFalse);

        itemModelGenerator.register(DEE_Items.REVERED_ENDER_PEARL, Models.GENERATED);

        itemModelGenerator.register(DEE_Items.RAW_JATTATIUM,Models.GENERATED);
        itemModelGenerator.register(DEE_Items.CUT_JATTATIUM,Models.GENERATED);

        itemModelGenerator.register(DEE_Items.JETTATIUM_SHOVEL,Models.HANDHELD);
        itemModelGenerator.register(DEE_Items.JETTATIUM_HOE,Models.HANDHELD);
        itemModelGenerator.register(DEE_Items.JETTATIUM_SWORD,Models.HANDHELD);
        itemModelGenerator.register(DEE_Items.JETTATIUM_AXE,Models.HANDHELD);
        itemModelGenerator.register(DEE_Items.JETTATIUM_PICKAXE,Models.HANDHELD);

        itemModelGenerator.registerArmor(DEE_Items.JETTATUIM_HELMET, DEE_Items.JettatuimEquipmentAssetKey, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(DEE_Items.JETTATUIM_CHEST_PLATE, DEE_Items.JettatuimEquipmentAssetKey, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(DEE_Items.JETTATUIM_LEGGINGS, DEE_Items.JettatuimEquipmentAssetKey, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(DEE_Items.JETTATUIM_BOOTS, DEE_Items.JettatuimEquipmentAssetKey, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);

    }
}
