package net.voidarkana.marvelous_menagerie.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.voidarkana.marvelous_menagerie.common.worldgen.util.HugePrototaxitesFeatureConfiguration;

public class HugePrototaxitesFeature extends Feature<HugePrototaxitesFeatureConfiguration> {

    public HugePrototaxitesFeature(Codec<HugePrototaxitesFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    protected int getProtoHeight(RandomSource pRandom) {
        return pRandom.nextInt(6, 10) + 2;
    }

    protected boolean isValidPosition(LevelAccessor pLevel, BlockPos pPos, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos, HugePrototaxitesFeatureConfiguration pConfig) {
        int i = pPos.getY();
        if (i >= pLevel.getMinBuildHeight() + 1 && i + pMaxHeight + 1 < pLevel.getMaxBuildHeight()) {

                return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean place(FeaturePlaceContext<HugePrototaxitesFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        RandomSource randomsource = pContext.random();
        HugePrototaxitesFeatureConfiguration hugePrototaxitesFeatureConfiguration = pContext.config();
        int i = this.getProtoHeight(randomsource);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        if (!this.isValidPosition(worldgenlevel, blockpos, i, blockpos$mutableblockpos, hugePrototaxitesFeatureConfiguration)) {
            return false;
        } else {
            this.placeStem(worldgenlevel, randomsource, blockpos, hugePrototaxitesFeatureConfiguration, i, blockpos$mutableblockpos, pContext);
            return true;
        }
    }

    protected void placeStem(LevelAccessor pLevel, RandomSource pRandom, BlockPos pPos, HugePrototaxitesFeatureConfiguration pConfig, int pMaxHeight, BlockPos.MutableBlockPos pMutablePos, FeaturePlaceContext<HugePrototaxitesFeatureConfiguration> pContext) {

        int radius = 1;

        for(int currentHeight = 1; currentHeight <= pMaxHeight; ++currentHeight) {

            for(int j = -radius; j <= radius; ++j) {
                for(int k = -radius; k <= radius; ++k) {
                    boolean flag = j == -radius;
                    boolean flag1 = j == radius;
                    boolean flag2 = k == -radius;
                    boolean flag3 = k == radius;
                    boolean flag4 = flag || flag1;
                    boolean flag5 = flag2 || flag3;
                    boolean flag6 = j == 0 && k == 0;
                    if (((!flag4 || !flag5 && currentHeight > pMaxHeight-6 ) || currentHeight <= pMaxHeight-6) && currentHeight < pMaxHeight) {
                        pMutablePos.setWithOffset(pPos, j, currentHeight-1, k);
                        if (!pLevel.getBlockState(pMutablePos).isSolidRender(pLevel, pMutablePos)) {
                            BlockState blockstate = pConfig.stemProvider.getState(pRandom, pPos);
                            this.setBlock(pLevel, pMutablePos, blockstate);

                            if (currentHeight==1 && flag6){
                                this.replaceGround(pContext, pPos);
                            }
                        }
                    }
                    else {
                        pMutablePos.setWithOffset(pPos, 0, currentHeight-1, 0);
                        BlockState blockstate = pConfig.stemProvider.getState(pRandom, pPos);
                        this.setBlock(pLevel, pMutablePos, blockstate);
                    }
                }
            }

        }
    }


    public void replaceGround(FeaturePlaceContext<HugePrototaxitesFeatureConfiguration> pContext, BlockPos pos) {

        this.placeCircle(pContext, pos.west().north());
        this.placeCircle(pContext, pos.east(2).north());
        this.placeCircle(pContext, pos.west().south(2));
        this.placeCircle(pContext, pos.east(2).south(2));

        for(int j = 0; j < 5; ++j) {
            int k = pContext.random().nextInt(64);
            int l = k % 8;
            int i1 = k / 8;
            if (l == 0 || l == 7 || i1 == 0 || i1 == 7) {
                this.placeCircle(pContext, pos.offset(-3 + l, -1, -3 + i1));
            }
        }

    }

    private void placeCircle(FeaturePlaceContext<HugePrototaxitesFeatureConfiguration> pContext, BlockPos pPos) {
        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                if (Math.abs(i) != 2 || Math.abs(j) != 2) {
                    this.placeMyceliumAt(pContext, pPos.offset(i, 0, j));
                }
            }
        }

    }

    private void placeMyceliumAt(FeaturePlaceContext<HugePrototaxitesFeatureConfiguration> pContext, BlockPos pPos) {

        BlockStateProvider provider = BlockStateProvider.simple(Blocks.MYCELIUM);

        for(int i = 2; i >= -3; --i) {
            BlockPos blockpos = pPos.above(i);
            if (Feature.isGrassOrDirt(pContext.level(), blockpos)) {
                this.setBlock(pContext.level(), blockpos, net.minecraftforge.event.ForgeEventFactory.alterGround(pContext.level(), pContext.random(), blockpos, provider.getState(pContext.random(), pPos)));
                break;
            }

            if (!(pContext.level().isStateAtPosition(pPos, BlockBehaviour.BlockStateBase::isAir)) && i < 0) {
                break;
            }
        }

    }

}
