package com.dr_complex.double_edged_enchantments.mixin;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityAttributes.class)
public abstract class AttributeModifierMixin {

    @Shadow
    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.ofVanilla(id), attribute);
    }

    @Shadow
    public static final RegistryEntry<EntityAttribute> EXPLOSION_KNOCKBACK_RESISTANCE = register(
            "double_edged_enchantments.explosion_knockback_resistance",new ClampedEntityAttribute("attribute.name.explosion_knockback_resistance",0.0f,-1.0f,1.0f).setTracked(true)
    );

    @Shadow
    public static final RegistryEntry<EntityAttribute> OXYGEN_BONUS = register(
            "double_edged_enchantments.oxygen_bonus", new ClampedEntityAttribute("attribute.name.oxygen_bonus", 0.0, -256.0, 255.0).setTracked(true)
    );

    @Shadow
    public static final RegistryEntry<EntityAttribute> WATER_MOVEMENT_EFFICIENCY = register(
            "double_edged_enchantments.water_movement_efficiency", new ClampedEntityAttribute("attribute.name.water_movement_efficiency", 0.0, -1.0, 1.0).setTracked(true)
    );

}
