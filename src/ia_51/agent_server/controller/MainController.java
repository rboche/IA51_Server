/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.controller;

import ia_51.agent_server.MAS.Kernel;
import ia_51.agent_server.MAS.behaviors.BehaviorOutput;
import ia_51.agent_server.model.Perception;
import ia_51.agent_server.server.UnityServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ia_51.agent_server.MAS.behaviors.Vector3f;

/**
 *
 * @author Logoosse
 */
public class MainController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      /*  String jsonExample="{\n"
                + "\"mybody\":{\n"
                + "\"position\" :{\n"
                + "\"x\":1\n"
                +"\"y\":2\n"
                + "},\n"
                + "\"orientation\":{\n"
                + "\"x\":1\n"
                +"\"y\":2\n"
                + "},\n"
                + "\"type\":\"fantome\""
                + "\n}"
                +"\n}";
        //System.out.println(jsonExample);
        JSONParser pars=new JSONParser();
        JSONObject jObj;*/
        try {
            Kernel.getInstance().Start();
            /*jObj =(JSONObject) pars.parse(jsonExample.trim());
            float x=Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("position")).get("x").toString());
            float y=Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("position")).get("y").toString());
            /*System.out.println(x);
            System.out.println(y);
            System.out.println(((JSONObject)jObj.get("mybody")).get("type").toString());
            System.out.println("-----------------------------------------------------");
            // test serveur
           UnityServer serv= new UnityServer();
            serv.Init();
            System.out.println("ici");
            Perception p=serv.getPerception();
            System.out.println("labas");
            System.out.println(p.getBody().getPosition());*/
           
            //BehaviorOutput behav= new BehaviorOutput(new Vector3f(1,2,3), 7.0f);
            //System.out.println(behav.toJson().toJSONString());
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
