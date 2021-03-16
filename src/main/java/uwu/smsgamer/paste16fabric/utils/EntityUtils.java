package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.collection.ReusableStream;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;

import java.util.stream.Stream;

public class EntityUtils {
    public static Vec3d adjustMovementForCollisions(Entity entity, Vec3d movement) {
        Box box = entity.getBoundingBox();
        ShapeContext shapeContext = ShapeContext.of(entity);
        VoxelShape voxelShape = entity.world.getWorldBorder().asVoxelShape();
        Stream<VoxelShape> stream = VoxelShapes.matchesAnywhere(voxelShape, VoxelShapes.cuboid(box.contract(1.0E-7D)), BooleanBiFunction.AND) ? Stream.empty() : Stream.of(voxelShape);
        Stream<VoxelShape> stream2 = entity.world.getEntityCollisions(entity, box.stretch(movement), (e) -> true);
        ReusableStream<VoxelShape> reusableStream = new ReusableStream(Stream.concat(stream2, stream));
        Vec3d vec3d = movement.lengthSquared() == 0.0D ? movement : Entity.adjustMovementForCollisions(entity, movement, box, entity.world, shapeContext, reusableStream);
        boolean bl = movement.x != vec3d.x;
        boolean bl2 = movement.y != vec3d.y;
        boolean bl3 = movement.z != vec3d.z;
        boolean bl4 = entity.isOnGround() || bl2 && movement.y < 0.0D;
        if (entity.stepHeight > 0.0F && bl4 && (bl || bl3)) {
            Vec3d vec3d2 = Entity.adjustMovementForCollisions(entity, new Vec3d(movement.x, (double) entity.stepHeight, movement.z), box, entity.world, shapeContext, reusableStream);
            Vec3d vec3d3 = Entity.adjustMovementForCollisions(entity, new Vec3d(0.0D, (double) entity.stepHeight, 0.0D), box.stretch(movement.x, 0.0D, movement.z), entity.world, shapeContext, reusableStream);
            if (vec3d3.y < (double) entity.stepHeight) {
                Vec3d vec3d4 = Entity.adjustMovementForCollisions(entity, new Vec3d(movement.x, 0.0D, movement.z), box.offset(vec3d3), entity.world, shapeContext, reusableStream).add(vec3d3);
                if (Entity.squaredHorizontalLength(vec3d4) > Entity.squaredHorizontalLength(vec3d2)) {
                    vec3d2 = vec3d4;
                }
            }

            if (Entity.squaredHorizontalLength(vec3d2) > Entity.squaredHorizontalLength(vec3d)) {
                return vec3d2.add(Entity.adjustMovementForCollisions(entity, new Vec3d(0.0D, -vec3d2.y + movement.y, 0.0D), box.offset(vec3d2), entity.world, shapeContext, reusableStream));
            }
        }

        return vec3d;
    }
}
