package com.dr_complex.double_edged_enchantments.block.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class HexingTableBlockEntityRenderer implements BlockEntityRenderer<HexingTableBlockEntity> {

    BlockEntityRendererFactory.Context contextMain;
    float degrees = 0.0f;

    public HexingTableBlockEntityRenderer(BlockEntityRendererFactory.@NotNull Context context){
        this.contextMain = context;
    }

    @Override
    public void render(HexingTableBlockEntity entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        this.degrees += 0.5f;
        if(entity.getWorld() != null){
            World worldMain = entity.getWorld();

            matrices.push();
            matrices.translate(0.5f, 1.125f, 0.5f);
            matrices.scale(0.5f, 0.5f, 0.5f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(this.degrees * 0.85f), 0, 0, 0);
            itemRenderer.renderItem(
                    entity.getStack(0),
                    ItemDisplayContext.GUI,
                    getLightLevel(worldMain, entity.getPos()),
                    OverlayTexture.DEFAULT_UV,
                    matrices,
                    vertexConsumers,
                    worldMain, 1);
            matrices.pop();

            for (int i1 = 0; i1 < entity.getStack(1).getCount(); i1++) {
                matrices.push();
                matrices.translate(1f, 1.25f, 0.5f);
                matrices.scale(0.25f, 0.25f, 0.25f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((this.degrees * 0.65f) + i1 * (360f/entity.getStack(1).getCount())), -2f, 0, 0);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(this.degrees * 1.135f));
                itemRenderer.renderItem(
                        entity.getStack(1),
                        ItemDisplayContext.GUI,
                        getLightLevel(worldMain, entity.getPos()),
                        OverlayTexture.DEFAULT_UV,
                        matrices,
                        vertexConsumers,
                        worldMain, 1);
                matrices.pop();
            }

            for (int i2 = 0; i2 < entity.getStack(2).getCount(); i2++) {
                matrices.push();
                matrices.translate(1f, 1f, 0.5f);
                matrices.scale(0.25f, 0.25f, 0.25f);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((this.degrees * 0.25f) + (180f/entity.getStack(2).getCount()) + i2 * (360f/entity.getStack(2).getCount())), -2f, 0, 0);
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(this.degrees * 0.96f));
                itemRenderer.renderItem(
                        entity.getStack(2),
                        ItemDisplayContext.GUI,
                        getLightLevel(worldMain, entity.getPos()),
                        OverlayTexture.DEFAULT_UV,
                        matrices,
                        vertexConsumers,
                        worldMain, 1);
                matrices.pop();
            }

        }
    }

    private int getLightLevel(@NotNull World world, BlockPos pos){
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight,sLight);
    }
}
