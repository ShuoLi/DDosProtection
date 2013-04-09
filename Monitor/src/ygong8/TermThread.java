package ygong8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TermThread extends Thread{
	
	private InputStream is;
	private String threadName;
	public TermThread(String threadName, InputStream is){
		this.is = is;
		this.threadName = threadName;
	}
	
	public void run(){
		System.out.println("Current Thread " + threadName + ": " + Thread.currentThread());
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = null;
		try {
			while((str = br.readLine()) != null){
				
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
