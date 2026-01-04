package com.addie.core.blocks;

import com.addie.screen.SpaceTimeFabricatorScreenHandler;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SpaceTimeFabricatorBlock extends Block {
    private static final Text TITLE = Text.translatable("whocraft.container.spacetimefabricator");

    public SpaceTimeFabricatorBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            player.incrementStat(Stats.INTERACT_WITH_STONECUTTER);
            return ActionResult.CONSUME;
        }
    }

    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new SpaceTimeFabricatorScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos)), TITLE);
    }


    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}


//    @Environment(EnvType.CLIENT)
//    @Override
//    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
//
//        double x = pos.getX() + 0.5;
//        double y = pos.getY() + 1.1;
//        double z = pos.getZ() + 0.5;
//
//        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.05, 0.0);
//        world.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0, 0.05, 0.0);
//        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 0.0, 0.02, 0.0);
//    }

