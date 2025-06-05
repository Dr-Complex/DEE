package com.dr_complex.double_edged_enchantments.block.custom;

import com.dr_complex.double_edged_enchantments.entity.block.DEE_BlockEntityTypes;
import com.dr_complex.double_edged_enchantments.entity.block.HexingTableBlockEntity;
import com.dr_complex.double_edged_enchantments.screen.HexingTableScreenHandler;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Nameable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HexingTableBlock extends BlockWithEntity {

    public static final MapCodec<HexingTableBlock> CODEC = createCodec(HexingTableBlock::new);
    private static final VoxelShape SHAPE = Block.createColumnShape(16.0, 0.0, 8.0);

    public HexingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    protected ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(!world.isClient){
            player.openHandledScreen(state.createScreenHandlerFactory(world,pos));
        }
        if(player.isSneaking()){
            world.playSound(player,pos, SoundEvents.ENTITY_GENERIC_DEATH, SoundCategory.BLOCKS,1.0f,0.25f);
            return ActionResult.FAIL;
        }else {
            world.playSound(player,pos, SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.BLOCKS,1.0f,0.25f);
            return ActionResult.SUCCESS;
        }
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
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, DEE_BlockEntityTypes.HexingTableBlockEntityType,HexingTableBlockEntity::tick);
    }

    @Override
    protected void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        ItemScatterer.onStateReplaced(state, world, pos);
    }

    @Nullable
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof HexingTableBlockEntity) {
            Text text = ((Nameable)blockEntity).getDisplayName();
            return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> new HexingTableScreenHandler(syncId,inventory,ScreenHandlerContext.create(world,pos)), text);
        } else {
            return null;
        }
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

}
