package GUI.SideBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrangChuGUI extends JPanel {
    JPanel pnlTop, pnlBot;
    
    public TrangChuGUI(){
        initComponents();
    }
    
    private void initComponents(){
        pnlTop= new JPanel();
        pnlTop.setBackground(new Color(220, 56, 92));
        pnlTop.add(new JLabel("Đây là tiêu Đề"));
        pnlTop.setPreferredSize(new Dimension(Integer.MAX_VALUE, 200));

        pnlBot= new JPanel();
        pnlBot.add(new JLabel("ĐÂy là ảnh"));


        setLayout(new BorderLayout());
        add(pnlTop,BorderLayout.NORTH);
        add(pnlBot,BorderLayout.CENTER);
    }
    
}   
