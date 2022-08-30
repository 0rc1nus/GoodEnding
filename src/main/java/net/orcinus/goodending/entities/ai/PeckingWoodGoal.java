package net.orcinus.goodending.entities.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingSoundEvents;

public class PeckingWoodGoal extends Goal {
    private final WoodpeckerEntity woodpeckerEntity;
    private int stuckToLogTicks;
    private int peckingCooldownTicks;
    private int peckingTicks;

    public PeckingWoodGoal(WoodpeckerEntity woodpeckerEntity) {
        this.woodpeckerEntity = woodpeckerEntity;
    }

    @Override
    public boolean canStart() {
        return this.woodpeckerEntity.getPeckingWoodCooldownTicks() == 0 && this.woodpeckerEntity.getWoodPos() != null;
    }

    @Override
    public boolean shouldContinue() {

        if(this.woodpeckerEntity.getDamageTracker().wasRecentlyAttacked()) return false;
        return this.stuckToLogTicks > 0 && this.woodpeckerEntity.getWoodPos() != null && super.shouldContinue();
    }

    @Override
    public void start() {
        super.start();
        if (this.woodpeckerEntity.getWoodPos() != null) {
            this.woodpeckerEntity.getLookControl().lookAt(Vec3d.ofBottomCenter(this.woodpeckerEntity.getWoodPos()));
            Random random = this.woodpeckerEntity.getRandom();
            this.stuckToLogTicks = 20 * 30 + random.nextInt(20 * 45);
            this.peckingCooldownTicks = 20 * 2 + random.nextInt(20 * 5);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.stuckToLogTicks > 0) {
            this.stuckToLogTicks--;
            this.peckingCooldownTicks--;
            this.peckingTicks--;
            BlockPos woodPos = this.woodpeckerEntity.getWoodPos();
            Random random = this.woodpeckerEntity.getRandom();
            if (this.peckingTicks < 0 && this.peckingTicks > 50) this.woodpeckerEntity.setPose(EntityPose.STANDING);

            if (woodPos != null) {
                double lookX = this.woodpeckerEntity.getLookControl().getLookX();
                double lookY = this.woodpeckerEntity.getLookControl().getLookY();
                double lookZ = this.woodpeckerEntity.getLookControl().getLookZ();
                this.woodpeckerEntity.getLookControl().lookAt(Vec3d.ofBottomCenter(new Vec3i(lookX, lookY, lookZ)));


                if (this.woodpeckerEntity.world.getBlockState(new BlockPos(lookX, lookY, lookZ)).isIn(BlockTags.LOGS) && peckingCooldownTicks == 0) {
                    this.peckingTicks = 50;

                    this.woodpeckerEntity.playSound(GoodEndingSoundEvents.ENTITY_WOODPECKER_DRUM, 1.0F, 1.0F);
                    this.peckingCooldownTicks = 20 * 10 + random.nextInt(20 * 15);
                }
            }
            if (this.peckingTicks > 0 && this.peckingTicks < 50) this.woodpeckerEntity.setPose(EntityPose.DIGGING);

            if (this.peckingTicks > 25 && this.peckingTicks < 40) {
                this.addDigParticles(woodPos);
            }
        }
    }

    private void addDigParticles(BlockPos woodPos) {
        BlockState blockState = this.woodpeckerEntity.world.getBlockState(woodPos);
        for (int i = 0; i < 15; ++i) {
            double d = woodPos.getX() + 0.5D;
            double e = woodPos.getY() + 0.5D;
            double f = woodPos.getZ() + 0.5D;
            ((ServerWorld)this.woodpeckerEntity.world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), d, e, f, 1, 0.5, 0.25, 0.5, 0.0);
        }
    }

    @Override
    public void stop() {
        this.woodpeckerEntity.setPose(EntityPose.FALL_FLYING);
        this.woodpeckerEntity.setWoodPos(null);
        this.woodpeckerEntity.setPeckingWoodCooldownTicks(600);
    }
}
