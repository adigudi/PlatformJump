package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FinalGamePanel{
	static Font titleFont;
	static Font endFont;
	static Font instructionsFont;
	public static BufferedImage faceImg;
	public static BufferedImage lavaImg;
	public static BufferedImage backgroundImg;
	public static BufferedImage platformImg;
	public static BufferedImage stoneImg;
	public static BufferedImage PowerUpImg;
	int maxX = 1050;
	int startX = 50;
	final static int MENU_STATE = 0;
	static int currentState = MENU_STATE;
	

	FinalGamePanel() {
		titleFont = new Font("Helvetica Neue", Font.PLAIN, 48);
		endFont = new Font("Helvetica Neue", Font.PLAIN, 48);

	
	try {

        faceImg = ImageIO.read(this.getClass().getResourceAsStream("smile.png"));
        lavaImg = ImageIO.read(this.getClass().getResourceAsStream("lava.png"));
        backgroundImg = ImageIO.read(this.getClass().getResourceAsStream("background.png"));
        platformImg = ImageIO.read(this.getClass().getResourceAsStream("platform.png"));
        stoneImg = ImageIO.read(this.getClass().getResourceAsStream("stone.png"));
        PowerUpImg = ImageIO.read(this.getClass().getResourceAsStream("powerup.png"));

      

} catch (IOException e) {

        // TODO Auto-generated catch block

        e.printStackTrace();

}
	} 
}

	
/*
	void updateMenuState() {

	}

	void updateGameState() {
		
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.setFont(titleFont);
		g.drawString("Platform Jump", 80, 150);
	}

	 void drawGameState(Graphics g) {
		//g.drawImage(FinalGamePanel.faceImg,Player.x, Player.y, Player.width, Player.height, null);
		//Camera cam = new Camera(Player.x, 0, 1000, 600);
		//g.drawRect(Level2FinalGame.x - cam.x, 50, Level2FinalGame.WIDTH, Level2FinalGame.HEIGHT);
		//g.drawRect(Level2FinalGame.x - cam.x, 0, Level2FinalGame.WIDTH, Level2FinalGame.HEIGHT);
		g.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);
		
	}

	static void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(endFont);
		g.drawString("GAME OVER!", 80, 150);
	}
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		if (currentState == MENU_STATE) {

			drawMenuState(g);

		}
	}


}

void updateMenuState() {

}

void updateGameState() {

}

void updateEndState() {

}

void drawMenuState(Graphics g) {
	g.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);
	g.setColor(Color.BLUE);
	g.drawImage(FinalGamePanel.faceImg, 600, 220, 200, 200, null);
	// System.out.println("Image printed");
	g.setFont(titleFont);
	g.drawString("Platform Jump", 870, 300);
	g.setFont(instructionsFont);
	g.drawString("Press ENTER to play", 870, 400);
}

void drawGameState(Graphics g){
	g.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);
	g.setColor(Color.BLUE);
	g.fillRect(0, 525, 350, 75);
	g.fillRect(1600, Level2FinalGame.finishY, 350, 75);
	g.setColor(Color.RED);
	// g.fillRect(350, 550, 1250, 50);
	Level2FinalGame.p1.draw(g);
	g.setColor(Color.BLACK);
	g.setFont(instructionsFont);
	g.drawString("Score:" + Level2FinalGame.getScore(), Level2FinalGame.x, Level2FinalGame.y);
	for (Platform p : Level2FinalGame.platforms) {
		p.draw(g);
	}
	/*
	 * for (Lava l : lavas) { l.drawLava(g); }
	 */
	// if(Level2FinalGame.score != Platform.lastScore && score % 5 == 0) {
	
/*	g.drawImage(FinalGamePanel.lavaImg, 350, 550, 1250, 50, null);
	if (Level2FinalGame.getScore() % 5 == 0 && Level2FinalGame.getScore() != 0) {
		for (PowerUp p : Level2FinalGame.powerups) {
			PowerUp.drawPowerUp(g);
		}
		int newLavaIncrease = Level2FinalGame.lavaIncrease * (Level2FinalGame.getScore() / 5);
		Level2FinalGame.lavaY = 550 - newLavaIncrease;
		Level2FinalGame.lavas.remove(Level2FinalGame.lava);
		Lava lavaNew = new Lava(350, Level2FinalGame.lavaY, 1250, 50 + newLavaIncrease);
		Level2FinalGame.lavas.add(lavaNew);
		System.out.println(newLavaIncrease);
		g.drawImage(FinalGamePanel.lavaImg, 350, Level2FinalGame.lavaY, 1250, 50 + newLavaIncrease, null);
		if (Level2FinalGame.p1.getCBox().intersects(lavaNew.getCBox())) {
			currentState = 2;
		}
		if (Level2FinalGame.p1.getCBox().intersects(Level2FinalGame.powerup.getCBox())) {
			Level2FinalGame.powerups.remove(Level2FinalGame.powerup);
			Platform.resetPlatforms(Level2FinalGame.platform1, Level2FinalGame.platform2, Level2FinalGame.platform3, Level2FinalGame.platform4);
			while(Player.x > 1820)
			Platform.tempo = 1;
			}
		}
		
	}


void drawEndState(Graphics g) {
	g.setColor(Color.RED);
	g.fillRect(0, 0, 1950, 600);
	g.setFont(endFont);
	g.setColor(Color.BLACK);
	g.drawString("GAME OVER!", 800, 300);
	g.setFont(instructionsFont);
	g.drawString("Press P to go back to the main menu", 900, 400);
	g.drawString("Press ESC to exit the game", 900, 500);

}

public static void startTimer(Graphics g) {
	try {
		for (int i = 0; i <= 10; i++) {
			//System.out.println(i);
			Thread.sleep(1000);
			String time = Integer.toString(i);
			//g.drawString(time, 200, 200);
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
}

public void paintComponent(Graphics g) {
	// TODO Auto-generated method stub
	repaint();
	if (currentState == 0) {
		drawMenuState(g);
	} else if (currentState == 1) {
		drawGameState(g);
	} else if (currentState == 2) {
		drawEndState(g);
	}
}

private void repaint() {
	// TODO Auto-generated method stub
	
}

static void changeCurrentState() {
	currentState++;
}

}
*/
