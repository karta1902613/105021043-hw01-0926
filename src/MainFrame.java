import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.annotation.Target;
import java.util.Random;

import static java.lang.Thread.sleep;


public class MainFrame extends Frame {

    private Label labLine = new Label("");
    private Label labHit = new Label("Hit:");
    private Label labScore = new Label("0");
    private Label labShooter = new Label("⊥");
    private Label labBullet = new Label("");
    private Label labTarget = new Label("");
    Random ran = new Random();
    int score = 0;
    int sx = 400;
    int by = 410;
    int bs;
    private Button btnFire = new Button("Fire");
    private Button  btnR = new Button("=>");
    private Button  btnL = new Button("<=");
    private Button  btnAuto = new Button("Auto");
    private Timer BulletT;
    private Timer AutoT;
    Boolean time =true;

    public MainFrame(){
        init();
    }
    public void init(){
        this.setLayout(null);
        this.setBounds(50,50,800,600);
        labLine.setBounds(0,500,800,10);
        labHit.setBounds(29,549,25,20);
        labScore.setBounds(79,549,40,20);
        labShooter.setBounds( sx,440,100,60);
        labBullet.setBounds(labShooter.getX()+15,410,5,20);
        labTarget.setBounds(ran.nextInt((700)+50),80,40,30);
        btnAuto.setBounds(133,525,100,60);
        btnFire.setBounds(470,525,100,60);
        btnL.setBounds(330,525,100,60);
        btnR.setBounds(610,525,100,60);

        btnAuto.setBackground(new Color(0xBBFF93));
        btnR.setBackground(new Color(0xBBFF93));
        btnL.setBackground(new Color(0xBBFF93));
        btnFire.setBackground(new Color(0xBBFF93));
        labBullet.setBackground(new Color(0xFFFFFF));
        labTarget.setBackground(new Color(0x4AC3FF));

        btnFire.setFont(new Font("", Font.BOLD|Font.PLAIN , 16));
        btnL.setFont(new Font("", Font.BOLD|Font.PLAIN , 40));
        btnR.setFont(new Font("", Font.BOLD|Font.PLAIN , 40));
        btnAuto.setFont(new Font("", Font.BOLD|Font.PLAIN , 16));
        labShooter.setFont(new Font("", Font.BOLD|Font.PLAIN , 50));
        labHit.setFont(new Font("", Font.PLAIN|Font.PLAIN , 16));
        labScore.setFont(new Font("", Font.BOLD|Font.PLAIN , 16));

        this.add(labLine);
        this.add(labHit);
        this.add(labScore);
        this.add(btnFire);
        this.add(btnR);
        this.add(btnL);
        this.add(btnAuto);
        this.add(labShooter);
        this.add(labBullet);
        this.add(labTarget);


        labLine.setBackground(new Color(0x00FF00));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        btnR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sx<=750) {
                    sx += 10;
                    labShooter.setLocation(sx, 440);
                }
            }
        });
        btnL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sx>=10) {
                    sx -= 10;
                    labShooter.setLocation(sx, 440);
                }
            }
        });
        btnFire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(time==true){
                    labBullet.setLocation(sx+15,by);
                    BulletT.start();
                    bs=sx+15;
                    time = false;
                }else{
                }
            }
        });
        BulletT = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            by-=10;
            labBullet.setLocation(bs,by);
                if(labBullet.getY()<=90&&labBullet.getX()>labTarget.getX()-5&&labBullet.getX()<labTarget.getX()+45){
                    labTarget.setBackground(new Color(0xFF0000));
                    labBullet.setBackground(new Color(0xFFFFFF));
                    if(labBullet.getY()==0){
                        labTarget.setBounds(ran.nextInt((700)+50),80,40,30);
                        labTarget.setBackground(new Color(0x4AC3FF));
                        BulletT.stop();
                        labBullet.setLocation(bs+15,410);
                        by=410;
                        time=true;
                        score++;
                        labScore.setText(Integer.toString(score));
                    }
                    }
                if(labBullet.getY()<410) {
                    labBullet.setBackground(new Color(0xFF0000));
                }
                if(labBullet.getY()==0){
                    BulletT.stop();
                    labBullet.setLocation(bs+15,410);
                    labBullet.setBackground(new Color(0xFFFFFF));
                    by=410;
                    time=true;
                }
            }
        });

        btnAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AutoT.start();
            }
        });

        AutoT = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(labShooter.getX()> labTarget.getX()){
                    sx -= 10;
                    labShooter.setLocation(sx, 440);
                    if(sx<labTarget.getX()){
                        AutoT.stop();
                    }
                }else{
                    sx += 10;
                    labShooter.setLocation(sx, 440);
                    if(sx>labTarget.getX()){
                        AutoT.stop();
                    }
                }
            }
        });
    }
}
