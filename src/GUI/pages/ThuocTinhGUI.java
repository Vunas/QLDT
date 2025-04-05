
package GUI.pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import GUI.DiaLog.DungLuongRamDialog;
import GUI.DiaLog.DungLuongRomDialog;
import GUI.DiaLog.MauSacDialog;
import GUI.DiaLog.ThuongHieuDialog;
import GUI.Frame.Main;
import GUI.Panel.itemTaskbar;
import GUI.Panel.Component.IntegratedSearch;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThuocTinhGUI extends JPanel {

    private final int n = 20;
    JPanel box[], pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    
    IntegratedSearch search;
    JLabel lbl1, lblImage;
    JLabel lbl[], lblIcon[], info;
    JScrollPane scrPane;
    ThuongHieuDialog th;
    
    DungLuongRamDialog dlram;
    DungLuongRomDialog dlrom;
    MauSacDialog mausac;
    Main m;
    public itemTaskbar[] listitem;

    String iconst[] = {"brand","ram", "rom", "color"};

    String header[] = {"Thương hiệu","Ram", "Rom", "Màu sắc"};
    Color BackgroundColor = new Color(240, 247, 250);
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    
    public ThuocTinhGUI(Main m) {
        this.m = m;
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[header.length];

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new GridLayout(3, 2, 20, 20));

//        scrPane = new JScrollPane(contentCenter);
//        scrPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(contentCenter, BorderLayout.CENTER);

        box = new JPanel[n];
        lbl = new JLabel[n];
        lblIcon = new JLabel[n];
        for (int i = 0; i < header.length; i++) {
            listitem[i] = new itemTaskbar(iconst[i], header[i], header[i]);
            contentCenter.add(listitem[i]);
        }

        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                th = new ThuongHieuDialog(owner, ThuocTinhGUI.this, "Quản lý thương hiệu", true);
                th.setVisible(true);
            }
        });
        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                dlram = new DungLuongRamDialog(owner, ThuocTinhGUI.this, "Quản lý dung lượng RAM", true);
                dlram.setVisible(true);
            }
        });
        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                dlrom = new DungLuongRomDialog(owner, ThuocTinhGUI.this, "Quản lý dung lượng ROM", true);
                dlrom.setVisible(true);
            }
        });

        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                mausac = new MauSacDialog(owner, ThuocTinhGUI.this, "Quản lý màu sắc", true);
                mausac.setVisible(true);
            }
        });
    }

    public void Mouseopress(MouseEvent evt) {
        for (int i = 0; i < listitem.length; i++) {
            if (evt.getSource() == listitem[i]) {

            }
        }
    }

    

    public void initPadding() {

        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 150));
        pnlBorder1.setBackground(BackgroundColor);

        JLabel lblContent = new JLabel("Vui lòng chọn thuộc tính!");
        lblContent.putClientProperty("FlatLaf.style", "font: 300% $medium.font");
        lblContent.setBorder(new MatteBorder(0, 0, 3, 0, new Color(100, 149, 237)));
        
        pnlBorder1.add(lblContent);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 40));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(40, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(40, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);

    }

}
