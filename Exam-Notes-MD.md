## Java Basics

- **Bitwise Operators**: Manipulate individual bits of a number. Used with integer types.
  - `&`: Bitwise AND (true if both bits are 1)
  - `^`: Bitwise exclusive OR (true if bits are different)
  - `|`: Bitwise inclusive OR (true unless both bits are 0)
- **Shift Operators**: Shift bits left or right, thereby multiplying or dividing the number by two.
  - `~`: Unary bitwise complement
  - `<<`: Signed left shift
  - `>>`: Signed right shift
  - `>>>`: Unsigned right shift
- **Constants in Switch Cases**: Switch case values must be constants, literal values, or final variables.
- **Instance Initialization Order**: Class static fields, static initializers, instance fields, instance initializers, constructors.
- **Transient Field Defaults**: Default value for transient fields is null.
- **Locale and Resource Bundles**: Uses default locale bundle if key not found in specified bundle.
- **Inner Class Instantiation**: Requires an instance of its outer class, e.g., `new OuterClass().new InnerClass()`.
- **Static Interface Methods**: Cannot call default, abstract, or non-static private interface methods.
- **Generic Wildcards**: Cannot occur on the right side of assignments.
- **Array Initializer Block**: Cannot specify length when using initializer block (e.g., `new String[3]{ "a", "b", "c"}` is invalid).
- **Inheritance of Constructors**: Child must declare a constructor with `super()` if parent only has args constructors.
- **Static and Instance Initializers**: Can access static variables.
- **Immutable Classes**: Non-extendable outside class declaration, data cannot be modified by the caller.
- **Inheritance and Interface Implementation**: Inheriting ambiguous fields/methods doesn't cause issues unless referred to ambiguously.
- **Timezones**: Subtracting hours from -GMT is equivalent to adding them.
- **Sealed Classes/Interfaces**: Must have a `permits` clause.
- **Type Bounds in Generics**: "`? super Number`" indicates a super-type of `Number`.
- **Interface Extensions**: Interfaces can extend other interfaces.
- **Division by Zero in Java**: Integral division by zero throws `ArithmeticException`; floating-point division results in `POSITIVE_INFINITY` or `NEGATIVE_INFINITY`.

## Collections and Data Structures

- **HashMap vs Hashtable**: `HashMap` allows null keys/values; `Hashtable` does not.
- **Abstract Classes and Interfaces**: An abstract class may not implement methods of an interface.
- **List.subList**: Returns a view of the original list; modifications reflect in the original list.
- **Stream Operations**: Important to know what `stream()` methods return and their order.
- **Stream.peek()**: Performs action on elements, expects a consumer lambda (requires terminal operation).
- **Summary Statistics**: Computation of count, min, max, sum, and average.
- **Queue**: FIFO structure. Methods: `offer(e)/add(e)` (add), `poll()/remove()` (remove).
- **Stack**: LIFO structure. Methods: `push(e)` (add), `pop()` (remove).
- **NavigableMap**: Extends `SortedMap` for navigation methods in ascending/descending key order.

## Exception Handling

- **Try-Catch-Finally Behavior**: `finally` exceptions override catch clause exceptions.
- **Try-with-Resources Statement**: Resource must be marked final or effectively final.
- **Catch Block Exceptions**: Can't be caught by subsequent catch blocks in the same method.
- **Resource Closure**: Executed after resource closure in try-with-resources.
- **RuntimeExceptions**: No need to catch (e.g., `IndexOutOfBoundsException`).
- **Unchecked vs. Checked Exceptions**: Unchecked don't need explicit catch; Checked must be caught or declared.
- **Optional Class Methods**: `Optional.of()` (throws NPE for null), `Optional.ofNullable()` (empty for null), `optional.orElse()` and `optional.orElseGet()` (fallbacks).

## NIO

