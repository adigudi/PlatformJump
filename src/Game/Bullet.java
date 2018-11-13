package Game;

import java.awt.Color;
import java.awt.Graphics;

import intro_to_array_lists.GamePanel;

public class Bullet {
	int speed = 10;
	public Bullet(int x, int y, int width, int height) {
		super();
		// TODO Auto-generated constructor stub
	}
	void update(){
		super.update();
		y = y - speed;
		if (y < 0) {
			isAlive = false;
		}
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		g.drawImage(GamePanel.bulletImg, x, y, width, height, null);
		
	}
	
}
