/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.MAS.behaviors;


import org.json.simple.JSONObject;

/**
 *
 * @author Logoosse
 */
public class BehaviorOutput {
    
    private String bodyID;
    private Vector3f linearMotion;
    private Float angularMotion = 0.0f;
    
    
    public BehaviorOutput(){
        this.linearMotion=new Vector3f();
    }
    
    public BehaviorOutput(Float angularMotionInput){
        this.angularMotion = angularMotionInput;
    }
    
    public BehaviorOutput(Vector3f lMotion){
        this.linearMotion=lMotion;
    }
    
    public BehaviorOutput(Vector3f lMotion, Float angMotion){
        this.linearMotion=lMotion;
        this.angularMotion=angMotion;
    }

    public String getBodyID() {
        return bodyID;
    }

    public void setBodyID(String bodyID) {
        this.bodyID = bodyID;
    }

    public Vector3f getLinearMotion() {
        return linearMotion;
    }

    public void setLinearMotion(Vector3f linearMotion) {
        this.linearMotion = linearMotion;
    }

    public Float getAngularMotion() {
        return angularMotion;
    }

    public void setAngularMotion(Float angularMotion) {
        this.angularMotion = angularMotion;
    }
    
    public JSONObject toJson(){
        JSONObject res= new JSONObject();
        res.put("id",this.bodyID );
        JSONObject linearM=new JSONObject();
        linearM.put("x", this.linearMotion.x);
        linearM.put("y", this.linearMotion.y);
        linearM.put("z", this.linearMotion.z);
        res.put("linearMotion", linearM);
        JSONObject angularM=new JSONObject();
        res.put("angularMotion", this.angularMotion);
        return res;
    }
}
