package GUI.DiaLog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BLL.BUS.KhuyenMaiBLL;
import DTO.KhuyenMaiDTO;
import GUI.Panel.InputType.InputDate;
import GUI.Panel.InputType.InputText;

public class ThemKhuyenMaiDiaLog extends JDialog {
    private boolean isSaved = false;
    KhuyenMaiBLL khuyenMaiBLL;
    KhuyenMaiDTO selectedKhuyenMaiDTO;
    int tongTienHoaDon;
    int selectedindex;

    public ThemKhuyenMaiDiaLog(Frame owner, KhuyenMaiDTO khuyenMaiDTO, String title) {
        super(owner, title, true);
        khuyenMaiBLL = new KhuyenMaiBLL();
        khuyenMaiBLL.autoUpdateTrangThai(khuyenMaiBLL.getAllKhuyenMai());
        initComponents(khuyenMaiDTO, title);
        setLocationRelativeTo(owner);
    }

    public void setTongTienHoaDon(int tongTienHoaDon) {
        this.tongTienHoaDon = tongTienHoaDon;
    }

    private void initComponents(KhuyenMaiDTO khuyenMaiDTO, String title) {
        setSize(400, 550);
        setLayout(new BorderLayout(10, 10));

        JLabel lbContent = new JLabel(title);
        lbContent.setFont(new Font(getName(), Font.BOLD, 20));
        lbContent.setBorder(new EmptyBorder(10, 0, 10, 0));
        // lbContent.setForeground(new java.awt.Color(0, 102, 204)); // Màu xanh nổi bật
        JPanel pnlContent = new JPanel();
        pnlContent.add(lbContent, CENTER_ALIGNMENT);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        // pnlMain.setPreferredSize(new Dimension(400, 500));

        ButtonGroup btnGroup = new ButtonGroup();
        JRadioButton[] radioButton = new JRadioButton[khuyenMaiBLL.getAllKhuyenMai().size()];

        int index = 0;
        for (KhuyenMaiDTO dto : khuyenMaiBLL.getAllKhuyenMai()) {
            JPanel pnlKM = new JPanel();
            pnlKM.setLayout(new BoxLayout(pnlKM, BoxLayout.Y_AXIS));
            pnlKM.setBorder(new EmptyBorder(10, 10, 10, 10));
            // pnlKM.setBackground(new java.awt.Color(240, 248, 255)); // Màu nền nhạt
            radioButton[index] = new JRadioButton(dto.getTenKM());
            radioButton[index].setFont(new Font(getName(), Font.BOLD, 20));
            // radioButton[index].setBackground(new java.awt.Color(240, 248, 255));
            JLabel lbMoTa = new JLabel(dto.getMota());
            lbMoTa.setFont(new Font(getName(), Font.PLAIN, 14));
            btnGroup.add(radioButton[index]);
            pnlKM.add(radioButton[index]);
            pnlKM.add(lbMoTa);
            pnlMain.add(pnlKM);
            pnlMain.add(new JLabel(" "));
            index++;
        }

        JScrollPane scrollPane = new JScrollPane(pnlMain, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel pnlOK = new JPanel();
        JButton btnOK = new JButton("Xác Nhận");
        btnOK.setPreferredSize(new Dimension(150, 40));
        btnOK.setFont(new Font(getName(), Font.BOLD, 20));
        btnOK.setBackground(new Color(100, 149, 237));
        btnOK.setForeground(java.awt.Color.WHITE);
        btnOK.setFocusPainted(false);
        btnOK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                selectedindex = -1;
                boolean ktrKhongHopLe = false;
                for (int i = 0; i < radioButton.length; i++) {
                    if (radioButton[i].isSelected()) {
                        if (checkKM(i, tongTienHoaDon)) {
                            selectedindex = i;
                            isSaved = true;
                            selectedKhuyenMaiDTO = khuyenMaiBLL.getAllKhuyenMai().get(selectedindex);
                            JOptionPane.showMessageDialog(null, "Thêm Khuyến Mãi Thành Công", "Thông Báo",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        } else {
                            ktrKhongHopLe = true;
                        }
                    }
                }
                if (selectedindex == -1 && ktrKhongHopLe) {
                    JOptionPane.showMessageDialog(null, "Không Đủ Điều Kiện Áp Dụng Khuyến Mãi", "Thông Báo",
                            JOptionPane.WARNING_MESSAGE);
                } else if(selectedindex == -1){
                    JOptionPane.showMessageDialog(null, "Chưa Chọn Khuyến Mãi Nào", "Thông Báo",
                            JOptionPane.WARNING_MESSAGE);
                }
                dispose();
            }

        });

        pnlOK.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlOK.add(btnOK);
        this.add(pnlContent, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(pnlOK, BorderLayout.SOUTH);
        this.setResizable(false);
    }

    public boolean isSaved() {
        return isSaved;
    }

    public KhuyenMaiDTO getSelectedKhuyenMaiDTO() {
        return selectedKhuyenMaiDTO;
    }

    public int getSelectedindex() {
        return selectedindex;
    }

    public boolean checkKM(int index, int tong) {
        return tong >= khuyenMaiBLL.getAllKhuyenMai().get(index).getApDungChoHoaDonTu();
    }
}
