package streams_api;

import java.util.Arrays;
import java.util.List;

// Write a Java program to count the number of strings in a list that have a length greater than 3.
public class CountString {

	public static void main(String[] args) {
		
		List<String> nameList = Arrays.asList("RAJ", "JACK", "TOM", "SAM", "JOHN");
		
		long count = nameList.stream()
				.filter(name -> name.length() > 3)
				.count();
		
		System.out.println(count);

	}

}
