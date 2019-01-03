package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Lava {
	private int x;
	private int y;
	private int width;
	private int height;
	private Rectangle cBox = new Rectangle();
	Lava(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		cBox.setBounds(x, y, width, height);
	}
	public void lavaUpdate() {
		cBox.setBounds(x, y, width, height);
	}
	/*public void drawLava(Graphics g){
		g.drawImage(FinalGamePanel.lavaImg, 350, Level2FinalGame.lavaY, 1250, 50 + Level2FinalGame.lavaIncrease*(Level2FinalGame.getScore() % 5), null);
	}*/
	public Rectangle getCBox(){
		return cBox;
	}
}
