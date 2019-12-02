package reirokusanami.tools.modifier;

import reirokusanami.handler.TGCModifierRegister;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.Modifier;

import java.util.List;

import static reirokusanami.modules.ModuleItems.itemMagazine;

public class TGCModifier {
    //Modifier
    public static Modifier modBulletDance;

    public static List<Material> fortifyMods;
    public static List<Material> extraTraitMods;

    public static void setupModifier() {
        modBulletDance = new ModBulletDance();
        modBulletDance.addItem(itemMagazine);
        TGCModifierRegister.registerModifier(modBulletDance);
    }
}
