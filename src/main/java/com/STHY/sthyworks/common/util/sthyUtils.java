package com.STHY.sthyworks.common.util;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class sthyUtils {

    public static boolean isAtSpecificTimes(World world, int ticks, int offset) {
        return (world.getTotalWorldTime() + offset) % ticks == 0;
    }

    public static EntityLivingBase getClosestTarget(World worldIn, EntityPlayer player, double maxDistance) {
        Vec3 startPos = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3 lookVec = player.getLookVec();
        Vec3 endPos = Vec3.createVectorHelper(
            startPos.xCoord + lookVec.xCoord * maxDistance,
            startPos.yCoord + lookVec.yCoord * maxDistance,
            startPos.zCoord + lookVec.zCoord * maxDistance);

        List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(
            player,
            player.boundingBox.expand(maxDistance, maxDistance, maxDistance));
        EntityLivingBase closestTarget = null;

        double closestDistance = maxDistance;
        for (Entity entity : entities) {
            if (!(entity instanceof EntityLivingBase)) {
                continue;
            }
            if (!entity.canBeCollidedWith()) {
                continue;
            }
            Vec3 entityPos = Vec3.createVectorHelper(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
            // 1.7.10 的 rayTraceBlocks 会原地改写第一个参数（拿它当游标逐格推进），
            // 这里必须传一次性副本，不能复用 startPos，否则后续实体的射线起点会被污染
            Vec3 rayStart = Vec3.createVectorHelper(startPos.xCoord, startPos.yCoord, startPos.zCoord);
            if (worldIn.rayTraceBlocks(rayStart, entityPos) != null) continue;
            float borderSize = entity.getCollisionBorderSize();
            AxisAlignedBB aabb = entity.boundingBox.expand(borderSize, borderSize, borderSize);
            MovingObjectPosition EntityMop = aabb.calculateIntercept(startPos, endPos);
            if (EntityMop != null) {
                double distance = startPos.distanceTo(EntityMop.hitVec);
                if (distance < closestDistance) {
                    closestTarget = (EntityLivingBase) entity;
                    closestDistance = distance;
                }
            }
        }
        return closestTarget;
    }
}
