# 🧩 TỔNG HỢP CÁC DỰ ÁN MINI BẰNG JAVA THEO MÔ HÌNH MVC

## 📘 Giới thiệu chung

Ngày nay, khi xây dựng các ứng dụng phần mềm, đặc biệt là trong học tập và thực tế công việc, việc tổ chức mã nguồn một cách **khoa học** là điều vô cùng quan trọng. Mô hình **MVC (Model - View - Controller)** là một trong những kiến trúc phổ biến giúp phân tách rõ ràng **logic xử lý**, **dữ liệu** và **giao diện người dùng**, giúp ứng dụng dễ bảo trì, nâng cấp và mở rộng.

Trong bài viết này, chúng tôi trình bày các **dự án mini bằng Java**, tuân thủ mô hình MVC, sử dụng các lớp như `I_List`, `Utils`, `MountainStatistic`,... Dự án được thiết kế gọn nhẹ, dễ hiểu, rất phù hợp để học và thực hành lập trình hướng đối tượng.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## ⚙️ 1. Kiến trúc tổng quát theo mô hình MVC

Mỗi dự án được thiết kế tuân thủ cấu trúc như sau:

```
├── model/        (các lớp dữ liệu, nghiệp vụ)
├── controller/   (xử lý logic, điều phối dữ liệu và giao diện)
├── view/         (tầng giao diện người dùng - console)
├── interface/    (các interface trừu tượng hóa)
├── utils/        (hàm tiện ích dùng chung)
```

| Tầng MVC       | Vai trò                                                               |
| -------------- | --------------------------------------------------------------------- |
| **Model**      | Đại diện cho dữ liệu và logic nghiệp vụ (ví dụ: Mountain, Order, Car) |
| **View**       | Giao diện người dùng: nhập dữ liệu, hiển thị danh sách, menu lựa chọn |
| **Controller** | Xử lý yêu cầu từ người dùng, thao tác dữ liệu từ Model và gọi View    |

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🏔️ 2. Dự Án 1: Thống Kê Các Ngọn Núi (Mountain Statistic)

### 🎯 Mục tiêu

Xây dựng một ứng dụng console giúp người dùng **quản lý danh sách các ngọn núi**, đồng thời **thực hiện thống kê** như tìm ngọn núi cao nhất, trung bình độ cao, lọc núi theo quốc gia...

### 🧱 Cấu trúc dự án

* **Model**:

  * `Mountain`: gồm tên, độ cao, quốc gia
  * `I_List<T>`: Interface quản lý danh sách kiểu generic, hỗ trợ `add`, `remove`, `search`, `display`
* **Controller**:

  * `MountainStatistic.java`: điều phối các thao tác chính, xử lý logic thống kê
* **Utils**:

  * `Utils.java`: chứa các hàm nhập liệu an toàn như nhập số, nhập chuỗi có kiểm tra
* **View**:

  * Giao diện dạng console, menu điều hướng thao tác

### 🔧 Chức năng chính

| Chức năng                  | Mô tả                                                       |
| -------------------------- | ----------------------------------------------------------- |
| Thêm núi                   | Nhập tên, độ cao, quốc gia                                  |
| Hiển thị danh sách         | In toàn bộ danh sách núi                                    |
| Tìm núi cao nhất           | Tìm núi có độ cao lớn nhất trong danh sách                  |
| Thống kê độ cao trung bình | Tính và hiển thị độ cao trung bình                          |
| Lọc núi theo quốc gia      | Hiển thị danh sách núi thuộc quốc gia được nhập từ bàn phím |
| Lưu vào file (mở rộng)     | Ghi dữ liệu ra file `.txt` hoặc `.csv` để lưu trữ lâu dài   |

### 📌 Kỹ thuật áp dụng

* **Collection Framework**: sử dụng `ArrayList<Mountain>`
* **Generic Interface**: `I_List<T>` giúp tái sử dụng cho các loại đối tượng khác
* **Nhập liệu an toàn**: kiểm tra định dạng số, chuỗi, độ dài thông qua `Utils`

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🚘 3. Dự Án 2: Quản Lý Bảo Hiểm Xe Hơi (Insurance Management)

### 🎯 Mục tiêu

Xây dựng ứng dụng quản lý thông tin xe hơi và đăng ký bảo hiểm. Ứng dụng hỗ trợ kiểm tra điều kiện nhận bảo hiểm, tính toán giá trị hợp đồng và quản lý lịch sử khách hàng.

