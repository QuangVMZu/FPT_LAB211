/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class JobScheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Nhập số lượng công việc từ người dùng
        System.out.print("Nhập số lượng công việc: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Đọc dòng còn lại sau khi nhập số

        // Tạo danh sách các công việc
        List<Job> jobs = new ArrayList<>();

        // Nhập thông tin các công việc
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho công việc " + (i + 1) + ":");
            
            System.out.print("Tên công việc: ");
            String name = scanner.nextLine();
            
            System.out.print("Deadline(Tháng): ");
            int deadline = scanner.nextInt();
            
            System.out.print("Giá trị($): ");
            int value = scanner.nextInt();
            
            System.out.print("Thời gian hoàn thành(Tháng): ");
            int timeRequired = scanner.nextInt();
            scanner.nextLine();  // Đọc dòng còn lại sau khi nhập số
            
            // Thêm công việc vào danh sách
            jobs.add(new Job(name, deadline, value, timeRequired));
        }

        // Sắp xếp công việc theo các tiêu chí ưu tiên: Deadline > Giá trị > Thời gian hoàn thành
        jobs.sort((job1, job2) -> {
            // So sánh deadline
            if (job1.deadline != job2.deadline) {
                return Integer.compare(job1.deadline, job2.deadline);
            }
            // Nếu deadline giống nhau, ưu tiên giá trị
            if (job1.value != job2.value) {
                return Integer.compare(job2.value, job1.value); // Sắp xếp giảm dần theo giá trị
            }
            // Nếu deadline và giá trị giống nhau, ưu tiên thời gian hoàn thành ngắn hơn
            return Integer.compare(job1.timeRequired, job2.timeRequired);
        });

        // In ra các công việc theo thứ tự ưu tiên
        System.out.println("\nCác công việc được sắp xếp theo ưu tiên:");
        for (Job job : jobs) {
            System.out.println(job);
        }

        scanner.close();
    }
}
