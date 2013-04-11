package NetworkSecurity.DDoS.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCPU {
	public static int currentPackets;
	public static int currentTraffic;
	public static Queue<Double> _cpuUsages = new PriorityBlockingQueue<Double>();
	public static Queue<Integer> _traffics = new PriorityBlockingQueue<Integer>();
	public static Queue<Integer> _packets = new PriorityBlockingQueue<Integer>();
	public static Queue<Double> _avg_load = new PriorityBlockingQueue<Double>();
	
	public static void execShell(String shell) {
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec(shell);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void runShell(String shStr) throws Exception {

		Process process;
		process = Runtime.getRuntime().exec(
				new String[] { "/bin/sh", "-c", shStr }, null, null);
		InputStreamReader ir = new InputStreamReader(process.getInputStream());
	}

	public static void monitor() {
		try {
			ReadCPU.runShell("./cpu.sh");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1 * 1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader("log.txt"));
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				if (line == null)
					break;
				if (line.contains("CPU usage")) {
					String s = line.split(":")[1].split(",")[0].split("%")[0];
					double cpu_usage = Double.parseDouble(s);
					if(cpu_usage>40)
						System.out.println("There could be a DDoS attack.");
					if(_cpuUsages.size()>30)
						_cpuUsages.remove();
					if(currentPackets!=0)
						_cpuUsages.add(cpu_usage);
//					System.out.println("cput usage:" + cpu_usage);
				} else if (line.contains("Load Avg")) {
					String s = line.split(":")[1].split(",")[0];
					double last_min_load = Double.parseDouble(s);
					if(last_min_load>1.5)
						System.out.println("There could be a DDoS attack.");
					if(_avg_load.size()>30)
						_avg_load.remove();
					if(currentPackets!=0)
						_avg_load.add(last_min_load);
//					System.out.println("last min load" + last_min_load);
				} else if (line.contains("Networks")) {
					String packets = line.split(":")[2].split(",")[0]
							.split(" ")[1].split("/")[0];
					String traffic = line.split(":")[2].split(",")[0]
							.split(" ")[1].split("/")[1];
					int pack_num = Integer.parseInt(packets);
					Pattern pattern = Pattern.compile("\\d+");
					Matcher matcher = pattern.matcher(traffic);
					int traf_num = 0;
					while (matcher.find()) {
						traf_num = Integer.parseInt(matcher.group(0));
					}
					int pack_increase = pack_num - currentPackets;
					int traf_increase = traf_num - currentTraffic;
					currentPackets = pack_num;
					currentTraffic = traf_num;
//					System.out.println("packets:" + pack_increase);
//					System.out.println("traffic:" + traf_increase);
					if((pack_increase>150&&pack_increase!=currentPackets)||
							(traf_increase>10&&traf_increase!=currentTraffic))
						System.out.println("There could be a DDoS attack.");
					if(pack_increase!=currentPackets||traf_increase!=currentTraffic){
						if(_packets.size()>30)
							_packets.remove();
						_packets.add(pack_increase);
						if(_traffics.size()>30)
							_traffics.remove();
						_traffics.add(traf_increase);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		while(true){
			ReadCPU.monitor();
			try {
				Thread.sleep(5*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new FileWriter("/Users/Shuo/Workspaces/MyEclipse10/.metadata/.me_tcat7/webapps/NSver2/WEB-INF/logOfSystem"));
			
				out.write(ReadCPU._cpuUsages.toString()+"\n");
				out.write(ReadCPU._avg_load.toString()+"\n");
				out.write(ReadCPU._packets.toString()+"\n");
				out.write(ReadCPU._traffics.toString()+"\n");
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
