package net.orcinus.goodending.events;

import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.generators.FancyDarkOakSaplingGenerator;
import net.orcinus.goodending.world.gen.features.generators.LargeOakSaplingGenerator;
import net.orcinus.goodending.world.gen.features.generators.SwampSaplingGenerator;
import net.orcinus.goodending.world.gen.features.generators.TallBirchSaplingGenerator;

import java.util.List;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MiscEvents {

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        BlockPos blockPos = event.getPos();
        Level world = event.getLevel();
        ItemStack stack = event.getItemStack();
        if (world.getBlockState(blockPos).is(Blocks.LILY_PAD) && stack.is(Items.BONE_MEAL)) {
            if (!world.isClientSide) {
                if (!event.getEntity().getAbilities().instabuild) {
                    stack.shrink(1);
                }
                List<Block> list = Util.make(Lists.newArrayList(), block -> {
                    block.add(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get());
                    block.add(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get());
                    block.add(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.get());
                });
                world.setBlock(blockPos, list.get(world.getRandom().nextInt(list.size())).defaultBlockState(), 2);
                world.levelEvent(1505, blockPos, 0);
            }
            world.playSound(null, blockPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            event.getEntity().swing(event.getHand());
        }
    }

    private boolean isTallGrass(LevelAccessor world, BlockPos blockPos) {
        return world.getBlockState(blockPos).is(Blocks.TALL_GRASS);
    }

}
