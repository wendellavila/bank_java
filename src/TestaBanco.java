//=========================================================================================//
//                      Projeto Banco com Interface gráfica JavaFX                         //
//                                                                                         //
//  Autor: Wendell João Castro de Ávila                                                    //
//  RA: 2017.1.08.013                                                                      //
//  Data: 28/07/2018                                                                       //
//                                                                                         //
//=========================================================================================//

import windows.MainWindow;

import javafx.application.Application;
import javafx.stage.Stage;


public class TestaBanco extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    //=================================== javafx ==========================================//

    @Override
    public void start(Stage stage) throws Exception {
        MainWindow mainWindow = new MainWindow(stage);
        mainWindow.display();
    }
}