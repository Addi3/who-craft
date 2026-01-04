package com.addie;

import com.addie.core.WhoCraftItems;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class WhoCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModelPredicateProviderRegistry.register(
				WhoCraftItems.KEYCHAIN,
				new Identifier("filled"),
				(stack, world, entity, seed) -> {
					int count = 0;
					if (stack.hasNbt() && stack.getNbt().contains("Items")) {
						count = stack.getNbt().getList("Items", 10).size();
					}
					// Normalize 0..3 items to 0..1
					return count / 3f;
				}
		);

	}
}