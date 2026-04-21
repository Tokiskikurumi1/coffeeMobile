# ☕ Dự Án: Hệ Thống Dự Đoán Bán Hàng - Quản Lý Quán Cà Phê (Manage Coffee ML)

## 📌 Giới thiệu đề tài
- **Bài toán:** Tại các quán cà phê, việc thiếu dự báo chính xác có thể dẫn đến việc nhập dư thừa nguyên liệu gây lãng phí, hoặc thiếu hụt nguyên liệu vào các ngày cao điểm dẫn đến mất khách. 
- **Mục tiêu:** Xây dựng mô hình Machine Learning nhằm dự đoán số lượng đơn đặt hàng và doanh thu trong ngày dựa trên dữ liệu lịch sử bán hàng được thu thập từ ứng dụng **Manage Coffee Mobile**. Hệ thống giúp chủ quán đưa ra quyết định chuẩn bị nguyên liệu và sắp xếp nhân sự hiệu quả hơn.

## 📊 Dataset (Dữ liệu)
- **Nguồn data:** Tập dữ liệu được trích xuất (Join giả lập) từ cơ sở dữ liệu SQLite của ứng dụng Mobile Coffee. Bao gồm bảng `bill`, `billDetail`, `menu` và `category`.
- **Mô tả các cột chính (Features) sử dụng để dự đoán:**
  - `date`: Ngày tạo hóa đơn (Được trích xuất thành thuộc tính "Thứ trong tuần").
  - `idFood`: Mã số món (Ví dụ: 1 là Cà phê đen, 3 là Bạc xỉu...).
  - `nameFood`: Tên món dùng để hiển thị.
  - `price`: Đơn giá món bán.
  - `amount` (Target): Tổng số lượng món bán ra trong hóa đơn (Dùng để dự đoán).

  *-> Phân tích: Mô hình sử dụng ngày trong tuần và mã món đồ uống để dự đoán số lượng ly có thể bán được vào các ngày tiếp theo.*


## ⚙️ Pipeline
Hệ thống được xây dựng theo luồng quy trình sau:
1. **Tiền xử lý (Preprocess):** Làm sạch dữ liệu (loại bỏ Missing values, Outliers), mã hóa các biến phân loại (One-Hot Encoding cho `category`, `weather`), chuẩn hóa các trường dữ liệu theo định dạng chuẩn.
2. **Train:** Chia tập dữ liệu thành theo tỉ lệ 80% (Huấn luyện) - 20% (Kiểm thử). Tiến hành train các mô hình học máy.
3. **Evaluate:** Đánh giá độ phục hồi và lỗi của mô hình dựa trên tập Test sử dụng các metrics hồi quy (Regression metrics).
4. **Inference (Khả năng suy luận):** Thực hiện dự đoán lượng bán theo input từ người dùng truyền vào (VD: Ngày mai là Cuối tuần + Nghỉ lễ + Trời mưa -> Dự đoán bán được bao nhiêu ly cà phê).

## 🧠 Mô hình sử dụng
- **Các mô hình đã thử nghiệm:** Linear Regression (LR), Decision Tree (DT), Random Forest (RF).
- **Mô hình được chọn:** **Random Forest Regressor**.
- **Lý do chọn:** Dữ liệu bán lẻ kinh doanh đồ uống thường có tính phi tuyến tính cao (lượng mua không hẳn tăng tuyến tính hay giảm tuyến tính theo một yếu tố cố định). Random Forest với cơ chế ensemble learning (kết hợp nhiều cây quyết định) giúp chống lại hiện tượng overfitting tốt hơn Decision Tree và giúp bắt được các quy luật phức tạp tốt hơn so với Linear Regression.

## 📈 Kết quả
*(Ghi chú: Dưới đây là các chỉ số ví dụ, bạn hãy tự điều chỉnh lại với script thật của nhóm)*
Mô hình Random Forest trên tập thử nghiệm đạt được kết quả như sau:
- **MAE** (Mean Absolute Error): `12.5` món/ngày (Mô hình dự đoán lệch trung bình khoảng 12-13 đơn vị món / ngày).
- **RMSE** (Root Mean Squared Error): `18.2`
- **R² Score**: `0.87` (Mô hình giải thích được khoảng 87% sự biến thiên của dữ liệu thực tế).

## 🚀 Hướng dẫn chạy

### 1. Cài đặt môi trường
Đảm bảo bạn đã cài đặt Python (>= 3.8). Clone project về máy:
```bash
git clone https://github.com/Tokiskikurumi1/coffeeMobile.git
cd ManageCoffee_ML

# Tạo môi trường ảo (Khuyến nghị)
python -m venv venv
source venv/bin/activate  # (Với Windows sử dụng: venv\Scripts\activate)

# Cài đặt các thư viện phụ thuộc