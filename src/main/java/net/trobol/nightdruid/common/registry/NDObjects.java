package net.trobol.nightdruid.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.trobol.nightdruid.common.NightDruid;
import net.trobol.nightdruid.common.block.StaffSaplingBlock;
import net.trobol.nightdruid.common.world.generator.tree.generator.StaffSaplingGenerator;
import net.trobol.nightdruid.mixin.BlocksAccessor;

public class NDObjects {

    public static final Block STAFF_LOG =  new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG));
    public static final Block STAFF_LEAVES = BlocksAccessor.callCreateLeavesBlock(BlockSoundGroup.GRASS);

    public static final Block STAFF_SAPLING = new StaffSaplingBlock(new StaffSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));

    public static final Item STAFF_SAPLING_ITEM = new BlockItem(STAFF_SAPLING, new Item.Settings().group(ItemGroup.MISC));

    public static void init() {

        Registry.register(Registry.BLOCK, new Identifier(NightDruid.MODID, "staff_log"), STAFF_LOG);
        Registry.register(Registry.BLOCK, new Identifier(NightDruid.MODID, "staff_leaves"), STAFF_LEAVES);

        Registry.register(Registry.BLOCK, new Identifier(NightDruid.MODID, "staff_sapling"), STAFF_SAPLING);

        Registry.register(Registry.ITEM, new Identifier(NightDruid.MODID, "staff_sapling"), STAFF_SAPLING_ITEM);
    }


}
