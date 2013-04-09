package ygong8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import sun.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class MonitorServiceImp implements MonitorService {
	
	
	public MonitorInfo getMonitorInfo() throws Exception{
		
		int kb = 1024;
		
		long totalMemory = Runtime.getRuntime().totalMemory() / kb;
		long freeMemory = Runtime.getRuntime().freeMemory() / kb;
		long maxMemory = Runtime.getRuntime().maxMemory() / kb;
		
		OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		
		long totalPhysicalMemorySize = os.getTotalPhysicalMemorySize() / kb;
		long freePhysicalMemorySize = os.getFreePhysicalMemorySize() / kb;
		long usedPhysicalMemory = (os.getTotalPhysicalMemorySize() - os.getFreePhysicalMemorySize()) / kb;
		
		double cpuRatio = 0;
		
		if(osName.toLowerCase().startsWith("windows")){
			cpuRatio = this.getCpuRatioForWindows();
		}
		else if(osName.toLowerCase().startsWith("mac")){
			cpuRatio = this.getCpuRatioForMac();
		}
		else{
			cpuRatio = this.getCpuRatioForMac();
		}
		
		MonitorInfo info = new MonitorInfo();
		info.setOsName(osName);
		info.setTotalMemory(totalMemory);
		info.setFreeMemory(freeMemory);
		info.setTotalPhysicalMemorySize(totalPhysicalMemorySize);
		info.setFreePhysicalMemory(freePhysicalMemorySize);
		info.setUsedMemory(usedPhysicalMemory);
		info.setCpuRatio(cpuRatio);
		
		return info;
		
	}
	
	double getCpuRatioForMac(){
		
		//List<String> command = new ArrayList<String>();
		String[] command = new String[] {"/usr/bin/command", "top", "-n", "1"};
		ProcessBuilder builder = new  ProcessBuilder();
		builder.command("bash", "-c", "cd /Users/yugegong/Desktop/IR ; ls");
		//builder.command("bash", "-c", "top -n 1");
		//builder.command("ls");
		//builder.command("/usr/bin/top", "-n", "1");
		try{
			Process process = builder.start();
			System.out.println("Process begin");
			final InputStream is1 = process.getErrorStream();
			final InputStream is2 = process.getInputStream();
			
			TermThread thread1 = new TermThread("error", is1);
			TermThread thread2 = new TermThread("norm", is2);
			thread1.start();
			thread2.start();
			
			int exitValue = process.waitFor();
			System.out.println("exitValue = "+ exitValue);
			System.out.println("done");
			
		}catch  (Exception e){
			e.printStackTrace();
		}
		
		double cpuRatio = 0;
		
		return cpuRatio;
	}
	
	double getCpuRatioForWindows(){
		double cpuRatio = 0;
		
		return cpuRatio;
	}
	
	double getCpuRatioForLinux(){
		double cpuRatio = 0;
		
		return cpuRatio;
	}

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MonitorServiceImp msi = new MonitorServiceImp();
		msi.getMonitorInfo();

	}

}
