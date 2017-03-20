package main;

import np.com.ngopal.control.AutoFillTextBox;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Narayan G. Maharjan
 * @see <a href="http://blog.ngopal.com.np"> Blog </a>
 */
public class ControlTest extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AutoFillTextBox without FilterMode");

        //SAMPLE DATA
        ObservableList<String> data = FXCollections.observableArrayList();
       String[] s = new String[]{"apple","ball","cat","doll","elephant",
            "fight","georgeous","height","ice","jug",
             "aplogize","bank","call","done","ego",
             "finger","giant","hollow","internet","jumbo",
             "kilo","lion","for","length","primary","stage",
             "scene","zoo","jumble","auto","text",
            "root","box","items","hip-hop","himalaya","nepal",
            "kathmandu","kirtipur","everest","buddha","epic","hotel"};

            for(int j=0; j<s.length; j++){
                data.add(s[j]);
            }

        //Layout
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        //CustomControl
        final AutoFillTextBox box = new AutoFillTextBox(data);
        box.setListLimit(10);
        box.setFilterMode(true);
        box.getListview().setVisible(false);

        //Here we are using external Listview instead of AutoFillTextBox's
        ListView list = new ListView();
        list.itemsProperty().bind(box.getListview().itemsProperty());

        //Label
        Label l = new Label("AutoFillTextBox: (Filter Mode 'ON')");
        l.translateYProperty().set(5);
        l.translateXProperty().set(5); 

        vbox.getChildren().addAll(l,box,list);
        Scene scene = new Scene(vbox,300,200);

        primaryStage.setScene(scene);
        //scene.getStylesheets().add(getClass().getResource("control.css").toExternalForm());
        primaryStage.show();

    }
}