package objects;

public class ObjectLifecycle {

	public static void main(String[] args) {
		//Created object instances in Java are stored in a Heap, a memory zone shared by all threads
		//the heap size can be fixed of variable. Objects are cleaned by the garbage collector
		
		//Heap is entirely managed by the JVM, it keeps track of all object references
		//when it detects that there are no references to an object => garbage

		objectLifeCycle();
		garbageCollection();
	}

	private static void objectLifeCycle() {
		//All classes extends java.lang.Object => every object will take space in the heap
		
		//Object creation methods :
			//new keyword
		    //deserialization
			//cloning
		
		//1-load the class of the object to be instanciated
		//2-initialize static fields using static initializers
			//	static {
			//		var creationDate = new Date();
			//	}
		//3-JVM allocates heap space for the object and sets up its reference
		//4-Call constructor (init vars, open files/db ..)
		//5-JVM drops its internal reference to the object and calls GC when no longer used
	}
	
	private static void garbageCollection() {
		// Garbage collection
			//performed periodically
			//java.lang.System.gc() method to perform garbage collection
			//reclaiming memory of a cleaned object
			//objects that are no longer referenced		
	}
}