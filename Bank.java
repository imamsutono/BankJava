import java.util.Arrays;
import java.util.Scanner;
/**
 * Bank
 */
public class Bank {

  private String[][] dataNasabah = {};

  public static void main(String[] args) {

    System.out.println("=====================");
    System.out.println("1 Input No Rekening, Nama dan Saldo Nasabah");
    System.out.println("2 Cek Data Nasabah");
    System.out.println("3 Transfer Saldo");
    System.out.println("4 Keluar");
    System.out.println("=====================");
    int menu;
    Scanner menuScanner;

    do {
      System.out.print("\nPilih Menu: ");
      menuScanner = new Scanner(System.in);
      menu = menuScanner.nextInt();
      System.out.println("");

      switch (menu) {
        case 1:
          inputNasabah();
          break;
        case 2:
          checkNasabah();
          break;
        case 3:
          transferBalance();
          break;
        case 4:
          System.exit(0);
          break;
      
        default:
          System.out.println("Menu yang Anda pilih tidak tersedia");
          break;
      }
    } while (menu != 4);

    menuScanner.close();
  }

  public static void inputNasabah() {
    String[] currentNasabah = {"", "", ""};
    String rekening;

    do {
      System.out.print("No rekening: ");
      Scanner rekeningScanner = new Scanner(System.in);
      rekening = rekeningScanner.next();
      currentNasabah = getNasabahByRekening(rekening);

      if (currentNasabah[0] != "") System.out.println("Nomor rekening telah terpakai!");
    } while (currentNasabah[0] != "");

    System.out.print("Nama nasabah: ");
    Scanner namaScanner = new Scanner(System.in);
    String nama = namaScanner.nextLine();

    System.out.print("Saldo: ");
    Scanner saldoScanner = new Scanner(System.in);
    String saldo = saldoScanner.next();

    String[] nasabah = { rekening, nama, saldo };

    addNasabah(nasabah);
  }

  public static void checkNasabah() {
    System.out.print("Masukkan no rekening: ");
    Scanner rekScanner = new Scanner(System.in);
    String rek = rekScanner.next();

    printNasabahWhereRek(rek);
  }

  public static void transferBalance() {
    Bank bank = new Bank();
    if (bank.dataNasabah.length == 0) {
      System.out.println("Belum ada data nasabah.");
      return;
    }

    if (bank.dataNasabah.length == 1) {
      System.out.println("Tidak ada nasabah lain.");
      return;
    }

    System.out.print("Transfer dari rekening: ");
    Scanner senderScanner = new Scanner(System.in);
    String senderRek = senderScanner.next();
    String[] sender = getNasabahByRekening(senderRek);

    if (sender[0] == "") {
      System.out.println("Nomor rekening tidak ditemukan");
    } else {
      System.out.print("Transfer ke rekening: ");
      Scanner receiverScanner = new Scanner(System.in);
      String receiverRek = receiverScanner.next();
      String[] receiver = getNasabahByRekening(receiverRek);

      if (receiver[0] == "") {
        System.out.println("Nomor rekening tidak ditemukan");
      } else {
        System.out.print("Nominal transfer: ");
        Scanner nominalScanner = new Scanner(System.in);
        int nominal = nominalScanner.nextInt();
        int balance = Integer.parseInt(sender[2]) - nominal;

        if (balance < 0) {
          System.out.println("Gagal transfer, jumlah saldo tidak cukup!");
        } else {
          System.out.println("Transfer berhasil. Terima kasih!");
        }
      }
    }
  }

  public static void addNasabah(String[] item) {
    Bank bank = new Bank();
    String[][] dataNasabah = bank.dataNasabah;
    int len = dataNasabah.length;
    String[][] newData = new String[len + 1][3];

    newData[len][0] = item[0];
    newData[len][1] = item[1];
    newData[len][2] = item[2];

    for (int i = 0; i < len; i++) {
      System.arraycopy(dataNasabah[i], 0, newData[i], 0, dataNasabah[0].length);
    }
    bank.dataNasabah = newData;
  }

  public static void printNasabahs(String[][] dataNasabah) {
    System.out.println("=============");
    System.out.println("Data Nasabah");
    System.out.println("=============\n");

    for (int i = 0; i < dataNasabah.length; i++) {
      printNasabah(dataNasabah[i]);
    }
  }

  public static void printNasabah(String[] data) {
    System.out.println("No. Rekening : " + data[0]);
    System.out.println("Nama Nasabah : " + data[1]);
    System.out.println("Saldo        : " + data[2]);
  }

  public static String[] getNasabahByRekening(String noRekening) {
    Bank bank = new Bank();
    String[][] dataNasabah = bank.dataNasabah;
    String[] result = {"", "", ""};

    for (int i = 0; i < dataNasabah.length; i++) {
      if (dataNasabah[i][0].compareTo(noRekening) == 0) {
        result = dataNasabah[i];
        break;
      }
    }

    return result;
  }

  public static void printNasabahWhereRek(String noRekening) {
    Bank bank = new Bank();
    String[][] dataNasabah = bank.dataNasabah;

    for (int i = 0; i < dataNasabah.length; i++) {
      if (dataNasabah[i][0].compareTo(noRekening) == 0) {
        System.out.println("===============");
        printNasabah(dataNasabah[i]);
        System.out.println("===============");
        break;
      }
      if (i == dataNasabah.length - 1) System.out.println("Data nasabah tidak ditemukan");
    }
  }
}
