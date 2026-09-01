package com.STHY.sthyworks.common.item;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.util.sthyUtils;

public class ImmortalSword extends ItemSword {

    public static final Item.ToolMaterial ImmortalSwordMaterial = EnumHelper
        .addToolMaterial("ImmortalSwordMaterial", 0, 0, 10.0F, 4.0F, 22);

    public ImmortalSword() {
        super(ImmortalSwordMaterial);
        this.setUnlocalizedName("immortalSword");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:immortalSword");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line1"));
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line2"));
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line3"));
        list.add(StatCollector.translateToLocal("item.immortalSword.tooltips.line4"));
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return this.getItemStackLimit(itemStack) == 1;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            EntityLivingBase target = sthyUtils.getClosestTarget(worldIn, player, 20.0D);
            if (target != null) {
                player.attackTargetEntityWithCurrentItem(target);
            }
        } else {
            sthyUtils.getClosestTarget(worldIn, player, 20.0D);
        }
        super.onItemRightClick(itemStackIn, worldIn, player);
        return itemStackIn;
    }
}
