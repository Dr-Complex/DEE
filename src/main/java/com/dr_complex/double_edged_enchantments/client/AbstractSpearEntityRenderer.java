package com.dr_complex.double_edged_enchantments.client;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.entity.projectiles.AbstractSpearEntity;
import com.dr_complex.double_edged_enchantments.utils.SpearType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class AbstractSpearEntityRenderer extends EntityRenderer<AbstractSpearEntity, SpearEntityRenderState> {

    protected SpearEntityModel model;

    private static final Map<SpearType, Identifier> TEXTURES = Map.of(
            SpearType.Wooden,DEE_Common.id("textures/entity/spear/wooden.png"),
            SpearType.Stone,DEE_Common.id("textures/entity/spear/stone.png"),
            SpearType.Golden,DEE_Common.id("textures/entity/spear/golden.png"),
            SpearType.Iron,DEE_Common.id("textures/entity/spear/iron.png"),
            SpearType.Diamond,DEE_Common.id("textures/entity/spear/diamond.png"),
            SpearType.Netherite, DEE_Common.id("textures/entity/spear/netherite.png"),
            SpearType.Copper,DEE_Common.id("textures/entity/spear/copper.png"),
            SpearType.Jettatium,DEE_Common.id("textures/entity/spear/jettatium.png")
    );
    
    public AbstractSpearEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new SpearEntityModel(context.getPart(SpearEntityModel.Main));
    }

    @Override
    public SpearEntityRenderState createRenderState() {
        return new SpearEntityRenderState();
    }

    @Override
    public void render(@NotNull SpearEntityRenderState state, @NotNull MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw - 90.0f));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(state.pitch + 90.0f));

        VertexConsumer vertexConsumerReturnable = ItemRenderer.getItemGlintConsumer(
                vertexConsumers,
                this.model.getLayer(TEXTURES.get(state.Type)),
                false, state.Enchanted
        );
        this.model.render(matrices,vertexConsumerReturnable,light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
    }

    @Override
    public void updateRenderState(AbstractSpearEntity entity, SpearEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.Type = entity.getTypeSpear();
        state.pitch = entity.getLerpedPitch(tickDelta);
        state.yaw = entity.getLerpedYaw(tickDelta);
        state.Enchanted = entity.isEnchanted();
    }
}
