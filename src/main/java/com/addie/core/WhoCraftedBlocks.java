package com.addie.core;

import com.addie.core.blocks.RoundelBlock;
import com.addie.core.blocks.SpaceTimeFabricatorBlock;
import dev.amble.lib.block.ABlockSettings;
import dev.amble.lib.container.impl.BlockContainer;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.datagen.util.PickaxeMineable;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class WhoCraftedBlocks extends BlockContainer {

    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block SPACE_TIME_FABRICATOR = new SpaceTimeFabricatorBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(WhoCraftItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.COPPER).luminance(9).lightLevel(9));

    // Roundels

    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block CIRCLE_ROUNDEL = new RoundelBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(WhoCraftItemGroups.DECOR)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque());

}
