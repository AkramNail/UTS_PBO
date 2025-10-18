package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Group implements Serializable {
    
    String namaGroup;
    List<String> daftarLinkAplikasi = new ArrayList<>();

    public Group(String namaGroup, List<String> daftarLinkAplikasi){

        this.namaGroup = namaGroup;
        this.daftarLinkAplikasi = daftarLinkAplikasi;

    }

    public void menjalankanGroup(List<Group> daftarGroup){

        System.out.println("Daftar group: ");

        int loop = 1;
        for (Group app : daftarGroup) {
            System.out.println( loop + ". " + app.namaGroup);
            loop++;
        }

        int pilihan;

        Scanner input = new Scanner(System.in);
        System.out.print("Ketik nomor group yang ingin anda jalankan");
        pilihan = input.nextInt();

        System.out.println("Menjalankan group, dengan aplikasi: ");
        loop = 1;
        for (String app : daftarGroup.get(pilihan - 1).daftarLinkAplikasi) {
            System.out.println( loop + ". " + app);
            try{
                Runtime.getRuntime().exec(new String[] {
                "cmd", "/c", "start", "", app
            });
            }catch (IOException e) {
                e.printStackTrace();
            }
            loop++;
        }

    }

    public Group menambahGroup(List<Aplikasi> daftarAplikasi){

        String nama;
        List<String> daftarLink = new ArrayList<>();

        Scanner inputNama = new Scanner(System.in);
        System.out.println("Masukan nama group yang ingin dibuat: ");
        nama = inputNama.nextLine();

        int totalAplikasi = daftarAplikasi.size();
        int pilihan;
        String keluar = "y";

        while(keluar.equals("y")){

            int loop = 1;
            for (Aplikasi app : daftarAplikasi) {
                System.out.println( loop + ". " + app.namaAplikasi);
                loop++;
            }

            Scanner input = new Scanner(System.in);
            System.out.print("Ketik nomor aplikasi/website yang ingin anda tambah: ");
            pilihan = input.nextInt();

            for (int i = 0; i < totalAplikasi; i++) {
                if (pilihan - 1 == i) {

                    daftarLink.add(daftarAplikasi.get(i).linkAplikasi);

                    System.out.println("Aplikasi/website: " + daftarAplikasi.get(i).namaAplikasi + " berhasil di tambah");

                    break;

                }
            }
            Scanner input2 = new Scanner(System.in);
            System.out.println("apakah anda igin mendambah aplikasi/website lain, ketik y untuk ya dan ketik n untuk tidak");
            keluar = input2.nextLine();

        }

        return new Group(nama, daftarLink);

    }

    public int menghapusGroup(List<Group> daftarGroup){

        System.out.println("Daftar group: ");

        int loop = 1;
        for (Group app : daftarGroup) {
            System.out.println( loop + ". " + app.namaGroup);
            loop++;
        }

        int pilihan;

        Scanner input = new Scanner(System.in);
        System.out.print("Ketik nomor group yang ingin anda hapus");
        pilihan = input.nextInt();

        return (pilihan - 1);

    }
}
