package com.addie.core.blockentites;

import com.addie.core.WhoCraftBlockEntityTypes;
import com.addie.core.blocks.CircleRoundelDoorBlock;
import lombok.Getter;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CircleRoundelDoorBlockEntity extends BlockEntity {

    private float progress;
    @Getter
    private float prevProgress;

    public CircleRoundelDoorBlockEntity(BlockPos pos, BlockState state) {
        super(WhoCraftBlockEntityTypes.CIRCLE_ROUNDEL_DOOR_BLOCK_ENTITY_TYPE, pos, state);
        this.progress = 0;
        this.prevProgress = 0;
    }

    public float getScale() {
        return 1.0F;
    }


    public float getProgress(float tickDelta) {
        return prevProgress + (progress - prevProgress) * tickDelta;
    }

    public float getRawProgress() {
        return progress;
    }

    public void useOn(World world, boolean sneaking, PlayerEntity player) {
        if (world.isClient) return;

        boolean open = world.getBlockState(pos).get(CircleRoundelDoorBlock.OPEN);

        if (!open) {
            progress = 1.0f;
        } else {
            progress = 0.0f;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, CircleRoundelDoorBlockEntity be) {
        be.prevProgress = be.progress;

        boolean open = state.get(CircleRoundelDoorBlock.OPEN);

        if (open && be.progress < 1f) {
            be.progress += 0.03f; // adjust speed for animation
            if (be.progress > 1f) be.progress = 1f;
        }

        if (!open && be.progress > 0f) {
            be.progress -= 0.03f; // adjust speed for animation
            if (be.progress < 0f) be.progress = 0f;
        }
    }




}
