
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ASCIIPicture {
    private final static int columns = 140;
    private int scale;
    private BufferedImage image;



    public ASCIIPicture(File file) throws IOException {
        image = ImageIO.read(file);
        if (image.getWidth() > columns) {
            scale = image.getWidth() / columns + 1;
        } else {
            scale = 1;
        }
    }

    private int getBlockIntensity(int x, int y) {
        int averageIntensity = 0;
        for (int i = x; i < scale + x; i++) {
            for (int j = y; j < scale + y; j++) {
                averageIntensity += getIntensity(i, j);
            }
        }
        return averageIntensity / (scale * scale);
    }

    private int getIntensity(int x, int y) {
        Color color = new Color(image.getRGB(x, y), false);
        int intensity = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return intensity;
    }

    private String pixelToChar(int intensity) {
        if (intensity >= 240) {
            return " ";
        } else if (intensity >= 200 && intensity < 240) {
            return ".";
        } else if (intensity >= 160 && intensity < 200) {
            return "~";
        } else if (intensity >= 120 && intensity < 160) {
            return "+";
        } else if (intensity >= 80 && intensity < 120) {
            return "=";
        } else if (intensity >= 40 && intensity < 80) {
            return "#";
        } else {
            return "@";
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < image.getHeight() - scale; y += scale) {
            for (int x = 0; x < image.getWidth() - scale; x += scale) {
                result += pixelToChar(getBlockIntensity(x, y));
            }
            result += "\n";
        }
        return result;
    }
    public static void main(String[] args){
        //happy_cat.jpg
		System.out.println(System.lineSeparator());
        try{
            ASCIIPicture viewer = new ASCIIPicture(new File(args[0]));
            System.out.println(viewer);
        }catch(IOException e){
            System.out.println("File not found");
        }
        
    }
}