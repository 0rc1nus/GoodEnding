package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import java.util.Optional;

public class FindWoodGoal extends Goal {
    private final WoodpeckerEntity woodpeckerEntity;
    private boolean running;

    public FindWoodGoal(WoodpeckerEntity woodpeckerEntity) {
        this.woodpeckerEntity = woodpeckerEntity;
    }

    @Override
    public boolean canStart() {
        Optional<BlockPos> woodPos = this.getNearbyWood();
        if (woodPos.isPresent()) {
            this.woodpeckerEntity.setWoodPos(woodPos.get());
            this.woodpeckerEntity.getNavigation().startMovingTo((double)this.woodpeckerEntity.getWoodPos().getX() + 0.5, (double)this.woodpeckerEntity.getWoodPos().getY() + 0.5, (double)this.woodpeckerEntity.getWoodPos().getZ() + 0.5, 1.2f);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        if (!this.running) {
            return false;
        }
        if (!this.woodpeckerEntity.hasWoodPos()) {
            return false;
        }
        if (this.woodpeckerEntity.age % 20 == 0 && !this.woodpeckerEntity.world.getBlockState(this.woodpeckerEntity.getWoodPos()).isIn(BlockTags.LOGS)) {
            this.woodpeckerEntity.setWoodPos(null);
            return false;
        }
        return true;
    }

    @Override
    public void start() {
        super.start();
        this.running = true;
    }

    @Override
    public void stop() {
        super.stop();
        this.running = false;
    }

    @Override
    public void tick() {
        super.tick();

        Vec3d vec3d = Vec3d.ofBottomCenter(this.woodpeckerEntity.getWoodPos());

        BlockHitResult hitResult = this.woodpeckerEntity.world.raycast(new BlockStateRaycastContext(this.woodpeckerEntity.getSyncedPos(), vec3d, state -> state.isIn(BlockTags.LOGS)));

        this.woodpeckerEntity.getNavigation().startMovingAlong(this.woodpeckerEntity.getNavigation().findPathTo(vec3d.x, vec3d.y, vec3d.z, 0), 1.0F);
        this.woodpeckerEntity.getLookControl().lookAt(vec3d);

        if (hitResult.getSide() != Direction.UP && hitResult.getSide() != Direction.DOWN) {
            BlockPos relativePosition = new BlockPos(vec3d).offset(hitResult.getSide());
            if (MathHelper.sqrt((float) this.woodpeckerEntity.squaredDistanceTo(new Vec3d(relativePosition.getX(), relativePosition.getY(), relativePosition.getZ()))) <= 1D) {
                if (hitResult.getSide() == Direction.NORTH) this.woodpeckerEntity.teleport(relativePosition.getX() + 0.5D, relativePosition.getY() + 0.25D, relativePosition.getZ() + 0.825D);
                if (hitResult.getSide() == Direction.SOUTH) this.woodpeckerEntity.teleport(relativePosition.getX() + 0.5D, relativePosition.getY() + 0.25D, relativePosition.getZ() + 0.175D);
                if (hitResult.getSide() == Direction.EAST) this.woodpeckerEntity.teleport(relativePosition.getX() + 0.175D, relativePosition.getY() + 0.25D, relativePosition.getZ() + 0.5D);
                if (hitResult.getSide() == Direction.WEST) this.woodpeckerEntity.teleport(relativePosition.getX() + 0.825D, relativePosition.getY() + 0.25D, relativePosition.getZ() + 0.5D);

                System.out.println(this.woodpeckerEntity.getWoodPos());
                this.woodpeckerEntity.setAttachedFace(hitResult.getSide());
            }
        }
    }

    private Optional<BlockPos> getNearbyWood() {
        BlockPos blockPos = this.woodpeckerEntity.getBlockPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        double searchDistance = 5.0D;
        for(int y = 0; (double)y <= searchDistance; y = y > 0 ? -y : 1 - y) {
            for(int j = 0; (double)j < searchDistance; ++j) {
                for(int x = 0; x <= j; x = x > 0 ? -x : 1 - x) {
                    for(int z = x < j && x > -j ? j : 0; z <= j; z = z > 0 ? -z : 1 - z) {
                        mutable.set(blockPos, x, y - 1, z);
                        if (blockPos.isWithinDistance(mutable, searchDistance) && this.woodpeckerEntity.world.getBlockState(mutable).isIn(BlockTags.LOGS)) {
                            return Optional.of(mutable);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }

}
