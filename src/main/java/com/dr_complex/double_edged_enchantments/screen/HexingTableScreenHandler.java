package com.dr_complex.double_edged_enchantments.screen;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;

import java.util.ArrayList;
import java.util.List;

public class HexingTableScreenHandler extends ScreenHandler {
    private List<RegistryEntry<Enchantment>> enchants;
    private final Inventory InputInventory;
    private final ScreenHandlerContext context;
    private final DynamicRegistryManager manager;

    public HexingTableScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId,playerInventory, ScreenHandlerContext.EMPTY);
    }

    public HexingTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context){
        super(DEE_ScreenHandlers.HEXING_TABLE_SCREEN_HANDLER,syncId);

        this.InputInventory = new SimpleInventory(3){
            @Override
            public void markDirty() {
                super.markDirty();
                HexingTableScreenHandler.this.onContentChanged(this);
            }
        };

        this.addSlot(new Slot(this.InputInventory, 0, 26, 30) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ItemTags.VANISHING_ENCHANTABLE);
            }}
        );

        this.addSlot(new Slot(this.InputInventory, 1, 17, 12) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.LAPIS_LAZULI) || stack.isOf(Items.COAL) || stack.isOf(Items.CHARCOAL);
            }}
        );

        this.addSlot(new Slot(this.InputInventory, 2, 35, 12) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.IRON_INGOT) || stack.isOf(Items.COPPER_INGOT);
            }}
        );

        this.addPlayerSlots(playerInventory, 8, 84);
        this.context = context;
        this.manager = playerInventory.player.getWorld().getRegistryManager();
        this.setEnchants(List.of());
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot == 0) {
                if (!this.insertItem(itemStack2, 3, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot == 1) {
                if (!this.insertItem(itemStack2, 3, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot == 2) {
                if (!this.insertItem(itemStack2, 3, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemStack2.isOf(Items.LAPIS_LAZULI) || itemStack2.isOf(Items.COAL) || itemStack2.isOf(Items.CHARCOAL)) {
                if (!this.insertItem(itemStack2, 0, 3, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemStack2.isOf(Items.IRON_INGOT) || itemStack2.isOf(Items.COPPER_INGOT)) {
                if (!this.insertItem(itemStack2, 0, 3, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (this.slots.getFirst().hasStack() || !this.slots.getFirst().canInsert(itemStack2)) {
                    return ItemStack.EMPTY;
                }

                ItemStack itemStack3 = itemStack2.copyWithCount(1);
                itemStack2.decrement(1);
                this.slots.getFirst().setStack(itemStack3);
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        if (inventory == this.InputInventory) {
            ItemStack MAIN_INPUT = this.InputInventory.getStack(0);
            ItemStack EC_INPUT = this.InputInventory.getStack(1);
            ItemStack UD_INPUT = this.InputInventory.getStack(2);

            if(!MAIN_INPUT.isEmpty() && !EC_INPUT.isEmpty() && !UD_INPUT.isEmpty()){
                    List<RegistryEntry<Enchantment>> List = new ArrayList<>();

                    if(EC_INPUT.isOf(Items.LAPIS_LAZULI)){
                        this.manager.getOptional(RegistryKeys.ENCHANTMENT)
                                .ifPresent(streamer -> streamer.streamEntries()
                                        .filter(filters -> !filters.isIn(EnchantmentTags.CURSE) && filters.value().isAcceptableItem(MAIN_INPUT))
                                        .forEach(List::add));
                    }else {
                        this.manager.getOptional(RegistryKeys.ENCHANTMENT)
                                .ifPresent(streamer -> streamer.streamEntries()
                                        .filter(filters -> filters.isIn(EnchantmentTags.CURSE) && filters.value().isAcceptableItem(MAIN_INPUT))
                                        .forEach(List::add));
                    }

                    this.setEnchants(List);
            }else {
                this.setEnchants(List.of());
            }
            this.sendContentUpdates();
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.InputInventory.canPlayerUse(player);
    }

    public void setEnchants(List<RegistryEntry<Enchantment>> enchants) {
        this.enchants = enchants;
    }

    public List<RegistryEntry<Enchantment>> getEnchants(){
        return this.enchants;
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id >= 0 && id < this.enchants.size()) {
            ItemStack Input = this.InputInventory.getStack(0);
            ItemStack EoC = this.InputInventory.getStack(1);
            ItemStack UoD = this.InputInventory.getStack(2);
            int[] LevelPerEnchant = new int[getEnchants().size()];

            for (int k = 0; k < getEnchants().size(); k++) {
                LevelPerEnchant[k] = Input.getEnchantments().getLevel(getEnchants().get(k));
            }

            if (Input.isEmpty() && EoC.isEmpty() && UoD.isEmpty()) {
                return false;
            } else {
                if(EoC.getCount() < 4 || UoD.getCount() < 4){
                    return false;
                }

                int Up = UoD.isOf(Items.IRON_INGOT) ? 1:-1;

                if(LevelPerEnchant[id] >= 10 && Up == 1){
                    LevelPerEnchant[id] = 10;
                    return true;
                }

                if(LevelPerEnchant[id] < 0 && Up == -1){
                    LevelPerEnchant[id] = 0;
                    return true;
                }

                this.context.run((world, blockPos) -> {

                    ItemEnchantmentsComponent component = EnchantmentHelper.apply(Input, builder -> {
                        for (int w = 0; w < enchants.size(); w++) {
                            if(w == id){
                                builder.set(enchants.get(w),LevelPerEnchant[w] + Up);
                            }else {
                                builder.set(enchants.get(w),LevelPerEnchant[w]);
                            }
                        }
                        builder.build();
                    });

                    EnchantmentHelper.set(Input,component);

                    EoC.decrementUnlessCreative(world.random.nextBetween(0,4),player);
                    UoD.decrementUnlessCreative(world.random.nextBetween(0,4),player);
                    if (EoC.isEmpty()) {
                        this.InputInventory.setStack(1, ItemStack.EMPTY);
                    }
                    if (UoD.isEmpty()) {
                        this.InputInventory.setStack(2, ItemStack.EMPTY);
                    }

                    player.incrementStat(Stats.ENCHANT_ITEM);
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Criteria.ENCHANTED_ITEM.trigger(serverPlayer, Input, Up);
                    }

                    this.InputInventory.markDirty();
                    this.onContentChanged(this.InputInventory);

                    world.playSound(player, blockPos, SoundEvents.ENTITY_ENDER_EYE_DEATH, SoundCategory.BLOCKS, 2.5f, 1.5f + (float) Up);
                });
                return true;
            }
        } else {
            DEE_Common.LOGGER.error("{} is out of range of {}",id, this.enchants.size());
            return false;
        }
    }

    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.InputInventory));
    }
}
