package streams_api;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

// Write a Java program to filter even numbers from a list, square them, and collect them into a new list.
public class Filter {
	
	public static void main(String[] arg) {
		
		List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		List<Integer> result = numList.stream()
										.filter(num -> num % 2 == 0)
										.map(num -> num * num)
										.collect(Collectors.toList());
		
		System.out.println(result);
		
		int sum = numList.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Sum of all numbers : "+sum);
		
		OptionalDouble average = numList.stream().mapToInt(Integer::intValue).average();
		System.out.println("Average of all numbers : "+average);
	}

}
