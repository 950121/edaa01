package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland","hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	
	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		TextProcessor r = new GeneralWordCounter();
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		
		while (scan.hasNext()) {
			stopwords.add(scan.next());
		}
		
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			if (!stopwords.contains(word)) {
				r.process(word);
			}
		}
		
		s.close();
		scan.close();
		
		r.report();
		
		TextProcessor y = new MultiWordCounter(REGIONS);
		
		Scanner t = new Scanner(new File("nilsholg.txt"));
		t.findWithinHorizon("\uFEFF", 1);
		t.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		
		while (t.hasNext()) {
			String word = t.next().toLowerCase();
			
			y.process(word);
		}
		
		t.close();
		
		y.report();
		
		
		List<String> words = new ArrayList<>();
		words.add("nils");
		words.add("norge");

		for (int i = 0; i < words.size(); i++) {
			
			Scanner u = new Scanner(new File("nilsholg.txt"));
			u.findWithinHorizon("\uFEFF", 1);
			u.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
			
			TextProcessor x = new SingleWordCounter(words.get(i));
			
			while (u.hasNext()) {
				String word = u.next().toLowerCase();
				
				x.process(word);
			}

			u.close();

			x.report();
		}

		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");		
		
	}
}