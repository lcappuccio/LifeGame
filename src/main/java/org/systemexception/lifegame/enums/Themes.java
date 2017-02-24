/**
 * @author leo
 * Jan 28, 2015 7:15:56 PM
 */
package org.systemexception.lifegame.enums;

public enum Themes {

	BLUE("Blue"),
	BW("B & W"),
	GREEN("Green"),
	INVERSE("Inverse"),
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
