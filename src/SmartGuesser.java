import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class SmartGuesser {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean[] GecerliSayilar = new boolean[9877];

        for (int i = 1023; i < 9877; i++) {
            int binler = i / 1000;
            int yuzler = i / 100 % 10;
            int onlar = i / 10 % 10;
            int birler = i % 10;
            if (binler != yuzler && binler != onlar && binler != birler && yuzler != onlar && yuzler != birler && onlar != birler) {
                GecerliSayilar[i] = true;
            }
        }
        System.out.println("Lütfen Dört basamaklı bir sayı tuttunuz ve OK yazarak entera basınız");
        String SistemeGiris = scanner.next();
        SistemeGiris = SistemeGiris.toLowerCase(Locale.ROOT);
        if (SistemeGiris.equals("ok")){
            int Deneme = 1;
            int Deneme1 = 1;
            boolean Dongu = true;
            while (Dongu){
                int Tahmin = findNextGuess(GecerliSayilar);
                System.out.println(Deneme + "nci Tahminim : " + Tahmin);
                System.out.print("+");
                int PozitifDeger = scanner.nextInt();
                PozitifKontrol(PozitifDeger);
                System.out.print("-");
                int NegatifDeger = scanner.nextInt();
                NegatifKontrol(NegatifDeger);
                if (PozitifDeger+NegatifDeger<=4 && PozitifDeger+NegatifDeger>=-4){
                    if (PozitifDeger == 4) {
                        System.out.println(Deneme + "nci Tahmininiz : " + Tahmin);
                        System.out.println(Deneme + " tahminde buldum");
                        Dongu = false;
                    }else {
                        int b = 0;

                        eliminatePossibilities(GecerliSayilar, Tahmin, PozitifDeger, NegatifDeger);
                        Deneme++;
                        for (int a = 0; a<GecerliSayilar.length;a++){
                            if (GecerliSayilar[a]==false){
                                b++;
                            }
                        }if (b==9877){
                            System.out.println("Girdiğiniz değerler ile sonuç bulamadık program tekrar dönecek");
                            Deneme =1;
                            for (int i = 1023; i < 9877; i++) {
                                int binler = i / 1000;
                                int yuzler = i / 100 % 10;
                                int onlar = i / 10 % 10;
                                int birler = i % 10;
                                if (binler != yuzler && binler != onlar && binler != birler && yuzler != onlar && yuzler != birler && onlar != birler) {
                                    GecerliSayilar[i] = true;
                                }
                            }continue;
                        }
                    }

                }
                if (PozitifDeger+NegatifDeger>4 || PozitifDeger+NegatifDeger<-4) {
                    System.out.println("Lütfen değerlerinizi düzgün giriniz");
                }
            }
        }
    }
    public static int PozitifKontrol(int GirilenSayi){
        boolean Dongu = true;
        Scanner scanner = new Scanner(System.in);
        if (GirilenSayi>=0){
            return GirilenSayi;
        }
        else {
            while (Dongu){
                System.out.println("Lütfen 0 yada pozitif bir sayı giriniz");
                GirilenSayi = scanner.nextInt();
                if (GirilenSayi>=0){
                    Dongu = false;
                }
            }return GirilenSayi;
        }
    }
    public static int NegatifKontrol(int GirilenSayi){
        boolean Dongu = true;
        Scanner scanner = new Scanner(System.in);
        if (GirilenSayi>=0){
            return GirilenSayi;
        }
        else {
            while (Dongu) {
                System.out.println("Lütfen 0 yada pozitif bir sayı giriniz");
                GirilenSayi = scanner.nextInt();
                if (GirilenSayi <= 0) {
                    Dongu = false;
                }
            }
            return GirilenSayi;
        }}


    public static int findNextGuess(boolean[] possibleNumbers) {
        Random random = new Random();
        int guess;
        do {
            guess = 1023 + random.nextInt(9877 - 1023);
        } while (!possibleNumbers[guess]);
        return guess;
    }


    public static void eliminatePossibilities(boolean[] possibleNumbers, int guess, int plus, int minus) {
        String guessStr = String.valueOf(guess);
        for (int i = 1023; i <= 9876; i++) {
            if (possibleNumbers[i]) {
                if (!rakamlarFarkliMi(i)) {
                    possibleNumbers[i] = false;
                    continue;
                }

                String currentStr = String.valueOf(i);
                int currentPlus = 0;
                int currentMinus = 0;

                for (int j = 0; j < 4; j++) {
                    if (guessStr.charAt(j) == currentStr.charAt(j)) {
                        currentPlus++;
                    } else if (guessStr.contains(String.valueOf(currentStr.charAt(j)))) {
                        currentMinus++;
                    }
                }

                if (currentPlus != plus || currentMinus != minus) {
                    possibleNumbers[i] = false;
                }
            }
        }
    }
    public static boolean rakamlarFarkliMi(int num) {
        String numStr = String.valueOf(num);
        for (int i = 0; i < numStr.length(); i++) {
            for (int j = i + 1; j < numStr.length(); j++) {
                if (numStr.charAt(i) == numStr.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}

