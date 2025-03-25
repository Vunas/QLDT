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

-- -- Bảng Kho Hàng
-- CREATE TABLE kho_hang (
--     maKho INT PRIMARY KEY AUTO_INCREMENT,
--     tenKho VARCHAR(255) NOT NULL,
--     diaChi VARCHAR(255) NOT NULL
-- );

-- INSERT INTO kho_hang (tenKho, diaChi) VALUES
-- ('Kho Chính', '123 Đường A, Quận 1, TP. HCM'),
-- ('Kho Hà Nội', '456 Đường B, Ba Đình, Hà Nội'),
-- ('Kho Đà Nẵng', '789 Đường C, Hải Châu, Đà Nẵng');

CREATE TABLE quyen (
    maQuyen INT AUTO_INCREMENT PRIMARY KEY, -- Mã quyền (Khóa chính, tự tăng)
    tenQuyen VARCHAR(100) NOT NULL,        -- Tên quyền (Không được để trống)
    danhSachChucNang VARCHAR(100)                  -- Danh sách chức năng (Chuỗi dài, chứa các chức năng liên kết)
);

INSERT INTO quyen (maQuyen, tenquyen, danhSachChucNang) VALUES
('1', 'admin', 'rcfd /rcfd /rcfd /rcfd /rcfd /rcfd /rcfd /rcfd /rcfd /rcfd /'),
('2', 'quản lý', 'rcfd / / /rcfd /rcfd /rcfd / / / / /'),
('3', 'nhân viên','rcfd / / /rcfd / / / / / / /');

-- Bảng Tài khoản
CREATE TABLE TaiKhoan (
    MaNV INT PRIMARY KEY,   -- Mã nhân viên (khóa chính)
    TenDangNhap VARCHAR(50), -- Tên đăng nhập
    MatKhau VARCHAR(100),    -- Mật khẩu
    MaQuyen INT,             -- Mã quyền
    
    -- Định nghĩa khóa ngoại
    CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV),
    CONSTRAINT FK_TaiKhoan_Quyen FOREIGN KEY (MaQuyen) REFERENCES Quyen(MaQuyen)
);


INSERT INTO TaiKhoan (MaNV, TenDangNhap, MatKhau, MaQuyen) VALUES 
(1, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1),
(2, 'quanly', '34a378e876712e095ed1059a4ee44ea2ac7c93831dd4f58e5e980853a6b7999e', 2),
(3, 'nhanvien', 'fa5a1d3e67d2193b86bc68c7db41bd84f242fe4e41146ef4a4a5441254d2a3f7', 3);

CREATE TABLE SanPham (
    maSP INT PRIMARY KEY,
    tenSP VARCHAR(50),
    img VARCHAR(100),
    soLuong INT,
    giaNhap INT,
    giaBan INT,
    mauSac VARCHAR(100),
    thuongHieu VARCHAR(100),
    Ram INT,
    Rom INT,
    Chip VARCHAR(100),
    thoiGianBaoHanh FLOAT
);

INSERT INTO SanPham (maSP, tenSP, img, soLuong, giaNhap, giaBan, mauSac, thuongHieu, Ram, Rom, Chip, thoiGianBaoHanh)
VALUES
(1, 'iPhone 14 Pro Max','src/resources/img/phone7.jpeg', 10, 25000000, 28000000, 'Đen', 'Apple', 6, 128, 'A16 Bionic', 12),
(2, 'Samsung Galaxy S23 Ultra','src/resources/img/phone4.jpeg', 8, 23000000, 26000000, 'Xanh', 'Samsung', 12, 256, 'Snapdragon 8 Gen 2', 12),
(3, 'Xiaomi 13 Pro','src/resources/img/phone6.jpeg', 15, 18000000, 20000000, 'Trắng', 'Xiaomi', 12, 256, 'Snapdragon 8 Gen 2', 12),
(4, 'Oppo Find X5 Pro','src/resources/img/phone8.jpeg', 12, 19000000, 22000000, 'Đen', 'Oppo', 8, 256, 'Snapdragon 8 Gen 1', 12),
(5, 'Vivo X90 Pro','src/resources/img/phone9.jpeg', 7, 17000000, 19500000, 'Đỏ', 'Vivo', 12, 512, 'Dimensity 9200', 12),
(6, 'Realme GT 3','src/resources/img/phone2.jpeg', 20, 12000000, 14000000, 'Tím', 'Realme', 8, 256, 'Snapdragon 8+ Gen 1', 12),
(7, 'Google Pixel 7 Pro','src/resources/img/phone9.jpeg', 6, 21000000, 24000000, 'Xám', 'Google', 12, 256, 'Google Tensor G2', 12),
(8, 'iPhone 13','src/resources/img/phone1.jpeg', 18, 19000000, 22000000, 'Xanh', 'Apple', 4, 128, 'A15 Bionic', 12),
(9, 'Samsung Galaxy Z Flip4','src/resources/img/phone3.jpeg', 10, 22000000, 25000000, 'Hồng', 'Samsung', 8, 256, 'Snapdragon 8+ Gen 1', 12),
(10, 'Xiaomi Redmi Note 12 Pro','src/resources/img/phone4.jpg', 25, 8000000, 10000000, 'Xanh dương', 'Xiaomi', 8, 128, 'Dimensity 1080', 12);
