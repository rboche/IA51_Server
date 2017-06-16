/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.MAS;

import ia_51.agent_server.MAS.behaviors.BehaviorOutput;
import ia_51.agent_server.model.Perception;
import ia_51.agent_server.server.UnityServer;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Logoosse
 */
public class Kernel {
    //Stat of the Mass Système
    private static Kernel instance;
    // Executor service to run agent main function;
    private final ExecutorService pool;
    
    private UnityServer serv;
    //Agents of the system
    private HashMap<String,Agent> agents;
    
    
    private Kernel() throws IOException{
        agents=new HashMap();
        pool=Executors.newCachedThreadPool();
        this.serv=new UnityServer();
    }
    
    public static Kernel getInstance() throws IOException{
        if(instance==null){
            instance=new Kernel();
        }
        return instance;
    }
    
    public void Start() throws IOException{
        this.serv.Init();
        this.run();
    }
    
    public void sendBehaviourOutput(BehaviorOutput op) throws IOException{
        this.serv.sendBehaviorOutput(op);
        
    }
    
    public Agent addAgent(String Type, String id){
        Agent res=null;
        if(Type.equals("fantome")){
            res=new Ghost(id) {};
            this.agents.put(id, res);
        }
        
        return res;
    }
    
    public void run() throws IOException{
        while(this.serv.isActive()){
            Perception perc = this.serv.getPerception();
            //System.out.println("Run");
            if(perc!=null){
                Agent a;
                if(!this.agents.containsKey(perc.getTarget())){
                    a=addAgent(perc.getBody().getType(),perc.getTarget());
                }
                else{
                    a = this.agents.get(perc.getTarget());
                }
                a.setPerception(perc);
               pool.execute(a);
            }
        }
    }   
}
