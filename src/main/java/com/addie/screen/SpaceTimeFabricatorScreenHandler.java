package com.addie.screen;

import com.addie.core.WhoCraftBlocks;
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
    private long lastTakeTime;

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
                SpaceTimeFabricatorScreenHandler.this.output.unlockLastRecipe(player, getInputStacks());

                SpaceTimeFabricatorScreenHandler.this.inputSlot.takeStack(1);
                SpaceTimeFabricatorScreenHandler.this.inputSlot1.takeStack(1);
                SpaceTimeFabricatorScreenHandler.this.populateResult();

                context.run((world, pos) -> {
                    long time = world.getTime();
                    if (SpaceTimeFabricatorScreenHandler.this.lastTakeTime != time) {
                        world.playSound(null, pos, SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        SpaceTimeFabricatorScreenHandler.this.lastTakeTime = time;
                    }
                });

                super.onTakeItem(player, stack);
            }

            private List<ItemStack> getInputStacks() {
                return List.of(
                        SpaceTimeFabricatorScreenHandler.this.inputSlot.getStack(),
                        SpaceTimeFabricatorScreenHandler.this.inputSlot1.getStack()
                );
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
        return !this.availableRecipes.isEmpty();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, WhoCraftBlocks.SPACE_TIME_FABRICATOR);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id >= 0 && id < this.availableRecipes.size()) {
            this.selectedRecipe.set(id);
            this.populateResult();
        }
        return true;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        ItemStack stack0 = this.inputSlot.getStack();
        ItemStack stack1 = this.inputSlot1.getStack();

        if (!ItemStack.areEqual(stack0, this.inputStack)
                || !ItemStack.areEqual(stack1, this.inputStack1)) {

            this.inputStack = stack0.copy();
            this.inputStack1 = stack1.copy();
            this.updateInput(inventory, stack0, stack1);
        }
    }

    private void updateInput(Inventory inventory, ItemStack stack0, ItemStack stack1) {
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.setStackNoCallbacks(ItemStack.EMPTY);

        if (!stack0.isEmpty() || !stack1.isEmpty()) {
            this.availableRecipes = this.world
                    .getRecipeManager()
                    .getAllMatches(WhoCraftRecipes.FABRICATION, inventory, this.world);
        }

        this.sendContentUpdates();
    }

    void populateResult() {
        if (!this.availableRecipes.isEmpty() && this.selectedRecipe.get() >= 0 && this.selectedRecipe.get() < this.availableRecipes.size()) {
            FabricationRecipe recipe = this.availableRecipes.get(this.selectedRecipe.get());
            ItemStack result = recipe.craft(this.input, this.world.getRegistryManager());

            if (result.isItemEnabled(this.world.getEnabledFeatures())) {
                this.output.setLastRecipe(recipe);
                this.outputSlot.setStackNoCallbacks(result);
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
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot != null && slot.hasStack()) {
            ItemStack stack = slot.getStack();
            result = stack.copy();
            Item item = stack.getItem();

            if (slotIndex == OUTPUT_ID) {
                item.onCraft(stack, player.getWorld(), player);
                if (!this.insertItem(stack, INVENTORY_START, OUTPUT_END, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(stack, result);
            } else if (slotIndex == INPUT_ID || slotIndex == INPUT_ID + 1) {
                if (!this.insertItem(stack, INVENTORY_START, OUTPUT_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.world.getRecipeManager()
                    .getFirstMatch(WhoCraftRecipes.FABRICATION, new SimpleInventory(stack), this.world)
                    .isPresent()) {

                if (!this.insertItem(stack, INPUT_ID, INPUT_ID + 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= INVENTORY_START && slotIndex < INVENTORY_END) {
                if (!this.insertItem(stack, OUTPUT_START, OUTPUT_END, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotIndex >= OUTPUT_START && slotIndex < OUTPUT_END) {
                if (!this.insertItem(stack, INVENTORY_START, INVENTORY_END, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }

            slot.markDirty();
            slot.onTakeItem(player, stack);
            this.sendContentUpdates();
        }

        return result;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.output.removeStack(1);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }
}
