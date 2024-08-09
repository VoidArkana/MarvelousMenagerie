package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.common.entity.custom.*;

public class MarvelousEntityPlacement {
    public  static void entityPlacement() {
        SpawnPlacements.register(ModEntities.DODO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DodoEntity::checkSurfaceDinoSpawnRules);
        SpawnPlacements.register(ModEntities.THYLACINE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ThylacineEntity::checkSurfaceDinoSpawnRules);
        SpawnPlacements.register(ModEntities.ELEPHANT_BIRD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ElephantBirdEntity::checkSurfaceDinoSpawnRules);
        SpawnPlacements.register(ModEntities.JOSEPHO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JosephoEntity::checkSurfaceDinoSpawnRules);

        SpawnPlacements.register(ModEntities.OPHTHALMO.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OphthalmoEntity::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(ModEntities.STELLER_SEA_COW.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StellerEntity::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(ModEntities.ANOMALOCARIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AnomalocarisEntity::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(ModEntities.TRILOBITE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TrilobiteEntity::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(ModEntities.SACABAMBASPIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SacabambaspisEntity::checkSurfaceWaterDinoSpawnRules);
    }
}
