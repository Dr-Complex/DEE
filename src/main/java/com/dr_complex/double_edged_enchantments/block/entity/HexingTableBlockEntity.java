package com.dr_complex.double_edged_enchantments.block.entity;

import com.dr_complex.double_edged_enchantments.screen.HexingTableScreenHandler;
import com.dr_complex.double_edged_enchantments.utils.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HexingTableBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4,ItemStack.EMPTY);
    int fuel;

    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return HexingTableBlockEntity.this.fuel;
        }

        @Override
        public void set(int index, int value) {
            HexingTableBlockEntity.this.fuel = value;
        }

        @Override
        public int size() {
            return 1;
        }
    };

    public HexingTableBlockEntity(BlockPos pos, BlockState state) {
        super(DEE_BlockEntityTypes.HexingTableBlockEntityType, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, @NotNull HexingTableBlockEntity blockEntity) {
        ItemStack fuelItem = blockEntity.inventory.get(3);

        if(blockEntity.fuel <= 31 && fuelItem.isOf(Items.END_CRYSTAL)){
            blockEntity.fuel += 1;
            fuelItem.decrement(1);
            markDirty(world,pos,state);
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new HexingTableScreenHandler(syncId, playerInventory, this, ScreenHandlerContext.create(world,pos), propertyDelegate);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.hexing_table");
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        Inventories.writeData(view,inventory);
        view.putByte("Fuel", (byte)this.fuel);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        Inventories.readData(view,inventory);
        this.fuel = view.getByte("Fuel", (byte)0);
    }

    @Override
    public void onBlockReplaced(BlockPos pos, BlockState oldState) {
        if(world != null){
            ItemScatterer.spawn(world,pos,inventory);
        }
        super.onBlockReplaced(pos, oldState);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
