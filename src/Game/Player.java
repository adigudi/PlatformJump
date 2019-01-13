package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;



public class Player {
	static int x;
	static int y;
	static int width;
	static int height;
	static int lavaH = 0;
	static int lavaHeight = 0;
	private Rectangle cBox = new Rectangle();
	public boolean left = false;
	public boolean right = false;
	static int xVelocity = 5;
	static int gravity = 1;
	static double yVelocity = 0;
	static int jumpPower = 20;
	private int yLimit = 500;
	boolean canJump = false;
	String point = "point.wav";

	public Player(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		cBox.setBounds(x, y, width, height);
	}

	public void jump() {
		if (canJump) {
			yVelocity -= jumpPower;
			canJump = false;
		}
	}

	public void update() {
		if (left) {
			x -= xVelocity;
		}
		if (right) {
			x += xVelocity;
		}

		yVelocity += gravity;
		y += yVelocity;

		if (y >= yLimit + 1) {
			y = yLimit + 1;
			yVelocity = 0;
			canJump = true;
		}

		cBox.setBounds(x, y, width, height);
		//Player.x = Player.x + 5;
		if (Player.x > 1820) {
			Player.x = 100;
			Level2FinalGame.y = 50;
			Platform.tempo++;
			playPoint();
			Level2FinalGame.increaseScore();
		}
		if (Player.y < -50) {
			yVelocity = 0.5;
		}
	}
	public void playPoint() {
		Level2FinalGame.sound = JApplet.newAudioClip(getClass().getResource(point));
		Level2FinalGame.sound.play();
	}

	
	public void draw(Graphics g) {
		//g.setColor(Color.BLUE);
		//g.fillRect(x, y, width, height);
		g.drawImage(FinalGamePanel.faceImg, x, y, width, height, null);
	}

	public Rectangle getCBox() {
		return cBox;
	}

	public void setYLimit(int l) {
		yLimit = l;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getYVelocity() {
		return yVelocity;
	}
}
