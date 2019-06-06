
package calculadora2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente implements Runnable{
    private static String enderezo;
    private static int porto;
    static boolean fin=false;
    static Socket clienteSocket;
    public Cliente() {
    }
    public void start(){
        
        new Thread(this).start();
    }
    @Override
    public void run(){
       while(fin=false){
        a();
       }
    }
    public static void a(){
        try {
           
            enderezo=JOptionPane.showInputDialog("Poña o enderezo IP");
            porto=Integer.parseInt(JOptionPane.showInputDialog("Poña o porto"));
            
            
            
            //Creando socket cliente
            clienteSocket=new Socket();
            //Estableciendo conexion
            InetSocketAddress addr=new InetSocketAddress(enderezo,porto);
            clienteSocket.connect(addr);
                InputStream is = clienteSocket.getInputStream();
                OutputStream os= clienteSocket.getOutputStream();
            
                
            
                
                double r = 0;
                double p = 0;
                try{
                    r=Double.parseDouble(JOptionPane.showInputDialog("Por favor, meta o numero para a seguinte operacion"));
                    
                }catch(NumberFormatException excepcion){
                    System.out.println("No es un numero");
                    
                }
                
                Object seleccion = JOptionPane.showInputDialog(null,"Qué operacion quiere realizar?", "        CUADRO DE OPERACIONES",
                        JOptionPane.QUESTION_MESSAGE,
                        null,new Object[] { "Sumar", "Restar", "Multiplicar","Dividir","Raíz Cuadrada","NADA" },
                        "NADA");
                
                
                
                if(seleccion=="Sumar"||seleccion=="Restar"||seleccion=="Multiplicar"||seleccion=="Dividir"){
                    p=Double.parseDouble(JOptionPane.showInputDialog("Por favor, meta o numero para a seguinte operacion"));
                }
                if (seleccion=="NADA"){
                    JOptionPane.showMessageDialog(null,"Gracias por utilizar la aplicación");
                    clienteSocket.close();
                }
                
                String numer=r+"";
                String numero=p+"";
                String selecion= (String) seleccion;
                
                os.write(numer.getBytes());
                byte[] mensaje= new byte[27];
                is.read(mensaje);
                System.out.println(new String(mensaje));
                os.write(selecion.getBytes());
                is.read(mensaje);
                System.out.println(new String(mensaje));
                os.write(numero.getBytes());
                is.read(mensaje);
                System.out.println(new String(mensaje));
                
                
                
                
                
                
                byte[] resultado=new byte[70];
                is.read(resultado);
                String g= new String(resultado);
                System.out.println("El resultado es:"+g);
                int h=JOptionPane.showConfirmDialog(null, "quieres continuar?");
                if(h==JOptionPane.YES_OPTION){
                    
                }
                else{
                    fin=true;//fin del cliente
                }          
        
           
       
            clienteSocket.close();
        
            //cierro el socket
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }
       
           
        
        
    
    public static void main(String[] args) {
        Cliente a= new Cliente();
        a.start();
    }
    
   
}
