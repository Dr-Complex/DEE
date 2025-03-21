package com.dr_complex.double_edged_enchantments.utils;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.Vec3d;

import java.util.function.UnaryOperator;

public class DEE_DataComponentTypes {

    public static final ComponentType<Vec3d> POS_CONTAINER = register("pos_container", blockPosBuilder -> blockPosBuilder.codec(Vec3d.CODEC));
    public static final ComponentType<Integer> XP_CONTAINER = register("xp_container", integerBuilder -> integerBuilder.codec(Codec.INT));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderUnaryOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE,DEE_Common.id(name), builderUnaryOperator.apply(ComponentType.builder()).build());
    }

    public static void LoadDataComponents(){
        DEE_Common.LOGGER.info("Damaging the data (not really)");
    }
}
