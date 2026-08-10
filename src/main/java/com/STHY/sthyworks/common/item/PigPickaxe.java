package com.STHY.sthyworks.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

public class PigPickaxe extends ItemPickaxe {

    public static final Item.ToolMaterial PIG = EnumHelper.addToolMaterial("PIG", 3, 1561, 6.0F, 2.0F, 14)
        .setRepairItem(new ItemStack(Items.porkchop, 1));

    public PigPickaxe() {
        super(PIG);
        this.setUnlocalizedName("pigPickaxe");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:pigPickaxe");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.eat;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_) {
        return 32;
    }

    @Override
    public ItemStack onEaten(ItemStack itemStack, World worldIn, EntityPlayer player) {
        int expendedDamage = Math.max(1, (int) (0.25F * (itemStack.getMaxDamage() - itemStack.getItemDamage())));
        itemStack.damageItem(expendedDamage, player);
        player.getFoodStats()
            .func_151686_a((ItemFood) Items.cooked_porkchop, itemStack);
        worldIn.playSoundAtEntity(player, "random.burp", 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        player.addPotionEffect(
            new PotionEffect(
                Potion.digSpeed.getId(),
                expendedDamage * expendedDamage / 250 + 100,
                expendedDamage / 100));
        return itemStack;
    }
}
