package bawo.healthfacility.Util;

import java.util.Random;


public class Constants {
    public static final String URL = "https://hacklab2018-14718.firebaseio.com/hospitals.json";

    public static final int LIMIT = 30;

    public static int randomInt(int max, int min) {
        return new Random().nextInt(max - min) + min;

    }
}
