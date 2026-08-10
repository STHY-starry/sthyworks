package com.STHY.sthyworks.common.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.entity.AdorableGugu;

public class GuguSphere extends Item {

    public GuguSphere() {
        this.setUnlocalizedName("guguSphere");
        this.setTextureName("sthyworks:guguSphere");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setMaxStackSize(1);
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        if (player.worldObj.isRemote) return false;
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound()
                .hasKey("guguData")) {
                return false;
            }
        }
        if (target instanceof AdorableGugu && !target.isDead) {
            NBTTagCompound tag = new NBTTagCompound();
            target.writeEntityToNBT(tag);
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            stack.getTagCompound()
                .setTag("guguData", tag);
            target.setDead();
            return true;
        }
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float xOffset, float yOffset, float zOffset) {
        if (world.isRemote) return false;
        if (!stack.hasTagCompound()) return false;
        if (!stack.getTagCompound()
            .hasKey("guguData")) return false;
        int posX = x;
        int posY = y;
        int posZ = z;
        switch (side) {
            case 0:
                posY--;
                break;
            case 1:
                posY++;
                break;
            case 2:
                posZ--;
                break;
            case 3:
                posZ++;
                break;
            case 4:
                posX--;
                break;
            case 5:
                posX++;
                break;
        }
        AdorableGugu gugu = new AdorableGugu(world);
        NBTTagCompound tag = stack.getTagCompound()
            .getCompoundTag("guguData");
        gugu.readEntityFromNBT(tag);
        gugu.setPosition(posX + 0.5F, posY, posZ + 0.5F);
        world.spawnEntityInWorld(gugu);
        stack.setTagCompound(null);
        return true;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        list.add(StatCollector.translateToLocal("item.guguSphere.tooltips.line1"));
    }
}
