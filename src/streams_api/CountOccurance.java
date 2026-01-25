 package streams_api;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CountOccurance {

	public static void main(String[] args) {

		List<String> strList = Arrays.asList("Geeks", "for", "Geeks");
		
		// imperative way
		Set<String> setVal = new HashSet<>(strList);
		for (String str : setVal) {
			System.out.println(str+" : "+Collections.frequency(strList, str));
		}
		System.out.println("-----------------------------");
		
		// declarative way
		Map<String, Long> occuranceList = strList.stream()
													.collect(Collectors.groupingBy(word -> word, Collectors.counting()));
		
		occuranceList.forEach((word, count) -> System.out.println(word+" : "+count));
	}

}
