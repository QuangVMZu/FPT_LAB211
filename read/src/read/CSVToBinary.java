import java.io.*;
import java.util.*;

// Lớp Feast để lưu thông tin từng bữa tiệc
class Feast implements Serializable {
    private String code;
    private String name;
    private int price;
    private String ingredients;

    // Constructor
    public Feast(String code, String name, int price, String ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Phương thức toString để hiển thị thông tin
    @Override
    public String toString() {
        return "Code: " + code + ", Name: " + name + ", Price: " + price + ", Ingredients: " + ingredients;
    }
}

// Lớp chính để xử lý đọc/ghi file CSV và BIN
public class CSVToBinary {
    public static void main(String[] args) {
        String csvFile = "FeastMenu.csv";  // File CSV đầu vào
        String binFile = "FeastMenu.bin";  // File BIN đầu ra

        // Đọc dữ liệu từ file CSV
        List<Feast> feastList = readCSV(csvFile);
        
        // Ghi dữ liệu vào file BIN
        writeToBinaryFile(binFile, feastList);

        System.out.println("✅ Chuyển đổi thành công! Dữ liệu đã được lưu vào " + binFile);

        // Đọc lại từ file BIN để kiểm tra
        List<Feast> readList = readFromBinaryFile(binFile);
        System.out.println("\n📌 Dữ liệu đọc từ file BIN:");
        readList.forEach(System.out::println);
    }

    // 📌 Hàm đọc file CSV và chuyển thành danh sách Feast
    public static List<Feast> readCSV(String filePath) {
        List<Feast> feastList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Bỏ qua dòng tiêu đề

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");  // Tách dữ liệu theo dấu phẩy
                
                if (parts.length < 4) continue; // Đảm bảo có đủ dữ liệu
                
                String code = parts[0].trim();
                String name = parts[1].trim();
                int price = Integer.parseInt(parts[2].trim());
                String ingredients = parts[3].trim();

                feastList.add(new Feast(code, name, price, ingredients));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feastList;
    }

    // 📌 Hàm ghi danh sách Feast vào file BIN
    public static void writeToBinaryFile(String filePath, List<Feast> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list); // Ghi toàn bộ danh sách vào file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 📌 Hàm đọc file BIN và trả về danh sách Feast
    public static List<Feast> readFromBinaryFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Feast>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
