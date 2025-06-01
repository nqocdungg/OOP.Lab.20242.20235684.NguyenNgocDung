package hust.soict.hedspi.garbage;

public class jvm {
	    public static void main(String[] args) {
	        System.out.println("Java version: " + System.getProperty("java.version"));
	        System.out.println("Java vendor: " + System.getProperty("java.vendor"));
	        System.out.println("OS name: " + System.getProperty("os.name"));
	        System.out.println("OS architecture: " + System.getProperty("os.arch"));
	        System.out.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
	        System.out.println("JVM memory max (MB): " + Runtime.getRuntime().maxMemory() / (1024 * 1024));
	        System.out.println(System.getProperty("java.vm.name"));
	        System.out.println(System.getProperty("java.vm.version"));
	        System.out.println(System.getProperty("java.vm.vendor"));

	    }

}
