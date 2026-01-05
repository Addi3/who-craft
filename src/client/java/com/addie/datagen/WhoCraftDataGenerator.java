package com.addie.datagen;

import com.addie.core.WhoCraftItemGroups;
import com.addie.core.WhoCraftItems;
import com.addie.core.WhoCraftBlocks;
import dev.amble.lib.datagen.lang.AmbleLanguageProvider;
import dev.amble.lib.datagen.lang.LanguageType;
import dev.amble.lib.datagen.loot.AmbleBlockLootTable;
import dev.amble.lib.datagen.sound.AmbleSoundProvider;
import dev.amble.lib.datagen.tag.AmbleBlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WhoCraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		FabricDataGenerator.Pack pack = gen.createPack();

		//generateachivement(pack);
		genLang(pack);
		genLoot(pack);
		genTags(pack);
		generateSoundData(pack);
		//generateRecipes(pack);
	}

	public void generateSoundData(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> new AmbleSoundProvider(output))));
	}

	private void genTags(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> new AmbleBlockTagProvider(output, registriesFuture).withBlocks(WhoCraftBlocks.class))));
	}

	private void genLoot(FabricDataGenerator.Pack pack) {
		pack.addProvider((((output, registriesFuture) -> new AmbleBlockLootTable(output).withBlocks(WhoCraftBlocks.class))));
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

			//Blocks
			provider.addTranslation(WhoCraftBlocks.SPACE_TIME_FABRICATOR,"Space-Time Fabricator");
			provider.addTranslation(WhoCraftBlocks.CIRCLE_ROUNDEL,"Roundel (Circle)");

			return provider;
		})));
	}
}
