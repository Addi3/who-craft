package com.addie.core;

import com.addie.core.items.GoldKeyItem;
import com.addie.core.items.IronKeyItem;
import com.addie.core.items.KeyChainItem;
import com.addie.core.items.NetheriteKeyItem;
import dev.amble.lib.container.impl.ItemContainer;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.item.Item;

public class WhoCraftItems extends ItemContainer {

    //Keys

        //Classic
    @NoEnglish
    public static final Item CLASSIC_GOLD_KEY = new GoldKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item CLASSIC_IRON_KEY = new IronKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item CLASSIC_NETHERITE_KEY = new NetheriteKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

        //Gallifreyan
    @NoEnglish
    public static final Item GALLIFREYAN_GOLD_KEY = new GoldKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item GALLIFREYAN_IRON_KEY = new IronKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item GALLIFREYAN_NETHERITE_KEY = new NetheriteKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

        //Yale
    @NoEnglish
    public static final Item YALE_GOLD_KEY = new GoldKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item YALE_IRON_KEY = new IronKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

    @NoEnglish
    public static final Item YALE_NETHERITE_KEY = new NetheriteKeyItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

        //Keychain

    @NoEnglish
    public static final Item KEYCHAIN = new KeyChainItem(new AItemSettings().group(WhoCraftItemGroups.MAIN).maxCount(1));

}
