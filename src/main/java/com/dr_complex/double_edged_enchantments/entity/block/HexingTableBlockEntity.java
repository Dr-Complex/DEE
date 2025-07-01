package com.dr_complex.double_edged_enchantments.entity.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HexingTableBlockEntity extends BlockEntity implements Nameable {
    @Nullable
    private Text customName;

    public HexingTableBlockEntity(BlockPos pos, BlockState state) {
        super(DEE_BlockEntityTypes.HexingTableBlockEntityType, pos, state);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries);
    }

    @Override
    public Text getName() {
        return (this.customName != null ? this.customName : Text.translatable("container.enchant"));
    }

    public void setCustomName(@Nullable Text customName) {
        this.customName = customName;
    }

    @Override
    protected void writeData(WriteView view) {
        super.writeData(view);
        view.putNullable("custom_name", TextCodecs.CODEC,this.customName);
    }

    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        this.customName = tryParseCustomName(view, "CustomName");
    }

    public @Nullable Text getCustomName() {
        return this.customName;
    }

    protected void readComponents(ComponentsAccess components) {
        super.readComponents(components);
        this.customName = components.get(DataComponentTypes.CUSTOM_NAME);
    }

    protected void addComponents(ComponentMap.Builder builder) {
        super.addComponents(builder);
        builder.add(DataComponentTypes.CUSTOM_NAME, this.customName);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, HexingTableBlockEntity hexingTableBlockEntity) {
        if(world instanceof ServerWorld serverWorld){
            int ticks = serverWorld.getTickManager().getStepTicks();
            if(hexingTableBlockEntity.hasCustomName()){
                world.addParticleClient(ParticleTypes.CRIT,blockPos.getX(),blockPos.getY(),blockPos.getZ(), MathHelper.sin(ticks),1,MathHelper.cos(ticks));
            }else {
                world.addParticleClient(ParticleTypes.ANGRY_VILLAGER,blockPos.getX(),blockPos.getY(),blockPos.getZ(), MathHelper.sin(ticks),0.5,MathHelper.cos(ticks));
            }
        }
    }
}
