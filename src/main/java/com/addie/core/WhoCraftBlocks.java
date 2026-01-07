package com.addie.core;

import com.addie.core.blocks.*;
import dev.amble.lib.block.ABlockSettings;
import dev.amble.lib.container.impl.BlockContainer;
import dev.amble.lib.datagen.util.NoEnglish;
import dev.amble.lib.datagen.util.PickaxeMineable;
import dev.amble.lib.item.AItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class WhoCraftBlocks extends BlockContainer {

    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block SPACE_TIME_FABRICATOR = new SpaceTimeFabricatorBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(WhoCraftItemGroups.MAIN)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.COPPER));


    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block SEAL_BLOCK = new SealBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(WhoCraftItemGroups.DECOR)).requiresTool()
            .strength(2.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.METAL));


    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block SEAL_SMALL_BLOCK = new SealSmallBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(WhoCraftItemGroups.DECOR)).requiresTool()
            .strength(2.0F, 3.0F).pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.METAL));

    // Roundels
    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block CIRCLE_ROUNDEL = new RoundelBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(WhoCraftItemGroups.DECOR)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque().luminance(state -> 13));

    @NoEnglish
    @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
    public static final Block CIRCLE_ROUNDEL_ALT = new RoundelBlock(ABlockSettings.create()
            .itemSettings(new AItemSettings().group(WhoCraftItemGroups.DECOR)).requiresTool()
            .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque().luminance(state -> 13));

        // Roundel Doors
        @PickaxeMineable(tool = PickaxeMineable.Tool.IRON)
        @NoEnglish
        public static final Block CIRCLE_ROUNDEL_DOOR_BLOCK = new CircleRoundelDoorBlock(ABlockSettings.create()
                .itemSettings(new AItemSettings().group(WhoCraftItemGroups.DECOR)).requiresTool()
                .strength(0.5F, 1.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.SHROOMLIGHT).nonOpaque().luminance(state -> 3));

}
