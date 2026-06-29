package com.STHY.sthyworks.common.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.block.BlockLoader;

public class AdorableGugu extends EntityCreature implements IRangedAttackMob {

    public static final RangedAttribute rangedAttackDamage = new RangedAttribute(
        "gugu.rangedAttackDamage",
        8.0D,
        0.0D,
        2048.0D);

    public AdorableGugu(World world) {
        super(world);
        this.setSize(0.6F, 1.8F);

        initEntityAI();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    @Override
    protected void dropFewItems(boolean hitByPlayer, int lootingLevel) {
        if (this.rand.nextInt(2) == 0) {
            this.dropItem(Item.getItemFromBlock(BlockLoader.guguBlock), 1);
        }
        super.dropFewItems(hitByPlayer, lootingLevel);
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }

    private void initEntityAI() {
        this.tasks.addTask(0, new EntityAIArrowAttack(this, 1.0D, 20, 40, 15.0F));
        this.tasks.addTask(1, new EntityAITempt(this, 1.25D, Items.cake, false));
        this.tasks.addTask(2, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));

        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(
            1,
            new EntityAINearestAttackableTarget(
                this,
                EntityLivingBase.class,
                0,
                true,
                false,
                entity -> entity instanceof IMob));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap()
            .registerAttribute(SharedMonsterAttributes.attackDamage);
        this.getAttributeMap()
            .registerAttribute(rangedAttackDamage);

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
            .setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed)
            .setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage)
            .setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange)
            .setBaseValue(32.0D);

        this.getEntityAttribute(rangedAttackDamage)
            .setBaseValue(8.0D);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        GuguProjectile projectile = new GuguProjectile(this.worldObj, this);

        double dx = target.posX - projectile.posX;
        double dy = target.boundingBox.maxY - (double) (target.height * 0.3F) - projectile.posY;
        double dz = target.posZ - projectile.posZ;

        // 子弹不受重力影响，不加抛物线修正
        projectile.setThrowableHeading(dx, dy, dz, 1.4F, 6.0F);
        projectile.setDamage(
            (float) this.getEntityAttribute(rangedAttackDamage)
                .getAttributeValue());
        this.worldObj.spawnEntityInWorld(projectile);
    }
}
