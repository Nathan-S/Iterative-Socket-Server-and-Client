import java.net.*;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		int port = 0;
		int total = 0;
		String IP = null;
		String choice;
		Scanner scan = new Scanner(System.in);
		
		if (args.length != 2) {
			System.out.println("Argument type: <port number> <IP>");
			System.exit(1);
		}
		
		port = Integer.parseInt(args[0]);
		IP = args[1];
		
		System.out.print("How many clients would you like to generate?:");
		total = scan.nextInt();
		
		System.out.println("What command would you like the clients to run?\n"
				+ "1: Server Date and Time\n"
				+ "2: Server uptime\n"
				+ "3: Server current memory usage\n"
				+ "4: Network connections on the server\n"
				+ "5: Current users connected to the server\n"
				+ "6: Running processes on the server\n"
				+ "Any other Key will abort program");
		choice = scan.next();
		
		Thread[] threds = new Thread[total];
		MultiThread[] multi = new MultiThread[total];
		switch(choice) {
		case "1":
			break;
		case "2":
			break;
		case "3":
			break;
		case "4":
			break;
		case "5":
			break;
		case "6":
			break;
		default:
			System.out.println("Aborted");
			System.exit(0);
		}
		scan.close();
		
		long totalTime = 0;
		for(int i = 0; i < total; i++) {
			multi[i] = new MultiThread(port, IP, choice);
			threds[i] = new Thread(multi[i]);
		}
		
		for(Thread y : threds) {
			y.start();
		}
		
		for(Thread t : threds) {
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
		}
		for(MultiThread i : multi) {
			totalTime += i.timeDifference;
		}
		
		System.out.println("Total time for all clients was " + totalTime + " milliseconds");
		long averageTurnaround =  totalTime / total;
		System.out.println("Average TurnAround time was " + averageTurnaround + " milliseconds");
	}
}
