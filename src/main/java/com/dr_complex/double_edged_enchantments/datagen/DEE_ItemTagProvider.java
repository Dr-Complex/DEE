package com.dr_complex.double_edged_enchantments.datagen;

import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import com.dr_complex.double_edged_enchantments.utils.DEE_Tags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class DEE_ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public DEE_ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE)
                .addOptionalTag(ItemTags.CROSSBOW_ENCHANTABLE)
                .addOptionalTag(ItemTags.BOW_ENCHANTABLE);

        valueLookupBuilder(DEE_Tags.Items.SPEAR_WEAPONS)
                .addOptionalTag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(DEE_Items.JETTATIUM_SPEAR)
                .add(DEE_Items.WOODEN_SPEAR)
                .add(DEE_Items.STONE_SPEAR)
                .add(DEE_Items.GOLDEN_SPEAR)
                .add(DEE_Items.COPPER_SPEAR)
                .add(DEE_Items.IRON_SPEAR)
                .add(DEE_Items.DIAMOND_SPEAR)
                .add(DEE_Items.NETHERITE_SPEAR);

        valueLookupBuilder(DEE_Tags.Items.SHOOT_ENCHANTABLE)
                .addTag(DEE_Tags.Items.ARROW_SHOOT_ENCHANTABLE)
                .addTag(DEE_Tags.Items.SPEAR_WEAPONS);

        valueLookupBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(DEE_Tags.Items.SPEAR_WEAPONS);

        valueLookupBuilder(DEE_Tags.Items.REPAIR_JATTATIUM)
                .add(Items.OMINOUS_BOTTLE)
                .add(Items.EXPERIENCE_BOTTLE);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(DEE_Items.JETTATIUM_SWORD);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(DEE_Items.JETTATIUM_PICKAXE);

        valueLookupBuilder(ItemTags.AXES)
                .add(DEE_Items.JETTATIUM_AXE);

        valueLookupBuilder(ItemTags.SHOVELS)
                .add(DEE_Items.JETTATIUM_SHOVEL);

        valueLookupBuilder(ItemTags.HOES)
                .add(DEE_Items.JETTATIUM_HOE);
    }
}
