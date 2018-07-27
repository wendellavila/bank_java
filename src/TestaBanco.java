/*
* Esta classe cria um programa para testar as classes do projeto banco.
* Ela cria um conjunto de clientes, com algumas contas,
* e gera um relatorio do saldo atual em conta.
*/

import banco.dominio.*;
import banco.relatorios.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TestaBanco extends Application {
    
    Scene homePage, sobrePage, relatorioPage, adicionarPage;
    Stage window;

    public static void main(String[] args) {
        
        Banco banco = Banco.getBanco();
        Cliente cliente;
        Conta conta;

        // Cria dois clientes e suas contas
        banco.adicionarCliente("Jane", "Simms");
        cliente = banco.getCliente(0);
        cliente.setConta(new ContaPoupanca(300.00, 0.05));
        cliente.setConta(new ContaCorrente(200.00, 300.00));
        banco.adicionarCliente("Owen", "Bryant");
        cliente = banco.getCliente(1);
        cliente.setConta(new ContaCorrente(200.00));

        // Testa  a conta corrente de Jane Simms (com cheque especial)
        cliente = banco.getCliente(0);
        conta = cliente.getConta(1);
        System.out.println("Cliente [" + cliente.getUltimoNome()
                           + ", " + cliente.getPrimeiroNome() + "]"
                           + " Tem um saldo em conta corrente de "
                           + conta.getSaldo()
                             + " Com cheque especial de  R$ 300.00.");
        try {
          System.out.println("Conta Corrente [Jane Simms] : Saque de R$ 150,00");
          conta.sacar(150.00);
          System.out.println("Conta Corrente [Jane Simms] : deposito de R$ 22,50");
          conta.depositar(22.50);
          System.out.println("Conta Corrente [Jane Simms] : Saque de R$ 147,62");
          conta.sacar(147.62);
          System.out.println("Conta Corrente [Jane Simms] : Saque de R$ 470,00");
          conta.sacar(470.00);
        } catch (ExcecaoChequeEspecial e1) {
          System.out.println("Excecao: " + e1.getMessage()
                             + "   Deficit: " + e1.getDeficit());
        } finally {
          System.out.println("Cliente [" + cliente.getUltimoNome()
                             + ", " + cliente.getPrimeiroNome() + "]"
                             + " Tem um saldo em conta corrente de "
                             + conta.getSaldo());
        }
        System.out.println();

        // Testa a conta corrente de Owen Bryant (sem cheque especial)
        cliente = banco.getCliente(1);
        conta = cliente.getConta(0);
        System.out.println("Cliente [" + cliente.getUltimoNome()
                           + ", " + cliente.getPrimeiroNome() + "]"
                           + " tem um saldo de "
                           + conta.getSaldo());
        try {
          System.out.println("Conta Corrente [Owen Bryant] : Saque de R$100.00");
          conta.sacar(100.00);
          System.out.println("Conta Corrente [Owen Bryant] : deposito de R$25.00");
          conta.depositar(25.00);
          System.out.println("Conta Corrente [Owen Bryant] : Saque de R$175.00");
          conta.sacar(175.00);
        } catch (ExcecaoChequeEspecial e1) {
          System.out.println("Excecao: " + e1.getMessage()
                             + "   Deficit: " + e1.getDeficit());
        } finally {
          System.out.println("Cliente [" + cliente.getUltimoNome()
                             + ", " + cliente.getPrimeiroNome() + "]"
                             + " Tem um saldo dem conta corrente de "
                             + conta.getSaldo());
        }
        launch(args);
    }
    
    //=================================== javafx ==========================================//

    @Override
    public void start(Stage stage) throws Exception {
    
        window = stage;
        openHomePage();
        
        //========================= window ===============================//
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
        Image logo = new Image("javabank.png");
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
        sobrePage.getStylesheets().add("stylesheet.css");
        
        //titulo
        HBox titleBar = new HBox(10);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setPrefHeight(45);
        titleBar.setPrefWidth(2000);
        titleBar.setAlignment(Pos.CENTER);
        Label pageTitle = new Label("Sistema JavaBank de gerenciamento de clientes");
        pageTitle.getStyleClass().add("page-title");
        titleBar.getChildren().add(pageTitle);
        
        //botão esquerdo
        Button leftButton = new Button("Adicionar Cliente");
        leftButton.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        leftButton.getStyleClass().add("options-button");
        leftButton.setPrefHeight(400);
        leftButton.setPrefWidth(400);
        //leftButton.setOnAction(e -> openAdicionarPage());
        
        //botão direito
        Button rightButton = new Button("Gerar Relatório");
        rightButton.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        rightButton.getStyleClass().add("options-button");
        rightButton.setPrefHeight(400);
        rightButton.setPrefWidth(400);
        rightButton.setOnAction(e -> openRelatorioPage());
        
        //grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(50, 100, 50, 100)); //cima, esq, baixo, dir
        grid.setVgap(40);
        grid.setHgap(30);
        
        grid.setAlignment(Pos.CENTER);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col1,col2);
        
        grid.add(titleBar, 0, 0, 2, 1); //colindex, rowindex, colspan, rowspan
        grid.add(leftButton, 0, 1);
        grid.add(rightButton, 1, 1);
        
        GridPane.setHalignment(leftButton, HPos.CENTER);
        GridPane.setHalignment(rightButton, HPos.CENTER);
        
        
        template.setCenter(grid);
        window.setScene(sobrePage);
        window.show();
    }
    public void openSobrePage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        sobrePage = new Scene(template, 1300, 700);
        sobrePage.getStylesheets().add("stylesheet.css");
        
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
        HBox textBar = new HBox(10);
        textBar.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        textBar.getStyleClass().add("text-bar");
        textBar.setPrefHeight(400);
        textBar.setPrefWidth(3000);
        textBar.setAlignment(Pos.TOP_LEFT);
        
        Label infoTitle = new Label("Desenvolvido Por:          ");
        infoTitle.getStyleClass().add("page-title");
        Label infoText = new Label("Wendell J. C. Ávila\nRA: 2017.1.08.013\n\nDisciplina: Programação Orientada a Objetos\n"
                                    + "Universidade Federal de Alfenas\n\n27/07/2018");
        infoText.getStyleClass().add("page-text");
        textBar.getChildren().addAll(infoTitle, infoText);
        
        GridPane.setConstraints(titleBar, 0, 0);
        GridPane.setConstraints(textBar, 0, 8);
        
        grid.getChildren().addAll(titleBar, textBar);
        
        template.setCenter(grid);
        window.setScene(sobrePage);
        window.show();
    }
    
    public void openRelatorioPage(){
        
        RelatorioClientes relatorio = new RelatorioClientes();
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        relatorioPage = new Scene(template, 1300, 700);
        relatorioPage.getStylesheets().add("stylesheet.css");
        
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
        
        //caixa de texto
        HBox textBar = new HBox(10);
        textBar.setPadding(new Insets(30, 40, 30, 40)); //cima, esq, baixo, dir
        textBar.getStyleClass().add("text-bar");
        textBar.setPrefHeight(400);
        textBar.setPrefWidth(3000);
        textBar.setAlignment(Pos.TOP_LEFT);
        
        Label infoText = new Label(relatorio.relatorio);
        infoText.getStyleClass().add("page-text");
        textBar.getChildren().add(infoText);
        
        GridPane.setConstraints(titleBar, 0, 0);
        GridPane.setConstraints(textBar, 0, 8);
        
        grid.getChildren().addAll(titleBar, textBar);
        
        template.setCenter(grid);
        window.setScene(relatorioPage);
        window.show();
    }
}