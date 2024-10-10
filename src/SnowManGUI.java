/*
Snow Man
 */
import javax.swing.*;
import java.awt.Dimension;

public class SnowManGUI implements Runnable
{
	private final Dimension scaledSize;
	private final String title;

	public SnowManGUI(Dimension scaledSize, String title) {
		this.scaledSize = scaledSize;
		this.title = title;
	}

	/**
	 * Runs this GUI in a separate thread.
	 */
	@Override
	public void run() {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// note the use of setPreferredSize instead of setSize, coupled with
		// frame.pack() below:
		frame.setSize(scaledSize);
		frame.setPreferredSize(scaledSize);
		SnowManPanel pnl = new SnowManPanel();
		frame.add(pnl);

		frame.pack();

		// puts the JFrame in the middle of the physical screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
