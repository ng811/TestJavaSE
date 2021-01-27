package game;


import javax.swing.*;
import java.net.URL;

//用来获取游戏中所涉及的图片
public class Images {
    //将图片的路径封装为一个对象
    public static URL bodyRUL = Images.class.getResource("/images/body.png");
    public static URL downRUL = Images.class.getResource("/images/down.png");
    public static URL foodRUL = Images.class.getResource("/images/food.png");
    public static URL headerRUL = Images.class.getResource("/images/header.png");
    public static URL leftRUL = Images.class.getResource("/images/left.png");
    public static URL rightRUL = Images.class.getResource("/images/right.png");
    public static URL upRUL = Images.class.getResource("/images/up.png");
    //将图片封装为程序中的一个对象
    public static ImageIcon bodyImg = new ImageIcon(bodyRUL);
    public static ImageIcon downImg = new ImageIcon(downRUL);
    public static ImageIcon foodImg = new ImageIcon(foodRUL);
    public static ImageIcon headerImg = new ImageIcon(headerRUL);
    public static ImageIcon leftImg = new ImageIcon(leftRUL);
    public static ImageIcon rightImg = new ImageIcon(rightRUL);
    public static ImageIcon upImg = new ImageIcon(upRUL);
}
