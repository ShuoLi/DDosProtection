package NetworkSecurity.DDoS.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPController {
	private static Map<String,Integer> connections=new HashMap<String,Integer>();
	private static List<String> blockIPS = new ArrayList<String>();
	
	public static void increase(String IP){
		if(connections.containsKey(IP)){
			if(connections.get(IP)>300){
				blockIPS.add(IP);
				System.err.println("This is an DDoS attack");
			}
			connections.put(IP, connections.get(IP)+1);
		}else
			connections.put(IP, 1);
	}
	
	public static boolean checkAccess(String IP){
		if(blockIPS.contains(IP))
			return false;
		return true;
	}
	
	public static void readFromLog(){
		
	}
}
