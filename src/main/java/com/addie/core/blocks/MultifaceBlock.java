package com.addie.core.blocks;

import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class MultifaceBlock extends Block {

    public static final Map<Direction, BooleanProperty> FACING_PROPERTIES =
            ConnectingBlock.FACING_PROPERTIES;

    private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);

    static {
        SHAPES.put(Direction.UP,    Block.createCuboidShape(0, 15, 0, 16, 16, 16));
        SHAPES.put(Direction.DOWN,  Block.createCuboidShape(0, 0, 0, 16, 1, 16));
        SHAPES.put(Direction.SOUTH, Block.createCuboidShape(0, 0, 15, 16, 16, 16));
        SHAPES.put(Direction.NORTH, Block.createCuboidShape(0, 0, 0, 16, 16, 1));
        SHAPES.put(Direction.WEST,  Block.createCuboidShape(0, 0, 0, 1, 16, 16));
        SHAPES.put(Direction.EAST,  Block.createCuboidShape(15, 0, 0, 16, 16, 16));
    }

    public MultifaceBlock(Settings settings) {
        super(settings);

        BlockState state = this.stateManager.getDefaultState();
        for (BooleanProperty prop : FACING_PROPERTIES.values()) {
            state = state.with(prop, false);
        }
        this.setDefaultState(state);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        for (BooleanProperty prop : FACING_PROPERTIES.values()) {
            builder.add(prop);
        }
    }


    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState existing = ctx.getWorld().getBlockState(pos);

        for (Direction dir : ctx.getPlacementDirections()) {
            if (!canAttach(ctx.getWorld(), pos, dir)) continue;

            BooleanProperty prop = FACING_PROPERTIES.get(dir);

            if (existing.isOf(this)) {
                return existing.with(prop, true);
            }
            return this.getDefaultState().with(prop, true);
        }
        return null;
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext ctx) {
        return true;
    }


    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            WorldAccess world,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        BooleanProperty prop = FACING_PROPERTIES.get(direction);
        if (prop != null && state.get(prop) && !canAttach(world, pos, direction)) {
            state = state.with(prop, false);
        }

        return hasAnyFace(state) ? state : Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return hasAnyFace(state);
    }


    @Override
    public VoxelShape getOutlineShape(
            BlockState state,
            BlockView world,
            BlockPos pos,
            ShapeContext context
    ) {
        VoxelShape shape = VoxelShapes.empty();

        for (Direction dir : Direction.values()) {
            BooleanProperty prop = FACING_PROPERTIES.get(dir);
            if (prop != null && state.get(prop)) {
                shape = VoxelShapes.union(shape, SHAPES.get(dir));
            }
        }

        return shape.isEmpty() ? VoxelShapes.fullCube() : shape;
    }


    private static boolean canAttach(BlockView world, BlockPos pos, Direction dir) {
        BlockPos supportPos = pos.offset(dir);
        BlockState support = world.getBlockState(supportPos);
        return Block.isFaceFullSquare(
                support.getCollisionShape(world, supportPos),
                dir.getOpposite()
        );
    }

    private static boolean hasAnyFace(BlockState state) {
        for (BooleanProperty prop : FACING_PROPERTIES.values()) {
            if (state.get(prop)) return true;
        }
        return false;
    }


}
