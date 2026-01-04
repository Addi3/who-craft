package com.addie.recipe;

import com.addie.WhoCraft;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class WhoCraftRecipeSerializers {

    public static final RecipeSerializer<FabricationRecipe> FABRICATION = register("fabrication", new FabricationRecipe.Serializer());

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, WhoCraft.id(id), serializer);
    }
    public static void init() {

    }
}
