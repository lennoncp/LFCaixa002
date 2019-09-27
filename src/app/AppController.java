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
import javafx.scene.text.Text;
import javafx.util.Duration;

public class AppController implements Initializable {
	
	private int contaTamanhoVBApostas;
	private Aposta aposta;
	private List<Aposta> apostas = new ArrayList<Aposta>();
	private Sorteio sorteio = new Sorteio();;
	
	ObservableList<TextField> fields = FXCollections.observableArrayList();
    
    @FXML
    private ScrollPane spApostas = new ScrollPane();
    
    @FXML
    private VBox vbApostas = new VBox();
    
    @FXML
    private HBox hbDezenas;
    
    @FXML
    private TextField txfQtdDezenas = new TextField();
    
    @FXML
    private TextField txfQtdImpares = new TextField();
    
    @FXML
    private TextField txfQtdApostas = new TextField();
    
    private Button btnGerar = new Button("Gerar");
    private Button btnLimpar = new Button("Limpar");
    
    private int indexAposta = 0;
    
    private Label lb;
    private int contaApostas ;

    void gerarAposta() {
    	contaApostas = 0;
    	
    	while(contaApostas < Integer.valueOf(txfQtdApostas.getText())) {//TODO WHILE
    	
    	if(Integer.valueOf(txfQtdImpares.getText()) == 0) {
    		aposta = sorteio.gerarAposta(Integer.valueOf(txfQtdDezenas.getText()));
    	}else {
    		aposta = sorteio.gerarAposta(Integer.valueOf(txfQtdDezenas.getText()), Integer.valueOf(txfQtdImpares.getText()));
    	}
    	
    	//TODO 
    	System.out.println("Conparador: " + aposta.comparador(aposta, apostas));

    	if(aposta.comparador(aposta, apostas) > 12) {
    		continue;
    	}
    		
    	
    	lb = new Label(String.valueOf(indexAposta));
    	//lb.setVisible(false);
    	lb.setStyle("-fx-border-color: #FFF");
    	apostas.add(indexAposta++, aposta);
    	
    	//TODO almentar vapostas
    	if(apostas.size() > 10) {
    		vbApostas.setPrefHeight(vbApostas.getPrefHeight() + 50);
    	}
    	
    	
    	HBox hb = new HBox();
    	
    	ObservableList<Label> labels = FXCollections.observableArrayList();
    	for(int i = 0; i < Integer.valueOf(txfQtdDezenas.getText()); i++) {
    		labels.add(i, new Label(aposta.getDezenas().get(i).toString()));
    	}
    	
    	Button btnExcluir = new Button("Excluir");
    	
    	
    	hb.setAlignment(Pos.CENTER);
    	hb.setSpacing(2);
    	hb.setPrefHeight(50);
    	hb.getChildren().add(lb);
    	hb.getChildren().addAll(labels);
    	hb.getChildren().add(btnExcluir);

        vbApostas.getChildren().add(hb);
        vbApostas.setPrefWidth(spApostas.getWidth() - 20);
        
      //TODO  System.out.println("SPWIdth: " + spApostas.getWidth() + " vbApostaWidth: " + vbApostas.getPrefWidth());
    	
        btnExcluir.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				vbApostas.getChildren().remove(hb);	
				
				//Remove da lista de apostas
				Label lb = (Label) hb.getChildren().get(0);
				apostas.remove(apostas.get(Integer.valueOf(lb.getText())));
				System.out.println(apostas.toString());
				
				
				 if(contaTamanhoVBApostas > vbApostas.getPrefHeight()) {
					   vbApostas.setPrefHeight(vbApostas.getPrefHeight() - 50);
				   }
			}
		});
    	
      contaApostas++; //Conta as apostas feitas.  
      }//TODO WHILE
    	
    	System.out.println(apostas.toString());
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