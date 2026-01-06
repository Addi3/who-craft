package com.addie.datagen;

import com.addie.core.WhoCraftItemGroups;
import com.addie.core.WhoCraftItems;
import com.addie.core.WhoCraftBlocks;
import com.addie.datagen.providers.WhoCraftAchievementProvider;
import com.addie.datagen.providers.WhoCraftBlockTagProvider;
import com.addie.datagen.providers.WhoCraftItemTagProvider;
import com.addie.datagen.providers.WhoCraftModelGen;
import com.lib.datagenproviders.CallistoLibRecipeProvider;
import dev.amble.lib.datagen.lang.AmbleLanguageProvider;
import dev.amble.lib.datagen.lang.LanguageType;
import dev.amble.lib.datagen.loot.AmbleBlockLootTable;
import dev.amble.lib.datagen.sound.AmbleSoundProvider;
import dev.amble.lib.datagen.tag.AmbleBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import static net.minecraft.data.server.recipe.RecipeProvider.conditionsFromItem;
import static net.minecraft.data.server.recipe.RecipeProvider.hasItem;

public class WhoCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		FabricDataGenerator.Pack pack = gen.createPack();

		generateachivement(pack);
		genLang(pack);
		genLoot(pack);
		//genTags(pack);
		generateSoundData(pack);
		generateRecipes(pack);
		generateItemTags(pack);
		generateBlockTags(pack);
		genModels(pack);
	}


	public void generateBlockTags(FabricDataGenerator.Pack pack) {
		pack.addProvider(WhoCraftBlockTagProvider::new);
	}

	private void genModels(FabricDataGenerator.Pack pack) {
		pack.addProvider(((output, registriesFuture) -> {
			WhoCraftModelGen provider = new WhoCraftModelGen(output);
			provider.withBlocks(WhoCraftBlocks.class);
			provider.withItems(WhoCraftItems.class);
			return provider;
		}));

	}

	private void generateachivement(FabricDataGenerator.Pack pack) {
		pack.addProvider(WhoCraftAchievementProvider::new);
	}

	public void generateSoundData(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> new AmbleSoundProvider(output))));
	}

