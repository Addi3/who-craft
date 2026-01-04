package com.addie.core.items;

import com.lib.client.TooltipItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.util.List;

public class NetheriteKeyItem extends TooltipItem {

    public NetheriteKeyItem(Settings settings) {
        super(settings, true);
    }

    @Override
    protected void appendShiftTooltip(ItemStack stack, List<Text> tooltip) {
        tooltip.add(
                Text.translatable("item.whocraft.key.type")
                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xe2eaee)))
                        .append(Text.literal(" "))
                        .append(
                                Text.translatable("item.whocraft.key.netherite")
                                        .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0x3c3232)))
                        )
        );
    }
}
