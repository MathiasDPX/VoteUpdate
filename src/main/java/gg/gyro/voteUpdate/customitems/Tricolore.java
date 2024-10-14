package gg.gyro.voteUpdate.customitems;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

public class Tricolore extends CustomItem {
    @Override
    public ItemStack getVanilla() {
        ItemStack banner = new ItemStack(org.bukkit.Material.WHITE_BANNER);
        BannerMeta meta = (BannerMeta) banner.getItemMeta();

        List<Pattern> patterns = new ArrayList<>();
        patterns.add(new Pattern(DyeColor.BLUE, PatternType.STRIPE_LEFT));
        patterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT));
        meta.setPatterns(patterns);

        banner.setItemMeta(meta);
        return banner;
    }

    @Override
    public ItemStack getItemsAdder() {
        CustomStack stack = CustomStack.getInstance("votes:le_tricolore");
        if (stack != null) {
            return stack.getItemStack();
        } else {
            return null;
        }
    }
}
