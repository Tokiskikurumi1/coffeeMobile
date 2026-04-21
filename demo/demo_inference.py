import pandas as pd
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_absolute_error, r2_score
import warnings
import os
warnings.filterwarnings('ignore')

print("=== HỆ THỐNG DỰ ĐOÁN DOANH THU QUÁN CÀ PHÊ ===")
print("[1] Đang tải dữ liệu từ data/sample_data.csv...")

try:
    # Cách nạp file từ thực tế
    file_path = os.path.join(os.path.dirname(__file__), '..', 'data', 'sample_data.csv')
    df = pd.read_csv(file_path)
    
    # Tiền xử lý (Preprocess)
    # 1. Chuyển date thành đối tượng thời gian
    df['date'] = pd.to_datetime(df['date'])
    # 2. Trích xuất đặc trưng 'Thứ trong tuần' (0: Thứ 2 -> 6: Chủ nhật)
    df['day_of_week'] = df['date'].dt.dayofweek
    
except Exception as e:
    # Dự phòng giả lập data nếu báo lỗi đường dẫn học máy
    print("[!] Lỗi nạp file, sử dụng dữ liệu giả lập...")
    df = pd.DataFrame({
        'day_of_week': [0, 0, 1, 1, 2, 2, 3, 4, 5, 6],
        'idFood': [1, 2, 6, 1, 9, 3, 7, 1, 10, 2],
        'amount': [2, 1, 3, 1, 2, 4, 2, 5, 1, 3] # Số lượng ly bán ra
    })

print("[2] Đang tiền xử lý và huấn luyện mô hình Random Forest...")
# Bài toán: Dựa vào "Thứ trong tuần" và "Mã đồ uống", dự đoán "Số lượng" (amount) sẽ bán được.
# Chọn Features & Target
X = df[['day_of_week', 'idFood']]
y = df['amount']

# Chia tập Train/Test theo tỉ lệ 80 - 20 (chỉ để minh họa do data nhỏ)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Build mô hình (Train)
model = RandomForestRegressor(n_estimators=100, random_state=42)
model.fit(X_train, y_train)

print("[3] Đánh giá mô hình (Evaluate):")
y_pred = model.predict(X_test)
print(f" -> MAE (Sai số tuyệt đối): {mean_absolute_error(y_test, y_pred):.2f} (ly/đơn vị)")
print(f" -> R2 Score: {r2_score(y_test, y_pred):.2f}\n")

print("=== CHẠY SUY LUẬN (INFERENCE) ===\n")
# Giả sử cần dự đoán bán được bao nhiêu ly Bạc xỉu (idFood = 3) vào ngày Chủ Nhật (day_of_week = 6)
demo_input = pd.DataFrame({
    'day_of_week': [6], # 6 = Chủ Nhật
    'idFood': [3]       # 3 = Bạc xỉu
})
prediction = model.predict(demo_input)
print(f"[*] Kịch bản: Chủ Nhật (Cuối tuần), Món: Bạc xỉu (Mã 3)")
print(f"[*] Dự đoán số lượng bán ra đạt khoảng: {prediction[0]:.0f} ly\n")

# Một kịch bản khác: Thứ Hai (day_of_week = 0), Cà phê đen (idFood = 1)
demo_input_2 = pd.DataFrame({'day_of_week': [0], 'idFood': [1]})
prediction_2 = model.predict(demo_input_2)
print(f"[*] Kịch bản: Thứ Hai (Đầu tuần), Món: Cà phê đen (Mã 1)")
print(f"[*] Dự đoán số lượng bán ra đạt khoảng: {prediction_2[0]:.0f} ly")
