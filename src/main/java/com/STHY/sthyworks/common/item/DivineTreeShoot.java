package com.STHY.sthyworks.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class DivineTreeShoot extends Item {

    public DivineTreeShoot() {
        setUnlocalizedName("divineTreeShoot");
        setTextureName("sthyworks:divineTreeShoot");
        setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slotIndex, boolean isHeld) {
        if (worldIn.isRemote) return;
        if (!(entityIn instanceof EntityPlayer)) return;
        if (isHeld) {
            EntityPlayer player = (EntityPlayer) entityIn;
            int currentSlot = player.inventory.currentItem;
            ItemStack heldStack = player.inventory.mainInventory[currentSlot];
            if (heldStack != null) {
                ItemStack dropStack = heldStack.copy();
                player.inventory.mainInventory[currentSlot] = null;
                EntityItem entityItem = new EntityItem(
                    worldIn,
                    player.posX,
                    player.posY + player.getEyeHeight(),
                    player.posZ,
                    dropStack);
                entityItem.motionX = player.getLookVec().xCoord * 0.8D;
                entityItem.motionY = player.motionY;
                entityItem.motionZ = player.getLookVec().zCoord * 0.8D;
                worldIn.spawnEntityInWorld(entityItem);
                player.addChatMessage(new ChatComponentTranslation("item.divineTreeShoot.drop"));
            }
        }
    }
}
