package View.cmdHistory;

import java.util.ArrayList;
import java.util.List;

import View.FrontEndData;
import View.I_FrontEndModule;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.RawCommand;
import java.awt.Dimension;

public class CmdHistoryDisplay implements I_FrontEndModule{

	private ScrollPane container;
	private VBox historyDisplay;
	private ObservableList<String> history;
	private Dimension size;
	
	public CmdHistoryDisplay(int width, int height) {
		size=new Dimension(width, height);
		historyDisplay=new VBox();
		container=new ScrollPane();
		history = FXCollections.observableArrayList(new ArrayList<String>());
		setUpDisplay();
	}
	
	private void setUpDisplay(){
		container.setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID,
				new CornerRadii(2.5), new BorderWidths(5.0))));
		container.setPrefSize(size.getWidth(), size.getHeight());
		history.addListener(new ListChangeListener<String>() {
			@Override
			public void onChanged(Change<? extends String> c) {
				while (c.next()) {
					List<? extends String> added = c.getAddedSubList();
					for (String str : added) {
						historyDisplay.getChildren().add(new Text(str));
					}
				}
			}
		});
		container.setContent(historyDisplay);
		container.vvalueProperty().bind(container.heightProperty());
	}
	
	@Override
	public void updateDisplayedData(FrontEndData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getVisualizedContent() {
		return container;
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		// TODO Auto-generated method stub
		return false;
	}

}
