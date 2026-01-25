package streams_api;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// Write a Java program to find the first name in a list that starts with the letter "J".
public class FindLetter {

	public static void main(String[] arg) {

		List<String> nameList = Arrays.asList("RAJ", "JACK", "TOM", "SAM", "JOHN");

		Optional<String> result = nameList.stream()
								.filter(name -> name.startsWith("J"))
								.findFirst();
		
		result.ifPresent(System.out::print);
	}
}
