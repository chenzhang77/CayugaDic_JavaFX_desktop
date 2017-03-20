/**
 * 
 */
package model;

/**
 * @author cz5670
 *
 */
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dictionary {
	
	
	private final StringProperty firstcol;
	private final StringProperty secondcol;
	private final StringProperty thirdcol;

	
	public Dictionary() {
		
		this(null,null,null);
	}
	
	public Dictionary(String firstcolString, String secondcolString, String thirdcolString) {
		
		this.firstcol = new SimpleStringProperty(firstcolString);
		this.secondcol = new SimpleStringProperty(secondcolString);
		this.thirdcol = new SimpleStringProperty(thirdcolString);
	}
	
//----------------------------------------------	
	public void setFirstcol(String firstcol) {
		this.firstcol.set(firstcol);
	}

	public String getFirstcol() {
		return firstcol.get();
	}
	
	public StringProperty firstcolProperty() {
		return firstcol;
	}

// -----------------------------------------------
	
	public StringProperty secondcolProperty() {
		return this.secondcol;
	}
	
	public String getSecondcol() {
		return this.secondcolProperty().get();
	}
	
	public void setSecondcol(final String secondcol) {
		this.secondcolProperty().set(secondcol);
	}
	

//--------------------------------------------------
	
	public StringProperty thirdcolProperty() {
		return this.thirdcol;
	}
	
	public String getThirdcol() {
		return this.thirdcolProperty().get();
	}
	
	public void setThirdcol(final String thirdcol) {
		this.thirdcolProperty().set(thirdcol);
	}
	

}
