package com.addie;

import com.addie.clientscreens.SpaceTimeFabricatorScreen;
import com.addie.core.WhoCraftItems;
import com.addie.core.WhoCraftedBlocks;
import com.addie.screen.WhoCraftScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

public class WhoCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		// Keychain model predicates
		ModelPredicateProviderRegistry.register(
				WhoCraftItems.KEYCHAIN,
				new Identifier("filled"),
				(stack, world, entity, seed) -> {
					int count = 0;
					if (stack.hasNbt() && stack.getNbt().contains("Items")) {
						count = stack.getNbt().getList("Items", 10).size();
					}
					return count / 3f;
				}
		);

		ScreenRegistry.register(WhoCraftScreens.SPACE_TIME_FABRICATOR, SpaceTimeFabricatorScreen::new);
		BlockRenderLayerMapRegister();
	}

	public static void BlockRenderLayerMapRegister() {
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftedBlocks.CIRCLE_ROUNDEL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftedBlocks.SPACE_TIME_FABRICATOR, RenderLayer.getCutout());
	}
}