package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import app.dao.ConcursoDAO;
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
import javafx.print.Printer.MarginType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private VBox vbPrincipal;
    
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
    
    @FXML
    private Text TxNConcursos;
    
    @FXML
    private TextField txfRange;
    
    @FXML
    private TextField txfMaiorMedia;

    @FXML
    private TextField txfMenorMedia;
    
    @FXML
    private Button btnRegistrarApostas;
    
    HBox hbDezContagem = new HBox();
    HBox hbDezPorcentagens = new HBox();
    
    private Button btnGerar = new Button("Gerar");
    private Button btnLimpar = new Button("Limpar");
    
    private int indexAposta = 0;
    
    private Label lb;
    private int contaApostas ;
    
    private int media = 0;
    
    private List<Aposta> concursos = new ArrayList<Aposta>();
    
    private ConcursoDAO cdao = new ConcursoDAO();
    
    //A aposta deve ter de 4 a 7 numeros primos
    List<Integer> primos = Arrays.asList(2,3,5,7,11,13,17,19,23);

    void gerarAposta() {
    	
    	//TODO gerando porcentagens
    	List<Integer> porcentagens = cdao.getRangePorcentagens(Integer.valueOf(txfRange.getText()));
    	System.out.print("Porcentagens: " + porcentagens.toString());
    	int soma = 0;

    	for(Integer v : porcentagens) {
    		soma += v;
    	}
    	
    	media = soma / porcentagens.size();
    	System.out.print(" Media: " + media);
    	// gerando porcentagem
    	
    	contaApostas = 0;
    	while(contaApostas < Integer.valueOf(txfQtdApostas.getText())) {//TODO WHILE
    	
    	if(Integer.valueOf(txfQtdImpares.getText()) == 0) {
    		aposta = sorteio.gerarAposta(Integer.valueOf(txfQtdDezenas.getText()));
    	}else {
    		aposta = sorteio.gerarAposta(Integer.valueOf(txfQtdDezenas.getText()), Integer.valueOf(txfQtdImpares.getText()));

    	}
    	
    	int apostaComparadaConcursos = aposta.comparador(aposta, concursos);
    	if( apostaComparadaConcursos > 13) {
    		//TODO 
        	System.out.println("Conparador Concursos: " + apostaComparadaConcursos);
    		continue;
    	}
    	
    	int apostaComparada = aposta.comparador(aposta, apostas);
    	if( apostaComparada > 13) {
    		//TODO 
        	System.out.println("Conparador Apostas: " + apostaComparada);
    		continue;
    	}
    	
    	if(!apostas.isEmpty())
    	if(apostaComparadaConcursos < 11 || apostaComparada < 11) {
    		//TODO 
        	System.out.println("Conparador Apostas: " + apostaComparada + " Concurso: " + apostaComparadaConcursos);
        	System.out.print(" Apostas: " + apostas.toString() + " ");
    		continue;
    	}
    	
    	//IF verifica primos
    	List<Integer> dezena = new ArrayList<Integer>();
    	dezena.addAll(Arrays.asList(1,4,6,8,9,10,12,14,15,16,18,20, 21, 22,24));
    	int contPrimos = 0;
    	for(int n : aposta.getDezenas()) {
    		if(primos.contains(n))
    			contPrimos++;
    	}
    	
    	System.out.println(" ContPrimos FORA: " + contPrimos + " ");
    	
    	List<Integer> inPrimos = Arrays.asList(5,6,7);
    	if(!inPrimos.contains(contPrimos)) {
    		System.out.println(" ContPrimos DENTRO: " + contPrimos + " ");
    		continue;
    	}
    	
    	//IF verifica primos
    		  	
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
    	
    	//TODO gerando media das minhas apostas
    	List<Integer> porcentagensMinhasApostas = cdao.getApostasPorcentagens(apostas);
    	System.out.print("Porcentagens: " + porcentagensMinhasApostas.toString());
    	int somaApostas = 0;

    	for(Integer v : porcentagensMinhasApostas) {
    		somaApostas += v;
    	}
    	
    	media = somaApostas / porcentagensMinhasApostas.size();
    	System.out.print(" Media das Apostas: " + media);
    	// gerando porcentagem das minhas apostas
    	
    	//TODO Carregando HBOX hbDezContagem
    	Integer[] contagem = aposta.contagem(apostas);
    	
    	List<Label> labels = new ArrayList<Label>();
    	for(int i = 0; i < contagem.length ; i++ ) {
    		
    		Label lb = new Label(" " + (i+1) + " : " + contagem[i] );
    		lb.getStyleClass().add("lbcontagem");
    		lb.setPrefWidth(40);
    		lb.setPrefHeight(40);
        	labels.add(lb);
        	
    	}
    	
    	hbDezContagem.getStyleClass().add("hbmargem");
    	hbDezContagem.setPrefWidth(45);
    	hbDezContagem.getChildren().addAll(labels);
    	vbPrincipal.getChildren().add(hbDezContagem);
    	//TODO Carregando HBOX hbDezContagem
    	
    	//TODO Carregando hbox com porcentagens   	
    	List<Label> labelPorcentagens = new ArrayList<Label>();
    	for(int i = 0; i < contagem.length ; i++ ) {
    		
    		Label lbp = new Label(" " + (i+1) + " : " + porcentagens.get(i) );
    		lbp.getStyleClass().add("lbcontagem");
    		lbp.setPrefWidth(40);
    		lbp.setPrefHeight(40);
    		labelPorcentagens.add(lbp);
        	
    	}
    	
    	hbDezPorcentagens.getStyleClass().add("hbmargem");
    	hbDezPorcentagens.setPrefWidth(45);
    	hbDezPorcentagens.getChildren().addAll(labelPorcentagens);
    	vbPrincipal.getChildren().add(hbDezPorcentagens);
    	//TODO Carregando HBOX hbDezPorcentagens
    	
    }
    

    @FXML
    void registrarApostas(ActionEvent event) {

    	for(int i = 0; i < apostas.size(); i++) {
    		Long codigo = cdao.salvarAposta(apostas.get(i));
    		System.out.println("Registro de Aposta: " + codigo);
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vbApostas.setAlignment(Pos.TOP_CENTER);
		
		//TODO 
		concursos = cdao.getAllConcursosSimples();
		
		TxNConcursos.setText(String.valueOf(concursos.size()));
		
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
		
		btnLimpar.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				vbPrincipal.getChildren().remove(1);
				hbDezContagem.getChildren().clear();
				hbDezPorcentagens.getChildren().clear();
				vbApostas.getChildren().clear();
				apostas.clear();
				indexAposta = 0;
			}
		});
	
	}


}