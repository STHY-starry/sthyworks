package com.STHY.sthyworks.common.item;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.STHY.sthyworks.client.key.KeyLoader;
import com.STHY.sthyworks.common.attribute.STHYAttributes;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.damege.DamageLoader;
import com.STHY.sthyworks.common.entity.withoutEgg.PathogenesisProjectile;
import com.STHY.sthyworks.common.potion.PotionLoader;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Pathogenesis extends ItemBow {

    @SideOnly(Side.CLIENT)
    IIcon[] icons = new IIcon[3];

    public Pathogenesis() {
        this.maxStackSize = 1;
        this.setMaxDamage(0);
        this.setUnlocalizedName("pathogenesis");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public void registerIcons(IIconRegister register) {
        icons[0] = register.registerIcon("sthyworks:pathogenesis_0");
        icons[1] = register.registerIcon("sthyworks:pathogenesis_1");
        icons[2] = register.registerIcon("sthyworks:pathogenesis_2");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[0];
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (usingItem == null) {
            return icons[0];
        }
        int usedTime = this.getMaxItemUseDuration(stack) - useRemaining;
        if (usedTime >= 48) {
            return icons[2];
        } else if (usedTime >= 24) {
            return icons[1];
        } else return icons[0];
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        KeyBinding toggleTooltipsDisplayKey = KeyLoader.toggleTooltipsDisplay;
        if (!Keyboard.isKeyDown(toggleTooltipsDisplayKey.getKeyCode())) {
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.routine.line1"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.routine.line2"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.routine.line3"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.routine.line4"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.routine.line5"));
            list.add(
                String.format(
                    StatCollector.translateToLocal("key.sthyworks.toggleTooltipsDisplay.tooltips"),
                    GameSettings.getKeyDisplayString(toggleTooltipsDisplayKey.getKeyCode())));
        } else {
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.toggle.line1"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.toggle.line2"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.toggle.line3"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.toggle.line4"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.toggle.line5"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.toggle.line6"));
            list.add(StatCollector.translateToLocal("item.pathogenesis.tooltips.toggle.line7"));
        }

    }

    @Override
    public boolean isItemTool(ItemStack stack) {
        return this.getItemStackLimit(stack) == 1;
    }

    @Override
    public int getItemEnchantability(ItemStack stack) {
        return 16;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
        int usedTime = this.getMaxItemUseDuration(stack) - count;
        double pathologyExpertise = 1.0D;
        if (player.getEntityAttribute(STHYAttributes.pathologyExpertise) != null) {
            pathologyExpertise = player.getEntityAttribute(STHYAttributes.pathologyExpertise)
                .getAttributeValue();
        }
        double adjustedPathologyExpertise = pathologyExpertise / (pathologyExpertise + 1);// 0~1
        int powerLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
        int punchLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
        if (usedTime >= 48) {
            double radius = 7.0D * pathologyExpertise;
            List<EntityLivingBase> list = world
                .getEntitiesWithinAABB(EntityLivingBase.class, player.boundingBox.expand(radius, radius, radius));
            for (EntityLivingBase entityLivingBase : list) {
                if (entityLivingBase == player) continue;
                if (entityLivingBase.getActivePotionEffect(PotionLoader.deadlyPoison) != null) {
                    Random rand = player.getRNG();
                    int recordAmplifier = entityLivingBase.getActivePotionEffect(PotionLoader.deadlyPoison)
                        .getAmplifier();
                    int recordDuration = entityLivingBase.getActivePotionEffect(PotionLoader.deadlyPoison)
                        .getDuration();
                    if (!world.isRemote) {
                        entityLivingBase.removePotionEffect(PotionLoader.deadlyPoison.getId());
                        float damage = 12.0F * (1 + powerLevel * 0.2F)
                            * (float) pathologyExpertise
                            * (float) Math.pow(1.4F, recordAmplifier);
                        DamageSource damageSource = new EntityDamageSource(
                            DamageLoader.DeadlyPoison.getDamageType(),
                            player);
                        entityLivingBase.attackEntityFrom(damageSource, damage);
                        int newDuration = (int) (recordDuration * adjustedPathologyExpertise);
                        int newAmplifier = recordAmplifier + 1 + adjustedPathologyExpertise > rand.nextDouble() ? 1
                            : 0 + adjustedPathologyExpertise > rand.nextDouble() ? 1 : 0;
                        entityLivingBase.addPotionEffect(
                            new PotionEffect(PotionLoader.deadlyPoison.getId(), newDuration, newAmplifier));
                    } else {
                        int particleCount = 32;
                        for (int i = 0; i < particleCount; i++) {
                            world.spawnParticle(
                                "instantSpell",
                                entityLivingBase.posX + (rand.nextDouble() - 0.5D) * 1.5D,
                                entityLivingBase.posY + entityLivingBase.height * 1.2D,
                                entityLivingBase.posZ + (rand.nextDouble() - 0.5D) * 1.5D,
                                (rand.nextDouble() - 0.5D) * 0.5D,
                                (rand.nextDouble() - 0.5D) * 0.5D,
                                (rand.nextDouble() - 0.5D) * 0.5D);
                        }
                    }
                }
            }

        } else if (usedTime >= 24) {
            if (!world.isRemote) {
                Vec3 look = player.getLookVec();
                PathogenesisProjectile projectile = new PathogenesisProjectile(world, player);
                projectile.setDamage(8.0F + powerLevel * 2.0F);
                projectile.setExplosive(true);
                projectile.setKnockbackStrength(punchLevel + 1);
                projectile.setThrowableHeading(look.xCoord, look.yCoord, look.zCoord, 1.3F, 1.0F);
                world.spawnEntityInWorld(projectile);
            }
        } else {
            if (!world.isRemote) {
                Vec3 look = player.getLookVec();
                PathogenesisProjectile projectile = new PathogenesisProjectile(world, player);
                projectile.setDamage(4.5F + powerLevel * 1.5F);
                projectile.setKnockbackStrength(punchLevel);
                projectile.setMaxPenetration(2);
                projectile.setThrowableHeading(look.xCoord, look.yCoord, look.zCoord, 1.5F, 1.0F);
                world.spawnEntityInWorld(projectile);
            }
        }
    }

    public static void addDeadlyPoison(EntityLivingBase entitylivingbase, int deadlyPoisonMaxLevel,
        int deadlyPoisonBaseDuration, boolean gradual) {
        if (gradual) {
            if (entitylivingbase.getActivePotionEffect(PotionLoader.deadlyPoison) == null) {
                entitylivingbase
                    .addPotionEffect(new PotionEffect(PotionLoader.deadlyPoison.getId(), deadlyPoisonBaseDuration, 0));
            } else {
                int currentLevel = entitylivingbase.getActivePotionEffect(PotionLoader.deadlyPoison)
                    .getAmplifier();
                int currentDuration = entitylivingbase.getActivePotionEffect(PotionLoader.deadlyPoison)
                    .getDuration();
                if (currentLevel < deadlyPoisonMaxLevel) {
                    entitylivingbase.addPotionEffect(
                        new PotionEffect(
                            PotionLoader.deadlyPoison.getId(),
                            currentDuration / 2 + deadlyPoisonBaseDuration / 8,
                            currentLevel + 1));
                } else {
                    entitylivingbase.addPotionEffect(
                        new PotionEffect(
                            PotionLoader.deadlyPoison.getId(),
                            currentDuration + deadlyPoisonBaseDuration / 6,
                            currentLevel));
                }
            }
        } else {
            entitylivingbase.addPotionEffect(
                new PotionEffect(PotionLoader.deadlyPoison.getId(), deadlyPoisonBaseDuration, deadlyPoisonMaxLevel));
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);
        multimap.put(
            STHYAttributes.pathologyExpertise.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("571b5726-dacf-48b5-bf24-81f040109d0f"),
                "Pathogenesis pathologyExpertise 1",
                0.4D,
                1));
        multimap.put(
            STHYAttributes.deadlyPoisonResistance.getAttributeUnlocalizedName(),
            new AttributeModifier(
                UUID.fromString("0489e866-3d79-4039-b614-0f4ee14c81bb"),
                "Pathogenesis deadlyPoisonResistance 0",
                1.0D,
                0));
        return multimap;
    }
}
