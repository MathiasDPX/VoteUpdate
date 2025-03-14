package gg.gyro.voteUpdate.utils;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class TextReducer {

    public static List<Component> reduceText(String text, int maxSize) {
        String[] words = text.split(" ");
        List<Component> result = new ArrayList<>(List.of());
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (line.length() + word.length() + 1 > maxSize) {
                result.add(Component.text(line.toString().trim()));
                line = new StringBuilder();
            }

            if (!line.isEmpty()) {
                line.append(" ");
            }
            line.append(word);
        }

        if (!line.isEmpty()) {
            result.add(Component.text(line.toString().trim()));
        }

        return result;
    }
}
