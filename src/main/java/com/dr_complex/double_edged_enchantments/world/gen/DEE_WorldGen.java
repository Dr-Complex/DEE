package com.dr_complex.double_edged_enchantments.world.gen;

import com.dr_complex.double_edged_enchantments.world.DEE_PlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class DEE_WorldGen {
    public static void generateAll(){
        generateOres();
    }

    private static void generateOres(){
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.TOP_LAYER_MODIFICATION, DEE_PlacedFeatures.JETTATUIM_ORE_KEY);
    }
}
