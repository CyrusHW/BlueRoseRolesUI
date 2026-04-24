package blue.rose;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

import java.io.IOException;

public class App extends Application{
    private WebEngine engine;

    // This is all not working that is why I made the other main file

    public static void main(String[] args) throws IOException{
        launch(args);
    }

    @SuppressWarnings(value = {"removal"})
    @Override
    public void start(Stage stage){
        WebView view = new WebView();
        engine = view.getEngine();
        engine.load(getClass().getResource("/Index.html").toExternalForm());
        
        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
        if (newState == Worker.State.SUCCEEDED) {
            JSObject window = (JSObject) engine.executeScript("window");
            window.setMember("java", this);
            
            // Push constants once
            engine.executeScript("window.CONSTANTS = JSON.parse(java.getConstantsJSON());");
            
            // Trigger the JS init
            engine.executeScript("waitForConstants();");
            
            startLoop();
        }
    });

    }

    private void startLoop() {
        AnimationTimer timer = new AnimationTimer() {
            private long last = 0;

            @Override
            public void handle(long now) {
                if (now - last < 20000000) return;
                last = now;
            }
        };
        timer.start();
    }
    public String getConstantsJSON() {
    // Using a StringBuilder to build clean JSON
    StringBuilder json = new StringBuilder("{");
    json.append("\"windowWidth\":").append(Constants.windowWidth).append(",");
    json.append("\"windowHeight\":").append(Constants.windowHeight); 
    // Notice: No comma after the last item
    json.append("}");
    return json.toString();
}


    private String arrayToJSON(int[] phasetimeremaining) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < phasetimeremaining.length; i++) {
            sb.append(phasetimeremaining[i]);
            if (i < phasetimeremaining.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
