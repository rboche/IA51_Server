package ia_51.agent_server.MAS.behaviors;

public class AligningBehavior {
	float slowDownAngle;
	
	public AligningBehavior(){}
	
	public BehaviorOutput run(Vector3f target, Vector3f orientation, Float speed){
		Float angle = target.signedAngle(target, orientation);
		Float rotation;
		if(angle <= slowDownAngle){
			Float timeToTarget = slowDownAngle / speed;
			rotation = angle/timeToTarget;
		}else{
			rotation = angle *speed;
		}
		BehaviorOutput beh = new BehaviorOutput(rotation);
		return beh;
	}	
}
