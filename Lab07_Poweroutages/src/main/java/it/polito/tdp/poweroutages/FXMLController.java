/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboBox"
    private ComboBox<Nerc> comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtYears"
    private TextField txtYears; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalysis"
    private Button btnAnalysis; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doAnalysis(ActionEvent event) {
    	txtResult.clear();
    	String anni=txtYears.getText();
    	String ore=txtHours.getText();
    	Nerc nerc=comboBox.getValue();
    	if(nerc==null) {
    		txtResult.appendText("Devi scegliere un nerc!");
    		return;
    	}
    	int X=0;
    	int Y=0;
    	try {
    		X=Integer.parseInt(anni);
    		Y=Integer.parseInt(ore);
    	} catch(Exception e) {
    		txtResult.appendText("Devi inserire numeri!");
    		return;
    	}
    	List<String> risultato=new ArrayList<String>(model.worstCase(X, Y, nerc));
    	txtResult.appendText("Tot people affected: "+model.getTotCustomers()+"\n");
    	txtResult.appendText("Tot hours of outage: "+model.getTotHours()+"\n");
    	for(String s:risultato) {
    		txtResult.appendText(s);
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYears != null : "fx:id=\"txtYears\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalysis != null : "fx:id=\"btnAnalysis\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        model=new Model();

    }

	public void setModel(Model model) {
		this.model=model;
		comboBox.getItems().addAll(model.getNercList());
	}
}
