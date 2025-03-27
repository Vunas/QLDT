/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.DiaLog;

import GUI.Panel.InputType.InputText;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen
 */
public class PhieuNhapDiaLog extends JDialog{
    JPanel top,center,center_top;
    JTable thongtinchitiet;
    JScrollPane scrollthongtinchitiet;
    DefaultTableModel dftmthongtinchitiet;
    JLabel title;
    InputText maphieunhap,nhanviennhap,nhacungcap;
    
    public PhieuNhapDiaLog(JFrame main  ) {
        super(main);
        initComponent();
        this.setLocationRelativeTo(main);
        this.setVisible(true);
    }
    
    public void initComponent(){
        this.setSize(new Dimension(1100, 500));
        this.setLayout(new BorderLayout(0, 0));

        
        top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBackground(new Color(22, 122, 198));
        top.setPreferredSize(new Dimension(400, 60));

        title = new JLabel("CHI TIẾT PHIẾU NHẬP");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        
        center = new JPanel();
        center.setLayout(new BorderLayout());
        
        thongtinchitiet = new JTable();
        scrollthongtinchitiet = new JScrollPane();
        dftmthongtinchitiet = new DefaultTableModel();
        String[] header = new String[]{"Mã SP", "Tên SP", "RAM", "ROM", "Màu sắc", "Đơn giá", "Số lượng"};
        dftmthongtinchitiet.setColumnIdentifiers(header);
        thongtinchitiet.setModel(dftmthongtinchitiet);
        thongtinchitiet.setFocusable(false);
        scrollthongtinchitiet.setViewportView(thongtinchitiet);
        
        
        center_top = new JPanel();
        center_top.setLayout(new GridLayout(1,3));
        

        maphieunhap = new InputText("MÃ PHIẾU NHẬP");
        maphieunhap.setEditable(false);
        nhanviennhap = new InputText("NHÂN VIÊN NHẬP");
        nhanviennhap.setEditable(false);
        nhacungcap = new InputText("NHÀ CUNG CẤP");
        nhacungcap.setEditable(false);
        
        center_top.add(maphieunhap);
        center_top.add(nhanviennhap);
        center_top.add(nhacungcap);

        center.add(center_top,BorderLayout.NORTH);
        center.add(scrollthongtinchitiet,BorderLayout.CENTER);

        
        top.add(title,BorderLayout.CENTER);
        this.add(top,BorderLayout.NORTH);
        this.add(center,BorderLayout.CENTER);
        
    }
    
    
    
}
