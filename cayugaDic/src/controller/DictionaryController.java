/**
 * 
 */
package controller;

/**
 * @author cz5670
 *
 */
import model.Dictionary;
import main.MainApp;
import main.RemoveLine;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;

import org.controlsfx.control.textfield.AutoCompletionBinding;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class DictionaryController {

	
	  @FXML
	  private AnchorPane mainScene;	
	  @FXML
	  private ImageView logoImage;
	  @FXML
	  private ImageView imagebackgroup;
	  @FXML
	  private TableView<Dictionary> dictionaryTable;
	  @FXML
	  private TableColumn<Dictionary, String> firsteColumn;
	  @FXML
	  private TableColumn<Dictionary, String> lastColumn;  
	  @FXML
	  private RadioButton englishBut;
	  @FXML
	  private RadioButton cayugaBut; 
	  
	  @FXML
	  private RadioButton related_1;
	  @FXML
	  private RadioButton related_2; 
	  @FXML
	  private RadioButton related_3; 
	  
	  @FXML
	  private Button relatedWordBut;
	  
	  
	  @FXML
	  private Button searchBut;
	  @FXML
	  private Pane topPane;	  
	  @FXML
	  private Label firstLabel;
	  @FXML
	  private Label lastLabel; 
	  @FXML
	  private Button textBut_1;
	  @FXML
	  private Button textBut_2;
	  @FXML
	  private Button textBut_3;
	  @FXML
	  private Button textBut_4;  
	  @FXML
	  private Button showButton;
	  
	  @FXML
	  private Button deleteButton;
	  
	  @FXML
	  private TextField inputText;
	  
	  private MainApp mainApp;
	  
	  private ArrayList<String> dictionaryEnglishList = new ArrayList<>();
	  private ArrayList<String> dictionaryCayugaList = new ArrayList<>();
	  public DictionaryController() {
		  
	  }
	
	  /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	  */
    @SuppressWarnings("unchecked")
	@FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	
    	firsteColumn.setCellValueFactory(cellData -> cellData.getValue().secondcolProperty());
     	lastColumn.setCellValueFactory(cellData -> cellData.getValue().thirdcolProperty());
    	dictionaryTable.setVisible(true);
    	showButton.setVisible(false);
    	deleteButton.setVisible(false);
    	
    	initialArrayList();
    	initialButtons();
    	
//    	showButton.textProperty().addListener(new ChangeListener() {
//            public void changed(ObservableValue observable, Object oldVal,Object newVal) {
//               System.out.println("text change");
//               //Event.fireEvent(inputText, event);
//            }
//        });
    	
    	

    	
    	new AutoCompletionTextFieldBinding(inputText, new Callback<AutoCompletionBinding.ISuggestionRequest, ArrayList<String>>() {
    	    @Override
    	    public ArrayList<String> call(AutoCompletionBinding.ISuggestionRequest param) {
    	    	
    	    	ArrayList<String> aa = new ArrayList<>();
    	    	String inputString = param.getUserText();
    	    	//System.out.println(inputString);
    	    	if(inputString.length() != 0 && inputString != null) {
        	    	
    	    		if(englishBut.selectedProperty().getValue())
	        	    	for(String item :dictionaryEnglishList) {
	        	    		if(item.matches("^"+inputString+".*$"))
	        	    			aa.add(item);
	        	    	}
    	    		else
            	    	for(String item :dictionaryCayugaList) {
            	    		//System.out.println(item + "          " +inputString);
            	    		if(item.matches("^"+inputString+".*$")){
            	    			//System.out.println("coming");
            	    			aa.add(item);
            	    		}
            	    			
            	    	}
    	    		
    	    		return aa;	
    	    	} else {
    	    		return aa;
    	    	}   	
    	    }
    	});
    	
    

      dictionaryTable.setRowFactory( tv -> {
      TableRow<Dictionary> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
    	  
    	  if(event.getClickCount() == 1 && (! row.isEmpty()) ) {
    		  
    		  logoImage.setVisible(false);
    		  showButton.setVisible(true);
    		  deleteButton.setVisible(true);		  
//    		  Dictionary rowData = row.getItem();
    		  
    		  //double click the row, show the dialog
    	  }else if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
        	 
    		  showAction(null);
//    		  	Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();
//    		 	   
//    		    if (tempData !=null) {
//    		    		    	
//    	    	   boolean okClicked = mainApp.showAddItemDialog(tempData);
//    	           if (okClicked) {
//    	               mainApp.getData().add(tempData);
//    	           } 
//    		    	
//    		    } else {
//    		        // Nothing selected.
//    		    	Alert alert = new Alert(AlertType.INFORMATION);
//    		    	alert.setTitle("Information Dialog");
//    		    	alert.setHeaderText(null);
//    		    	alert.setContentText("Please select one row !");
//    		    	alert.showAndWait();
//    		    }
    		  
          }
         
      });
      return row ;
      });
    	
    }
        
    private void initialButtons() {
    	
    	final ToggleGroup group = new ToggleGroup();
      	englishBut.setToggleGroup(group);
    	englishBut.setSelected(true);
    	cayugaBut.setToggleGroup(group);
    	relatedWordBut.setVisible(false);
    	
    	final ToggleGroup group2 = new ToggleGroup();
    	related_1.setToggleGroup(group2);
    	related_2.setToggleGroup(group2);
    	related_3.setToggleGroup(group2);
    	related_1.setVisible(false);
    	related_2.setVisible(false);
    	related_3.setVisible(false);
    	//related_3.setText("ni hao a");
    	
    	textBut_1.setVisible(false);
    	textBut_2.setVisible(false);
    	textBut_3.setVisible(false);
    	textBut_4.setVisible(false);
    	
    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    		
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {
    	    	
    	            if (group.getSelectedToggle() != null) {
    	            	if(englishBut.selectedProperty().getValue()) {
    	                	textBut_1.setVisible(false);
    	                	textBut_2.setVisible(false);
    	                	textBut_3.setVisible(false);
    	                	textBut_4.setVisible(false);
    	                	
    	                	relatedWordBut.setVisible(false);
    	                	related_1.setVisible(false);
    	                	related_2.setVisible(false);
    	                	related_3.setVisible(false);
    	                	
    	                	
    	                	mainApp.getData().clear();
    	                	inputText.clear();
    	                	
    	                	  logoImage.setVisible(true);
    	            		  showButton.setVisible(false);
    	            		  deleteButton.setVisible(false);
    	                	
    	            	} else {
    	            		inputText.clear();
		            		  logoImage.setVisible(true);
		            		  showButton.setVisible(false);
		            		  deleteButton.setVisible(false);
		            		  
		            		  relatedWordBut.setVisible(true);
		            		related_1.setVisible(false);
		            	    	related_2.setVisible(false);
		            	    	related_3.setVisible(false);
		            		  
		            		  
    	                	textBut_1.setVisible(true);
    	                	textBut_2.setVisible(true);
    	                	textBut_3.setVisible(true);
    	                	textBut_4.setVisible(true);
    	                	mainApp.getData().clear();
    	            	}
    	            }                
    	        }
    	});
    	
    	
    	group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    		
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {
    	    	
    	    	
	    	    	relatedWordBut.requestFocus();
		            if (group2.getSelectedToggle() != null) {
		            	mainApp.getData().clear();
		            	String currentInput;
		            	if(related_1.selectedProperty().getValue()) {
		            		currentInput = related_1.getText();
		                	
		            	} else if (related_2.selectedProperty().getValue()) {
		            		currentInput = related_2.getText();
		            	}
		            	
		            	else {
		            		currentInput = related_3.getText();
		            	}
		            	
		        		for(int i=0; i<dictionaryCayugaList.size();i++) {
		    			
			    			if(dictionaryCayugaList.get(i).toString().matches(".*"+currentInput+".*")) {
			    				//System.out.println("coming in update dictionaryCayugaList");
			    				Dictionary tempData = new Dictionary();
			    				tempData.setSecondcol(dictionaryEnglishList.get(i));
			    				tempData.setThirdcol(dictionaryCayugaList.get(i));
			    				mainApp.getData().add(tempData);
			    			}
		        		}
		            }                
	    	   }
    	});
    	
  
    
    	
    }
    
    private void initialArrayList() {
    	String file = "data/cayuga-english_5.txt";
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(file);
			Reader chars = new InputStreamReader(fstream, StandardCharsets.UTF_16);
			BufferedReader br = new BufferedReader(chars);
			String strLine;
			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null && !strLine.trim().isEmpty())   {
				
				String[] outstring = strLine.split("     ");
				//System.out.println(outstring.length);
				//if(outstring.length !=2)System.out.println(strLine);
				dictionaryCayugaList.add(outstring[0].replaceAll("\\p{C}", "").trim());
				dictionaryEnglishList.add(outstring[1]);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        dictionaryTable.setItems(mainApp.getData());
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewData() {
    	Dictionary tempData = new Dictionary();
        boolean okClicked = mainApp.showAddItemDialog(tempData);
        if (okClicked) {
        	dictionaryCayugaList.add(tempData.getThirdcol());
        	dictionaryEnglishList.add(tempData.getSecondcol());
            mainApp.getData().add(tempData);
        }     
    }
    
    @FXML
    public void onEnter(ActionEvent ae){
    	       
       	mainApp.getData().clear();
    	related_1.setVisible(false);
    	related_2.setVisible(false);
    	related_3.setVisible(false);
    	
       	searchBut.requestFocus();
       	String currentInput = inputText.getText();
       	if(currentInput.length() == 0) return;
		if(englishBut.selectedProperty().getValue()) {

			for(int i=0; i<dictionaryEnglishList.size();i++) {
			
				String words = dictionaryEnglishList.get(i).toString();
		
				String matches = "^"+currentInput+".*";
				
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {
					
					if(words_sb.charAt(a)==')'||words_sb.charAt(a)=='(') words_sb.deleteCharAt(a);
					
				}
				words = words_sb.toString();
				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {
					
					if(sb.charAt(a)==')'||sb.charAt(a)=='(') sb.deleteCharAt(a);
					
				}
					
				matches = sb.toString();
				
//				System.out.println(words);
//				System.out.println(matches);
				
				if(words.matches(matches)) {

					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}	
		} else {
			
			for(int i=0; i<dictionaryCayugaList.size();i++) {
				
				if(dictionaryCayugaList.get(i).toString().matches(".*"+currentInput+".*")) {
					//System.out.println("coming in 1");
					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}
			
		}
		
		
		
		
    }
    
    public void showAction(ActionEvent ae) {
    	
   
    	
    	Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();

 	   
	    if (tempData !=null) {
	    		   
	    	String english = tempData.getSecondcol();
	    	String cayuga = tempData.getThirdcol();
	    	
	    	Dictionary old = mainApp.showUpdateItemDialog(tempData);
//           if (old != null) {
//        	   ListIterator<Dictionary> iterator =  mainApp.getData().listIterator(0);
//        	   int i = 0;
//        	   while (iterator.hasNext()){
//        		   Dictionary iteratoerDic = iterator.next();
//        		   
//        		   if(iteratoerDic.secondcolProperty().getValue().equals(old.secondcolProperty().getValue())
//        				   &&iteratoerDic.thirdcolProperty().getValue().equals(old.thirdcolProperty().getValue())){
////        			   System.out.println("coming");
////        			   System.out.println(mainApp.getData().get(i).getSecondcol());
////        			   System.out.println(mainApp.getData().get(i).getThirdcol());
////        			   System.out.println(tempData.getSecondcol());
////        			   System.out.println(tempData.getThirdcol());
//        			   mainApp.getData().get(i).setSecondcol(tempData.getSecondcol());
//        			   mainApp.getData().get(i).setThirdcol(tempData.getThirdcol());
//        		   }
//        		   i++;     
//        	   }
//        	   
//        	   System.out.println("outside in update dictionaryEnglishList");
//        	   System.out.println(old.secondcolProperty().getValue());
//        	   System.out.println(old.thirdcolProperty().getValue());
//        	   System.out.println(tempData.getSecondcol());
//        	   System.out.println(tempData.getThirdcol());
               for(int j=0; j<dictionaryEnglishList.size();j++) {
            	   //System.out.println(dictionaryEnglishList.get(j));
            	   if(dictionaryEnglishList.get(j).toString().equals(english) 
            			   &&dictionaryCayugaList.get(j).toString().equals(cayuga))  {
            		 //System.out.println("coming in update dictionaryEnglishList");
            		   dictionaryEnglishList.set(j, tempData.getSecondcol());
            		   dictionaryCayugaList.set(j, tempData.getThirdcol());
            		   
            	   }
            	   
               }
        	   
//           }
           

	    } else {
	        // Nothing selected.
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information Dialog");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Please select one row !");
	    	alert.showAndWait();
	    }
    }
    
    public void deleteAction(ActionEvent ae) {
    	
    	   int selectedIndex = dictionaryTable.getSelectionModel().getSelectedIndex();
    	   Dictionary tempData = dictionaryTable.getSelectionModel().getSelectedItem();
    	   
    	    if (selectedIndex >= 0) {
    	    	
    	 
    	    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	    	alert.setTitle("Confirmation Dialog");
    	    	alert.setHeaderText("Delete");
    	    	alert.setContentText("Are you sure to delete this row from the database?");

    	    	Optional<ButtonType> result = alert.showAndWait();
    	    	if (result.get() == ButtonType.OK){
    
    	    		RemoveLine rl = new RemoveLine();
    	        	rl.removeLine(tempData.getThirdcol(),tempData.getSecondcol());
    	    		
    	    		dictionaryTable.getItems().remove(selectedIndex); 
    	    		
	               for(int j=0; j<dictionaryEnglishList.size();j++) {
	            	   
	            	   if(dictionaryEnglishList.get(j).toString().equals(tempData.getSecondcol()) 
	            			   &&dictionaryCayugaList.get(j).toString().equals(tempData.getThirdcol()))  {
	            		   
	            		 //System.out.println("coming in update dictionaryEnglishList");
	            		 dictionaryEnglishList.remove(j);
	            		 dictionaryCayugaList.remove(j);    	            		   
	            	   }   
	               }  	    		
    	    	} else {
	
    	    	}
    	    	
    	    } else {
    	        // Nothing selected.
    	    	Alert alert = new Alert(AlertType.INFORMATION);
    	    	alert.setTitle("Information Dialog");
    	    	alert.setHeaderText(null);
    	    	alert.setContentText("Please select one row !");
    	    	alert.showAndWait();
    	    }
    	
    }
    
       
    @FXML
    public void searchButtonAction() {
    	
    	onEnter(null);	
    }
    
    @FXML
    public void MouseMoveText() {
    	mainScene.requestFocus();
    }
    
    @FXML
    public void textButAction_1(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ˀ");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_2(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ǫ");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_3(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+":");
    	inputText.positionCaret(currentString.length()+1);
    }
    @FXML
    public void textButAction_4(ActionEvent ae){
    	inputText.requestFocus();
    	String currentString = inputText.getText();
    	inputText.setText(currentString+"ę");
    	inputText.positionCaret(currentString.length()+1);
    }
    
    @FXML
    public void partialSearch(ActionEvent ae) {
    	
    	mainApp.getData().clear();
    	related_1.setVisible(false);
    	related_2.setVisible(false);
    	related_3.setVisible(false);
    	
    	
       	String currentInput = inputText.getText();
       	if(currentInput.length() == 0) return;
		if(englishBut.selectedProperty().getValue()) {

			for(int i=0; i<dictionaryEnglishList.size();i++) {
			
				String words = dictionaryEnglishList.get(i).toString();
		
				String matches = ".*"+currentInput+".*";
				
				StringBuilder words_sb = new StringBuilder(words);
				for(int a=0; a<words_sb.length();a++) {
					
					if(words_sb.charAt(a)==')'||words_sb.charAt(a)=='(') words_sb.deleteCharAt(a);
					
				}
				words = words_sb.toString();
				
				StringBuilder sb = new StringBuilder(matches);
				for(int a=0; a<sb.length();a++) {
					
					if(sb.charAt(a)==')'||sb.charAt(a)=='(') sb.deleteCharAt(a);
					
				}
					
				matches = sb.toString();
							
				if(words.matches(matches)) {

					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}	
		} else {
			
			for(int i=0; i<dictionaryCayugaList.size();i++) {
				
				if(dictionaryCayugaList.get(i).toString().matches(".*"+currentInput+".*")) {
					//System.out.println("coming in update dictionaryCayugaList");
					Dictionary tempData = new Dictionary();
					tempData.setSecondcol(dictionaryEnglishList.get(i));
					tempData.setThirdcol(dictionaryCayugaList.get(i));
					mainApp.getData().add(tempData);
				}
			}
			
		}
    	
    }
      
    public void relatedWord(ActionEvent ae) {
    	
    	String currentInput = inputText.getText();
       	if(currentInput.length() < 3) partialSearch(null);
       	int total = 0;
    	int length = currentInput.length();
       	int subStringLength = length-1;
       	ArrayList<String> newList = new ArrayList<>(dictionaryCayugaList);
       	
       	for(int i =subStringLength; i>=2; i--) {
       		if(total >=3) break;
       		
       		for(int j=0; j+i<=length; j++) {
       			
       			if(total >=3) break;
       			
       			String sub  = currentInput.substring(length-j-i,length-j);
       			System.out.println(sub);
       			
       			for(int d=0; d<newList.size();d++) {
    			
	    			if(newList.get(d).toString().matches(".*"+sub+".*") && !newList.get(d).toString().matches(".*"+currentInput+".*")) {
	    				
	    				if(total == 0) {
	    					newList.remove(d);
	    					related_1.setText(sub);
	    					related_1.setVisible(true);
	    					total++;
	    					break;
	    				}
	    				else if(total == 1) {
	    					newList.remove(d);
	    					related_2.setText(sub);
	    					related_2.setVisible(true);
	    					total++;
	    					break;
	    				}
	    				else if(total == 2) {
	    					newList.remove(d);
	    					related_3.setText(sub);
	    					related_3.setVisible(true);
	    					total++;
	    					break;
	    				} else {
	    					
	    					break;
	    				}		
	    			}
       			}
       			
       		}       		
       	}
       	
//    	Dictionary tempData = new Dictionary();
//		tempData.setSecondcol(dictionaryEnglishList.get(d));
//		tempData.setThirdcol(dictionaryCayugaList.get(d));
//		mainApp.getData().add(tempData);
       
//        for( int c = 0 ; c < length ; c++ )
//        {
//           for( int i = 1 ; i <= length - c ; i++ )
//           {
//        	   if(c+i == length) continue;
//              sub = currentInput.substring(c, c+i);
//              System.out.println(sub);
//           }
//        }
//       	
//			
//		for(int i=0; i<dictionaryCayugaList.size();i++) {
//			
//			if(dictionaryCayugaList.get(i).toString().matches(".*"+currentInput+".*")) {
//				//System.out.println("coming in update dictionaryCayugaList");
//				Dictionary tempData = new Dictionary();
//				tempData.setSecondcol(dictionaryEnglishList.get(i));
//				tempData.setThirdcol(dictionaryCayugaList.get(i));
//				mainApp.getData().add(tempData);
//			}
//		}
			
		
       	
       	

    }
    
}
