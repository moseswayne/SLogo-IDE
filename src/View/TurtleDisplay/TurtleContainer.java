package View.TurtleDisplay;


import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import java.util.Queue;

import View.viewUtils.ObservedDisplay;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import utils.Language;
import utils.PropertyUtility;
import utils.TurtleParameters;

public class TurtleContainer {
	private HashMap<Integer, TurtleDispNode>  myTurtleMap;
	private StackPane graphicContainer;
	private TabPane overallContainer;
	private ObservedDisplay<Text> turtleStatusDisplay;
	private int height, width;
	private Tab graphicTab, infoTab;
	private PropertyUtility prop;
	private Language myLanguage;
	
	public TurtleContainer(int _height, int _width){
		prop=new PropertyUtility("GeneraGUISettings.properties");
		myLanguage=Language.valueOf(prop.getProperties().getProperty("DEAFAULT_LANGUAGE"));
		myTurtleMap=new HashMap<>();
		height=_height;
		width=_width;
		graphicContainer=new StackPane();
		graphicContainer.setStyle("-fx-background-color: red");
		turtleStatusDisplay=new ObservedDisplay<Text>();
		addNewTurtle(1);
		addNewTurtle(2);
		graphicTab=createTab(graphicContainer);
		infoTab=createTab(turtleStatusDisplay.getDisplay());
		
		setLanguage(myLanguage);
		overallContainer=new TabPane();
	}
	
	public void show(Queue<TurtleParameters> paramQ, Paint penColor){
		while(paramQ.size()!=0){
			TurtleParameters param=paramQ.poll();
			int paramID=param.getID();
			if(myTurtleMap.keySet().contains(paramID)){
				myTurtleMap.get(paramID).show(param, penColor);
			} else {
				addNewTurtle(paramID);
			}
		}
		turtleStatusDisplay.clear();
		for(int id: myTurtleMap.keySet()){
			turtleStatusDisplay.add(new Text(myTurtleMap.get(id).getTurtleString()));
		}
		
	}

	private void addNewTurtle(int paramID) {
		myTurtleMap.put(paramID, new TurtleDispNode(paramID, height, width));
		graphicContainer.getChildren().add(myTurtleMap.get(paramID).getContainerNode());
		turtleStatusDisplay.add(new Text(myTurtleMap.get(paramID).getTurtleString()));
	}

	private Tab createTab(Node node) {
		Tab tab=new Tab();
		tab.setContent(node);
		tab.setClosable(false);
		return tab;
	}

	
	public Node getContainerNode(){
		
		overallContainer.getTabs().addAll(graphicTab, infoTab);
		return new ScrollPane(overallContainer);
	}

	public void setTurtleImg(File file) {
		for(int id: myTurtleMap.keySet()){
			myTurtleMap.get(id).setTurtleImg(file);
		}
	}

	public void setLanguage(Language language) {
		prop=new PropertyUtility(language.toString()+"Text.properties");
		graphicTab.setText(prop.getProperties().getProperty("turtleDispTab"));
		infoTab.setText(prop.getProperties().getProperty("turtleInfoTab"));
	}
}
