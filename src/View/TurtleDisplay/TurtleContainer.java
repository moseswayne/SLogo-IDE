package View.TurtleDisplay;


import java.io.File;
import java.util.HashMap;
import java.util.Queue;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import utils.TurtleParameters;

public class TurtleContainer {
	private HashMap<Integer, TurtleDispNode>  myTurtleMap;
	private StackPane graphicContainer;
	private TabPane overallContainer;
	private VBox turtleStatusDisp;
	private int height, width;
	
	public TurtleContainer(int _height, int _width){
//		initializeTurtleMap();
		myTurtleMap=new HashMap<>();
		height=_height;
		width=_width;
		graphicContainer=new StackPane();
		graphicContainer.setStyle("-fx-background-color: transparent");
		addNewTurtle(1);
		turtleStatusDisp=new VBox();
		overallContainer=new TabPane();
	}

//	private void initializeTurtleMap() {
//		myTurtleMap=FXCollections.observableMap(new HashMap<Integer, TurtleDispNode>());
//		
//		myTurtleMap.addListener(new MapChangeListener<Integer, TurtleDispNode>(){
//			@Override
//			public void onChanged(
//					javafx.collections.MapChangeListener.Change<? extends Integer, ? extends TurtleDispNode> change) {
//				if(change.wasAdded()){
//					TurtleDispNode added=change.getValueAdded();
//					turtleStatusDisp.getChildren().add(new Text(added.getTurtleString()));
//				} else if(change.wasRemoved()){
//					turtleStatusDisp.getChildren().clear();
//					for(int id : change.getMap().keySet()){
//						turtleStatusDisp.getChildren().add(new Text(change.getMap().get(id).toString()));
//					}
//				}
//			}
//		});
//	}
	
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
	}

	private void addNewTurtle(int paramID) {
		myTurtleMap.put(paramID, new TurtleDispNode(paramID, height, width));
		graphicContainer.getChildren().add(myTurtleMap.get(paramID).getContainerNode());
		
	}

	private Tab createTab(Node node) {
		Tab tab=new Tab();
		tab.setContent(node);
		tab.setClosable(false);
		return tab;
	}

	
	public Node getContainerNode(){
//		Tab graphicTab=createTab(graphicContainer),
//				infoTab=createTab(turtleStatusDisp);
//		overallContainer.getTabs().addAll(graphicTab, infoTab);
		return graphicContainer;
	}

	public void setTurtleImg(File file) {
		for(int id: myTurtleMap.keySet()){
			myTurtleMap.get(id).setTurtleImg(file);
		}
	}
}
