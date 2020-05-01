
import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
GamePanel a;
	public static void main(String[] args) {

		LeagueInvaders e = new LeagueInvaders();
		
		e.setup();

	}

	 LeagueInvaders() {
		frame = new JFrame();
a = new GamePanel();
	}

	void setup() {
		frame.add(a);
		frame.addKeyListener(a);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
