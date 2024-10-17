package gg.gyro.voteUpdate.utils;

import lombok.Getter;
import org.bukkit.NamespacedKey;

public class NSKeyManager {
    @Getter static NamespacedKey isPlayerGolden;

    public NSKeyManager() {
        isPlayerGolden = new NamespacedKey("voteupdate", "isPlayerGolden");
    }
}
