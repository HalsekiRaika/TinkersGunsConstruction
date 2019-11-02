package reirokusanami.core;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ranged.IAmmo;
import slimeknights.tconstruct.library.tools.ranged.IProjectile;
import slimeknights.tconstruct.library.tools.ranged.ProjectileCore;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.library.utils.TooltipBuilder;
import slimeknights.tconstruct.tools.traits.TraitEnderference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public abstract class BulletCore extends TinkerToolCore implements IProjectile, IAmmo {
    public static final String DAMAGE_TYPE_PROJECTILE = "arrow";
    protected int DurabilityPerAmmo;

    public BulletCore(PartMaterialType... requireComponents) {
        super(requireComponents);
        DurabilityPerAmmo = 5;
    }

    // --Bullet Handling

    protected void setDurabilityPerAmmo(int durabilityPerAmmo) {
        this.DurabilityPerAmmo = durabilityPerAmmo;
    }

    public int getDurabilityPerAmmo() {
        return DurabilityPerAmmo;
    }

    @Override
    public int getMaxAmmo(@Nonnull ItemStack stack) {
        return ToolHelper.getMaxDurability(stack) / DurabilityPerAmmo;
    }

    @Override
    public int getCurrentAmmo(ItemStack stack) {
        return ToolHelper.getCurrentDurability(stack) /DurabilityPerAmmo;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (double) (getMaxAmmo(stack) - getCurrentAmmo(stack)) / (double) getMaxAmmo(stack);
    }

    @Override
    public void setAmmo(int count, @Nonnull ItemStack stack) {
        stack.setItemDamage((getMaxAmmo(stack) - count) * DurabilityPerAmmo);
    }

    @Override
    public boolean addAmmo(@Nonnull ItemStack stack, @Nullable EntityLivingBase player) {
        int ammo = getCurrentAmmo(stack);
        if (ammo > getMaxAmmo(stack)){
            ToolHelper.healTool(stack, DurabilityPerAmmo, player);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean useAmmo(@Nonnull ItemStack stack, @Nullable EntityLivingBase player) {
        int ammo = getCurrentAmmo(stack);
        if (ammo > 0) {
            ToolHelper.damageTool(stack, DurabilityPerAmmo, player);
            int newammo = getCurrentAmmo(stack);
            if (newammo <= 0) {
                ToolHelper.breakTool(stack, player);
            }
            return newammo < ammo;
        } else {
            return false;
        }
    }

    // --Tool Stuff

    protected ItemStack getProjectileStack(ItemStack stack, World world, EntityPlayer player, boolean useAmmo) {
        ItemStack _reference = stack.copy();
        _reference.setCount(1);
        setAmmo(1, stack);

        if (!player.capabilities.isCreativeMode && !world.isRemote && !useAmmo) {
            setAmmo(0, _reference);
        }

        ToolHelper.unbreakTool(stack);
        return _reference;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        return false;
    }

    @Override
    public double attackSpeed() {
        return 10;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return false;
    }

    public boolean dealDamageRanged(ItemStack stack, Entity projectile, EntityLivingBase player, Entity entity, float damage) {
        DamageSource _damageSource = new EntityDamageSourceIndirect(DAMAGE_TYPE_PROJECTILE, projectile, player).setProjectile();
        if (entity instanceof EntityEnderman && ((EntityEnderman) entity).getActivePotionEffect(TraitEnderference.Enderference) != null) {
            _damageSource = new ProjectileCore.DamageSourceProjectileForEndermen(DAMAGE_TYPE_PROJECTILE, projectile, player);
        }
        return entity.attackEntityFrom(_damageSource, damage);
    }

    @Override
    protected String getBrokenTooltip(ItemStack stack) {
        return Util.translate(TooltipBuilder.LOC_Empty);
    }

    @Override
    public List<String> getInformation(ItemStack stack, boolean detailed) {
        TooltipBuilder info = new TooltipBuilder(stack);
        info.addAmmo(!detailed);
        info.addAttack();
        info.addAccuracy();

        if (ToolHelper.getFreeModifiers(stack) > 0) {
            info.addFreeModifiers();
        }
        if (detailed) {
            info.addModifierInfo();
        }
        return info.getTooltip();
    }

    @Nonnull
    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, ItemStack stack) {
        return this.getItemAttributeModifiers(slot);
    }
}
