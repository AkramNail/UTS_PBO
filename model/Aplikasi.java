package model;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Aplikasi implements Serializable {

    String namaAplikasi;
    String linkAplikasi;

    public Aplikasi(String namaAplikasi, String linkAplikasi) {
        this.namaAplikasi = namaAplikasi;
        this.linkAplikasi = linkAplikasi;   
    }

    public void jalankanAplikasi(List<Aplikasi> daftarAplikasi) {
        
        System.out.println("Daftar aplikasi: ");

        int loop = 1;
        for (Aplikasi app : daftarAplikasi) {
            System.out.println( loop + ". " + app.namaAplikasi);
            loop++;
        }
        
        int totalAplikasi = daftarAplikasi.size();
        int pilihan;

        Scanner input = new Scanner(System.in);
        System.out.print("Ketik nomor aplikasi/website yang anda ingin jalankan: ");
        pilihan = input.nextInt();

        for (int i = 0; i < totalAplikasi; i++) {
            if (pilihan - 1 == i) {
                
                System.out.println("Menjalankan aplikasi/website: " + daftarAplikasi.get(i).namaAplikasi);

                try{
                    Runtime.getRuntime().exec(new String[] {
                    "cmd", "/c", "start", "", daftarAplikasi.get(i).linkAplikasi
                });
                }catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            } 
        }

    }

    public Aplikasi tambahAplikasi(){
        Scanner input = new Scanner(System.in);

        String nama;
        String link;

        System.out.println("Masuk nama aplikasi: ");
        nama = input.nextLine();

        System.out.println("Masukan link path aplikasi atau link website: ");
        link = input.nextLine();

        System.out.println("Aplikasi/website berhasil ditambahkan");

        return new Aplikasi(nama, link);
    }

    public void menghapusAplikasi(List<Aplikasi> daftarAplikasi){

        System.out.println("Daftar aplikasi: ");

        int loop = 1;
        for (Aplikasi app : daftarAplikasi) {
            System.out.println( loop + ". " + app.namaAplikasi);
            loop++;
        }

        int totalAplikasi = daftarAplikasi.size();
        int pilihan;

        Scanner input = new Scanner(System.in);
        System.out.print("Ketik nomor aplikasi yang anda ingin hapus: ");
        pilihan = input.nextInt();

        for (int i = 0; i < totalAplikasi; i++) {
            if (pilihan - 1 == i) {
                
                String jawaban;
                System.out.println("Apakah anda yakin ingin menghapus aplikasi: " + daftarAplikasi.get(i).namaAplikasi + ", Ketik y untuk ya ketik n untuk tidak");
                Scanner input2 = new Scanner(System.in);
                jawaban = input2.nextLine();

                if(jawaban.equals("y")){
                    daftarAplikasi.remove(i);
                    System.out.println("data aplikasi/website berhasil di hapus");
                }

                break;

            } 
        }

    }

}
