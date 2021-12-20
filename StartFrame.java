
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame
{   public static int score = 0;
    public static void main(String[] agrs)
    {
        //美化界面
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }

        //
        //加载图片
        ImageIcon icon=new ImageIcon("04.png");
        //Image im=new Image(icon);
        //将图片放入label中
        JLabel label=new JLabel(icon);

        //设置label的大小
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());



        JFrame frame=new JFrame("开始界面");    //创建Frame窗口
        JPanel p1=new JPanel();    //面板1
        JPanel p2=new JPanel();    //面板2

        //获取窗口的第二层，将label放入
        frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));

        //获取frame的顶层容器,并设置为透明
        JPanel j=(JPanel)frame.getContentPane();
        j.setOpaque(false);

        JLabel label1=new JLabel("人机or人人：");
        label1.setFont(new Font("楷体",Font.BOLD,20));    p2.add(label1);
        JRadioButton rb1=new JRadioButton("人机");      JRadioButton rb2=new JRadioButton("人人");
        ButtonGroup group1=new ButtonGroup();    group1.add(rb1);     group1.add(rb2);      p2.add(rb1);    p2.add(rb2);

        JLabel label2=new JLabel("选择棋盘大小：");
        label2.setFont(new Font("楷体",Font.BOLD,20));    p2.add(label2);
        JRadioButton rb3=new JRadioButton("13*13");      JRadioButton rb4=new JRadioButton("15*15");
        ButtonGroup group2=new ButtonGroup();    group2.add(rb3);     group2.add(rb4);      p2.add(rb3);    p2.add(rb4);

        p1.setOpaque(false);    p2.setOpaque(false);    frame.setSize(icon.getIconWidth(),icon.getIconHeight());

        JPanel cards=new JPanel(new CardLayout());//卡片式布局的面板

        JButton a1 = new JButton("开始");     JButton a2 = new JButton("退出");     JButton a3 = new JButton("开始游戏");
        p1.add(a1);     p1.add(a2);     p2.add(a3);

        cards.add(p1,"card1");    //向卡片式布局面板中添加面板1
        cards.add(p2,"card2");    //向卡片式布局面板中添加面板2

        p1.setLayout(null);     a1.setBounds(95,130,150,50);   a2.setBounds(305,130,150,50);//设置按钮大小与位置
        p2.setLayout(null);     label1.setBounds(80,50,150,50);     rb1.setBounds(250,50,50,50);      rb2.setBounds(370,50,50,50);//rb3.setBounds(250,100,50,50); rb4.setBounds(350,100,50,50);
                                label2.setBounds(80,100,150,50);    rb3.setBounds(250,100,80,50);     rb4.setBounds(370,100,80,50);
                                a3.setBounds(185,200,180,50);

        CardLayout cl=(CardLayout)(cards.getLayout());
        cl.show(cards,"card1");    //调用show()方法显示面板2
        frame.add(cards);
        frame.setBounds(600,400,550,400);
        frame.setVisible(true);     cards.setOpaque(false);
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
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new aGame(15).play();
                            }
                        }).start();
                        frame.setVisible(false);
                }
            }
        });
    }
}
