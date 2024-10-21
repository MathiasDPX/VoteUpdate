package gg.gyro.voteUpdate.customitems;

import dev.lone.itemsadder.api.CustomStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Baguette extends CustomItem {
    @Override
    public ItemStack getVanilla() {
        ItemStack baguette = new ItemStack(Material.BREAD);
        ItemMeta meta = baguette.getItemMeta();

        AttributeModifier attackDmg = new AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.getKey(), 4, AttributeModifier.Operation.ADD_NUMBER);
        AttributeModifier attackSpd = new AttributeModifier(Attribute.GENERIC_ATTACK_SPEED.getKey(), 1.6, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDmg);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpd);

        meta.displayName(Component.text("La Baguette").decoration(TextDecoration.ITALIC, false));

        baguette.setItemMeta(meta);
        return baguette;
    }

    @Override
    public ItemStack getItemsAdder() {
        CustomStack stack = CustomStack.getInstance("votes:la_baguette");
        if (stack != null) {
            return stack.getItemStack();
        } else {
            return null;
        }
    }
}
