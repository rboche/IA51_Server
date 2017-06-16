/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.model;

import ia_51.agent_server.MAS.behaviors.Point3f;
import ia_51.agent_server.MAS.behaviors.Vector3f;


/**
 *
 * @author Logoosse
 */
public class Perceivable {
    private final Point3f position;
    private final Vector3f orientation;
    private final String type;
    
    public Perceivable(){
        this.position=new Point3f();
        this.orientation=new Vector3f();
        this.type="unknown";
    }
    
    public Perceivable(String t, Point3f pos,Vector3f or){
        this.type=t;
        this.position=pos;
        this.orientation=or;
    }
    
    public Vector3f getOrientation(){
        return this.orientation;
    }
    
    public Point3f getPosition(){
        return this.position;
    }
    
    
    
    public String getType(){
        return this.type;
    }
    
    @Override
    public Perceivable clone(){
        return new Perceivable(this.type,this.position,this.orientation);
    }
}
