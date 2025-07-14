package com.dr_complex.double_edged_enchantments.datagen;

import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class DEE_LootProvider extends FabricBlockLootTableProvider {
    public DEE_LootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(DEE_Blocks.HEXING_TABLE);
        addDrop(DEE_Blocks.JETTATUIM);
        addDrop(DEE_Blocks.JETTATUIM_ORE, oreDrops(DEE_Blocks.JETTATUIM_ORE, DEE_Items.RAW_JATTATIUM));
    }
}
