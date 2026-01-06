package com.addie.datagen.providers;

import com.addie.WhoCraft;
import com.addie.core.WhoCraftBlocks;
import com.addie.core.WhoCraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Consumer;

public class WhoCraftAchievementProvider extends FabricAdvancementProvider {
    public WhoCraftAchievementProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.create()
                .display(WhoCraftItems.GALLIFREYAN_GOLD_KEY,
                        Text.translatable("achievement.whocraft.title.root"),
                        Text.translatable("achievement.whocraft.description.root"),
                        new Identifier("who-craft","textures/overlay/background.png"),
                        AdvancementFrame.GOAL, true, true, false)
                // REPLACE STONE!!!
                .criterion("root", InventoryChangedCriterion.Conditions.items(Blocks.STONE))
                .build(consumer, WhoCraft.MOD_ID + "/root");

        Advancement fabricator = Advancement.Builder.create().parent(root)
                .display(WhoCraftBlocks.SPACE_TIME_FABRICATOR,
                        Text.translatable("achievement.whocraft.title.obtain_fabricator_block"),
                        Text.translatable("achievement.whocraft.description.obtain_fabricator_block"),
                        null, AdvancementFrame.GOAL, true, true, true)
                .criterion("obtain_fabricator_block", InventoryChangedCriterion.Conditions.items(WhoCraftBlocks.SPACE_TIME_FABRICATOR))
                .build(consumer, WhoCraft.MOD_ID + "/obtain_fabricator_block");

        Advancement key = Advancement.Builder.create().parent(root)
                .display(WhoCraftItems.CLASSIC_IRON_KEY,
                        Text.translatable("achievement.whocraft.title.obtain_key"),
                        Text.translatable("achievement.whocraft.description.obtain_key"),
                        null, AdvancementFrame.GOAL, true, true, true)
                .criterion("obtain_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.CLASSIC_IRON_KEY))
                .build(consumer, WhoCraft.MOD_ID + "/obtain_key");

        Advancement.Builder builder = Advancement.Builder.create()
                .parent(key)
                .display(
                        WhoCraftItems.YALE_IRON_KEY,
                        Text.translatable("achievement.whocraft.title.collect_all_keys"),
                        Text.translatable("achievement.whocraft.description.collect_all_keys"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true, true, true
                )
                .criterion("classic_gold_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.CLASSIC_GOLD_KEY))
                .criterion("classic_netherite_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.CLASSIC_NETHERITE_KEY))
                .criterion("gallifreyan_iron_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.GALLIFREYAN_IRON_KEY))
                .criterion("gallifreyan_gold_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.GALLIFREYAN_GOLD_KEY))
                .criterion("gallifreyan_netherite_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.GALLIFREYAN_NETHERITE_KEY))
                .criterion("yale_iron_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.YALE_IRON_KEY))
                .criterion("yale_gold_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.YALE_GOLD_KEY))
                .criterion("yale_netherite_key", InventoryChangedCriterion.Conditions.items(WhoCraftItems.YALE_NETHERITE_KEY));
        builder.requirements(new String[][]{
                {"classic_gold_key"},
                {"classic_netherite_key"},
                {"gallifreyan_iron_key"},
                {"gallifreyan_gold_key"},
                {"gallifreyan_netherite_key"},
                {"yale_iron_key"},
                {"yale_gold_key"},
                {"yale_netherite_key"}
        });

        builder.build(consumer, WhoCraft.MOD_ID + "/collect_all_keys");



    }
}


