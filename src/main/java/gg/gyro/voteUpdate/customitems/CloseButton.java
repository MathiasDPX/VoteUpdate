package gg.gyro.voteUpdate.customitems;

import dev.lone.itemsadder.api.CustomStack;
import gg.gyro.localeAPI.Locales;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CloseButton extends CustomItem {
    private ItemStack format(ItemStack initial) {
        ItemMeta meta = initial.getItemMeta();
        meta.displayName(Component.text(Locales.get("gui.close")).decoration(TextDecoration.ITALIC, false));
        initial.setItemMeta(meta);
        return initial;
    }

    @Override
    public ItemStack getVanilla() {
        ItemStack item = new ItemStack(Material.DARK_OAK_DOOR);
        return format(item);
    }

    @Override
    public ItemStack getItemsAdder() {
        CustomStack stack = CustomStack.getInstance("_iainternal:icon_cancel");
        if (stack != null) {
            return format(stack.getItemStack());
        } else {
            return null;
        }
    }
}
