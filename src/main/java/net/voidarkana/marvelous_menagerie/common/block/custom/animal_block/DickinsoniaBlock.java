package net.voidarkana.marvelous_menagerie.common.block.custom.animal_block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DickinsoniaBlock extends DirectionalWaterCreatureBlock{
    public DickinsoniaBlock(Properties pProperties) {
        super(pProperties);
    }

    protected static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 2, 15);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
