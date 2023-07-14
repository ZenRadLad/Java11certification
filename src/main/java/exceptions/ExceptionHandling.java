package exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionHandling {

	private static Logger logger = Logger.getLogger("MyLogger");

	public static void main(String[] args) {

		/*
		 * Object -> Throwable-> Error Exception-> SQLException | ClassNotFoundException
		 * | IOException Exception -> RuntimeException -> NPE | IllegalArgument | IndexOutOfBound
		 */
		logger();
		checkedException();
		runtimeException();
		customExceptions();
	}

	private static void logger() {
		// Set log threshold so subsequent logs of higher levels will be ignored
		logger.setLevel(Level.INFO);

		// 7 Log levels : FINEST > FINER > FINE > CONFIG > INFO > WARNING > SEVERE
		logger.info("Start logging information");
		logger.warning("Warning log example");
		logger.log(Level.SEVERE, "Severe log example");
	}

	private static void checkedException() {
		// Must be caught or explicitely propagated (often not a bug | BusinessErrors)

		// Exception, SQLException, IOException ->
		// (FileNotFoundException,FileSystemException)

		// Multi-catch = to be used for non-related exceptions
		// it prevents you from specifying redundant types in a multi-catch

		// Try-with-resources : implicitly creates a finally block to close
		// autocloseable resources

		File file = new File("./README.md");

		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String text = br.readLine();
			logger.log(Level.INFO, "First line = {0}  ", text);
		} catch (NoSuchFileException | FileNotFoundException e) {
			logger.log(Level.SEVERE, "file not found :", e);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "IOException: ", ex);
		} finally {
			logger.info("finally executed");
		}
	}

	private static void runtimeException() {
		// Unchecked (Runtime) -> may be caught but do not have to be
		// Error, OutOfMemoryError, RuntimeException (NPE,
		// IllegalArgument,IndexOutOfBounds)

		// Catching exception order => More Specific to more General (catch NoSuchFile
		// before IOException)
		try {
			String s = null;
			System.out.println(s.charAt(1));
		} catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// Catch multiple exception to apply differents actions for each exception
		} finally {
			logger.info("App stopped.");
		}
	}

	private static void customExceptions() {
		String s = null;

		if (s == null) {
			throw new BusinessException("Empty required parameter"); // add error code
		}
	}
}
