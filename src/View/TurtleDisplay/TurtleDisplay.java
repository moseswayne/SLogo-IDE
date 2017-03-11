package View.TurtleDisplay;

import javafx.scene.paint.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.Properties;
import java.util.Queue;
import View.I_FrontEndModule;
import View.viewUtils.FrontEndData;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import utils.ColorManager;
import utils.Language;
import utils.PropertyUtility;
import utils.RawCommand;
import utils.TurtleParameters;

public class TurtleDisplay implements I_FrontEndModule {
	private Paint penColor;
	private Dimension paneSize;
	private Properties prop;
	private ColorManager myColorManager;
	private TurtleContainer turtleContainer;
	private Node graphicDisp;
	private Tab infoTab;
	private Node infoDisp;
	private Tab graphicTab;
	private Language myLanguage;
	
	
	/**
	 *  
	 * @param height height of turtleDisplay
	 * @param width width of turtleDisplay
	 */
	public TurtleDisplay(int width, int height, ColorManager _myColorManager) {
		
		paneSize=new Dimension(width, height);
		turtleContainer=new TurtleContainer(width, height);
		graphicDisp=turtleContainer.getGraphicNode();
		prop=new PropertyUtility("GeneraGUISettings.properties").getProperties();
		myLanguage=Language.valueOf(prop.getProperty("DEAFAULT_LANGUAGE"));
		infoDisp=turtleContainer.getInfoNode();
		infoTab=createTab(infoDisp);
		graphicTab=createTab(graphicDisp);
		setLanguage(myLanguage);
		prop=new PropertyUtility("deafaultTurtleDisp.properties").getProperties();
		myColorManager=_myColorManager;
		setPenColor(myColorManager.getColor(prop.getProperty("DEAFAULT_PEN_COLOR")));
		setBackgroudColor(myColorManager.getColor(prop.getProperty("DEAFAULT_BACKGROUND_COLOR")));

	}




	
	/**
	 * 
	 * @param color String specifying the color, USE VALID
	 */
	public void setBackgroudColor(Color color){
		int blue=(int) (255*color.getBlue()),
				red=(int) (255*color.getRed()),
				green=(int) (255*color.getGreen());
		graphicDisp.setStyle(String.format("-fx-background-color: rgb(%d, %d, %d)", red, green, blue));
	}
	
	public void setPenColor(Color color){
		penColor=color;
	}
	

	/**
	 * 
	 * API for updating visualization
	 */
	@Override
	public void updateDisplayedData(FrontEndData data) {
		Queue<TurtleParameters> paramQ=data.getTurtleParameters();
		turtleContainer.show(paramQ, penColor);
	}


	public void setTurtleImg(File file) {
		turtleContainer.setTurtleImg(file);
	}

    
    
	@Override
	public RawCommand getUserInteractionResult() {
		// TODO Auto-generated method stub
		return null;
	}

	private Tab createTab(Node node) {
		Tab tab=new Tab();
		tab.setContent(node);
		tab.setClosable(false);
		return tab;
	}
	

	@Override
	public Node getVisualizedContent() {
		TabPane out=new TabPane();
		out.getTabs().addAll(graphicTab, infoTab);
		return out;
	}

	@Override
	public boolean hasBufferedUserInteraction() {
		return false;
	}

	/**
	 * Has no displayed language components
	 */
	@Override
	public void setLanguage(Language language) {
		prop=new PropertyUtility(language+"Text.properties").getProperties();
		infoTab.setText(prop.getProperty("turtleInfoTab"));
		graphicTab.setText(prop.getProperty("turtleDispTab"));
	}


}