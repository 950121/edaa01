package lab3;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;
import textproc.TextProcessor;

public class BookReaderController extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		TextProcessor counter = new GeneralWordCounter();
		
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
				counter.process(word);
			}
		}
		
		s.close();
		scan.close();
		
		ObservableList<Map.Entry<String, Integer>> words
			= FXCollections.observableArrayList(counter.getWords());
		
		ListView<Map.Entry<String, Integer>> listView
			= new ListView<Map.Entry<String, Integer>>(words);
		
		root.setCenter(listView);
		
		//Skapar en hbox och lägger in tre knappar samt ett sökfält
		HBox hBox = new HBox();
		ToggleGroup group = new ToggleGroup();
		
		RadioButton button1 = new RadioButton("Alphabetic");
		button1.setToggleGroup(group);
	    button1.setSelected(true);
	    RadioButton button2 = new RadioButton("Frequency");
	    button2.setToggleGroup(group);
	    Button button3 = new Button("Search");
	    
	    TextField search = new TextField("Search");
	    HBox.setHgrow(search, Priority.ALWAYS);
	    
	    hBox.getChildren().addAll(button1, button2, search, button3);
	    root.setBottom(hBox);
	    
	    button1.setOnAction(e -> {
	    	System.out.println("Sorterat efter Bokstavsordning");
	    	words.sort((e1,e2) -> e1.getKey().compareTo(e2.getKey()));
	    });
	    button2.setOnAction(e -> {
	    	System.out.println("Sorterat efter Frekvens");
	    	words.sort((e1,e2) -> e2.getValue()-e1.getValue());
	    });
	    button3.setOnAction(e -> {
	    	String searchword = search.getText().toLowerCase().replaceAll("\\s","");
	    	System.out.println("Söker efter : " + searchword);
	    	words.forEach(a -> {
	    		if(a.getKey().equals(searchword)){
	    			System.out.println("Hittade det!");
	    			listView.scrollTo(a);
	    			listView.getSelectionModel().select(a);
	    		}
	    	});
	    	
	    });
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}