package textproc;

import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MultiWordCounter implements TextProcessor {
	private Map<String,Integer> map = new TreeMap<>();

	public MultiWordCounter(String[] regions) {
		for (int i = 0; i<regions.length;i++) {
		map.put(regions[i],0);
		}
	}

	@Override
	public void process(String w) {
		
			if(map.containsKey(w)) {
				map.replace(w, map.get(w), map.get(w)+1);
			}
		
	}

	@Override
	public void report() {
		for (String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
		
	}

	@Override
	public Set<Entry<String, Integer>> getWords() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
