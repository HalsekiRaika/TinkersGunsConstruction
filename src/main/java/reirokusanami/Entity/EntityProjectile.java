package reirokusanami.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;

import javax.vecmath.Vector3d;

public class EntityProjectile extends EntityProjectileBase {
    private static float RegexAccuracy;

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

    public static void setRegexAccuracy(float regexAccuracy) {
        RegexAccuracy = regexAccuracy;
    }

    public static float getRegexAccuracy() {
        return RegexAccuracy;
    }

    @Override
    public void shoot(double x, double y, double z, final float velocity, final float inaccuracy)
    {
        float inaccuracyA = getRegexAccuracy();
        Vector3d vector = new Vector3d(x, y, z);
        shoot(vector, velocity, inaccuracyA);
        this.motionX = vector.x;
        this.motionY = vector.y;
        this.motionZ = vector.z;
        float __float = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float) (MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(y, (double) __float) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = rotationPitch;
    }

    protected void shoot(Vector3d vector, final float velocity, final float inaccuracy)
    {
        vector.normalize();
        vector.scale((double)velocity);

        Vector3d noise = new Vector3d(this.rand.nextGaussian(), this.rand.nextGaussian(), this.rand.nextGaussian());
        noise.normalize();
        noise.cross(vector, new Vector3d(this.rand.nextGaussian(), this.rand.nextGaussian(), this.rand.nextGaussian()));
        noise.scale((double) inaccuracy);

        vector.add(noise);
    }

/*
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
*/
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
