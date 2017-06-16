package ia_51.agent_server.MAS.behaviors;

public class Point2f {
	float x;
	float y;
	
	public Point2f(float x, float y){
		this.x = x;
		this.y = y;
	}
	public Vector2f min(Point2f point){
		float xa = this.x - point.x;
		float ya = this.y - point.y;
		
		Vector2f vec = new Vector2f(xa,ya);
		return vec;
	}
}
