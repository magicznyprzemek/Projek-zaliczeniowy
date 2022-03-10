package com.company;

import java.lang.Math;
import java.io.Serializable;
import java.util.Scanner;
import java.util.*;

public class Gwiazda implements Serializable {
    private static final long serialVersionUID = 3774657275813838134L; // nie mam pojęcia, ale potrzebne do zapisu w pliku obiektowym

    public Gwiazda() {
    }

    // tablica alfabetu greckiego
    static String[] AlfabetGrecki = {
            "Alfa", "Beta", "Gamma", "Delta", "Epsilon",
            "Dzeta", "Eta", "Theta", "Iota", "Kappa",
            "Lambda", "My", "Ny", "Xy", "Omicron",
            "Pi", " Rho", "Sigma", "Tau", "Ypsilon",
            "Phi", "Chi", "Psi", "Omega"
    };

    public String KatalogNazwa;
    public String Nazwa;
    public int[] Deklinacja = new int[3];
    public int[] Rektascencja = new int[3];
    public float Obserowalna_Wielk;
    public float Absolutna_Wielk;
    public float Odleglosc;
    public String Gwiazdozbior;
    public boolean Polkula;
    public float Temperatura;
    public float Masa;
    public int NrNadania;
    // wspólny scanner dla całej klasy, otwieranie i zamykanie go w ramach każdej pojedyńczej funkcji okazało się problematyczne
    static Scanner reader = new Scanner(System.in);

    // poniżej są funkcje nadawania wartości pól obiektu gwiazdy, nazwy raczej jednoznacznie sugerują działanie
    public void SetNazwa() {
        while (true) {
            System.out.print("Nazwa Gwiazdy: ");
            Nazwa = reader.nextLine();
            if (Nazwa.matches("^[A-Z]{3}\\d{4}"))
                break;
            System.out.println("- Błdny format, wprowadź ponownie");
        }
    }

    public void SetDeklinacja() {
        System.out.println("Nadaj Deklinację");
        MinutySekundy(Deklinacja);
    }

    public void SetRektascencja() {
        System.out.println("Nadaj Rektascencję");
        MinutySekundy(Rektascencja);
    }

    // MinutySekundy(int[] tab) deklinacja i rektascencja mają identyczny format danych wejściowych, więc korzystają z tej samej funkcji
    public void MinutySekundy(int[] tab) {
        while (true) {
            System.out.print("Godzina: ");
            tab[0] = reader.nextInt();
            System.out.print("Minuta: ");
            tab[1] = reader.nextInt();
            System.out.print("Sekunda: ");
            tab[2] = reader.nextInt();
            if ((float) tab[0] + (float) tab[1] / 10 + (float) tab[2] / 100 <= 90)
                break;
            System.out.println("- Błędne dane, wprowadź ponownie");
        }
    }

    public void SetObserwowalnaW() {
        while (true) {
            System.out.print("Obserwowalna wielkość: ");
            Obserowalna_Wielk = reader.nextFloat();
            if (Obserowalna_Wielk >= -26.74 & Obserowalna_Wielk <= 15) {
                SetAbsolutnaW();
                break;
            }
            System.out.println("- Błdne dane, wprowadź ponownie");
        }
    }

    public void SetAbsolutnaW() {
        Absolutna_Wielk = (float) (Obserowalna_Wielk - 5 * (Math.log(Math.log(Odleglosc))));
    }

    public void SetOdleglosc() {
        System.out.print("Odległość w latach świetlnych: ");
        Odleglosc = reader.nextFloat();
        SetAbsolutnaW();
    }

    public void SetGwiazdozbior() {
        System.out.print("Gwiazdozbiór: ");
        Gwiazdozbior = reader.next();
    }

    // Półkule są obsługiwane poprzez boola
    public void SetPolkula() {
        System.out.print("Półkula:" + '\n' + "1. Północna" + '\n' + "2. Połódnoowa" + '\n');
        int choise = reader.nextInt();
        boolean isRight = false;
        while (!isRight)
            switch (choise) {
                case 1:
                    Polkula = true;
                    isRight = true;
                    break;
                case 2:
                    Polkula = false;
                    isRight = true;
                    break;
                default:
                    break;
            }
    }

    public void SetTemperatura() {
        while (true) {
            System.out.print("Temperatura: ");
            Temperatura = reader.nextFloat();
            if (Temperatura >= 2000)
                break;
            System.out.println("- Błdne dane, wprowadź ponownie");
        }
    }

    public void SetMasa() {
        while (true) {
            System.out.print("Masa: ");
            Masa = reader.nextFloat();
            if (Masa >= 0.1 & Masa <= 50)
                break;
            System.out.println("- Błdny dane, wprowadź ponownie");
        }
    }

    // NadajKatalog(Obiekt gwiazdy, pozycja w gwiazozbiorze) nadaje nazwę katalogową w zależności od kolejności dodania w gwiazdozbiorze
    public static void NadajKatalog(Gwiazda gw, int poz) {
        gw.NrNadania = poz;
        gw.KatalogNazwa = AlfabetGrecki[poz] + " " + gw.Gwiazdozbior;

    }

    // GetPolkola() zwraca nazwę pólkuli w stringu
    String GetPolkola() {
        String x;
        if (Polkula)
            x = "Północna";
        else
            x = "Połódniowa";
        return x;
    }

    // FullInfo() wyświetla szczegółowy widok
    public void FullInfo() {
        System.out.printf("%20s %10s %20s %30s %30s %30s %20s %20s %20s %20s", KatalogNazwa, Nazwa, Arrays.toString(Deklinacja), Arrays.toString(Rektascencja), Obserowalna_Wielk, Absolutna_Wielk, Odleglosc, GetPolkola(), Temperatura, Masa);
    }

    // toString() wyświetla uproszczony widok
    @Override
    public String toString() {
        return KatalogNazwa + " - " + Nazwa;
    }
}

