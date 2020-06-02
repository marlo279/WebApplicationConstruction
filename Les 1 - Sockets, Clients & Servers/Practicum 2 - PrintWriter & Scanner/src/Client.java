import java.net.Socket;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class Client {
    public static void main(String[] arg) throws Exception {
    	
    	
        Socket s = new Socket("localhost", 8279);
              
        System.out.println("Maakt verbinding met localhost:8279");
        
        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream()); 
            
	    if (os != null) {
	    	
	     	System.out.println("Verbinding maken Server gelukt");
	    	
	        PrintWriter out = new PrintWriter(os);
	        
	        String bericht1 = "Ik ben Marlo Celestri";
	        String bericht2 = "Het is lekker weer vandaag";
	        out.write(bericht1);
	        out.write(bericht2);
	        out.flush();
	        
	        out.close();
	    }
    			   
    }
}

