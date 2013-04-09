package ygong8;

import java.io.File;

import sun.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

class SystemMonitor implements Runnable{
	
	private long totalMemory;
	private long freeMemory;
	private long maxMemory;
	private long totalPhysicalMemory;
	private long freePhysicalMemory;
	private long usedMemory;
	private double cpuRatio;
	

	Thread runner;
	public SystemMonitor(){
		
	}
	public SystemMonitor(String threadName){
		runner = new Thread(this, threadName);
		System.out.println("Monitor Name: " + runner.getName());
		runner.start();
	}
	public void run(){
		
		System.out.println("Current Thread: " + Thread.currentThread());
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("Available processors (cores): " + cores);
		
		long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("Total Memory[bytes]: " + totalMemory);
		
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("Free Memory[bytes]: " + freeMemory);
		
		long usedMemory = totalMemory - freeMemory;
		System.out.println("Used Memroy[bytes]: " + usedMemory);
		
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("MaxMemory[bytes]: " + (maxMemory == Long.MAX_VALUE ? "no limits" : maxMemory));
		File roots[] = File.listRoots();
		
		/* For each filesystem root, print some info */
	    for (File root : roots) {
	      System.out.println("File system root: " + root.getAbsolutePath());
	      System.out.println("Total space (bytes): " + root.getTotalSpace());
	      System.out.println("Free space (bytes): " + root.getFreeSpace());
	      
	      //Returns the number of bytes available to this virtual machine on the partition named by this abstract pathname.
	      System.out.println("Usable space (bytes): " + root.getUsableSpace());
	      
	      OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
			String osName = System.getProperty("os.name");
			System.out.println(osName);
			
	    }
		
		
	}

	//cache memory cpu
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SystemMonitor monitor = new SystemMonitor("monitor");

	}

}
