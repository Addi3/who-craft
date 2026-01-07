package com.addie;

import com.addie.clientscreens.BetaText;
import com.addie.clientscreens.SpaceTimeFabricatorScreen;
import com.addie.core.WhoCraftBlockEntityTypes;
import com.addie.core.WhoCraftItems;
import com.addie.core.WhoCraftBlocks;
import com.addie.renderers.decor.CircleRoundelDoorRenderer;
import com.addie.screen.WhoCraftScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
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
		blockEntityRendererRegister();

		BetaText.register();
	}

	public static void blockEntityRendererRegister() {

		BlockEntityRendererFactories.register(WhoCraftBlockEntityTypes.CIRCLE_ROUNDEL_DOOR_BLOCK_ENTITY_TYPE, CircleRoundelDoorRenderer::new);
	}
	public static void BlockRenderLayerMapRegister() {
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.CIRCLE_ROUNDEL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.CIRCLE_ROUNDEL_ALT, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.SPACE_TIME_FABRICATOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.SEAL_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(WhoCraftBlocks.SEAL_SMALL_BLOCK, RenderLayer.getCutout());
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