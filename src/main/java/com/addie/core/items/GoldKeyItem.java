package com.addie.core.items;

import com.lib.client.TooltipItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.util.List;

public class GoldKeyItem extends TooltipItem {

    public GoldKeyItem(Settings settings) {
        super(settings, true);
    }

    @Override
    protected void appendShiftTooltip(ItemStack stack, List<Text> tooltip) {
        tooltip.add(
                Text.translatable("item.whocraft.key.type")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xe2eaee)))
                        .append(Text.literal(" "))
                        .append(
                                Text.translatable("item.whocraft.key.gold")
                                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xe9b115)))
                        )
        );
    }
}
