package com.addie;

import com.addie.core.WhoCraftItemGroups;
import com.addie.core.WhoCraftItems;
import com.addie.core.WhoCraftedBlocks;
import dev.amble.lib.container.RegistryContainer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhoCraft implements ModInitializer {
	public static final String MOD_ID = "who-craft";

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	//public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		RegistryContainer.register(WhoCraftItems.class, MOD_ID);
		RegistryContainer.register(WhoCraftItemGroups.class, MOD_ID);
		RegistryContainer.register(WhoCraftedBlocks.class, MOD_ID);
	}
}