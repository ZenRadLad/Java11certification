package concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class Concurrency {

	public static void main(String[] args) {
//		threads();
//		conccurentCollections();
//		executorService();
//		lockingMechanisms();
//		threadingProblems();
//		parrallelStreams();

//		The thread executor contains only one thread, but our CyclicBarrier limit is 3.
//		Even though 12 tasks are all successfully submitted to the service,
//		the first task will block forever on the call to await(). 
//		Since the barrier is never reached, nothing is printed, and the program hangs.

		final var cb = new CyclicBarrier(3, () -> System.out.println("Clean!"));
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		try {
			IntStream.generate(() -> 1)
				.limit(12).parallel()
				.forEach(i -> service.submit(() -> cb.await()));
		} finally {
			if (service != null)
				service.shutdown();
		}
		// TODO :
		// Worker threads runnable/callable
		// Threading issues identification
		// Executor service
		// java.util.concurrent API
	}

	private static void threads() {
		// Thread : an execution path managed by CPU cores
		// Implemented using :
		// Callable = creates a thread returns generic data type an throw a checked
		// exception
		// Runnable = creates a thread
		// Thread.start()
		// Thread scheduler allocates portions of CPU time to execute thread actions

		Object obj = new Object();
		Runnable runnable = () -> {
			try {
				synchronized (obj) {
					// puts thread into waiting state against object monitor
					obj.wait();
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		synchronized (obj) {
			// notify wakes up waiting thread
			// notifyAll() wakes up all waiting threads
			obj.notify();
		}

		// Thead Properties
		// id
		// Thread.setPriority(int)
		// Thread.MAX_PRIORITY 10
		// Thread.MIN_PRIORITY 1
		// Thread.NORM_PRIORITY (= 5, Default)
		// Thread.setDaemon(true|false)
		// user = highest priority, JVM will wait for its complete execution
		// daemon = low priority, only role is to provide services to user threads
		//

		// Multithreading = executing actions at the same time

		// Synchronization : Two threads must synchronize on the same shared object

		// Executors = provide thread management automatin using different
		// ExecutorService objects
		// Fixed Thread Pool = reuses a fixed number of threads
		// Work Stealing Pool =
		// Single Thread Executor =
		// Cached Thread Executor =
		// Scheduled Thread Executor = schedules tasks to execute with a delay and/or
		// periodically
		// Single Thread Scheduled Executor =
		// Unconfigurable Executor Service = provides a way to freeze another
		// ExecutorService config

		// ScheduledExecutorService : schedule tasks at a fixed rate or interval between
		// executions

		// Future<V> : represents the result of an asynchronous computation.
	}

	private static void conccurentCollections() {
		// java.util.concurrent

		// CopyOnWriteArrayList | CopyOnWriteArraySET: arrayList/set that makes a copy
		// of its content when using
		// a modifying method (add(), remove(), set().)

		// ConcurrentMap<K,V> : map providing additional atomic putIfAbsent, remove, and
		// replace methods

		// ConcurrentHashMap<K,V> : hash table supporting full concurrency of retrievals
	}

	private static void executorService() {
		// Executor : Object that executes submitted Runnable tasks
		// ExecutorService : Creates and manages a thread or pool of thread
		// Instances of Runnable and Callable can be submitted to a thread executor
		// and will be completed using available threads in the service

	}

	private static void lockingMechanisms() {
		// volatile variables = disable compiler caching the shared value locally within
		// a thread
		// always read from main memory

		// Atomic actions = action guaranteed to be executed by a thread without an
		// interruption
		// atomic vars behave as if they are volatile. Arithmetic operations are not
		// atomic
		AtomicBoolean aBool = new AtomicBoolean(true);
		AtomicInteger aInt = new AtomicInteger(12);
		AtomicLong aLong = new AtomicLong(41);
		AtomicReference<Object> aRef = new AtomicReference<>();

		// Monitor : ensures that only one thread processes a particular section of code
		// at a time
		// implemented with "synchronized" block or method or using an instance of Lock

		// Lock :

		// ReentrantLock : has an advantage over using synchronized block = it checks
		// whether Lock is available
		// without blocking it

		// CyclicBarrier : force a set of threads to wait until they are at a certain
		// stage of execution before continuing

		// CountDownLatch : synchronization aid that allows one or more threads to wait
		// until a set of operations
		// being performed in other threads completes.
	}

	private static void threadingProblems() {
		Object a = new Object();
		Runnable runnable = () -> {
		};
		Thread thread = new Thread(runnable);
		// Deadlock : two or more threads are blocked forever waiting for each other

		// Starvation : a thread is waiting for a resource blocked by another busy
		// thread
		// thread 1
		synchronized (a) {
			// action taking a very long time
		}

		// thread 2
		synchronized (a) {
			// thread 2 is blocked by thread 1
		}

		// Lived Lock : two or more threads are active but each is expecting
		// confirmation of completion from the other
		boolean threadCompleted = false;

		while (threadCompleted) {
			// do thread 1 actions
		}

		threadCompleted = true;

		while (threadCompleted) {
			// do thread 2 actions
		}
		threadCompleted = true;

		// Race condition : two threads execute at the same time resulting in an
		// expected outcome

	}

	private static void parrallelStreams() {
		// code examples and potential problems

	}

}