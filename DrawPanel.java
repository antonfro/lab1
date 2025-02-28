import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    final Map<String, BufferedImage> images = new HashMap<>();
    final Map<String, Point> carPositions = new HashMap<>();

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    // To keep track of a single car's position


    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
    void moveit(String carName, int x, int y){
        Point carPosition = carPositions.get(carName);
        if (carPosition != null){
            carPosition.setLocation(x, y);
        }
    }

    private void loadCarImage(String carName, String filepath, Point startingPosition) throws IOException {
        BufferedImage image = ImageIO.read(DrawPanel.class.getResourceAsStream(filepath));
        images.put(carName, image);
        carPositions.put(carName, new Point(startingPosition.x, startingPosition.y));
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and-
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            loadCarImage("Volvo240","pics/Volvo240.jpg", new Point(0,0));
            loadCarImage("Saab95","pics/Saab95.jpg", new Point(0,100));
            loadCarImage("Scania","pics/Scania.jpg", new Point(0,200));

            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Map.Entry<String, BufferedImage> entry : images.entrySet()) {
            String carName = entry.getKey();
            BufferedImage image = entry.getValue();
            Point position = carPositions.get(carName);
            if (position != null) {
                System.out.println("Moving " + carName + " to (" + position.x + ", " + position.y + ")");
                g.drawImage(image, position.x, position.y, null);
            } else {
                System.out.println("NoPositionFoundFor" + carName);// see javadoc for more info on the parameters
            }
            g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        }
    }
}
