package mainGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BufferedImageLoader {
    private BufferedImage image;
    public BufferedImage loadImage(String path)
    {
        try {
            image = ImageIO.read(getClass().getResource(path));
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Image Not Found!");
        }
        return image;
    }
}
