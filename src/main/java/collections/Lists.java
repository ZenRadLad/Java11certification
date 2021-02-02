package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Lists {

	public static void main(String[] args) {

		// Collections :
		// List (Dynamic arrays) --> ArrayList, LinkedList
		// Set (Unique elements) --> HashSet, SortedSet, TreeSet
		// Queue (Order is important) --> PriorityQueue, Deque

		array();
		arrayList();
		set();
		map();
		queue();
		comparatorAndComparable();
		genericsAndWildCards();
	}

	private static void array() {
		float[] floatArray = new float[2];
		floatArray[0] = 20.1f;
		floatArray[1] = 18.0f;

		short[] shortArray = new short[] { 89, 12, 110, 20 };

		System.out.println("float array : " + Arrays.toString(floatArray) + ", size : " + floatArray.length);
		System.out.println("short array : " + Arrays.toString(shortArray) + ", size : " + shortArray.length);

		// creating an array with reference variable
		char[] charArray = { 'a', 'z' };
		char[] charArrayCopy = charArray;
		System.out.println("charArray : " + Arrays.toString(charArray) + ", charArrayCopy : "
				+ Arrays.toString(charArrayCopy) + ", areEqual : " + charArray.equals(charArrayCopy));

		// sorting
		Arrays.sort(shortArray);
		System.out.println("sorted shortArray : " + Arrays.toString(shortArray));

		// comparing
		char[] charArray2 = { 'a', 'b' };

		System.out.println("equals() (return true/false) : " + Arrays.equals(charArray2, charArray2));
		System.out
				.println("compare() (positiveNumber = firstArray > second) : " + Arrays.compare(charArray, charArray2));
		System.out.println("mismatch() (returns -1 if arrays are equal) : " + Arrays.mismatch(charArray, charArray2));

		// varArgs (variable arguments)

		// Valid ways to declare 2D array
		int[][] twoDArray = new int[2][2];
		int twoDArray2[][];
		int[] twoDArray3[];

		String[][] names = { { "firstCol", "firstCol2", "firstCol3" }, { "secCol", "secCol2" } };
		System.out.println("Asymmetric multiDimensional array : " + Arrays.deepToString(names));

		// Init 2D array using two loops
		for (int i = 0; i < twoDArray.length; i++) {
			for (int j = 0; i < twoDArray.length; i++) {
				twoDArray[i][j] = 0;
			}
		}
		System.out.println("Init 2D array using two loops : " + Arrays.deepToString(twoDArray));
	}

	private static void arrayList() {
		ArrayList l1 = new ArrayList();
		System.out.println("init empty arrayList with 2 empty slots : " + l1.size());

		var varList = new ArrayList<Integer>();
		varList.add(10);
		varList.add(20);
		varList.remove(0);
		varList.set(0, 99);
		varList.clear();
		varList.add(20);
		varList.add(190);
		varList.add(8);
		varList.contains(1);
		varList.equals(l1);
		System.out.println("varList firstElement : " + varList.get(0));

		// Create list using varArgs
		List<String> strList = Arrays.asList("one", "two"); // asList(T... a)
		List<Integer> intList = List.of(2, 1, 9); // of(E... elements)

		// Sorting
		Collections.sort(varList);
		System.out.println("Sort ArrayList<Integer> : " + varList);
	}

	private static void set() {
		// Collection of Unique items
		Set<Integer> intSet = new HashSet<>();
		intSet.add(20);
		intSet.add(20);
		intSet.add(19);
		intSet.add(null); // allows one null element
		intSet.remove(19);
		System.out.println("Set<Integer> : " + intSet);

		// HashSet is faster than TreeSet
		// HashSet uses Hashtable, TreeSet use TreeMap

		// TreeSet : sorts unique element by asc order
		Set<String> treeSet = new TreeSet<>();
		treeSet.add("B");
		treeSet.add("A");
		treeSet.remove("c");
		// treeSet.add(null); cannot insert null in TreeSet
		System.out.println("new TreeSet<String> : " + treeSet);
		System.out.println("treeSet.contains(); : " + treeSet.contains("b"));

		// TreeSet with Comparator constructor to define order of sorting
		Set<String> insertOrderTreeSet = new TreeSet<>(Comparator.comparing(String::length));
		insertOrderTreeSet.add("10");
		insertOrderTreeSet.add("111");
		insertOrderTreeSet.add("11");
		System.out.println("new TreeSet<>(Comparator.comparing(String::length) : " + insertOrderTreeSet);
	}

	private static void map() {
		// Map init
		Map<Integer, String> hashMap = new HashMap<>();
		hashMap.put(20, "one");
		hashMap.put(10, "two");
		hashMap.put(22, "three");
		hashMap.put(null, null); // allows one null

		System.out.println("Map<Integer, String> : " + hashMap);
		System.out.println("Map<Integer, String> keys : " + hashMap.keySet());
		System.out.println("Map<Integer, String> values : " + hashMap.values());
		System.out.println("Map<Integer, String> containsKey() : " + hashMap.containsKey(11));
		System.out.println("Map<Integer, String> containsValue() : " + hashMap.containsValue("two"));

		// TreeMap : sorts elements by asc or alphaneticall order
		Map<Integer, String> treeMap = new TreeMap<>();
		treeMap.put(3, "one");
		treeMap.put(2, "two");
		treeMap.put(1, "three");
		// treeMap.put(null, null);//NPE
		System.out.println("TreeMap<Integer, String> : " + treeMap);
		System.out.println("TreeMap<Integer, String> values() : " + treeMap.values());
		System.out.println("TreeMap<Integer, String> keySet() : " + treeMap.keySet());

		Map<Integer, String> reverseOrderTreeMap = new TreeMap<>(Comparator.reverseOrder());
		reverseOrderTreeMap.put(2, "two");
		reverseOrderTreeMap.put(6, "six");
		reverseOrderTreeMap.put(1, "one");
		System.out.println("TreeMap<Integer, String>  Reverse Order : " + reverseOrderTreeMap.keySet());
	}

	private static void queue() {

		// Queue : interface that holds elements to be processed in FIFO order
					// inserts elements at the end of the list
					// deletes delements at the start of the list
		  // java.util.Queue --> PriorityQueue (natural order of elements), LinkedList
			// have 2 version of same func :
			// poll() removeFirstElement and returns null if queue is empty
			// remove() removeFirstElement and throws an exception if queue is empty

		// LinkedList : stores items in containers that are linked to each other
					// each new element's container is linked to to another container in the list
					// used for frequent add/remove items from start/middle/end of the list
		
		Queue<Integer> linkedList = new LinkedList();
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);

		System.out.println("LinkedList  =  " + linkedList);
		System.out.println("LinkedList contains() = " + linkedList.contains(2));
		System.out.println("LinkedList Retrieve head without removing it = " + linkedList.element());
		System.out.println("LinkedList =  " + linkedList);
		System.out.println("LinkedList Retrieve and remove head :  " + linkedList.poll());
		System.out.println("LinkedList = " + linkedList);

		Queue<Short> priorityQ = new PriorityQueue();
		priorityQ.add((short) 6);
		priorityQ.add((short) 2);
		priorityQ.add((short) 9);
		System.out.println("PriorityQueue  =  " + priorityQ);
	}
	
	private static void comparatorAndComparable() {
		//Compare objects that are not directly comparable
		
		//Comparable : natural order (default way of comparing objects)
			//implements Comparable<T> and override method compareTo(T o )
		
		//Comparator : used to define multiple/different comparison strategies
		Player p1 = new Player(17);
		Player p2 = new Player(2);
	    List<Player> playersList = new ArrayList<>();
	    playersList.add(p1);
	    playersList.add(p2);

		System.out.println("compareTo(T o) of Comparable<T> returns (-1, 1, 0) : " + p1.compareTo(p2));
        
		Collections.sort(playersList, Player::compareTo);//
		System.out.println("sort list using Comparator<T>.compare() : " + playersList);
	}
	
	static class Player implements Comparable<Player>, Comparator<Player> {
		
		private int goalScore;
		
		Player(int score){
			this.goalScore = score;
		}
		
		public int getGoalScore() { return goalScore;}

		public void setGoalScore(int goalScore) { this.goalScore = goalScore;}

		@Override //Comparable<T>
		public int compareTo(Player otherPlayer) {
			return Integer.compare(this.getGoalScore(), otherPlayer.getGoalScore() );
		}
		
		@Override //Comparator<T>
		public int compare(Player p1, Player p2) {
			return Integer.compare(p1.getGoalScore(), p2.getGoalScore() );
		}
		
		@Override
		public String toString() {
			return "Player goal score : " + goalScore;
		}
	}
	
	private static void genericsAndWildCards() {
		// TODO Auto-generated method stub
		
	}
}