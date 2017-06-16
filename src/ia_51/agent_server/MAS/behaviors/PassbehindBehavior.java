/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.MAS.behaviors;

import ia_51.agent_server.model.Perceivable;

/**
 *
 * @author khoneb
 */
public class PassbehindBehavior {
    
    public PassbehindBehavior(){}
    
    
    
    public BehaviorOutput run(Perceivable player, Perceivable ghost, Float distance, Float speed)
    {    
        //System.out.println("PASSING BEHIND");
        
        float distanceMax = 20 ;
        Point3f target = new Point3f();
        Vector3f finalbis = new Vector3f();
        SeekingBehavior seeking = new SeekingBehavior();
        FleeingBehavior fleeing = new FleeingBehavior();
        
        
        // Compute the target point that is approximatly behind the Player
        target.x = ghost.getPosition().x*-1;
        target.z = ghost.getPosition().z*-1;
        target.y = 0;
        
        Vector3f seekingV = seeking.mixingBehavior(target, ghost, speed);
        Vector3f fleeingV = fleeing.mixingBehavior(player.getPosition(), ghost,speed);
        
        float alphaSeek = distance/distanceMax ;
        float alphaFlee = 1-(distance/distanceMax);
        
        seekingV = seekingV.operator_time(alphaSeek);
        fleeingV = fleeingV.operator_time(alphaFlee);
      
        finalbis.x = seekingV.x + fleeingV.x;
        finalbis.z = seekingV.z + fleeingV.z;
        
        
        BehaviorOutput beh = new BehaviorOutput(finalbis);
            
        return beh;

    }
    
    public Vector3f mixingBehavior(Perceivable player, Perceivable ghost, Float distance, Float speed)
    {
        float distanceMax = 20 ;
        Point3f target = new Point3f();
        Vector3f finalbis = new Vector3f();
        SeekingBehavior seeking = new SeekingBehavior();
        FleeingBehavior fleeing = new FleeingBehavior();
        
        
        // Compute the target point that is approximatly behind the Player
        target.x = ghost.getPosition().x*-1;
        target.z = ghost.getPosition().z*-1;
        target.y = 0;
        
        Vector3f seekingV = seeking.mixingBehavior(target, ghost, speed);
        Vector3f fleeingV = fleeing.mixingBehavior(player.getPosition(), ghost,speed);
        
        float alphaSeek = distance/distanceMax ;
        float alphaFlee = 1-(distance/distanceMax);
        
        seekingV = seekingV.operator_time(alphaSeek);
        fleeingV = fleeingV.operator_time(alphaFlee);
        

        finalbis.x = seekingV.x + fleeingV.x;
        finalbis.z = seekingV.z + fleeingV.z;
        
        finalbis.y=0;
        finalbis.setLength(speed);
        return finalbis;
    }
}
