package ygong8;

public class MonitorInfo {
	
	private String osName;
	private long totalMemory;
	private long freeMemory;
	private long maxMemory;
	private long totalPhysicalMemorySize;
	private long freePhysicalMemorySize;
	private long usedMemory;
	private double cpuRatio;
	
	public String getOsName(){
		return this.osName;
	}
	
	public void setOsName(String osName){
		this.osName = osName;
	}
	
	public long getTotalMemory(){
		return this.totalMemory;
	}
	
	public void setTotalMemory(long totalMemory){
		this.totalMemory = totalMemory;
	}
	
	public long getFreeMemory(){
		return this.getFreeMemory();
	}
	
	public void setFreeMemory(long freeMemory){
		this.freeMemory = freeMemory;
	}
	
	public long getMaxMemory(){
		return this.maxMemory;
	}
	
	public void setMaxMemory(long maxMemory){
		this.maxMemory = maxMemory;
	}
	
	public long getTotalPhysicalMemorySize(){
		return this.totalPhysicalMemorySize;
	}
	
	public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize){
		this.totalPhysicalMemorySize = totalPhysicalMemorySize;
	}
	
	public long getFreePhysicalMemorySize(){
		return this.freePhysicalMemorySize;
	}
	
	public void setFreePhysicalMemory(long freePhysicalMemory){
		this.freePhysicalMemorySize = freePhysicalMemory;
	}
	
	public long getUsedMemory(){
		return this.usedMemory;
	}
	
	public void setUsedMemory(long usedMemory){
		this.usedMemory = usedMemory;
	}
	
	public double getCpuRatio(){
		return this.cpuRatio;
	}
	
	public void setCpuRatio(double cpuRatio){
		this.cpuRatio = cpuRatio;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