//	private void genTags(FabricDataGenerator.Pack pack) {
//		pack.addProvider((((output, registriesFuture) -> new AmbleBlockTagProvider(output, registriesFuture).withBlocks(WhoCraftBlocks.class))));
//	}
	public void generateItemTags(FabricDataGenerator.Pack pack) {
		pack.addProvider(WhoCraftItemTagProvider::new);
	}

	private void genLoot(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> new AmbleBlockLootTable(output).withBlocks(WhoCraftBlocks.class))));
	}

	public void generateRecipes(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> {
			CallistoLibRecipeProvider provider = new CallistoLibRecipeProvider(output);

			// Blocks
			;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, WhoCraftBlocks.SPACE_TIME_FABRICATOR, 1)
					.pattern("DCD")
					.pattern("GDG")
					.input('G', Items.GOLD_INGOT)
					.input('C', Blocks.CRAFTING_TABLE)
					.input('D', Blocks.COBBLED_DEEPSLATE)
					.criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
					.criterion(hasItem(Blocks.COBBLED_DEEPSLATE), conditionsFromItem(Blocks.COBBLED_DEEPSLATE))
					.criterion(hasItem(Blocks.CRAFTING_TABLE), conditionsFromItem(Blocks.CRAFTING_TABLE)));

			// Items
			;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, WhoCraftItems.CLASSIC_IRON_KEY, 1)
					.pattern(" IE")
					.pattern("IN ")
					.pattern("IN ")
					.input('I', Items.IRON_INGOT)
					.input('N', Items.IRON_NUGGET)
					.input('E', Items.ENDER_EYE)
					.criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
					.criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
					.criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE)));

			;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, WhoCraftItems.ROUNDEL_PATTERN, 1)
					.pattern(" PM")
					.input('P', Items.PAPER)
					.input('M', WhoCraftItems.ROUNDEL_MOULD)
					.criterion(hasItem(Items.PAPER), conditionsFromItem(Items.PAPER))
					.criterion(hasItem(WhoCraftItems.ROUNDEL_MOULD), conditionsFromItem(WhoCraftItems.ROUNDEL_MOULD)));

			;provider.addShapedRecipe(ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, WhoCraftItems.ROUNDEL_MOULD, 2)
					.pattern(" C ")
					.pattern("CCC")
					.pattern(" C ")
					.input('C', Items.CLAY_BALL)
					.criterion(hasItem(Items.CLAY_BALL), conditionsFromItem(Items.CLAY_BALL)));


			return provider;

		})));
	}

	private void genLang(FabricDataGenerator.Pack pack) {
		genEnglish(pack);
	}

	private void genEnglish(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> {
			AmbleLanguageProvider provider = new AmbleLanguageProvider(output, LanguageType.EN_US);

			//Misc
			provider.addTranslation("whocraft.container.spacetimefabricator","Fabrication");
			provider.addTranslation("text.whocraft.betatext","CAUTION: This is a BETA version of WhoCraft!");
				//Tooltips
			provider.addTranslation("item.whocraft.key.type","Type:");
			provider.addTranslation("item.whocraft.key.gold","Gold");
			provider.addTranslation("item.whocraft.key.iron","Iron");
			provider.addTranslation("item.whocraft.key.netherite","Netherite");
			provider.addTranslation("block.roundel.description","Can Be Dyed!");

				//ItemGroup
			provider.addTranslation(WhoCraftItemGroups.MAIN,"Who-Craft");
			provider.addTranslation(WhoCraftItemGroups.DECOR,"Who-Craft: Deco");

			//Items
			provider.addTranslation(WhoCraftItems.CLASSIC_GOLD_KEY,"Classic Key");
			provider.addTranslation(WhoCraftItems.CLASSIC_IRON_KEY,"Classic Key");
			provider.addTranslation(WhoCraftItems.CLASSIC_NETHERITE_KEY,"Classic Key");

			provider.addTranslation(WhoCraftItems.GALLIFREYAN_GOLD_KEY,"Gallifreyan Key");
			provider.addTranslation(WhoCraftItems.GALLIFREYAN_IRON_KEY,"Gallifreyan Key");
			provider.addTranslation(WhoCraftItems.GALLIFREYAN_NETHERITE_KEY,"Gallifreyan Key");

			provider.addTranslation(WhoCraftItems.YALE_GOLD_KEY,"Yale Key");
			provider.addTranslation(WhoCraftItems.YALE_IRON_KEY,"Yale Key");
			provider.addTranslation(WhoCraftItems.YALE_NETHERITE_KEY,"Yale Key");

			provider.addTranslation(WhoCraftItems.KEYCHAIN,"KeyChain");

			provider.addTranslation(WhoCraftItems.BLUEPRINT,"Blueprint");
			provider.addTranslation(WhoCraftItems.CIRCUIT,"Circuit");
			provider.addTranslation(WhoCraftItems.ROUNDEL_MOULD,"Roundel Mould");
			provider.addTranslation(WhoCraftItems.ROUNDEL_PATTERN,"Roundel Pattern");

			//Blocks
			provider.addTranslation(WhoCraftBlocks.SPACE_TIME_FABRICATOR,"Space-Time Fabricator");
			provider.addTranslation(WhoCraftBlocks.CIRCLE_ROUNDEL,"Roundel (Circle)");
			provider.addTranslation(WhoCraftBlocks.CIRCLE_ROUNDEL_ALT,"Roundel (Circle Alt)");
			provider.addTranslation(WhoCraftBlocks.CIRCLE_ROUNDEL_DOOR_BLOCK,"Roundel Door (Circle)");

			//Achievements
			provider.addTranslation("achievement.whocraft.title.root","Who-Craft");
			provider.addTranslation("achievement.whocraft.description.root","A journey through space and time!");

			provider.addTranslation("achievement.whocraft.title.obtain_fabricator_block","Fabrication");
			provider.addTranslation("achievement.whocraft.description.obtain_fabricator_block","Craft a Space Time Fabricator");

			provider.addTranslation("achievement.whocraft.title.obtain_key","Key To Time");
			provider.addTranslation("achievement.whocraft.description.obtain_key","Craft a TARDIS Key");

			provider.addTranslation("achievement.whocraft.title.collect_all_keys","Collect them all!");
			provider.addTranslation("achievement.whocraft.description.collect_all_keys","Obtail all Key variants");

			provider.addTranslation("achievement.whocraft.title.obtain_roundel","What are the round things?!");
			provider.addTranslation("achievement.whocraft.description.obtain_roundel","No seriously why are they?");

			provider.addTranslation("achievement.whocraft.title.obtain_roundel_door","What are the round things- door edition?!");
			provider.addTranslation("achievement.whocraft.description.obtain_roundel_door","Why are they on doors now?");

			return provider;
		})));
	}
}
