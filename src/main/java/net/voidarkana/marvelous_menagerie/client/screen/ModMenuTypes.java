package net.voidarkana.marvelous_menagerie.client.screen;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<MenuType<ElephantBirdMenu>> ELEPHANT_BIRD_CONTAINER = MENUS.register("elephant_bird_container",
            () -> new MenuType<>(ElephantBirdMenu::new, FeatureFlags.REGISTRY.allFlags()));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
