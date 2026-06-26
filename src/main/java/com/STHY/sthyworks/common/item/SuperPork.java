package com.STHY.sthyworks.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.damege.DamageLoader;

public class SuperPork extends ItemFood {

    public SuperPork() {
        super(16, 0.8F, false);
        this.setAlwaysEdible();
        this.setUnlocalizedName("superPork");
        this.setTextureName("sthyworks:superPork");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public void onFoodEaten(ItemStack itemStack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 1));
            player.addExperience(100);
            player.attackEntityFrom(DamageLoader.Pig, 10.0F);
        }
        super.onFoodEaten(itemStack, worldIn, player);
    }
}
