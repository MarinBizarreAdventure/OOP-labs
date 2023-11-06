package models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

public class Image extends Record{
    public Image(Path path){
        super(path);
    }

    @Override
    public String getInfo(){
        return super.getInfo() + "\nImage size:" + getImageSize();
    }

    public String getImageSize() {
        try {
            BufferedImage image = ImageIO.read(this);
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                return width + "x" + height;
            } else {
                return "Image dimensions not available";
            }
        } catch (Exception e) {
            return "Error reading image dimensions";
        }
    }
}
