package security;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.io.FilePermission;
import java.net.SocketPermission;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;

public class CodeSecurity {

    public static void main(String[] args) {
        // Official Secure Coding Guidelines : https://www.oracle.com/java/technologies/javase/seccodeguide.html
        denialOfService();
        injections();
        sensitiveData();

        //TODO :
        // doPrivileged() Q&As
    }

    private static void denialOfService() {
        // Caused by unchecked and unrestricted resource utilizations
        // File or code construct grows too large
        // Service or connection is overwhelmed with bogus requests

        // Prevention :
        // permissions to restrict access to code that consume resources
        // input validation (before deserialization)
        // verify expected file sizes and lengths of streams
        // resource release and timeout lengthy operations
        // excessive resource consumption monitoring
        // set limits on data when decompressing files
        // terminate opertations that process excessive amount of data

        // Security policies (files located under JAVA_HOME/conf/security/
        // Update java.security to reference permissions to be defined
        // in java policies

        // java.security.Permission = enables verification of permissions
        // Check poermissions before performing an action that accesses resources
        FilePermission filePermission = new FilePermission("/CodeSecurity", "read, write");
        SocketPermission socketPermission = new SocketPermission("localhost:3000", "accept, connect, listen");

        try {
            //AccessController.checkPermission(filePermission);
            //AccessController.checkPermission(socketPermission);
        } catch (SecurityException e) {
            // access denied by policies
        }

        // Policies can be used to permit access to resources to execute privileged actions

        // AccessController.checkPermission() stops checking if it reaches a caller marked as privileged
        // via doPivileged()

        String file = "/CodeSecurity";

        //Deleting file even it file has only r+w permissions
        //doPrivileged allow an isolated code to be executed above defined permissions
        /*AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            try {
                Files.delete(Path.of(file));
            } catch (IOException iox) {
                iox.printStackTrace();
            } finally {
                return null;
            }
        });*/


        // I/O Operations :
        // To protect against directory traversal attacks = guessing directory
        //structure using ../path relative paths) :
        //  Remove redundant elements from path and convert it to canonical form
        // by using : normalize() and toRealPath(linkOption)
        // Use whitelist to specify which values are allowed (better than blacklisting)

        String dirName = "read dir Name";

        if (dirName.equals("allowedDirName") || dirName.equals("anotherAllowedDirName")) {
            // Files.walk(Path.of(dirname)) ..
        }
    }

    private static void injections() {
        // Code injections caused by lack of input value validation and sanitation
        // input validation must occur after any defensive copying of that input

        // SQL :
        // Use PreparedStatment or Statment.enquoteLiteral(param)
        // use JPA and BeanValidation APIs

        // Javascript :
        // Arbitray code can be passed as param, cookie, header and url value
        // validate and sanitize every value processed by the browser
        // use OWASP library org.owasp.html.Sanitizers

        // XML :
        // External entity injection (XXE)
        // Untrusted XML input containing a reference to an external entity
        // is processed by a weakly configured XML parser
        // Always disable DTDs (External entities)
        // Set XMLConstants.FEATURE_SECURE_PROCESSING
        // SEE : https://rules.sonarsource.com/java/tag/owasp/RSPEC-2755
    }

    private static void codeProtectionBestPractices() {
        //Enforce tight encapsulation
        // use Java modules to protect classes from reflection
        // use private and protected as much as possible

        //Make objects as immutable as possible
        // use final
        // operate on cloned objects (when overidding inherited object
        // that can modify parent object)


        // Cloning objects to apply modifications on copied object for
        // preserving the state of the original object :

        // Shallow Copy = default close(), any change made to interal objects
        // of the cloned object will be reflected on the original object

        // Deep Copy = override clone() and call clone() on internal objects,
        // cloned object is indepent of original object

        var codeSecurity = new ArrayList<>();
        var codeSecurityCopy = codeSecurity.clone();
        var codeSecurityCopy2 = new ArrayList<>(codeSecurity);

        //Class and method design
        // use final and private classes and methods
        // use factory methods to perform validation before invoking constructors
        // don't invoke overridable methods from constructors
        // Beware of changes to superclass method implementations
        // Beware of introduction of new methods by a superclass
        // use interfaces and composition over inheritance

        // Protect byte-code against tampering
        // don't disable byte code verification

        // Use value guards against :
        // overflowed number space = use xxxExact() of Math class
        //throw ArithmeticException
        // bad floating point values  =
        // isInfinite() : positive or negative floating point number infinity
        // isNan() : division by 0.0 or infities - infinities
        // null references  = Optional wrapper if(Optional<Product>.isPresent())
    }

    private static void sensitiveData() {
        // Unsubclassable class with composed behavior
        class Behavior {
        }

        final class SensitiveClass {

            private final Behavior behavior;

            // Hide constructor
            private SensitiveClass(Behavior behavior) {
                this.behavior = behavior;
            }

            // Guarded construction
            public SensitiveClass newSensitiveClass(Behavior behavior) {
                // validate any arguments ...
                // perform security checks ...
                return new SensitiveClass(behavior);
            }
        }

        // Scramble data : MessageDigest digest()
        // do not log sensitive information (reconstruct error messages)
        // Encrypt and Decrypt values (javax.crypto)

        final String text = "text to be encrypted ! ";

        try {
            SecretKey key = KeyGenerator.getInstance("AES").generateKey();
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            // encryption
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] textToByteArray = text.getBytes();
            byte[] encryptedTextValue = cipher.doFinal(textToByteArray);
            System.out.println("Before encryption : " + text);
            System.out.println("After encryption : " + encryptedTextValue);

            // decryption
            GCMParameterSpec gcmParamSpec = cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
            cipher.init(Cipher.DECRYPT_MODE, key, gcmParamSpec);
            byte[] decryptedTextValue = cipher.doFinal(encryptedTextValue);
            System.out.println("Decryption from encrypted chain : " + decryptedTextValue);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException iex) {
            iex.printStackTrace();
        } catch (IllegalBlockSizeException ibe) {
            ibe.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException ipe) {
            ipe.printStackTrace();
        } catch (InvalidAlgorithmParameterException iape) {
            iape.printStackTrace();
        }

        // Guard sensitive data during serialization :
        // Declare sensitive fields transient
        // Define the serialPersistentFields array field appropriately
        // Implement writeObject() and use ObjectOutputStream.putField selectively
        // Implement writeReplace() to replace the instance with a serial proxy
        // Implement the Externalizable interface
        // View deserialization the same as object construction
    }
}