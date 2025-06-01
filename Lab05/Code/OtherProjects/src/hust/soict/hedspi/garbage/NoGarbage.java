package hust.soict.hedspi.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
	public static void main(String[] args) throws IOException {
		String filename = "C:\\Users\\Admin\\OneDrive\\Desktop\\OOP Lab\\OtherProjects\\test.txt" ;
		byte[] inputBytes = { 0 };
		long startTime, endTime;

		inputBytes = Files.readAllBytes(Paths.get(filename));
		startTime = System.currentTimeMillis();
		StringBuilder output = new StringBuilder();
		for (byte b : inputBytes) {
			output.append((char) b);
		}
		endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
		// System.out.println(outputStringBuilder.toString());
	}
}