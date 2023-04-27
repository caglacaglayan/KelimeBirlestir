package netrox;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static Process process = new Process();
    public static Record record = new Record();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //girdi sayisi ile input girisi
        Input();
        // birlestir butonuna basılırsa
        Combine();
        // kaydet butonuna basılırsa
        Save();
        // listele butonuna basılırsa
        List();

        process.connection.CloseConnection();
    }
    public static void Input() {
        String metin;
        Piece();

        for(int i = 1; i <= record.adet; i++) {
            System.out.println("Metin "+i+":");
            metin = scanner.nextLine();
            record.metinler.add(metin);
        }
    }
    public static int Piece() {
        System.out.println("Kaç adet metin gireceksiniz? (2-5):");
        record.adet = scanner.nextInt();
        scanner.nextLine();
        if (record.adet >= 2 && record.adet <= 5) {
            return record.adet;
        }
        else {
            return Piece();
        }
    }
    public static void Combine() {
        String komut;
        System.out.println("Birleştirilsin mi? (E/H):");
        komut = scanner.nextLine();
        if (komut.equals("E") || komut.equals("e")) {
            process.Combine(record.GetMetinler());
            System.out.println(process.birlesikMetin);
            System.out.println(process.CalculateTime());
        }
    }
    public static void Save() {
        String komut;
        System.out.println("Kaydedilsin mi? (E/H):");
        komut = scanner.nextLine();
        if (komut.equals("E") || komut.equals("e")) {
            process.SetRecordValues(record);
            process.SaveRecord(record);
        }
    }
    public static void List() {
        String komut;
        System.out.println("Kayıtlar listelensin mi? (E/H):");
        komut = scanner.nextLine();
        if (komut.equals("E") || komut.equals("e")) {
            List<String> records = process.ListRecords();
            for (int i = 0; i < records.size(); i++) {
                System.out.println(records.get(i));
            }
        }
    }

}