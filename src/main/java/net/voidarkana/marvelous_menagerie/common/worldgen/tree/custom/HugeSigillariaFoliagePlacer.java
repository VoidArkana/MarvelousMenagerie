package net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModFoliagePlacers;

import java.util.Random;

public class HugeSigillariaFoliagePlacer extends FoliagePlacer {

    public static final Codec<HugeSigillariaFoliagePlacer> CODEC = RecordCodecBuilder.create(sigillariaFoliagePlacerInstance ->
            foliagePlacerParts(sigillariaFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(sigillariaFoliagePlacerInstance, HugeSigillariaFoliagePlacer::new));

    private final int height;

    public HugeSigillariaFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.HUGE_SIGILLARIA_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
                                 int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {

        //represents the radius
        int currentRadius = 0;

        // currentYpos = height + offset (height + 1)
        // stops when currentYpos reaches the bottom of the foliage height
        //goes from the top down
        for(int currentYpos = pOffset; currentYpos >= pOffset - pFoliageHeight; --currentYpos) {

            //places the row of leaves
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos(), currentRadius, currentYpos, pAttachment.doubleTrunk());

            if (currentRadius < 2) {
                ++currentRadius;
            }else {
                --currentRadius;
            }
        }
    }

    public int foliageRadius(Random pRandom, int pRadius) {
        return super.foliageRadius((RandomSource) pRandom, pRadius) + pRandom.nextInt(Math.max(pRadius + 1, 1));
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return pLocalX == pRange && pLocalZ == pRange && pRange > 0;
    }
}
