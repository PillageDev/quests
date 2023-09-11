package dev.pillage.quests.Utils;

public class TextBuilder {
    public static String build(String content) {
        return TextUtils.border + "\n" + TextUtils.color(content) + "\n" + TextUtils.border;
    }
}