package com.dr_complex.double_edged_enchantments.utils;

import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum SpearType{
    Wooden(0),
    Stone(1),
    Golden(2),
    Iron(3),
    Diamond(4),
    Netherite(5),
    Copper(6),
    Jettatium(7);

    private static final IntFunction<SpearType> BY_ID = ValueLists.createIndexToValueFunction(SpearType::getId, values(), ValueLists.OutOfBoundsHandling.WRAP);
    private final int id;


    SpearType(final int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static SpearType byId(int id) {
        return BY_ID.apply(id);
    }
}