//Daniel Stewart 1501369
package OS_Project;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class ProcessManager {
	private Node Head;
	private int Count;
	PrintWriter pw;

	public ProcessManager() {
		try {
			pw = new PrintWriter("Process Manager\\ProcessHistory.txt");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Head = null;
		Count = 0;
	}

	public Node getHead() {
		return Head;
	}

	public void setHead(Node head) {
		Head = head;
	}

	public boolean isEmpty() {

		if (Head != null) {
			return false;
		}
		return true;
	}

	public int getCount() {
		return Count;
	}

	@SuppressWarnings("unused")
	public void insertAtFront(Process process) {
		Node temp = new Node(process);
		if (temp != null) {
			if (isEmpty()) {
				Head = temp;
			} else {
				temp.setNextNode(Head);
				Head = temp;
			}
			Count++;
		} else {
			System.out.println("Not enough memory to create this process");
		}
	}

	// insert for processHistroy
	@SuppressWarnings("unused")
	public Node insertAtFront(Process process, Node head) {
		Node temp = new Node(process);
		if (temp != null) {
			if (head == null) {
				head = temp;
			} else {
				temp.setNextNode(head);
				head = temp;
			}
			Count++;
			return temp;
		} else {
			System.out.println("Not enough memory to create this process");
		}
		return temp;
	}

	//
	public void getSystemProcesses() {
		try (Scanner file = new Scanner(new File("Process Manager\\SystemProcesses.txt"))) {

			while (file.hasNextLine()) {
				insertAtFront(getDataFromFileLine(file.nextLine()));
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
	}

	private Process getDataFromFileLine(String fileLine) {
		Process process = new Process();
		try (Scanner line = new Scanner(fileLine)) {
			line.useDelimiter("\t");
			process.setProcessId(line.nextInt());
			process.setApplicationName(line.next());
			process.setProcessType(line.next());
			process.setProcessSize(line.nextInt());
		}
		return process;
	}

	public void killProcess(int processId) {
		int count=0;
		if (isEmpty() == false) 
		{
			Node temp = Head;
			while (temp != null) {
				if (temp.getProcess().getProcessId() == processId) 
				{
					if (temp.getProcess().getProcessType().equals("Utility")) {
						System.out.println("System process cannot be terminated");
						break;
					} else {
						delete(count);
						System.out.println("Process terminated");
						//write to process history file
						temp.getProcess().setEndTime(new Date());
						temp.getProcess().setCPUTime(
								temp.getProcess().getEndTime().getTime() - temp.getProcess().getStartTime().getTime());

						try (FileWriter fw = new FileWriter("Process Manager\\ProcessHistory.txt", true);
								BufferedWriter bw = new BufferedWriter(fw);
								PrintWriter out = new PrintWriter(bw)) {

							out.println(temp.getProcess().getProcessId() + "\t" + temp.getProcess().getApplicationName()
									+ "\t" + temp.getProcess().getProcessType() + "\t"+ temp.getProcess().getProcessSize() + "\t"
									+ temp.getProcess().getStartTime() + "\t" + temp.getProcess().getEndTime() + "\t"
									+ temp.getProcess().getCPUTime());
							out.close();
						} catch (IOException e) {
							e.getMessage();
						}
						break;
					} // end else
				} // end if
				else {
					temp = temp.getNextNode();
				}
				count++;
			} // end while
			if (temp == null) {
				System.out.println("Process ID not found");
			}
		} else {
			System.out.println("Process list is empty");
		}
	}

	private void delete(int position) {
		// Complete this method
		Node current = Head;
		Node parent = null;
		int index = 0;

		while (current != null && index < position) {
			parent = current;
			current = current.getNextNode();
			index++;
		}

		if (current != null && parent == null) {
			// Delete head.
			if (current.getNextNode() != null) {
				// Move head to next value.
				Head = current.getNextNode();
			} else {
				// Just set the head to null.
				Head = null;
			}
		} else if (current != null) {
			// Delete current, link parent to child..
			parent.setNextNode(current.getNextNode());
		}
	}

	@SuppressWarnings("resource")
	public void restartProcess(int processId) {
		try {
			Scanner ProcessHistory = new Scanner(new File("Process Manager\\ProcessHistory.txt"));
			if (ProcessHistory.hasNextByte() != true) {
				System.out.println("No process to restart");
			} else {
				Node ProcessHistoryHead = new Node();
				try (Scanner file = new Scanner(new File("Process Manager\\ProcessHistory.txt"))) {
					while (file.hasNextLine()) {
						ProcessHistoryHead = insertAtFront(getDataFromFileLine(file.nextLine()), ProcessHistoryHead);
					}
					file.close();
				} catch (FileNotFoundException e) {
					e.getMessage();
				}
				while (ProcessHistoryHead != null) {
					if (ProcessHistoryHead.getProcess().getProcessId() == processId) {
						insertAtFront(ProcessHistoryHead.getProcess());
						System.out.println("Successful");
						break;
					}
					ProcessHistoryHead = ProcessHistoryHead.getNextNode();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void DisplayAll() {

		if (isEmpty() == false) {
			Node temp = Head;

			while (temp != null) {
				temp.getProcess().display();
				temp = temp.getNextNode();
				System.out.println("");
			}
		} else {
			System.out.println("Process list is empty");
		}
	}
}