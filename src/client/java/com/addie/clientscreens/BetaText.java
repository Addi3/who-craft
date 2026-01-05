package com.addie.clientscreens;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class BetaText {

    private static final boolean IS_BETA = isBetaBuild();
    private static final float SCALE = 0.75f;

    private BetaText() {}

    public static void register() {
        if (!IS_BETA) return;
        HudRenderCallback.EVENT.register(BetaText::render);
    }

    private static void render(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.debugEnabled) return;

        Text text = Text.translatable("text.whocraft.betatext");

        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(SCALE, SCALE, 1.0f);

        int x = (int) (5 / SCALE);
        int y = (int) (5 / SCALE);

        drawContext.drawText(
                client.textRenderer,
                text,
                x,
                y,
                0xAF4F4F,
                false
        );

        drawContext.getMatrices().pop();
    }

    private static boolean isBetaBuild() {
        return FabricLoader.getInstance()
                .getModContainer("who-craft")
                .map(container -> {
                    String version = container.getMetadata()
                            .getVersion()
                            .getFriendlyString();
                    return version.contains("beta");
                })
                .orElse(false);
    }
}
