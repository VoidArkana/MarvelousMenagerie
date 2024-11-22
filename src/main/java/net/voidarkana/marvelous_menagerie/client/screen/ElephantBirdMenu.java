package net.voidarkana.marvelous_menagerie.client.screen;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ElephantBirdEntity;

public class ElephantBirdMenu extends AbstractContainerMenu {
    private final Container horseContainer;
    private final ElephantBirdEntity horse;


    public ElephantBirdMenu(int pContainerId, Inventory pPlayerInventory, Container pContainer, final ElephantBirdEntity pHorse) {
        super(ModMenuTypes.ELEPHANT_BIRD_CONTAINER.get(), pContainerId);
        this.horseContainer = pContainer;
        this.horse = pHorse;
        int i = 3;
        pContainer.startOpen(pPlayerInventory.player);
        int j = -18;

        for(int r = 0; r < 3; ++r) { //rows
            for(int c = 0; c < 4; ++c) { //columns
                this.addSlot(new Slot(pContainer, c + (4*r), 8 + c * 18, 18 + r * 18));
            }
        }

        for(int r = 0; r < 3; ++r) {
            for(int c = 0; c < 4; ++c) {
                this.addSlot(new Slot(pContainer, c + (4*r) + 12, 134 + c * 18, 18 + r * 18));
            }
        }


        for(int i1 = 0; i1 < 3; ++i1) {
            for(int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(pPlayerInventory, k1 + i1 * 9 + 9, 26 + k1 * 18, 102 + i1 * 18 + -18));
            }
        }

        for(int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(pPlayerInventory, j1, 26 + j1 * 18, 142));
        }

    }

    public ElephantBirdMenu(int i, Inventory inventory) {
        this(i, inventory, new SimpleContainer(24), null);
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean stillValid(Player pPlayer) {
        return !this.horse.hasInventoryChanged(this.horseContainer) && this.horseContainer.stillValid(pPlayer) && this.horse.isAlive() && this.horse.distanceTo(pPlayer) < 8.0F;
    }

    private boolean hasChest(ElephantBirdEntity pHorse) {
        return pHorse.hasChest();
    }

    /**
     * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
     * inventory and the other inventory(s).
     */
    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        ItemStack itemStack;
        Slot slot = this.slots.get(i);
        ItemStack itemStack2 = slot.getItem();
        itemStack = itemStack2.copy();
        if (slot.hasItem()) {
            if (i > 11 && !this.moveItemStackTo(itemStack2, 0, 24, false)) {
                return ItemStack.EMPTY;
            } else if (!this.moveItemStackTo(itemStack2, 24, 60, true)) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }
            slot.setChanged();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemStack2);
            this.broadcastChanges();
        }
        return itemStack;
    }

    /**
     * Called when the container is closed.
     */
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
        this.horseContainer.stopOpen(pPlayer);
    }
}
