import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class MultiThread implements Runnable{
	String ip;
	int port = 0;
	String chosen;
	public long timeDifference = 0;
	
	public MultiThread(int port1, String ip1, String choice) {
		this.port = port1;
		this.ip = ip1;
		this.chosen = choice;
	}
	
	public void run() {
			System.out.println("Thread is Running");
			PrintWriter write = null;
			BufferedReader read = null;
			Socket foot = null;
			try {
				foot = new Socket(ip, port);
				write = new PrintWriter(foot.getOutputStream(), true);
				read = new BufferedReader(new InputStreamReader(foot.getInputStream()));
				
			} catch (IOException e) {
				System.out.println("Failure making socket for client thread.");
				e.printStackTrace();
			}
			String linereader = null;
			long begining = System.currentTimeMillis();
			try {
				write.println(chosen);
				while((linereader = read.readLine()) != null) {
					if( linereader == "Finished")	{
						break;
					}
					System.out.println(linereader);
				}
			} catch (IOException e) {
				System.out.println("There was an issue with the input ofthe command or the output.");
			}
			
			long end = System.currentTimeMillis();
			
			timeDifference = end - begining;
			System.out.println("The elapsed time was " + timeDifference + " milliseconds");
			
			try {
				foot.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
