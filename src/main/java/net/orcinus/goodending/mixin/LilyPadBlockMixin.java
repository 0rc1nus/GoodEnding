package net.orcinus.goodending.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.orcinus.goodending.init.GoodEndingBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LilyPadBlock.class)
public class LilyPadBlockMixin extends PlantBlock {

    public LilyPadBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext entityShapeContext && entityShapeContext.getEntity() != null) {
            if (entityShapeContext.getEntity() instanceof BoatEntity) {
                return VoxelShapes.empty();
            }
        }
        return super.getCollisionShape(state, world, pos, context);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;breakBlock(Lnet/minecraft/util/math/BlockPos;ZLnet/minecraft/entity/Entity;)Z"), method = "onEntityCollision", cancellable = true)
    private void GE$onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        ci.cancel();
    }


    @Inject(at = @At(value = "HEAD"), method = "canPlantOnTop", cancellable = true)
    private void GE$canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).isOf(GoodEndingBlocks.LARGE_LILY_PAD)) cir.setReturnValue(false);
    }

}
