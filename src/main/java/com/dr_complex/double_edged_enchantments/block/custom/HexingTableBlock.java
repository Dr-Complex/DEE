package com.dr_complex.double_edged_enchantments.block.custom;

import com.dr_complex.double_edged_enchantments.block.entity.DEE_BlockEntityTypes;
import com.dr_complex.double_edged_enchantments.block.entity.HexingTableBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HexingTableBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final MapCodec<HexingTableBlock> CODEC = createCodec(HexingTableBlock::new);
    private static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,8,16);

    public HexingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(world.getBlockEntity(pos) instanceof HexingTableBlockEntity hex){
            if(!player.isSneaking() && !world.isClient){
                player.openHandledScreen(hex);
            }
            if (player.isSneaking()){
                return ActionResult.FAIL;
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new HexingTableBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        ItemScatterer.onStateReplaced(state, world, pos);
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : validateTicker(type, DEE_BlockEntityTypes.HexingTableBlockEntityType, HexingTableBlockEntity::tick);
    }
}
