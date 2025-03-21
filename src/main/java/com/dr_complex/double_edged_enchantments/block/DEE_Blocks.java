package com.dr_complex.double_edged_enchantments.block;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.block.custom.HexingTableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class DEE_Blocks {

    public static final Block HEXING_TABLE = register("hexing_table", HexingTableBlock::new,AbstractBlock.Settings.copy(Blocks.STONE).nonOpaque());

    public static Block register(RegistryKey<Block> key, @NotNull Function<AbstractBlock.Settings, Block> factory, AbstractBlock.@NotNull Settings settings) {
        Block block = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.BLOCK, key, block);
    }

    public static Block register(RegistryKey<Block> key, AbstractBlock.Settings settings) {
        return register(key, Block::new, settings);
    }

    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, DEE_Common.id(id));
    }

    private static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return register(keyOf(id), factory, settings);
    }

    private static Block register(String id, AbstractBlock.Settings settings) {
        return register(id, Block::new, settings);
    }

    public static void LoadBlocks(){
        DEE_Common.LOGGER.info("Blocks are Loaded");
    }
}
