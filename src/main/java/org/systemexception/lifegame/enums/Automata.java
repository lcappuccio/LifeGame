/**
 * @author leo
 * Jan 28, 2015 7:29:09 PM
 */
package org.systemexception.lifegame.enums;

public enum Automata {

	ASSIMILATION("Assimilation"),
	CONWAY("Conway's Life"),
	CORAL("Coral"),
	DRYLIFE("DryLife"),
	HIGHLIFE("HighLife"),
	LIVEFREEORDIE("Live Free or Die"),
	MAZE("Maze"),
	MOVE("Move"),
	SERVIETTES("Serviettes");

	private final String automataName;

	Automata(final String automataName) {
		this.automataName = automataName;
	}

	@Override
	public String toString() {
		return automataName;
	}
}
