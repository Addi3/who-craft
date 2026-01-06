package com.addie.core;

import com.addie.WhoCraft;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class WhoCraftTags {

    public static class Blocks {
        public static final TagKey<Block> ROUNDELS = createTag("roundels");
        public static final TagKey<Block> ROUNDEL_DOORS = createTag("roundel_doors");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, WhoCraft.id(name));
        }
    }
}
