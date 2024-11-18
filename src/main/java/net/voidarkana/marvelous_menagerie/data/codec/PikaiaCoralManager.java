package net.voidarkana.marvelous_menagerie.data.codec;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;

public class PikaiaCoralManager extends SimpleJsonResourceReloadListener {


    public static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
    public static final List<PikaiaCoralData> DATA = Lists.newArrayList();

    public PikaiaCoralManager() {
        super(GSON_INSTANCE, "gameplay/pikaia_revive_coral");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        object.forEach((resourceLocation, jsonElement) -> {
            PikaiaCoralData gooberGooData = PikaiaCoralData.CODEC.parse(JsonOps.INSTANCE, jsonElement).result().orElseThrow();
            DATA.add(gooberGooData);
        });
    }

    public record PikaiaCoralData(Block input, Block output) {
        public static final Codec<PikaiaCoralData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        BuiltInRegistries.BLOCK.byNameCodec().fieldOf("input").forGetter(PikaiaCoralManager.PikaiaCoralData::input),
                        BuiltInRegistries.BLOCK.byNameCodec().fieldOf("output").forGetter(PikaiaCoralManager.PikaiaCoralData::output)
                ).apply(instance, PikaiaCoralManager.PikaiaCoralData::new));
    }
}
