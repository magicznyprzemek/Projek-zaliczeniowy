package com.company;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static List<Gwiazda> ListaGwiazd = new ArrayList<>();

    // AddStar() dodaje nowy obiek gwiazdy
    public static void AddStar() {
        Gwiazda NowaGwiazda = new Gwiazda();
        NowaGwiazda.SetNazwa();
        NowaGwiazda.SetDeklinacja();
        NowaGwiazda.SetRektascencja();
        NowaGwiazda.SetObserwowalnaW();
        NowaGwiazda.SetOdleglosc();
        NowaGwiazda.SetGwiazdozbior();
        NowaGwiazda.SetPolkula();
        NowaGwiazda.SetTemperatura();
        NowaGwiazda.SetMasa();
        Gwiazda.NadajKatalog(NowaGwiazda, LiczGwiazdozbiory(NowaGwiazda.Gwiazdozbior) + 1);
        ListaGwiazd.add(NowaGwiazda);
    }

    // LiczGwiazdozbiory(nazwa liczonego gwiazdozbioru) zwraca ilość gwiazd w wybranym gwiazdozbiorze
    public static int LiczGwiazdozbiory(String nazwaGw) {
        List<Gwiazda> matches = ListaGwiazd.stream()
                .filter(h -> h.Gwiazdozbior.equals(nazwaGw))
                .toList();
        return matches.size();
    }

    // PrintFilter(lista zawierająca wyniki) wyświetla szczegółową listę gwiazd spełniających wymogi
    public static void PrintFilter(List<Gwiazda> lista) {
        System.out.printf("%20s %10s %20s %30s %30s %30s %20s %20s %20s %20s", "NAZWA KATALOGOWA", "NAZWA", "DEKLINACJA", "REKTASCENCJA", "OBSERWOWALNA WIELKOŚĆ", "ABSOLUTNA WIELKOŚĆ", "ODLEGŁOŚĆ", "PÓŁKULA", "TEMPERATURA", "MASA" + '\n');
        for (Gwiazda x : lista) {
            x.FullInfo();
            System.out.println();
        }
    }

    // PrintStars - bardzo ogólna lista gwiazd z widocznym podziałem na gwiazdozbiory
    public static void PrintStars() {
        System.out.println("--- lista gwiazd ---");
        String Gw = "";
        Collections.sort(ListaGwiazd, Comparator.comparingInt(p -> p.NrNadania));
        Collections.sort(ListaGwiazd, (o1, o2) -> (o1.Gwiazdozbior.compareTo(o2.Gwiazdozbior)));
        int i = 1;
        for (Gwiazda x : ListaGwiazd) {
            if (Gw.compareTo(x.Gwiazdozbior) != 0) {
                System.out.println("Gwiazdozbiór " + x.Gwiazdozbior);
                i = 1;
            }
            Gwiazda.NadajKatalog(x, i - 1);
            System.out.print("  " + i + ". " + x + "\n");
            Gw = x.Gwiazdozbior;
            i++;
        }
        System.out.println("---------------------");
    }

    // SaveStars(nazwa pliku) - zapis do pliku obiekowego
    public static void SaveStars(String FileName) {
        ObjectOutputStream strumien;
        try {
            strumien = new ObjectOutputStream(new FileOutputStream(FileName));
            for (Gwiazda gwiazda : ListaGwiazd) {
                //  X=ListaGwiazd.get(i);
                strumien.writeObject(gwiazda);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    //Czytaj(nazwa pliku) odczyt z pliku
    public static void Czytaj(String FileName) {
        ObjectInputStream strumien;
        try {
            strumien = new ObjectInputStream(new FileInputStream(FileName));
            Object obj;
            while ((obj = strumien.readObject()) != null) {
                if (obj instanceof Gwiazda) {
                    ListaGwiazd.add((Gwiazda) obj);
                }
            }
        } catch (EOFException ex) {
            // nic
        } catch (Exception e) {
            System.out.print("\n-- brak pliku wejściowego --\n");
        }
    }

    //usun(Nazwa katalogowa pliku) usuwa pasujący obiekt z listy
    public static void usun(String NazwaKatalogowa) {
        Object DoUsuniecia = null;
        for (Gwiazda x : ListaGwiazd) {
            if (NazwaKatalogowa.compareTo(x.KatalogNazwa) == 0) {
                DoUsuniecia = x;
                break;
            }
        }
        if (DoUsuniecia != null)
            ListaGwiazd.remove(DoUsuniecia); //
        PrintStars();
    }

    // main zawiera menu wywoływania funkcji
    public static void main(String[] args) {
        String NazwaPliku = "Gwiazdy";
        Czytaj(NazwaPliku);
        System.out.println();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Lista");
            System.out.println("2. Wprowadź dane");
            System.out.println("3. Edytuj");
            System.out.println("4. Usuń");
            System.out.println("5. Lista z filtrem");
            System.out.println("6. Zapisz zmiany"); // zapisanie zmian do pliku, konieczność robienia tego manualnie pozwala na testowanie bazy do woli, bez konieczności przywracania pliku wejściowego
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    PrintStars();
                    break;
                case 2:
                    AddStar();
                    break;
                case 3:
                    System.out.print("Nazwa Katalogowa edytowanej gwiazdy: ");
                    scanner.nextLine();
                    Edytuj(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Nazwa Katalogowa usuwanej gwiazdy: ");
                    scanner.nextLine();
                    usun(scanner.nextLine());
                    break;
                case 5:
                    Filtruj();
                    break;
                case 6:
                    SaveStars(NazwaPliku);
                    break;
                default:
                    break;
            }
        }
    }

    // Edytuj(Nazwa Katalogowa gwiazdy) edycja pól obiektu gwiazdy
    public static void Edytuj(String NazwaKatalogowa) {
        Gwiazda DoEdycji = null;
        boolean exit = false;
        for (Gwiazda x : ListaGwiazd) {
            if (NazwaKatalogowa.compareTo(x.KatalogNazwa) == 0) {
                DoEdycji = x;
                break;
            }
        }
        if (DoEdycji != null) {
            while (!exit) {
                System.out.println("Edytuj: " + DoEdycji.KatalogNazwa);
                Scanner scanner = new Scanner(System.in);
                System.out.println(" 1. Nazwę");
                System.out.println(" 2. Deklinację");
                System.out.println(" 3. Rektascencję");
                System.out.println(" 4. Obserwowalną wielkośc");
                System.out.println(" 5. Absolutną wielkość");
                System.out.println(" 6. Odległość");
                System.out.println(" 7. Półkulę");
                System.out.println(" 8. Temperaturę");
                System.out.println(" 9. Masę");
                System.out.println("10. Zakończ edycję");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        DoEdycji.SetNazwa();
                        break;
                    case 2:
                        DoEdycji.SetDeklinacja();
                        break;
                    case 3:
                        DoEdycji.SetRektascencja();
                        break;
                    case 4:
                        DoEdycji.SetObserwowalnaW();
                        break;
                    case 5:
                        DoEdycji.SetAbsolutnaW();
                        break;
                    case 6:
                        DoEdycji.SetOdleglosc();
                        break;
                    case 7:
                        DoEdycji.SetPolkula();
                        break;
                    case 8:
                        DoEdycji.SetTemperatura();
                        break;
                    case 9:
                        DoEdycji.SetMasa();
                        break;
                    case 10:
                        exit = true;
                        break;
                    default:
                        break;
                }
            }
        } else
            System.out.println("nie znaleziono " + NazwaKatalogowa);
    }

    //  Filtruj() zestaw opcji filtrowania listy
    public static void Filtruj() {
        System.out.println("Wyświetl:");
        Scanner scanner = new Scanner(System.in);
        List<Gwiazda> ListaWynikow;
        System.out.println(" 1. Wszystkie gwiazdy w gwiazdozbiorze");
        System.out.println(" 2. Gwiazdy w podanej odległości");
        System.out.println(" 3. Gwiazdy z temperaturą z podanego przedziału");
        System.out.println(" 4. Gwiazdy o rozmiarach z podanego przedziału");
        System.out.println(" 5. Gwiazdy z pólkuli północnej / połudiowej");
        System.out.println(" 6. Potencjalne supernowe");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Nazwa gwiazdozbioru: ");
                String Nazwa = scanner.next();
                ListaWynikow = ListaGwiazd.stream()
                        .filter(p -> p.Gwiazdozbior.compareTo(Nazwa) == 0)
                        .collect(Collectors.toList());
                PrintFilter(ListaWynikow);
                break;
            case 2:
                System.out.println("Minimalna odległość od gwiazdy (w parsekach): ");
                float minO = (float) (scanner.nextFloat() / 3.2616); //1 pc ≈ 3,2616 roku świetlnego
                System.out.println("Maksymalna odległość od gwiazdy (w parsekach): ");
                float maxO = (float) (scanner.nextFloat() / 3.2616);
                ListaWynikow = ListaGwiazd.stream()
                        .filter(p -> p.Odleglosc >= minO & p.Odleglosc <= maxO)
                        .collect(Collectors.toList());
                PrintFilter(ListaWynikow);
                break;
            case 3:
                System.out.println("Minimalna temperatura gwiazdy: ");
                float minT = scanner.nextFloat();
                System.out.println("Maksymalna temperatura gwiazdy: ");
                float maxT = scanner.nextFloat();
                ListaWynikow = ListaGwiazd.stream()
                        .filter(p -> p.Odleglosc >= minT & p.Odleglosc <= maxT)
                        .collect(Collectors.toList());
                PrintFilter(ListaWynikow);
                break;
            case 4:
                System.out.println("Minimalna wielkość gwiazdy: ");
                float minW = scanner.nextFloat();
                System.out.println("Maksymalna wielkość gwiazdy: ");
                float maxW = scanner.nextFloat();
                ListaWynikow = ListaGwiazd.stream()
                        .filter(p -> p.Odleglosc >= minW & p.Odleglosc <= maxW)
                        .collect(Collectors.toList());
                PrintFilter(ListaWynikow);
                break;
            case 5:
                System.out.println("Pólkula gwiazdy: \n 1. Północna \n 2. Południowa");
                int plk = scanner.nextInt();
                boolean plk1;
                if (plk == 1)
                    plk1 = true;
                else if (plk == 2)
                    plk1 = false;
                else {
                    System.out.println("-Błędny wybór-");
                    break;
                }
                boolean finalPlk = plk1;
                ListaWynikow = ListaGwiazd.stream()
                        .filter(p -> p.Polkula == finalPlk)
                        .collect(Collectors.toList());
                PrintFilter(ListaWynikow);
                break;
            case 6:
                ListaWynikow = ListaGwiazd.stream()
                        .filter(p -> p.Masa >= 1.44)
                        .collect(Collectors.toList());
                PrintFilter(ListaWynikow);
                break;
            default:
                break;
        }
    }
}
