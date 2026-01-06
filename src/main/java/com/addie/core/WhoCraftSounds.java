package com.addie.core;

import com.addie.WhoCraft;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class WhoCraftSounds {

    public static final SoundEvent SPACE_AND_TIME = register("ambience/space_and_time");

    public static final SoundEvent MAIN_THEME = register("main_theme");

    public static final SoundEvent ROUNDEL_DOOR = register("tardis/roundel_door");


    // Register a SoundEvent
    private static SoundEvent register(String name) {
        Identifier id = WhoCraft.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    // Initialize all sounds
    public static void init() {
        // This ensures the static fields are loaded and registered
    }
}
