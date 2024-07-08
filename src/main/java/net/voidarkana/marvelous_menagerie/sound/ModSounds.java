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
    public static final RegistryObject<SoundEvent> ELE_GRUMBLE = registerSoundEvents("elephant_bird_grumble");
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

    public static final RegistryObject<SoundEvent> JOSEPHO_IDLE = registerSoundEvents("josepho_idle");
    public static final RegistryObject<SoundEvent> JOSEPHO_HURT = registerSoundEvents("josepho_hurt");
    public static final RegistryObject<SoundEvent> JOSEPHO_DEATH = registerSoundEvents("josepho_death");

    public static final RegistryObject<SoundEvent> OPHTHALMO_IDLE = registerSoundEvents("ophthalmo_idle");
    public static final RegistryObject<SoundEvent> OPHTHALMO_HURT = registerSoundEvents("ophthalmo_hurt");
    public static final RegistryObject<SoundEvent> OPHTHALMO_DEATH = registerSoundEvents("ophthalmo_death");

    public static final RegistryObject<SoundEvent> LARGE_STEPS = registerSoundEvents("large_footsteps");
    public static final RegistryObject<SoundEvent> CREATURE_FLOPS = registerSoundEvents("creature_flops");
    public static final RegistryObject<SoundEvent> CREATURE_SWIM = registerSoundEvents("creature_swim");

    public static final RegistryObject<SoundEvent> ARTHROPOD_HURT = registerSoundEvents("arthropod_hurt");
    public static final RegistryObject<SoundEvent> ARTHROPOD_DEATH = registerSoundEvents("arthropod_death");

    public static final RegistryObject<SoundEvent> PET_ARMOR_BREAK = registerSoundEvents("caris_armor_break");
    public static final RegistryObject<SoundEvent> PET_ARMOR_CRACK = registerSoundEvents("caris_armor_crack");
    public static final RegistryObject<SoundEvent> PET_ARMOR_DAMAGE = registerSoundEvents("caris_armor_damage");
    public static final RegistryObject<SoundEvent> PET_ARMOR_REPAIR = registerSoundEvents("caris_armor_repair");
    public static final RegistryObject<SoundEvent> PET_ARMOR_EQUIP = registerSoundEvents("caris_armor_equip");
    public static final RegistryObject<SoundEvent> PET_ARMOR_UNEQUIP = registerSoundEvents("caris_armor_unequip");

    public static final RegistryObject<SoundEvent> EGG_ARMOR = registerSoundEvents("egg_armor");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MarvelousMenagerie.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }

}
