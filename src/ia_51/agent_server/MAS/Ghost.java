package ia_51.agent_server.MAS;
import ia_51.agent_server.model.Perceivable;
import ia_51.agent_server.MAS.behaviors.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JNEB
 */
public  class Ghost extends Agent {
    
    
    Perceivable player = null;
    Perceivable ghost;
    boolean playerInVision;
    int playerInVisionI;
    
    // GHOST RELATED INFORMATION
        // --- SPEED ---
    float speed = 5f;
    // --- MAX ROTATION ---
    float maxRotation = 500;
    
    // --- DISTANCE FROM PLAYER TO GHOST --- //
    Vector3f vectorPG = null;
    float distance = 0.0f;
    float distanceMax = 20;
    
    // --- ANGLE FROM PLAYER TO GHOST --- //
    float angle = 0.0f;
    float angleMax = 180;
    float playerAngleView = 100f;
    
    
    // --- INITIALIZING ALL THE BEHAVIORS --- //
    WanderingBehavior wanders;
    SeekingBehavior seeks;
    SeekingBehavior attractionAa, attractionBb;
    FleeingBehavior repulsion;
    FleeingBehavior flees;
    AligningBehavior aligns;
    stop stopnow;
    PassbehindBehavior passB;
    
    // --- INITIALIZING ALL THE COEFFICIENTS --- //
    float alpha1=0f;
    float alpha2=0f;
    float alpha3=0f;
    
    BehaviorOutput beh = null;
    
    public Ghost(String id){
        this.id=id;
    }   
    
    
    public void die(){
        
    }
    
    public BehaviorOutput mixingBehaviors(WanderingBehavior wander, 
            SeekingBehavior seeks, FleeingBehavior flees)
    {
    
    return this.beh;
    }
   
    
    public void run(){
        // get the collection of perceivable objects
        this.ghost=vision.getBody();
        
        List<Perceivable> a = vision.getPerception();
        // compare types so we can find the player
        for(Perceivable i : a){
            if("player".equals(i.getType()))
            {
                playerInVision = true;
                player = i;
            }
            else
                playerInVision = false;
        }
         // if the ghost CAN'T see the player -> WANDERING BEHAVIOR
        if(playerInVision == false )
        {
            this.wanders = new WanderingBehavior();
            this.beh = wanders.run(ghost, speed, maxRotation);
            
        }
        
        // if the ghost CAN see the player -> weird & mixed behavior
        else if(playerInVision == true )
        {
            // get the vector3f between ghost to player
            this.vectorPG = ghost.getPosition().min(player.getPosition());
            this.vectorPG.y=0;
            // Retrieving ANGLE & DISTANCE
            float angle =  this.vectorPG.getOwnOrientation();
            this.angle = this.player.getOrientation().y - angle;
            this.distance = this.vectorPG.length();
            // Retrieving the point directly behind the player
            
            
            // initializing all the behaviors
            this.seeks = new SeekingBehavior();
            this.stopnow = new stop();
            this.flees = new FleeingBehavior();
            
           // Computing the coeffs
           //   alpha1 --> Attraction toward the pointAdynamic
           //   alpha2 --> Attraction toward the player itself
           //   alpha3 --> Repulsion from the player itself
           
           if(this.angle >= 100 || this.angle <= -100)
           {
               this.alpha1 = 0.3f;
               this.alpha3 = 0;
               this.alpha2 = ((Math.abs(this.angle)-100)/80);
           }
           else
           {
               this.alpha1 = ( (Math.abs(this.angle))/100);
               this.alpha1 = this.alpha1 + (this.distance)/distanceMax;
               this.alpha1 = this.alpha1/2;
               this.alpha1*=2;
               
               this.alpha3 = ( 1 - (Math.abs(this.angle))/100);
               this.alpha3 = this.alpha3 + (1-(this.distance)/distanceMax);
               this.alpha3 = this.alpha3/2;
               
               this.alpha2 = 0;
           }
       
           Point3f pointBstatic = new Point3f();
           float cosA = (float)Math.cos((double) this.player.getOrientation().y);
           float sinA = (float)Math.sin((double) this.player.getOrientation().y);
           Point3f pointAstatic = new Point3f(-sinA,0,-cosA);
           pointBstatic.x = pointAstatic.x * 1.5f;
           pointBstatic.z = pointAstatic.z * 1.5f;
           
           pointAstatic.x *=5;
           pointAstatic.z *=5;
           
           
           
           Point3f pointAdynamic = new Point3f(pointAstatic.x + player.getPosition().x,0,pointAstatic.z + player.getPosition().z );
           Point3f pointBdynamic = new Point3f(pointBstatic.x + player.getPosition().x,0,pointBstatic.z + player.getPosition().z );
          
           this.attractionAa = new SeekingBehavior();
           this.attractionBb = new SeekingBehavior();
           this.repulsion = new FleeingBehavior();
           
           Vector3f attractionA = this.attractionAa.mixingBehavior(pointAdynamic, ghost, speed);
           Vector3f attractionB = this.attractionBb.mixingBehavior(pointBdynamic, ghost, speed);
           Vector3f repulsionB = this.repulsion.mixingBehavior(this.player.getPosition(), ghost, speed);
           
           attractionA = attractionA.operator_time(this.alpha1);
           attractionB = attractionB.operator_time(this.alpha2);
           repulsionB = repulsionB.operator_time(this.alpha3); 

           Vector3f vectorfinal = attractionA.operator_plus(attractionB).operator_plus(repulsionB);
           System.out.println("alpha 1 "+ this.alpha1);
           System.out.println("alpha 2 "+ this.alpha2);
           System.out.println("alpha 3 "+ this.alpha3);
           System.out.println("pointB " + pointBdynamic);
           this.beh = new BehaviorOutput(vectorfinal);
        }
        
        try {
            this.beh.setBodyID(id);
            Kernel.getInstance().sendBehaviourOutput(this.beh);
        } catch (IOException ex) {
            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
    
   

