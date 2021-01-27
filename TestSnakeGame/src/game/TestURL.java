package game;

import java.net.URL;

public class TestURL {
    public static void main(String[] args) {
        URL url = Images.class.getResource("/");// /指代的就是相对路径，相对/D:/TestJavaSE/out/production/TestSnakeGame/
        System.out.println(url);
    }
}
