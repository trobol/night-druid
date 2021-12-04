package net.trobol.nightdruid.common.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.RarityFilterPlacementModifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.trobol.nightdruid.common.NightDruid;
import net.trobol.nightdruid.mixin.SimpleBlockStateProviderAccessor;

public class NDWorldGenerators {
    public static final ConfiguredFeature<TreeFeatureConfig, ?> STAFF_TREE =
            Feature.TREE.configure( new TreeFeatureConfig.Builder(
                    SimpleBlockStateProviderAccessor.constructor(NDObjects.STAFF_LOG.getDefaultState()), // Trunk block provider
                    new StraightTrunkPlacer(8, 3, 0),
                    SimpleBlockStateProviderAccessor.constructor(NDObjects.STAFF_LEAVES.getDefaultState()), // Foliage block provider
                    new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(3), 4),
                    new TwoLayersFeatureSize(0, 0, 0) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
                    ).build());

    public static final PlacedFeature STAFF_TREE_WITH_CHANCE = STAFF_TREE.withPlacement(VegetationPlacedFeatures.modifiersWithWouldSurvive(RarityFilterPlacementModifier.of(1), NDObjects.STAFF_SAPLING));

    public static void init() {

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(NightDruid.MODID, "staff_tree"), STAFF_TREE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(NightDruid.MODID, "staff_tree"), STAFF_TREE_WITH_CHANCE);

        BiomeModification worldGen = BiomeModifications.create(new Identifier(NightDruid.MODID, "world_features"));


        worldGen.add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), context -> context.getGenerationSettings().addBuiltInFeature(GenerationStep.Feature.VEGETAL_DECORATION, STAFF_TREE_WITH_CHANCE));

    }
}
