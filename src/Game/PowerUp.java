package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerUp {
	private int x;
	private int y;
	private int w;
	private int h;
	static Rectangle cBox = new Rectangle();
	PowerUp(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h= h;
		cBox.setBounds(x, y, w, h);
	}
	public static void drawPowerUp(Graphics g) {
		g.drawImage(FinalGamePanel.PowerUpImg, Level2FinalGame.randX, Level2FinalGame.randY, 40, 80, null);
	}
	public void powerUpUpdate() {
		cBox.setBounds(Level2FinalGame.randX, Level2FinalGame.randY, 40, 80);
	}
	public Rectangle getCBox(){
		return cBox;
	}
	public static void deleteCBOX() {
		cBox.reshape(0, 0, 0,0);
	}
}
