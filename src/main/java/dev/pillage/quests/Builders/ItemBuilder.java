package dev.pillage.quests.Builders;

import dev.pillage.quests.Enums.Rarities;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemBuilder {
    private String name;
    private List<String> lore;
    private Rarities rarity;
    private Material material;
    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public Rarities getRarity() {
        return rarity;
    }

    public Material getMaterial() {
        return material;
    }

    public static class Builder {
        private final ItemBuilder builder;

        public Builder() {
            builder = new ItemBuilder();
        }

        public Builder setName(String name) {
            builder.name = name;
            return this;
        }

        public Builder setLore(List<String> lore) {
            builder.lore = lore;
            return this;
        }

        public Builder setRarity(Rarities rarity) {
            builder.rarity = rarity;
            return this;
        }

        public Builder setMaterial(Material material) {
            builder.material = material;
            return this;
        }
        public ItemStack build() {
            ItemStack item = new ItemStack(builder.material);
            item.getItemMeta().setDisplayName(builder.name);
            item.getItemMeta().setLore(builder.lore);
            return item;
        }
    }
}
