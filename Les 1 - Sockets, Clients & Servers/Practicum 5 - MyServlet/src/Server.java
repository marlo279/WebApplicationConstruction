
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
    	
    	while (true) {

	        ServerSocket ss = new ServerSocket(4711);
	        Socket s = ss.accept();
	        System.out.println("New connection from " + s.getInetAddress());
	
	        MyServlet thread = new MyServlet(s);
	        thread.start();
	             
	        ss.close();
    	}

    }
}