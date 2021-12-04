package net.trobol.nightdruid.common.world.generator.tree.generator;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.trobol.nightdruid.common.registry.NDWorldGenerators;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class StaffSaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return NDWorldGenerators.STAFF_TREE;
    }
}