### 🧱 Cấu trúc dự án

* **Model**:

  * `Car`: thông tin xe như biển số, hãng xe, năm sản xuất
  * `Insurance`: thông tin hợp đồng bảo hiểm: gói bảo hiểm, thời hạn, chi phí
  * `Acceptable.java`: interface kiểm tra điều kiện bảo hiểm (VD: năm sản xuất > 2005)
* **Controller**:

  * `CarInsuranceController.java`: xử lý logic hợp đồng, điều kiện, tính chi phí
* **View**:

  * Menu nhập thông tin xe, lựa chọn loại bảo hiểm, in danh sách hợp đồng

### 🔧 Chức năng chính

| Chức năng             | Mô tả                                                      |
| --------------------- | ---------------------------------------------------------- |
| Thêm xe               | Nhập thông tin xe cần bảo hiểm                             |
| Kiểm tra điều kiện    | Chỉ chấp nhận bảo hiểm với xe đủ điều kiện (ví dụ đời mới) |
| Tạo hợp đồng bảo hiểm | Chọn gói, tính toán chi phí, thời hạn                      |
| Hiển thị hợp đồng     | In thông tin hợp đồng theo xe hoặc toàn bộ danh sách       |

### 📌 Tính năng mở rộng

* Hệ thống có thể được tích hợp tính năng **đọc/ghi file JSON/XML**
* Giao diện GUI bằng JavaFX hoặc Swing

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🍽️ 4. Dự Án 3: Quản Lý Đặt Tiệc (Feast Order Management)

### 🎯 Mục tiêu

Cho phép người dùng quản lý danh sách món ăn, khách hàng và đơn đặt tiệc. Dự án có khả năng in hóa đơn và thống kê doanh thu.

### 🧱 Cấu trúc dự án

* **Model**:

  * `Customer.java`, `MenuItem.java`, `FeastOrder.java`
  * Sử dụng lại `I_List<T>` để quản lý khách hàng và đơn hàng
* **Controller**:

  * `OrderController.java`, `CustomerController.java`
* **View**:

  * Menu console, thao tác đặt tiệc, in hóa đơn

### 🔧 Chức năng chính

| Chức năng          | Mô tả                                                       |
| ------------------ | ----------------------------------------------------------- |
| Quản lý khách hàng | Thêm, sửa, tìm kiếm khách hàng                              |
| Quản lý thực đơn   | Thêm món ăn, sửa giá                                        |
| Tạo đơn đặt tiệc   | Khách hàng chọn món và số lượng, hệ thống tính tổng chi phí |
| In hóa đơn         | In danh sách món ăn, khách hàng, giá tiền, tổng tiền        |
| Thống kê           | Tổng số đơn hàng, doanh thu theo ngày/tháng                 |

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## ✅ Lợi ích học được từ các dự án

| Kỹ năng                         | Mô tả                                                    |
| ------------------------------- | -------------------------------------------------------- |
| Java cơ bản và OOP              | Class, interface, kế thừa, trừu tượng                    |
| Mô hình MVC                     | Hiểu rõ cách tách biệt tầng dữ liệu, xử lý và giao diện  |
| Kiểm tra và xử lý dữ liệu       | Nhập chuỗi/số an toàn, kiểm tra logic dữ liệu            |
| Quản lý dữ liệu bằng collection | Sử dụng `ArrayList`, `HashMap` để quản lý danh sách động |
| Khả năng mở rộng                | Có thể nâng cấp thành app GUI hoặc hệ thống web với JSP  |

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 📈 Định hướng mở rộng

* **Lưu trữ dữ liệu lâu dài** bằng file (`.txt`, `.csv`, JSON)
* **Giao diện đồ họa (GUI)**: dùng JavaFX, Swing
* **Kết nối cơ sở dữ liệu**: MySQL, SQLite qua JDBC
* **Triển khai Web**: JSP/Servlet hoặc Spring Boot
* **Báo cáo PDF/Excel**: dùng thư viện Apache POI hoặc iText

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## 🏁 Kết luận

Thông qua các dự án mini như **quản lý núi**, **bảo hiểm xe hơi** và **đặt tiệc**, sinh viên có thể:

* Làm quen với tư duy thiết kế phần mềm theo chuẩn MVC
* Thực hành kỹ năng xử lý dữ liệu, giao diện và logic trong Java
* Chuẩn bị nền tảng vững chắc cho các đồ án lớn hoặc lập trình thực tế

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
