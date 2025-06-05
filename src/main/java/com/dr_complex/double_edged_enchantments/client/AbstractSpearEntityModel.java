package com.dr_complex.double_edged_enchantments.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public abstract class AbstractSpearEntityModel<T extends SpearEntityRenderState> extends EntityModel<T> {


    public AbstractSpearEntityModel(ModelPart root) {
        super(root);
    }

    public static @NotNull TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, 3.15F, -0.5F, 1.0F, 16.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 0).cuboid(-1.0F, -4.85F, -0.5F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 0).mirrored().cuboid(1.0F, -3.85F, -0.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(10, 0).mirrored().cuboid(-2.0F, -3.85F, -0.5F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(10, 14).cuboid(2.0F, -2.85F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 14).mirrored().cuboid(-3.0F, -2.85F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(14, 14).cuboid(-0.5F, -5.85F, -0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(4, 9).cuboid(-0.5F, -3.85F, -1.0F, 1.0F, 6.0F, 2.0F, new Dilation(0.0F))
                .uv(14, 0).cuboid(0.5F, -1.85F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(14, 0).cuboid(-1.5F, -1.85F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 4.85F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(T state) {
        super.setAngles(state);
    }

}
