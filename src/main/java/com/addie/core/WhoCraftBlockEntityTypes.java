package com.addie.core;

import com.addie.core.blockentites.CircleRoundelDoorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class WhoCraftBlockEntityTypes {

    public static final BlockEntityType<CircleRoundelDoorBlockEntity> CIRCLE_ROUNDEL_DOOR_BLOCK_ENTITY_TYPE =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    new Identifier("whocraft", "circle_roundel_door"),
                    FabricBlockEntityTypeBuilder.create(
                            CircleRoundelDoorBlockEntity::new,
                            WhoCraftBlocks.CIRCLE_ROUNDEL_DOOR_BLOCK
                    ).build(null)
            );
}
