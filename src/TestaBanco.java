/*
* Esta classe cria um programa para testar as classes do projeto banco.
* Ela cria um conjunto de clientes, com algumas contas,
* e gera um relatoorio do saldo atual em conta.
*/

import banco.dominio.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class TestaBanco extends Application {
    
    Scene homePage, sobrePage;
    Scene auxPage = new Scene(new BorderPane(), 1300, 700);
    Stage window;

    public static void main(String[] args) {
        launch(args);
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
          System.out.println("Conta Corrente [Jane Simms] : dep�sito de R$ 22,50");
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
    }

    @Override
    public void start(Stage stage) throws Exception {
    
        window = stage;
        openHomePage();
        
        //==========================window================================//
        window.setTitle("JavaBank");
        window.show();
    }
    
    public BorderPane addNavbarAndFooter(BorderPane template){
        
        //================navbar e footer======================//
        
        //==navbar-buttons==//
        Button homebutton = new Button("Home"), sobrebutton = new Button("Sobre");
        homebutton.getStyleClass().add("navbar-buttons");
        sobrebutton.getStyleClass().add("navbar-buttons");
        
        homebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                //remover cena atual que ja esta usando o template template
                if(window.getScene() != null){
                    window.setScene(auxPage);
                }
                openHomePage();
            }
        });
        sobrebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                //remover cena atual que ja esta usando o template template
                if(window.getScene() != null){
                    window.setScene(auxPage);
                }
                System.out.println("teste");
                openSobrePage();
            }
        });
        
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
        
        homePage = new Scene(template, 1300, 700);
        homePage.getStylesheets().add("stylesheet.css");
        
        GridPane grid = new GridPane();
        HBox titleBox = new HBox();
        grid.setPadding(new Insets(50, 50, 80, 80)); //cima, baixo, dir, esq
        grid.setVgap(8);
        grid.setHgap(10);
        
        Label sceneTitle = new Label("Home");
        GridPane.setConstraints(sceneTitle, 0, 0);
        
        grid.getChildren().add(sceneTitle);
        
        template.setCenter(grid);
        
        window.setScene(homePage);
        window.show();
    }
    public void openSobrePage(){
        
        BorderPane template = new BorderPane();
        template = addNavbarAndFooter(template);
        
        sobrePage = new Scene(template, 1300, 700);
        sobrePage.getStylesheets().add("stylesheet.css");
        
        GridPane grid = new GridPane();
        HBox titleBox = new HBox();
        grid.setPadding(new Insets(50, 50, 80, 80)); //cima, baixo, dir, esq
        grid.setVgap(8);
        grid.setHgap(10);
        
        Label sceneTitle = new Label("Sobre");
        GridPane.setConstraints(sceneTitle, 5, 5);
        
        grid.getChildren().add(sceneTitle);
        
        template.setCenter(grid);
        
        window.setScene(sobrePage);
        window.show();
    }
}