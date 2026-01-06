package com.addie.core.blocks;

import com.addie.core.WhoCraftBlockEntityTypes;
import com.addie.core.WhoCraftSounds;
import com.addie.core.blockentites.CircleRoundelDoorBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CircleRoundelDoorBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    protected static final VoxelShape SHAPE_NORTH = Block.createCuboidShape(-16, 0, 0, 16, 32, 8);
    protected static final VoxelShape SHAPE_SOUTH = Block.createCuboidShape(0, 0, 8, 32, 32, 16);
    protected static final VoxelShape SHAPE_WEST  = Block.createCuboidShape(0, 0, 0, 8, 32, 32);
    protected static final VoxelShape SHAPE_EAST  = Block.createCuboidShape(8, 0, -16, 16, 32, 16);


    public CircleRoundelDoorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(OPEN, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        return switch (dir) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case EAST  -> SHAPE_EAST;
            case WEST  -> SHAPE_WEST;
            default -> VoxelShapes.fullCube();
        };
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CircleRoundelDoorBlockEntity(pos, state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(OPEN)
                ? VoxelShapes.empty()
                : getOutlineShape(state, world, pos, context);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient) {
            return (lvl, pos, st, t) -> {
                if (t instanceof CircleRoundelDoorBlockEntity be) {
                    CircleRoundelDoorBlockEntity.tick(lvl, pos, st, be);
                }
            };
        } else {
            return (lvl, pos, st, t) -> {
                if (t instanceof CircleRoundelDoorBlockEntity be) {
                    CircleRoundelDoorBlockEntity.tick(lvl, pos, st, be);
                }
            };
        }
    }


    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!(world.getBlockEntity(pos) instanceof CircleRoundelDoorBlockEntity be)) {
            return super.onUse(state, world, pos, player, hand, hit);
        }

        if (!world.isClient) {
            boolean isOpen = state.get(OPEN);
            world.setBlockState(pos, state.with(OPEN, !isOpen), Block.NOTIFY_ALL);

            world.playSound(
                    null,
                    pos,
                    WhoCraftSounds.ROUNDEL_DOOR,
                    SoundCategory.BLOCKS,
                    3.0f,
                    1.0f
            );
        }

        be.useOn(world, player.isSneaking(), player);

        return ActionResult.SUCCESS;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos,
                               Block sourceBlock, BlockPos sourcePos, boolean notify) {

        if (world.isClient) return;

        boolean powered = world.isReceivingRedstonePower(pos);
        boolean open = state.get(OPEN);

        if (powered != open) {
            world.setBlockState(pos, state.with(OPEN, powered), Block.NOTIFY_ALL);

            world.playSound(
                    null,
                    pos,
                    WhoCraftSounds.ROUNDEL_DOOR,
                    SoundCategory.BLOCKS,
                    3.0f,
                    1.0f
            );
        }
    }

}
