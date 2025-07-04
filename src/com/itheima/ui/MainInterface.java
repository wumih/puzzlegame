package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Container;          // ★ 新增
// 下面这行可选：revalidate() 属于 java.awt.Component
// import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class MainInterface extends JFrame implements KeyListener, ActionListener {


    //创建一个2维数组
//目的：用来管理数据
    //加载图片的时候，会根据二维数组中的数据进行
    int[][] data = new int[4][4];
    //记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;
    //定义一个二维数组，存储正确的数据
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //定义变量用来统计步数
    int step = 0;
    //创建选项下面的条目对象
    JMenuItem replymenuitem = new JMenuItem("重新游戏");
    JMenuItem loginmenuitem = new JMenuItem("重新登录");
    JMenuItem closemenuitem = new JMenuItem("关闭游戏");
    JMenuItem accountmenuitem = new JMenuItem("公众号");


    public MainInterface() {
//初始化界面
        initialjframe();
        //初始化菜单，创造整个菜单对象
        initialjmenuBar();

        //初始化数据（打乱）
        initData();
        //初始化图片.根据打乱后的图片序号进行加载。
        initImage();

        //让界面显示出来，建议写在最后
        this.setVisible(true);

    }

    //初始化数据（打乱）
    private void initData() {
        // 1. 定义一个一维数组
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 2. 打乱数组中的数据顺序
        //    遍历数组，拿当前元素与随机索引上的元素交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            // 获取随机索引
            int index = r.nextInt(tempArr.length);
            // 交换 tempArr[i] 与 tempArr[index]
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }


        // 4. 给二维数组添加数据
        //    遍历 tempArr，将每个元素依次填入 data 中
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }

            data[i / 4][i % 4] = tempArr[i];
        }
    }


    //初始化图片
    private void initImage() {
        // ① 仅一次性清空
        Container pane = this.getContentPane();
        pane.removeAll();

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        if (victory()) {
            //显示胜利的图标
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        // ② 绘制 16 张图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                // 空白块 0 不加载图片
                if (num == 0) continue;

                ImageIcon icon = new ImageIcon("image/animal/animal1/" + num + ".jpg");
                JLabel label = new JLabel(icon);
                label.setBounds(83 + 105 * j, 134 + 105 * i, 105, 105);
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));
                pane.add(label);
            }
        }

        // ③ 背景
        JLabel bg = new JLabel(new ImageIcon("image/background.png"));
        bg.setBounds(40, 40, 508, 560);
        pane.add(bg);

        // ④ 刷新
        pane.revalidate();   // 让布局管理器重新布局
        pane.repaint();      // 重绘
    }


    private void initialjmenuBar() {
        JMenuBar jmb = new JMenuBar();
        //创建菜单上面的两个选项，功能和关于我们
        JMenu functionjmenu = new JMenu("功能");
        JMenu aboutjmenu = new JMenu("关于我们");



        functionjmenu.add(replymenuitem);
        functionjmenu.add(loginmenuitem);
        functionjmenu.add(closemenuitem);
        aboutjmenu.add(accountmenuitem);
        //给条目绑定事件
        replymenuitem.addActionListener(this);
        loginmenuitem.addActionListener(this);
        closemenuitem.addActionListener(this);
        accountmenuitem.addActionListener(this);


        jmb.add(functionjmenu);
        jmb.add(aboutjmenu);

        this.setJMenuBar(jmb);
    }

    //        游戏主界面
    private void initialjframe() {
        this.setSize(603, 680);
        this.setTitle("拼图小游戏1.0");
        this.setAlwaysOnTop(true);
        //位置居中
        this.setLocationRelativeTo(null);
        //设置关闭模式，有0.1.2.3之分其中1是默认。0是点击关闭但是什么都不发生， 2是关闭最后一个界面虚拟机才关闭，3是关闭一个界面就都关闭
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            //把界面中所有的图片全部删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon("E:\\Java代码\\puzzlegame\\image\\animal\\animal1\\all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //加载背景图片
            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("E:\\Java代码\\puzzlegame\\image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            //把背景图片添加到界面当中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }

    }

    //松开按键的时候会调用这个方法
    @Override
    public void keyReleased(KeyEvent e) {
//        //判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的移动代码了
//
//        //对上，下，左，右进行判断
//        //左：37 上：38 右：39 下：40
       int code = e.getKeyCode();
       System.out.println(code);
//        if (code == 37) {
//            System.out.println("向左移动");
//            if (y == 3) {
//                return;
//            }
//            //逻辑：ww
//            //把空白方块右方的数字往左移动
//            data[x][y] = data[x][y + 1];
//            data[x][y + 1] = 0;
//            y++;
//            //每移动一次，计数器就自增一次。
//            //每移动一次，计数器就自增一次。
//            step++;
//
//            //调用方法按照最新的数字加载图片
//            initImage();
//
//        } else if (code == 38) {
//            System.out.println("向上移动");
//            if (x == 3) {
//                //表示空白方块已经在最下方了，他的下面没有图片再能移动了
//                return;
//            }
//            //逻辑：
//            //把空白方块下方的数字往上移动
//            //x，y  表示空白方块
//            //x + 1， y 表示空白方块下方的数字
//            //把空白方块下方的数字赋值给空白方块
//            data[x][y] = data[x + 1][y];
//            data[x + 1][y] = 0;
//            x++;
//            //每移动一次，计数器就自增一次。
//            //每移动一次，计数器就自增一次。
//            step++;
//
//            //调用方法按照最新的数字加载图片
//            initImage();
//        } else if (code == 39) {
//            System.out.println("向右移动");
//            if (y == 0) {
//                return;
//            }
//            //逻辑：
//            //把空白方块左方的数字往右移动
//            data[x][y] = data[x][y - 1];
//            data[x][y - 1] = 0;
//            y--;
//
//            //每移动一次，计数器就自增一次。
//            step++;
//
//            //调用方法按照最新的数字加载图片
//            initImage();
//        } else if (code == 40) {
//            System.out.println("向下移动");
//            if (x == 0) {
//                return;
//            }
//            //逻辑：
//            //把空白方块上方的数字往下移动
//            data[x][y] = data[x - 1][y];
//            data[x - 1][y] = 0;
//            x--;
//            //每移动一次，计数器就自增一次。
//
//            step++;
//
//            //调用方法按照最新的数字加载图片
//            initImage();
//        } else if (code == 65) {
//            initData();
//        } else if (code == 16) {
//            data = new int[][]{
//                    {1, 2, 3, 4},
//                    {5, 6, 7, 8},
//                    {9, 10, 11, 12},
//                    {13, 14, 15, 0}
//            };
//            initImage();
//        }
        switch (code) {
            case KeyEvent.VK_LEFT:      // ← ：空白块向左移
                if (y > 0) {                  // 左边有方块
                    data[x][y]     = data[x][y - 1];
                    data[x][y - 1] = 0;
                    y--;
                    step++;
                    initImage();
                }
                break;

            case KeyEvent.VK_RIGHT:     // → ：空白块向右移
                if (y < 3) {                  // 右边有方块
                    data[x][y]     = data[x][y + 1];
                    data[x][y + 1] = 0;
                    y++;
                    step++;
                    initImage();
                }
                break;

            case KeyEvent.VK_UP:        // ↑ ：空白块向上移
                if (x > 0) {                  // 上边有方块
                    data[x][y]     = data[x - 1][y];
                    data[x - 1][y] = 0;
                    x--;
                    step++;
                    initImage();
                }
                break;

            case KeyEvent.VK_DOWN:      // ↓ ：空白块向下移
                if (x < 3) {                  // 下边有方块
                    data[x][y]     = data[x + 1][y];
                    data[x + 1][y] = 0;
                    x++;
                    step++;
                    initImage();
                }
                break;

            case KeyEvent.VK_A:         // A 键：重新打乱
                initData();
                initImage();
                break;

            case KeyEvent.VK_SHIFT:     // Shift：一步通关
                data = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9,10,11,12},
                        {13,14,15,0}
                };
                initImage();
                break;
        }

    }

    //判断data数组中的数据是否跟win数组中相同
    //如果全部相同，返回true。否则返回false
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            //i : 依次表示二维数组 data里面的索引
            //data[i]：依次表示每一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    //只要有一个数据不一样，则返回false
                    return false;
                }
            }
        }
        //循环结束表示数组遍历比较完毕，全都一样返回true
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        //判断
        if (obj == replymenuitem) {
            System.out.println("重新游戏");
            //计步器清零
            step = 0;
            //再次打乱二维数组中的数据
            initData();
            //重新加载图片
            initImage();

        } else if (obj == loginmenuitem) {
            System.out.println("重新登录");
            //关闭当前的游戏界面
            this.setVisible(false);
            //打开登录界面
            new logininterface();
        } else if (obj == closemenuitem) {
            System.out.println("关闭游戏");
            //直接关闭虚拟机即可
            System.exit(0);
        } else if (obj == accountmenuitem) {
//            System.out.println("公众号");
//            //创建一个弹框对象
//            JDialog jDialog = new JDialog();
//            //创建一个管理图片的容器对象JLabel
//            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\image\\about.png"));
//            //设置位置和宽高
//            jLabel.setBounds(0,0,258,258);
//            //把图片添加到弹框当中
//            jDialog.getContentPane().add(jLabel);
//            //给弹框设置大小
//            jDialog.setSize(344,344);
//            //让弹框置顶
//            jDialog.setAlwaysOnTop(true);
//            //让弹框居中
//            jDialog.setLocationRelativeTo(null);
//            //弹框不关闭则无法操作下面的界面
//            jDialog.setModal(true);
//            //让弹框显示出来
//            jDialog.setVisible(true);
        }
    }

}




