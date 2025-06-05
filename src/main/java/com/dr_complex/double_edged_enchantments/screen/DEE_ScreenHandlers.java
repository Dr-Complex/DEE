package com.dr_complex.double_edged_enchantments.screen;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class DEE_ScreenHandlers<T extends ScreenHandler>{
    private final ScreenHandlerType.Factory<T> factory;

    public static final ScreenHandlerType<HexingTableScreenHandler> HEXING_TABLE_SCREEN_HANDLER = register("hexing_table",HexingTableScreenHandler::new);

    public DEE_ScreenHandlers(ScreenHandlerType.Factory<T> factory) {
        this.factory = factory;
    }

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER,id,new ScreenHandlerType<>(factory, FeatureSet.empty()));
    }

    public static void LoadScreens(){
        DEE_Common.LOGGER.info("Screens are now smashed");
    }
}
