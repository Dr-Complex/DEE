package com.dr_complex.double_edged_enchantments;

import com.dr_complex.double_edged_enchantments.datagen.*;
import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class DEE_DataGen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(@NotNull FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(DEE_BlockTagProvider::new);
		pack.addProvider(DEE_ItemTagProvider::new);
		pack.addProvider(DEE_ModelProvider::new);
		pack.addProvider(DEE_LootProvider::new);
		pack.addProvider(DEE_RegistryDataGen::new);
		pack.addProvider(DEE_EnchantmentTagProvider::new);
	}

	@Override
	public void buildRegistry(@NotNull RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, DEE_Enchantments::bootstrap);
	}
}
