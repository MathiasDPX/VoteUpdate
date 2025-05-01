package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.NSKeyManager;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class Uncraftable extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CRAFTING_TABLE);
    }

    @Override
    public String getName() {
        return Locales.get("options.uncraftable.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.uncraftable.description");
    }

    @Override
    public void apply() {
        Server server = VoteUpdate.getInstance().getServer();

        // Disc 5 Fragment
        ShapelessRecipe recipeDiscFragment = new ShapelessRecipe(NSKeyManager.getCraftDiscFragment(), new ItemStack(Material.DISC_FRAGMENT_5, 9));
        recipeDiscFragment.addIngredient(1, Material.MUSIC_DISC_5);

        // Gilded Blackstone
        ShapedRecipe recipeGildedBlackstone = new ShapedRecipe(NSKeyManager.getCraftGildedBlackstone(), new ItemStack(Material.GILDED_BLACKSTONE, 1));
        recipeGildedBlackstone.shape("GGG", "GBG", "GGG");
        recipeGildedBlackstone.setIngredient('G', Material.GOLD_NUGGET);
        recipeGildedBlackstone.setIngredient('B', Material.BLACKSTONE);

        // Cobweb
        ShapedRecipe recipeCobweb = new ShapedRecipe(NSKeyManager.getCraftCobweb(), new ItemStack(Material.COBWEB, 1));
        recipeCobweb.shape("S S"," S ","S S");
        recipeCobweb.setIngredient('S', Material.STRING);

        // Reinforced Deepslate
        ShapedRecipe recipeReinforcedDeepslate = new ShapedRecipe(NSKeyManager.getCraftReinforcedDeepslate(), new ItemStack(Material.REINFORCED_DEEPSLATE, 1));
        recipeReinforcedDeepslate.shape("BTB", "DDD", "BTB");
        recipeReinforcedDeepslate.setIngredient('B', Material.BONE_BLOCK);
        recipeReinforcedDeepslate.setIngredient('T', Material.DEEPSLATE_TILES);
        recipeReinforcedDeepslate.setIngredient('D', Material.DEEPSLATE);

        // Nametag
        ShapedRecipe recipeNametag = new ShapedRecipe(NSKeyManager.getCraftNametag(), new ItemStack(Material.NAME_TAG, 1));
        recipeNametag.shape("  S", " P ", "P  ");
        recipeNametag.setIngredient('S', Material.STRING);
        recipeNametag.setIngredient('P', Material.PAPER);

        // Chainmail armor
        ShapedRecipe recipeChainmailHelmet = new ShapedRecipe(NSKeyManager.getCraftChainmailHelmet(), new ItemStack(Material.CHAINMAIL_HELMET, 1));
        ShapedRecipe recipeChainmailChestplate = new ShapedRecipe(NSKeyManager.getCraftChainmailChestplate(), new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
        ShapedRecipe recipeChainmailLeggings = new ShapedRecipe(NSKeyManager.getCraftChainmailLeggings(), new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
        ShapedRecipe recipeChainmailBoots = new ShapedRecipe(NSKeyManager.getCraftChainmailBoots(), new ItemStack(Material.CHAINMAIL_BOOTS, 1));

        recipeChainmailHelmet.shape("CCC", "C C","   ");
        recipeChainmailChestplate.shape("C C", "CCC","CCC");
        recipeChainmailLeggings.shape("CCC", "C C","C C");
        recipeChainmailBoots.shape("   ", "C C","C C");

        recipeChainmailHelmet.setIngredient('C', Material.CHAIN);
        recipeChainmailChestplate.setIngredient('C', Material.CHAIN);
        recipeChainmailLeggings.setIngredient('C', Material.CHAIN);
        recipeChainmailBoots.setIngredient('C', Material.CHAIN);

        // Horse armors
        ShapedRecipe recipeIronHorseArmor = new ShapedRecipe(NSKeyManager.getCraftIronHorseArmor(), new ItemStack(Material.IRON_HORSE_ARMOR, 1));
        ShapedRecipe recipeGoldenHorseArmor = new ShapedRecipe(NSKeyManager.getCraftGoldenHorseArmor(), new ItemStack(Material.GOLDEN_HORSE_ARMOR, 1));
        ShapedRecipe recipeDiamondHorseArmor = new ShapedRecipe(NSKeyManager.getCraftDiamondHorseArmor(), new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1));

        recipeIronHorseArmor.shape("  I", "III","IWI");
        recipeGoldenHorseArmor.shape("  G", "GGG","GWG");
        recipeDiamondHorseArmor.shape("  D", "DDD","DWD");

        recipeIronHorseArmor.setIngredient('I', Material.IRON_INGOT);
        recipeGoldenHorseArmor.setIngredient('G', Material.GOLD_INGOT);
        recipeDiamondHorseArmor.setIngredient('D', Material.DIAMOND);

        recipeIronHorseArmor.setIngredient('W', Material.WHITE_WOOL);
        recipeGoldenHorseArmor.setIngredient('W', Material.WHITE_WOOL);
        recipeDiamondHorseArmor.setIngredient('W', Material.WHITE_WOOL);


        // Register all recipes
        server.addRecipe(recipeIronHorseArmor);
        server.addRecipe(recipeGoldenHorseArmor);
        server.addRecipe(recipeDiamondHorseArmor);
        server.addRecipe(recipeChainmailHelmet);
        server.addRecipe(recipeChainmailChestplate);
        server.addRecipe(recipeChainmailLeggings);
        server.addRecipe(recipeChainmailBoots);
        server.addRecipe(recipeNametag);
        server.addRecipe(recipeReinforcedDeepslate);
        server.addRecipe(recipeCobweb);
        server.addRecipe(recipeGildedBlackstone);
        server.addRecipe(recipeDiscFragment);
    }
}
