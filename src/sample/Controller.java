package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public WebView myWebView;


    public void onClick(ActionEvent actionEvent) {
        actionEvent.getEventType();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dimension.width;
        int y = dimension.height;
        String rez = String.format("X=%d, Y=%d", x, y);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Scrin");
        alert.setHeaderText("FFF");
        alert.setContentText(rez);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println(read());

            WebEngine webEngine = myWebView.getEngine();
            webEngine.loadContent(read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    public static String read() throws FileNotFoundException {
        File file = new File("src/sample/web/index.html");

        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();


        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        //Возвращаем полученный текст с файла
        return sb.toString();
    }

}
