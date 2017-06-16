//package ia_51.agent_server.MAS.behaviors;
//
//public class EvadingBehavior {
//	FleeingBehavior delegate ;
//	
//	public EvadingBehavior(){}
//	
//	public BehaviorOutput run(Point3f targetP, Vector3f targetV, float targetVMax, Point3f position, float speed)
//	{
//		Vector3f d = targetP.min(position);
//		float distance = d.length();
//		
//		float time = distance / targetVMax;
//		Point3f futurePosition = addPV(targetP, targetV.toColinearVector(time));
//		
//		BehaviorOutput beh = this.delegate.run(futurePosition, position, speed);
//		return beh;
//	}
//	public Point3f addPV(Point3f t, Vector3f v){
//		Point3f ret = new Point3f(0,0,0);
//		ret.x = t.x + v.x;
//		ret.y = t.y + v.y;
//		ret.z = t.z + v.z;
//		return ret;
//	}
//}
