CREATE TABLE khach_hang (
    maKH INT PRIMARY KEY Auto_Increment,
    ten VARCHAR(50),
    diaChi VARCHAR(100),
    sdt VARCHAR(15),
    email VARCHAR(50)
);
INSERT INTO khach_hang (maKH, ten, diaChi, sdt, email) VALUES 
(1, 'Nguyen Van A', '123 Đường ABC, Hà Nội', '0901234567', 'nguyenvana@example.com'),
(2, 'Tran Thi B', '456 Đường DEF, TP.HCM', '0912345678', 'tranthib@example.com'),
(3, 'Le Van C', '789 Đường GHI, Đà Nẵng', '0923456789', 'levanc@example.com'),
(4, 'Pham Thi D', '321 Đường JKL, Hải Phòng', '0934567890', 'phamthid@example.com'),
(5, 'Hoang Van E', '654 Đường MNO, Cần Thơ', '0945678901', 'hoangvane@example.com'),
(6, 'Vu Thi F', '987 Đường PQR, Huế', '0956789012', 'vuthif@example.com'),
(7, 'Nguyen Van G', '147 Đường STU, Biên Hòa', '0967890123', 'nguyenvang@example.com'),
(8, 'Tran Thi H', '258 Đường VWX, Vũng Tàu', '0978901234', 'tranthih@example.com'),
(9, 'Le Van I', '369 Đường YZ, Nha Trang', '0989012345', 'levani@example.com'),
(10, 'Pham Thi J', '741 Đường ABCD, Buôn Ma Thuột', '0990123456', 'phamthij@example.com'),
(11, 'Hoang Van K', '852 Đường EFGH, Pleiku', '0912345670', 'hoangvank@example.com'),
(12, 'Vu Thi L', '963 Đường IJKL, Vinh', '0923456781', 'vuthil@example.com'),
(13, 'Nguyen Van M', '174 Đường MNOP, Thanh Hóa', '0934567892', 'nguyenvanm@example.com'),
(14, 'Tran Thi N', '285 Đường QRST, Nam Định', '0945678903', 'tranthin@example.com'),
(15, 'Le Van O', '396 Đường UVWX, Bắc Ninh', '0956789014', 'levano@example.com');

-- Bảng Nhân viên

CREATE TABLE NhanVien (
    MaNV INT PRIMARY KEY,
    HoTen VARCHAR(100),
    NgaySinh DATE,
    GioiTinh INT,
    SDT VARCHAR(15)
);

INSERT INTO NhanVien (MaNV, HoTen, NgaySinh, GioiTinh, SDT)
VALUES 
(2, 'Tran Thi B', '1985-03-10', 0, '0912345678'),
(3, 'Le Van C', '1992-07-20', 1, '0938765432'),
(4, 'Pham Minh D', '1988-09-15', 1, '0981234567'),
(5, 'Nguyen Thi E', '1995-12-25', 0, '0976543210'),
(6, 'Le Van F', '1990-06-30', 1, '0909876543'),
(7, 'Tran Hoang G', '1987-04-18', 1, '0932456789'),
(8, 'Do Thi H', '1993-07-09', 0, '0913456789'),
(9, 'Hoang Van I', '1998-11-01', 1, '0945678910'),
(10, 'Vo Minh K', '1985-02-15', 1, '0964567890'),
(11, 'Bui Thi L', '1991-10-21', 0, '0923456781'),
(12, 'Ngo Van M', '1983-03-14', 1, '0951234567'),
(13, 'Phan Thi N', '1997-05-29', 0, '0931234568'),
(14, 'Tran Van O', '1986-08-20', 1, '0902345679'),
(15, 'Vu Thi P', '1992-09-10', 0, '0974561234'),
(16, 'Dang Van Q', '1994-01-23', 1, '0941235678'),
(17, 'Ly Thi R', '1990-06-17', 0, '0907891234'),
(18, 'Cao Van S', '1989-07-03', 1, '0912349876');


