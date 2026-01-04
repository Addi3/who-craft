package com.addie.core.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;

public class RoundelBlock extends MultifaceBlock {

    public static final EnumProperty<DyeColor> COLOR = EnumProperty.of("color", DyeColor.class);

    public RoundelBlock(Settings settings) {
        super(settings);

        this.setDefaultState(this.getDefaultState().with(COLOR, DyeColor.WHITE));
    }

    @Override
    public BlockState rotate(BlockState state, net.minecraft.util.BlockRotation rotation) {
        return super.rotate(state, rotation).with(COLOR, state.get(COLOR));
    }

    @Override
    public BlockState mirror(BlockState state, net.minecraft.util.BlockMirror mirror) {
        return super.mirror(state, mirror).with(COLOR, state.get(COLOR));
    }

    @Override
    protected void appendProperties(net.minecraft.state.StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(COLOR);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.getItem() instanceof DyeItem dyeItem) {
            DyeColor color = dyeItem.getColor();
            if (state.get(COLOR) != color) {
                if (!world.isClient) {
                    world.setBlockState(pos, state.with(COLOR, color), 3);
                    if (!player.isCreative()) {
                        stack.decrement(1);
                    }
                }
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }
}
