package net.orcinus.goodending.entities.ai;

import com.google.common.collect.Lists;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import java.util.List;

public class FindWoodGoal extends Goal {
    private final WoodpeckerEntity woodpeckerEntity;
    private int findingTicks;
    private BlockPos woodPos;

    public FindWoodGoal(WoodpeckerEntity woodpeckerEntity) {
        this.woodpeckerEntity = woodpeckerEntity;
    }

    @Override
    public boolean canStart() {
        if (this.woodpeckerEntity.getWoodPos() == null && this.getWoodPos() != null) {
            this.woodPos = this.getWoodPos();
            return this.woodPos != null && this.woodpeckerEntity.woodAttachingCooldownTicks == 0;
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        return this.findingTicks > 0 && this.woodpeckerEntity.getWoodPos() == null && this.woodPos != null && super.shouldContinue();
    }

    @Override
    public void start() {
        super.start();
        this.findingTicks = 20 * 20;
    }

    @Override
    public void stop() {
        super.stop();
        this.woodpeckerEntity.woodAttachingCooldownTicks = 20 * 90;
        if (this.woodpeckerEntity.getWoodPos() != null) {
            this.woodpeckerEntity.getLookControl().lookAt(Vec3d.ofBottomCenter(this.woodpeckerEntity.getWoodPos()));
        }
        this.findingTicks = 0;
    }

    @Override
    public void tick() {
        if (this.findingTicks > 0) {
            this.findingTicks--;
        }
        final BlockPos finalPos = this.woodPos;
        if (finalPos != null && this.findingTicks > 0) {
            Vec3d vec3d = Vec3d.ofBottomCenter(finalPos);

            BlockHitResult hitResult = this.woodpeckerEntity.world.raycast(new BlockStateRaycastContext(this.woodpeckerEntity.getSyncedPos(), vec3d, state -> state.isIn(BlockTags.LOGS)));

            this.woodpeckerEntity.getNavigation().startMovingTo(vec3d.x, vec3d.y, vec3d.z, 1.0F);
            this.woodpeckerEntity.getLookControl().lookAt(vec3d);

            Direction direction = hitResult.getSide();
            if (direction != Direction.UP && direction != Direction.DOWN) {
                BlockPos relativePosition = new BlockPos(vec3d).offset(direction);
                if (this.woodpeckerEntity.world.isAir(relativePosition)) {
                    float dist = MathHelper.sqrt((float) this.woodpeckerEntity.squaredDistanceTo(new Vec3d(relativePosition.getX(), relativePosition.getY(), relativePosition.getZ())));
                    List<WoodpeckerEntity> woodpeckerEntities = this.woodpeckerEntity.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(relativePosition));
                    if (woodpeckerEntities.size() == 1 && dist <= 1.3D) {
                        double axisDirection = direction.getDirection() == Direction.AxisDirection.POSITIVE ? 0.175D : 0.825D;
                        double xPosition = relativePosition.getX() + (direction.getAxis() == Direction.Axis.Z ? 0.5D : axisDirection);
                        double zPosition = relativePosition.getZ() + (direction.getAxis() == Direction.Axis.X ? 0.5D : axisDirection);
                        double yPosition = relativePosition.getY() + 0.25D;
                        this.woodpeckerEntity.getLookControl().lookAt(xPosition, yPosition, zPosition);
                        this.woodpeckerEntity.setPos(xPosition, yPosition, zPosition);
                        if (dist <= 1.005D) {
                            BlockPos offset = new BlockPos(xPosition, yPosition, zPosition).offset(direction.getOpposite());
                            this.woodpeckerEntity.setWoodPos(offset);
                            this.woodpeckerEntity.setPose(EntityPose.STANDING);
                        }
                    }
                }
            }
        }
    }

    public BlockPos getWoodPos() {
        List<BlockPos> poses = Lists.newArrayList();
        int range = 5;
        BlockPos pos = this.woodpeckerEntity.getBlockPos();
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    BlockPos add = pos.add(x, y, z);
                    if (this.woodpeckerEntity.world.getBlockState(add).isIn(BlockTags.LOGS) && this.woodpeckerEntity.getBlockPos().isWithinDistance(add, range)) {
                        poses.add(add);
                    }
                }
            }
        }
        if (poses.isEmpty()) return null;

//        return poses.get(this.woodpeckerEntity.world.getRandom().nextInt(poses.size()));
        return poses.get(this.woodpeckerEntity.world.getRandom().nextInt(poses.size()));
    }

}
