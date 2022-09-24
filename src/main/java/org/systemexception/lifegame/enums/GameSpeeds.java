/**
 * @author leo
 * Jan 28, 2015 7:37:41 PM
 */
package org.systemexception.lifegame.enums;

public enum GameSpeeds {

	NEUTRINO(1),
	CHEETAH(10),
	HORSE(60),
	JACKRABBIT(210),
	LLAMA(360),
	TURTLE(500);
	
	private final int gameSpeed;
	
	GameSpeeds(int gameSpeed) {
		this.gameSpeed = gameSpeed;
	}
	
	public int getGameSpeed() {
		return gameSpeed;
	}
}
