package com.andyr.impatient.ch3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.function.Function;
import java.util.function.UnaryOperator;
public class C3E11 extends Application {

    public static Image transform(Image in, ColorTransformer f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(
                width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                out.getPixelWriter().setColor(x, y,
                        f.apply(x,y,in.getPixelReader().getColor(x, y)));
        return out;
    }

    public static ColorTransformer compose(ColorTransformer f, ColorTransformer s) {
        return (x,y,c) -> s.apply(x,y,f.apply(x,y,c));
    }
    public static ColorTransformer convert(UnaryOperator<Color> op) {
        return (x,y,c) -> op.apply(c);
    }
    public static ColorTransformer colorBorder(Image in, int ax, int ay, Color ac) {
        return (x,y,c) -> (
                (x <= ax )|| x >= (in.getWidth() - ax)
                || (y <= ay) || y >= (in.getHeight() - ay)) ? ac : c;

    }
    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("corgi.png");
        UnaryOperator<Color> op = Color::brighter;
        Image newImage = transform(image, compose(convert(Color::grayscale),colorBorder(image,10,10,Color.CORAL)));
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(newImage))));
        stage.show();
    }
}
