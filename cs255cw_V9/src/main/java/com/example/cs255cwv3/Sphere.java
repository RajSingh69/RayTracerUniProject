package com.example.cs255cwv3;

/**
 * CS-255 Coursework: Ray Tracing
 * Sphere Class
 * @author Raj Bhamra (2034215)
 * @version V9
 */


public class Sphere {

    /**
     * This is my sphere class. It contains everything to do with the spheres. The variables include the origin,
     * centreSphere, direction, sphereRadius, light and sphereColour.
     */

    private Vector origin;
    private Vector direction;
    private Vector centreSphere;
    private double sphereRadius;
    private Vector light;                           //Feel it is not needed.
    private Vector sphereColour;


    /**
     * This is where the constructor is set up. It takes in the centreSphere, sphereRadius and the sphereColour. There
     * is also a slight math error, I got confused with the difference between the origin and the centreSphere, so I set
     * the origin to equal the centre of the sphere.
     */
    public Sphere (Vector centreSphere, double sphereRadius, Vector sphereColour) {
        this.origin = centreSphere;
        this.sphereRadius = sphereRadius;
        this.centreSphere = centreSphere;
        this.sphereColour = sphereColour;
    }

    /**
     * Here are all my getters and setters. They all have various purposes.
     * @return
     */

    public Vector getOrigin() {
        return origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public Vector getSphereColour(){
        return sphereColour;
    }

    public Vector getCentreSphere() {
        return centreSphere;
    }

    public double getSphereRadius(){
        return sphereRadius;
    }

    public void setColour (Vector sphereColour) {
        this.sphereColour = sphereColour;
    }

    public void setDirection (Vector origin){
        this.origin = origin;
    }

    public void setSphereRadius(double[] doubles) {this.sphereRadius = sphereRadius;}

    /**
     * Here is my attempt at passing the sphere intersection method into the sphere class. It did not work, as I kept
     * getting errors about missing variables, or "sphere" on line 107.
     */

//    public double intersectionMethod(Vector origin, Vector direction) {

//        Vector v = origin.sub(centreSphere);
//        double quad_a = direction.dot(direction);
//        double quad_b = (v.dot(direction)) * 2;
//
//        double t = 0;
//        double t_min = Double.MAX_VALUE;
//        Sphere closestSphere = null;
//        double radius = 100;
//
//        double quad_c = v.dot(v) - radius * radius;
//        double discriminant = (quad_b * quad_b) - (4 * quad_a * quad_c);
//
//        /**
//         *
//         */
//
//
//        // If the light misses the ray, set background colour as 0
//        if (discriminant < 0) {
//            //
//
//        } else { // If the light hits the ray, do this
//            t = (-quad_b - Math.sqrt(discriminant)) / (2 * quad_a);
//            if (t < 0) {
//                t = (-quad_b + Math.sqrt(discriminant)) / 2 * quad_a;
//            }
//
//            /**
//             *
//             */
//
//            if (t < t_min) {
//                t_min = t;
//                closestSphere = sphere; //"sphere" gives error
//            }
//
//            return t;
//
//        }
//    }

}
