package com.addie.core;

import com.addie.WhoCraft;
import dev.amble.lib.container.impl.ItemGroupContainer;
import dev.amble.lib.itemgroup.AItemGroup;
import net.minecraft.item.ItemStack;

public class WhoCraftItemGroups implements ItemGroupContainer {

    // Main
    public static final AItemGroup MAIN = AItemGroup.builder(WhoCraft.id("item_group"))
            .icon(() -> new ItemStack(WhoCraftItems.GALLIFREYAN_IRON_KEY))
            .entries((displayContext, entries) -> {
            })
            .build();

    // Decor
    public static final AItemGroup DECOR = AItemGroup.builder(WhoCraft.id("item_group_decor"))
            .icon(() -> new ItemStack(WhoCraftedBlocks.CIRCLE_ROUNDEL))
            .entries((displayContext, entries) -> {
            })
            .build();
}


