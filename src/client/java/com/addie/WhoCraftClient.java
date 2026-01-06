package com.addie;

import com.addie.clientscreens.BetaText;
import com.addie.clientscreens.SpaceTimeFabricatorScreen;
import com.addie.core.WhoCraftItems;
import com.addie.core.WhoCraftBlocks;
import com.addie.screen.WhoCraftScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import static com.addie.WhoCraft.id;

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
		resourcepackRegister();

		BetaText.register();
	}

	public static void BlockRenderLayerMapRegister() {
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.CIRCLE_ROUNDEL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.CIRCLE_ROUNDEL_ALT, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.SPACE_TIME_FABRICATOR, RenderLayer.getCutout());
	}

	public static void resourcepackRegister() {

		// Register builtin resourcepacks
		FabricLoader.getInstance().

				getModContainer("who-craft").

				ifPresent(modContainer ->

				{
					ResourceManagerHelper.registerBuiltinResourcePack(id("whocraftmenu"), modContainer, ResourcePackActivationType.DEFAULT_ENABLED);
					ResourceManagerHelper.registerBuiltinResourcePack(id("roundelemissions"), modContainer, ResourcePackActivationType.DEFAULT_ENABLED);
				});
	}
}