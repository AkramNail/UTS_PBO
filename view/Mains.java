package view;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Mahasiswa implements Serializable {
    private String nama;
    private int umur;
    private String jurusan;

    public Mahasiswa(String nama, int umur, String jurusan) {
        this.nama = nama;
        this.umur = umur;
        this.jurusan = jurusan;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Umur: " + umur + ", Jurusan: " + jurusan;
    }
}

public class Mains {
    public static void main(String[] args) {
        String fileName = "data_mhs.ser";

        List<Mahasiswa> daftar = new ArrayList<>();
        daftar.add(new Mahasiswa("Andi", 20, "Informatika"));
        daftar.add(new Mahasiswa("Budi", 21, "Sistem Informasi"));
        

        // Simpan objek ke file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(daftar);
            System.out.println("Data berhasil disimpan ke " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Baca objek dari file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Mahasiswa> dataBaca = (List<Mahasiswa>) ois.readObject();
            System.out.println("Data hasil dibaca dari file:");
            for (Mahasiswa m : dataBaca) {
                System.out.println(m);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
