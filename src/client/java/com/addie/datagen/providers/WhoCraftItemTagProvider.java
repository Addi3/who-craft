package com.addie.datagen.providers;

import com.addie.WhoCraft;
import com.addie.core.WhoCraftItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class WhoCraftItemTagProvider extends FabricTagProvider<Item> {

    public  WhoCraftItemTagProvider(FabricDataOutput output,
                                    @Nullable CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, RegistryKeys.ITEM, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {


        getOrCreateTagBuilder(WhoCraftTags.Items.KEYCHAIN_ITEMS)
                .add(WhoCraftItems.GALLIFREYAN_IRON_KEY)
                .add(WhoCraftItems.GALLIFREYAN_GOLD_KEY)
                .add(WhoCraftItems.GALLIFREYAN_NETHERITE_KEY)
                .add(WhoCraftItems.CLASSIC_IRON_KEY)
                .add(WhoCraftItems.CLASSIC_GOLD_KEY)
                .add(WhoCraftItems.CLASSIC_NETHERITE_KEY)
                .add(WhoCraftItems.YALE_IRON_KEY)
                .add(WhoCraftItems.YALE_GOLD_KEY)
                .add(WhoCraftItems.YALE_NETHERITE_KEY);

   }

    public static class WhoCraftTags {
        public static class Items {
            public static final TagKey<Item> KEYCHAIN_ITEMS = TagKey.of(
                    RegistryKeys.ITEM,
                    id("keychain_items")
            );

        }
    }

    public static Identifier id(String path) {
        return new Identifier(WhoCraft.MOD_ID, path);
    }
}
