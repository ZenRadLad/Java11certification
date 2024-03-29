package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		streams();
		advancedStreamsProcessing();
	}

	private static void streams() {
		// Streams :
			// doesn't hold any data, it pulls data form a source to process it
			// doesn't modify data it processes
		
		// Ways of generating a Stream
		Stream.empty();
		Stream.of(1,2,3);
		
		// Generate infinite stream of values produced by a supplier (ex :UUID::randomUUID)
		Stream<UUID> uuids = Stream.generate(UUID::randomUUID);
		uuids.limit(2).forEach(System.out::println);
		
		//Stream.iterate(initial value, next value)
	    Stream.iterate(0, n -> n + 1)
	                .limit(10)
	                .forEach(System.out::print);

		List<User> users = getUsers();
		
		// Get only age attribute and display users older than 18
		users.stream().filter(u -> u.getAge() >= 17).forEach(u 
				-> System.out.println(u.getName() + " is " + u.getAge() + " old"));
		
		// Skip() and Limit() then apply a filter
		users.stream().skip(1).limit(3).filter(u -> u.getAge() > 20)
			.forEach(u -> System.out.println(u.getName()));
		
		// FindFirst FindAny reductions
		Optional<User> firstUser = users.stream().findFirst();
		Optional<User> anyUser = users.stream().findAny();
		System.out.println("First user : " + firstUser.get().getName() + ", he is " + firstUser.get().getAge() + " old");
		System.out.println("Any user : " + anyUser.get().getName() + ", he is " + anyUser.get().getAge() + " old");

		// Matchers anyMatch, allMatch, noneMatch
		boolean anyMatch = users.stream().anyMatch(u -> u.getAge() > 18);
		boolean allMatch = users.stream().allMatch(u -> u.getAge() > 18);
		boolean noneMatch = users.stream().noneMatch(u -> u.getAge() == 30);

		System.out.println("anyMatch users older than 18 : " + anyMatch);
		System.out.println("allMatch all users older than 18 : " + allMatch);
		System.out.println("noneMatch no user is 30 years old : " + noneMatch);

		// flatMap (flatten list items)
		List<List<String>> listOfLists = Arrays.asList(Arrays.asList("A", " Yo"), Arrays.asList("Shawty"),
				Arrays.asList("it's my birthday !"));

		System.out.println("listOfLists : " + listOfLists);

		System.out.println("flattened using flatMap() =  "
				+ listOfLists.stream().flatMap(Collection::stream).collect(Collectors.toList()));
	}

	private static void advancedStreamsProcessing() {
		// Perform decomposition and reduction, including grouping and partitioning on sequential and parallel streams

		//List<User> usersToSort = getUsers();

		List<String> cities = Arrays.asList("Milan", "london", "san Francisco", "Tokyo");
		System.out.println("List of cities : " + cities);

		
		// Reducation and collections
		
		
		
		
		// Grouping and partioning
		
		
		
		
		// Parrallel streams 
		
		
		
		
		
		// Specialized streams (IntStream, DoubleStream, LongStream)
	
	
	
	
	}
	
	private static List<User> getUsers() {
		List<User> users = new ArrayList<>();
		users.add(new User("Pythagoras", 16));
		users.add(new User("Leonhard Euler", 17));
		users.add(new User("Grigori Perelman", 22));
		users.add(new User("Carl Friedrich Gauss ", 33));
		users.add(new User("Henri Poincaré", 27));
		return users;
	}
	
	private static class User {
		private String name;
		private int age;

		public User(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
