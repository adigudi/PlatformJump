package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform{
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Rectangle cBox = new Rectangle();
	
	public Platform(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		cBox.setBounds(x, y, width, height);
	}
	
	public void update(){
		cBox.setBounds(x, y, width, height);
		Level2FinalGame.plat1 = Level2FinalGame.plat1 + 2;
		Level2FinalGame.plat2 = Level2FinalGame.plat2 + 4;
		Level2FinalGame.plat3 = Level2FinalGame.plat3 + 6;
		Level2FinalGame.plat4 = Level2FinalGame.plat4 + 8;
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}
	
	public Rectangle getCBox(){
		return cBox;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
