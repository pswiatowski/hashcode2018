package com.katzo.hashcode.selfdrive;

/**
 * Created by pswiatowski on 3/1/18.
 */
public class Util {

    public static int calculatePosition(Position p1, Position p2) {
        return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
    }
}
