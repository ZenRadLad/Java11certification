package io;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class NewIO {
    public static void main(String[] args) throws IOException {
        // Create, traverse, read, and write Path objects and their properties using java.nio.file API
        // Read and write console and file data using I/O Streams
        // Serialize and de-serialize Java objects
        files();
        ioStreams();
        consoleObject();
        serialization();
    }

    private static void files() throws IOException {
        /* Files */
        File newFile = new File("/home/desktop/file.txt");

        File parentFolder = new File("/home/desktop");
        File file = new File(parentFolder, "/work/file.txt");

        /* Paths */
        Path pathOf = Path.of("/home/desktop/file.txt");
        Path pathGet = Paths.get("/home/desktop/work.txt");
        Path pathGet2 = Paths.get("/home", "desktop", "work.txt");

        // Path to File and vice versa
        Path fileToPath = newFile.toPath();
        File backToFile = fileToPath.toFile();

        /* Metadata using NIO */
        if (Files.exists(pathOf)) {
            System.out.println("Absolute path : " + pathOf.toAbsolutePath());
            System.out.println("isDirectory  : " + Files.isDirectory(pathOf));
            System.out.println("Parent path : " + pathOf.getParent());

            if (Files.isRegularFile(pathOf)) {
                System.out.println("File size : " + Files.size(pathOf));
                System.out.println("Last modified date : " + Files.getLastModifiedTime(pathOf));
            } else {
                try (Stream<Path> filesList = Files.list(pathOf)) {
                    filesList.forEach(path -> System.out.println(" " + pathOf.getFileName()));
                }
            }
        }

        /* Common NIO arguments */

        //check if the symbolic link itself exists, even if the target it points to does not exist
        Files.exists(pathOf, LinkOption.NOFOLLOW_LINKS);

        // Move a file (source, target, options...)
        Files.move(pathOf, pathGet, StandardCopyOption.ATOMIC_MOVE);

        // Interacting with Paths //
        // Paths like Strings are immutable
        // pathOf.resolve(pathGet); result is ignored
        Path path = Paths.get("/home/user/docs/notes.txt");

        Path root = path.getRoot(); // returns /home, returns null if relative path like getParent()

        int numberOfElementsInPath = path.getNameCount();
        Path homePath = path.getName(0);

        // subpatch(beginIndex, endIndex) returns relative path 0 - (endIndex - 1)
        Path subPath = path.subpath(1, 3); // returns users/docs

        /* Resolving paths */

        // resolve() combines two paths
        Path basePath = Paths.get("/home/user");
        Path pathToResolve = Paths.get("docs/notes.txt");

        Path resolvedPath = basePath.resolve(pathToResolve); // /home/user/docs/notes.txt

        // relativize() constructs relative path between current and given paths
        Path otherPath = Paths.get("/home/user/docs/notes.txt");
        Path relativePath = basePath.relativize(otherPath); // docs/notes.txt


        // normalize() removes redundant elements like a dot or multiple dots
        Path toNormalize = Paths.get("/home/user/../user/docs/./notes.txt");
        Path normalized = toNormalize.normalize(); // /home/user/docs/notes.txt

        // toRealPath(LinkOption...) follows symbolic links to find path on file system
        // Assuming /home/user/docs is a symbolic link to /documents
        Path pathToRealPath = Paths.get("/home/user/docs/notes.txt");
        Path realPath = pathToRealPath.toRealPath(LinkOption.NOFOLLOW_LINKS); // /documents/notes.txt

        /* Creating, Moving, and Deleting Files and Directories */

        // Create directory
        Files.createFile(Path.of("/tmp/path/of/directory"));

        // Replacing Files
        Path sourcePath = Paths.get("/home/source.txt");
        Path targetPath = Paths.get("/home/target.txt");

        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

        // StandardCopyOption.REPLACE_EXISTING
        // StandardCopyOption.COPY_ATTRIBUTES metadata will be copied with the file itself
        // StandardCopyOption.ATOMIC_MOVE move file in a single system operation

        // Copying Files into a Directory
        Path sourceFilePath = Paths.get("/home/source.txt");
        Path directoryPath = Paths.get("/home/targetdir");

        Files.copy(sourceFilePath, directoryPath.resolve(sourceFilePath.getFileName()));

        // Moving Files
        Files.move(sourceFilePath, targetPath, StandardCopyOption.ATOMIC_MOVE);

        // Deleting Files
        Path pathToDelete = Paths.get("/tmp/deletable.txt");
        Files.deleteIfExists(pathToDelete);

        // Comparing Files
        boolean isSameFile = Files.isSameFile(path, pathOf); // doesn't check content of file

        // returns -1 if identical else returns position of first differring byte
        long mismatchIndex = Files.mismatch(path, pathOf);
        if (mismatchIndex == -1) {
            System.out.println("Files are identical.");
        } else {
            System.out.println("Files differ starting at byte position: " + mismatchIndex);
        }

        // checking File Accessiblity
        Files.isHidden(path);
        Files.isReadable(path);
        Files.isWritable(path);
        Files.isExecutable(path);

        // Attribute and View types : group of related metadata for a particular file system
        // BasicFileAttributes (all filesystems), DosFileAttributes (windows), PosixFileAttributes (unix/linux)
        BasicFileAttributes fileMetadata = Files.readAttributes(path, BasicFileAttributes.class);

        System.out.println("Is a directory ? " + fileMetadata.isDirectory());
        System.out.println("Is a regular file? " + fileMetadata.isRegularFile());
        System.out.println("Is a symbolic link? " + fileMetadata.isSymbolicLink());
        System.out.println("Size (in bytes): " + fileMetadata.size());
        System.out.println("Last modified: " + fileMetadata.lastModifiedTime());


        // Modify attributes using BasicFileAttributeView
        // update last modified time by 10 seconds
        BasicFileAttributeView metadataView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes attributes = metadataView.readAttributes();
        FileTime increasedlastModifiedTime = FileTime.fromMillis(attributes.lastModifiedTime().toMillis() + 10_000);
        metadataView.setTimes(increasedlastModifiedTime, null, null);

        // Traverse/Walk a directory
        // walk(), depth limit, FileVisitOption.NOFOLLOW_LINKS (to avoid circular paths)
        // calculate
        int depthLimit = 5; // 0 depth respresents the current path

        try (Stream<Path> paths = Files.walk(directoryPath, depthLimit)) {
            long totalFilesSizeInDirectory =
                    paths.filter(p -> !Files.isDirectory(p))
                            .mapToLong(NewIO::getSize)
                            .sum();

        }

        // Searching a directory
        // Define the BiPredicate to match the file
        BiPredicate<Path, BasicFileAttributes> matcherBiFunction = (thePath, attrs) ->
                String.valueOf(thePath).endsWith(".txt") && attrs.isRegularFile();

        try (Stream<Path> paths = Files.find(directoryPath, depthLimit, matcherBiFunction)) {
            // Print out all found files
            paths.forEach(System.out::println);
        }
    }

    private static long getSize(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static void ioStreams() throws IOException {
        // I/O Streams are abstractions for reading from sources or writing to destinations.
        // They are broadly categorized into byte streams and character streams.

        /* ByteStreams and Character Streams */

        // Byte Streams

        // Reading bytes from a file using FileInputStream
        try (FileInputStream fis = new FileInputStream("test.txt")) {
            int content;
            while ((content = fis.read()) != -1) {
                // content is a byte value; cast to char to display it
                System.out.println((char) content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Writing bytes to a file using FileOutputStream
        String data = "Hello World !";
        try (FileOutputStream fos = new FileOutputStream("output.txt")) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Character Streams
        // FileReader
        try (FileReader reader = new FileReader("example.txt")) {
            int content;
            while ((content = reader.read()) != -1) {
                // content is a character
                System.out.println((char) content);
            }
        }

        // FileWriter
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(data); // Writes the string to the file
        }

        /* Input and Output Streams */
        // For binary files, you would typically use FileInputStream and FileOutputStream.
        // For text files, you'd use FileReader and FileWriter as demonstrated above.

        /* Using Files NIO2 APIs */

        // Reading a file using NIO2
        try {
            List<String> lines = Files.readAllLines(Path.of("/home/text.txt"));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Writing to a file using NIO.2
        List<String> lines = Arrays.asList("Hello", "World");
        try {
            Files.write(Path.of("/home/output.txt"), lines, StandardOpenOption.CREATE);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // RandomAccessFile
        // read/write data to a file in non sequential manner
        // allows to move to any position within af file and read from or write to it
        // useful when wanting to access and modify specific parts of a file w/o reading or writing the entire file.

        // read only mode ("r") or read/write ("rw")
        try (RandomAccessFile raf = new RandomAccessFile("example.txt", "rw")) {
            System.out.println("File pointer initially at: " + raf.getFilePointer());

            raf.writeBytes("Hello World");
            // raf.writeUTF("Hello, World!"); write using UTF encoding
            // Seeking to the 6th byte

            //sets the file pointer to a specified position within the file
            raf.seek(6);

            byte[] bytes = new byte[5];
            raf.read(bytes);

            String fileData = new String(bytes);
            System.out.println("Read from file: " + fileData);  // Output: "World"
            // Seeking to the end of the file to append data
            raf.seek(raf.length());
            raf.writeBytes("\nAppend this!");

            // Now the file pointer is moved to after the bytes that were written
            System.out.println("File pointer after writing: " + raf.getFilePointer());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consoleObject() {
        // Console class includes methods for formatting data & retrieving complex input (passwords) 
        Console console = System.console();

        if (console != null) {
            String userInput = console.readLine();
            console.writer().println("User input = " + userInput);
            char[] passwordArray = console.readPassword("Enter password = ");
            console.format("Password entered is = ", new String(passwordArray));
        } else
            System.err.println("Error: Couldn't get the Console ");

        // Reading input using System.un with Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name :");
        String name = scanner.nextLine();
        System.out.println("Name is : " + name);
        scanner.close();
    }

    private static void serialization() {
        // Serialization : converting an in-memory object to byte stream
        // new Car(); to car.txt
        // Deserialization : byte stream to object
        // car.txt to new Car();
        // A class is considered serializable if it implements the java.io.Serializable interface and
        // contains instance members that are either serializable or marked transient. All Java primitives and the String class are serializable. The ObjectInputStream and ObjectOutputStream classes can be used to read and write a Serializable object from and to an I/O stream, respectively. »


        /* Serializable */
        // To make a Java class serializable, it should implement the java.io.Serializable interface.
        // This is a marker interface (an interface with no methods) that tells the JVM it can
        // "serialize" (convert to a byte stream) instances of the class.


        /* transient */
        // indicates that a field should not be serialized. Transient fields are ignored during serialization

        /* private static final long serialVersionUID = 1 */
        // unique id for the class version used during deserialization to verify that the sender & receiver
        // of a serialized object maintain serialized compatibility.

        /* ObjectOutputStream and ObjectInputStream */
        User user = new User();
        user.setName("John");
        user.setAge(20);

        // Serialization process: Object to Byte Stream
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.txt"))) {
            oos.writeObject(user); // Serializing user object and write it to "user.txt" file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Deserialization process: Byte Stream to Object
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.txt"))) {
            User userDeserialized = (User) ois.readObject(); // Serializing user object and write it to "user.txt" file
            System.out.println(userDeserialized.getName());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        /* Deserialization */
        // to perform custom actions pre and after serialization and deserialisation use :
        // Serialization : writeObject(ObjectOutputStream oos)
        // Default serialization logic method : oos.defaultWriteObject();
        // Deserialization : readObject(ObjectInputStream ois)
        // Default deserialization logic method :  ois.defaultReadObject();
    }

    static class User implements Serializable {
        @Serial
        private static final long serialVersionUID = 12301L; // Recommended for Serializable classes
        private String name;
        private int age;
        private transient String password; // will not be serialized

        private static String hash; // static fields are also not serialized (only instance fields)

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

        @Serial
        private void writeObject(ObjectOutputStream oos) throws IOException {
            // Perform any custom pre-serialization actions

            // Default serialization logic
            oos.defaultWriteObject();

            // Perform custom actions after serializtion
        }

        @Serial
        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            // Perform any custom pre-deserialization actions

            // Default deserialization logic
            ois.defaultReadObject();

            // Perform custom actions after deserialization like init transient fields
        }
    }
}
