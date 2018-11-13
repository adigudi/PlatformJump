package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform{
	private int x;
	private int y;
	private int width;
	private int height;
	static int plat1 = 475;
	static int plat2 = 475;
	static int plat3 = 475;
	static int plat4 = 475;
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
		/*plat1 = plat1 - 2;
		plat2 = plat2 - 4;
		plat3 = plat3 - 6;
		plat4 = plat4 - 8;*/
		//System.out.println("Moving");
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLUE);
		g.fillRect(0, 525, 350, 75);
		g.fillRect(1600, 525, 350, 75);
		plat1 = plat1 - 2;
		plat2 = plat2 - 4;
		plat3 = plat3 - 6;
		plat4 = plat4 - 8;
		
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
