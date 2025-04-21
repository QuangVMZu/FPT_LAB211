
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class TuitionFeeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng môn học: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhập tên khoá học (HK Summer, HK1, HK2, AllYear): ");
        String courseName = scanner.nextLine().toUpperCase();
        int weeks;
        switch (courseName) {
            case "HK SUMMER":
                weeks = 4;
                break;
            case "HK1":
                weeks = 16;
                break;
            case "HK2":
                weeks = 18;
                break;
            case "ALLYEAR":
                weeks = 38;
                break;
            default:
                System.out.println("Khoá học không hợp lệ!");
                return;
        }
        
        List<String> subjects = new ArrayList<>();
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Nhập tên môn học " + (i + 1) + ": ");
            String nameClass = scanner.nextLine().toUpperCase();
            subjects.add(nameClass);
        }

        Map<String, Double> subjectFees = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("hocphi.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); // Giả sử dữ liệu phân tách bằng dấu phẩy
                if (data.length == 2) {
                    String subject = data[0].trim();
                    try {
                        double fee = Double.parseDouble(data[1].trim());
                        subjectFees.put(subject, fee);
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi định dạng học phí cho môn " + subject + ": " + data[1]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
            return;
        }

        double totalFee = 0;
        for (String subject : subjects) {
            if (subjectFees.containsKey(subject)) {
                totalFee += subjectFees.get(subject) * (weeks / 4.0); // Điều chỉnh học phí theo số tuần
            } else {
                System.out.println("Môn học " + subject + " không có trong dữ liệu!");
            }
        }

        // Kiểm tra nếu người dùng chọn khóa học cả năm hoặc số môn học > 3 thì giảm 10%
        if (courseName.equals("Cả năm") || numSubjects >= 3) {
            totalFee *= 0.9;  // Giảm 10%
        }

        // Định dạng số có dấu phẩy ngăn cách hàng nghìn
        DecimalFormat df = new DecimalFormat("#,###");

        // Hiển thị tổng học phí
        System.out.println("Tổng học phí cần thanh toán: " + df.format(totalFee) + " VND");
    }
}
