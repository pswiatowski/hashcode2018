package com.katzo.hashcode;

 /**
 * Created by pswiatowski on 2/20/18.
 * Usage example java com.katzo.hashcode.Main < ../../../resources/main/in.txt
 */
public class Main {

    public static void main(String[] args) {

        try(HashCodeIO io = new HashCodeIO(System.in, System.out)) {
            while(io.hasMoreTokens()) {
                int anInt = io.getInt();
                io.printf("I read an int %d\n", anInt);
            }
        }
    }
}
