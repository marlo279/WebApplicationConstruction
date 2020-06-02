import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Client {
	public static void main(String[] arg) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		Socket s = new Socket("localhost", 8279);
		PrintWriter out = new PrintWriter( s.getOutputStream() );

		while(true){
			System.out.println("Stuur bericht: ");
			String bericht = keyboard.nextLine();
			System.out.println("Ingevoerd: " + bericht);
			
			out.write(bericht + "\n");
			out.flush();
			
			if (bericht.equals("stop")) {
				break;
			}
		}
		s.close();
		keyboard.close();


	}
}