package com.addie.datagen.providers;

import com.addie.core.WhoCraftBlocks;
import com.addie.core.WhoCraftTags;
import dev.amble.lib.datagen.tag.AmbleBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class WhoCraftBlockTagProvider extends AmbleBlockTagProvider {
    public WhoCraftBlockTagProvider(FabricDataOutput output,
                               CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {


        getOrCreateTagBuilder(WhoCraftTags.Blocks.ROUNDELS)
                .add(WhoCraftBlocks.CIRCLE_ROUNDEL)
                .add(WhoCraftBlocks.CIRCLE_ROUNDEL_ALT);

        getOrCreateTagBuilder(WhoCraftTags.Blocks.ROUNDEL_DOORS)
                .add(WhoCraftBlocks.CIRCLE_ROUNDEL_DOOR_BLOCK);





        this.withBlocks(WhoCraftBlocks.class);
        super.configure(arg);
    }

    @Override
    public FabricTagProvider<Block>.FabricTagBuilder getOrCreateTagBuilder(TagKey<Block> tag) {
        return super.getOrCreateTagBuilder(tag);
    }
}

