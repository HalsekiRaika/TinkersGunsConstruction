package reirokusanami.proxy;

import slimeknights.mantle.pulsar.pulse.Pulse;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.Modifier;

import java.util.List;

@Pulse(id = TGCModifier.PulseId, forced = true)
public class TGCModifier {
    public static final String PulseId = "TGCModifiers";

    //Modifier
    public static Modifier modBulletDance;

    public static List<Material> fortifyMods;
    public static List<Material> extraTraitMods;

}
