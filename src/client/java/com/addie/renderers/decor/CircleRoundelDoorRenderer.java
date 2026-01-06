package com.addie.renderers.decor;

import com.addie.WhoCraft;
import com.addie.core.blockentites.CircleRoundelDoorBlockEntity;
import com.addie.core.blocks.CircleRoundelDoorBlock;
import com.addie.models.decor.RoundelDoorModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

public class CircleRoundelDoorRenderer<T extends CircleRoundelDoorBlockEntity> implements BlockEntityRenderer<T> {

    public static final Identifier DOOR_TEXTURE = new Identifier(
            WhoCraft.MOD_ID, "textures/blockentities/decor/circle_roundel_door.png");
    public static final Identifier EMISSIVE_DOOR_TEXTURE = new Identifier(
            WhoCraft.MOD_ID, "textures/blockentities/decor/circle_roundel_door_emission.png");

    private final RoundelDoorModel roundeldoorModel;

    public CircleRoundelDoorRenderer(BlockEntityRendererFactory.Context ctx) {
        this.roundeldoorModel = new RoundelDoorModel(RoundelDoorModel.getTexturedModelData().createModel());
    }

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light, int overlay) {

        BlockState blockState = entity.getCachedState();
        if (!(blockState.getBlock() instanceof CircleRoundelDoorBlock)) {
            return;
        }

        Direction direction = blockState.get(CircleRoundelDoorBlock.FACING);


        float rotationDegrees = switch (direction) {
            case NORTH -> 180f;
            case SOUTH -> 0f;
            case WEST -> 90f;
            case EAST -> -90f;
            default -> 0f;
        };

        matrices.push();
        matrices.translate(0.5f, 1.5f, 0.5f);
        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(rotationDegrees));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));

        float scale = entity.getScale();
        matrices.scale(scale, scale, scale);


        this.roundeldoorModel.render(matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(DOOR_TEXTURE)),
                light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);


        this.roundeldoorModel.render(matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityTranslucentEmissive(EMISSIVE_DOOR_TEXTURE)),
                0xF000F00, overlay, 1.0F, 1.0F, 1.0F, 1.0F);

        matrices.pop();
    }
}

