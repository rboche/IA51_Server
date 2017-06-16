/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.MAS;

import ia_51.agent_server.model.Perception;
import java.util.UUID;

/**
 *
 * @author Logoosse
 */
public abstract class Agent implements Agent_int{
    protected String id;
    protected Perception vision;
    
    public void setPerception(Perception p){
        this.vision = p;
    }
    
    
    
    
    
}
