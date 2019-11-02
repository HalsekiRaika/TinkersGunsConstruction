package reirokusanami.core;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import reirokusanami.event.GunToolEvent;
import slimeknights.tconstruct.library.events.ProjectileEvent;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IAmmoUser;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.tools.ranged.IAmmo;
import slimeknights.tconstruct.library.tools.ranged.ILauncher;
import slimeknights.tconstruct.library.tools.ranged.ProjectileLauncherCore;
import slimeknights.tconstruct.library.utils.AmmoHelper;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public abstract class GunCore extends ProjectileLauncherCore implements IAmmoUser, ILauncher {
    protected abstract List<Item> getAmmoItems();
    public abstract float baseProjectileDamage();
    public abstract float projectileDamageModifier();
    protected static final UUID LAUNCHER_BONUS_DAMAGE = UUID.fromString("066b8892-d2ac-4bae-ac22-26f9f91a02ee");
    protected static final UUID LAUNCHER_DAMAGE_MODIFIER = UUID.fromString("4f76565a-3845-4a09-ba8f-92a37937a7c3");
    public GunCore(PartMaterialType... requiredComponents) {
        super(requiredComponents);
        addCategory(Category.LAUNCHER);
    }

    /**
     * Accuracy is less accurate as the value is higher. </br>
     * On the other hand, if it is made smaller, it will be close to straight.
     */
    protected float baseInaccuracy(){
        return 0.5f;
    }

    protected float baseProjectileSpeed() {
        return 4.0f;
    }

    protected float baseInaccuracyRange() {
        return 1.0f;
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack _ItemStack){
        return EnumAction.NONE;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    public boolean dealDamage(ItemStack stack, EntityLivingBase player, Entity entity, float damage) {
        if (player instanceof EntityPlayer) {
            return entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), 1.0F);
        }
        return entity.attackEntityFrom(DamageSource.causeMobDamage(player), 1.0F);
    }

    @Override
    public ItemStack findAmmo(ItemStack weapon, EntityLivingBase player){
        return AmmoHelper.findAmmoFromInventory(getAmmoItems(), player);
    }

    @Nonnull
    protected ItemStack getCreativeProjectileStack() {
        return new ItemStack(Items.ARROW);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack ItemStackIn = playerIn.getHeldItem(handIn);
        if (!ToolHelper.isBroken(ItemStackIn)) {
            boolean HasAmmo = !findAmmo(ItemStackIn, playerIn).isEmpty();
            if (playerIn.capabilities.isCreativeMode || HasAmmo) {
                playerIn.setActiveHand(handIn);
                return new ActionResult<>(EnumActionResult.SUCCESS, ItemStackIn);
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, ItemStackIn);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if(ToolHelper.isBroken(stack) || !(entityLiving instanceof EntityPlayer)){
            return;
        }
        EntityPlayer player = (EntityPlayer) entityLiving;
        ItemStack ammo = findAmmo(stack, entityLiving);
        if(ammo.isEmpty() && player.capabilities.isCreativeMode) {
            return;
        }
        if(ammo.isEmpty()) {
            ammo = getCreativeProjectileStack();
        }
        ShootProjectile(ammo, stack, worldIn, player);
        StatBase statBase = StatList.getObjectUseStats(this);
        assert statBase != null;
        player.addStat(statBase);
        TagUtil.setResetFlag(stack, true);
    }

    public boolean consumeAmmo(ItemStack ammo, EntityPlayer player) {
        if(player.capabilities.isCreativeMode) {
            return false;
        }
        if(ammo.getItem() instanceof IAmmo) {
            return ((IAmmo) ammo.getItem()).useAmmo(ammo, player);
        } else {
            ammo.shrink(1);
            if (ammo.isEmpty())
            {
                player.inventory.deleteStack(ammo);
            }
            return true;
        }
    }

    public EntityArrow getProjectileEntity(ItemStack ammo, ItemStack bow, World world, EntityPlayer player, float power, float inaccuracy, float progress, boolean usedAmmo){
        if(ammo.getItem() instanceof IAmmo){
            return ((IAmmo) ammo.getItem()).getProjectile(ammo, bow, world, player, power, inaccuracy, progress, usedAmmo);
        } else if (ammo.getItem() instanceof ItemArrow) {
            EntityArrow projectile = ((ItemArrow) ammo.getItem()).createArrow(world, ammo, player);
            projectile.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, power, inaccuracy);
            if(player.capabilities.isCreativeMode){
                projectile.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
            } else if (!usedAmmo) {
                projectile.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
            }
            return projectile;
        }
        return null;
    }

    public void ShootProjectile(@Nonnull ItemStack ammoIn, @Nonnull ItemStack bow, World worldIn, EntityPlayer player) {
        float power = ItemBow.getArrowVelocity(30) * baseProjectileSpeed();
        power *= ProjectileLauncherNBT.from(bow).range;

        if (!worldIn.isRemote) {
            GunToolEvent.OnGunShoot toolEvent = GunToolEvent.OnGunShoot.fireEvent(bow, ammoIn, player, 1, baseInaccuracy());
            ItemStack ammoShoot = ammoIn.copy();
            for (int i = 0; i < toolEvent.projectileCount; i++){
                boolean usedAmmo = false;
                if(i == 0 || toolEvent.consumeAmmoPerProjectile){
                    usedAmmo = consumeAmmo(ammoIn, player);
                }
                float baseInAccuracy = toolEvent.bonusInaccuracy;
                if (i > 0) {
                    baseInAccuracy += toolEvent.getBaseInaccuracy();
                }

                baseInAccuracy *= baseInaccuracyRange();
                EntityArrow projectile = getProjectileEntity(ammoShoot, bow, worldIn, player, power, baseInAccuracy, 1, usedAmmo);
                if(projectile != null && ProjectileEvent.OnLaunch.fireEvent(projectile, bow, player)){
                    if(!player.capabilities.isCreativeMode){
                        ToolHelper.damageTool(bow, 1, player);
                    }
                    worldIn.spawnEntity(projectile);
                }
            }
        }
    }



    @Override
    public void modifyProjectileAttributes(Multimap<String, AttributeModifier> projectileAttributes, @Nullable ItemStack launcher, ItemStack projectile, float power) {
        double Damage = baseProjectileDamage() * power;
        Damage += ProjectileLauncherNBT.from(launcher).bonusDamage;
        if(Damage != 0){
            projectileAttributes.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(LAUNCHER_BONUS_DAMAGE, "Launcher bonus damege", Damage, 0));
        }
        if(projectileDamageModifier() != 0);{
            projectileAttributes.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(LAUNCHER_DAMAGE_MODIFIER, "Launcher damage modifier", projectileDamageModifier() - 1f, 1));
        }
    }
}
