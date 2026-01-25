package streams_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicates {
	
	public static void main(String[] args) {
		
		List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 4 ,6 , 8, 10));
		System.out.println("Actual numbers:"+numbers);
		
		// by using stream
		Set<Integer> collect = numbers.stream().filter(num -> Collections.frequency(numbers, num) > 1).collect(Collectors.toSet());
		System.out.println("Find duplicates using stream:"+collect);
		
	}

}
