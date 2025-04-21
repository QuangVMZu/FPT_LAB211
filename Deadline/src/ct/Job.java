/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ct;

/**
 *
 * @author ADMIN
 */
class Job {
    String name;
    int deadline;      // Deadline của công việc (ví dụ: ngày hoàn thành, số ngày)
    int value;         // Giá trị của công việc (ví dụ: lợi nhuận hoặc giá trị lợi ích)
    int timeRequired;  // Thời gian cần để hoàn thành công việc (ví dụ: số giờ hoặc ngày)

    // Constructor để khởi tạo công việc
    public Job(String name, int deadline, int value, int timeRequired) {
        this.name = name;
        this.deadline = deadline;
        this.value = value;
        this.timeRequired = timeRequired;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", deadline=" + deadline +
                ", value=" + value +
                ", timeRequired=" + timeRequired +
                '}';
    }
}
