//Daniel Stewart 1501369
package OS_Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Process {
	int ProcessId;
	String ApplicationName;
	String ProcessType;
	int ProcessSize;
	int MemoryBlockIndex;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date StartTime= new Date();
	Date EndTime= new Date();
	long CPUTime;
	@SuppressWarnings("unused")
	public Process(){
		ProcessId=0;
		ApplicationName="";
		ProcessType="";
		MemoryBlockIndex=-1;
		int ProcessSize=0;
		Date StartTime= new Date();
		Date EndTime=null;
		CPUTime=0;
	}
	
	@SuppressWarnings("unused")
	public Process(int pID, String aName, String pType, int processSize){
		ProcessId=pID;
		ApplicationName=aName;
		ProcessType=pType;
		MemoryBlockIndex=-1;
		ProcessSize=processSize;
		Date StartTime= new Date();
		Date EndDate=null;
		CPUTime=0;
	}
	public int getProcessId() {
		return ProcessId;
	}

	public void setProcessId(int processId) {
		ProcessId = processId;
	}

	public String getApplicationName() {
		return ApplicationName;
	}

	public void setApplicationName(String applicationName) {
		ApplicationName = applicationName;
	}

	public String getProcessType() {
		return ProcessType;
	}

	public void setProcessType(String processType) {
		ProcessType = processType;
	}
	
	public int getMemoryBlockIndex() {
		return MemoryBlockIndex;
	}

	public void setMemoryBlockIndex(int memoryBlockIndex) {
		MemoryBlockIndex = memoryBlockIndex;
	}

	public int getProcessSize() {
		return ProcessSize;
	}

	public void setProcessSize(int processSize) {
		ProcessSize = processSize;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public long getCPUTime() {
		return CPUTime;
	}

	public void setCPUTime(long cPUTime) {
		CPUTime = cPUTime;
	}
	
	
	public void display(){
		System.out.println("PID: "+ProcessId+"\tAppName: "+ApplicationName+"\tPType: "+ProcessType+ "\tPSize: "+ProcessSize+
				"\tStartTime: "+dateFormat.format(StartTime));
	}
}