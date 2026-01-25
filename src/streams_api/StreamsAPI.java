package streams_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    }


    // Find the words with a specified numer of vowels; No.of vowels = 2
    public static long countVowelsFromWord(String word) {

        return word.toLowerCase()
                   .chars()
                   .filter(ch -> "aeiou".indexOf(ch) != -1)
                   .count();
    }
}
