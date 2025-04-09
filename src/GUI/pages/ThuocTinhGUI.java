package GUI.pages;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import GUI.DiaLog.DungLuongRamDialog;
import GUI.DiaLog.DungLuongRomDialog;
import GUI.DiaLog.MauSacDialog;
import GUI.DiaLog.ThuongHieuDialog;
import GUI.Frame.Main;
import GUI.Panel.itemTaskbar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThuocTinhGUI extends JPanel {

    JPanel contentCenter;
    Main m;
    public itemTaskbar[] listitem;

    String iconst[] = {"brand", "ram", "rom", "color"};
    String header[] = {"Thương hiệu", "Ram", "Rom", "Màu sắc"};
    Color BackgroundColor = new Color(240, 247, 250);

    public ThuocTinhGUI(Main m) {
        this.m = m;
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[header.length];

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentCenter = new JPanel();
        contentCenter.setBackground(BackgroundColor);
        // Sử dụng GridLayout để lấp đầy toàn bộ panel với 2 hàng, 2 cột
        contentCenter.setLayout(new GridLayout(2, 2, 20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        // Thêm các item vào panel
        for (int i = 0; i < header.length; i++) {
            listitem[i] = new itemTaskbar(iconst[i], header[i], header[i]);
            contentCenter.add(listitem[i]);
        }

        // Xử lý sự kiện chuột cho các item
        listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                ThuongHieuDialog th = new ThuongHieuDialog(getOwnerFrame(), ThuocTinhGUI.this, "Quản lý thương hiệu", true);
                th.setVisible(true);
            }
        });

        listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                DungLuongRamDialog dlram = new DungLuongRamDialog(getOwnerFrame(), ThuocTinhGUI.this, "Quản lý dung lượng RAM", true);
                dlram.setVisible(true);
            }
        });

        listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                DungLuongRomDialog dlrom = new DungLuongRomDialog(getOwnerFrame(), ThuocTinhGUI.this, "Quản lý dung lượng ROM", true);
                dlrom.setVisible(true);
            }
        });

        listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                MauSacDialog mausac = new MauSacDialog(getOwnerFrame(), ThuocTinhGUI.this, "Quản lý màu sắc", true);
                mausac.setVisible(true);
            }
        });
    }

    private JFrame getOwnerFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }
}