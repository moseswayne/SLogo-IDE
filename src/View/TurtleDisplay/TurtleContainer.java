package View.TurtleDisplay;


import java.io.File;
import java.util.HashMap;
import java.util.Queue;
import View.viewUtils.ObservedDisplay;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import utils.TurtleParameters;

public class TurtleContainer {
	private HashMap<Integer, TurtleDispNode>  myTurtleMap;
	private StackPane graphicContainer;
	private ObservedDisplay<Text> turtleStatusDisplay;
	private int height, width;
	
	public TurtleContainer(int _height, int _width){
		myTurtleMap=new HashMap<>();
		height=_height;
		width=_width;
		graphicContainer=new StackPane();
		graphicContainer.setStyle("-fx-background-color: transparent");
		turtleStatusDisplay=new ObservedDisplay<Text>();
		addNewTurtle(1);
		addNewTurtle(2);
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



	Node getGraphicNode(){
		return graphicContainer;
	}
	
	Node getInfoNode(){
		return turtleStatusDisplay.getDisplay();
	}
	


	public void setTurtleImg(File file) {
		for(int id: myTurtleMap.keySet()){
			myTurtleMap.get(id).setTurtleImg(file);
		}
	}

}
