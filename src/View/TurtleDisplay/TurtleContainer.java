package View.TurtleDisplay;


import java.io.File;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import utils.TurtleParameters;

public class TurtleContainer {
	private Map<Integer, TurtleDispNode>  myTurtleMap;
	private StackPane container;
	private int height, width;
	
	public TurtleContainer(int _height, int _width){
		height=_height;
		width=_width;
		myTurtleMap=new TreeMap<>();
		container=new StackPane();
		container.setStyle("-fx-background-color: transparent");
		addNewTurtle(1);
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
	}

	private void addNewTurtle(int paramID) {
		myTurtleMap.put(paramID, new TurtleDispNode(paramID, height, width));
		container.getChildren().add(myTurtleMap.get(paramID).getContainerNode());
	}

	
	public Node getContainerNode(){
		return container;
	}

	public void setTurtleImg(File file) {
		for(int id: myTurtleMap.keySet()){
			myTurtleMap.get(id).setTurtleImg(file);
		}
	}
}
