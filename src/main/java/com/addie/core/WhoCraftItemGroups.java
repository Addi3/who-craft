package com.addie.core;

import com.addie.WhoCraft;
import dev.amble.lib.container.impl.ItemGroupContainer;
import dev.amble.lib.itemgroup.AItemGroup;
import net.minecraft.item.ItemStack;

public class WhoCraftItemGroups implements ItemGroupContainer {

    public static final AItemGroup MAIN = AItemGroup.builder(WhoCraft.id("item_group"))
            .icon(() -> new ItemStack(WhoCraftItems.GALLIFREYAN_IRON_KEY))
            .entries((displayContext, entries) -> {
            })
            .build();
}

