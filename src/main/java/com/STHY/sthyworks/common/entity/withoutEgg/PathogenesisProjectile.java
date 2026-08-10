package com.STHY.sthyworks.common.entity.withoutEgg;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.item.Pathogenesis;

public class PathogenesisProjectile extends EntityThrowable {

    private float damage;
    private int knockbackStrength;
    private int maxPenetration;
    private int penetrationCount = 0;
    private boolean isExplosive = false;

    public PathogenesisProjectile(World world) {
        super(world);
    }

    public PathogenesisProjectile(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public PathogenesisProjectile(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getKnockbackStrength() {
        return knockbackStrength;
    }

    public void setKnockbackStrength(int knockbackStrength) {
        this.knockbackStrength = knockbackStrength;
    }

    public int getMaxPenetration() {
        return maxPenetration;
    }

    public void setMaxPenetration(int maxPenetration) {
        this.maxPenetration = maxPenetration;
    }

    public void setExplosive(boolean explosive) {
        isExplosive = explosive;
    }

    @Override
    protected float getGravityVelocity() {
        if (!isExplosive) {
            return 0.01F;
        } else {
            return 0.03F;
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {
        if (!isExplosive) {
            if (!worldObj.isRemote) {
                if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    this.setDead();
                    return;
                }

                if (position.entityHit instanceof EntityLivingBase) {
                    EntityLivingBase hitEntity = (EntityLivingBase) position.entityHit;
                    EntityLivingBase thrower = this.getThrower();
                    DamageSource damageSource;
                    if (thrower == null) {
                        damageSource = new DamageSource("arrow");
                    } else {
                        damageSource = new EntityDamageSource("arrow", thrower);
                    }
                    damageSource.setProjectile();

                    Pathogenesis.addDeadlyPoison(hitEntity, 6, 120, true);
                    hitEntity.attackEntityFrom(damageSource, calculateDamage());
                    applyKnockback(hitEntity, false);

                    penetrationCount++;
                    if (penetrationCount > maxPenetration) {
                        this.setDead();
                    }
                }
            }
        } else {
            if (!worldObj.isRemote) {
                EntityLivingBase thrower = this.getThrower();
                DamageSource damageSource;
                if (thrower == null) {
                    damageSource = new DamageSource("explosion");
                } else {
                    damageSource = new EntityDamageSource("explosion", thrower);
                }
                damageSource.setExplosion();

                AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
                List<EntityLivingBase> list = this.worldObj
                    .getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

                for (EntityLivingBase entityLivingBase : list) {
                    Pathogenesis.addDeadlyPoison(entityLivingBase, 2, 180, false);
                    entityLivingBase.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 80, 2));
                    entityLivingBase.addPotionEffect(new PotionEffect(Potion.weakness.getId(), 80, 2));
                    entityLivingBase.attackEntityFrom(damageSource, damage);
                    applyKnockback(entityLivingBase, true);
                }

                worldObj.playAuxSFX(2002, (int) Math.round(posX), (int) Math.round(posY), (int) Math.round(posZ), 8196);
            }
            this.setDead();
        }
    }

    private float calculateDamage() {
        float damage = this.damage;
        damage *= (float) Math.pow(0.8D, penetrationCount);
        return damage;
    }

    private void applyKnockback(EntityLivingBase entityLivingBase, boolean isExplosive) {
        if (!isExplosive) {
            if (this.knockbackStrength > 0) {
                float horizontalVelocity = MathHelper
                    .sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                if (horizontalVelocity > 0.0F) {
                    entityLivingBase.addVelocity(
                        this.motionX * (double) this.knockbackStrength * 0.05D / (double) horizontalVelocity,
                        0.05D,
                        this.motionZ * (double) this.knockbackStrength * 0.05D / (double) horizontalVelocity);
                }
            }
        } else {
            double deltaX = entityLivingBase.posX - this.posX;
            double deltaZ = entityLivingBase.posZ - this.posZ;
            double distance = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);

            if (distance > 0.0D) {
                double normalizedX = deltaX / distance;
                double normalizedZ = deltaZ / distance;
                double knockbackForce = (1.0D - distance / 4.0D) * this.knockbackStrength;

                if (knockbackForce > 0.0D) {
                    entityLivingBase
                        .addVelocity(normalizedX * knockbackForce * 0.5D, 0.2D, normalizedZ * knockbackForce * 0.5D);
                }
            }
        }
    }
}
