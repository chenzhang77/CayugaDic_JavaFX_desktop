/**
 * 
 */
package controller;

/**
 * @author cz5670
 *
 */

import javafx.event.ActionEvent;
/**
 * @author cz5670
 *
 */
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.RemoveLine;
import main.WriteToFile;
import model.Dictionary;
import org.controlsfx.dialog.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;

public class UpdateDataController {

    @FXML
    private TextField firstField;
    @FXML
    private TextField lastField;   
    @FXML
    private Button textButAdd_1;
    @FXML
    private Button textButAdd_2;
    @FXML
    private Button textButAdd_3;
    @FXML
    private Button textButAdd_4;
	  
    private Stage dialogStage;
    private Dictionary dictionary;
    private boolean okClicked = false;
    private boolean newDictionary = false;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	firstField.setText(" ");
    	
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the dictionary to be edited in the dialog.
     * 
     * @param dictionary
     */
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
        lastField.setText(dictionary.getSecondcol());
        firstField.setText(dictionary.getThirdcol());
        
        
        if(dictionary.getSecondcol() == null && dictionary.getThirdcol() == null) newDictionary = true;
        else{
        	newDictionary = false;
        	oldDictionary();
        }
        	

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    public Dictionary oldDictionary() {
    	Dictionary dic = new Dictionary();
    	dic.setSecondcol(dictionary.getSecondcol());
    	dic.setThirdcol(dictionary.getThirdcol());
    	return dic;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	
        if (isInputValid()) {
        	
        	RemoveLine rl = new RemoveLine();
        	rl.removeLine(dictionary.getThirdcol(),dictionary.getSecondcol());
 
        	dictionary.setFirstcol(lastField.getText());
        	dictionary.setSecondcol(lastField.getText());
        	dictionary.setThirdcol(firstField.getText());     
        	
        	
        	
        	 WriteToFile wf = new WriteToFile();
             wf.addNewItem(firstField.getText(),lastField.getText());   
        	
        	dialogStage.close();
        

        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstField.getText() == null || firstField.getText().length() == 0) {
            errorMessage += "No valid english input!"; 
            //firstField.setText(errorMessage);
        }
        if (lastField.getText() == null || lastField.getText().length() == 0) {
            errorMessage += "No valid cayuga input!"; 
            //lastField.setText(errorMessage);
        }
        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Information Dialog");
        	alert.setHeaderText("Look, an Information Dialog");
        	alert.setContentText(errorMessage);
        	alert.showAndWait();
            return false;
        }
    }
    
    @FXML
    public void textButAction_1(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+"ˀ");
    	firstField.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_2(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+"ǫ");
    	firstField.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_3(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+":");
    	firstField.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_4(ActionEvent ae){
    	firstField.requestFocus();
    	String currentString = firstField.getText();
    	if(currentString == null)currentString = "";
    	firstField.setText(currentString+"ę");
    	firstField.positionCaret(currentString.length()+1);
    }
    
    @FXML
    public void MouseMoveText() {
    	//mainScene.requestFocus();
    }
    
}
