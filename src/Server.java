import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
public class Server {
	
	public static void main(String[] args) throws IOException{

		  
		int port = 0;
		
		if (args.length != 1) {
			System.out.println("Argument type: <port number>");
			System.exit(1);
		} else {
			port = Integer.parseInt(args[0]);
		}
			
		try {
		 	ServerSocket sock = new ServerSocket(port);
		 	
			do {
				try(
						Socket foot = sock.accept();
						BufferedReader in = new BufferedReader(new InputStreamReader(foot.getInputStream()));
				){
					
					
					String choice = in.readLine();
					 
					
						boolean exit = false;//may need to be deleted
				
						switch(choice) {
							case "1":
								command("date", foot);
								break;
							case "2":
								command("uptime", foot);
								break;
							case "3":
								command("free", foot);
								break;
							case "4":
								command("netstat -an", foot);
								break;
							case "5":
								command("who", foot); //could also use "users" but it only displays the login name and it sorts them
								break;
							case "6":
								command("ps -A", foot);
								break;
							default:
								System.out.println("Quit");
								exit = true;//This may need to be deleted
							}
					
							//exits the loop because the client chose to exit or a command was wrong. This may need to be deleted
							if(exit) {
								break;
							}
						
							
					
					foot.close();
					System.out.println("Client has disconnected");
					
				} catch(IOException e) {
					System.out.println("Error accepting Client to socket.");
				}
				
			} while(true);	
			
			
			
		} catch(IOException e) {
			System.out.println("Error when creating server socket.");
			e.printStackTrace();
		}
			
		
		
	}
	
	public static void command(String command, Socket foot) {
		String linereader = null;
		try {
			Process runner = Runtime.getRuntime().exec(command); //executes the "command" on the command line of the current environment that Java is running on
			PrintWriter print = new PrintWriter(foot.getOutputStream(), true);//this will take the output stream and send it to the client
			BufferedReader input = new BufferedReader(new InputStreamReader(runner.getInputStream())); //reader that will read from the command line
			
			while((linereader = input.readLine()) != null) {
				print.println(linereader);
			}
			print.println("Finished");
			
		} catch (IOException e) {
			System.out.println("There was an issue with the input ofthe command or the output.");
		}
		
	}
}
