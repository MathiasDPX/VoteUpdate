package gg.gyro.voteUpdate.utils;

public class TextReducer {
    public static String reduceText(String text, int maxSize) {
        if (text == null || text.isEmpty() || maxSize <= 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        String[] words = text.split("\\s+");
        int currentLineLength = 0;

        for (String word : words) {
            if (result.length() + word.length() > maxSize) {
                break;
            }

            if (currentLineLength + word.length() > maxSize) {
                result.append("\n");
                currentLineLength = 0;
            }

            if (currentLineLength > 0) {
                result.append(" ");
                currentLineLength++;
            }

            result.append(word);
            currentLineLength += word.length();
        }

        return result.toString();
    }
}
