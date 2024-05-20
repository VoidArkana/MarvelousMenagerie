package net.voidarkana.marvelous_menagerie.worldgen.tree.custom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.voidarkana.marvelous_menagerie.worldgen.tree.ModTrunkPlacerTypes;

public class SigillariaTrunkPlacer extends TrunkPlacer {

    public static final Codec<SigillariaTrunkPlacer> CODEC = RecordCodecBuilder.create(sigillariaTrunkPlacerInstance ->
            trunkPlacerParts(sigillariaTrunkPlacerInstance).apply(sigillariaTrunkPlacerInstance, SigillariaTrunkPlacer::new));

    public SigillariaTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.SIGILLARIA_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader levelReader,
            BiConsumer<BlockPos, BlockState> blockConsumer, RandomSource random, int trunkLength,
            BlockPos startingPosition, TreeConfiguration treeConfig) {

        setDirtAt(levelReader, blockConsumer, random, startingPosition.below(), treeConfig);

        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        int pointX=0;
        int pointY=0;
        int coinToss = random.nextInt(2);

        while (pointX == 0 && pointY == 0) {

            if (coinToss == 0) {
                pointY = random.nextInt(2)-1;
            }
            else {
                pointX = random.nextInt(2)-1;
            }

        }

        int timesItOffsets = 2;
        int offsetStored = timesItOffsets;


        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();


        int currentXposition = startingPosition.getX();
        int currentZposition = startingPosition.getZ();


        OptionalInt optionalint = OptionalInt.empty();


        for(int i1 = 0; i1 < trunkLength; ++i1) {

            int currentYposition = startingPosition.getY() + i1; //goes adding up the height

            //when adding the starting point, it also adds 4 logs at the base that surround it
            if (i1 == 0)
            {
                if (placeLog(levelReader, blockConsumer, random, blockpos$mutableblockpos.set(startingPosition.getX()+1, currentYposition, currentZposition),
                        treeConfig, (blockState) -> blockState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))) {
                    optionalint = OptionalInt.of(currentYposition + 1);
                }
                if (placeLog(levelReader, blockConsumer, random, blockpos$mutableblockpos.set(startingPosition.getX()-1, currentYposition, currentZposition),
                        treeConfig, (blockState) -> blockState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))) {
                    optionalint = OptionalInt.of(currentYposition + 1);
                }
                if (placeLog(levelReader, blockConsumer, random, blockpos$mutableblockpos.set(currentXposition, currentYposition, startingPosition.getZ()+1),
                        treeConfig, (blockState) -> blockState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))) {
                    optionalint = OptionalInt.of(currentYposition + 1);
                }
                if (placeLog(levelReader, blockConsumer, random, blockpos$mutableblockpos.set(currentXposition, currentYposition, startingPosition.getZ()-1),
                        treeConfig, (blockState) -> blockState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))) {
                    optionalint = OptionalInt.of(currentYposition + 1);
                }
            }

            //goes setting the log in a different direction
            if (i1 >= trunkLength-4 && timesItOffsets > 0) {
                currentXposition += pointX;
                currentZposition += pointY;
                --timesItOffsets;
            }


            if (placeLog(levelReader, blockConsumer, random, blockpos$mutableblockpos.set(currentXposition, currentYposition, currentZposition), treeConfig)) {
                optionalint = OptionalInt.of(currentYposition + 1); //sets where the leaves would be in the current position
            }
        }


        //places the leaves on top of the main branch
        if (optionalint.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(currentXposition, optionalint.getAsInt(), currentZposition), 1, false));
        }





        currentXposition = startingPosition.getX();
        currentZposition = startingPosition.getZ();
        timesItOffsets = offsetStored;
        optionalint = OptionalInt.empty();

        //repeats the branching process in mirror form
        for(int i1 = 0; i1 < trunkLength; ++i1) {

            int currentYposition = startingPosition.getY() + i1; //goes adding up the height

            //goes setting the log in a different direction
            if (i1 >= trunkLength-4 && timesItOffsets > 0) {
                currentXposition -= pointX;
                currentZposition -= pointY;
                --timesItOffsets;
            }

            if (placeLog(levelReader, blockConsumer, random, blockpos$mutableblockpos.set(currentXposition, currentYposition, currentZposition), treeConfig)) {
                optionalint = OptionalInt.of(currentYposition + 1); //sets where the leaves would be in the current position
            }
        }


        //places the leaves on top of the main branch
        if (optionalint.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(currentXposition, optionalint.getAsInt(), currentZposition), 1, false));
        }

        return list;
    }

}