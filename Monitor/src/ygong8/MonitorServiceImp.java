package ygong8;

import java.io.BufferedReader;
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
		
		//InputStream is = null;
		//InputStreamReader isr = null;
		BufferedReader br = null;
		StringTokenizer st = null;
		try{
			
			System.out.println("Process begin");
			
			Process process = Runtime.getRuntime().exec("top -n -1");
			
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String str;
			while((str = br.readLine()) != null){
				System.out.println(str);
			}
			System.out.println("done");
			/*System.out.println(br.readLine());
			System.out.println(br.readLine());
			System.out.println(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreElements()){
				System.out.println(st.nextElement());
			}*/
			
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
