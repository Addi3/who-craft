package com.addie.models.decor;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class RoundelDoorModel extends SinglePartEntityModel {
    private final ModelPart root;
    public RoundelDoorModel(ModelPart root) {
        this.root = root.getChild("root");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create().uv(48, 0).cuboid(0.0F, -24.0F, 0.0F, 16.0F, 48.0F, 8.0F, new Dilation(-0.001F))
                .uv(48, 0).mirrored().cuboid(16.0F, -24.0F, 0.0F, 16.0F, 48.0F, 8.0F, new Dilation(-0.001F)).mirrored(false), ModelTransform.pivot(-8.0F, 0.0F, -8.0F));

        ModelPartData door = root.addChild("door", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData L = door.addChild("L", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -48.0F, -4.0F, 16.0F, 48.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 4.0F));

        ModelPartData R = door.addChild("R", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-16.0F, -48.0F, -4.0F, 16.0F, 48.0F, 8.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(32.0F, 24.0F, 4.0F));
        return TexturedModelData.of(modelData, 96, 96);
    }


    @Override
    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}

