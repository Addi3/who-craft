package com.addie.screen;

import com.addie.core.WhoCraftedBlocks;
import com.addie.recipe.FabricationRecipe;
import com.addie.recipe.WhoCraftRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class SpaceTimeFabricatorScreenHandler extends ScreenHandler {
    public static final int INPUT_ID = 0;
    public static final int OUTPUT_ID = 1;
    private static final int INVENTORY_START = 2;
    private static final int INVENTORY_END = 29;
    private static final int OUTPUT_START = 29;
    private static final int OUTPUT_END = 38;
    private final ScreenHandlerContext context;
    private final Property selectedRecipe = Property.create();
    private final World world;
    private List<FabricationRecipe> availableRecipes = new ArrayList<>();
    private ItemStack inputStack = ItemStack.EMPTY;
    private ItemStack inputStack1 = ItemStack.EMPTY;
    long lastTakeTime;
    final Slot inputSlot;
    final Slot inputSlot1;
    final Slot outputSlot;

    Runnable contentsChangedListener = () -> {};
    public final Inventory input = new SimpleInventory(2) {
        @Override
        public void markDirty() {
            super.markDirty();
            SpaceTimeFabricatorScreenHandler.this.onContentChanged(this);
            SpaceTimeFabricatorScreenHandler.this.contentsChangedListener.run();
        }
    };

    final CraftingResultInventory output = new CraftingResultInventory();

    public SpaceTimeFabricatorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public SpaceTimeFabricatorScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(WhoCraftScreens.SPACE_TIME_FABRICATOR, syncId);
        this.context = context;
        this.world = playerInventory.player.getWorld();
        this.inputSlot = this.addSlot(new Slot(this.input, 0, 20, 16));
        this.inputSlot1 = this.addSlot(new Slot(this.input, 1, 20, 52));
        this.outputSlot = this.addSlot(new Slot(this.output, 2, 143, 33) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                stack.onCraft(player.getWorld(), player, stack.getCount());
                SpaceTimeFabricatorScreenHandler.this.output.unlockLastRecipe(player, this.getInputStacks());
                ItemStack itemStack = SpaceTimeFabricatorScreenHandler.this.inputSlot.takeStack(1);
                ItemStack itemStack1 = SpaceTimeFabricatorScreenHandler.this.inputSlot1.takeStack(1);
                if (!itemStack.isEmpty() && !itemStack1.isEmpty()) {
                    SpaceTimeFabricatorScreenHandler.this.populateResult();
                }

                context.run((world, pos) -> {
                    long l = world.getTime();
                    if (SpaceTimeFabricatorScreenHandler.this.lastTakeTime != l) {
                        world.playSound(null, pos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        SpaceTimeFabricatorScreenHandler.this.lastTakeTime = l;
                    }
                });
                super.onTakeItem(player, stack);
            }

            private List<ItemStack> getInputStacks() {
                return List.of(SpaceTimeFabricatorScreenHandler.this.inputSlot.getStack(), SpaceTimeFabricatorScreenHandler.this.inputSlot1.getStack());
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addProperty(this.selectedRecipe);
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    public List<FabricationRecipe> getAvailableRecipes() {
        return this.availableRecipes;
    }

    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    public boolean canCraft() {
        return this.inputSlot.hasStack() && !this.availableRecipes.isEmpty();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, WhoCraftedBlocks.SPACE_TIME_FABRICATOR);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (this.isInBounds(id)) {
            this.selectedRecipe.set(id);
            this.populateResult();
        }

        return true;
    }

    private boolean isInBounds(int id) {
        return id >= 0 && id < this.availableRecipes.size();
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        ItemStack itemStack = this.inputSlot.getStack();
        ItemStack itemStack1 = this.inputSlot1.getStack();
        if (!itemStack.isOf(this.inputStack.getItem()) && !itemStack1.isOf(this.inputStack1.getItem())) {
            this.inputStack = itemStack.copy();
            this.inputStack1 = itemStack1.copy();
            this.updateInput(inventory, itemStack, itemStack1);
        }
    }

    private void updateInput(Inventory input, ItemStack stack, ItemStack stack1) {
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
        if (!stack.isEmpty() && !stack1.isEmpty()) {
            //System.out.println(this.world.getRecipeManager().listAllOfType(WhoCraftRecipes.FABRICATION));
            this.availableRecipes = this.world.getRecipeManager().getAllMatches(WhoCraftRecipes.FABRICATION, input, this.world);
        }
    }

    void populateResult() {
        if (!this.availableRecipes.isEmpty() && this.isInBounds(this.selectedRecipe.get())) {
            FabricationRecipe fabricationRecipe = this.availableRecipes.get(this.selectedRecipe.get());
            ItemStack itemStack = fabricationRecipe.craft(this.input, this.world.getRegistryManager());
            if (itemStack.isItemEnabled(this.world.getEnabledFeatures())) {
                this.output.setLastRecipe(fabricationRecipe);
                this.outputSlot.setStackNoCallbacks(itemStack);
            } else {
                this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
            }
        } else {
            this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);
        }

        this.sendContentUpdates();
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return WhoCraftScreens.SPACE_TIME_FABRICATOR;
    }

    public void setContentsChangedListener(Runnable contentsChangedListener) {
        this.contentsChangedListener = contentsChangedListener;
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (slot == 1) {
                item.onCraft(itemStack2, player.getWorld(), player);
                if (!this.insertItem(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot == 0) {
                if (!this.insertItem(itemStack2, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.world.getRecipeManager().getFirstMatch(WhoCraftRecipes.FABRICATION, new SimpleInventory(itemStack2), this.world).isPresent()) {
                if (!this.insertItem(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 2 && slot < 29) {
                if (!this.insertItem(itemStack2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slot >= 29 && slot < 38 && !this.insertItem(itemStack2, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            }

            slot2.markDirty();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
            this.sendContentUpdates();
        }

        return itemStack;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.output.removeStack(1);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }
}
