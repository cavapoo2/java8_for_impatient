package com.andyr.impatient.ch3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class C3E12 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Image image = new Image("corgi.png");
        Image newImage = LatentImage.from(image).transform(Color::brighter).transform(Color::grayscale)
                .transform(colorBorder(image, 10, 10, Color.CORNSILK))
                .toImage();
        stage.setScene(new Scene(new HBox(new ImageView(image), new ImageView(newImage))));
        stage.show();
    }

    public static ColorTransformer colorBorder(Image in, int ax, int ay, Color ac) {
        return (x, y, c) -> (
                (x <= ax) || x >= (in.getWidth() - ax)
                        || (y <= ay) || y >= (in.getHeight() - ay)) ? ac : c;

    }
}
class LatentImage {
    private Image in;
    private List<ColorTransformer> pendingOperations;

    public static LatentImage from(Image in) {
        LatentImage result = new LatentImage();
        result.in = in;
        result.pendingOperations = new ArrayList<>();
        return result;
    }
    LatentImage transform(UnaryOperator<Color> f) {
        pendingOperations.add(convert(f));
        return this;
    }
    LatentImage transform(ColorTransformer f) {
        pendingOperations.add(f);
        return this;
    }

    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Color c = in.getPixelReader().getColor(x, y);
                for (ColorTransformer f : pendingOperations) c = f.apply(x, y, c);
                out.getPixelWriter().setColor(x, y, c);
            }
        return out;
    }

    private  ColorTransformer convert(UnaryOperator<Color> op) {
        return (x,y,c) -> op.apply(c);
    }

}
