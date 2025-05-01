package gg.gyro.voteUpdate.utils;

import lombok.Getter;
import org.bukkit.NamespacedKey;

public class NSKeyManager {
    @Getter static NamespacedKey isPlayerGolden;

    @Getter static NamespacedKey craftDiscFragment;
    @Getter static NamespacedKey craftGildedBlackstone;
    @Getter static NamespacedKey craftIronHorseArmor;
    @Getter static NamespacedKey craftGoldenHorseArmor;
    @Getter static NamespacedKey craftDiamondHorseArmor;
    @Getter static NamespacedKey craftNametag;
    @Getter static NamespacedKey craftCobweb;
    @Getter static NamespacedKey craftReinforcedDeepslate;
    @Getter static NamespacedKey craftChainmailHelmet;
    @Getter static NamespacedKey craftChainmailChestplate;
    @Getter static NamespacedKey craftChainmailLeggings;
    @Getter static NamespacedKey craftChainmailBoots;

    public static void initialize() {
        isPlayerGolden = new NamespacedKey("voteupdate", "is_player_golden");

        craftDiscFragment = new NamespacedKey("voteupdate", "craft.disc_fragment");
        craftGildedBlackstone = new NamespacedKey("voteupdate", "craft.gilded_blackstone");
        craftIronHorseArmor = new NamespacedKey("voteupdate", "craft.iron_horse_armor");
        craftGoldenHorseArmor = new NamespacedKey("voteupdate", "craft.golden_horse_armor");
        craftDiamondHorseArmor = new NamespacedKey("voteupdate", "craft.diamond_horse_armor");
        craftNametag = new NamespacedKey("voteupdate", "craft.nametag");
        craftCobweb = new NamespacedKey("voteupdate", "craft.cobweb");
        craftReinforcedDeepslate = new NamespacedKey("voteupdate", "craft.reinforced_deepslate");
        craftChainmailHelmet = new NamespacedKey("voteupdate", "craft.chainmail_helmet");
        craftChainmailChestplate = new NamespacedKey("voteupdate", "craft.chainmail_chestplate");
        craftChainmailLeggings = new NamespacedKey("voteupdate", "craft.chainmail_leggings");
        craftChainmailBoots = new NamespacedKey("voteupdate", "craft.chainmail_boots");
    }
}
