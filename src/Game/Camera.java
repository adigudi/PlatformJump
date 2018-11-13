package Game;

public class Camera {
	static int x;
	static int y;
	static int width;
	static int height;

	Camera(int x, int y, int width , int height) {
		this.x = x;
		this.y = y;
		this.width = Level2FinalGame.WIDTH;
		this.height = Level2FinalGame.HEIGHT;
	}

}
