package net.voidarkana.marvelous_menagerie.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<SoundEvent> DODO_IDLE = registerSoundEvents("dodo_idle");
    public static final RegistryObject<SoundEvent> DODO_HURT = registerSoundEvents("dodo_hurt");
    public static final RegistryObject<SoundEvent> DODO_DEATH = registerSoundEvents("dodo_death");

    public static final RegistryObject<SoundEvent> ELE_IDLE = registerSoundEvents("elephant_bird_idle");
    public static final RegistryObject<SoundEvent> ELE_HURT = registerSoundEvents("elephant_bird_hurt");
    public static final RegistryObject<SoundEvent> ELE_DEATH = registerSoundEvents("elephant_bird_death");

    public static final RegistryObject<SoundEvent> BABY_ELE_IDLE = registerSoundEvents("baby_elephant_bird_idle");
    public static final RegistryObject<SoundEvent> BABY_ELE_HURT = registerSoundEvents("baby_elephant_bird_hurt");
    public static final RegistryObject<SoundEvent> BABY_ELE_DEATH = registerSoundEvents("baby_elephant_bird_death");

    public static final RegistryObject<SoundEvent> STELLER_IDLE = registerSoundEvents("steller_idle");
    public static final RegistryObject<SoundEvent> DOLPHIN_BLOWHOLE = registerSoundEvents("dolphin_blowhole");
    public static final RegistryObject<SoundEvent> STELLER_HURT = registerSoundEvents("steller_hurt");
    public static final RegistryObject<SoundEvent> STELLER_LAND_HURT = registerSoundEvents("steller_land_hurt");
    public static final RegistryObject<SoundEvent> STELLER_DEATH = registerSoundEvents("steller_death");

    public static final RegistryObject<SoundEvent> THYLACINE_IDLE = registerSoundEvents("thylacine_idle");
    public static final RegistryObject<SoundEvent> THYLACINE_HURT = registerSoundEvents("thylacine_hurt");
    public static final RegistryObject<SoundEvent> THYLACINE_ALERT = registerSoundEvents("thylacine_alert");
    public static final RegistryObject<SoundEvent> THYLACINE_DEATH = registerSoundEvents("thylacine_death");
    public static final RegistryObject<SoundEvent> THYLACINE_YAWN = registerSoundEvents("thylacine_yawn");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MarvelousMenagerie.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }

}
