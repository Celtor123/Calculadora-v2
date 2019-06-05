
package calculadora2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Servidor implements Runnable{
      //Creando socket servidor
            private int puerto;
            private static ServerSocket serverSocket;
            private static Socket newSocket;
            private static InputStream is;
            private static OutputStream os;
          private boolean running = true;
          public int clientes=0;
    public Servidor(){
              
    }
    public void start() {
        running = true;
        puerto=Integer.parseInt(JOptionPane.showInputDialog(null,"Meta o número de porto:"));
        new Thread(this).start();
    }
    
    public void a(){
                try {                    
                    
                    
                    System.out.println("r");
                    serverSocket = new ServerSocket();
                    InetSocketAddress addr=new InetSocketAddress("localhost",puerto);
                    serverSocket.bind(addr);
                    
                    while(running=true){
                    if(clientes<=0){
                        running=false;
                    }    
                    newSocket= serverSocket.accept();
                    clientes++;
                    System.out.println("Hay unos"+clientes+"clientes conectados");
                    is=newSocket.getInputStream();
                    os=newSocket.getOutputStream();
                    double resultado = 0;
                    double as,ar;
                    String p,x,g,hy = null;
                    byte[] mensaje1=new byte[14];
                    byte[] mensaje2=new byte[14];
                    byte[] mensaje3=new byte[14];
                    is.read(mensaje1);
                    os.write("Recibido".getBytes());
                    is.read(mensaje2);
                    os.write("Recibido2".getBytes());
                    is.read(mensaje3);
                    os.write("Recibido3".getBytes());
                    g= new String(mensaje1);
                    as=Double.parseDouble(g);
                    System.out.println("Mensaje recibido: "+g);
                    p= new String(mensaje2);
                    System.out.println("Mensaje recibido: "+p);
                    x= new String(mensaje3);
                    ar=Double.parseDouble(x);
                    System.out.println("Mensaje recibido: "+x);
                    if(p.contains("Sumar")){
                        resultado=as+ar;
                        hy=resultado+"";
                    }
                    else if(p.contains("Restar")){
                        resultado=as-ar;
                        hy=resultado+"";
                    }
                    else if(p.contains("Multiplicar")){
                        resultado=as*ar;
                        hy=resultado+"";
                    }
                    else if(p.contains("Dividir")){
                        if(ar==0){
                            System.out.println("Division por cero infinito");
                            hy="La division entre cero no es una operacion aceptada, ERROR LÓGICO";
                            if(as==0){
                                System.out.println("división entre ceros");
                                hy="La division entre ceros da un resultado nulo";
                            }
                        }
                        else{
                            resultado=as/ar;
                            hy=resultado+"";
                        }
                    }
                    else if(p.contains("Raíz")){
                        if(as<0){
                            System.out.println("El numero es negativo y por lo tanto es imposible su operación");
                            resultado=as;
                            hy="El error es en la raíz del numero: "+resultado+"\n disculpe las molestias";
                        }else{
                            resultado=Math.sqrt(as);
                            hy=resultado+"";
                        }
                    }
                    if(x.contains("exit")){
                        running=false;
                    }
                        
                    System.out.println(hy);
                    os.write(hy.getBytes());
                    newSocket.close();
                } 
    }           
                catch(java.net.SocketException r){
                    running=false;
                }
                catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    running=false;
                }
                
    }
    public void run(){
           

    
        a();
        
      

}
    public static void main(String[] args)  {
     Servidor a= new Servidor();
                    a.start();
                
                   
     }

    }

