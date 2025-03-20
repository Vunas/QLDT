package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import GUI.Frame.Main;
import GUI.SideBar.KhachHangGUI;
import GUI.SideBar.KhoHangGui;
import GUI.SideBar.NhaCungCapGUI;
import GUI.SideBar.NhanVienGUI;
import GUI.SideBar.QuyenGUI;
import GUI.SideBar.TaiKhoanGUI;
import GUI.SideBar.TrangChuGUI;

public class SideBar extends JPanel {
    Main main;
    JPanel pnlTop, pnlMid, pnlBot;
    ItemBar[] itemBars;
    String[] menuBars = {"Trang chủ", "Sản phẩm", "Thuộc tính", "Khu vực kho", 
                     "Phiếu nhập", "Phiếu xuất", "Khách hàng", "Nhà cung cấp", 
                     "Nhân viên", "Tài khoản", "Thống kê", "Phân quyền"};

    String[] icons ={"home","phone","del","place","home","user","home","user","home","account","home","protect"};
    int thisPage= 0;

    Color mainColor = Color.GRAY;

    public SideBar(Main main) {
        initComponent(main);
    }

    private void initComponent(Main main) {
        this.main= main;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(190, 200));

        // Top panel
        pnlTop = new JPanel();
        pnlTop.setBackground(new Color(64, 64, 64));  // Màu tối cho top panel
        pnlTop.setPreferredSize(new Dimension(230, 120));

        JLabel lblAvatar = new JLabel(new ImageIcon("path/to/avatar/image"));  // Thay đổi đường dẫn ảnh avatar
        JLabel lblName = new JLabel("");
        JLabel lblTitle = new JLabel("Quản lý kho");
        lblName.setForeground(Color.WHITE);
        lblTitle.setForeground(Color.LIGHT_GRAY);

        pnlTop.add(lblAvatar);
        pnlTop.add(lblName);
        pnlTop.add(lblTitle);
        pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.Y_AXIS));

        // Middle panel
        pnlMid = new JPanel();
        pnlMid.setPreferredSize(new Dimension(230, 600));
        pnlMid.setBackground(Color.WHITE);
        pnlMid.setLayout(new FlowLayout(0, 0, 5));
        pnlMid.setBorder(BorderFactory.createEmptyBorder(5 , 5, 5, 5));

        itemBars= new ItemBar[menuBars.length];
        for (int i=0; i< menuBars.length; i++) {
            itemBars[i] = new ItemBar(menuBars[i],icons[i]);
            pnlMid.add(itemBars[i]);
        }
        itemBars[0].setBackground(mainColor);

        // Bottom panel
        pnlBot = new JPanel();
        pnlBot.setBackground(Color.WHITE);  

        ItemBar logoutItem= new ItemBar("Đăng xuất","logout");
      

        pnlBot.add(logoutItem,Label.BOTTOM_ALIGNMENT);
        // pnlBot.setLayout(new BoxLayout(pnlBot, BoxLayout.Y_AXIS));

        setBorder(new MatteBorder(0, 0, 0, 1, Color.GRAY));
        setBackground(Color.BLACK);
        // Add panels to the SideNavBar
        add(pnlTop, BorderLayout.NORTH);
        add(pnlMid, BorderLayout.CENTER);
        add(pnlBot, BorderLayout.SOUTH);

        itemBars[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new TrangChuGUI());
                changePage(0);
            }
        });

        itemBars[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new KhachHangGUI());
                changePage(1);
            }
        });

        itemBars[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new KhachHangGUI());
                changePage(2);
            }
        });
        
        itemBars[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new KhoHangGui());
                changePage(3);
            }
        });

        itemBars[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new KhachHangGUI());
                changePage(4);
            }
        });

        itemBars[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new KhachHangGUI());
                changePage(5);
            }
        });

        itemBars[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new KhachHangGUI());
                changePage(6);
            }
        });

        itemBars[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new NhaCungCapGUI());
                changePage(7);
            }
        });

        itemBars[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new NhanVienGUI());
                changePage(8);
            }
        });

        itemBars[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new TaiKhoanGUI());
                changePage(9);
            }
        });

        itemBars[10].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new KhachHangGUI());
                changePage(10);
            }
        });

        itemBars[11].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                main.setPanel(new QuyenGUI());
                changePage(11);
            }
        });
    
    }

    private void changePage(int i){
        itemBars[thisPage].setBackground(Color.WHITE);
        itemBars[thisPage].iselected = false;
        thisPage= i;
        itemBars[i].setBackground(mainColor);
        itemBars[i].iselected= true;
    }
    
}