- **Path.resolve()**: Combines paths; behavior varies with path type (absolute, relative, empty).
- **Path Methods**: `getRoot()`, `getName(index)`, etc.
- **Files.move/copy**: Static utility methods not used with try-with-resources.
- **Reader and BufferedReader**: `FileReader` for low-level operations; `BufferedReader` adds high-level methods like `readLine()`.
- **System.console()**: Returns null if Console not available.
- **path1.resolveSibling(path2)**: Replaces filename portion of first path with second path.
- **file.mkdirs()**: Creates non-existing parent directories.
- **NoSuchFileException**: Thrown by `Files.delete(path)` if file does not exist.
- **BufferedReader Wrapping**: Can wrap any Reader.
- **Character Size**: Depends on default encoding of platform.

## JPMS

### Overview

- **Basic Structure**: `module-info.java` mandatory for declaring module's name.
- **Module and Package Relationship**: Modules contain packages.
- **Exports and Dependencies**: Only packages exported; dependencies at module level.
- **Service Usage**: Module using a service must require the module defining the service interface.

### Module Operations and Tools

- **Running Modules**: Use `--module-path` and `--module` options.
- **Jmod Tool**: Includes create, extract, list, describe, hash.
- **Jdeps Tool**: Analyzes dependencies, uses `--module-path`, `--class-path`, or `-cp`.

### Module Accessibility and Interactions

- **Unnamed vs. Named Modules**: Unnamed can't access named modules.
- **Automatic Modules**: Access classes from all modules.
- **Direct Usage**: Must use named or automatic JARs.
- **Module Resolution**: Use `java --show-module-resolution`.
- **Module Source Path**: Set with `--module-source-path` in `javac`.
- **Customizing Module Access**: Use `--add-reads` and `--add-exports`.

## JDBC

### Connection Management

- **JDBC URL Format**: `jdbc:<subprotocol>:<subname>`.
- **AutoCommit Behavior**: `connection.setAutoCommit(true)` auto-commits transactions.
- **Driver Registration**: Automatic in JDBC 4.0+.

### PreparedStatement and SQLException Handling

- **setNull in PreparedStatement**: Use `ps.setNull(index, Types.XXX)` for non-string types.
- **Parameter Setting**: Must set parameters before executing query.
- **SQL Injection Protection**: `PreparedStatement` preferred over `Statement`.
- **After Connection Closure**: Can't access `Statement` and `ResultSet` from closed `Connection`.
- **Parameter Retention**: `PreparedStatement` retains parameter values until closed.

## Enums

- **Interface Implementation**: Enums can implement interfaces.
- **Declaration Order**: Constants first.
- **Constructor Visibility**: Implicitly private.
- **Implicit Constructor**: Provided if not declared.
- **Final and Non-extendable**: Enums can't be extended.
- **Singleton Nature**: Can't clone enums.
- **Built-in Methods**: `values()`, `valueOf(String)`, `ordinal()`, `name()`.
- **toString() Method**: Overridable, returns enum's name by default.
- **Comparable Implementation**: Follows ordinal values.
- **Definition Scope**: No access modifiers in methods/constructors.
- **Field Access Restrictions**: Can't access non-final static fields in constructors.

## Functional/Lambda

- **Abstract Method Source**: Can be in interface or super interface.
- **Single Abstract Method**: Required for functional interfaces.
- **Variable Access**: Must be final or effectively final.

## Concurrency

- **CopyOnWriteArrayList**: Thread-safe ArrayList variant.
- **java.util.concurrent API**: Manages concurrent tasks (Executors, ConcurrentHashMap, synchronization aids).
- **wait() vs sleep()**: `wait()` releases lock, `sleep()` does not.
- **Deadlock**: Threads block forever, waiting for each other.
- **Livelock**: Active threads make no progress.
- **Thrashing**: More time in overhead than task execution.
- **Starvation**: Thread indefinitely denied resources.
- **Thread Safe Classes**: Safe in multithreaded environments (Vector, Hashtable).
- **UnsupportedOperationException**: For unsupported operations.
- **Worker Threads (Runnable/Callable)**: Execute tasks.
- **Identifying Threading Issues**: Check for deadlocks, livelocks, synchronization issues.
- **Executor Service**: Manages threads, offers thread pools.
