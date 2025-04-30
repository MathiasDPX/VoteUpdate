package gg.gyro.voteUpdate.commands;

import dev.xernas.menulib.PaginatedMenu;
import dev.xernas.menulib.utils.ItemBuilder;
import dev.xernas.menulib.utils.StaticSlots;
import gg.gyro.voteUpdate.customitems.CloseButton;
import gg.gyro.voteUpdate.customitems.NextPage;
import gg.gyro.voteUpdate.customitems.PreviousPage;
import gg.gyro.voteUpdate.utils.TextReducer;
import gg.gyro.voteUpdate.utils.Vote;
import gg.gyro.voteUpdate.utils.Votes;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListMenu extends PaginatedMenu {
    public ListMenu(Player owner) {
        super(owner);
    }

    @Override
    public @Nullable Material getBorderMaterial() {
        return Material.GRAY_STAINED_GLASS_PANE;
    }

    @Override
    public @NotNull List<Integer> getStaticSlots() {
        return StaticSlots.BOTTOM;
    }

    @Override
    public @NotNull List<ItemStack> getItems() {
        List<ItemStack> items = new ArrayList<>();

        for (Vote vote: Votes.getVotes()) {
            ItemStack icon = vote.getIcon();

            ItemMeta meta = icon.getItemMeta();
            meta.displayName(Component.text(vote.getName()).decoration(TextDecoration.ITALIC, false));
            List<Component> lore = TextReducer.reduceText(vote.getDescription(), 25);
            lore.add(Component.empty());
            lore.add(Component.text(vote.getId()).color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false));

            meta.lore(lore);
            icon.setItemMeta(meta);

            items.add(icon);
        }

        return items;
    }

    @Override
    public Map<Integer, ItemStack> getButtons() {
        Map<Integer, ItemStack> map = new HashMap<>();
        map.put(49, new ItemBuilder(this, new CloseButton().getBest())
                .setCloseButton());
        map.put(48, new ItemBuilder(this, new PreviousPage().getBest())
                .setPreviousPageButton());
        map.put(50, new ItemBuilder(this, new NextPage().getBest())
                .setNextPageButton());
        return map;
    }

    @Override
    public @NotNull String getName() {
        return "Votes";
    }

    @Override
    public void onInventoryClick(InventoryClickEvent e) {}
}
