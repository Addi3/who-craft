package com.addie.recipe;


import com.addie.core.WhoCraftBlocks;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class FabricationRecipe implements Recipe<Inventory> {

    protected final Ingredient input;
    protected final Ingredient input1;
    protected final ItemStack output;
    protected final Identifier id;
    protected final String group;

    public FabricationRecipe(Identifier id, String group, Ingredient input, Ingredient input1, ItemStack output) {

        this.id = id;
        this.group = group;
        this.input = input;
        this.input1 = input1;
        this.output = output;
    }

    @Override
    public RecipeType<?> getType() {
        return WhoCraftRecipes.FABRICATION;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WhoCraftRecipeSerializers.FABRICATION;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.input);
        return defaultedList;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return this.output.copy();
    }

    public static class Serializer implements RecipeSerializer<FabricationRecipe> {


        public FabricationRecipe read(Identifier identifier, JsonObject jsonObject) {
            String string = JsonHelper.getString(jsonObject, "group", "");
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "top"), false);
            Ingredient ingredient1 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "bottom"), false);


            String string2 = JsonHelper.getString(jsonObject, "result");
            int i = JsonHelper.getInt(jsonObject, "count");
            ItemStack itemStack = new ItemStack(Registries.ITEM.get(new Identifier(string2)), i);
            return new FabricationRecipe(identifier, string, ingredient, ingredient1, itemStack);
        }

        public FabricationRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            String string = packetByteBuf.readString();
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient1 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new FabricationRecipe(identifier, string, ingredient, ingredient1, itemStack);
        }

        public void write(PacketByteBuf packetByteBuf, FabricationRecipe fabricationRecipe) {
            packetByteBuf.writeString(fabricationRecipe.group);
            fabricationRecipe.input.write(packetByteBuf);
            fabricationRecipe.input1.write(packetByteBuf);
            packetByteBuf.writeItemStack(fabricationRecipe.output);
        }
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        return this.input.test(inventory.getStack(0)) && this.input1.test(inventory.getStack(1));
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(WhoCraftBlocks.SPACE_TIME_FABRICATOR);
    }
}
