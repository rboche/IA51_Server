/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.MAS.behaviors;

import java.util.Random;
import ia_51.agent_server.MAS.Ghost;
import ia_51.agent_server.model.Perceivable;

/**
 *
 * @author JNEB
 */
public class WanderingBehavior {
    
    public WanderingBehavior(){}
        
    
    public BehaviorOutput run(Perceivable ghost, Float linearSpeed, Float maxRotation)
    {    

        float binomialRandom = (float)(Math.random() - Math.random());
        Vector3f orientation = ghost.getOrientation();

        orientation.setLength(linearSpeed);
        float rotation = binomialRandom * maxRotation;
       
        orientation.z = orientation.y;
        orientation.y=0;
        
        
        BehaviorOutput beh = new BehaviorOutput(orientation, rotation);

        return beh;

    }
}
