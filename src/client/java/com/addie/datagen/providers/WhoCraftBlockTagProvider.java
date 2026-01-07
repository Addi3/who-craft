package com.addie.datagen.providers;

import com.addie.core.WhoCraftBlocks;
import dev.amble.lib.datagen.tag.AmbleBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class WhoCraftBlockTagProvider extends AmbleBlockTagProvider {
    public WhoCraftBlockTagProvider(FabricDataOutput output,
                               CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {




        this.withBlocks(WhoCraftBlocks.class);
        super.configure(arg);
    }

    @Override
    public FabricTagProvider<Block>.FabricTagBuilder getOrCreateTagBuilder(TagKey<Block> tag) {
        return super.getOrCreateTagBuilder(tag);
    }
}

