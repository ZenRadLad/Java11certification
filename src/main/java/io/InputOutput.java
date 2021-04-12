package io;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributes;
import java.time.Instant;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class InputOutput {

	private static Path poemPath = Paths
			.get("../../resources/poem.txt");
	private static Path anotherFilePath = Paths
			.get("shakespeare.txt");
	private static Path javaFilePath = Path.of("C", "workspace", "Java11certification", "src", "main", "java",
			"io", "Files.java");

	public static void main(String[] args) throws IOException, InterruptedException {
		readWriteFiles();
		consoleIOStreams();
		serializationTechniques();
		fileSystemObjects();
		paths();
	}

	private static void readWriteFiles() throws IOException {
		// Read
		StringBuilder fileContent = new StringBuilder();

		Files.lines(poemPath).forEach(l -> fileContent.append(l));

		System.out.println("File Content : " + fileContent);

		// Write
		try (BufferedWriter writer = Files.newBufferedWriter(anotherFilePath, StandardCharsets.UTF_8)) {
			writer.write("To be, or not to be. That is the question.");
		}
	}

	private static void consoleIOStreams() {
		// Use java.util.Scanner to perse input in console
		Scanner sc = new Scanner(System.in);

		String inputText = null;
		System.out.print("Enter a name : ");
		inputText = sc.nextLine();
		System.out.println("You entered  : " + inputText);

		// Use java.io.Console
		Console console = System.console();
		if (console == null) {
			System.out.println("Console is not Supported");
		} else {
			PrintWriter printWriter = console.writer();
			printWriter.println("Using PrintWriter and Console.writer to print text in the console");
			printWriter.println("Using Console.readLine() to input text : ");
			String anotherInput = null;
			char[] inputPassword = null;
			anotherInput = console.readLine();
			inputPassword = console.readPassword();
			printWriter.println("Using PrintWriter and Console.writer you entered : " + anotherInput);
			printWriter.println("Using Console.readPassword() you entered  : " + inputPassword);
		}
	}

	private static void serializationTechniques() {
		// Serialization : write objects from memory into a stream in a binary format
		// Deserialization : read objects from a the stream
		// Use cases : Sending objects across Rest APIs, memory swap
		// 		Not a suitable for long term storage, serialization writes actual binary
		//		compiled version version of the code direclty into JVM memory, if code is recompiled,
		// 		previously serialized objects would become invalid as they would no longer match new code

		// java.io.Serilizable marker interface
		class Product implements Serializable { // local class (class inside a method)

			// ObjectInputStream checks the serialVersionUID to ensure that the class
			// definition used is the same as the class definition used by the java runtime at the time
			// when the object was serilized and throws an InvaldClassException in case of mismatch
			private static final long serialVersionUID = 1L;

			// new code version
			// private static final long serialVersionUID = 2L;

			private transient long id; // exclude this field from serialization
			private String name;
			private double price;

			Product(long id, String name, double price) {
				this.id = id;
				this.name = name;
				this.price = price;
			}

			// Customize Serialization Process
			private void writeObject(ObjectOutputStream out) throws IOException {
				out.defaultWriteObject();
				// add timestamp to the output
				out.writeObject(Instant.now());
			}

			private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
				in.defaultReadObject();
				// id = recalculateTransientId();
			}
		}

		// the Product instances will be deeply copied
		// SerializationException will be thrown if a class doesn't implements
		// Serializable tries to serilize its fields
		Product product = new Product(1, "Smartphone", 124);

		// ObjectInputStream : reads serializable object from a stream
		// ObjectOutputStream : writes serilizable object to a stream

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("swap"))) {
			// write object into a file "swap" and clear object reference
			out.writeObject(product);
			product = null;
		} catch (FileNotFoundException fnde) {
			fnde.printStackTrace();
		} catch (IOException iox) {
			iox.printStackTrace();
		}

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("swap"))) {
			// read object from "swap" file
			product = (Product) in.readObject();
		} catch (FileNotFoundException fnde) {
			fnde.printStackTrace();
		} catch (IOException iox) {
			iox.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		// Consider protecting serialized information by generating an object
		// hash/encryption : 
			// MessageDigest.getInstance("SHA-256") => MessageDigest.digest(..)
	}

	private static void fileSystemObjects() throws IOException {
		// Handle file system objects using java.nio.file API
		// Path : represents files and folders
		// Files : provides operations that handle path objects
		// FileSystem : describes available filesystems and their props

		// FileSystem
		FileSystem fs = FileSystems.getDefault();
		fs.getFileStores().forEach(s -> System.out.println("FileSystems : " + s.type() + " " + s.name()));

		fs.getRootDirectories().forEach(d -> System.out.println("RootDirectories : " + d));
		String separator = fs.getSeparator();
		System.out.println("FileSystem separator (/ or \\): " + separator);
	}

	private static void paths() throws IOException, InterruptedException {

		// Paths
		Path currentPath = Path.of(".");
		Path parentDirectoryOfFile = poemPath.getParent();
		Path resolveFromParent = parentDirectoryOfFile.resolve("poem.txt");
		Path resolveSibling = parentDirectoryOfFile.resolveSibling("poem.txt");
		Path normalizedPath = poemPath.normalize();
		Path verifiedPath = poemPath.toRealPath();
		Path betweenTwoFiles = poemPath.relativize(anotherFilePath);

		System.out.println("Paths.get : " + poemPath);
		System.out.println("Path.of : " + javaFilePath);
		System.out.println("Path.of(\".\") : " + currentPath);
		System.out.println("Path.getParent() : " + parentDirectoryOfFile);
		System.out.println("PrentDirectory.resolve(\"poem.txt\") : " + resolveFromParent);
		System.out.println("PrentDirectory.resolveSibling(\"poem.txt\") : " + resolveSibling);
		System.out.println("Path.normalize() = removes redundant elements of the paths : " + normalizedPath);
		System.out
				.println("Path.toRealPath() = like normalize() but throws exception if !pathExists : " + verifiedPath);
		System.out.println(
				"Path.relativize(anotherPath) = constructs relative path between two paths: " + betweenTwoFiles);

		// Files = operations on path objects

		// Create symbolic link (shortcut, needs privilege to create symlink)
		Path symlink = Files.createSymbolicLink(currentPath, anotherFilePath);

		System.out.println("Files.createSymbolicLink(p1, p2) : " + symlink);

		// List files in a path
		Path mainJavaPath = Paths.get("C:\\DevEnv\\workspace\\Java11certification\\src\\main\\java\\");

		Files.list(mainJavaPath).forEach(p -> System.out.println("Files.list : " + p));

		// Similar to list plus it goes into all subfolders and their subfolders
		Files.walk(mainJavaPath).map(Path::toString).filter(f -> !f.endsWith("txt"))
				.forEach(p -> System.out.println("Files.Walk with filter : " + p));

		Path readSymLink = Files.readSymbolicLink(symlink);
		System.out.println("Files.readSymbolicLink(symLink) : " + readSymLink);

		// Retrieve path object properties
		System.out.println("Files.isDirectory(path) : " + Files.isDirectory(mainJavaPath));
		System.out.println("Files.isExecutable(path) : " + Files.isExecutable(mainJavaPath));
		System.out.println("Files.isHidden(path) : " + Files.isHidden(mainJavaPath));
		System.out.println("Files.isReadable(path) : " + Files.isReadable(mainJavaPath));
		System.out.println("Files.isWritable(path) : " + Files.isWritable(mainJavaPath));
		System.out.println("Files.isRegularFile(path) : " + Files.isRegularFile(mainJavaPath));
		System.out.println("Files.isSymbolicLink(path) : " + Files.isSymbolicLink(mainJavaPath));
		System.out.println(
				"Files.isSameFile(p1, p2) = ex : check if absolute and relative paths points to the same file : "
						+ Files.isSameFile(mainJavaPath, poemPath));
		System.out.println("Files.probeContentType(txtFilePath) : " + Files.probeContentType(poemPath));

		// java.nio.file.attribute.PosixFileAttributes read and setLastModifiedTime()..
		PosixFileAttributes posicxAttr = Files.readAttributes(poemPath, PosixFileAttributes.class);
		System.out.println("PosixFileAttributes.creationTime() : " + posicxAttr.creationTime());
		System.out.println("PosixFileAttributes.lastModifiedTime() : " + posicxAttr.lastModifiedTime());
		System.out.println("PosixFileAttributes.owner() : " + posicxAttr.owner());

		// Create Paths
		Path newPath = Path.of("./newParentFolder/newfolder");

		if (Files.notExists(newPath)) {
			Files.createDirectories(newPath);
		}

		Path newFile = newPath.resolve("../yoo.txt").normalize();
		Files.createFile(newFile);

		// write text into file
		Files.writeString(newFile, "Lorem ipsum form Files.writeString");

		// read text from file
		Files.lines(newFile, StandardCharsets.UTF_8).forEach(l -> System.out.println(l));

		// Delete Path = cannot delete an non empty dir => exception
		// use Files.walk(.. Files.delete(path)) (empty dirs then delete them)
		Path tempFolder = Files.createTempDirectory("TEMP");
		Files.deleteIfExists(tempFolder);

		// Copy = creates replica of
		Path targetPath = Path.of("../");

		Path copy = Files.copy(poemPath, targetPath.resolve(poemPath), StandardCopyOption.COPY_ATTRIBUTES,
				StandardCopyOption.REPLACE_EXISTING);

		// Move = Deletes path after copying it
		Path move = Files.move(poemPath, targetPath, StandardCopyOption.ATOMIC_MOVE);

		// StandardCopyOption.ATOMIC_MOVE = undo entire move if error occured

		System.out.println("File copied to : " + copy);
		System.out.println("File moved to : " + move);

		// Create and extract Zip Archive (java.util.zip.ZipInputStream, ZipOutputStream)
		Path zipPath = Path.of("/myZip.zip");
		try (ZipOutputStream zoutStream = new ZipOutputStream(Files.newOutputStream(zipPath))) {
			zoutStream.setLevel(Deflater.DEFAULT_COMPRESSION);
			Files.walk(targetPath).forEach(p -> {
				ZipEntry zipEntry = new ZipEntry(targetPath.toString());
				try {
					zoutStream.putNextEntry(zipEntry);
					zoutStream.write(Files.readAllBytes(p));
					zoutStream.closeEntry();
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		// Access HTTP resources java.net.http
		// download index.html into a path
		URI uri = URI.create("https://google.com");
		HttpRequest req = HttpRequest.newBuilder(uri).GET().build();
		HttpClient client = HttpClient.newHttpClient();

		// Channel the http response to a path
		HttpResponse<Path> result = client.send(req, HttpResponse.BodyHandlers.ofFile(poemPath));
		System.out.println("Http response : " + result.toString());
	}
}