package com.dr_complex.double_edged_enchantments.datagen;

import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import com.dr_complex.double_edged_enchantments.utils.DEE_Tags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class DEE_ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public DEE_ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE)
                .addOptionalTag(ItemTags.CROSSBOW_ENCHANTABLE)
                .addOptionalTag(ItemTags.BOW_ENCHANTABLE);

        getOrCreateTagBuilder(DEE_Tags.Items.SPEAR_WEAPONS)
                .addOptionalTag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(DEE_Items.WOODEN_SPEAR)
                .add(DEE_Items.STONE_SPEAR)
                .add(DEE_Items.GOLDEN_SPEAR)
                .add(DEE_Items.COPPER_SPEAR)
                .add(DEE_Items.IRON_SPEAR)
                .add(DEE_Items.DIAMOND_SPEAR)
                .add(DEE_Items.NETHERITE_SPEAR);

        getOrCreateTagBuilder(DEE_Tags.Items.SHOOT_ENCHANTABLE)
                .addTag(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE)
                .addTag(DEE_Tags.Items.SPEAR_WEAPONS);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(DEE_Tags.Items.SPEAR_WEAPONS);
    }
}
