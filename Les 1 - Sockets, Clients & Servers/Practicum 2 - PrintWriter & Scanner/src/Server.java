import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

class Server {
    public static void main(String[] arg) throws Exception {
    	
        System.out.println("Socket is aangemaakt op port: 8279");
        ServerSocket ss = new ServerSocket(8279);
        
        System.out.println("Server wacht op Client request");
        Socket s = ss.accept(); 
        
        
        System.out.println("Client is verbonden");
        
        InputStream is = s.getInputStream() ;
        Scanner sr = new Scanner(is);

        int c = is.read();
        while (sr.hasNextLine()) {
            System.out.print("Bericht van Client: " + (char) c + sr.nextLine());
            c = is.read();
        }


 

        }

    }

