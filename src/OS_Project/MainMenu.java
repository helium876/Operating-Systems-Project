//Daniel Stewart 1501369
package OS_Project;

import java.util.Scanner;

public class MainMenu {
	static int SystemMemory = 1000;
	static int SystemStorage = 1024;

	@SuppressWarnings({ "resource", "static-access" })
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int MainCase = 0;
		int NumOfProcess = 0;
		
		Main game = new Main();
		ProcessManager PM = new ProcessManager();
		MemoryManager MM = new MemoryManager();
		PM.getSystemProcesses();

		Node temp = PM.getHead();
		while (temp != null) {
			MM.insertInMemory(temp.getProcess());
			temp = temp.getNextNode();
			NumOfProcess++;
		}
		do {
			System.out.println("\n-------- MENU --------");
			System.out.println("[1] Tic-Tac-Toe");
			System.out.println("[2] Memory Manager");
			System.out.println("[3] Process Manager");
			System.out.println("[4] File Manager");
			System.out.println("[5] Shutdown");
			MainCase = in.nextInt();
			switch (MainCase) {
			case 1:
				Process process = new Process(++NumOfProcess, "Tic-Tac-Toe", "User", 50);
				if (MM.insertInMemory(process) == true) {
					PM.insertAtFront(process);
					game.main(args);
				} else {
					System.out.println("Close a program and try again");
				}
				break;
			case 2:
				System.out.println("------- Currently in Memory --------");
				MM.display();
				break;
			case 3:
				int PMcase;
				int processId;
				do {
					System.out.println("\n------ Process Manager ------");
					System.out.println("[1] View running processes");
					System.out.println("[2] Kill process");
					System.out.println("[3] Restart process");
					System.out.println("[4] Return to Main Menu");
					PMcase = in.nextInt();
					switch (PMcase) {
					case 1:
						PM.DisplayAll();
						break;
					case 2:
						System.out.println("Enter Process ID: ");
						processId = in.nextInt();
						PM.killProcess(processId);
						MM.deleteFromMemory(processId);
						break;
					case 3:
						System.out.println("Enter Process ID: ");
						processId = in.nextInt();
						PM.restartProcess(processId);
						break;
					}// Process Manager Case
				} while (PMcase != 4);
				break;
			case 4:
				System.out.println("File Manager");
				break;
			case 5:
				System.out.println("Shutting Down...");
				break;
			default:
				System.out.println("Invalid option");
			}// Main case
		} while (MainCase != 5);
	}// end main
}// end class