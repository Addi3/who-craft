package com.addie.core;

import com.addie.core.blockentites.CircleRoundelDoorBlockEntity;
import dev.amble.lib.container.impl.BlockEntityContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;

public class WhoCraftBlockEntityTypes implements BlockEntityContainer {

    public static BlockEntityType<CircleRoundelDoorBlockEntity> CIRCLE_ROUNDEL_DOOR_BLOCK_ENTITY_TYPE = FabricBlockEntityTypeBuilder
            .create(CircleRoundelDoorBlockEntity::new, WhoCraftBlocks.CIRCLE_ROUNDEL_DOOR_BLOCK).build();
}
