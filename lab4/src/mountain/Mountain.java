package mountain;

import java.util.HashMap;
import fractal.*;

public class Mountain extends Fractal {
	private int length;
	private Point p1, p2, p3;
	private double dev;
	private HashMap<Side, Point> map;

	/** Creates an object that handles Mountain fractal. 
	 * @param length the length of the triangle side
	 */
	public Mountain(int length) {
		super();
		this.length = length;
		
		this.p1 = new Point(150, 500);	//vänstra punkten
		this.p2 = new Point(300, 50);	//övre punkten
		this.p3 = new Point(400, 400);	//högra punkten
		
		this.dev = 40;
		
		map = new HashMap<Side, Point>();
	}

	/**
	 * Returns the title.
	 * @return the title
	 */
	public String getTitle() {
		return "Mountain";
	}

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(turtle.getWidth() / 2.0 - length / 2.0,
				turtle.getHeight() / 2.0 + Math.sqrt(3.0) * length / 4.0);
		fractalTriangle(turtle, order, p1, p2, p3, dev);
	}

	/* 
	 * Recursive method: Draws a recursive line of the mountains triangles. 
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, Point p, Point q, Point r, double dev) {
		
		if (order == 0) {

			turtle.moveTo(p.getX(), p.getY());
			turtle.forwardTo(q.getX(), q.getY());
			turtle.forwardTo(r.getX(), r.getY());
			turtle.forwardTo(p.getX(), p.getY());

		} else {
			
			Point a = middle(p, q, dev);
			Point b = middle(q, r, dev);
			Point c = middle(r, p, dev);
			
			a = check(new Side(p, q, a), a);
			b = check(new Side(q, r, b), b);
			c = check(new Side(r, p, c), c);
			
			//första delen "vänster"
			fractalTriangle(turtle, order-1, p, a, c, dev/2);
			//andra delen "mitten"
			fractalTriangle(turtle, order-1, q, a, b, dev/2);
			//tredje delen "toppen"
			fractalTriangle(turtle, order-1, r, b, c, dev/2);
			//fjärde delen "höger"
			fractalTriangle(turtle, order-1, a, b, c, dev/2);
			
		}
	}
	
	private Point middle(Point a, Point b, double dev) {
		return new Point((a.getX() + b.getX())/2, (a.getY() + b.getY())/2 + RandomUtilities.randFunc(dev));
	}
	
	private Point check(Side s, Point m) {
		if (map.containsKey(s)) {
			return map.remove(s);
		} else {
			map.put(s, m);
			return m;
		}
	}

}
