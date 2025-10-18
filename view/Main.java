package view;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Aplikasi;

public class Main {

    public static void main(String[] args) {
        String fileName = "data_saves.ser";
        
        List<Aplikasi> daftarAplikasi = new ArrayList<>();
        Aplikasi aplikasi = new Aplikasi("Start", "FLCL");

        // Baca objek dari file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Aplikasi> dataBaca = (List<Aplikasi>) ois.readObject();
            System.out.println("Data hasil dibaca dari file:");
            for (Aplikasi m : dataBaca) {
                daftarAplikasi.add(m);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner input = new Scanner(System.in);

        int pilihan = 99;

        while (pilihan != 7) {
            
            System.out.println("Daftar fitur yang dapat anda gunakan: ");
            System.out.print("1.\tJalankan group\r\n" + //
                                "2.\tJalankan aplikasi\r\n" + //
                                "3.\tJalankan website\r\n" + //
                                "4.\tMengatur group \r\n" + //
                                "5.\tMengatur aplikasi\r\n" + //
                                "6.\tMengatur website\r\n" + //
                                "7.\tKeluar\r\n" //
                                );

            System.out.print("Ketik nomor fitur yang anda ingin jalankan: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 2:
                    try {
                        daftarAplikasi.get(0).jalankanAplikasi(daftarAplikasi);                        
                    }  catch (Exception e) {
                        System.out.println("Tidak ada aplikasi dalam data, harap tambah aplikasi terlebih dahulu");
                    } 
                    break;

                case 5:
                    System.out.print("1.\tMenambah aplikasi\r\n" + //
                                    "2.\tMenghapus aplikasi\r\n" +
                                    "3.\tMain menu\r\n" + //
                                    "4.\tKeluar dan Save data\r\n");
                    System.out.println("Ketik nomer fitur yang ingin anda gunakan: ");
                    Scanner input5 = new Scanner(System.in);
                            int jawaban = input5.nextInt();
                    
                    switch (jawaban) {
                        case 1:
                            daftarAplikasi.add(aplikasi.tambahAplikasi()); 
                        break;

                        case 2:
                        try {
                                daftarAplikasi.get(0).menghapusAplikasi(daftarAplikasi);                        
                            }  catch (Exception e) {
                                System.out.println("Tidak ada aplikasi dalam data, harap tambah aplikasi terlebih dahulu");
                            } 
                        break;

                        case 4:
                            System.exit(0);
                        break;

                        default:
                        break;
                    }
                    break;
            
                
                default:
                break;
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(daftarAplikasi);
            System.out.println("Data berhasil disimpan ke " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
