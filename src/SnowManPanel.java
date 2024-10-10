// Computer Math - Unit 2A - Lab - Snow Man
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class SnowManPanel extends JPanel
{

	/**
	 * Draws the snowman on this JPanel.
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// get current size of parent container (because it may have been resized)
		Container parent = getParent();
		
		// set our size to be the same as our parent container
		setSize(parent.getWidth(), parent.getHeight());

		fillBackground(g);
		drawGround(g);
		drawHead(g);
		drawMiddle(g);
		drawBottom(g);
		drawHat(g);
	}

	/**
	 * Draw arms on the snowman.
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawArms(Graphics g)
	{
		Rectangle location = getMiddleLocation();
		int armLength = (int) (Math.min(location.height, location.width) * 0.5);

		// calculate position and length of left arm
		int x1 = location.x;
		int y1 = location.y + (int) (location.height / 2);
		int x2 = x1 - armLength;
		int y2 = y1 - armLength;

		Color COLOR_BROWN = new Color(139, 69, 19);
		g.setColor(COLOR_BROWN);

		// make arms thick
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(12));

		// draw left arm
		g.drawLine(x1, y1, x2, y2);

		// calculate position and length of right arm
		x1 = location.x + location.width;
		// y1 stays same
		x2 = x1 + armLength;
		// y2 stays same

		// draw right arm
		g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * Draw the bottom piece of the snowman.
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawBottom(Graphics g)
	{
		Rectangle location = getBottomLocation();
		g.setColor(Color.WHITE);
		fillOvalCentered(location.y, location.width, location.height, g);
	}

	/**
	 * Draw buttons on the middle part of the snowman.
	 * 
	 * @param middle - Rectangle containing the location of the middle of the
	 *            snowman.
	 * @param g - Graphics object we are drawing on
	 */
	private void drawButtons(Rectangle middle, Graphics g)
	{
		// calculate size and position of buttons
		int width = (int) (middle.width / 7.0);
		int height = width;

		g.setColor(Color.BLACK);
		// draw as many button as will fit comfortably on the middle of the snowman
		int y = (int) (middle.y + middle.height / 10.0);
		int yLimit = y + middle.height;
		int buttonSpacing = (int) (1.75 * height);
		for (; y < yLimit; y += buttonSpacing)
		{
			fillOvalCentered(y, width, height, g);
		}
	}

	/**
	 * Draws a message on the screen, centered horizontally.
	 * 
	 * @param g - Graphics object we are drawing on
	 * @param message - the String containing the message to display
	 * @param y - vertical position of where to draw the message.
	 */
	private void drawCenteredMessage(Graphics g, String message, int y)
	{
		FontMetrics fontMetrics = g.getFontMetrics();
		int textWidth = fontMetrics.stringWidth(message);
		// center the message horizontally
		int x = getWidth() / 2 - (textWidth / 2);
		int textHeight = fontMetrics.getHeight();
		// determine the line number
		y -= textHeight;// (int) (textHeight * lineNumber + (getHeight() * 0.01));
		g.drawString(message, x, y);
	}

	/**
	 * Draw eyes on the snowman's face
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawEyes(Graphics g)
	{
		// get position of the head
		Rectangle location = getHeadLocation();
		int y = location.y;
		int width = location.width;
		int height = location.height;

		// calculate left eye position
		int xCenter = (int) (getWidth() / 2.0);
		int xOffset = (int) (width * 1 / 4.0);
		int x = xCenter - xOffset;
		int yOffset = (int) (height * 1 / 3.0);
		int yEye = y + yOffset;

		// define size of each eye
		final int eyeWidth = (int) (width * 0.10);
		final int eyeHeight = (int) (height * 0.10);

		// draw left eye
		g.setColor(Color.BLACK);
		g.fillOval(x, yEye, eyeWidth, eyeHeight);

		// calculate right eye position
		x = xCenter + xOffset;

		// draw right eye
		g.fillOval(x, yEye, eyeWidth, eyeHeight);
	}

	/**
	 * Draw the ground beneath the snowman and a message.
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawGround(Graphics g)
	{
		g.setColor(Color.WHITE);
		int x = 0;
		// make height of the ground proportional to JPanel's height
		int height = (int) (getHeight() * 0.15);
		int y = getHeight() - height;
		int width = getWidth();
		g.fillRect(x, y, width, height);

		// add a message
		y += height * 0.75;
		g.setColor(Color.BLUE);
		int fontSize = (int) (height * 0.2);
		g.setFont(new Font("Helvetica", Font.BOLD | Font.ITALIC | Font.TRUETYPE_FONT, fontSize));
		drawCenteredMessage(g, "Do you want to build a snowman?", y);
	}

	/**
	 * Draw a hat on the snowman
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawHat(Graphics g)
	{
		// find out where the head is located
		Rectangle location = getHeadLocation();

		// calculate size and position of bottom part of hat
		// relative to the head
		int width = (int) (location.width * 1.25);
		int x = (int) (location.x - width / 2.0);
		int height = (int) (location.height / 4.0);
		int y = (int) (location.y - height * 2 / 3.0);

		// draw bottom portion of hat
		g.setColor(Color.BLACK);
		fillRoundedRectCentered(y, width, height, g);

		// calculate size and position of top part of hat
		x = (int) (location.x - width / 3.0);
		width = (int) (location.width * 0.667);
		height = (int) (location.height / 1.5);
		y = (int) (y - height * 2 / 3.0);

		// draw top portion of hat
		g.setColor(Color.BLACK);
		fillRoundedRectCentered(y, width, height, g);
	}

	/**
	 * Draw the snowman's head.
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawHead(Graphics g)
	{
		// location of where the head should go
		Rectangle location = getHeadLocation();

		// draw the head
		g.setColor(Color.white);
		fillOvalCentered(location.y, location.width, location.height, g);

		// draw the eyes
		drawEyes(g);

		// draw a nice smile
		drawSmile(g);

		// draw the nose
		drawNose(g);
	}

	/**
	 * Draw the middle part of the snowman
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawMiddle(Graphics g)
	{
		// get location where the middle should go
		Rectangle location = getMiddleLocation();

		// draw the middle
		g.setColor(Color.WHITE);
		fillOvalCentered(location.y, location.width, location.height, g);

		// draw buttons
		drawButtons(location, g);

		// draw arms
		drawArms(g);
	}

	/**
	 * Draw the snowman's nose.
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawNose(Graphics g)
	{
		// get location of the head
		Rectangle location = getHeadLocation();

		// nose should start half way down the head
		int yNoseStart = location.y + (int) (location.height / 2.0);
		int xNoseStart = location.x + (int) (location.width / 2.0);
		int noseWidth = (int) (location.width * 0.1);
		int noseLength = (int) (location.height * 0.25);
		// make the nose go sideways proportionally to the nose length
		int xNoseOffset = (int) (noseLength * 0.5);

		final int NUM_POINTS = 3;
		int[] xs = new int[NUM_POINTS];
		int[] ys = new int[NUM_POINTS];

		// calculate bottom left corner of nose
		int idx = 0;
		Point pBottomLeft = new Point(xNoseStart - xNoseOffset, yNoseStart + noseLength);
		xs[idx] = pBottomLeft.x;
		ys[idx] = pBottomLeft.y;

		// calculate top left of nose
		idx++;
		Point bTopLeft = new Point(xNoseStart - noseWidth / 2, yNoseStart);
		xs[idx] = bTopLeft.x;
		ys[idx] = bTopLeft.y;

		// calculate top right of nose
		idx++;
		Point bTopRight = new Point(xNoseStart + noseWidth / 2, yNoseStart);
		xs[idx] = bTopRight.x;
		ys[idx] = bTopRight.y;

		// draw the nose
		g.setColor(Color.ORANGE);
		g.fillPolygon(xs, ys, NUM_POINTS);
	}

	/**
	 * Draw a smile on the snowman
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void drawSmile(Graphics g)
	{
		// get position of head
		Rectangle location = getHeadLocation();

		// calculate where the smile should be drawn
		int x = (int) (getWidth() / 2.0); // center horizontally to start
		int y = (int) (location.height - (location.height * 0.035));
		int width = (int) (location.width * 3.0 / 4);
		// adjust x starting point based on arc size to keep it centered
		x -= (width / 2.0);
		int height = (int) (width * 2.0 / 3);

		// make lines thicker
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(7));

		// calculate the angle and length of the smile arc
		int startAngle = 180 + 40;
		int endAngle = 360 - 40;
		int arcAngle = endAngle - startAngle + 1;

		// draw the smile arc
		g.setColor(Color.BLACK);
		g.drawArc(x, y, width, height, startAngle, arcAngle);
	}

	/**
	 * Fill the background of the JPanel to make it look like the sky.
	 * 
	 * @param g - Graphics object we are drawing on
	 */
	private void fillBackground(Graphics g)
	{
		final Color SKY_BLUE = new Color(135, 206, 235);
		g.setColor(SKY_BLUE); // sky blue
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(getX(), getY(), SKY_BLUE, 0, getHeight(), Color.white);
		g2.setPaint(gp);   
		g.fillRect(getX(), getY(), getWidth(), getHeight());		
	}

	/**
	 * Draw an oval in the center of the JPanel
	 * 
	 * @param y - where along the y-axis to draw the oval. This is the upper-left
	 *            corner.
	 * @param width - width of the shape
	 * @param height - height of the shape
	 * @param g - Graphics object we are drawing on
	 */
	private void fillOvalCentered(int y, int width, int height, Graphics g)
	{
		// left corner X = center of JPanel minus half width of shape
		int x = this.getWidth() / 2 - width / 2;

		g.fillOval(x, y, width, height);
	}

	/**
	 * Draw a rectangle in the center of the JPanel
	 * 
	 * @param y - where on the y-axis to draw the rectangle. This is the upper-left
	 *            corner.
	 * @param width - width of the shape
	 * @param height - height of the shape
	 * @param g - Graphics object we are drawing on
	 */
	private void fillRectCentered(int y, int width, int height, Graphics g)
	{
		// left corner X = center of JPanel minus half width of shape
		int x = this.getWidth() / 2 - width / 2;

		g.fillRect(x, y, width, height);
	}

	/**
	 * Draw a rectangle in the center of the JPanel with rounded edges
	 * 
	 * @param y - position on the y-axis. This is the upper left corner.
	 * @param width - width of the shape
	 * @param height - height of the shape
	 * @param g - Graphics object we are drawing on
	 */
	private void fillRoundedRectCentered(int y, int width, int height, Graphics g)
	{
		// left corner X = center of JPanel minus half width of shape
		int x = this.getWidth() / 2 - width / 2;

		// switch to 2D graphics mode
		Graphics2D g2 = (Graphics2D) g;
		// calculate arc width/height as percentage of rectangle's width/height
		int arcWidth = (int) (width * 30.0 / 100);
		int arcHeight = (int) (height * 30.0 / 100);

		// draw the rounded rectangle
		g2.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
	}

	/**
	 * @return - Rectangle containing the location of where the bottom should be
	 *         drawn
	 */
	private Rectangle getBottomLocation()
	{
		// start with information about the part that sits on top of us
		Rectangle location = getMiddleLocation();

		// calculate proportional height and width based on JPanel's height and width
		int height = (int) (location.height * 1.5);
		int width = (int) (height * 1.25);
		int y = location.y + location.height - 10;
		int x = this.getWidth() / 2 - width / 2;
		return new Rectangle(x, y, width, height);
	}

	/**
	 * @return - Rectangle containing the location of where the head should be drawn
	 */
	private Rectangle getHeadLocation()
	{
		// use proportions of the JPanel's height and width to determine where
		// the head should be located
		int height = (int) (getHeight() * 0.15);
		int width = (int) (height * 1.2);
		int y = (int) (getHeight() * 0.12);
		int x = this.getWidth() / 2 - width / 2;
		return new Rectangle(x, y, width, height);
	}

	/**
	 * @return - Rectangle containing the location of where the middle should be
	 *         drawn
	 */
	private Rectangle getMiddleLocation()
	{
		// start with information about the part that sits on top of us
		Rectangle location = getHeadLocation();

		// scale the middle to be proportionally larger than the head
		int height = (int) (location.height * 1.6667);
		int width = (int) (height * 1.2);

		// make sure middle slightly overlaps the head
		int y = location.y + location.height - 10;
		int x = this.getWidth() / 2 - width / 2;

		return new Rectangle(x, y, width, height);
	}

}
