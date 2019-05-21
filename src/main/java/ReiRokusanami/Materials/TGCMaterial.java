package ReiRokusanami.Materials;

import slimeknights.tconstruct.library.materials.Material;

public class TGCMaterial extends Material {
    public static boolean Registered = false;
    public TGCMaterial(String Text, int TextColor){
        super(Text, TextColor);
    }

    public boolean isRegistered() {
        if(Registered) {
            return true;
        }
        return false;
    }

    public void setRegistered() {
        Registered = true;
    }
}
