package windows;

import banco.dominio.Banco;
import banco.dominio.Cliente;
import banco.dominio.Conta;
import banco.dominio.ContaCorrente;
import banco.dominio.ContaPoupanca;
import banco.relatorios.RelatorioClientes;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class MainWindow {
    
    Stage window;
    Scene homePage, sobrePage, relatorioPage, addClientePage, addContaPage;
    Scene searchClientePage, searchOperacoesPage, operacoesPage;
    Banco banco = Banco.getBanco();
    Cliente cliente;
    Conta conta;
    RelatorioClientes relatorio = new RelatorioClientes();
    
    public MainWindow(Stage stage){
        window = stage;
    }
    public void display(){
        openHomePage();
        window.setTitle("JavaBank");
        window.show();
    }
    
    public BorderPane addNavbarAndFooter(BorderPane template){
        
        //=============== navbar e footer =====================//
        
        //==navbar-buttons==//
        Button homebutton = new Button("Home"), sobrebutton = new Button("Sobre");
        homebutton.getStyleClass().add("navbar-buttons");
        sobrebutton.getStyleClass().add("navbar-buttons");
        
        homebutton.setOnAction(e -> openHomePage());
        sobrebutton.setOnAction(e -> openSobrePage());
        
        //=====navbar======//
        HBox navbar = new HBox(10);
        navbar.getStyleClass().add("navbar");
        navbar.setPrefHeight(50);
        navbar.setAlignment(Pos.CENTER_LEFT);
        Image logo = new Image("windows/javabank.png");
        ImageView logoview = new ImageView(logo);
        logoview.setFitHeight(48);
        logoview.setFitWidth(200);
        navbar.getChildren().add(logoview);
        navbar.getChildren().addAll(homebutton, sobrebutton);
        
        //=====footer======//
        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.setPrefHeight(20);
        footer.setAlignment(Pos.CENTER);
        Label footercopyright = new Label("JavaBank® 2018");
        footercopyright.getStyleClass().add("footer-copyright");
        footer.getChildren().addAll(footercopyright);
        
        //=adicinando navbar e footer ao template=//
        template.setTop(navbar);
        template.setBottom(footer);
        
        return template;
    }
    public void openHomePage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        sobrePage = new Scene(template, 1300, 700);
        sobrePage.getStylesheets().add("windows/stylesheet.css");
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45);
        titleBar.setAlignment(Pos.CENTER);
        Label pageTitle = new Label("Sistema JavaBank de gerenciamento de clientes");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        //botão cima esquerdo
        Button topLeftButton = new Button("Adicionar Cliente");
        topLeftButton.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        topLeftButton.getStyleClass().add("options-button");
        topLeftButton.setPrefHeight(400);
        topLeftButton.setPrefWidth(400);
        topLeftButton.setOnAction(e -> openAddClientePage());
        GridPane.setHalignment(topLeftButton, HPos.CENTER);
        
        //botão cima direito
        Button topRightButton = new Button("Adicionar Conta");
        topRightButton.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        topRightButton.getStyleClass().add("options-button");
        topRightButton.setPrefHeight(400);
        topRightButton.setPrefWidth(400);
        topRightButton.setOnAction(e -> openSearchClientePage());
        GridPane.setHalignment(topRightButton, HPos.CENTER);
        
        //botão baixo esquerdo
        Button botLeftButton = new Button("Operações Bancárias");
        botLeftButton.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        botLeftButton.getStyleClass().add("options-button");
        botLeftButton.setPrefHeight(400);
        botLeftButton.setPrefWidth(400);
        botLeftButton.setOnAction(e -> openSearchOperacoesPage());
        GridPane.setHalignment(botLeftButton, HPos.CENTER);
        
        //botão baixo direito
        Button botRightButton = new Button("Gerar Relatório");
        botRightButton.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        botRightButton.getStyleClass().add("options-button");
        botRightButton.setPrefHeight(400);
        botRightButton.setPrefWidth(400);
        botRightButton.setOnAction(e -> openRelatorioPage());
        GridPane.setHalignment(botRightButton, HPos.CENTER);
        
        //grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(40);
        grid.setHgap(30);
        
        grid.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        
        col1.setPercentWidth(50);
        col2.setPercentWidth(50);
        row1.setPercentHeight(10);
        row2.setPercentHeight(45);
        row3.setPercentHeight(45);
        
        grid.getColumnConstraints().addAll(col1,col2);
        grid.getRowConstraints().addAll(row1, row2, row3);
        
        grid.add(titleBar, 0, 0, 2, 1); //colindex, rowindex, colspan, rowspan
        grid.add(topLeftButton, 0, 1);
        grid.add(topRightButton, 1, 1);
        grid.add(botLeftButton, 0, 2);
        grid.add(botRightButton, 1, 2);
        
        //grid.setGridLinesVisible(true);
        
        
        template.setCenter(grid);
        window.setScene(sobrePage);
        window.show();
    }
    public void openSobrePage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        sobrePage = new Scene(template, 1300, 700);
        sobrePage.getStylesheets().add("windows/stylesheet.css");
        
        //grid
        GridPane grid = new GridPane();
        HBox titleBox = new HBox();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(8);
        grid.setHgap(10);
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45);
        titleBar.setPrefWidth(3000);
        titleBar.setAlignment(Pos.CENTER);
        
        Label pageTitle = new Label("Sobre o sistema");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        //caixa de texto
        HBox contentBar = new HBox(10);
        contentBar.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        contentBar.getStyleClass().add("content-bar");
        contentBar.setPrefHeight(400);
        contentBar.setPrefWidth(3000);
        contentBar.setAlignment(Pos.TOP_LEFT);
        
        Label infoTitle = new Label("Desenvolvido por:          ");
        infoTitle.getStyleClass().add("page-title");
        Label infoText = new Label("Wendell J. C. Ávila\nRA: 2017.1.08.013\n\nDisciplina: Programação Orientada a Objetos\n"
                                    + "Universidade Federal de Alfenas\n\n28/07/2018");
        infoText.getStyleClass().add("page-text");
        contentBar.getChildren().addAll(infoTitle, infoText);
        
        GridPane.setConstraints(titleBar, 0, 0);
        GridPane.setConstraints(contentBar, 0, 8);
        
        grid.getChildren().addAll(titleBar, contentBar);
        
        template.setCenter(grid);
        window.setScene(sobrePage);
        window.show();
    }
    public void openRelatorioPage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        relatorioPage = new Scene(template, 1300, 700);
        relatorioPage.getStylesheets().add("windows/stylesheet.css");
        
        //grid
        GridPane grid = new GridPane();
        HBox titleBox = new HBox();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(8);
        grid.setHgap(10);
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45);
        titleBar.setPrefWidth(3000);
        titleBar.setAlignment(Pos.CENTER);
        
        Label pageTitle = new Label("Relatório dos clientes e suas contas");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        TextArea textArea = new TextArea(relatorio.geraRelatorio());
        textArea.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        textArea.getStyleClass().add("content-bar");
        textArea.setPrefHeight(400);
        textArea.setPrefWidth(3000);
        //textArea.setAlignment(Pos.TOP_LEFT);
        
        GridPane.setConstraints(titleBar, 0, 0);
        GridPane.setConstraints(textArea, 0, 8);
        
        grid.getChildren().addAll(titleBar, textArea);
        
        template.setCenter(grid);
        window.setScene(relatorioPage);
        window.show();
    }
    public void openAddClientePage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        addClientePage = new Scene(template, 1300, 700);
        addClientePage.getStylesheets().add("windows/stylesheet.css");
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45); 
        titleBar.setAlignment(Pos.CENTER);
        Label pageTitle = new Label("Adicionar Cliente");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        //conteudo
        HBox contentBar = new HBox();
        contentBar.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        contentBar.getStyleClass().add("content-bar");
        contentBar.setPrefHeight(400);
        
        //labels
        Label nomeLabel = new Label("Nome: ");
        Label sobrenomeLabel = new Label("Sobrenome: ");
        nomeLabel.getStyleClass().add("page-title");
        sobrenomeLabel.getStyleClass().add("page-title");
        GridPane.setHalignment(nomeLabel, HPos.RIGHT);
        GridPane.setHalignment(sobrenomeLabel, HPos.RIGHT);
        
        //textfields
        TextField nomeField = new TextField();
        TextField sobrenomeField = new TextField();
        nomeField.getStyleClass().add("text-field");
        sobrenomeField.getStyleClass().add("text-field");
        GridPane.setHalignment(nomeField, HPos.LEFT);
        GridPane.setHalignment(sobrenomeField, HPos.LEFT);
        //botão enviar
        Button searchButton = new Button("+");
        searchButton.getStyleClass().add("options-button");
        searchButton.setPrefHeight(55);
        searchButton.setPrefWidth(55);
        searchButton.setOnAction(e -> {
            String nome = nomeField.getText();
            String sobrenome = sobrenomeField.getText();
            banco.adicionarCliente(nome, sobrenome);
            OptionWindow optionWindow = new OptionWindow();
            optionWindow.display("Cliente Adicionado com sucesso.\nDeseja adicionar contas a esse cliente?", "Sucesso");
            boolean option = optionWindow.getOption();
            if(option){
                int pos = banco.getNumeroDeClientes() - 1;
                openAddContaPage(pos);
            }
        });
        GridPane.setHalignment(searchButton, HPos.CENTER);
        
        //grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(40);
        grid.setHgap(30);
        //grid.setGridLinesVisible(true);
        
        grid.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        ColumnConstraints col5 = new ColumnConstraints();
        ColumnConstraints col6 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        
        col1.setPercentWidth(25);
        col2.setPercentWidth(16);
        col3.setPercentWidth(17);
        col4.setPercentWidth(9);
        col5.setPercentWidth(8);
        col6.setPercentWidth(25);
        row1.setPercentHeight(12);
        row2.setPercentHeight(20);
        row3.setPercentHeight(15);
        row4.setPercentHeight(15);
        row5.setPercentHeight(15);
        row6.setPercentHeight(20);
        
        grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
        grid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6);
        
        grid.add(titleBar, 0, 0, 6, 1); //colindex, rowindex, colspan, rowspan
        grid.add(contentBar, 1, 1, 4, 5);
        grid.add(searchButton, 2, 4);
        grid.add(nomeLabel, 1, 2);
        grid.add(sobrenomeLabel, 1, 3);
        grid.add(nomeField, 2, 2, 2, 1);
        grid.add(sobrenomeField, 2, 3, 2, 1);
        
        GridPane.setHalignment(contentBar, HPos.CENTER);
        
        
        template.setCenter(grid);
        window.setScene(addClientePage);
        window.show();
    }
    public void openAddContaPage(int indice){
        
        cliente = banco.getCliente(indice);
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        addContaPage = new Scene(template, 1300, 700);
        addContaPage.getStylesheets().add("windows/stylesheet.css");
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45); 
        titleBar.setAlignment(Pos.CENTER);
        Label pageTitle = new Label("Adicionar contas ao cliente");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        //conteudo
        HBox contentBar = new HBox();
        contentBar.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        contentBar.getStyleClass().add("content-bar");
        contentBar.setPrefHeight(400);
        
        //labels
        Label poupancaLabel = new Label("Conta Poupança");
        Label valorPoupancaLabel = new Label("Valor: ");
        Label taxaPoupancaLabel = new Label("Taxa: ");
        poupancaLabel.getStyleClass().add("page-title");
        valorPoupancaLabel.getStyleClass().add("page-title");
        taxaPoupancaLabel.getStyleClass().add("page-title");
        GridPane.setHalignment(poupancaLabel, HPos.CENTER);
        GridPane.setHalignment(valorPoupancaLabel, HPos.RIGHT);
        GridPane.setHalignment(taxaPoupancaLabel, HPos.RIGHT);
        
        Label correnteLabel = new Label("Conta Corrente sem proteção");
        Label valorCorrenteLabel = new Label("Valor: ");
        correnteLabel.getStyleClass().add("page-title");
        valorCorrenteLabel.getStyleClass().add("page-title");
        GridPane.setHalignment(correnteLabel, HPos.CENTER);
        GridPane.setHalignment(valorCorrenteLabel, HPos.RIGHT);
        
        Label correnteProtLabel = new Label("Conta Corrente com Proteção");
        correnteProtLabel.getStyleClass().add("page-title");
        Label valorCorrenteProtLabel = new Label("Valor Corrente: ");
        Label valorPoupancaProtLabel = new Label("Valor Poupança: ");
        Label taxaProtLabel = new Label("Taxa Poupança: ");
        valorCorrenteProtLabel.getStyleClass().add("page-title");
        valorPoupancaProtLabel.getStyleClass().add("page-title");
        taxaProtLabel.getStyleClass().add("page-title");
        GridPane.setHalignment(valorCorrenteProtLabel, HPos.RIGHT);
        GridPane.setHalignment(valorPoupancaProtLabel, HPos.RIGHT);
        GridPane.setHalignment(taxaProtLabel, HPos.RIGHT);
        
        //textfields
        TextField valorPoupancaField = new TextField();
        TextField poupancaTaxaField = new TextField();
        valorPoupancaField.getStyleClass().add("text-field");
        poupancaTaxaField.getStyleClass().add("text-field");

        TextField valorCorrenteField = new TextField();
        valorCorrenteField.getStyleClass().add("text-field");
        
        TextField valorProtCorrenteField = new TextField();
        TextField valorProtPoupancaField = new TextField();
        TextField taxaProtPoupancaField = new TextField();
        valorProtCorrenteField.getStyleClass().add("text-field");
        
        //botão enviar
        Button sendPoupanca = new Button("+");
        sendPoupanca.getStyleClass().add("options-button");
        sendPoupanca.setPrefHeight(55);
        sendPoupanca.setPrefWidth(55);
        sendPoupanca.setOnAction(e -> {
            
            Double valor, taxa;
            String valorString = valorPoupancaField.getText();
            String taxaString = poupancaTaxaField.getText();
            if(valorString.isEmpty()){
                valor = 0.0;
            }
            else {
                valor = Double.parseDouble(valorString);
            }
            if(taxaString.isEmpty()){
                taxa = 0.0;
            }
            else {
                taxa = Double.parseDouble(taxaString);
            }
            
            cliente.setConta(new ContaPoupanca(valor, taxa));
            AlertWindow alertPoupanca = new AlertWindow();
            alertPoupanca.display("Conta poupança adicionada com sucesso.", "Sucesso");
        });
        GridPane.setHalignment(sendPoupanca, HPos.CENTER);
        
        Button sendCorrente = new Button("+");
        sendCorrente.getStyleClass().add("options-button");
        sendCorrente.setPrefHeight(55);
        sendCorrente.setPrefWidth(55);
        sendCorrente.setOnAction(e -> {
            Double valor;
            String valorString = valorCorrenteField.getText();
            if(valorString.isEmpty()){
                valor = 0.0;
            }
            else {
                valor = Double.parseDouble(valorString);
            }

            cliente.setConta(new ContaCorrente(valor));
            AlertWindow alertCorrente = new AlertWindow();
            alertCorrente.display("Conta poupança sem proteção adicionada com sucesso.", "Sucesso");
        });
        GridPane.setHalignment(sendCorrente, HPos.CENTER);
        
        Button sendCorrenteProt = new Button("+");
        sendCorrenteProt.getStyleClass().add("options-button");
        sendCorrenteProt.setPrefHeight(55);
        sendCorrenteProt.setPrefWidth(55);
        sendCorrenteProt.setOnAction(e -> {
            Double valorCorrente, valorPoupanca, taxa;
            String valorCorrenteString = valorProtCorrenteField.getText();
            String valorPoupancaString = valorProtCorrenteField.getText();
            String taxaString = taxaProtPoupancaField.getText();
            if(valorCorrenteString.isEmpty()){
                valorCorrente = 0.0;
            }
            else {
                valorCorrente = Double.parseDouble(valorCorrenteString);
            }
            if(valorPoupancaString.isEmpty()){
                valorPoupanca = 0.0;
            }
            else {
                valorPoupanca = Double.parseDouble(valorPoupancaString);
            }
            if(taxaString.isEmpty()){
                taxa = 0.0;
            }
            else {
                taxa = Double.parseDouble(taxaString);
            }
            
            cliente.setConta(new ContaPoupanca(valorPoupanca, taxa));
            cliente.setConta(new ContaCorrente(valorCorrente, valorPoupanca));
            
            AlertWindow alertCorrenteProt = new AlertWindow();
            alertCorrenteProt.display("Conta poupança com proteção adicionada com sucesso.", "Sucesso");
        });
        GridPane.setHalignment(sendCorrenteProt, HPos.CENTER);
        
        //grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(40);
        grid.setHgap(30);
        //grid.setGridLinesVisible(true);
        
        grid.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        ColumnConstraints col5 = new ColumnConstraints();
        ColumnConstraints col6 = new ColumnConstraints();
        ColumnConstraints col7 = new ColumnConstraints();
        ColumnConstraints col8 = new ColumnConstraints();
        ColumnConstraints col9 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        
        col1.setPercentWidth(7);
        col2.setPercentWidth(22);
        col3.setPercentWidth(1);
        col4.setPercentWidth(7);
        col5.setPercentWidth(22);
        col6.setPercentWidth(1);
        col7.setPercentWidth(16);
        col8.setPercentWidth(22);
        col9.setPercentWidth(1);
        row1.setPercentHeight(12);
        row2.setPercentHeight(20);
        row3.setPercentHeight(15);
        row4.setPercentHeight(15);
        row5.setPercentHeight(15);
        row6.setPercentHeight(20);
        
        grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);
        grid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6);
        
        grid.add(titleBar, 0, 0, 9, 1); //colindex, rowindex, colspan, rowspan
        grid.add(contentBar, 0, 1, 9, 5);
        
        grid.add(poupancaLabel, 0, 1, 2, 1);
        grid.add(valorPoupancaLabel, 0, 2);
        grid.add(valorPoupancaField, 1, 2);
        grid.add(taxaPoupancaLabel, 0, 3);
        grid.add(poupancaTaxaField, 1, 3);
        grid.add(sendPoupanca, 0, 4, 2, 1);
        
        grid.add(correnteLabel, 3, 1, 2, 1);
        grid.add(valorCorrenteLabel, 3, 2);
        grid.add(valorCorrenteField, 4, 2);
        grid.add(sendCorrente, 3, 3, 2, 1);
        
        grid.add(correnteProtLabel, 6, 1, 2, 1); //colindex, rowindex, colspan, rowspan
        grid.add(valorCorrenteProtLabel, 6, 2);
        grid.add(valorProtCorrenteField, 7, 2);
        grid.add(valorPoupancaProtLabel, 6, 3);
        grid.add(valorProtPoupancaField, 7, 3);
        grid.add(taxaProtLabel, 6, 4);
        grid.add(taxaProtPoupancaField, 7, 4);
        grid.add(sendCorrenteProt, 6, 5, 2, 1);

        GridPane.setHalignment(contentBar, HPos.CENTER);
        
        
        template.setCenter(grid);
        window.setScene(addContaPage);
        window.show();
    }
    public void openSearchClientePage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        searchClientePage = new Scene(template, 1300, 700);
        searchClientePage.getStylesheets().add("windows/stylesheet.css");
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45); 
        titleBar.setAlignment(Pos.CENTER);
        Label pageTitle = new Label("Adicionar Conta a um cliente já existente");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        //conteudo
        HBox contentBar = new HBox();
        contentBar.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        contentBar.getStyleClass().add("content-bar");
        contentBar.setPrefHeight(400);
        
        //labels
        Label nomeLabel = new Label("Nome: ");
        Label sobrenomeLabel = new Label("Sobrenome: ");
        nomeLabel.getStyleClass().add("page-title");
        sobrenomeLabel.getStyleClass().add("page-title");
        GridPane.setHalignment(nomeLabel, HPos.RIGHT);
        GridPane.setHalignment(sobrenomeLabel, HPos.RIGHT);
        
        //textfields
        TextField nomeField = new TextField();
        TextField sobrenomeField = new TextField();
        nomeField.getStyleClass().add("text-field");
        sobrenomeField.getStyleClass().add("text-field");
        GridPane.setHalignment(nomeField, HPos.LEFT);
        GridPane.setHalignment(sobrenomeField, HPos.LEFT);
        //botão enviar
        Button searchButton = new Button("Pesquisar");
        searchButton.getStyleClass().add("options-button");
        searchButton.setPrefHeight(50);
        searchButton.setPrefWidth(150);
        searchButton.setOnAction(e -> {
            String nome = nomeField.getText();
            String sobrenome = sobrenomeField.getText();
            
            int index = banco.getClienteIndex(nome, sobrenome);
            if(index < 0){
                AlertWindow alertWindow = new AlertWindow();
                alertWindow.display("Não existe um cliente cadastrado com esses dados.", "Erro");
            }
            else {
                openAddContaPage(index);
            }
        });
        GridPane.setHalignment(searchButton, HPos.CENTER);
        
        //grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(40);
        grid.setHgap(30);
        //grid.setGridLinesVisible(true);
        
        grid.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        ColumnConstraints col5 = new ColumnConstraints();
        ColumnConstraints col6 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        
        col1.setPercentWidth(25);
        col2.setPercentWidth(16);
        col3.setPercentWidth(17);
        col4.setPercentWidth(9);
        col5.setPercentWidth(8);
        col6.setPercentWidth(25);
        row1.setPercentHeight(12);
        row2.setPercentHeight(20);
        row3.setPercentHeight(15);
        row4.setPercentHeight(15);
        row5.setPercentHeight(15);
        row6.setPercentHeight(20);
        
        grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
        grid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6);
        
        grid.add(titleBar, 0, 0, 6, 1); //colindex, rowindex, colspan, rowspan
        grid.add(contentBar, 1, 1, 4, 5);
        grid.add(searchButton, 2, 4);
        grid.add(nomeLabel, 1, 2);
        grid.add(sobrenomeLabel, 1, 3);
        grid.add(nomeField, 2, 2, 2, 1);
        grid.add(sobrenomeField, 2, 3, 2, 1);
        
        GridPane.setHalignment(contentBar, HPos.CENTER);
        
        
        template.setCenter(grid);
        window.setScene(searchClientePage);
        window.show();
    }
    public void openSearchOperacoesPage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        searchOperacoesPage = new Scene(template, 1300, 700);
        searchOperacoesPage.getStylesheets().add("windows/stylesheet.css");
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45); 
        titleBar.setAlignment(Pos.CENTER);
        
        Label pageTitle = new Label("Realizar operações bancárias");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        //conteudo
        HBox contentBar = new HBox();
        contentBar.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        contentBar.getStyleClass().add("content-bar");
        contentBar.setPrefHeight(400);
        GridPane.setHalignment(contentBar, HPos.CENTER);
        
        //labels
        Label credenciaisLabel = new Label("Credenciais do cliente");
        Label contaLabel = new Label("Número da conta - 0 a 2");
        Label nomeLabel = new Label("Nome: ");
        Label sobrenomeLabel = new Label("Sobrenome: ");
        Label numeroLabel = new Label("Número: ");
        
        credenciaisLabel.getStyleClass().add("page-title");
        contaLabel.getStyleClass().add("page-title");
        nomeLabel.getStyleClass().add("page-title");
        sobrenomeLabel.getStyleClass().add("page-title");
        numeroLabel.getStyleClass().add("page-title");
        
        GridPane.setHalignment(credenciaisLabel, HPos.CENTER);
        GridPane.setHalignment(contaLabel, HPos.CENTER);
        GridPane.setHalignment(nomeLabel, HPos.RIGHT);
        GridPane.setHalignment(sobrenomeLabel, HPos.RIGHT);
        GridPane.setHalignment(numeroLabel, HPos.RIGHT);
        
        //textfields
        TextField nomeField = new TextField();
        TextField sobrenomeField = new TextField();
        TextField numeroField = new TextField();
        nomeField.getStyleClass().add("text-field");
        sobrenomeField.getStyleClass().add("text-field");
        numeroField.getStyleClass().add("text-field");

        //botão enviar
        Button searchButton = new Button("Pesquisar");
        searchButton.getStyleClass().add("options-button");
        searchButton.setPrefHeight(50);
        searchButton.setPrefWidth(150);
        searchButton.setOnAction(e -> {
            String nomeString = nomeField.getText();
            String sobrenomeString = sobrenomeField.getText();
            String numeroString = numeroField.getText();
            int numero = Integer.parseInt(numeroString);
            
            int index = banco.getClienteIndex(nomeString, sobrenomeString);
            if(index < 0){
                AlertWindow alertWindow = new AlertWindow();
                alertWindow.display("Não existe um cliente cadastrado com esses dados.", "Erro");
            }
            else {
                cliente = banco.getCliente(index);
                int indexContas = cliente.getNumeroDeContas() - 1;
                if(numero > indexContas || numero < 0){
                    AlertWindow alertWindow = new AlertWindow();
                    alertWindow.display("O cliente não tem uma conta com o número fornecido", "Erro");
                }
                else {
                    openHomePage();
                }
            }
        });
        GridPane.setHalignment(searchButton, HPos.CENTER);
        
        //grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(20);
        grid.setHgap(30);
        //grid.setGridLinesVisible(true);
        
        grid.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        ColumnConstraints col5 = new ColumnConstraints();
        ColumnConstraints col6 = new ColumnConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        RowConstraints row6 = new RowConstraints();
        RowConstraints row7 = new RowConstraints();
        
        col1.setPercentWidth(25);
        col2.setPercentWidth(16);
        col3.setPercentWidth(17);
        col4.setPercentWidth(9);
        col5.setPercentWidth(8);
        col6.setPercentWidth(25);
        row1.setPercentHeight(12);
        row2.setPercentHeight(14.6);
        row3.setPercentHeight(14.6);
        row4.setPercentHeight(14.6);
        row5.setPercentHeight(14.6);
        row6.setPercentHeight(14.6);
        row7.setPercentHeight(14.6);
        
        grid.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
        grid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7);
        
        grid.add(titleBar, 0, 0, 6, 1); //colindex, rowindex, colspan, rowspan
        grid.add(contentBar, 1, 1, 4, 6);
        grid.add(credenciaisLabel, 1, 1, 4, 1);
        grid.add(nomeLabel, 1, 2);
        grid.add(nomeField, 2, 2, 2, 1);
        grid.add(sobrenomeLabel, 1, 3);
        grid.add(sobrenomeField, 2, 3, 2, 1);
        grid.add(contaLabel, 1, 4, 4, 1);
        grid.add(numeroLabel, 1, 5);
        grid.add(numeroField, 2, 5, 2, 1);
        grid.add(searchButton, 2, 6);
        
        template.setCenter(grid);
        window.setScene(searchOperacoesPage);
        window.show();
    }
}
