package net.voidarkana.marvelous_menagerie.worldgen.tree.custom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.voidarkana.marvelous_menagerie.worldgen.tree.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;

public class HugeSigillariaTrunkPlacer extends TrunkPlacer {

    public static final Codec<HugeSigillariaTrunkPlacer> CODEC = RecordCodecBuilder.create(sigillariaTrunkPlacerInstance ->
            trunkPlacerParts(sigillariaTrunkPlacerInstance).apply(sigillariaTrunkPlacerInstance, HugeSigillariaTrunkPlacer::new));

    public HugeSigillariaTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.HUGE_SIGILLARIA_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos,
            BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {

        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();

        BlockPos blockpos = pPos.below();
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos, pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.east(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south().east(), pConfig);

        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.east().east(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south().south(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south().south().east(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south().east().east(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, blockpos.south().south().east().east(), pConfig);

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        int pointX=0;
        int pointZ=0;

        if (pRandom.nextBoolean()) {
            pointZ = 1;
        }
        else {
            pointX = 1;
        }


        for(int size = 0; size < pFreeTreeHeight; ++size) {

            if (size < pFreeTreeHeight-6){
                this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 0);
                if (size < pFreeTreeHeight - 7) {

                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, 0);
                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 1);
                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, 0);
                    this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -1);

                    if (size < pFreeTreeHeight - ((pFreeTreeHeight/3)*2)){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, 1);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, -1);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, -1);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, 1);
                    }

                    if (size == 0 || size == 1){
                        if (size == 0){
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 2, size, 1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 2, size, -1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 3, size, 1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 3, size, -1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 4, size, 0);

                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -2, size, 1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -2, size, -1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -3, size, 1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -3, size, -1);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -4, size, 0);

                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, 2);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, 2);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, 3);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, 3);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 4);

                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, -2);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, -2);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, -3);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, -3);
                            this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -4);
                        }

                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 2, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -2, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 3, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -3, size, 0);

                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 2);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -2);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 3);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -3);
                    }
                }
            }else {

                if (pointX > 0){

                    if (size > pFreeTreeHeight-5){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 4, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -4, size, 0);
                    }

                    if (size == pFreeTreeHeight-5){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 3, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -3, size, 0);
                    }

                    if (size == pFreeTreeHeight-6){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 1, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -1, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 2, size, 0);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, -2, size, 0);
                    }

                    if (size == pFreeTreeHeight-1){
                        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pPos.getX()+4, pPos.getY()+pFreeTreeHeight+1, pPos.getZ()), 1, false));
                        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pPos.getX()-4, pPos.getY()+pFreeTreeHeight+1, pPos.getZ()), 1, false));
                    }

                }else{

                    if (size > pFreeTreeHeight-5){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 4);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -4);
                    }

                    if (size == pFreeTreeHeight-5){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 3);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -3);
                    }

                    if (size == pFreeTreeHeight-6){
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 1);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -1);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, 2);
                        this.placeLogIfFreeWithOffset(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos, pConfig, pPos, 0, size, -2);
                    }

                    if (size == pFreeTreeHeight-1){
                        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pPos.getX(), pPos.getY()+pFreeTreeHeight+1, pPos.getZ()+4), 1, false));
                        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(pPos.getX(), pPos.getY()+pFreeTreeHeight+1, pPos.getZ()-4), 1, false));
                    }

                }


            }

        }


        return list;
    }

    private void placeLogIfFreeWithOffset(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, BlockPos.MutableBlockPos pPos, TreeConfiguration pConfig, BlockPos pOffsetPos, int pOffsetX, int pOffsetY, int pOffsetZ) {
        pPos.setWithOffset(pOffsetPos, pOffsetX, pOffsetY, pOffsetZ);
        this.placeLogIfFree(pLevel, pBlockSetter, pRandom, pPos, pConfig);
    }
}
