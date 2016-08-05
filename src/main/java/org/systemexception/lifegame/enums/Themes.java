/**
 * @author leo
 * Jan 28, 2015 7:15:56 PM
 */
package org.systemexception.lifegame.enums;

public enum Themes {

	BW("B & W"),
	INVERSE("Inverse"),
	BLUE("Blue"),
	GREEN("Green"),
	RED("Red");
	
	private final String theme;

    Themes(final String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return theme;
    }
}
