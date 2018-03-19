package zoomAndDrag;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Piotr on 01.03.2018.
 */
public class NodesOnMap {

    public static Group generateNodesOnMap(Object[][] nodes, Object[][] connections) {

        int i = 0;
        double width;
        double height;
        long integer;
        double decimal;
        Double[][] data = new Double[nodes.length][3];
        double random;
        Group group = new Group();
        Group groupNodes = new Group();
        Group groupConnections = new Group();

        while(nodes.length > i) {
            width = ((double)nodes[i][4]);
            integer = (long)width;
            decimal = (width - integer)*100/60;
            width = integer + decimal;
            width = (width - 14.119) * 66.0 + 15;
            width = (int)Math.round(width * 100)/(double)100;
            random = ThreadLocalRandom.current().nextDouble(-2, 2 + 1);
            width += random;

            height = (double)nodes[i][5];
            integer = (long)height;
            decimal = (height - integer)*100/60;
            height = integer + decimal;
            height = 608 - ((height - 49.18) * 104.04);
            height = (int)Math.round(height * 100)/(double)100;
            random = ThreadLocalRandom.current().nextDouble(-2, 2 + 1);
            height += random;
            
            data[i][0] = (double)((Integer)nodes[i][0]);
            data[i][1] = width;
            data[i][2] = height;

            Circle circle = new Circle(width, height, 1);

            circle.setId(Integer.toString(data[i][0].intValue()));
            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                circle.setFill(Color.RED);
                System.out.println(circle.getId());
            });

            groupNodes.getChildren().addAll(circle);
            i++;
        }

//        Arrays.sort(data, new Comparator<double[]>() {
//            @Override
//            public int compare(double[] o1, double[] o2) {
//                Double nodeId1 = o1[0];
//                Double nodeId2 = o2[0];
//                return nodeId1.compareTo(nodeId2);
//            }
//        });

        Arrays.sort(data, (Double[] o1, Double[] o2) -> o1[0].compareTo(o2[0]));

        //Some comparators like (person1, person2) -> person1.getName().compareTo(person2.getName())
        // could be simplified like this: Comparator.comparing(Person::getName)
        i = 0;

        while(connections.length > i){
            Line connectionLine = new Line(data[((int)connections[i][0])-1][1],data[((int)connections[i][0])-1][2],
                    data[((int)connections[i][1])-1][1],data[((int)connections[i][1])-1][2]);
            connectionLine.setStroke(Color.LIGHTGRAY);
            connectionLine.setId(Integer.toString((int)connections[i][0]) + "-" + Integer.toString((int)connections[i][1]));
            groupConnections.getChildren().addAll(connectionLine);
            i++;
        }

        group.getChildren().addAll(groupConnections, groupNodes);

        return group;
    }
}
