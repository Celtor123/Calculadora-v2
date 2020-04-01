package calculadora2;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Celso
 */
public class Servidor extends Thread{
    ServerSocket server;
    Socket socket;
    public void a(){
        try {
            server= new ServerSocket();
            InetSocketAddress a = new InetSocketAddress("localhost",5555);
            server.bind(a);
             while(true){
                 
             socket=server.accept();
             DataInputStream llegada = new DataInputStream(socket.getInputStream());
             DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
             float qw = 0;
             float y=llegada.readFloat();
             String g=llegada.readUTF();              
             float h=llegada.readFloat();
             if(g.equals("+")){
                qw=y+h;
             }
             if(g.equals("-")){
                qw=y-h;
             }
             if(g.equals("X")){
                qw=y*h;
             }
             if(g.equals("/")){
                qw=y/h;
             }
             if(g.equals("%")){
                qw=(y*h)/100;
             }
             salida.writeUTF(""+qw);
             socket.close(); 
             }        
                                    
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        a();
    }
    
     public static void main(String[] args) {
         Servidor ser= new Servidor();
         ser.start();
     }
    
}
