package streams_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsAPI {

    public static void main(String[] args) {

        // **********************************************************************************************************
        System.out.println("--- GroupBy starting char of string");
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Tom", "Jerry", "Sam", "Mike", "Max");

        Map<Character, List<String>> groupbyStartChar =
                names.stream()
                     .collect(Collectors.groupingBy(name -> name.charAt(0)));

        System.out.println(groupbyStartChar);


        // **********************************************************************************************************
        System.out.println("--- PartitionBy length greater than 4");
        List<String> namesOne = Arrays.asList("John", "Jane", "Jack", "Tom", "Jerry", "Sam", "Mike", "Max");

        Map<Boolean, List<String>> partitionbyLength =
                namesOne.stream()
                        .collect(Collectors.partitioningBy(name -> name.length() > 4));

        System.out.println(partitionbyLength);


        // **********************************************************************************************************
        System.out.println("--- Frequency of Words Using Stream API");
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        Map<String, Long> frequency =
                words.stream()
                     .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        System.out.println(frequency);


        // **********************************************************************************************************
        System.out.println("--- Filter Even Numbers and Square Them");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        List<Integer> evenNumbers =
                numbers.stream()
                       .filter(num -> num % 2 == 0)
                       .map(num -> num * num)
                       .collect(Collectors.toList());

        System.out.println(evenNumbers);


        // **********************************************************************************************************
        System.out.println("--- Flatten a List of Lists");
        List<List<String>> nameArr =
                Arrays.asList(
                        Arrays.asList("Alice", "Bob"),
                        Arrays.asList("Charlie", "David")
                );

        List<String> flattenList =
                nameArr.stream()
                       .flatMap(List::stream)
                       .collect(Collectors.toList());

        System.out.println(flattenList);


        // **********************************************************************************************************
        System.out.println("--- Group Numbers by Odd and Even");
        List<Integer> numbersArr = Arrays.asList(1, 2, 3, 4, 5, 6);

        Map<String, List<Integer>> groudOddEven =
                numbersArr.stream()
                          .collect(Collectors.groupingBy(
                                  num -> num % 2 == 0 ? "even" : "odd"
                          ));

        System.out.println(groudOddEven);


        // **********************************************************************************************************
        System.out.println("--- Find First Non-Repeating Character");
        String input = "swiss";

        Character firstNonRepeatedChar =
                input.chars()
                     .mapToObj(c -> (char) c)
                     .collect(Collectors.groupingBy(
                             c -> c,
                             LinkedHashMap::new,
                             Collectors.counting()
                     ))
                     .entrySet()
                     .stream()
                     .filter(val -> val.getValue() == 1)
                     .map(Map.Entry::getKey)
                     .findFirst()
                     .orElse(null);

        System.out.println(firstNonRepeatedChar);


        // **********************************************************************************************************
        System.out.println("--- Find Maximum Even Number");
        List<Integer> nums = Arrays.asList(10, 3, 7, 18, 2, 15);

        Integer maxEvenNumber =
                nums.stream()
                    .filter(num -> num % 2 == 0)
                    .max(Integer::compareTo)
                    .orElse(-1);

        System.out.println(maxEvenNumber);


        // **********************************************************************************************************
        System.out.println("--- Reverse Each Word in a List");
        List<String> words1 = Arrays.asList("java", "stream", "api");

        List<String> reverseWord =
                words1.stream()
                      .map(word -> new StringBuilder(word).reverse().toString())
                      .collect(Collectors.toList());

        System.out.println(reverseWord);


        // **********************************************************************************************************
        System.out.println("--- Find Common Elements Between Two Lists");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 3);

        Set<Integer> commonElements =
                list1.stream()
                     .filter(num -> list2.contains(num))
                     .collect(Collectors.toSet());

        System.out.println(commonElements);


        // **********************************************************************************************************
        System.out.println("--- Find Duplicate Elements");
        List<String> items =
                Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        // approach 1
        Set<String> duplicateElements =
                items.stream()
                     .collect(Collectors.groupingBy(item -> item, Collectors.counting()))
                     .entrySet()
                     .stream()
                     .filter(val -> val.getValue() > 1)
                     .map(Map.Entry::getKey)
                     .collect(Collectors.toSet());

        System.out.println(duplicateElements);

        // approach 2
        Set<String> duplicateElements2 =
                items.stream()
                     .filter(item -> Collections.frequency(items, item) > 1)
                     .collect(Collectors.toSet());

        System.out.println(duplicateElements2);


        // **********************************************************************************************************
        System.out.println("--- Sum of All Digits in a String");
        String word3 = "a1b2c3";

        int sum =
                word3.chars()
                     .filter(Character::isDigit)
                     .map(Character::getNumericValue)
                     .sum();

        System.out.println(sum);


        // **********************************************************************************************************
        System.out.println("--- Find Longest String in List");
        List<String> names4 =
                Arrays.asList("apple", "banana", "pineapple", "kiwi");

        String longestString =
                names4.stream()
                      .max(Comparator.comparingInt(String::length))
                      .orElse(null);

        System.out.println(longestString);


        // **********************************************************************************************************
        System.out.println("--- Join Strings with Comma");
        List<String> fruits = Arrays.asList("apple", "banana", "orange");

        String strJoin =
                fruits.stream()
                      .collect(Collectors.joining(", "));

        System.out.println(strJoin);


        // **********************************************************************************************************
        System.out.println("--- Find the first repeated character");
        String str1 = "programming";

        Character firstRepeatedChar =
                str1.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                            c -> c,
                            LinkedHashMap::new,
                            Collectors.counting()
                    ))
                    .entrySet()
                    .stream()
                    .filter(val -> val.getValue() > 1)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

        System.out.println(firstRepeatedChar);


        // **********************************************************************************************************
        System.out.println("--- Group words by length");
        Map<Integer, List<String>> groupWordsByLength =
                Stream.of("cat", "dog", "rat", "elephant", "ant", "bird")
                      .collect(Collectors.groupingBy(word -> word.length()));

        System.out.println(groupWordsByLength);


        // **********************************************************************************************************
        System.out.println("--- Count character occurrences");
        String word4 = "hello";

        Map<Character, Long> characterOccurrences =
                word4.chars()
                     .mapToObj(c -> (char) c)
                     .collect(Collectors.groupingBy(
                             word -> word,
                             LinkedHashMap::new,
                             Collectors.counting()
                     ));

        System.out.println(characterOccurrences);


        // **********************************************************************************************************
        System.out.println("--- Find Second Largest Number and If less than 2 distinct numbers exist, return null");
        List<Integer> numbers14 = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6);

        Integer secondLargestNum =
                numbers14.stream()
                         .distinct()
                         .sorted(Comparator.reverseOrder())
                         .skip(1)
                         .findFirst()
                         .orElse(null);

        System.out.println(secondLargestNum);


        // **********************************************************************************************************
        System.out.println("--- Find the word that has highest length");
        String str = "I am learning streams API in java";

        String highestWordLength =
                Arrays.stream(str.split(" "))
                      .max(Comparator.comparingInt(String::length))
                      .get();

        System.out.println(highestWordLength);


        // **********************************************************************************************************
        System.out.println("--- Remove duplicates from the string & return in same order");
        String duplicates = "dabcadefg"; // output: dabcefg

        String removedDuplicates =
                Arrays.stream(duplicates.split(""))
                      .distinct()
                      .collect(Collectors.joining());

        System.out.println(removedDuplicates);


        // **********************************************************************************************************
        System.out.println("--- Find the word that has 2nd highest length");
        String str_1 = "I am learning streams API in java";

        String secondhighestWordLength =
                Arrays.stream(str_1.split(" "))
                      .sorted(Comparator.comparing(String::length).reversed())
                      .skip(1)
                      .findFirst()
                      .get();

        System.out.println(secondhighestWordLength);


        // **********************************************************************************************************
        System.out.println("--- Find the occurance of each word");
        String str_2 = "I am learning streams API in java java";

        Map<String, Long> wordOccuranceCount =
                Arrays.stream(str_2.split(" "))
                      .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        System.out.println(wordOccuranceCount);


        // **********************************************************************************************************
        System.out.println("--- Find the words with a specified numer of vowels; No.of vowels = 2");
        String str_3 = "I am learning streams API in java";

        // imperative way
        List<String> vowelsWordList = new ArrayList<>();
        String[] strSplit = str_3.split(" ");

        for (String word : strSplit) {
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                char ch = Character.toLowerCase(word.charAt(i));
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    count++;
                }
            }
            if (count == 2) {
                vowelsWordList.add(word);
            }
        }

        System.out.println(vowelsWordList);

        // declarative way 1
        List<String> findVowels =
                Arrays.stream(str_3.split(" "))
                      .filter(word -> countVowelsFromWord(word) == 2)
                      .collect(Collectors.toList());

        System.out.println(findVowels);

        // declarative way 2
        Arrays.stream(str_3.split(" "))
              .filter(word -> word.replaceAll("[^aeiouAEIOU]", "").length() == 2)
              .forEach(System.out::println);


        // **********************************************************************************************************
        System.out.println("--- Find the occurance of each character");
        String word2 = "Mississippi";

        Map<Character, Long> occuranceChar =
                word2.chars()
                     .mapToObj(ch -> (char) ch)
                     .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

        System.out.println(occuranceChar);


        // **********************************************************************************************************
        System.out.println("--- re-arrange the elemente to form the higest/lowest possible value");
        int[] arr = {4, 2, 1, 5, 3};

        // approach 1
        Arrays.stream(arr)
              .boxed()
              .sorted(Comparator.reverseOrder())
              .forEach(System.out::print);

        // approach 2
        Arrays.stream(arr)
              .mapToObj(num -> num)
              .sorted(Comparator.reverseOrder())
              .forEach(System.out::print);


        // **********************************************************************************************************
        System.out.println("--- find the sum of unique elements");
        int[] arr2 = {1, 6, 7, 8, 1, 1, 8, 8, 7};

        int sum2 =
                Arrays.stream(arr2)
                      .distinct()
                      .sum();

        System.out.println(sum2);


        // **********************************************************************************************************
        System.out.println("--- group the numbers by the range in they belong");
        int[] arrRange = {2, 3, 10, 14, 20, 24, 30, 34, 40, 44};

        LinkedHashMap<Integer, List<Integer>> groubByRange =
                Arrays.stream(arrRange)
                      .boxed()
                      .collect(Collectors.groupingBy(
                              num -> (num / 10) * 10,
                              LinkedHashMap::new,
                              Collectors.toList()
                      ));

        System.out.println(groubByRange);


        // **********************************************************************************************************
        System.out.println("--- from list, create a list that contains only integers");
        String[] strArr = {"abc", "123", "456", "xyz"};

        List<String> onlyIntegers =
                Arrays.stream(strArr)
                      .filter(word -> word.matches("\\d+"))
                      .collect(Collectors.toList());

        System.out.println(onlyIntegers);


        // **********************************************************************************************************
        System.out.println("--- find the product of first 2 elements from an array");
        int[] productArr = {12, 5, 2, 4, 3};

        int asInt =
                Arrays.stream(productArr)
                      .limit(2)
                      .reduce((a, b) -> a * b)
                      .getAsInt();

        System.out.println(asInt);


        // **********************************************************************************************************
        System.out.println("--- group anagram from a list of strings. for 1 word consider only 1 anagram");
        String[] anagram = {"pat", "tap", "pan", "nap", "team", "tree", "maet"};

        // approach 1
        Map<String, List<String>> grouped =
                Arrays.stream(anagram)
                      .collect(Collectors.groupingBy(
                              word -> {
                                  char[] chars = word.toCharArray();
                                  Arrays.sort(chars);
                                  return new String(chars);
                              },
                              Collectors.toList()
                      ));

        grouped.values().forEach(System.out::println);

        // approach 2
        Collection<List<String>> values =
                Arrays.stream(anagram)
                      .collect(Collectors.groupingBy(
                              word -> Arrays.stream(word.split(""))
                                            .sorted()
                                            .collect(Collectors.toList())
                      ))
                      .values();

        System.out.println(values);


        // **********************************************************************************************************
        System.out.println("--- muliply alternate number in an array");
        int[] numArr = {4, 5, 1, 7, 2, 9, 2}; // output: 16

        int mulplesOfAlternateNums =
                IntStream.range(0, numArr.length)
                         .filter(idx -> idx % 2 == 0)
                         .map(idx -> numArr[idx])
                         .reduce((a, b) -> a * b)
                         .getAsInt();

        System.out.println(mulplesOfAlternateNums);


        // **********************************************************************************************************
        System.out.println("--- program to multiply 1st and last element, 2nd and 2nd last element, etc numbers in an array");
        int[] arr5 = {4, 5, 1, 7, 2, 9}; // output: 36; 10; 7

        // imperative way
        for (int idx = 0; idx < arr5.length / 2; idx++) {
            int val = arr5[idx] * arr5[arr5.length - 1 - idx];
            System.out.println(val);
        }

        System.out.println();

        // declarative way
        IntStream.range(0, arr5.length / 2)
                 .map(idx -> arr5[idx] * arr5[arr5.length - 1 - idx])
                 .forEach(System.out::println);


        // **********************************************************************************************************
        System.out.println("--- program to move all zeros to beginning of array int[]");
        int[] zerosArr = {5, 0, 1, 0, 8, 0}; // output: {0,0,0,5,1,8}

        long zeroCount =
                Arrays.stream(zerosArr)
                      .filter(num -> num == 0)
                      .count();

        int[] resultArr =
                IntStream.concat(
                        IntStream.generate(() -> 0).limit(zeroCount),
                        Arrays.stream(zerosArr).filter(num -> num != 0)
                ).toArray();

        System.out.println(Arrays.toString(resultArr));


        // **********************************************************************************************************
        System.out.println("--- Find all numbers starting with 1 in Integer list");
        List<Integer> numList =
                Arrays.asList(10, 15, 8, 49, 13, 25, 98, 32, 17, 15);

        List<String> result1 =
                numList.stream()
                       .map(String::valueOf)
                       .filter(num -> num.startsWith("1"))
                       .collect(Collectors.toList());

        System.out.println(result1);


        // **********************************************************************************************************
        System.out.println("--- Find dulpicate elements from list");
        Set<Integer> uniqueElements = new HashSet<>();

        List<Integer> result2 =
                numList.stream()
                       .filter(num -> !uniqueElements.add(num))
                       .collect(Collectors.toList());

        System.out.println(result2);


        // **********************************************************************************************************
        System.out.println("--- Find first non-repeated char from a string");
        String repeatedWord = "swiss";

        repeatedWord.chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            LinkedHashMap::new,
                            Collectors.counting()
                    ))
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .ifPresent(System.out::println);


        // **********************************************************************************************************
        System.out.println("--- Check if list is empty in Java 8 using Optional, if not null iterate through the list and print the object");
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry");

        Optional.ofNullable(list)
                .filter(l -> !l.isEmpty())
                .ifPresent(l -> l.forEach(System.out::println));
        
        
        // **********************************************************************************************************
        System.out.println("--- Given the string[] group the strings based on the middle character.");
        String[] strArr1 = {"ewe", "jji" , "jhj" , "kwk" , "aha" };
        //O/P:{ w = [ewe, kwk], h = [jhj, aha], j = [jji]}
        Map<Character, List<String>> grpByMiddleChar = 
        		Arrays.stream(strArr1)
        			  .collect(Collectors.groupingBy(
			        			word -> word.charAt(word.length() / 2),
			        			Collectors.toList()
        					  ));
        System.out.println(grpByMiddleChar);
        
        
        // **********************************************************************************************************
        System.out.println("--- Find the sum of all the elements in a list.");
        List<Integer> numList1 = Arrays.asList(1,2,3,4,5); // O/P: 15
        int sum3 = numList1.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum3);
        
        
        // **********************************************************************************************************
        System.out.println("--- Sort a list of strings in alphabetical order.");
        List<String> strList = Arrays.asList("Zudio","Puma","Addidas","MAC","H&M");
        // O/P: [Addidas, H&M, MAC, Puma, Zudio]
        strList.stream()
        	   .sorted()
        	   .forEach(s -> System.out.print(s + ";\t"));
        System.out.println();
        
        
        // **********************************************************************************************************
        System.out.println("--- Convert a list of integers to a list of their squares.");
        List<Integer> numList2 = Arrays.asList(1,2,3,4,5);
        // O/P:[1, 4, 9, 16, 25]
        List<Integer> squareList = numList2.stream()
							        	   .map(num -> num * num)
							        	   .collect(Collectors.toList());
        System.out.println(squareList);
        
        
        // **********************************************************************************************************
        System.out.println("--- Find and print the distinct odd numbers.");
        List<Integer> numList4 = Arrays.asList(1,2,3,4,5,6,7,8,9,9,10);
        // O/P:[1, 3, 5,7,9]
        List<Integer> oddNum = numList4.stream()
						        		.distinct()
						        		.filter(num -> num % 2 != 0)
						        		.collect(Collectors.toList());
        System.out.println(oddNum);
        
        
        // **********************************************************************************************************
        System.out.println("--- Find the union of two lists of integers");
        List<Integer> numList5 = Arrays.asList(1,2,3,4,5);
        List<Integer> numList6 = Arrays.asList(6,7,8,9,10);
        // O/P:[1, 2,3,4,5,6,7,8,9,10]
        List<Integer> unionList = Stream.concat(
        										numList5.stream(), 
        										numList6.stream()
        										)
        								.collect(Collectors.toList());
        System.out.println(unionList);
        
        
        // **********************************************************************************************************
        System.out.println("--- Find the kth smallest element in alist of integers.");
        List<Integer> numList7 = Arrays.asList(7,1,6,2,1,3,4,5); // 1 1 2 3 4 5 6 7 8
        int k = 3; //O/P:[2]
        Integer smallestNumber = numList7.stream()
        								 .sorted().skip(k - 1)
        								 .findFirst()
        								 .get();
        System.out.println(smallestNumber);
        
        
        // **********************************************************************************************************
        System.out.println("--- Remove all non-numeric characters from a list.");
        List<String> strList2 = Arrays.asList("a1b2c3","1a2b3c","123abc");
        // O/P:[123, 123,123]
        List<String> nonNumericChar = strList2.stream()
        									  .map(word -> word.replaceAll("[^0-9]", ""))
        									  .collect(Collectors.toList());
        System.out.println(nonNumericChar);
        
        // **********************************************************************************************************
        System.out.println("--- Find and print the strings containing only digits.");
        List<String> strList3 = Arrays.asList("123","abc","123abc","45");
        // O/P:[123,45]
        List<String> onlyDigits = strList3.stream()
        								  .filter(word -> word.matches("\\d+"))
        								  .collect(Collectors.toList());
        System.out.println(onlyDigits);
        
        
        // **********************************************************************************************************
        System.out.println("--- Convert a list of strings to uppercase.");
        List<String> strList4 = Arrays.asList("breaking bad"," game of thrones", "big bang theory");
        // O/P: [BREAKING BAD, GAME OF THRONES, BIG BANG THEORY]
        List<String> upperCaseList = strList4.stream()
        									 .map(String::toUpperCase)
        									 .collect(Collectors.toList());
        System.out.println(upperCaseList);
        
        
        // **********************************************************************************************************
        System.out.println("--- Calculate the average of all the numbers.");
        List<Integer> numList8 = Arrays.asList(1,2,3,4,5); // O/P: 3.0
        // approach 1
        double avgVal = numList8.stream()
        						.mapToInt(Integer::intValue)
        						.average()
        						.getAsDouble();
        // approach 2
        IntSummaryStatistics summaryStatistics = numList8.stream()
        												 .mapToInt(Integer::intValue)
        												 .summaryStatistics();
        System.out.println(summaryStatistics.getAverage());
        
        
        // **********************************************************************************************************
        System.out.println("--- Find the intersection of two lists using Java streams");
        List<Integer> numList9 = Arrays.asList(1,2,3,4,5);
        List<Integer> numList10 = Arrays.asList(3,5,6,7);
        // O/P:[3,5]
        Set<Integer> intersectionList = numList9.stream()
        										.filter(num -> numList10.contains(num))
        										.collect(Collectors.toSet());
        System.out.println(intersectionList);
        
        
        // **********************************************************************************************************
        System.out.println("--- There is a list of Employee objects having field name and email."
			        		+ " Find the list of domains( gmail.com, yahoo.com etc.)"
			        		+ " And the occurrences of each domain.");
        EmployeeBioData e1 = new EmployeeBioData("Sam","sam@gmail.com");
        EmployeeBioData e2 = new EmployeeBioData("Adam","adam@yahoo.com");
        EmployeeBioData e3 = new EmployeeBioData("Peter","peter@gmail.com");
        List<EmployeeBioData> employeeList = Arrays.asList(e1, e2, e3);
        Map<String, Long> domainOccurance = 
        		employeeList.stream()
		        			.map(val -> val.getEmail())
		        			.collect(Collectors.groupingBy(
										        		domain -> domain.substring(domain.indexOf('@') + 1),
										        		Collectors.counting()
		        					));
        System.out.println(domainOccurance);
        
        
        // **********************************************************************************************************
        System.out.println("--- Generate the first 10 numbers of the Fibonacci Sequence.");
        // O/P: 0,1, 1, 2, 3, 5, 8, 13, 21, 34
        
        // traditional way
        int firstNum = 0;
        int secondNum = 1;
        int nextNum;
        System.out.print(firstNum +" "+secondNum);
        for (int idx = 3; idx <= 10; idx++) {
        	nextNum = firstNum + secondNum;
        	System.out.print(" " + nextNum);
        	
        	firstNum = secondNum;
        	secondNum = nextNum;
		}
        
        // declarative way
        List<Integer> fibonacciSeries = Stream.iterate(
	        										new int[] {0, 1}, 
	        										fib -> new int[] {fib[1], fib[0] + fib[1]})
        									  .limit(10)
        									  .map(t -> t[0]) // Each element of the stream is an array [a, b]
        									 				 // so, We only want the first number of each pair for the Fibonacci sequence
        									  .collect(Collectors.toList());
        System.out.println(fibonacciSeries);
        
        
        // **********************************************************************************************************
        System.out.println("--- Transform Person object stream into a single string "
			        		+ " consisting of all names in upper letters separated by 'l' (pipe) character.");
        EmployeeBioData emp1 = new EmployeeBioData("Sam","sam@gmail.com");
        EmployeeBioData emp2 = new EmployeeBioData("Adam","adam@yahoo.com");
        List<EmployeeBioData> employeeList1 = Arrays.asList(emp1, emp2);
        String upperCaseWithDelimiter = employeeList1.stream()
        											 .map(emp -> emp.getName().toUpperCase())
        											 .collect(Collectors.joining(" | "));
        System.out.println(upperCaseWithDelimiter);
        
        
        // **********************************************************************************************************
        System.out.println("--- group them by their first character and count the number of strings in each group. ");
        List<String> stringList = Arrays.asList("apple", "banana", "apricot", "cherry", "blueberry", "avocado");
        // O/P: {a=3, b=2, c=1 }
        Map<Character, Long> grpByFirstChar = stringList.stream()
        										 .collect(Collectors.groupingBy(
														        		word -> word.charAt(0),
														        		Collectors.counting()
        												 ));
        System.out.println(grpByFirstChar);
        
        
        
        // **********************************************************************************************************
        System.out.println("--- Convert a list to a map. Given a Person object list, "
        		+ "convert it to a map with a key as 'Department' and Value as List<Person>");
        // o/p List<Person> -> Map<String, List<Person>>
        List<Employee> employeesList = Arrays.asList(
                new Employee("John", "Sales", 30, 50000),
                new Employee("Jane", "Sales", 25, 45000),
                new Employee("Jack", "IT", 35, 70000),
                new Employee("Tom", "IT", 28, 65000),
                new Employee("Jerry", "Sales", 30, 48000)
            );
        Map<String, List<Employee>> grpByDept = 
        		employeesList.stream()
        					 .collect(Collectors.groupingBy(
										        		Employee::getDepartment,
										        		Collectors.toList()
					        		 ));
        System.out.println(grpByDept);
        
        
        // **********************************************************************************************************
        System.out.println("--- Multiply array elements.");
        Integer[] arrInteger = {1,2,3,4,5}; // O/P: 120
        Integer multipliedVal = Arrays.stream(arrInteger)
        							  .reduce((a,b) -> a*b)
        							  .get();
        System.out.println(multipliedVal);
        
        
        // **********************************************************************************************************
        System.out.println("--- Can we reuse the stream in Java 8 ?");
        List<String> namesList = Arrays.asList("Alice", "Bob", "Charlie", "Anna");
        
        Stream<String> nameStream = namesList.stream();
        
        //Consumption #1
        nameStream.forEach(System.out::println);
        
        //Consumption #2
        //long listSize = nameStream.count();
        //System.out.print(listSize);
        
        // Error occurs: IllegalStateException: stream has already been operated upon or closed
        
        // No — Java 8 streams cannot be reused. Refer Java 8 - sheet
        
        
        // **********************************************************************************************************
        System.out.println("--- map vs flatmap");
        // map() transforms each element of the stream into another element. It is a one-to-one mapping.
        // Use map() when you have a stream of elements and want to apply a function to each element.
        List<Person> personList = Arrays.asList(
		        		new Person("Alice", Arrays.asList("red", "blue")),
		        		new Person("Bob", Arrays.asList("green")),
		        		new Person("Charlie", Arrays.asList("yellow", "pink", "purple"))
        			);
		List<List<String>> colorList = personList.stream()
												 .map(Person::getColors)
												 .collect(Collectors.toList());
		System.out.println(colorList) ;
        
        // flatMap() transforms + flattens the resulting streams into one stream, 
		// usually when dealing with collections of collections.
		List<String> colorList1 = personList.stream()
											.flatMap(person -> person.getColors().stream())
											.collect(Collectors.toList());
		System.out.println(colorList1);
		
		
		// **********************************************************************************************************
        System.out.println("--- Concatenate 2 streams using Stream API");
        Stream<String> stream1 = Stream.of("Java", "Python");
        Stream<String> stream2 = Stream.of("C++", "C#");
        // O/P: Java Python C++ C#
        List<String> concatStream = Stream.concat(stream1, stream2).collect(Collectors.toList());
        System.out.println(concatStream);
        
        
        // **********************************************************************************************************
        System.out.println("--- Given a Person Object where each person has a first name and last name."
        					+ " Sort the List<Person> by their first name then by last Name;");
        List<Person2> peopleList = Arrays.asList(
							        		new Person2("Bobby", "Smith"),
							        		new Person2("Bobby", "Adams"),
							        		new Person2("John", "Smith"),
							        		new Person2("Alice", "Johnson")
    									);
        List<Person2> sortByFirstAndLastName = peopleList.stream()
								          .sorted(
								        	    Comparator.comparing(
								        	        Person2::getFirstName,
								        	        String.CASE_INSENSITIVE_ORDER
								        	    ).thenComparing(
								        	        Person2::getLastName,
								        	        String.CASE_INSENSITIVE_ORDER
								        	    ))
								          .collect(Collectors.toList());
        System.out.println(sortByFirstAndLastName);
        
        
        // **********************************************************************************************************
        System.out.println("--- Given a string print the count of a particular substring");
        String actualString = "byebyeBirdiebye!"; // Find the count of "bye" - O/P: 3
        String subString = "bye";
        int actualStrLength = actualString.length();
        int subStrLength = subString.length();
        long subStrOccuranceCount = IntStream.range(0, actualStrLength - subStrLength + 1)
							        		 .filter(idx -> actualString.substring(idx, idx + subStrLength)
							        				 					.equals(subString))
							        		 .count();
        System.out.println(subStrOccuranceCount);
        
        
        // **********************************************************************************************************
        System.out.println("--- Given a list of employees with their names and department names, write a Stream API solution "
			        		+ " to find the department that has the maximum number of employees.");
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Sales", 30, 50000),
                new Employee("Jane", "Sales", 25, 45000),
                new Employee("Jack", "IT", 35, 70000),
                new Employee("Tom", "IT", 28, 65000),
                new Employee("Jerry", "Sales", 30, 48000)
            );
        Entry<String, Long> grpByMaxDept = employees.stream()
									        		 .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
									        		 .entrySet()
									        		 .stream()
									        		 .max(Map.Entry.comparingByValue())
									        		 .get();
        System.out.println(grpByMaxDept);
        
        
        // **********************************************************************************************************
        System.out.println("--- Using Stream API extract messages in the correct "
        					+ " chronological order from the given log data in format \"HH:MM:ID:Message\"");
        List<String> logs = Arrays.asList(
        		"14:30:3:Server started",
        		"14:30:1:User logged in",
        		"14:29:2:Database connected",
        		"18:32:4:User logged out" );
        // o/p: Database connected; User logged in; Server started; User logged out
        List<String> sortTime = logs.stream()
        							.map(log -> log.split(":", 4)) // ["14", "29", "2", "Database connected"]
        							.sorted(Comparator.comparing(p -> p[0] + ":" + p[1])) // p[0] + ":" + p[1] → "14:30"
        							.map(p -> p[3]) // Extracts only the message part from each sorted log
        							.collect(Collectors.toList());
        System.out.println(sortTime);
        
        
        // **********************************************************************************************************
        System.out.println("--- Given a string return the character with the maximum frequency in the string.");
        String str2 = "javadeveloper"; // o/p  = e
        Character maxFreq = str2.chars()
        						.mapToObj(c -> (char)c)
        						.collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
        						.entrySet()
        						.stream()
        						.max(Map.Entry.comparingByValue())
        						.get()
        						.getKey();
        System.out.println(maxFreq);
        
        
        // **********************************************************************************************************
        System.out.println("--- return a map of the String and its length");
        List<String> fruitsList = Arrays.asList("orange","banana","kiwi");
        // o/p = {banana=6, orange=6, kiwi=4}
        Map<String, Integer> collectorMap = fruitsList.stream()
        											  .collect(Collectors.toMap(fruit -> fruit, String::length));
        System.out.println(collectorMap);
        
    }


    // Find the words with a specified numer of vowels; No.of vowels = 2
    public static long countVowelsFromWord(String word) {

        return word.toLowerCase()
                   .chars()
                   .filter(ch -> "aeiou".indexOf(ch) != -1)
                   .count();
    }
}

//Simple Employee class
class EmployeeBioData {
	private String name;
	private String email;

	public EmployeeBioData(String name, String email) {
		this.name = name;
		this.email = email;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return name + ", " + email;
	}
}

// Person class
class Person{
	
	private String name;
	
	private List<String> colors;

	public Person(String name, List<String> colors) {
		this.name = name;
		this.colors = colors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColors() {
		return colors;
	}

	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	
}

class Person2{
	
	private String firstName;
	
	private String lastName;

	public Person2(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person2 [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
