package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform{
	private int x;
	private double y;
	private int width;
	private int height;
	private Rectangle cBox = new Rectangle();
	static double tempo = 1;
	
	public Platform(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		cBox.setBounds(x, y, width, height);
	}

	
	public void update(){
		cBox.setBounds(x, (int) y, width, height);
		Level2FinalGame.platform1.y = Level2FinalGame.platform1.y - 1*tempo;
		Level2FinalGame.platform2.y = Level2FinalGame.platform2.y - 0.75*tempo;
		Level2FinalGame.platform3.y = Level2FinalGame.platform3.y - 0.5*tempo;
		Level2FinalGame.platform4.y = Level2FinalGame.platform4.y - 0.25*tempo;
		if(Level2FinalGame.platform1.y <= 0) {
			Level2FinalGame.platform1.y = 550;
		}
		if(Level2FinalGame.platform2.y <= 0) {
			Level2FinalGame.platform2.y = 550;
		}
		if(Level2FinalGame.platform3.y <= 0) {
			Level2FinalGame.platform3.y = 550;
		}
		if(Level2FinalGame.platform4.y <= 0) {
			Level2FinalGame.platform4.y = 550;
		}
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, (int) y, width, height);
		g.setColor(Color.BLUE);
		g.fillRect(0, 525, 350, 75);
		g.fillRect(1600, 525, 350, 75);
		
	}
	
	public Rectangle getCBox(){
		return cBox;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return (int) y;
	}
}
