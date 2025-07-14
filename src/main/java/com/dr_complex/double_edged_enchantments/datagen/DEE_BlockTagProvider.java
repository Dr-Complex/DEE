package com.dr_complex.double_edged_enchantments.datagen;

import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class DEE_BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public DEE_BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE).add(DEE_Blocks.HEXING_TABLE,DEE_Blocks.JETTATUIM,DEE_Blocks.JETTATUIM_ORE);
        valueLookupBuilder(BlockTags.VALID_SPAWN).add(DEE_Blocks.JETTATUIM,DEE_Blocks.JETTATUIM_ORE);
        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(DEE_Blocks.JETTATUIM,DEE_Blocks.JETTATUIM_ORE,DEE_Blocks.HEXING_TABLE);
    }
}
