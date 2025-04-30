package gg.gyro.voteUpdate.listeners;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.NSKeyManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class MidasTouch implements Listener {

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        Location loc = event.getFrom().subtract(0,1,0);
        Block block = loc.getBlock();

        if (block.getType() == Material.AIR) return;
        block.setType(Material.GOLD_BLOCK);
    }

    private ItemStack changeItemType(ItemStack item, Material newType) {
        return new ItemStack(newType, item.getAmount());
    }

    private ItemStack getNewVersion(ItemStack item) {
        return switch (item.getType()) {
            case IRON_BLOCK -> changeItemType(item, Material.GOLD_BLOCK);
            case IRON_ORE, DIAMOND_ORE, COAL_ORE, REDSTONE_ORE, EMERALD_ORE, COPPER_ORE, LAPIS_ORE -> changeItemType(item, Material.GOLD_ORE);
            case DEEPSLATE_IRON_ORE, DEEPSLATE_DIAMOND_ORE, DEEPSLATE_COAL_ORE, DEEPSLATE_REDSTONE_ORE, DEEPSLATE_EMERALD_ORE, DEEPSLATE_COPPER_ORE, DEEPSLATE_LAPIS_ORE -> changeItemType(item, Material.DEEPSLATE_GOLD_ORE);
            case IRON_NUGGET -> changeItemType(item, Material.GOLD_NUGGET);
            case RAW_COPPER_BLOCK, RAW_IRON_BLOCK -> changeItemType(item, Material.RAW_GOLD_BLOCK);
            case RAW_COPPER, RAW_IRON -> changeItemType(item, Material.RAW_GOLD);
            case NETHER_QUARTZ_ORE -> changeItemType(item, Material.NETHER_GOLD_ORE);
            case LEATHER_HELMET,IRON_HELMET,DIAMOND_HELMET,NETHERITE_HELMET,TURTLE_HELMET -> changeItemType(item, Material.GOLDEN_HELMET);
            case LEATHER_CHESTPLATE,IRON_CHESTPLATE,DIAMOND_CHESTPLATE,NETHERITE_CHESTPLATE -> changeItemType(item, Material.GOLDEN_CHESTPLATE);
            case LEATHER_LEGGINGS,IRON_LEGGINGS,DIAMOND_LEGGINGS,NETHERITE_LEGGINGS -> changeItemType(item, Material.GOLDEN_LEGGINGS);
            case LEATHER_BOOTS,IRON_BOOTS,DIAMOND_BOOTS,NETHERITE_BOOTS -> changeItemType(item, Material.GOLDEN_BOOTS);
            case LEATHER_HORSE_ARMOR,IRON_HORSE_ARMOR,DIAMOND_HORSE_ARMOR -> changeItemType(item, Material.GOLDEN_HORSE_ARMOR);
            case WOODEN_SWORD,STONE_SWORD,IRON_SWORD,DIAMOND_SWORD,NETHERITE_SWORD -> changeItemType(item, Material.GOLDEN_SWORD);
            case WOODEN_SHOVEL,STONE_SHOVEL,IRON_SHOVEL,DIAMOND_SHOVEL,NETHERITE_SHOVEL -> changeItemType(item, Material.GOLDEN_SHOVEL);
            case WOODEN_PICKAXE,STONE_PICKAXE,IRON_PICKAXE,DIAMOND_PICKAXE,NETHERITE_PICKAXE -> changeItemType(item, Material.GOLDEN_PICKAXE);
            case WOODEN_AXE,STONE_AXE,IRON_AXE,DIAMOND_AXE,NETHERITE_AXE -> changeItemType(item, Material.GOLDEN_AXE);
            case WOODEN_HOE,STONE_HOE,IRON_HOE,DIAMOND_HOE,NETHERITE_HOE -> changeItemType(item, Material.GOLDEN_HOE);
            case ARROW -> changeItemType(item, Material.SPECTRAL_ARROW);
            case RAIL -> changeItemType(item, Material.POWERED_RAIL);
            case MELON_SLICE -> changeItemType(item, Material.GLISTERING_MELON_SLICE);
            case CARROT -> changeItemType(item, Material.GOLDEN_CARROT);
            case APPLE -> changeItemType(item, Material.GOLDEN_APPLE);
            default -> changeItemType(item, Material.GOLD_INGOT);
        };
    }

    @EventHandler
    void onPlayerPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Item item = event.getItem();
        item.setItemStack(getNewVersion(item.getItemStack()));
    }

    @EventHandler
    void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        event.deathMessage(Component.text(Locales.get("death_messages.midas_touch", player.displayName().toString())));
    }

    @EventHandler
    void onEntityDie(EntityDeathEvent event) {
        List<ItemStack> oldDrops = event.getDrops();
        World world = event.getEntity().getWorld();

        event.getDrops().removeAll(oldDrops);
        Location dropLoc = event.getEntity().getLocation();
        for (ItemStack item : oldDrops) {
            event.getDrops().add(getNewVersion(item));
            world.dropItemNaturally(dropLoc, getNewVersion(item));
        }
    }

    @EventHandler
    void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        if (event.getEntity() instanceof Player player) {
            player.getPersistentDataContainer().set(NSKeyManager.getIsPlayerGolden(), PersistentDataType.BOOLEAN, true);
        } else if (event.getEntity() instanceof LivingEntity entity) {
            entity.setAI(false);
        }

    }

    @EventHandler
    void onInventoryMove(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (!(event.getInventory() instanceof PlayerInventory)) return;
        if (item == null) return;
        event.setCurrentItem(getNewVersion(item));
    }
}
