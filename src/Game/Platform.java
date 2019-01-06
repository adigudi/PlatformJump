package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import org.omg.Messaging.SyncScopeHelper;

public class Platform{
	private int x;
	private double y;
	private double yspeed;
	private int width;
	private int height;
	private Rectangle cBox = new Rectangle();
	static double tempo = 1;
	static int lastScore;
	static int finishPlat;
	public Platform(int x, int y, int w, int h, double yspeed){
		this.x = x;
		this.y = y;
		this.width = w;
		this.setHeight(h);
		this.yspeed = yspeed;
		cBox.setBounds(x, y, width, getHeight());
	}

	public void update(){
		cBox.setBounds(x, (int) y, width, getHeight());
		if(Player.x >= 1820) {
			tempo += 0.5;
		}
		y = y - yspeed*tempo;
		//System.out.println(Level2FinalGame.platform1.x);
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
		/*if(Level2FinalGame.score != lastScore && Level2FinalGame.score % 5 == 0) {
			//lastScore = Level2FinalGame.score;
			//Level2FinalGame.finish.height += 10;
			finishPlat = Level2FinalGame.finishY -= 10;
			System.out.println(Level2FinalGame.finish.x + "," + Level2FinalGame.finishY + "," + "350 ," + Level2FinalGame.finish.height);
		}*/
		
	
	}
	
	public static void resetPlatforms(Platform platform1, Platform platform2, Platform platform3, Platform platform4) {
		platform1.y = 500;
		platform2.y = 500;
		platform3.y = 500;
		platform4.y = 500;
	}
	
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		//g.fillRect(x, (int) y, width, getHeight());
		g.drawImage(FinalGamePanel.platformImg, x, (int) y, 200, 50, null);
		g.drawImage(FinalGamePanel.stoneImg, 0, 525, 350, 75, null);
		g.drawImage(FinalGamePanel.stoneImg, 1600, Level2FinalGame.finishY, 350, 75, null);
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


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
}
