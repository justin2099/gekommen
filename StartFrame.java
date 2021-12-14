
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame
{   public static int score = 0;
    public static void main(String[] agrs)
    {   JFrame frame=new JFrame("开始界面");    //创建Frame窗口
        JPanel p1=new JPanel();    //面板1
        JPanel p2=new JPanel();    //面板2

        JLabel label1=new JLabel("人机or人人：");
        label1.setFont(new Font("楷体",Font.BOLD,20));    p2.add(label1);
        JRadioButton rb1=new JRadioButton("人机");      JRadioButton rb2=new JRadioButton("人人");
        ButtonGroup group1=new ButtonGroup();    group1.add(rb1);     group1.add(rb2);      p2.add(rb1);    p2.add(rb2);

        JLabel label2=new JLabel("选择棋盘大小：");
        label2.setFont(new Font("楷体",Font.BOLD,20));    p2.add(label2);
        JRadioButton rb3=new JRadioButton("13*13");      JRadioButton rb4=new JRadioButton("15*15");
        ButtonGroup group2=new ButtonGroup();    group2.add(rb3);     group2.add(rb4);      p2.add(rb3);    p2.add(rb4);

        JPanel cards=new JPanel(new CardLayout());//卡片式布局的面板

        JButton a1 = new JButton("开始");     JButton a2 = new JButton("退出");     JButton a3 = new JButton("开始游戏");
        p1.add(a1);     p1.add(a2);     p2.add(a3);

        cards.add(p1,"card1");    //向卡片式布局面板中添加面板1
        cards.add(p2,"card2");    //向卡片式布局面板中添加面板2
        CardLayout cl=(CardLayout)(cards.getLayout());
        cl.show(cards,"card1");    //调用show()方法显示面板2
        frame.add(cards);
        frame.setBounds(600,400,800,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(cards,"card2");//enter card2
            }
        });

        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.exit(1);//exit the game
            }
        });

        rb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = score + 1;
            }
        });

        rb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = score + 2;
            }
        });

        rb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = score + 3;
            }
        });

        rb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = score + 5;
            }
        });

        a3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (score){
                    case 4://人机13
                        System.out.println("人机13");     frame.setVisible(false);
                        break;
                    case 6://人机15
                        System.out.println("人机15");     frame.setVisible(false);
                        break;
                    case 5://人人13
                        System.out.println("人人13");     frame.setVisible(false);
                        break;
                    case 7://人人15
                        System.out.println("人人15");     frame.setVisible(false);
                        break;
                }
            }
        });

    }
}