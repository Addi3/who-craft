package com.addie.core.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class SpaceTImeFabricatorBlock extends Block {
    public SpaceTImeFabricatorBlock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 1.1;
        double z = pos.getZ() + 0.5;

        world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.05, 0.0);
        world.addParticle(ParticleTypes.CLOUD, x, y, z, 0.0, 0.05, 0.0);
        world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 0.0, 0.02, 0.0);
    }
}
