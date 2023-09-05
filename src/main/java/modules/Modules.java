package modules;

public class Modules {

	public static void main(String[] args) {
		modules();
		services();
		versions();
		compilationAndPackaging();
		jdeps();
		migration();
		//TODO : 
			//create module example with providers to understand fully exports and requires ..
			//Modules service parts and usage
			//jdeps outputs
	}

	private static void modules() {
		// Modules 
			// aggregates related packages and resources (imgs, xmls..)
			// hidden by default, 
			// described by module-info.java at root src folder
			// naming : com.java11certification.moduleName (reverse dns)
			// module-path : classes of modules are loaded using it instead of classpath
			// java.base is implicitly required in all modules
		
		// Creation 
			/*
				module userModule {
					require  //required modules 
					exports  //packages of this modules required in other modules;
					opens    //packges of this module to others via reflection; 
					uses     //services to consume provided by others
					provides //services exposed to others 
							with //service implementations;
					version <value>;
				}
				
				module productModule {
					requires static userModule, anotherModule;
						// optional use of userModule & anotherModule, not required at runtime
						 
					requires transitive java.logging; 
						// any module that uses productModule will also use java.logging
						// without declaring requires
					
					exports javacertification11.io;
					exports javacertification11.modules to otherModule; //only to otherModule
					opens javacertification11.datatypes:
				}
				
				module otherModule {
					requires javacertification11.modules;
					requires javacertification11.datatypes; //via reflection
				}
				
				// accessible to all other modules via reflection
				open module openToAllOthersModule {}
			*/
		
		// Benefits of using modules 
			// impose physical restrictions on module use
			// deploymenet of commont releated classes
			// smaller application deployment footprint
			// circular module dependecies are not allowed
			// encapsulation cannot be bypassed using reflection
			// restrict when specific code can be used
		
		
		// Types of modules 
				// Application = feature modules, third party dependencies
				// Automated   = jars can be placed in modulePath without descriptor 
								// (use pre-Java 9 jars)
				// Unamed = any jar or class on the classpath belongs here
							//it can read/export all modules
				// Platform = jdk modules
		
		// Built-in JDK modules
				// Java SE = java.base, java.se, java.sql, java.logging
				// JDK = jdk.httpserver, jdk.console, jdk.jshell, jdk.jdeps
				// java --list-modules = cmd to list existing set of modules
	}

	private static void services() {
		
		// Service 
			// comprises an interface/abstract class and 1..* implementations
			// modules can produce and consume services instead of exposing all 
				// public classes to selected packages
		
		// Directives 	
			// "provides" <serviceIface> with <classes>
			// "uses"  <serviceIface> = specifies which iface that defines a service
						// this module will consume
		
			/*
				module provider {
					requires service;
					provides service.interfaces.MyIface 
						with provider.implem.IfaceImp;
				}

				module service {
					exports service.interfaces;
				}
				
				module application {
					requires service; 
						//no need to require module provider where 
						// its dynamically discovered via ServiceLoader
					uses service.interfaces.MyIface;
				}
				
				// dynamic discovery by service consumer
				public static void mai(String... args){
					ServiceLoader<MyIface> serviceLoader = ServiceLoader.load(MyIface.class);
					MyIface iface = serviceLoader.findFirst().get();
				}
			*/
	}
	
	private static void versions() {
		// Multi-Release modules archives
		
		// Only one copy of a module can be placed into a module-path
			// Multi-Release jar can be used to support different versions
			// of code for different versions of Java
		
		// Inside META-INF folder outside of modules folders
		// declare MANIFEST.MF 
			// Manifest-Version: 1.O
			// Multi-Release: true
		
		// MyModule > package > MyClass.java
		// module-info.java
		// META-INF > MANIFEST.FM
		//  		> versions
		//			---- Specific version of code (ex : for java 10)
		//				> package > MyClass.java
		//				> module-info.java
		//			---- Another Specific version of code (ex : for java 13)
		//				> package > MyClass.java
		//				> module-info.java
	}
	
	private static void compilationAndPackaging() {
		// Compilation   (.java --> .class)
			// java --module-path <pathsToOtherModules> 
			//		-d <compiledClassesOutputDirectory>
			//		-sourcePath <PathToSourceCode>
		
		// Package module into JAR file (.class -> .jar)
			// java --create -f <pathAndNameOfJarFile>	
			//		--main-class <package.Main.class>
			//		-C <PathToCompileModuleCode>
		
		// Verify packaged module = description of compiled module 
			// info about modules it contains, exports, requires..
			// java --module-path <PathToCompiledModule>
			// 		--describe-module <ModuleName> . 
			//	(dot after ModuleName includes all file from compiled code folder)
		
		// Execute module
			// java --p || --module-path <pathToModules>	
			//		-m  || --module <ModuleName/package.Main.class> args
		
		// Execute non-modular app (reminder)
			// java --cp || --class-path <pathToJars> <packageName.Main> args	
	}
	
	private static void migration() {
		// Legacy jars placed in module-path are treated as Automatic modules
		// => can be referenced as modules by any module using : "requires" JarFileName;
		// => (temp) specify in MANFIEST.MF "Automatic-Module-Name: MyJar.package.repo" to avoid issues
		// => create module-info.java for legacy JAR 
		
		
		// If migration is completed and the app is fully modularized 
		// use "jlink" command to create a custom runtime image (light JVM) 
		// Self-contained without needing to install a traditionnal JVM :
			// Optimized for space and speed
			// Enables faster search and class loading compared to classpath JARs
		
		// jlink --module-path <compiledMothsPath & %JAVA_HOME/jdmods folder>
		//		 --add-modules <listOfModuleNames>
		//		 --bind-services
		//		 --launcher <command-name>=<moduleName> (alias for running mainClass)
		//		 --output <nameOfRuntimeImage>
		// 		 --include-locales=<ListOfLocales>
		// 		 --strip-debug
		// 		 --no-header-files
		// https://docs.oracle.com/javase/9/tools/jlink.htm
		
		
		// This command creates a JIMAGE with the following structure :
			//bin > java executables
			// 	  > launcher
			//code
			//conf > configuration
			//files 
			//include > C/C++ header files
			//legal > legal notices
			//lib > modules (only modules used by the app are included)
		
		// Examine a JIMAGE
			// <image>/bin/java --version
			// <image>/bin/java --list-modules
		
		// TODO : Top-down = 


		
		
		// Bottom-up =
		
	}
	
	private static void jdeps() {
		// jdeps (javaDependencies) 
			// command to list required and internal packages 
			// Dependency analysis tool for java bytecode (classFiles and JARs)
			
		// Which JDK APIS my project is using ?
		
		// jdeps myJar.jar --class-path 'libs/*' -recursiv Jar.jar -> java.base
			// myJar.jar -> java.base
			// myJar.jar -> java.sql
			// myJar.jar -> libs/javaassist.jar
			// myJar.jar -> libs/spring-boot.jar
		
		// jdeps output can be configured by specifying multiple options
		// https://docs.oracle.com/javase/9/tools/jdeps.htm
	}
}