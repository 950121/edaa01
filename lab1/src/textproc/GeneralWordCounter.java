package textproc;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {
	private Map<String,Integer> map = new TreeMap<>();
	
	
	public GeneralWordCounter() {
		
	}
	
	@Override
	public void process(String w) {
	
		if (map.containsKey(w)) {
			map.replace(w, map.get(w), map.get(w)+1);
		} else {
			map.put(w,1);
		}
		
	}

	@Override
	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for (int i=0; i<5; i++) {
			System.out.println(wordList.get(i));
		}
		
		
//		for (String key : map.keySet()) {
//			if (map.get(key)>200) {
//				System.out.println(key + " : " + map.get(key));
//			}
//		}
		
	}
	
	public Set<Entry<String,Integer>> getWords() {
		return map.entrySet();
	}
	
	

}
