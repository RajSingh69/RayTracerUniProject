package com.example.cs255cwv3;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * CS-255 Coursework: Ray Tracing
 * Main Class
 * @author Raj Bhamra (2034215)
 * @version V9
 */

public class Main extends Application {

    /**
     * Setting the initial variables
     */
    private final int SCREEN_WIDTH = 500;
    private final int SCREEN_HEIGHT = 500;

    private ArrayList<Sphere> spheres = new ArrayList<>();

    private final double GREEN_COL = 0.1;
    private final double RED_COL = 0.1;
    private final double BLUE_COL = 0.1;
    private final double X_AXIS = 0.1;
    private final double Y_AXIS = 0.1;
    private final double Z_AXIS = 0.1;
    private double h_camera = 50;
    private double v_camera = -50;
    private double d_camera = -100;
    private double testerRadius = 5;

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        /**
         * Creating an image that the code can write to. I am also initialising the spheres here.
         * I also create a view of the image and add it to the pane.
         */
        stage.setTitle("Ray Tracing");

        spheres.add(new Sphere(new Vector(-50, -20, 100), 60, new Vector(GREEN_COL,
                RED_COL, BLUE_COL)));
        spheres.add(new Sphere(new Vector(-10, -150, 100), 90, new Vector(GREEN_COL,
                RED_COL, BLUE_COL)));

        /**
         * These two spheres are failed tests on trying to incorporate the selection of spheres using
         * the radio buttons. I kept them small as to not get in the way.
         */

        spheres.add(new Sphere(new Vector(-120, 200, 20), 30, new Vector(GREEN_COL,
                RED_COL, BLUE_COL)));
        spheres.add(new Sphere(new Vector(-90, -200, 20), 30, new Vector(GREEN_COL,
                RED_COL, BLUE_COL)));

        WritableImage image = new WritableImage(SCREEN_WIDTH, SCREEN_HEIGHT);
        ImageView view = new ImageView(image);


        /**
         * Creating the simple GUI
         */

        Label redLabel = new Label("        RED");
        Slider r_slider_one = new Slider(0.1, 0.99, RED_COL);
        Slider r_slider_zero = new Slider(0.1, 0.99, RED_COL);

        Label greenLabel = new Label("      GREEN");
        Slider g_slider_one = new Slider(0.1, 0.99, GREEN_COL);
        Slider g_slider_zero = new Slider(0.1, 0.99, GREEN_COL);

        Label blueLabel = new Label("      BLUE");
        Slider b_slider_one = new Slider(0.1, 0.99, BLUE_COL);
        //b_slider_one.setPrefWidth(200);
        //b_slider_one.setShowTickMarks(true);
        //b_slider_one.setShowTickLabels(true);
        //b_slider_one.setBlockIncrement(0.035);
        Slider b_slider_zero = new Slider(0.1, 0.99, BLUE_COL);

        Label xAxisLabel = new Label("      X-AXIS");
        Slider xAxisSlider_one = new Slider(-255, 255, X_AXIS);
        Slider xAxisSlider_zero = new Slider(-255, 255, X_AXIS);

        Label yAxisLabel = new Label("      Y-AXIS");
        Slider yAxisSlider_one = new Slider(-255, 255, Y_AXIS);
        Slider yAxisSlider_zero = new Slider(-255, 255, Y_AXIS);

        Label zAxisLabel = new Label("      Z-AXIS");
        Slider zAxisSlider_one = new Slider(-255, 255, Z_AXIS);
        Slider zAxisSlider_zero = new Slider(-255, 255, Z_AXIS);

        Slider radiusSlider_one = new Slider(-200, 200, testerRadius);

        Label hCameraLabel = new Label("      HORIZONTAL CAMERA LIGHTING");
        Slider hCameraSlider = new Slider(-255, 255, h_camera);

        Label vCameraLabel = new Label("      VERTICAL CAMERA LIGHTING");
        Slider vCameraSlider = new Slider(-255, 255, v_camera);

        Label dCameraLabel = new Label("      DISTANCE CAMERA LIGHTING");
        Slider dCameraSlider = new Slider(-255, 255, d_camera);

        /**
         * Here are where I set up and initialised the radio buttons for selection.
         */

