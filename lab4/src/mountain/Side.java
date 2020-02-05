package mountain;

public class Side {
	private Point a;
	private Point b;
	private Point mid;

	public Side(Point a, Point b, Point mid) {

		this.a = a;
		this.b = b;
		this.mid = mid;

	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public Point getMid() {
		return mid;
	}

	@Override
	public boolean equals(Object obj) {
		Side s = (Side) obj;
		return (s.a == this.a) && (s.b == this.b) || (s.b == this.a) && (s.a == this.b);
	}
	
	@Override
	public int hashCode() {
		return a.hashCode() + b.hashCode();
	}
}