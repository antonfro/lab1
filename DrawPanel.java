import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    final Map<Integer, BufferedImage> images = new HashMap<>();
    final Map<Integer, Point> carPositions = new ConcurrentHashMap<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    void moveit(Integer regId, int x, int y) {
        Point carPosition = carPositions.get(regId);
        if (carPosition != null){
            carPosition.setLocation(x, y);
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.magenta);
        try {
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void addCarImage(int id, String carType, int x, int y) {
        try {
            BufferedImage image = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/" + carType + ".jpg"));
            images.put(id, image);
            carPositions.put(id, new Point(x, y));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Map.Entry<Integer, BufferedImage> entry : images.entrySet()) {
            Integer regId = entry.getKey();
            BufferedImage image = entry.getValue();
            Point position = carPositions.get(regId);
            if (position != null) {
                g.drawImage(image, position.x, position.y, null);
            } else {
                System.out.println("NoPositionFoundFor" + regId);// see javadoc for more info on the parameters
            }
            g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        }
    }
}
