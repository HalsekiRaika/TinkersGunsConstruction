package reirokusanami.materials;

import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IToolPart;

public class GunMaterial {
    public static PartMaterialType orb(IToolPart part) {
        return new PartMaterialType(part, GunMaterialTypes.ORB);
    }
}
