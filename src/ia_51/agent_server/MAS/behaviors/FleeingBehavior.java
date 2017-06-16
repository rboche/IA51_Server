package ia_51.agent_server.MAS.behaviors;
import ia_51.agent_server.model.Perceivable;
public class FleeingBehavior{
	
	public FleeingBehavior(){}

	public BehaviorOutput run(Perceivable player, Perceivable ghost, Float speed){
                //System.out.println("FLeeing");
		Vector3f vec = ghost.getPosition().min(player.getPosition());
		vec.setLength(speed);
                vec.y=0;
		BehaviorOutput beh = new BehaviorOutput(vec);
		return beh;
	}
        
        public Vector3f mixingBehavior(Point3f player, Perceivable ghost, Float speed){
		Vector3f vec = ghost.getPosition().min(player);
		
                vec.y = 0;
		return vec;
	}

}