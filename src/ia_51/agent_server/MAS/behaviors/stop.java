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
public class stop {
    public stop(){}

    public BehaviorOutput run(){
        
        Vector3f vec = new Vector3f(0,0,0);
        BehaviorOutput beh = new BehaviorOutput(vec);
        return beh;
    }
}
