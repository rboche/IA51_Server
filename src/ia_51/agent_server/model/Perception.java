/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Logoosse
 */
public class Perception {
    private String target;
    private LinkedList<Perceivable> objs;
    private final Perceivable body;
    
    public Perception(Perceivable myBody,String t){
        this.target=t;
        this.objs=new LinkedList<>();
        this.body=myBody;
    }

    
    public List getPerception(){
        return Collections.unmodifiableList(objs);
    }
    
    public Perceivable getBody(){
        return this.body.clone();
    }
    
    public void addPerceivable(Perceivable oc){
        this.objs.add(oc);
    }
    public String getTarget(){
        return this.target;
    }
}
