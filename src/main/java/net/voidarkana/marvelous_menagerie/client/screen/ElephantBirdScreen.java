package net.voidarkana.marvelous_menagerie.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ElephantBirdEntity;

public class ElephantBirdScreen extends AbstractContainerScreen<ElephantBirdMenu> {
    private static final ResourceLocation HORSE_INVENTORY_LOCATION =
            new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/gui/container/elephant_bird.png");
    private final ElephantBirdEntity horse;
    private float xMouse;
    private float yMouse;

    public ElephantBirdScreen(ElephantBirdMenu pMenu, Inventory pPlayerInventory, ElephantBirdEntity pHorse) {
        super(pMenu, pPlayerInventory, pHorse.getDisplayName());
        this.horse = pHorse;
        this.imageWidth = 212;
        this.inventoryLabelX = 24;
    }

    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(HORSE_INVENTORY_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        InventoryScreen.renderEntityInInventoryFollowsMouse(pGuiGraphics, i + 106, j+72, 12, (float)(i + 106) - this.xMouse, (float)(j + 72) - this.yMouse, this.horse);
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        this.xMouse = (float)pMouseX;
        this.yMouse = (float)pMouseY;
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
