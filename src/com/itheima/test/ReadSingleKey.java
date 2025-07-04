package com.itheima.test;

import java.util.Scanner;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ReadSingleKey extends JFrame {

    private JLabel label;      // 显示按键值

    public ReadSingleKey() {
        // 基本窗口设置
        setTitle("键盘监听示例");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        label = new JLabel("按下任意键…", SwingConstants.CENTER);
        label.setBounds(50, 50, 200, 80);
        add(label);

        // 关键：给窗口加键盘监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // e.getKeyChar() 取字符；getKeyCode() 取虚拟键码
                char ch  = e.getKeyChar();
                int code = e.getKeyCode();
                label.setText("字符: " + ch + "   键码: " + code);
            }
        });

        // 保证窗口获得焦点才能接收键盘事件
        setFocusable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReadSingleKey::new);
    }
}
