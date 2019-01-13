package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ObjectManagerGame {

		static ArrayList<Platform> platforms = new ArrayList<Platform>();
		

		public void paintComponent(Graphics g) {
			Level2FinalGame.p1.draw(g);
			g.setColor(Color.BLUE);

			for (Platform p : platforms) {
				p.draw(g);
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			checkCollision();

			Level2FinalGame.p1.update();

			for (Platform p : platforms) {
				p.update();
			}
			
			repaint();

		}

		private void repaint() {
			// TODO Auto-generated method stub
			
		}

		private boolean checkCollision() {
				if (Level2FinalGame.p1.getCBox().intersects(Level2FinalGame.platform1.getCBox())) {
					handleCollision(Level2FinalGame.platform1);
					return true;
				}
				if (Level2FinalGame.p1.getCBox().intersects(Level2FinalGame.platform2.getCBox())) {
					handleCollision(Level2FinalGame.platform2);
					return true;
				}
				if (Level2FinalGame.p1.getCBox().intersects(Level2FinalGame.platform3.getCBox())) {
					handleCollision(Level2FinalGame.platform3);
					return true;
				}
				if (Level2FinalGame.p1.getCBox().intersects(Level2FinalGame.platform4.getCBox())) {
					handleCollision(Level2FinalGame.platform4);
					return true;
				}

			Level2FinalGame.p1.setYLimit(500);
			return false;
		}

		private void handleCollision(Platform p) {
			if (Level2FinalGame.p1.getYVelocity() >= 0 && Level2FinalGame.p1.getY() + Level2FinalGame.p1.getHeight() < p.getY() + 25) {
				Level2FinalGame.p1.setYLimit(p.getY() - Level2FinalGame.p1.getHeight());
			} else {
				Level2FinalGame.p1.setYLimit(500);
			}
		}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Level2FinalGame.p1.left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Level2FinalGame.p1.right = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				Level2FinalGame.p1.jump();
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Level2FinalGame.timer.stop();
				System.exit(0);
			}
			//System.out.println(Player.x);
			if(Player.x > 890) {
				Player.x = 0;
			}
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				Level2FinalGame.p1.left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				Level2FinalGame.p1.right = false;
			}
		}
	
	}
