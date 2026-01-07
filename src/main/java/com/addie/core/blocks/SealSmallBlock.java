package com.addie.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SealSmallBlock extends SealBlock {

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> SHAPE_NORTH_SMALL;
            case SOUTH -> SHAPE_SOUTH_SMALL;
            case EAST -> SHAPE_EAST_SMALL;
            case WEST -> SHAPE_WEST_SMALL;
            default -> SHAPE_NORTH_SMALL;
        };
    }

    protected static final VoxelShape SHAPE_NORTH_SMALL = Block.createCuboidShape(
            0, 0, 0, 16, 16, 2
    );

    protected static final VoxelShape SHAPE_SOUTH_SMALL = Block.createCuboidShape(
            0, 0, 14, 16, 16, 16
    );

    protected static final VoxelShape SHAPE_EAST_SMALL = Block.createCuboidShape(
            14, 0, 0, 16, 16, 16
    );

    protected static final VoxelShape SHAPE_WEST_SMALL = Block.createCuboidShape(
            0, 0, 0, 2, 16, 16
    );

    public SealSmallBlock(Settings settings) {
        super(settings);
    }




    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        tooltip.add(Text.translatable("whocraft.block.tooltip.seal_small").formatted(Formatting.GOLD));

    }
}
