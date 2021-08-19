//Rachi Rana
// 10455300

package assignment_6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class Anagrams {

	/**
	 * 
	 * Data
	 * Fields
	 * 
	 */
	
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable = new Hashtable<Character, Integer>();
	Map<Long,ArrayList<String>> anagramTable = new Hashtable<Long, ArrayList<String>>();
	
	/**
	 *
	 * Constructors
	 * 
	 */
	
	public Anagrams() {
		buildLetterTable();
		anagramTable = new Hashtable<Long, ArrayList<String>>();
	}
	
	/**
	 *
	 * Builds the hash table letterTable which consists of the entries
	 * 
	 */
	
	private void buildLetterTable() {
		// TODO Auto-generated method stub	
		char now;
		int x;
		
		for( x = 0, now = 'a'; now <= 'z'; now++, x++) {
			letterTable.put(now, primes[x]);
			
		}
		/**Character[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for (int i = 0; i < 26; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}**/
	}
	
	/**
	 *
	 * Hash functions for storing anagrams
	 * 
	 * 
	 */
	
	private void addWord(String s) {
		Long temp = myHashCode(s);
		ArrayList<String> Slist = anagramTable.get(temp);
		if(anagramTable.containsKey(temp)) {
			Slist.add(s);
			anagramTable.put(temp, Slist);
		}
		else {
			ArrayList<String> temp3 = new ArrayList<String>();
			temp3.add(s);
			anagramTable.put(temp, temp3);
		}
	}
	
	/**
	 * 
	 * Generates unique keys for hash functions
	 * 
	 */
	
	private Long myHashCode(String s) {
		int i = 0;
		long key = 1;
		while (i < s.length()) {
			Character a = s.charAt(i);
			key = key * letterTable.get(a);
			i++;
		}
		return key;
	}
	
	/**
	 *
	 * Check for anagrams
	 * @throws IOException The file could not be read
	 * 
	 */
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream)); 
		String strLine;
		while ((strLine = br.readLine()) != null) { 
			this.addWord(strLine);
		} 
		br.close();
	}
	
	/**
	 * 
	 * Gets the key with the most anagrams
	 * Keys and anagrams
	 * 
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		
		ArrayList<Map.Entry<Long,ArrayList<String>>> mentry = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		int max = 0;
		for (Map.Entry<Long,ArrayList<String>> entry: anagramTable.entrySet()) {
			if (entry.getValue().size() > max) {
				mentry.clear();
				mentry.add(entry);
				max = entry.getValue().size();
			} else {
				if (entry.getValue().size() == max) {
					mentry.add(entry);
				}
			}
		}
		return mentry;
	
	}
	
	/** 
	 * 
	 * Main method
	 *
	 */
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime(); 
		try {
			a.processFile("words_alpha.txt"); 
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries(); 
		int length = maxEntries.get(0).getValue().size();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000); 
		long key = maxEntries.get(0).getKey();
		System.out.println("Elapsed Time: "+ seconds);
		System.out.println("Key of max anagrams: " + key);
		System.out.println("List of max anagrams: "+ maxEntries.get(0).getValue()); 
		System.out.println("Length of list of max anagrams: "+ length);
	}

}
