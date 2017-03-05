package View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ObservedDisplay<N extends Node> {

	private VBox contentDisplay;
	private ObservableList<N> content;
	
	public ObservedDisplay() {
		contentDisplay=new VBox();
		content = FXCollections.observableArrayList(new ArrayList<N>());
		setUpDisplay();
	}
	
	private void setUpDisplay(){
		content.addListener(new ListChangeListener<N>() {
			@Override
			public void onChanged(Change<? extends N> change) {
				while (change.next()) {
					List<? extends N> added = change.getAddedSubList();
					for (N elem : added) {
						contentDisplay.getChildren().add(elem);
					}
				}
			}
		});
	}
	
	public Node getDisplay(){
		return contentDisplay;
	}
	
	public void add(N data) {
		content.add(data);
	}
	
	public void addAll(Collection<N> dataCollection){
		content.addAll(dataCollection);
	}
	
	public void clear(){
		content.clear();
	}

	public ReadOnlyDoubleProperty heightProperty(){
		return contentDisplay.heightProperty();
	}
}
