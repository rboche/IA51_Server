/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_51.agent_server.server;
import ia_51.agent_server.MAS.behaviors.BehaviorOutput;
import ia_51.agent_server.model.Perceivable;
import ia_51.agent_server.model.Perception;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import ia_51.agent_server.MAS.behaviors.Point3f;
import ia_51.agent_server.MAS.behaviors.Vector3f;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Logoosse
 */
public class UnityServer {
    //server
    private ServerSocket unityList;
    private Socket clientSocket;
    private final int port=1994;
    private BufferedReader in;
    private boolean isListening;
    
    //Client
    private Socket SendSocket;
    private final int Sendport=1991;
    private BufferedWriter wr;
    private boolean canSend=false;
    
    public UnityServer() throws IOException{
        this.unityList=new ServerSocket(port);
        this.isListening=false;
    }
    
    public void Init() throws IOException{
        //Listener
        System.out.println("wait for connection");
        this.clientSocket=this.unityList.accept();
        System.out.println("Client connected success !");
        this.isListening=true;
        in =
        new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        
        //Client Send
            System.out.println("send Connection to receiver");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(UnityServer.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.SendSocket=new Socket("127.0.0.1", Sendport);
            System.out.println("Connected to receiver !");
            OutputStream outstream = SendSocket.getOutputStream(); 
            OutputStreamWriter ow = new OutputStreamWriter(outstream);
            this.wr = new BufferedWriter(ow);
            this.wr.flush();
            this.canSend=true;
    }
    
    public void stop()throws IOException{
        this.clientSocket.close();
        this.isListening=false;
    }
    
    public boolean isActive(){
        return this.isListening;
    }
    
    public void sendBehaviorOutput(BehaviorOutput put) throws IOException{
        if(this.canSend){
            this.wr.write(put.toJson().toJSONString()+"\nEOJ");
            this.wr.flush();
        }
        
    }
    
    public Perception getPerception() throws IOException{
        Perception perc=null;
           
        String rec="";
        String json="";
        while ((rec = in.readLine()) != null && !rec.equals("EOJ")) {
            
            json+=rec+"\n";
        }
        if(json.equals("stop")){
            this.isListening=false;
            this.canSend=false;
            return null;
        }
        JSONParser pars=new JSONParser();
        JSONObject jObj;
        try {
            //get the obj from unity socket
            jObj =(JSONObject) pars.parse(json.trim());
            //get the body Position
            Point3f bodyPosition=new Point3f(Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("position")).get("x").toString()),
            Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("position")).get("y").toString()),
            Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("position")).get("z").toString()));;
            //get the body orientation
            Vector3f bodyOrientation= new Vector3f(Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("orientation")).get("x").toString()),
            Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("orientation")).get("y").toString()),
            Float.parseFloat(((JSONObject)((JSONObject)jObj.get("mybody")).get("orientation")).get("z").toString()));
            //get the type of body
            String BodyType=((JSONObject)jObj.get("mybody")).get("type").toString();
            String body_target=jObj.get("id").toString();
            //get the id
            perc=new Perception(new Perceivable(BodyType, bodyPosition, bodyOrientation),body_target);
            JSONArray objs=(JSONArray)jObj.get("objs");
            if(objs !=null){
            for(Object e: objs){
                JSONObject obj=(JSONObject)e;
                Point3f obj_pos=new Point3f(Float.parseFloat(((JSONObject)obj.get("position")).get("x").toString()),
                Float.parseFloat(((JSONObject)obj.get("position")).get("y").toString()),
                Float.parseFloat(((JSONObject)obj.get("position")).get("z").toString()));
                
                Vector3f obj_orientation=new Vector3f(Float.parseFloat(((JSONObject)obj.get("orientation")).get("x").toString()),
                Float.parseFloat(((JSONObject)obj.get("orientation")).get("y").toString()),
                Float.parseFloat(((JSONObject)obj.get("orientation")).get("z").toString()));
                
                String obj_type=obj.get("type").toString();
                perc.addPerceivable(new Perceivable(obj_type,obj_pos,obj_orientation));
                
            }
           }
        } catch (ParseException ex) {
            Logger.getLogger(UnityServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return perc;
    }
    
    
}