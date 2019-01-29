package org.web.blog.comm.threadtutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.zookeeper.server.quorum.Leader;
import org.web.blog.comm.threadtutorial.char01.AliveOtherThread;
import org.web.blog.comm.threadtutorial.char01.ExampleDeamonThread;

public class Test {

	private static Boolean ready = false;
	private static int number;
	
	private String[] s = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
	private String[] sabc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public static class ReaderThread extends Thread {
		
		@Override
		public  void run () {
			System.out.println(ready);
			while(!ready) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.yield();
				System.out.println(number);
			}
		}
	}
	
    public int uniqueMorseRepresentations(String[] words) {
    	if (words.length == 0) {
    		return 0;
    	}
    	String[] s = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
		String[] sabc = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		List<String> list = Arrays.asList(sabc);
		List<String> l = Arrays.asList(s);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < words.length; i++) {
			String[] str = words[i].split("");
			for (int j = 0; j < str.length; j++) {
				int in = list.indexOf(str[j]);
				sb.append(l.get(in));
			}
			sb.append(",");
		}
		Set<String> set = new HashSet<String>();
		set.addAll(Arrays.asList(sb.toString().split(",")));
		return set.size();
    }
	
	public static  void main(String[] args) throws InterruptedException {
		String[] s = {};
		new Test().uniqueMorseRepresentations(s);
	}
}
