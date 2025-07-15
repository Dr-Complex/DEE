package com.dr_complex.double_edged_enchantments.datagen;

import com.dr_complex.double_edged_enchantments.block.DEE_Blocks;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.predicate.NumberRange;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class DEE_RecipeProvider extends FabricRecipeProvider {
    public DEE_RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {

                createShapeless(RecipeCategory.MISC, Items.ECHO_SHARD,2)
                        .input(Items.SCULK_VEIN,5)
                        .input(Items.AMETHYST_SHARD)
                        .criterion(hasItem(Items.AMETHYST_SHARD),conditionsFromItem(Items.AMETHYST_SHARD))
                        .criterion(hasItem(Items.SCULK_VEIN),conditionsFromItem(NumberRange.IntRange.atLeast(5),Items.SCULK_VEIN)).offerTo(exporter);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, DEE_Blocks.JETTATUIM,1)
                        .input(DEE_Items.CUT_JATTATIUM,9)
                        .criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM)).offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, DEE_Blocks.HEXING_TABLE,1)
                        .pattern("   ")
                        .pattern("OTO")
                        .pattern("OEO")
                        .input('O', Items.OBSIDIAN)
                        .input('T', Items.GHAST_TEAR)
                        .input('E', Items.ENCHANTING_TABLE)
                        .criterion(hasItem(Items.GHAST_TEAR),conditionsFromItem(Items.GHAST_TEAR))
                        .criterion(hasItem(Items.ENCHANTING_TABLE),conditionsFromItem(Items.ENCHANTING_TABLE))
                        .criterion(hasItem(Items.OBSIDIAN),conditionsFromItem(NumberRange.IntRange.atLeast(4),Items.OBSIDIAN))
                        .offerTo(exporter);

                offerStonecuttingRecipe(RecipeCategory.MISC, DEE_Items.CUT_JATTATIUM, DEE_Items.RAW_JATTATIUM);

                createShaped(RecipeCategory.COMBAT, DEE_Items.JETTATUIM_HELMET,1)
                        .pattern("###")
                        .pattern("# #")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .offerTo(exporter);

            createShaped(RecipeCategory.COMBAT, DEE_Items.JETTATUIM_BOOTS,1)
                        .pattern("# #")
                        .pattern("# #")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .offerTo(exporter);

            createShaped(RecipeCategory.COMBAT, DEE_Items.JETTATUIM_LEGGINGS,1)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("# #")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .offerTo(exporter);

            createShaped(RecipeCategory.COMBAT, DEE_Items.JETTATUIM_CHEST_PLATE,1)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("###")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .offerTo(exporter);

            createShaped(RecipeCategory.COMBAT, DEE_Items.JETTATIUM_AXE,1)
                        .pattern("## ")
                        .pattern("#s ")
                        .pattern(" s ")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .input('s', Items.STICK).criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

            createShaped(RecipeCategory.COMBAT, DEE_Items.JETTATIUM_SWORD,1)
                        .pattern("#")
                        .pattern("#")
                        .pattern("s")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .input('s', Items.STICK).criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

            createShaped(RecipeCategory.TOOLS, DEE_Items.JETTATIUM_HOE,1)
                        .pattern("## ")
                        .pattern(" s ")
                        .pattern(" s ")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .input('s', Items.STICK).criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

            createShaped(RecipeCategory.TOOLS, DEE_Items.JETTATIUM_PICKAXE,1)
                        .pattern("###")
                        .pattern(" s ")
                        .pattern(" s ")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .input('s', Items.STICK).criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

            createShaped(RecipeCategory.TOOLS, DEE_Items.JETTATIUM_SHOVEL,1)
                        .pattern("#")
                        .pattern("s")
                        .pattern("s")
                        .input('#', DEE_Items.CUT_JATTATIUM).criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM))
                        .input('s', Items.STICK).criterion(hasItem(Items.STICK),conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

            createShapeless(RecipeCategory.MISC, DEE_Items.CUT_JATTATIUM, 9)
                    .input(DEE_Items.JETTATUIM_BLOCK)
                    .criterion(hasItem(DEE_Items.JETTATUIM_BLOCK),conditionsFromItem(DEE_Items.JETTATUIM_BLOCK))
                    .offerTo(exporter);

            createShaped(RecipeCategory.COMBAT,DEE_Items.WOODEN_SPEAR,1)
                    .pattern("  @")
                    .pattern(" s ")
                    .pattern("s  ")
                    .input('@', ItemTags.PLANKS)
                    .input('s', Items.STICK)
                    .criterion(hasItem(Items.OAK_PLANKS),conditionsFromTag(ItemTags.PLANKS)).offerTo(exporter);

            createShaped(RecipeCategory.COMBAT,DEE_Items.STONE_SPEAR,1)
                    .pattern("  @")
                    .pattern(" s ")
                    .pattern("s  ")
                    .input('@', ItemTags.STONE_TOOL_MATERIALS)
                    .input('s', Items.STICK)
                    .criterion(hasItem(Items.COBBLESTONE),conditionsFromTag(ItemTags.STONE_TOOL_MATERIALS)).offerTo(exporter);

            createShaped(RecipeCategory.COMBAT,DEE_Items.IRON_SPEAR,1)
                    .pattern("  @")
                    .pattern(" s ")
                    .pattern("s  ")
                    .input('@', Items.IRON_INGOT)
                    .input('s', Items.STICK)
                    .criterion(hasItem(Items.IRON_INGOT),conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter);

            createShaped(RecipeCategory.COMBAT,DEE_Items.COPPER_SPEAR,1)
                    .pattern("  @")
                    .pattern(" s ")
                    .pattern("s  ")
                    .input('@', Items.COPPER_INGOT)
                    .input('s', Items.STICK)
                    .criterion(hasItem(Items.COPPER_INGOT),conditionsFromItem(Items.COPPER_INGOT)).offerTo(exporter);

            createShaped(RecipeCategory.COMBAT,DEE_Items.DIAMOND_SPEAR,1)
                    .pattern("  @")
                    .pattern(" s ")
                    .pattern("s  ")
                    .input('@', Items.DIAMOND)
                    .input('s', Items.STICK)
                    .criterion(hasItem(Items.DIAMOND),conditionsFromItem(Items.DIAMOND)).offerTo(exporter);

            createShaped(RecipeCategory.COMBAT,DEE_Items.JETTATIUM_SPEAR,1)
                    .pattern("  @")
                    .pattern(" s ")
                    .pattern("s  ")
                    .input('@', DEE_Items.CUT_JATTATIUM)
                    .input('s', Items.STICK)
                    .criterion(hasItem(DEE_Items.CUT_JATTATIUM),conditionsFromItem(DEE_Items.CUT_JATTATIUM)).offerTo(exporter);

            createShaped(RecipeCategory.COMBAT,DEE_Items.GOLDEN_SPEAR,1)
                    .pattern("  @")
                    .pattern(" s ")
                    .pattern("s  ")
                    .input('@', Items.GOLD_INGOT)
                    .input('s', Items.STICK)
                    .criterion(hasItem(Items.GOLD_INGOT),conditionsFromItem(Items.GOLD_INGOT)).offerTo(exporter);

            createShaped(RecipeCategory.COMBAT, DEE_Items.REVERED_ENDER_PEARL, 3)
                    .pattern(" # ")
                    .pattern("#@#")
                    .pattern(" # ")
                    .input('@', Items.ENDER_PEARL)
                    .input('#', Items.ECHO_SHARD)
                    .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(NumberRange.IntRange.atLeast(5),Items.ECHO_SHARD)).offerTo(exporter);

            createShaped(RecipeCategory.MISC, DEE_Items.EXP_NEEDLE,1)
                    .pattern(" b ")
                    .pattern("gEg")
                    .pattern(" i ")
                    .input('i',Items.IRON_NUGGET).criterion(hasItem(Items.IRON_NUGGET),conditionsFromItem(Items.IRON_NUGGET))
                    .input('g',Items.GLASS_PANE).criterion(hasItem(Items.GLASS_PANE),conditionsFromItem(Items.GLASS_PANE))
                    .input('E',Items.EXPERIENCE_BOTTLE).criterion(hasItem(Items.EXPERIENCE_BOTTLE),conditionsFromItem(Items.EXPERIENCE_BOTTLE))
                    .input('b',ItemTags.WOODEN_BUTTONS).criterion(hasItem(Items.OAK_BUTTON),conditionsFromTag(ItemTags.WOODEN_BUTTONS)).offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "DEE_RecipeProvider";
    }
}
