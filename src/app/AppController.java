package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.model.Aposta;
import app.model.Sorteio;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class AppController implements Initializable {
	
	private int contaTamanhoVBApostas;
	private Aposta aposta;
	private List<Aposta> apostas;
	private Sorteio sorteio = new Sorteio();;
	
	ObservableList<TextField> fields = FXCollections.observableArrayList();
    
    @FXML
    private ScrollPane spApostas = new ScrollPane();
    
    @FXML
    private VBox vbApostas = new VBox();
    
    @FXML
    private HBox hbDezenas;
    
    private Button btnGerar = new Button("Gerar");
    private Button btnLimpar = new Button("Limpar");
    
    int qtdDesenas = 18;

    void gerarAposta() {
    	
    	/*List<Integer> dez = new ArrayList<Integer>();
    	
    	for(int i = 1; i <= 18; i++) {
    		dez.add(i);
    	}
    	
    	aposta = new Aposta();*/
    	
    	aposta = sorteio.gerarAposta(qtdDesenas);
    	
    	HBox hb = new HBox();
    	
    	ObservableList<Label> labels = FXCollections.observableArrayList();
    	for(int i = 0; i < qtdDesenas; i++) {
    		labels.add(i, new Label(aposta.getDezenas().get(i).toString()));
    	}
    	
    	Button btnExcluir = new Button("Excluir");
    	
    	
    	hb.setAlignment(Pos.CENTER);
    	hb.setSpacing(2);
    	hb.setPrefHeight(50);
    	hb.getChildren().addAll(labels);
    	hb.getChildren().add(btnExcluir);

        vbApostas.getChildren().add(hb);
        vbApostas.setPrefWidth(spApostas.getWidth() - 20);
        
        System.out.println("SPWIdth: " + spApostas.getWidth() + " vbApostaWidth: " + vbApostas.getPrefWidth());
    	
        btnExcluir.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				vbApostas.getChildren().remove(hb);	
				
				 if(contaTamanhoVBApostas > vbApostas.getPrefHeight()) {
					   vbApostas.setPrefHeight(vbApostas.getPrefHeight() - 50);
				   }
			}
		});
    	

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vbApostas.setAlignment(Pos.TOP_CENTER);
		
		for(int i = 0; i < 18; i++) {
    		fields.add(i, new TextField());
    	}
		
		btnGerar.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				
				gerarAposta(); //TODO
				
				contaTamanhoVBApostas += 50;		
				   
				   if(contaTamanhoVBApostas > vbApostas.getPrefHeight()) {
					   vbApostas.setPrefHeight(vbApostas.getPrefHeight() + 50);
				   }
			}
		});
		
		hbDezenas.getChildren().addAll(fields);
		hbDezenas.getChildren().addAll(btnLimpar, btnGerar);
	}


}