package streams_api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsAPI2 {
	
	public static void main(String[] args) {
		
		List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<Integer> unOrderedList = Arrays.asList(7, 2, 8, 1, 5, 3, 6, 10, 9, 4);
		String title = "java";
		List<String> courseList = Arrays.asList("java", "c", "python", "c#");
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Separate odd and even numbers?");
		Map<Boolean, List<Integer>> result = numList.stream().collect(Collectors.partitioningBy(num -> num%2 == 0));
		System.out.println("Odd number: "+result.get(false)+" || Even numbers: "+result.get(true));
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Frequency of each character in string"); 
		Map<Character, Long> result2 = title.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		result2.forEach((characters, count) -> System.out.print("[Char '"+characters+"' occurs at "+count+" times] "));
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Sort list in reverse order"); 
		List<Integer> result3 = numList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		System.out.println(result3);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Print multiples of 5");
		List<Integer> rsult4 = numList.stream().filter(num -> num%5 == 0).collect(Collectors.toList());
		System.out.println(rsult4);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Merge two unsorted arrays into single sorted array");
		int[] arr1 = {2, 5, 1, 7, 2, 7, 4, 9, 10, 3};
		int[] arr2 = {17, 12, 18, 5, 19, 25};
		int[] result5 = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).sorted().distinct().toArray();
		System.out.println(Arrays.toString(result5));
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Find 3 max and 3 min number in a list");
		List<Integer> sortedList = unOrderedList.stream().sorted().collect(Collectors.toList());
		List<Integer> minlist = sortedList.subList(0, 3);
		List<Integer> maxlist = sortedList.subList((sortedList.size() - 3), sortedList.size());
		System.out.println("3 minimum numbers are "+minlist+" || 3 maximum numbers are "+maxlist);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Sort list of string in increasing order of their length");
		List<String> result6 = courseList.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
		System.out.println(result6);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Sum & Avg of all numbers of an array");
		int[] arr3 = {2, 5, 1, 7, 2, 7, 4, 9, 10, 3};
		IntSummaryStatistics summaryStats = Arrays.stream(arr3).summaryStatistics();
		System.out.println("sum value: "+summaryStats.getSum()+" || Average value: "+summaryStats.getAverage());
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> sort & reverse in integer array using java stream");
		int[] arrResult = Arrays.stream(arr3).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
		System.out.println(Arrays.toString(arrResult));
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> reverse an integer array using java IntStream");
		int[] arrResult2 = IntStream.range(0, arr3.length).map(i -> arr3[arr3.length - 1 - i]).toArray();
		System.out.println(Arrays.toString(arrResult2));
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Palindrom program");
		String word = "madam";
		boolean match = IntStream.range(0, word.length()/2).allMatch(i -> word.charAt(i) == word.charAt(word.length()-1-i));
		System.out.println(match == true ? word+" is a palindrome" : word+" is not a palindrome");
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Last Element of an array");
		int asInt = Arrays.stream(arr3).skip(arr3.length-1).findFirst().getAsInt();
		System.out.println("Array: "+Arrays.toString(arr3)+" || The last element is "+asInt);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Frequency of each element in array");
		Map<Integer, Long> collect = Arrays.stream(arr3).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		collect.forEach((key, value) -> System.out.println("'"+key+"' occurs at "+value+" times"));
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Join list of strings with prefix, sufix and delimter");
		String collect2 = courseList.stream().collect(Collectors.joining(",", "[", "]"));
		System.out.println(collect2);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Anagram program");
		String word1 = "raja";
		String word2 = "jaar";
		String collect3 = word1.chars().sorted().mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
		String collect4 = word2.chars().sorted().mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
		System.out.println(collect3.equalsIgnoreCase(collect4)?true:false);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Find duplicates");
		List<Integer> duplicateElements = Arrays.asList(1, 2, 3, 4, 5, 6, 2, 3, 4);
		Set<Integer> uniqueElements = new HashSet<>();
		List<Integer> collect5 = duplicateElements.stream().filter(num -> !uniqueElements.add(num)).collect(Collectors.toList());
		System.out.println(collect5);
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Fibonacci series ");
		Stream.iterate(new int[] {1, 2}, fib -> new int[] {fib[1], fib[0]+fib[1]}).limit(20).forEach(fib -> System.out.print(fib[0]+" "));
		
		System.out.println("-------------------------------------------------------------");
		System.out.println(">> Compounding effect in chess board");
		Stream.iterate(1L, n -> n*2).limit(64).forEach(num -> System.out.print(num+" "));
	}

}
