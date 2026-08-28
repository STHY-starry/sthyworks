package com.STHY.sthyworks.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MilkTea extends ItemFood {

    @SideOnly(Side.CLIENT)
    private final IIcon[] icons = new IIcon[4];

    public MilkTea() {
        super(2, 0.8F, false);
        this.setAlwaysEdible();
        this.setMaxStackSize(1);
        this.setMaxDamage(3);
        this.setUnlocalizedName("milkTea");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public void registerIcons(IIconRegister register) {
        icons[0] = register.registerIcon("sthyworks:milkTea_0");
        icons[1] = register.registerIcon("sthyworks:milkTea_1");
        icons[2] = register.registerIcon("sthyworks:milkTea_2");
        icons[3] = register.registerIcon("sthyworks:milkTea_3");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage < 0) {
            damage = 0;
        }
        if (damage > this.getMaxDamage()) {
            damage = this.getMaxDamage();
        }
        return icons[damage];
    }

    @Override
    public boolean isItemTool(ItemStack p_77616_1_) {
        return false;
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World worldIn, EntityPlayer player) {
        itemStack.damageItem(1, player);
        player.getFoodStats()
            .func_151686_a(this, itemStack);
        worldIn.playSoundAtEntity(player, "random.burp", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(itemStack, worldIn, player);
        return itemStack;
    }

    @Override
    public void onFoodEaten(ItemStack itemStack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 800, 0));
        } else {
            player.addChatMessage(new ChatComponentTranslation("item.MilkTea.onEaten"));
        }
        super.onFoodEaten(itemStack, worldIn, player);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return super.getMaxItemUseDuration(itemStack) / 2;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        list.add(StatCollector.translateToLocal("item.MilkTea.tooltips.line1"));
        list.add(StatCollector.translateToLocal("item.MilkTea.tooltips.line2"));
    }
}
