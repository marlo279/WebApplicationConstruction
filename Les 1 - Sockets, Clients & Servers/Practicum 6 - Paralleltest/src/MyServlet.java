import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class MyServlet extends Thread {

  private Socket socket;
  
  private static final String OUTPUT = "<html><head><title>Response OK</title></head><body><p>Deze pagina is gemaakt vanuit de Back-end</p></body></html>";
  private static final String OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "Content-Length: ";
  private static final String OUTPUT_END_OF_HEADERS = "\r\n\r\n";

  public MyServlet(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try {

  
      BufferedWriter out = new BufferedWriter(
          new OutputStreamWriter(
            new BufferedOutputStream(socket.getOutputStream()),
            "UTF-8"
          )
      );
  
      out.write(OUTPUT_HEADERS + OUTPUT.length() + OUTPUT_END_OF_HEADERS + OUTPUT);
      
      Thread.sleep(10000);
      
      out.flush();
      out.close();  

    } catch(Exception e) {
      System.out.print(e);
    }

  }
}