-- Bảng tài khoản

CREATE TABLE TaiKhoan (
    MaNV INT PRIMARY KEY,          -- Mã nhân viên (khoá chính)
    TenDangNhap VARCHAR(50),      -- Tên đăng nhập
    MatKhau VARCHAR(100),         -- Mật khẩu
    MaQuyen INT,                   -- Mã quyền (1: quản lý, 2: nhân viên, 3: admin)
    FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) -- Khoá ngoại liên kết tới bảng NhânVien
);


INSERT INTO TaiKhoan (MaNV, TenDangNhap, MatKhau, MaQuyen)
VALUES 
(1, 'nv001', '123456', 1),
(2, 'nv002', 'qwerty', 2),
(3, 'nv003', 'abcdef', 2),
(4, 'nv004', '123abc', 3),
(5, 'nv005', '123qwe', 2),
(6, 'nv006', 'securepwd', 1),
(7, 'nv007', 'admin@123', 3),
(8, 'nv008', 'hello2023', 2),
(9, 'nv009', 'simplepwd', 2),
(10, 'nv010', 'password1', 2),
(11, 'nv011', 'mypass123', 2),
(12, 'nv012', 'letmein', 2),
(13, 'nv013', 'goodpass', 1),
(14, 'nv014', 'welcomepwd', 3),
(15, 'nv015', 'mypassword', 2);


-- Bảng nhà cung cấp

CREATE TABLE nha_cung_cap (
    maNCC INT PRIMARY KEY AUTO_INCREMENT, -- Mã nhà cung cấp, tự tăng
    ten VARCHAR(255) NOT NULL,            -- Tên nhà cung cấp, không được để trống
    diaChi VARCHAR(255) NOT NULL,         -- Địa chỉ, không được để trống
    sdt VARCHAR(10) NOT NULL              -- Số điện thoại, không được để trống
);


INSERT INTO nha_cung_cap (maNCC, ten, diaChi, sdt) VALUES
(1, 'Công Ty TNHH ABC', '123 Đường A, Quận 1, TP. HCM', '0912345678'),
(2, 'Công Ty CP DEF', '456 Đường B, Quận 2, TP. HCM', '0923456789'),
(3, 'Nhà Cung Cấp GHI', '789 Đường C, Hà Nội', '0934567890'),
(4, 'Công Ty TNHH JKL', '321 Đường D, Đà Nẵng', '0945678901'),
(5, 'Công Ty CP MNO', '654 Đường E, Nha Trang', '0956789012'),
(6, 'Nhà Cung Cấp PQR', '987 Đường F, Hải Phòng', '0967890123'),
(7, 'Công Ty TNHH STU', '135 Đường G, Cần Thơ', '0978901234'),
(8, 'Công Ty CP VWX', '246 Đường H, Đà Lạt', '0989012345'),
(9, 'Nhà Cung Cấp YZ', '369 Đường I, Huế', '0990123456'),
(10, 'Công Ty TNHH AAA', '579 Đường J, Bình Dương', '0901234567');

-- Bảng Kho Hàng

CREATE TABLE kho_hang (
    maKho INT PRIMARY KEY AUTO_INCREMENT, -- Mã kho, tự tăng
    tenKho VARCHAR(255) NOT NULL,         -- Tên kho, không được để trống
    diaChi VARCHAR(255) NOT NULL          -- Địa chỉ kho, không được để trống
);

INSERT INTO kho_hang (tenKho, diaChi) VALUES
('Kho Chính', '123 Đường A, Quận 1, TP. HCM'),
('Kho Hà Nội', '456 Đường B, Ba Đình, Hà Nội'),
('Kho Đà Nẵng', '789 Đường C, Hải Châu, Đà Nẵng'),
('Kho Nha Trang', '321 Đường D, TP. Nha Trang'),
('Kho Hải Phòng', '654 Đường E, Lê Chân, Hải Phòng');


