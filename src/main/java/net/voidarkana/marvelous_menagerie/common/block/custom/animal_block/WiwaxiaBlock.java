package net.voidarkana.marvelous_menagerie.common.block.custom.animal_block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WiwaxiaBlock extends DirectionalWaterCreatureBlock{

    public WiwaxiaBlock(Properties pProperties) {
        super(pProperties);
    }

    protected static final VoxelShape NORTHSOUTH = Block.box(5, 0, 4, 11, 4, 12);
    protected static final VoxelShape EASTWEST = Block.box(4, 0, 5, 12, 4, 11);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(FACING) == Direction.NORTH || pState.getValue(FACING) == Direction.SOUTH){
            return NORTHSOUTH;
        }else {
            return EASTWEST;
        }
    }
}
