package com.andyr.impatient.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.BiFunction;

public class C3E6 extends Application {

    public static <T> Image transform(Image in, BiFunction<Color,T,Color> f, T arg ) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y,
                        f.apply(in.getPixelReader().getColor(x,y),arg));
            }
        }
        return out;
    }
    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("corgi.png");
        Image newImage = transform(image,(c,a) -> c.deriveColor(0.5,0.5,a,0.5),2.0);
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(newImage))));
        stage.show();
    }
}
