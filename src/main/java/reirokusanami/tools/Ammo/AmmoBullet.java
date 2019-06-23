package reirokusanami.tools.Ammo;

import com.google.common.collect.ImmutableList;
import jdk.nashorn.internal.ir.annotations.Immutable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import reirokusanami.Entity.EntityProjectile;
import reirokusanami.modules.ModuleTools;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ProjectileNBT;
import slimeknights.tconstruct.library.tools.ranged.ProjectileCore;

import javax.annotation.Nonnull;
import java.util.List;

public class AmmoBullet extends ProjectileCore {
    protected final List<PartMaterialType> toolBuildComponents;
    public AmmoBullet() {
        super(PartMaterialType.arrowShaft(ModuleTools.partBulletcartridge),
              PartMaterialType.extra(ModuleTools.partPropellant),
              PartMaterialType.arrowHead(ModuleTools.partWarhead));
        addCategory(Category.NO_MELEE, Category.PROJECTILE);
        toolBuildComponents = ImmutableList.of(requiredComponents[0], requiredComponents[2]);
    }

    @Override
    public float damagePotential() {
        return 3;
    }

    @Override
    public List<PartMaterialType> getToolBuildComponents() {
        return toolBuildComponents;
    }

    @Override
    public EntityProjectileBase getProjectile(@Nonnull ItemStack stack, @Nonnull ItemStack launcher, World world, EntityPlayer player, float speed, float inaccuracy, float power, boolean usedAmmo) {
        inaccuracy = ProjectileNBT.from(stack).accuracy * speed;
        return new EntityProjectile(world, player, speed, inaccuracy, power, getProjectileStack(stack, world, player, usedAmmo), launcher);
    }

    @Override
    public ProjectileNBT buildTagData(List<Material> materials) {
        ProjectileNBT NBT = new ProjectileNBT();

        ArrowShaftMaterialStats Shaft = materials.get(0).getStatsOrUnknown(MaterialTypes.SHAFT);
        HeadMaterialStats Head = materials.get(1).getStatsOrUnknown(MaterialTypes.HEAD);
        ExtraMaterialStats Extra = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);

        NBT.head(Head);
        NBT.extra(Extra);
        NBT.shafts(this, Shaft);

        NBT.durability *= 0.8f;
        return NBT;
    }
}
