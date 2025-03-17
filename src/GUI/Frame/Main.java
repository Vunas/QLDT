package GUI.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import GUI.Panel.SideBar;
import GUI.SideBar.TrangChuGUI;

public class Main extends JFrame {
    SideBar sideBar;
    TrangChuGUI main;

    public Main(){
        initComponent();
    }

    private void initComponent(){
        this.setSize(new Dimension(1400, 800));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setTitle("Hệ thống quản lý ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sideBar= new SideBar(this);
        this.add(sideBar,BorderLayout.WEST);

        main= new TrangChuGUI();
        this.add(main, BorderLayout.CENTER);
    }

    public void setPanel(JPanel pn) {
        getContentPane().removeAll();
        getContentPane().add(sideBar,BorderLayout.WEST);
        getContentPane().add(pn,BorderLayout.CENTER);
        getContentPane().repaint();
        getContentPane().validate();
    }

    public static void main(String[] args) {
        
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatLightLaf.setup();
        FlatIntelliJLaf.setup();
        
        new Main().setVisible(true);
    }
}
