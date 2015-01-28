/**
 * @author leo
 * Jan 28, 2015 7:29:09 PM
 */
package org.systemexception.lifegame.enums;

public enum Automata {
	
	CONWAY("Conway's Life"), DRYLIFE("DryLife"), HIGHLIFE("HighLife"), LIVEFREEORDIE("Live Free or Die"), 
	MAZE("Maze"), SERVIETTES("Serviettes"), CORAL("Coral"), MOVE("Move");
	
	private final String automata;

    private Automata(final String automata) {
        this.automata = automata;
    }

    @Override
    public String toString() {
        return automata;
    }
}
