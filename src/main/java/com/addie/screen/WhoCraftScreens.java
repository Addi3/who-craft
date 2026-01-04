package com.addie.screen;

import com.addie.WhoCraft;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class WhoCraftScreens {

    public static final ScreenHandlerType<SpaceTimeFabricatorScreenHandler> SPACE_TIME_FABRICATOR = register("space_time_fabricator", SpaceTimeFabricatorScreenHandler::new);

    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, (WhoCraft.id(id)), new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }

    public static void init() {

    }
}
