package com.STHY.sthyworks.common.entity;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.item.ItemLoader;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

public class AdorableGugu extends EntityCreature implements IRangedAttackMob {

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
        this.tasks.addTask(0, new EntityAIArrowAttack(this, 1.0D, 20, 60, 10.0F));
        this.tasks.addTask(1, new EntityAITempt(this, 1.25D, Items.cake, false));
        this.tasks.addTask(2, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));

        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(
            this,
            EntityLivingBase.class,
            0,
            true,
            false,
            entity -> entity instanceof IMob
            )
        );
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);

        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        GuguProjectile projectile = new GuguProjectile(this.worldObj, this);

        double dx = target.posX - projectile.posX;
        double dy = target.boundingBox.minY + (double) (target.height / 2.0F) - projectile.posY;
        double dz = target.posZ - projectile.posZ;
        double d = MathHelper.sqrt_double(dx * dx + dz * dz);

        //子弹不受重力影响，不加抛物线修正
        projectile.setThrowableHeading(dx, dy, dz, 1.4F, 6.0F);
        this.worldObj.spawnEntityInWorld(projectile);
        this.playSound("random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
    }
}
