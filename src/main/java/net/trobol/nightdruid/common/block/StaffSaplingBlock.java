package net.trobol.nightdruid.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class StaffSaplingBlock extends SaplingBlock {
    public StaffSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        Box box = new Box(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D).offset(pos.getX(), pos.getY(), pos.getZ());
        List<ItemEntity> items = world.getEntitiesByClass(ItemEntity.class, box, EntityPredicates.VALID_ENTITY);
        Iterator<ItemEntity> iter = items.iterator();

        while (iter.hasNext())
        {
            ItemEntity itemEntity = iter.next();
            ItemStack itemStack = itemEntity.getStack();
            if ( itemStack.isOf(Items.DIAMOND) ) {
                LOGGER.info("create sapling with diamond");
                itemStack.decrement(1);
                return;
            }

        }

        this.generate(world, pos, state, random);
    }

}
