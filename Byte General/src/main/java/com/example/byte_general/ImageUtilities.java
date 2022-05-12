package com.example.byte_general;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ImageUtilities {
    public static ImageView clipImage(ImageView image) {
        double ratio = image.getImage().getWidth() / image.getImage().getHeight();
        Rectangle clip = new Rectangle(image.getFitWidth() , image.getFitWidth() / ratio - 20);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        image.setClip(clip);
        System.out.println(image.getImage().getWidth());
        System.out.println(image.getImage().getHeight());
        return image;
    }

    public static ImageView clipCircleImage(ImageView image) {
        image.setImage(cropImage(image, (int) image.getImage().getWidth()));
        double ratio = image.getImage().getWidth() / image.getImage().getHeight();
        Circle clip = new Circle(ratio * image.getFitHeight() / 2.0, image.getFitHeight() / 2.0, Math.min(image.getFitHeight() * ratio, image.getFitHeight()) / 2.0);
        image.setClip(clip);

        return image;
    }

    public static WritableImage cropImage(ImageView img, int m_value) {
        int width = (int) img.getImage().getWidth();
        int height = (int) img.getImage().getHeight();
        PixelReader reader = img.getImage().getPixelReader();
        WritableImage croppedImage = null;
        if (width > height) {
            int len = Math.min(m_value, height);
            croppedImage = new WritableImage(reader, (width - len) / 2, (height - len) / 2, len, len);
        } else {
            int len = Math.min(width, m_value);
            croppedImage = new WritableImage(reader, (width - len) / 2, (height - len) / 2, len, len);
        }
        return croppedImage;
    }


}
