package com.dr_complex.double_edged_enchantments.screen;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.block.entity.HexingTableBlockEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HexingTableScreenHandler extends ScreenHandler {
    private List<RegistryEntry<Enchantment>> enchants;
    private final Inventory Inventory;
    private final PropertyDelegate propertyDelegate;
    private final ScreenHandlerContext context;
    private final DynamicRegistryManager manager;
    private static final Identifier FuelTexture = DEE_Common.id("container/slot/fuel");
    private static final Identifier EoC_Texture = DEE_Common.id("container/slot/e_o_c");
    private static final Identifier UoD_Texture = DEE_Common.id("container/slot/u_o_d");


    public HexingTableScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos){
        this(syncId,playerInventory, (HexingTableBlockEntity) playerInventory.player.getWorld().getBlockEntity(pos), ScreenHandlerContext.EMPTY, new ArrayPropertyDelegate(1));
    }

    public HexingTableScreenHandler(int syncId, @NotNull PlayerInventory playerInventory, HexingTableBlockEntity blockEntity, ScreenHandlerContext context, PropertyDelegate propertyDelegate) {
        super(DEE_ScreenHandlers.HEXING_TABLE_SCREEN_HANDLER,syncId);
        this.Inventory = blockEntity;
        checkDataCount(propertyDelegate,1);
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);

        this.addSlot(new Slot(this.Inventory, 0, 40, 12) {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ItemTags.VANISHING_ENCHANTABLE);
            }

            @Override
            public void markDirty() {
                super.markDirty();
                HexingTableScreenHandler.this.onContentChanged(blockEntity);
            }
        });

        this.addSlot(new Slot(this.Inventory, 1, 40, 31) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.LAPIS_LAZULI) || stack.isOf(Items.COAL) || stack.isOf(Items.CHARCOAL);
            }

            @Override
            public @NotNull Identifier getBackgroundSprite() {
                return EoC_Texture;
            }

            @Override
            public void markDirty() {
                super.markDirty();
                HexingTableScreenHandler.this.onContentChanged(blockEntity);
            }
        });

        this.addSlot(new Slot(this.Inventory, 2, 40, 50) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.IRON_INGOT) || stack.isOf(Items.COPPER_INGOT);
            }

            @Override
            public @NotNull Identifier getBackgroundSprite() {
                return UoD_Texture;
            }

            @Override
            public void markDirty() {
                super.markDirty();
                HexingTableScreenHandler.this.onContentChanged(blockEntity);
            }
        });

        this.addSlot(new Slot(this.Inventory, 3, 10, 50) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(Items.END_CRYSTAL);
            }

            @Override
            public @NotNull Identifier getBackgroundSprite() {
                return FuelTexture;
            }

            @Override
            public void markDirty() {
                super.markDirty();
                HexingTableScreenHandler.this.onContentChanged(blockEntity);
            }
        });

        this.addPlayerSlots(playerInventory, 8, 84);
        this.context = context;
        this.manager = playerInventory.player.getWorld().getRegistryManager();

        if(this.getEnchants() == null){
            this.setEnchants(List.of());
        }
    }

    public int getFuel(){
        return this.propertyDelegate.get(0);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot == 0) {
                if (!this.insertItem(itemStack2, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot == 1) {
                if (!this.insertItem(itemStack2, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot == 2) {
                if (!this.insertItem(itemStack2, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot == 3) {
                if (!this.insertItem(itemStack2, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemStack2.isOf(Items.LAPIS_LAZULI) || itemStack2.isOf(Items.COAL) || itemStack2.isOf(Items.CHARCOAL)) {
                if (!this.insertItem(itemStack2, 0, 4, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemStack2.isOf(Items.IRON_INGOT) || itemStack2.isOf(Items.COPPER_INGOT)) {
                if (!this.insertItem(itemStack2, 0, 4, true)) {
                    return ItemStack.EMPTY;
                }
            }else if (itemStack2.isOf(Items.END_CRYSTAL)) {
                if (!this.insertItem(itemStack2, 0, 4, true)) {
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
        if (inventory == this.Inventory) {
            ItemStack MAIN_INPUT = this.Inventory.getStack(0);
            ItemStack EC_INPUT = this.Inventory.getStack(1);
            ItemStack UD_INPUT = this.Inventory.getStack(2);

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
        return this.Inventory.canPlayerUse(player);
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
            ItemStack InputItem = this.Inventory.getStack(0);
            ItemStack EoCItem = this.Inventory.getStack(1);
            ItemStack UoDItem = this.Inventory.getStack(2);
            int[] LevelPerEnchant = new int[getEnchants().size()];

            for (int k = 0; k < getEnchants().size(); k++) {
                LevelPerEnchant[k] = InputItem.getEnchantments().getLevel(getEnchants().get(k));
            }

            if (InputItem.isEmpty() && EoCItem.isEmpty() && UoDItem.isEmpty()) {
                return false;
            } else {
                if(EoCItem.getCount() < 4 || UoDItem.getCount() < 4){
                    return false;
                }

                int Up = UoDItem.isOf(Items.IRON_INGOT) ? 1:-1;

                if(LevelPerEnchant[id] >= 10 && Up == 1){
                    LevelPerEnchant[id] = 10;
                    return false;
                }

                if(LevelPerEnchant[id] < 0 && Up == -1){
                    LevelPerEnchant[id] = 0;
                    return false;
                }

                this.propertyDelegate.set(0,getFuel() - 1);
                if(getFuel() <= 0){
                    this.propertyDelegate.set(0,-1);
                    return false;
                }

                this.context.run((world, blockPos) -> {

                    ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(InputItem.getEnchantments());

                    for (int Idk = 0; Idk < enchants.size(); Idk++) {
                        if (Idk == id) {
                            builder.set(enchants.get(Idk), builder.getLevel(enchants.get(Idk)) + Up);
                        } else {
                            builder.set(enchants.get(Idk), builder.getLevel(enchants.get(Idk)));
                        }
                    }
                    ItemEnchantmentsComponent component = builder.build();

                    EnchantmentHelper.set(InputItem,component);

                    EoCItem.decrementUnlessCreative(2,player);
                    UoDItem.decrementUnlessCreative(2,player);

                    if (EoCItem.isEmpty()) {
                        this.Inventory.setStack(1, ItemStack.EMPTY);
                    }
                    if (UoDItem.isEmpty()) {
                        this.Inventory.setStack(2, ItemStack.EMPTY);
                    }

                    player.incrementStat(Stats.ENCHANT_ITEM);
                    if (player instanceof ServerPlayerEntity serverPlayer) {
                        Criteria.ENCHANTED_ITEM.trigger(serverPlayer, InputItem, Up);
                    }

                    this.Inventory.markDirty();
                    this.onContentChanged(this.Inventory);

                    world.playSound(player, blockPos, SoundEvents.BLOCK_ANVIL_DESTROY, SoundCategory.BLOCKS, 2.5f, 1.5f + (float) Up);
                });
                return true;
            }
        } else {
            DEE_Common.LOGGER.warn("no enchantment list");
            return false;
        }
    }
}
