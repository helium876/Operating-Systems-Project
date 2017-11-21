//Daniel Stewart 1501369
package OS_Project;

public class MemoryBlock {
	String Name;
	int Size;
	MemoryNode Head;

	public MemoryBlock() {
		Name = "";
		Size = 0;
	}

	public MemoryBlock(String name, int size) {
		Name = name;
		Size = size;
		Head = null;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getSize() {
		return Size;
	}

	public void setSize(int size) {
		Size = size;
	}

	public MemoryNode getHead() {
		return Head;
	}

	public void setHead(MemoryNode head) {
		Head = head;
	}
	
	public boolean isEmpty() {

		if (Head != null) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unused")
	public void insertAtFront(Process process) {
		MemoryNode temp = new MemoryNode(process);
		if (temp != null) {
			if (isEmpty()) {
				Head = temp;
			} else {
				temp.setNextNode(Head);
				Head = temp;
			}
		} else {
			System.out.println("Not enough memory to create this node");
		}
	}

	public boolean searchAndDelete(int processId) {
		MemoryNode temp = Head;
		int bool=-1;
		int count=0;
		while (temp != null) 
		{
			if (temp.getData().getProcessId() == processId) 
			{
				Size+=temp.getData().getProcessSize();
				delete(count);
				bool=0;
				break;
			} // end if
			else {
				temp = temp.getNextNode();
			}
			count++;	
		}//end while
		if (bool>-1){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void delete(int position) {
	    // Complete this method
	    MemoryNode current = Head;
	    MemoryNode parent = null;
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
	        }
	        else {
	            // Just set the head to null.
	            Head = null;
	        }
	    }
	    else if (current != null) {
	        // Delete current, link parent to child..
	        parent.setNextNode(current.getNextNode());
	    }
	}

	public void DisplayAll() {

		if (isEmpty() == false) {
			MemoryNode temp = Head;
			while (temp != null) {
				temp.getData().display();
				temp = temp.getNextNode();
				System.out.println("");
			}
		} else {
			System.out.println("Memory block is empty");
		}
	}
}