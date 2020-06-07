
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
    	
        ServerSocket ss = new ServerSocket(4711);

    	while (true) {

	        Socket s = ss.accept();
	        System.out.println("New connection from " + s.getInetAddress());
	
	        MyServlet thread = new MyServlet(s);
	        thread.start();
	             
	        
    	}
    	
    	ss.close();

    }
}