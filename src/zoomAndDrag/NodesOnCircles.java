package zoomAndDrag;

import java.util.concurrent.ThreadLocalRandom;

public class NodesOnCircles {
    final double x;
    final double y;

    public NodesOnCircles(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static NodesOnCircles generateRandomNodes(double startingX, double startingY, double radius){
        double x, y, angle, random;
        int randomSign = 0;

        angle = Math.random()*Math.PI;
        random = ThreadLocalRandom.current().nextDouble(-1, 1 + 1);
        while (randomSign == 0)
            randomSign = ThreadLocalRandom.current().nextInt(-1, 1 + 1);
        x = startingX + randomSign * (Math.cos(angle) * radius) + random;
        y = startingY + randomSign * (Math.sin(angle) * radius) + random;

        return new NodesOnCircles(x,y);
    }
}
