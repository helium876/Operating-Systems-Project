//Daniel Stewart 1501369
package OS_Project;

public class MemoryManager {
	MemoryBlock BlockA;
	MemoryBlock BlockB;
	MemoryBlock BlockC;
	MemoryBlock[] Blocks;
	public MemoryManager(){
		BlockA=new MemoryBlock("Block A", 350);
		BlockB=new MemoryBlock("Block B", 400);
		BlockC=new MemoryBlock("Block C", 250);
		Blocks= new MemoryBlock [] {BlockA, BlockB, BlockC};
		
	}
	
	public boolean insertInMemory(Process process) {
		int index=0;
		int largest = Blocks[0].getSize();
		for (int i = 0; i < Blocks.length; i++) {
			if(Blocks[i].getSize()>largest){
			largest = Blocks[i].getSize();
			index=i;
			}
		}
		if (process.getProcessSize() <=Blocks[index].getSize()){
			Blocks[index].setSize(Blocks[index].getSize()-process.getProcessSize());
			Blocks[index].insertAtFront(process);
			return true;
		}
		else{
			System.out.println("Could not load in memory!");
			return false;
		}
	}//end insertInMemomry
	
	public void deleteFromMemory (int processId){
		for (int i=0; i<Blocks.length; i++){
			if (Blocks[i].searchAndDelete(processId)==true){
				System.out.println("Processed removed from memory block\n");
				break;
			}
		}//end for 
	}
	
	public void display(){
		for (int i=0; i<Blocks.length; i++){
		System.out.println(Blocks[i].getName()+"\tRemaining Memory "+Blocks[i].getSize());
		Blocks[i].DisplayAll();
		System.out.println("");
		}
	}
}