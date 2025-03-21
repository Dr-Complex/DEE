package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

import static net.minecraft.block.Block.getDroppedStacks;

@Mixin(Block.class)
public abstract class LootTables {

    @Shadow
    public static void dropStack(World world, BlockPos pos, ItemStack stack) {
    }

    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",at = @At("HEAD"), cancellable = true)
    private static void ModifyBlockLootTable(BlockState state, @NotNull World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfo ci) {
        if (!world.isClient) {
            if (!tool.getEnchantments().isEmpty()) {
                List<String> enchantments = tool.getEnchantments().getEnchantments().stream().map(RegistryEntry::getIdAsString).toList();
                List<Integer> levels = tool.getEnchantments().getEnchantmentEntries().stream().map(Object2IntMap.Entry::getIntValue).toList();
                for (int k = 0; k < enchantments.size(); k++) {
                    int index = k;

                    if (Objects.equals(enchantments.get(index), DEE_Enchantments.CURSE_LOSS.getValue().toString()) && world.random.nextFloat() >= (float) 1 /levels.get(index)) {
                        getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, entity, tool).forEach(stack -> dropStack(world, pos, ItemStack.EMPTY));
                        state.onStacksDropped((ServerWorld) world, pos, tool, false);
                        ci.cancel();
                    }

                    if(Objects.equals(enchantments.get(index), DEE_Enchantments.CURSE_MISFORTUNE.getValue().toString())){
                        getDroppedStacks(state, (ServerWorld) world,pos,blockEntity,entity,tool).forEach(stack -> {
                            if(stack.isOf(Items.COAL) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.COBBLESTONE));
                            }else if(stack.isOf(Items.DIAMOND) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.COAL));
                            }else if(stack.isOf(Items.EMERALD) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.RAW_GOLD));
                            }else if(stack.isOf(Items.RAW_IRON) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.IRON_NUGGET));
                            }else if(stack.isOf(Items.RAW_COPPER) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(DEE_Items.COPPER_NUGGET));
                            }else if(stack.isOf(Items.RAW_GOLD) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.GOLD_NUGGET));
                            }else if(stack.isOf(Items.GOLD_NUGGET) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.AIR));
                            }else if(stack.isOf(Items.QUARTZ) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.NETHERRACK));
                            }else if(stack.isOf(Items.LAPIS_LAZULI) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.COAL));
                            }else if(stack.isOf(Items.AMETHYST_SHARD) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.STICK));
                            }else if(stack.isOf(Items.RESIN_CLUMP) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.AMETHYST_SHARD));
                            }else if(stack.isOf(Items.GLOWSTONE_DUST) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.GLOW_INK_SAC));
                            }else if(stack.isOf(Items.MELON_SLICE) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.MELON_SEEDS));
                            }else if(stack.isOf(Items.NETHER_WART) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.WARPED_FUNGUS));
                            }else if(stack.isOf(Items.REDSTONE) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.RED_DYE));
                            }else if(stack.isOf(Items.PRISMARINE_CRYSTALS) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.CYAN_DYE));
                            }else if(stack.isOf(Items.SWEET_BERRIES) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.PURPLE_DYE));
                            }else if(stack.isOf(Items.BEETROOT_SEEDS) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.BEETROOT));
                            }else if(stack.isOf(Items.WHEAT_SEEDS) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.BEETROOT_SEEDS));
                            }else if(stack.isOf(Items.CARROT) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.ORANGE_DYE));
                            }else if(stack.isOf(Items.POTATO) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.POISONOUS_POTATO));
                            }else if(stack.isOf(Items.FLINT) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.STONE_BUTTON));
                            }else if(stack.isOf(Items.STICK) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.AIR));
                            }else if(stack.isOf(Items.APPLE) && world.random.nextFloat() >= (float) 1 /levels.get(index)){
                                dropStack(world,pos,new ItemStack(Items.POTATO));
                            }else{
                                dropStack(world,pos,stack);
                            }
                        });
                        state.onStacksDropped((ServerWorld) world, pos, tool, false);
                        ci.cancel();
                    }
                }
            }
        }
    }
}
