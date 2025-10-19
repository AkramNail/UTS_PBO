package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainMenu {
    
    String fileName = "data_saves.ser";
    
    List<Aplikasi> daftarAplikasi;
    Aplikasi aplikasi;

    List<Group> daftarGroup;
    Group group;

    public void menjalankanMainMenu(){

        inisialisasi();

        membacaSaveFile(daftarAplikasi, daftarGroup);

        membukaMenu(daftarAplikasi, daftarGroup, aplikasi, group);

        mengSaveData(daftarAplikasi, daftarGroup);

    }

    void inisialisasi(){

        List<Aplikasi> daftarAplikasi = new ArrayList<>();
        Aplikasi aplikasi = new Aplikasi("NIRVANA", "FLCL");

        List<Group> daftarGroup = new ArrayList<>();
        Group group = new Group("OASIS", new ArrayList<>());

        this.daftarAplikasi = daftarAplikasi;
        this.aplikasi = aplikasi;

        this.daftarGroup = daftarGroup;
        this.group = group;


    }

    void membacaSaveFile(List<Aplikasi> daftarAplikasi, List<Group> daftarGroup){

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            List<Aplikasi> dataBaca1 = (List<Aplikasi>) ois.readObject();
            List<Group> dataBaca2 = (List<Group>) ois.readObject();
            System.out.println("Data hasil dibaca dari file:" + fileName);
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

    }
        
    void membukaMenu(List<Aplikasi> daftarAplikasi, List<Group> daftarGroup, Aplikasi aplikasi, Group group){

        Scanner input = new Scanner(System.in);

        int pilihan = 99;

        while (pilihan != 5) {
            
            System.out.println("Daftar fitur yang dapat anda gunakan: ");
            System.out.print("1.\tJalankan group\r\n" + //
                                "2.\tJalankan aplikasi/website\r\n" + //
                                "3.\tMengatur group \r\n" + //
                                "4.\tMengatur aplikasi/website\r\n" + //
                                "5.\tKeluar dan save\r\n" //
                                );

            System.out.print("Ketik nomor fitur yang anda ingin jalankan: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    if(daftarGroup.isEmpty()) {
                        System.out.println("Tidak ada group dalam data, harap tambah group terlebih dahulu");                      
                    }else {
                        group.menjalankanGroup(daftarGroup);  
                    } 
                break;

                case 2:
                    try {
                        daftarAplikasi.get(0).jalankanAplikasi(daftarAplikasi);                        
                    }  catch (Exception e) {
                        System.out.println("Tidak ada aplikasi/website dalam data, harap tambah aplikasi terlebih dahulu");
                    } 
                break;

                case 3:
                    System.out.print("1.\tMenambah group\r\n" + //
                                    "2.\tMenghapus group\r\n" +
                                    "3.\tMain menu\r\n" + //
                                    "4.\tKeluar\r\n");
                    System.out.println("Ketik nomer fitur yang ingin anda gunakan: ");
                    Scanner input78 = new Scanner(System.in);
                    int jawaban78 = input78.nextInt();

                    switch (jawaban78) {
                        case 1:
                            if(daftarAplikasi.isEmpty()) {
                                System.out.println("Tidak ada aplikasi/website dalam data, harap tambah aplikasi/website terlebih dahulu");                      
                            }else {
                                daftarGroup.add(group.menambahGroup(daftarAplikasi));  
                            } 
                        break;

                        case 2:
                            if(daftarGroup.isEmpty()) {
                                System.out.println("Tidak ada group dalam data, harap tambah group terlebih dahulu");                      
                            }else {
                                daftarGroup.remove(group.menghapusGroup(daftarGroup));  
                            } 
                        break;

                        case 4:
                            System.exit(0);
                        break;

                        default:
                        break;
                    }
                break;

                case 4:
                    System.out.print("1.\tMenambah aplikasi/website\r\n" + //
                                    "2.\tMenghapus aplikasi/website\r\n" +
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
                                System.out.println("Tidak ada aplikasi/website dalam data, harap tambah aplikasi terlebih dahulu");
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

    }

    void mengSaveData(List<Aplikasi> daftarAplikasi, List<Group> daftarGroup){

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(daftarAplikasi);
            oos.writeObject(daftarGroup);
            System.out.println("Data berhasil disimpan ke " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
