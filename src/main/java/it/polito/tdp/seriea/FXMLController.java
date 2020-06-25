package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.seriea.model.Model;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

//controller turno A --> switchare al branch master_turnoB o master_turnoC per turno B o C

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Team> boxSquadra;

    @FXML
    private ChoiceBox<Season> boxStagione;

    @FXML
    private Button btnCalcolaConnessioniSquadra;

    @FXML
    private Button btnSimulaTifosi;

    @FXML
    private Button btnAnalizzaSquadre;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalizzaSquadre(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText(model.creaGrafo());
    	
    	boxSquadra.getItems().clear();
    	boxSquadra.getItems().addAll(model.vertici());
    	

    }

    @FXML
    void doCalcolaConnessioniSquadra(ActionEvent event) {
    	txtResult.clear();
    	Team selezionato=boxSquadra.getValue();
    	if(selezionato==null) {
    		txtResult.appendText("DEVI SELEZIONARE UN TEAM");
    		return;
    	}
    	
    	txtResult.appendText(model.calcolaConnessioni(selezionato));
    	boxStagione.getItems().clear();
    	boxStagione.getItems().addAll(model.ritornaStagioni());

    }

    @FXML
    void doSimulaTifosi(ActionEvent event) {
    	txtResult.clear();
    	Season selezionato=boxStagione.getValue();
    	if(selezionato==null) {
    		txtResult.appendText("DEVI SELEZIONARE UNA STAGIONE");
    		return;
    	}

    	txtResult.appendText(model.simula(selezionato));
    }

    @FXML
    void initialize() {
        assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert boxStagione != null : "fx:id=\"boxStagione\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnSimulaTifosi != null : "fx:id=\"btnSimulaTifosi\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert btnAnalizzaSquadre != null : "fx:id=\"btnAnalizzaSquadre\" was not injected: check your FXML file 'SerieA.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}