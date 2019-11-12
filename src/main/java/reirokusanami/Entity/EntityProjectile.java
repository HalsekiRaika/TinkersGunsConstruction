package reirokusanami.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;

public class EntityProjectile extends EntityProjectileBase {
    public EntityProjectile(World world) {
        super(world);
    }

    public EntityProjectile(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    public EntityProjectile(World world, EntityPlayer player, float speed, float inaccuracy, float power, ItemStack stack, ItemStack launchingStack) {
        super(world, player, speed, inaccuracy, power, stack, launchingStack);
    }



    @Override
    protected void onEntityHit(Entity entityHit) {
        super.onEntityHit(entityHit);
        if(!this.getEntityWorld().isRemote && entityHit instanceof EntityLivingBase){
            EntityLivingBase LivingBase = (EntityLivingBase)entityHit;
            //LivingBase.setArrowCountInEntity(LivingBase.getArrowCountInEntity()+1);
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float _float = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double) _float;
        y = y / (double) _float;
        z = z / (double) _float;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        x = x * (double) velocity;
        y = y * (double) velocity;
        z = z * (double) velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float __float = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(y, (double) __float) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = rotationPitch;
    }

    public EntityProjectile createProjectile(World world, EntityPlayer player, float speed, float inaccuracy, float power, ItemStack stack, ItemStack launchingStack){
        return new EntityProjectile(world, player, speed, inaccuracy, power, stack, launchingStack);
    }

    @Override
    public double getGravity() {
        return 0.002;
    }

    @Override
    public double getSlowdown() {
        return 0.0001;
    }
}
