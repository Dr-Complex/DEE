package com.dr_complex.double_edged_enchantments.entity.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HexingTableBlockEntity extends BlockEntity implements Nameable {
    @Nullable
    private Text customName;

    public HexingTableBlockEntity(BlockPos pos, BlockState state) {
        super(DEE_BlockEntityTypes.HexingTableBlockEntityType, pos, state);
    }

    public static void tick(@NotNull World world, @NotNull BlockPos blockPos, BlockState blockState, HexingTableBlockEntity hexingTableBlockEntity) {
        float time = (world.getTimeOfDay()/240f) * MathHelper.TAU;
        time = (MathHelper.sin(time*6f)/-3f) + time;
        world.addParticleClient(
                ParticleTypes.ENCHANT,
                blockPos.getX() + 0.5f,blockPos.getY()+1f,blockPos.getZ() + 0.5f,
                MathHelper.cos(time),1f, -MathHelper.sin(time)
        );
    }

    @Override
    public Text getName() {
        return (this.customName != null ? this.customName : Text.translatable("container.enchant"));
    }

    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        if (this.hasCustomName()) {
            nbt.put("CustomName", TextCodecs.CODEC, registries.getOps(NbtOps.INSTANCE), this.customName);
        }

    }

    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        this.customName = tryParseCustomName(nbt.get("CustomName"), registries);
    }


    public void setCustomName(@Nullable Text customName) {
        this.customName = customName;
    }

    @Nullable
    public Text getCustomName() {
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
}
