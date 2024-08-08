package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;

import java.util.Optional;

public class PrototaxitesBlock extends Block implements IPlantable, BonemealableBlock {

    public static final BooleanProperty CAN_GROW = BooleanProperty.create("can_grow"); //0 grow, 1 stop growing
    public static final IntegerProperty PROTO_THICKNESS = IntegerProperty.create("proto_thickness", 0, 4);
    public static final IntegerProperty PROTO_AGE = IntegerProperty.create("proto_age", 0, 15);

    private static final VoxelShape TIP_SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 14.0D, 12.0D);
    private static final VoxelShape FRUSTUM_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    private static final VoxelShape MIDDLE_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private static final VoxelShape BASE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public PrototaxitesBlock(Properties pProperties, ResourceKey<ConfiguredFeature<?, ?>> pFeature) {
        super(pProperties);
        this.feature = pFeature;
        this.registerDefaultState(this.stateDefinition.any().setValue(CAN_GROW, true).setValue(PROTO_THICKNESS, 0).setValue(PROTO_AGE, 0));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(CAN_GROW, PROTO_THICKNESS, PROTO_AGE);
    }

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.scheduleTick(pPos, this, 1);
        }

        int protoThickness = calculatePrototaxitesThickness(pLevel, pPos);

        return pState.setValue(PROTO_THICKNESS, protoThickness);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        int protoThickness = pState.getValue(PROTO_THICKNESS);
        VoxelShape voxelshape;

        switch(protoThickness){
            case 1 -> voxelshape = FRUSTUM_SHAPE;
            case 2 -> voxelshape = MIDDLE_SHAPE;
            case 3 -> voxelshape = BASE_SHAPE;
            default -> voxelshape = TIP_SHAPE;
        }

        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return voxelshape.move(vec3.x, 0.0D, vec3.z);
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getValue(CAN_GROW) && pState.getValue(PROTO_THICKNESS)==0;
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!this.canSurvive(pState, pLevel, pPos)) {
            pLevel.destroyBlock(pPos, true);
        }
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {


        if (getHeightBelowUpToMax(pLevel, pPos)==3 && pState.getValue(CAN_GROW) && pLevel.isEmptyBlock(pPos.above())){
            pState.setValue(CAN_GROW, false);
        } else if (getHeightBelowUpToMax(pLevel, pPos)<3 && !pState.getValue(CAN_GROW) && pLevel.isEmptyBlock(pPos.above())) {
            pState.setValue(CAN_GROW, true);
        }
        
        if (pState.getValue(CAN_GROW) && pLevel.isEmptyBlock(pPos.above())) {


            int i;
            for(i = 1; pLevel.getBlockState(pPos.below(i)).is(ModBlocks.PROTOTAXITES.get()); ++i) {
            }

            if (i < 4) {
                int j = pState.getValue(PROTO_AGE);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pLevel, pPos, pState, true)) {
                    if (j == 15){
                        boolean k = (i <= 2 || !(pRandom.nextFloat() < 0.25F)) && i <4;

                        pLevel.setBlock(pPos.above(), ModBlocks.PROTOTAXITES.get().defaultBlockState().setValue(CAN_GROW, k), 3);
                        net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pLevel, pPos.above(), ModBlocks.PROTOTAXITES.get().defaultBlockState());
                        this.defaultBlockState().setValue(PROTO_AGE,0);

                    }
                    else {
                        pLevel.setBlock(pPos, pState.setValue(PROTO_AGE, Integer.valueOf(j + 1)), 3);
                    }
                }
            }
        }

    }

    protected int getHeightBelowUpToMax(BlockGetter pLevel, BlockPos pPos) {
        int i;
        for(i = 0; i < 4 && pLevel.getBlockState(pPos.below(i + 1)).is(ModBlocks.PROTOTAXITES.get()); ++i) {
        }
        return i;
    }

    private int calculatePrototaxitesThickness(LevelReader pLevel, BlockPos pPos) {

        BlockPos above1 = pPos.above();
        BlockPos above2 = above1.above();
        BlockPos above3 = above2.above();

        if (pLevel.getBlockState(above1).is(ModBlocks.PROTOTAXITES.get())){
            if (pLevel.getBlockState(above2).is(ModBlocks.PROTOTAXITES.get())){
                if (pLevel.getBlockState(above3).is(ModBlocks.PROTOTAXITES.get())){
                    return 3;
                }
                else {
                    return 2;
                }
            }
            else {
                return 1;
            }
        }
        else {
            return 0;
        }
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {

        int size = this.getHeightBelowUpToMax(pLevel, pPos) + 1;

        BlockState soil = pLevel.getBlockState(pPos.below());

        if (soil.canSustainPlant(pLevel, pPos.below(), Direction.UP, this)) {
            return true;
        }

        BlockState blockstate = pLevel.getBlockState(pPos.below());

        if (blockstate.is(this) && size <= 4) {
            return true;
        } else return blockstate.is(BlockTags.DIRT);

    }

    public boolean growMushroom(ServerLevel pLevel, BlockPos pPos, BlockState pState, RandomSource pRandom) {
        Optional<? extends Holder<ConfiguredFeature<?, ?>>> optional = pLevel.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(this.feature);
        if (optional.isEmpty()) {
            return false;
        } else {
            var event = net.minecraftforge.event.ForgeEventFactory.blockGrowFeature(pLevel, pRandom, pPos, optional.get());
            if (event.getResult().equals(net.minecraftforge.eventbus.api.Event.Result.DENY)) return false;
            pLevel.removeBlock(pPos, false);
            if (event.getFeature().value().place(pLevel, pLevel.getChunkSource().getGenerator(), pRandom, pPos)) {
                return true;
            } else {
                pLevel.setBlock(pPos, pState, 3);
                return false;
            }
        }
    }

    @Override
    public PlantType getPlantType(BlockGetter world, BlockPos pos) {
        return PlantType.PLAINS;
    }

    @Override
    public BlockState getPlant(BlockGetter level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() != this) return defaultBlockState();
        return state;
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        BlockState soil = pLevel.getBlockState(pPos.below());
        return !soil.is(this) && CommonConfig.BONEMEAL_PROTO.get();
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return (double)pRandom.nextFloat() < 0.4D;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        this.growMushroom(pLevel, pPos, pState, pRandom);
    }
}
