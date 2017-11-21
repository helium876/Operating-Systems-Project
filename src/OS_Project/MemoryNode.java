//Daniel Stewart 1501369
package OS_Project;

public class MemoryNode {
	Process Data;
	MemoryNode NextNode;
	public MemoryNode(){
		Data=new Process();
		NextNode=null;
	}
	
	public MemoryNode(Process data){
		Data=data;
		NextNode=null;
	}
	public Process getData() {
		return Data;
	}
	public void setData(Process data) {
		Data = data;
	}
	public MemoryNode getNextNode() {
		return NextNode;
	}
	public void setNextNode(MemoryNode nextNode) {
		NextNode = nextNode;
	}
	
}
