/*
 * Coded by M. Fazri Nizar
 * Informatics Engineering, Faculty of Computer Science (FASILKOM)
 * Sriwijaya University (UNSRI)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class PermuteMatrixes {
    private static int counter = 1;
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Masukkan ukuran matriks: ");
            int n = Integer.parseInt(reader.readLine());
            Vector<Integer> arr = new Vector<Integer>(n);
            long startTime = System.nanoTime();

            for (int i = 0; i < n; i++) {
                System.out.print("Masukkan elemen ke-" + (i + 1) + ": ");
                arr.add(Integer.parseInt(reader.readLine()));

            }
            printTable(arr);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;

            System.out.println("Waktu eksekusi: " + duration + " ms");
        }
    }

    private static void printTable(Vector<Integer> arr) {
        System.out.printf("%-5s %-15s %-15s %-10s %-20s %-20s%n", "No", "Permutasi",
                "Jumlah Inversi", "Kelompok",
                "Hasil Kali Elementer", "Hasil Kali Elementer Bertanda");
        permute(arr, 0);
    }

    private static void permute(Vector<Integer> arr, int l) {
        if (l == arr.size() - 1) {
            printRow(arr);
            counter++;
        } else {
            for (int i = l; i < arr.size(); i++) {
                swap(arr, l, i);
                permute(arr, l + 1);
                swap(arr, l, i);
            }
        }
    }

    private static void swap(Vector<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private static void printRow(Vector<Integer> arr) {
        int inversions = countInversions(arr);
        String group = inversions % 2 == 0 ? "Genap" : "Ganjil";
        String product = calculateProduct(arr);
        String signedProduct = group.equals("Genap") ? product : "-" + product;
        System.out.printf("%-5d %-15s %-15d %-10s %-20s %-20s%n", counter,
                arr.toString(), inversions, group, product,
                signedProduct);
    }

    private static int countInversions(Vector<Integer> arr) {
        int count = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i) > arr.get(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static String calculateProduct(Vector<Integer> arr) {
        StringBuilder product = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            if (i != 0)
                product.append("*");
            product.append("a").append(i + 1).append(arr.get(i));
        }
        return product.toString();
    }
}
