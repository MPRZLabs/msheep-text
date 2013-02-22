package mprz.textline;

import org.fusesource.jansi.Ansi.Color;

/**
 *
 * @author michcioperz <michcioperz@gmail.com>
 * @deprecated 
 * @see mSheep
 */
public class Colors {
    public String color(String text, int color) {
        return "\u001B[" + color + "m" + text + "\u001B[" + Color.WHITE.fg() + "m";
    }
    public String color(String text, Color color) {
        return color(text, color.fg());
    }
}
