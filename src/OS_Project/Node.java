//Daniel Stewart 1501369
package OS_Project;

public class Node {
	Process process;
	Node NextNode;
	
	public Node() {
		process= new Process();
		NextNode = null;
		
	}
	
	public Node(Process process) {
		this.process= process;
		NextNode = null;
		
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

	public Node getNextNode() {
		return NextNode;
	}

	public void setNextNode(Node nextNode) {
		NextNode = nextNode;
	}

}