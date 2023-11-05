package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Streams {

    public static void main(String[] args) {
        // streams();
        // mapping();
        advancedStreamsProcessing();
        // parrallelStream();
        // spliterator();
    }

    private static void mapping() {
        // map() applies a function to each element of a stream and returns a stream of the results
        // flatMap applies a function to each element of a stream, where the function returns a stream of new values
        //  and then "flattens" the streams into a single stream
        // flatMap takes a mapper function to apply to each element which produces a stream of new values
        List<List<String>> listOfLists = List.of(List.of("A", " Yo"), List.of("Shawty"), List.of("it's my birthday !"));

        System.out.println("listOfLists : " + listOfLists);

        System.out.println("flattened using flatMap() =  "
                + listOfLists.stream().flatMap(Collection::stream).collect(Collectors.toList()));
    }

    private static void spliterator() {
        // Spliterator is used to traverse and partition streams, suitable for parallelStream

        Stream<String> items = Stream.of("apple", "banana", "cherry", "kiwi", "avocado", "cherry", "apple");

        // Get Spliterator from stream
        Spliterator<String> itemsSpliterator1 = items.spliterator();

        // trySplit() method is used partition the spliterator
        Spliterator<String> itemsSpliterator2 = itemsSpliterator1.trySplit();

        // tryAdvance() iterate elements individually in different threads. It helps in parallel processing.
        System.out.println("items1 : ");
        itemsSpliterator1.tryAdvance(System.out::println);

        System.out.println("items2 : ");
        // forEachRemaining(consumer) iterate elements sequentially in a single Thread, use forEachRemaining()
        itemsSpliterator2.forEachRemaining(System.out::println);
    }

    private static void parrallelStream() {
        // concurrent processing of data to leverage multi-core processors effectively
        // offer a performance boost by dividing the data into multiple chunks and processing them in parallel threads
        // The number of threads it uses is typically one less than the number of available cores
        // Stateless operations (like map and filter) are generally safe in parallel streams.
        // Stateful operations (like distinct and sorted) may require significant coordination, which can reduce the benefit of parallelism.

        // Avoid parrallelStream for small data sets, I/O, mutable shared data

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.parallelStream().reduce(0, Integer::sum);
        System.out.println("parallelStream() Sum: " + sum);
    }

    private static void streams() {
        // Streams :
        // doesn't hold any data, it pulls data form a source to process it
        // doesn't modify data it processes

        // Ways of generating a Stream
        Stream.empty();
        Stream.of(1, 2, 3);

        // Generate infinite stream of values produced by a supplier (ex :UUID::randomUUID)
        Stream<UUID> uuids = Stream.generate(UUID::randomUUID);
        uuids.limit(2).forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(5)
                .sorted()
                .distinct()
                .forEach(System.out::println);

        Stream.generate(() -> "*")
                .limit(5)
                .sorted((s, t) -> s.length() - t.length()) //takes a comparator
                .distinct()
                .forEach(System.out::println);

        //Stream.iterate(initial value, next value)
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(System.out::print);

        IntStream.generate(() -> 111)
                .limit(5)
                .parallel()
                .forEach(System.out::println);

        // IntStream.range(1, 100).forEach(System.out::print); //  1 to 99
        // IntStream.rangeClosed(1, 100).forEach(System.out::print); //  1 to 100

        // boxed() : returns Stream of wrapped primitive values
        List<Integer> boxedList = IntStream.of(1, 2, 3, 4, 5).boxed().collect(Collectors.toList());
        System.out.println("boxed() : " + boxedList);

        // Statistics
        DoubleSummaryStatistics doubleSummaryStatistics = DoubleStream.of(1, 2, 3, 4).summaryStatistics();
        System.out.println("SummaryStatistics avg : " + doubleSummaryStatistics.getAverage());
        System.out.println("SummaryStatistics sum : " + doubleSummaryStatistics.getSum());

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

        // Linking Streams to the Underlying Data
        // Streams are often linked to an underlying data structure, and changes to that data structure may affect the stream.
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Stream<String> stream = list.stream();
        list.add("d"); // Modifying the list after creating the stream
        stream.forEach(System.out::print); // The stream will see the added element : a,b,c,d
    }

    private static void advancedStreamsProcessing() {
        // Perform decomposition and reduction, including grouping and partitioning on sequential and parallel streams

        //Function.identity()
        // is used as the keyMapper function to indicate that the stream elements themselves should be used as the keys of the map
        // in short : it's a function that does nothing except return the value that was passed to it

        // Specialized streams (IntStream, DoubleStream, LongStream)
        LongStream longStream = LongStream.of(1L, 2L, 3L, 4L);
        // Sum : longStream.sum();
        // Count : longStream.count();
        long lReduce = longStream.reduce(0L, (a, b) -> a - b);
        System.out.println("Sum of LongStream : " + lReduce);

        List<String> cities = Arrays.asList("Milan", "london", "san Francisco", "Tokyo");

        // Collecting
        //      Collecting to Set
        Set<Integer> set = Stream.of(1, 2, 3, 3, 4).collect(Collectors.toSet());
        System.out.println("Collecting to Set : " + set);

        //      Collecting to Map
        Map<String, Integer> citiesLength = cities.stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println("Collecting to Map (word length) : " + citiesLength);

        Map<Character, String> citiesStartingChars = cities.stream().collect(Collectors.toMap(
                (a) -> a.charAt(0),
                Function.identity(),
                (a, b) -> a + ", " + b));
        System.out.println("Collecting to Map (first char & concat) : " + citiesStartingChars);


        // Grouping (according to a classification function (String::length)
        // The groupingBy() collector tells collect() that it should group all of the elements of the stream into a Map.
        // The function determines the keys in the Map. Each value in the Map is a List of all entries that match that key. »

        //      Classification Function: function is applied to each element of the stream to determine the category it belongs to.
        Map<Integer, Set<String>> groupOfWordsAndLength = cities.stream().collect(
                Collectors.groupingBy(
                        // Classifier
                        String::length,
                        // Downstream collector = result
                        Collectors.toSet())
        );
        System.out.println("GroupingBy String length : " + groupOfWordsAndLength);

        List<String> items = Arrays.asList("apple", "banana", "cherry", "apple", "banana", "cherry", "apple");

        Map<String, Long> itemCount = items.stream()
                .collect(Collectors.groupingBy(
                        // Classifier
                        Function.identity(),
                        // Collector of reduced/grouped values
                        Collectors.counting()));
        System.out.println("GroupingBy counting occurences of each element : " + itemCount);


        //      Group employees by department
        // Map<Department, List<Employee>> byDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        //      Compute sum of salaries by department
        //Map<Department, Integer> totalByDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,
        //Collectors.summingInt(Employee::getSalary)));

        // Reduction
        int maxByReduce = Stream.of(1, 2, 3, 3, 4).reduce(0, Integer::max);
        int sumByReduce = Stream.of(1, 2, 3, 3, 4).reduce(0, Integer::sum);
        Optional<Integer> optionalSum = Stream.of(1, 2, 3, 3, 4).reduce(Integer::sum);

        System.out.println("reduce(0, accumulator) : " + maxByReduce);
        System.out.println("reduce(0, accumulator) : " + sumByReduce);
        System.out.println("Optional reduce(accumulator) : " + optionalSum.get());

        // Reduction by a combiner (useful for parallel streams)
        int concactenatedString = cities.stream().parallel().reduce(0, (sum, str) -> sum + str.length(), Integer::sum);
        System.out.println("reduce(0, accumulator, combiner) : " + concactenatedString);

        // Partitioning
        // partition a stream of elements into two categories: those that match a given predicate and those that don't 
        Map<Boolean, List<String>> partitionedByLength5 = items.stream().collect(Collectors.partitioningBy((string -> string.length() > 5)));
        System.out.println("partitioningBy String length > 5 : " + partitionedByLength5);

        Map<Boolean, Long> countPartitionedByLength5 = items.stream().collect(
                Collectors.partitioningBy((string -> string.length() > 5), Collectors.counting()));
        System.out.println("partitioningBy counting String length > 5 : " + countPartitionedByLength5);

        // Teeing
        // a collector that combines two collectors
        // takes two collectors and apply a merging function to combine the results of the first and second collector
        Stream<User> users = getUsers().stream();

        Map<String, List<User>> teedUsers = users.collect(Collectors.teeing(
                // First collector: collecting adult users
                Collectors.filtering(user -> user.getAge() >= 18, Collectors.toList()),
                // Second collector: collecting users with name length > 14
                Collectors.filtering(user -> user.getName().length() > 14, Collectors.toList()),
                // BiFunction to merge the results
                (user1, user2) -> {
                    Map<String, List<User>> map = new HashMap<>();
                    map.put("AdultsAged", user1);
                    map.put("LongNames", user2);
                    return map;
                }));
        System.out.println("Teeing (combines two collectors) : " + teedUsers);
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

        @Override
        public String toString() {
            return "name='" + name + '\'' + ", age=" + age;
        }
    }
}
