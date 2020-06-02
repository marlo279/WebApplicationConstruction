
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class Server {
	
    private static final String OUTPUT = "<html><head><title>Response OK</title></head><body><p>Deze pagina is gemaakt vanuit de Back-end</p></body></html>";
    private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "Content-Length: ";
    private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";
	
	public static void main(String[] arg) throws Exception {
	
	
    System.out.println("Socket is aangemaakt op port: 4711");
    ServerSocket ss = new ServerSocket(4711);
    
    System.out.println("Server wacht op Client request");
    Socket s = ss.accept(); 
    
    
    BufferedWriter out = new BufferedWriter(
            new OutputStreamWriter(
                new BufferedOutputStream(s.getOutputStream()),
                "UTF-8"
            )
        );
    


        out.write(OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT);
        System.out.println("Reponse is gegeven");
        
	    out.flush();
	    out.close();
	    


	  ss.close();

    }


}
    
    


    

