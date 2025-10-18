package view;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Aplikasi;
import model.Group;

public class Main {

    public static void main(String[] args) {
        String fileName = "data_saves.ser";
        
        List<Aplikasi> daftarAplikasi = new ArrayList<>();
        Aplikasi aplikasi = new Aplikasi("NIRVANA", "FLCL");

        List<Group> daftarGroup = new ArrayList<>();
        Group group = new Group("OASIS");

        // membaca file yang disimpan di data_save.ser
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Aplikasi> dataBaca1 = (List<Aplikasi>) ois.readObject();
            List<Group> dataBaca2 = (List<Group>) ois.readObject();
            System.out.println("Data hasil dibaca dari file:");
            for (Aplikasi m : dataBaca1) {
                daftarAplikasi.add(m);
            }
            for (Group m : dataBaca2) {
                daftarGroup.add(m);
            }
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println(".");
        }
        Scanner input = new Scanner(System.in);

        int pilihan = 99;

        while (pilihan != 5) {
            
            System.out.println("Daftar fitur yang dapat anda gunakan: ");
            System.out.print("1.\tJalankan group\r\n" + //
                                "2.\tJalankan aplikasi\r\n" + //
                                "3.\tMengatur group \r\n" + //
                                "4.\tMengatur aplikasi\r\n" + //
                                "5.\tKeluar dan save\r\n" //
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

                case 4:
                    System.out.print("1.\tMenambah aplikasi\r\n" + //
                                    "2.\tMenghapus aplikasi\r\n" +
                                    "3.\tMain menu\r\n" + //
                                    "4.\tKeluar\r\n");
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
