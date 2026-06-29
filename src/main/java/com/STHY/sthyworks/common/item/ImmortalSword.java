package com.STHY.sthyworks.common.item;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import java.util.List;

public class ImmortalSword extends ItemSword {
    public static final Item.ToolMaterial ImmortalSwordMaterial = EnumHelper.addToolMaterial("ImmortalSwordMaterial", 0, 0, 10.0F, 4.0F, 22);

    public ImmortalSword() {
        super(ImmortalSwordMaterial);
        this.setUnlocalizedName("immortalSword");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:immortalSword");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        super.addInformation(itemStack, entityPlayer, list, par4);
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line1"));
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line2"));
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line3"));
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line4"));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            double maxDistance = 20.0D;
            EntityLivingBase target = getClosestTarget(worldIn, player, maxDistance);
            if (target != null) {
                player.attackTargetEntityWithCurrentItem(target);
            }
        }
        super.onItemRightClick(itemStackIn, worldIn, player);
        return itemStackIn;
    }

    private EntityLivingBase getClosestTarget(World worldIn, EntityPlayer player, double maxDistance) {
        Vec3 startPos = Vec3.createVectorHelper(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        Vec3 lookVec = player.getLookVec();
        Vec3 endPos = Vec3.createVectorHelper(
            startPos.xCoord + lookVec.xCoord * maxDistance,
            startPos.yCoord + lookVec.yCoord * maxDistance,
            startPos.zCoord + lookVec.zCoord * maxDistance
        );

        List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(
            player, player.boundingBox.expand(maxDistance, maxDistance, maxDistance));
        EntityLivingBase closestTarget = null;

        for (Entity entity : entities) {
            if (!(entity instanceof EntityLivingBase)) {
                continue;
            }
            if (!entity.canBeCollidedWith()) {
                continue;
            }
            double closestDistance = maxDistance;
            float borderSize = entity.getCollisionBorderSize();
            AxisAlignedBB aabb = entity.boundingBox.expand(borderSize, borderSize, borderSize);
            MovingObjectPosition mop = aabb.calculateIntercept(startPos, endPos);
            if (mop != null) {
                double distance = startPos.distanceTo(mop.hitVec);
                if (distance < closestDistance) {
                    closestTarget = (EntityLivingBase) entity;
                    closestDistance = distance;
                }
            }
        }
        return closestTarget;
    }
}
