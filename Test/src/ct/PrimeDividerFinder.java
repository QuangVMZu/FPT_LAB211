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
import java.util.Scanner;

public class PrimeDividerFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập giá trị n từ người dùng
        System.out.print("Enter a value for n (n >= 1): ");
        int n = scanner.nextInt();

        if (n < 1) {
            System.out.println("Invalid input! n must be greater than or equal to 1.");
            return;
        }

        // Tìm và in kết quả
        System.out.println("For n = " + n + ":");
        findSmallestPrimeDivider(n);
    }

    // Hàm tìm và in ra số nguyên tố nhỏ nhất p > n
    public static void findSmallestPrimeDivider(int n) {
        for (int x = n; x <= 2 * n; x++) {
            int smallestPrime = Integer.MAX_VALUE;

            // Kiểm tra từng số trong dãy x+1, x+2, ..., x+n
            for (int i = 1; i <= n; i++) {
                int number = x + i;
                int prime = smallestPrimeFactorGreaterThanN(number, n);
                if (prime > n && prime < smallestPrime) {
                    smallestPrime = prime;
                }
            }

            // In kết quả cho từng giá trị x
            if (smallestPrime == Integer.MAX_VALUE) {
                System.out.println("x = " + x + ", No prime p > n found.");
            } else {
                System.out.println("x = " + x + ", Prime: " + smallestPrime);
            }
        }
    }

    // Hàm kiểm tra số nguyên tố
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Hàm tìm ước số nguyên tố nhỏ nhất của num lớn hơn n
    public static int smallestPrimeFactorGreaterThanN(int num, int n) {
        for (int i = 2; i <= num; i++) {
            if (num % i == 0 && isPrime(i) && i > n) {
                return i;
            }
        }
        return Integer.MAX_VALUE; // Không tìm thấy số nguyên tố
    }
}