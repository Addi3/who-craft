package com.addie.core.blockentites;

import com.addie.core.WhoCraftBlockEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CircleRoundelDoorBlockEntity extends BlockEntity {

    public CircleRoundelDoorBlockEntity(BlockPos pos, BlockState state) {
        super(WhoCraftBlockEntityTypes.CIRCLE_ROUNDEL_DOOR_BLOCK_ENTITY_TYPE, pos, state);
    }

    public float getScale() {
        return 1;
    }

    public void useOn(World world, boolean sneaking, PlayerEntity player) {
        if (world.isClient) return;
        BlockState state = world.getBlockState(pos);

    }
}


