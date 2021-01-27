package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel {
    //蛇的长度
    int length;
    //定义两个数组,用来存储蛇的x轴与y轴坐标
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    //游戏只有两个状态，开始，暂停
    boolean isStart = false;
    //加入一个定时器
    Timer timer;
    //定义蛇的行走方向
    String direction;
    //定义食物坐标
    int foodX;
    int foodY;
    //定义一个积分
    int score;
    //蛇的生死：
    boolean isDie = false;//默认情况下，蛇是活着的


    public void init(){
        //初始化蛇的长度
        length = 3;
        //初始化头部
        snakeX[0] = 175;
        snakeY[0] = 275;
        //初始化第一节身子
        snakeX[1] = 150;
        snakeY[1] = 275;
        //初始化第二节身子
        snakeX[2] = 125;
        snakeY[2] = 275;
        //初始化蛇头的方向
        direction = "R";
        //初始化食物坐标
        foodX = 300;
        foodY = 200;
    }

    public GamePanel(){
        init();
        //将焦点定位在当前操作的面板上
        this.setFocusable(true);
        //加入监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {//监听键盘的按下操作
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                System.out.println(keyCode);
                if (keyCode == KeyEvent.VK_SPACE){//监听空格
                    if(isDie){
                        //恢复初始化动作：
                        init();
                        //死亡状态改为false:
                        isDie = false;
                    }
                    else{
                        //将isStart设置为相反的值
                        isStart = !isStart;
                        //页面重新绘制：
                        repaint();//底层调用的还是paintComponent
                    }
                }
                //监听方向
                if (keyCode == KeyEvent.VK_UP){
                    direction = "U";
                }
                if (keyCode == KeyEvent.VK_DOWN){
                    direction = "D";
                }
                if (keyCode == KeyEvent.VK_LEFT){
                    direction = "L";
                }
                if (keyCode == KeyEvent.VK_RIGHT){
                    direction = "R";
                }
            }
        });
        //对定时器进行初始化动作
        timer = new Timer(100, new ActionListener() {
            /*
            ActionListener是事件监听，每100ms监听一下是否发生动作，具体动作放入actionPerformed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart){
                    //动身子
                    for (int i=length-1; i>0; i--){
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }
                    //动头
                    if ("R".equals(direction)){
                        snakeX[0] += 25;
                    }
                    if ("L".equals(direction)){
                        snakeX[0] -= 25;
                    }
                    if ("U".equals(direction)){
                        snakeY[0] -= 25;
                    }
                    if ("D".equals(direction)){
                        snakeY[0] += 25;
                    }
                    //防止蛇出边界
                    if (snakeX[0] > 750){
                        snakeX[0] = 25;
                    }
                    if (snakeX[0] < 25){
                        snakeX[0] = 750;
                    }
                    if (snakeY[0] < 100){
                        snakeY[0] = 725;
                    }
                    if (snakeY[0] > 725){
                        snakeY[0] = 100;
                    }
                    //碰撞检测
                    if (snakeX[0] == foodX && snakeY[0] == foodY){
                        //蛇的长度+1
                        length++;
                        //随机生成食物坐标且坐标必须是25的倍数 两种不同的方法
                        foodX = ((int) (Math.random()*30)+1)*25;//[25,750]
                        foodY = (new Random().nextInt(26)+4)*25;//[100,725]
                        score += 10;
                    }
                    //判断蛇是否死亡：
                    //蛇什么时候死：只要蛇头跟任意一节身子重合，就是死：
                    for (int i = 1; i < length ; i++) {
                        if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                            isDie = true;//蛇死亡了
                        }
                    }
                    repaint();
                }
            }
        });
        //启动定时器
        timer.start();

    }
    //这个方法比较特殊，相当于图形版的main方法，自动调用
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //填充背景颜色
        this.setBackground(new Color(239, 187, 123, 86));
        //画头部图片
        Images.headerImg.paintIcon(this,g,10,10);
        //调节画笔颜色
        g.setColor(new Color(219, 170, 170));
        //画一个矩形
        g.fillRect(10,70,770,685);
        //画蛇
        //蛇头
        if("R".equals(direction)){
            Images.rightImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if("L".equals(direction)){
            Images.leftImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if("U".equals(direction)){
            Images.upImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        if("D".equals(direction)){
            Images.downImg.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        //第一节身子
        //Images.bodyImg.paintIcon(this,g,snakeX[1],snakeX[1]);
        //第二节身子
        //Images.bodyImg.paintIcon(this,g,snakeX[2],snakeX[2]);
        //优化为循环画身子
        for(int i=1; i<length; i++){
            Images.bodyImg.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        //如果游戏是暂停的，界面中应有结束语
        if (isStart == false){
            //画一个文字
            g.setColor(new Color(245, 26, 7));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("点击空格开始游戏",250,330);
        }
        //画食物
        Images.foodImg.paintIcon(this,g,foodX,foodY);
        //画积分
        g.setColor(new Color(245, 26, 7));
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("积分：" + score,620,40);
        //判断死亡状态：
        if(isDie){
            g.setColor(new Color(255, 82, 109));
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString("小蛇死亡，游戏停止，按下空格游戏重新开始！",100,350);
            init(); 
        }
    }
}
