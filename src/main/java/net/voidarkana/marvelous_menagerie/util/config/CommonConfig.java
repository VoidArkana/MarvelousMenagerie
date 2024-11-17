package net.voidarkana.marvelous_menagerie.util.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BONEMEAL_PROTO;

    public static final ForgeConfigSpec.ConfigValue<Boolean> HALLU_FLASH;

    static {
        BUILDER.push("Configs for Marvelous Menagerie");

        BONEMEAL_PROTO = BUILDER.comment("Defines if Prototaxites can be bonemealed, turned on by default, if playing on NeoForge should set it to false as it causes crashes")
                .define("Can be bonemealed?", true);

        HALLU_FLASH = BUILDER.comment("Defines if the Hallucinating effect uses a shader that causes flashing lights. " +
                        "If this setting is set to \"false\" the effect will use a gentler shader instead.")
                .define("Flashy hallucinations?", true);

        BUILDER.pop();
        SPEC= BUILDER.build();
    }
}
