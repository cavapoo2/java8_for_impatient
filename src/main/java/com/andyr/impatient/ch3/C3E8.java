package com.andyr.impatient.ch3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Collection;

public class C3E8  extends Application {
    public static Image transform(Image in, ColorTransformer t) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y,
                        t.apply(x, y, in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }
    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("corgi.png");
        Image newImage = transform(image,colorTrans(image,10,10,Color.BURLYWOOD));
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(newImage))));
        stage.show();
    }

    public static ColorTransformer colorTrans(Image in, int ax, int ay, Color ac) {
        return (x,y,c) -> (
                (x <= ax )|| x >= (in.getWidth() - ax)
                || (y <= ay) || y >= (in.getHeight() - ay)) ? ac : c;

    }
}
