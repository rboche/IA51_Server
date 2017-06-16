package ia_51.agent_server.MAS.behaviors;


import java.awt.Point;
import ia_51.agent_server.model.Perceivable;
/**
 *
 * @author JNEB
 */


public class SeekingBehavior{
	
	public SeekingBehavior(){}

	public BehaviorOutput run(Perceivable player, Perceivable ghost, Float speed){
            

            Vector3f vec = new Vector3f();
            vec.x = player.getPosition().x-ghost.getPosition().x;
            vec.z = player.getPosition().z-ghost.getPosition().z;
            vec.y = 0;
            
            vec.setLength(speed);
            System.out.println("GhostOutput :  " + vec );
            BehaviorOutput beh = new BehaviorOutput(vec);
            return beh;
	}

        
        public Vector3f mixingBehavior(Point3f player, Perceivable ghost, Float speed)
        {
            Vector3f vec = player.min(ghost.getPosition());
            
            vec.y = 0;
            return vec;
        }

}