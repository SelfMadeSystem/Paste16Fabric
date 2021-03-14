package uwu.smsgamer.paste16fabric.utils;

import net.minecraft.text.*;
import net.minecraft.util.Formatting;

public class ChatUtils implements MinecraftHelper {
    public static void sendMessage(String message) {
        sendMessage(toColoredText(message));
    }

    public static void sendMessage(Text text) {
        mc.player.sendMessage(text, false);
    }

    public static Text toColoredText(String message) {
        char[] chars = message.toCharArray();
        BaseText base = new LiteralText("");
        StringBuilder sb = new StringBuilder();
        Style style = Style.EMPTY;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            BRK:
            if (c == '&') {
                char d = chars[i + 1];
                boolean set = false;
                Style cStyle = style;
                if (d == '#') {
                    int cColor = -1;
                    int curr = 1;
                    for (int j = 2; j <= 7; j++) {
                        int digit = Character.digit(chars[i + j], 16);
                        if (digit >= 0) {
                            cColor += digit * curr;
                        } else {
                            break BRK;
                        }
                        curr *= 16;
                    }
                    cStyle = style.withColor(TextColor.fromRgb(cColor));
                    set = true;
                    i += 7;
                } else if ("0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(d) > -1) {
                    cStyle = style.withFormatting(Formatting.byCode(d));
                    set = true;
                    i++;
                }
                if (set) {
                    base.append(new LiteralText(sb.toString()).setStyle(style));
                    style = cStyle;
                    sb.setLength(0);
                    continue;
                }
            }
            sb.append(c);
        }
        base.append(new LiteralText(sb.toString()).setStyle(style));
        return base;
    }
}
