package com.dr_complex.double_edged_enchantments.screen;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;

public class DEE_ScreenHandlers{

    public static final ScreenHandlerType<HexingTableScreenHandler> HEXING_TABLE_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER,
            DEE_Common.id("hexing_table"),
            new ExtendedScreenHandlerType<>(HexingTableScreenHandler::new, BlockPos.PACKET_CODEC)
    );


    public static void LoadScreens(){
        DEE_Common.LOGGER.info("Screens are now smashed");
    }
}
