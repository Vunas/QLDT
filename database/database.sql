-- Bảng Khách hàng
CREATE TABLE khach_hang (
    maKH INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(50),
    diaChi VARCHAR(100),
    sdt VARCHAR(15),
    email VARCHAR(50)
);

INSERT INTO khach_hang (ten, diaChi, sdt, email) VALUES 
('Nguyen Van A', '123 Đường ABC, Hà Nội', '0901234567', 'nguyenvana@example.com'),
('Tran Thi B', '456 Đường DEF, TP.HCM', '0912345678', 'tranthib@example.com'),
('Le Van C', '789 Đường GHI, Đà Nẵng', '0923456789', 'levanc@example.com'),
('Pham Thi D', '321 Đường JKL, Hải Phòng', '0934567890', 'phamthid@example.com'),
('Hoang Van E', '654 Đường MNO, Cần Thơ', '0945678901', 'hoangvane@example.com'),
('Vu Thi F', '987 Đường PQR, Huế', '0956789012', 'vuthif@example.com'),
('Nguyen Van G', '147 Đường STU, Biên Hòa', '0967890123', 'nguyenvang@example.com'),
('Tran Thi H', '258 Đường VWX, Vũng Tàu', '0978901234', 'tranthih@example.com'),
('Le Van I', '369 Đường YZ, Nha Trang', '0989012345', 'levani@example.com'),
('Pham Thi J', '741 Đường ABCD, Buôn Ma Thuột', '0990123456', 'phamthij@example.com');

-- Bảng Nhân viên
CREATE TABLE NhanVien (
    MaNV INT PRIMARY KEY,
    HoTen VARCHAR(100),
    NgaySinh DATE,
    GioiTinh INT,
    SDT VARCHAR(15)
);

INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SDT) VALUES 
(1, 'Nguyen Van A', '1990-01-01', 1, '0901234567'),
(2, 'Tran Thi B', '1985-03-10', 0, '0912345678'),
(3, 'Le Van C', '1992-07-20', 1, '0938765432'),
(4, 'Pham Minh D', '1988-09-15', 1, '0981234567');

-- Bảng Nhà cung cấp
CREATE TABLE nha_cung_cap (
    maNCC INT PRIMARY KEY AUTO_INCREMENT,
    ten VARCHAR(255) NOT NULL,
    diaChi VARCHAR(255) NOT NULL,
    sdt VARCHAR(10) NOT NULL
);

INSERT INTO nha_cung_cap (ten, diaChi, sdt) VALUES
('Công Ty TNHH ABC', '123 Đường A, Quận 1, TP. HCM', '0912345678'),
('Công Ty CP DEF', '456 Đường B, Quận 2, TP. HCM', '0923456789'),
('Nhà Cung Cấp GHI', '789 Đường C, Hà Nội', '0934567890'),
('Công Ty TNHH JKL', '321 Đường D, Đà Nẵng', '0945678901');

-- Bảng Kho Hàng
CREATE TABLE kho_hang (
    maKho INT PRIMARY KEY AUTO_INCREMENT,
    tenKho VARCHAR(255) NOT NULL,
    diaChi VARCHAR(255) NOT NULL
);

INSERT INTO kho_hang (tenKho, diaChi) VALUES
('Kho Chính', '123 Đường A, Quận 1, TP. HCM'),
('Kho Hà Nội', '456 Đường B, Ba Đình, Hà Nội'),
('Kho Đà Nẵng', '789 Đường C, Hải Châu, Đà Nẵng');

CREATE TABLE quyen (
    maQuyen INT AUTO_INCREMENT PRIMARY KEY, -- Mã quyền (Khóa chính, tự tăng)
    tenQuyen VARCHAR(100) NOT NULL,        -- Tên quyền (Không được để trống)
    danhSachChucNang VARCHAR(100)                  -- Danh sách chức năng (Chuỗi dài, chứa các chức năng liên kết)
);

-- Bảng Tài khoản
CREATE TABLE TaiKhoan (
    MaNV INT PRIMARY KEY,          -- Mã nhân viên (khoá chính)
    TenDangNhap VARCHAR(50),       -- Tên đăng nhập
    MatKhau VARCHAR(100),          -- Mật khẩu
    MaQuyen INT,                   -- Mã quyền
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV)
    FOREIGN Key (MaQuyen) REFERENCES quyen(MaQuyen)
);

INSERT INTO TaiKhoan (MaNV, TenDangNhap, MatKhau, MaQuyen) VALUES 
(1, 'nv001', '123456', 1),
(2, 'nv002', 'qwerty', 2),
(3, 'nv003', 'abcdef', 2),
(4, 'nv004', '123abc', 3);

