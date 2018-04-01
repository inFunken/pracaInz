package zoomAndDrag;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class SceneGestures {

    private static final double MAX_SCALE = 9d;
    private static final double MIN_SCALE = 0.9d;

    private DragScene dragScene = new DragScene();

    PannableCanvas canvas;

    public SceneGestures( PannableCanvas canvas) {
        this.canvas = canvas;
    }

    public EventHandler<ScrollEvent> getOnScrollEventHandler() {
        return scrollEventHandler;
    }
    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
        return mouseDragEventHandler;
    }
    public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
        return mousePressEventHandler;
    }

    private EventHandler<MouseEvent> mousePressEventHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent event) {

            if( !event.isSecondaryButtonDown())
                return;

            dragScene.mouseAnchorX = event.getSceneX();
            dragScene.mouseAnchorY = event.getSceneY();

            dragScene.translateAnchorX = canvas.getTranslateX();
            dragScene.translateAnchorY = canvas.getTranslateY();
        }
    };

    private EventHandler<MouseEvent> mouseDragEventHandler = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent event) {
            if( !event.isSecondaryButtonDown())
                return;

            canvas.setTranslateX(dragScene.translateAnchorX + event.getSceneX() - dragScene.mouseAnchorX);
            canvas.setTranslateY(dragScene.translateAnchorY + event.getSceneY() - dragScene.mouseAnchorY);

            event.consume();
        }
    };

    private EventHandler<ScrollEvent> scrollEventHandler = new EventHandler<ScrollEvent>() {

        @Override
        public void handle(ScrollEvent event) {

            double delta = 1.2;
            double scale = canvas.getScale();
            double oldScale = scale;

            if (event.getDeltaY() < 0)
                scale /= delta;
            else
                scale *= delta;

            scale = clamp(scale, MIN_SCALE, MAX_SCALE);

            double f = (scale / oldScale)-1;
            double dx = (event.getSceneX() - (canvas.getBoundsInParent().getWidth()/2 + canvas.getBoundsInParent().getMinX()));
            double dy = (event.getSceneY() - (canvas.getBoundsInParent().getHeight()/2 + canvas.getBoundsInParent().getMinY()));

            canvas.setScale( scale);
            canvas.setPivot(f*dx, f*dy);

            event.consume();
        }
    };
    
    public static double clamp( double value, double min, double max) {

        if(Double.compare(value, min) < 0)
            return min;

        if(Double.compare(value, max) > 0)
            return max;

        return value;
    }
}

