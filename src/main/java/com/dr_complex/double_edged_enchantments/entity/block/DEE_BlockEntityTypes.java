package com.dr_complex.double_edged_enchantments.entity.block;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class DEE_BlockEntityTypes {

    public static final BlockEntityType<HexingTableBlockEntity> HexingTableBlockEntityType = register(
            "hexing_table_entity_type", FabricBlockEntityTypeBuilder.create(HexingTableBlockEntity::new,DEE_Blocks.HEXING_TABLE).build());


    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, DEE_Common.id(path),blockEntityType);
    }

    public static void LoadBlockEntityTypes(){
        DEE_Common.LOGGER.info("Loaded blockEntity types");
    }
}
