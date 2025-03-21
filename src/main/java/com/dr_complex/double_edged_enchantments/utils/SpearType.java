package com.dr_complex.double_edged_enchantments.utils;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum SpearType implements StringIdentifiable {
    Wooden(0,"wooden"),
    Stone(1,"stone"),
    Golden(2,"golden"),
    Iron(3,"iron"),
    Diamond(4,"diamond"),
    Netherite(5,"netherite"),
    Copper(6,"copper");

    public static final Codec<SpearType> CODEC = StringIdentifiable.createCodec(SpearType::values);
    private static final IntFunction<SpearType> BY_ID = ValueLists.createIdToValueFunction(SpearType::getId, values(), ValueLists.OutOfBoundsHandling.WRAP);
    private final int id;
    private final String name;


    SpearType(final int id, final String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public static SpearType byId(int id) {
        return BY_ID.apply(id);
    }

    @Override
    public String asString() {
        return this.name;
    }
}