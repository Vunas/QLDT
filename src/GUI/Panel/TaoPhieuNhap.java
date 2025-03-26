/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import GUI.Panel.InputType.ButtonCustom;
import GUI.Panel.InputType.InputText;
import GUI.Panel.InputType.SelectForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nguyen
 */
public class TaoPhieuNhap extends JPanel{
     JPanel left,right,luachonsanpham,thongtinsanpham,luachonsanpham_left,right_top,right_bottom,tongsotien;
     JTable sanphamTable,sanphamdathemTable;
     JLabel tongTien,sotien;
     DefaultTableModel tbmsanphamTable,tbmsanphamdathemTable;
     JScrollPane scrollsanphamTable,scrollsanphamdathemTable;
     InputText masptxt,tensptxt,gianhaptxt,maimeitxt,soluongtxt,maphieunhaptxt,nhanviennhaptxt;
     JTextField timkiemsptxt;
     SelectForm cbxcauhinh,cbxnhacungcap;
     ButtonCustom addsp,nhaphang;

    public TaoPhieuNhap() {
        initComponent();
    }
    
    public void initComponent(){
        this.setSize(600, 600);
        this.setLayout(new BorderLayout(2, 2));
       
       left = new JPanel();
       left.setLayout(new BorderLayout(10,10));
       
       
       
       right = new JPanel();
       right.setLayout(new BorderLayout());
       right.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
       right.setPreferredSize(new Dimension(250,500));
       right.setBackground(Color.white);
       

       right_top = new JPanel();
       right_top.setLayout(new FlowLayout());
       
       maphieunhaptxt = new InputText("Mã phiếu nhập");
       maphieunhaptxt.setPreferredSize(new Dimension(250,80));
       maphieunhaptxt.setBackground(Color.white);
       nhanviennhaptxt = new InputText("Mã NV nhập");
       nhanviennhaptxt.setPreferredSize(new Dimension(250,80));
       nhanviennhaptxt.setBackground(Color.white);
       String[] arrnhacungcap = {"Chọn nhà cung cấp"};
       cbxnhacungcap = new SelectForm("Nhà cung cấp", arrnhacungcap);
       cbxnhacungcap.setPreferredSize(new Dimension(250,50));
       cbxnhacungcap.setBackground(Color.white);
      
       right_bottom = new JPanel();
       right_bottom.setLayout(new BorderLayout(5,5));
       
       nhaphang = new ButtonCustom("Nhập hàng","success",14);
       nhaphang.setPreferredSize(new Dimension(250,50));
       
       
       tongsotien = new JPanel();
       tongTien = new JLabel("Tổng tiền:");
       tongTien.setFont(new Font("Arial", 1, 18));
       tongTien.setForeground(new Color(255, 51, 51));
       sotien = new JLabel("0đ");
       sotien.setFont(new Font("Arial", 1, 18));
       tongsotien.add(tongTien);
       tongsotien.add(sotien);
       
       
       right_bottom.add(tongsotien,BorderLayout.CENTER);
       right_bottom.add(nhaphang,BorderLayout.SOUTH);
       
       right_top.add(maphieunhaptxt);
       right_top.add(nhanviennhaptxt);
       right_top.add(cbxnhacungcap);
       
       right.add(right_top);
       right.add(right_bottom,BorderLayout.SOUTH);
       
       luachonsanpham = new JPanel();
       luachonsanpham.setLayout(new GridLayout(1,2));
       luachonsanpham_left = new JPanel();
       luachonsanpham_left.setLayout(new BorderLayout());
       
       timkiemsptxt = new JTextField();
       timkiemsptxt.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
       
       addsp = new ButtonCustom("Thêm sản phẩm", "success", 14);
       
        
        sanphamTable = new JTable();
        tbmsanphamTable = new DefaultTableModel();
        scrollsanphamTable = new JScrollPane();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm", "Số lượng tồn"};
        tbmsanphamTable.setColumnIdentifiers(headerSP);
        sanphamTable.setModel(tbmsanphamTable);
        scrollsanphamTable.setViewportView(sanphamTable);
        scrollsanphamTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        sanphamTable.setFocusable(false);
        
        
        sanphamdathemTable = new JTable();
        tbmsanphamdathemTable = new DefaultTableModel();
        scrollsanphamdathemTable = new JScrollPane();
        String[] headerSPdathem = new String[]{"Mã SP", "Tên sản phẩm", "Ram", "Rom", "Màu sắc","Đơn giá","Số lượng"};
        tbmsanphamdathemTable.setColumnIdentifiers(headerSPdathem);
        sanphamdathemTable.setModel(tbmsanphamdathemTable);
        scrollsanphamdathemTable.setViewportView(sanphamdathemTable);
        sanphamdathemTable.setFocusable(false);
        scrollsanphamdathemTable.setPreferredSize(new Dimension(350, 250));
        scrollsanphamdathemTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));

        
        
        thongtinsanpham = new JPanel();
        thongtinsanpham.setLayout(new FlowLayout());
        thongtinsanpham.setBackground(Color.white);
        thongtinsanpham.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        String[] arrcauhinh = {"Chọn sản phẩm"};
        cbxcauhinh = new SelectForm("Cấu hình", arrcauhinh);
        cbxcauhinh.setBackground(Color.white);
        masptxt = new InputText("Mã sản phẩm");
        masptxt.setBackground(Color.white);
        tensptxt = new InputText("Tên sản phẩm");
        tensptxt.setBackground(Color.white);
        tensptxt.setPreferredSize(new Dimension(250,75));
        gianhaptxt = new InputText("Giá nhập");
        gianhaptxt.setBackground(Color.white);
        gianhaptxt.setPreferredSize(new Dimension(250,75));
        maimeitxt = new InputText("Mã imei");
        maimeitxt.setBackground(Color.white);
        maimeitxt.setPreferredSize(new Dimension(250,75));
        soluongtxt = new InputText("Số lượng");
        soluongtxt.setBackground(Color.white);
        thongtinsanpham.add(masptxt);
        thongtinsanpham.add(tensptxt);
        thongtinsanpham.add(cbxcauhinh);
        thongtinsanpham.add(gianhaptxt);
        thongtinsanpham.add(maimeitxt);
        thongtinsanpham.add(soluongtxt);
        
        
        luachonsanpham_left.add(timkiemsptxt,BorderLayout.NORTH);
        luachonsanpham_left.add(scrollsanphamTable,BorderLayout.CENTER);
        luachonsanpham_left.add(addsp,BorderLayout.SOUTH);
        
        luachonsanpham.add(luachonsanpham_left);
        luachonsanpham.add(thongtinsanpham);
        left.add(luachonsanpham,BorderLayout.CENTER);
        left.add(scrollsanphamdathemTable,BorderLayout.SOUTH);
        
        
        this.add(left,BorderLayout.CENTER);
        this.add(right,BorderLayout.EAST);
        this.setVisible(true);
    }
    
    
    
}
