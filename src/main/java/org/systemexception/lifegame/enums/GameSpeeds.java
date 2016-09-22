/**
 * @author leo
 * Jan 28, 2015 7:37:41 PM
 */
package org.systemexception.lifegame.enums;

public enum GameSpeeds {
	
	Cheetah(10),
	Horse(60),
	Jackrabbit(210),
	Llama(360),
	Turtle(500);
	
	private final int gameSpeed;
	
	GameSpeeds(int gameSpeed) {
		this.gameSpeed = gameSpeed;
	}
	
	public int getGameSpeed() {
		return gameSpeed;
	}
}
