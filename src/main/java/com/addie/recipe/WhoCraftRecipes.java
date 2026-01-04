package com.addie.recipe;

import com.addie.WhoCraft;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface WhoCraftRecipes<T extends Recipe<?>> {
    RecipeType<FabricationRecipe> FABRICATION = register("fabrication");

    static <T extends Recipe<?>> RecipeType<T> register(String id) {
        return Registry.register(Registries.RECIPE_TYPE, WhoCraft.id(id), new RecipeType<T>() {
        });
    }
    public static void init() {

    }
}
