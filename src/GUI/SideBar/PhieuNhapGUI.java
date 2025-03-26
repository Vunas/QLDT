/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.SideBar;

import GUI.DiaLog.PhieuNhapDiaLog;
import GUI.Frame.Main;
import GUI.Panel.TaoPhieuNhap;
import GUI.Panel.TopNav;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

/**
 *
 * @author nguyen
 */
public class PhieuNhapGUI extends JPanel{
    TopNav topNav;
    JPanel pnlBot;
    JTable tbl;
    TaoPhieuNhap pn;
    Main main;

    public PhieuNhapGUI(Main main) {
        initComponent(main);
        chucNang();
    }
    
    public void initComponent(Main main){
        this.main =  main;
        String[] itemFindFor ={"Tất Cả","Mã Phiếu Nhập","Nhà Cung Cấp","Nhân Viên Nhập"};
        
        topNav = new TopNav();
        topNav.setItemComboBox(itemFindFor);
        
        pnlBot = new JPanel(new BorderLayout());
        pnlBot.setPreferredSize(new Dimension(0, 500));
        
        tbl = new JTable();
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setRowHeight(35);
        tbl.setFocusable(false);
        
        JTableHeader header = tbl.getTableHeader();
        header.setPreferredSize(new Dimension(0, 40));
        header.setBackground(new Color(100, 149, 237));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(tbl);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        pnlBot.setLayout(new BorderLayout());
        pnlBot.setBorder(new EmptyBorder(10, 15, 10, 15));
        pnlBot.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(topNav, BorderLayout.NORTH);
        this.add(pnlBot, BorderLayout.CENTER);
        
    }
    
   private void chucNang(){
    JButton[] btn = topNav.getBtn();
    btn[1].setVisible(false);
    btn[0].addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            main.setPanel(new TaoPhieuNhap());
        }
    });
     btn[3].addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            PhieuNhapDiaLog pndialog = new PhieuNhapDiaLog(main);

        }
    });
}

    
    
    
}