        ToggleGroup tg = new ToggleGroup();
        RadioButton s1 = new RadioButton("Sphere One");
        s1.setToggleGroup(tg);
        RadioButton s2 = new RadioButton("Sphere Two");
        s2.setToggleGroup(tg);
        RadioButton s3 = new RadioButton("Sphere Three");
        s2.setToggleGroup(tg);
        RadioButton s4 = new RadioButton("Sphere Four");
        s2.setToggleGroup(tg);

//        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
//        {
//            public void changed(ObservableValue<? extends Toggle> ob,
//                                Toggle o, Toggle n)
//            {
//
//                RadioButton rb = (RadioButton)tg.getSelectedToggle();
//
//                if (rb != null) {
//                    String s = rb.getText();
//
//                    // change the label
//                    //l2.setText(s + " selected");
//                }
//            }
//        });

        /**
         * Here are all the sliders. I have 2 sets of each slider, referencing the arraylist position. I have slider 1
         * referencing position 1 and slider 0 referencing position 0 in the arraylist. I also get the old colour and
         * then set the colour by changing the value i aim to change (position i, j, k (x, y, z)).
         *
         * These sliders change the RED colour intensity for the spheres.
         */

        r_slider_zero.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldCol = spheres.get(0).getSphereColour();
                        spheres.get(0).setColour(new Vector(newValue.doubleValue(), oldCol.y, oldCol.z));
                        Render(image);
                        System.out.println(RED_COL);
                    }
                });

        r_slider_one.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldCol = spheres.get(1).getSphereColour();
                        spheres.get(1).setColour(new Vector(newValue.doubleValue(), oldCol.y, oldCol.z));
                        Render(image);
                        System.out.println(RED_COL);
                    }
                });

        /**
         * These sliders change the GREEN colour intensity for the spheres.
         */

        //GREEN SLIDERS

        g_slider_zero.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldCol = spheres.get(0).getSphereColour(); //change??

                        spheres.get(0).setColour(new Vector(oldCol.x, newValue.doubleValue(), oldCol.z));
                        Render(image);
                        System.out.println(GREEN_COL);
                    }
                });


        g_slider_one.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldCol = spheres.get(1).getSphereColour();

                        spheres.get(1).setColour(new Vector(oldCol.x, newValue.doubleValue(), oldCol.z));
                        Render(image);
                        System.out.println(GREEN_COL);
                    }
                });

        /**
         * These sliders change the BLUE colour intensity for the spheres.
         */

        //BLUE SLIDERS
        b_slider_zero.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldCol = spheres.get(0).getSphereColour();
                        spheres.get(0).setColour(new Vector(oldCol.x, oldCol.y, newValue.doubleValue()));

                        Render(image);
                        System.out.println(BLUE_COL);
                    }
                });

        b_slider_one.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldCol = spheres.get(1).getSphereColour();
                        spheres.get(1).setColour(new Vector(oldCol.x, oldCol.y, newValue.doubleValue()));

                        Render(image);
                        System.out.println(BLUE_COL);
                    }
                });

        /**
         * These sliders change the X AXIS position for the spheres.
         */

        xAxisSlider_zero.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldPos = spheres.get(0).getOrigin();
                        spheres.get(0).setDirection(new Vector(newValue.doubleValue(), oldPos.y, oldPos.z));
                        Render(image);
                        System.out.println(X_AXIS);
                    }
                });

        xAxisSlider_one.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldPos = spheres.get(1).getOrigin();
                        spheres.get(1).setDirection(new Vector(newValue.doubleValue(), oldPos.y, oldPos.z));
                        Render(image);
                        System.out.println(X_AXIS);
                    }
                });

        /**
         * These sliders change the Y AXIS position for the spheres.
         */


        yAxisSlider_zero.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldPos = spheres.get(0).getOrigin();
                        spheres.get(0).setDirection(new Vector(oldPos.x, newValue.doubleValue(), oldPos.z));
                        Render(image);
                        System.out.println(Y_AXIS);
                    }
                });

        yAxisSlider_one.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldPos = spheres.get(1).getOrigin();
                        spheres.get(1).setDirection(new Vector(oldPos.x, newValue.doubleValue(), oldPos.z));
                        Render(image);
                        System.out.println(Y_AXIS);
                    }
                });

        /**
         * These sliders change the Z AXIS position for the spheres.
         */

        zAxisSlider_zero.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldPos = spheres.get(0).getOrigin();
                        spheres.get(0).setDirection(new Vector(oldPos.x, oldPos.y, newValue.doubleValue()));
                        Render(image);
                        System.out.println(Z_AXIS);
                    }
                });

        zAxisSlider_one.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        Vector oldPos = spheres.get(1).getOrigin();
                        spheres.get(1).setDirection(new Vector(oldPos.x, oldPos.y, newValue.doubleValue()));
                        Render(image);
                        System.out.println(Z_AXIS);
                    }
                });

        radiusSlider_one.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        double oldRadius = spheres.get(1).getSphereRadius();
                        spheres.get(1).setSphereRadius(new double[5]);
                        Render(image);
                        System.out.println(testerRadius);
                    }
                });

        /**
         * This slider changes the HORIZONTAL position for the camera light (fixed position).
         */

        hCameraSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        h_camera = newValue.doubleValue();
                        Render(image);
                        System.out.println(h_camera);
                    }
                });

        /**
         * This slider changes the VERTICAL position for the camera light (fixed position).
         */

        vCameraSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        v_camera = newValue.doubleValue();
                        Render(image);
                        System.out.println(v_camera);
                    }
                });

        /**
         * This slider changes the DEPTH position for the camera light (fixed position).
         */

        dCameraSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number>
                                                observable, Number oldValue, Number newValue) {
                        d_camera = newValue.doubleValue();
                        Render(image);
                        System.out.println(d_camera);
                    }
                });

        /**
         * The following is in case you want to interact with the image in any way
         */

        view.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
            System.out.println(event.getX() + " " + event.getY());
            event.consume();
        });

        /**
         * Here, I am setting the gap between the grid panes. I am adding each object to the view pane, displaying
         * the scene to the user and rendering the image again.
         */

        Render(image);

        GridPane root = new GridPane();
        root.setVgap(12);
        root.setHgap(12);

        //3. (referring to the 3 things we need to display an image)
        //we need to add it to the pane
        root.add(view, 0, 0);

        root.add(redLabel, 0, 1);
        root.add(r_slider_one, 1, 1);
        root.add(r_slider_zero, 2, 1);

        root.add(greenLabel, 0, 2);
        root.add(g_slider_one, 1, 2);
        root.add(g_slider_zero, 2, 2);

        root.add(blueLabel, 0, 3);
        root.add(b_slider_one, 1, 3);
        root.add(b_slider_zero, 2, 3);

        root.add(xAxisLabel, 0, 4);
        root.add(xAxisSlider_one, 1, 4);
        root.add(xAxisSlider_zero, 2, 4);

        root.add(yAxisLabel, 0, 5);
        root.add(yAxisSlider_one, 1, 5);
        root.add(yAxisSlider_zero, 2, 5);

        root.add(zAxisLabel, 0, 6);
        root.add(zAxisSlider_one, 1, 6);
        root.add(zAxisSlider_zero, 2, 6);

        //root.add(radiusSlider_one, 3, 1);

        root.add(hCameraLabel, 0, 7);
        root.add(hCameraSlider, 1, 7);

        root.add(vCameraLabel, 0, 8);
        root.add(vCameraSlider, 1, 8);

        root.add(dCameraLabel, 0, 9);
        root.add(dCameraSlider, 1, 9);

        root.add(s1, 3, 1);
        root.add(s2, 3, 2);
        root.add(s3, 3, 3);
        root.add(s4, 3, 4);

        //Display to user
        Scene scene = new Scene(root, 1024, 768);
        stage.setScene(scene);
        stage.show();

        Render(image);
    }


    public void Render(WritableImage image) {

        /**
         * This is the render method. I first start off by declaring my image dimensions and loop variables.
         */

        int w = (int) image.getWidth();
        int h = (int) image.getHeight();
        PixelWriter image_writer = image.getPixelWriter();

        Vector origin = new Vector(0, 0, 0);
        Vector direction = new Vector(0, 0, 1);
        Vector centreSphere = new Vector(X_AXIS, Y_AXIS, Z_AXIS); //use this to change the position of the sphere

        Vector sphereColour = new Vector(RED_COL, GREEN_COL, BLUE_COL); //use this to change sphere colour
        Vector backgroundColour = new Vector(0.0, 0.0, 0.0);
        Vector lightSource = new Vector(d_camera, v_camera, h_camera); //-200 is slider where camera position changes

        double radius = 100;

        /**
         * This code block scans across the full pixel width and height of the screen. I set the background to 0, and
         * set the initial variables that I will use within the loop.
         */

        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {

                image_writer.setColor(i, j, Color.color(backgroundColour.x, backgroundColour.y,
                        backgroundColour.z, 1.0));

                double t = 0;
                origin.x = i - 250;
                origin.y = j - 250;
                origin.z = -400;
                double t_min = Double.MAX_VALUE;
                Sphere closestSphere = null;

                /**
                 * It also sets a predetermined set of actions for every sphere in the arraylist, on how to build it and
                 * display it to the screen with the right functionality.
                 */

                for (Sphere sphere : spheres) {

                    centreSphere = sphere.getOrigin();
                    sphereColour = sphere.getSphereColour();
                    radius = sphere.getSphereRadius();

                    Vector v = origin.sub(centreSphere);
                    double quad_a = direction.dot(direction);
                    double quad_b = (v.dot(direction)) * 2;
                    double quad_c = v.dot(v) - radius * radius;
                    double discriminant = (quad_b * quad_b) - (4 * quad_a * quad_c);

                    /**
                     * If the light misses the ray, I set the background colour as null (0). If the light hits the ray,
                     * I call the quadratic equation on it. If the discriminant is positive,  we take the smallest
                     * possible t. If it is negative, there is no solution.
                     */


                    // If the light misses the ray, set background colour as 0
                    if (discriminant < 0) {
                        //

                    } else { // If the light hits the ray, do this
                        t = (-quad_b - Math.sqrt(discriminant)) / (2 * quad_a);
                        if (t < 0) {
                            t = (-quad_b + Math.sqrt(discriminant)) / 2 * quad_a;
                        }

                        /**
                         * This if statement is used in finding out the closest point of the sphere. If the minimum
                         * distance between t and the light ray changes, we then set the sphere with the NEW minimum t
                         * distance as the new value of t.
                         * Furthermore, we then set the closestSphere variable to that sphere, in order to reference the
                         * correct one when doing lighting.
                         */

                        if (t < t_min) {
                            t_min = t;
                            closestSphere = sphere;
                        }
                    }
                }

                /**
                 * Here, we have the normalisation method (surfaceNormal) and the vector that goes from point of
                 * intersection to the light, and finally, we find the dot product of both of them.
                 * This all takes place within loop that assumes there is no closest sphere. We need this as there are
                 * multiple spheres and not all of them can be "the closest sphere".
                 */

                if (closestSphere != null) {
                    Vector intersection = origin.add(direction.mul(t_min)); //point of intersection (<- = p)

                    Vector surfaceNormal = intersection.sub(closestSphere.getCentreSphere());
                    surfaceNormal.normalise();

                    Vector lightVector = lightSource.sub(intersection);
                    lightVector.normalise();

                    double dotProduct = lightVector.dot(surfaceNormal);

                    /**
                     * This block is specifically used to find the dot product. If the dp (dotProduct) less than 0,
                     * light has gone behind the object
                     */

                    if (dotProduct < 0)
                        dotProduct = 0;
                    if (dotProduct > 1)
                        dotProduct = 1;

                    /**
                     * Here is where shading occurs. I set each sphere to have 0.7 (70%) diffuse shading, and then
                     * to have 0.3 (30%) ambient shading. The closestSphere variable is referenced here.
                     */

                    Vector col = closestSphere.getSphereColour().mul(dotProduct * .8).
                            add(closestSphere.getSphereColour().mul(.2));
                    image_writer.setColor(i, j, Color.color(col.x, col.y, col.z, 1.0));

                }
            }
        } // column loop
    } // row loop

    public static void main(String[] args) {
        launch();
    }
}
