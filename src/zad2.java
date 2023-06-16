import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private int nr_indeksu;
    private String imie;
    private String nazwisko;
    private int rok_st;
    private List<Double> oceny;

    public Student(int nr_indeksu, String imie, String nazwisko, int rok_st) {
        this.nr_indeksu = nr_indeksu;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_st = rok_st;
        this.oceny = new ArrayList<>();
    }

    public int getNr_indeksu() {
        return nr_indeksu;
    }

    public void setNr_indeksu(int nr_indeksu) {
        this.nr_indeksu = nr_indeksu;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getRok_st() {
        return rok_st;
    }

    public void setRok_st(int rok_st) {
        this.rok_st = rok_st;
    }

    public List<Double> getOceny() {
        return oceny;
    }

    public void setOceny(List<Double> oceny) {
        this.oceny = oceny;
    }

    public void wyswietlDane() {
        System.out.println("Student: " + imie + " " + nazwisko);
        System.out.println("Nr indeksu: " + nr_indeksu);
        System.out.println("Rok studiów: " + rok_st);
        System.out.println("Oceny: " + oceny);
    }
}

class Uni {
    private List<Double> listaDopuszczalnychOcen;
    private List<Student> listaStudentow;

    public Uni() {
        listaDopuszczalnychOcen = new ArrayList<>();
        listaDopuszczalnychOcen.add(2.0);
        listaDopuszczalnychOcen.add(3.0);
        listaDopuszczalnychOcen.add(3.5);
        listaDopuszczalnychOcen.add(4.0);
        listaDopuszczalnychOcen.add(4.5);
        listaDopuszczalnychOcen.add(5.0);

        listaStudentow = new ArrayList<>();
    }

    public void dodajStudenta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj numer indeksu:");
        int nr_indeksu = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj imię:");
        String imie = scanner.nextLine();

        System.out.println("Podaj nazwisko:");
        String nazwisko = scanner.nextLine();

        System.out.println("Podaj rok studiów:");
        int rok_st = scanner.nextInt();

        Student student = new Student(nr_indeksu, imie, nazwisko, rok_st);

        System.out.println("Podaj oceny (wpisz -1 aby zakończyć):");
        double ocena;
        while (true) {
            ocena = scanner.nextDouble();
            if (ocena == -1) {
                break;
            }

            if (listaDopuszczalnychOcen.contains(ocena)) {
                student.getOceny().add(ocena);
            } else {
                System.out.println("Błędna ocena. Podaj ocenę z listy dopuszczalnych ocen.");
            }
        }

        listaStudentow.add(student);
    }

    public void usunStudenta(int nr_indeksu) {
        for (int i = 0; i < listaStudentow.size(); i++) {
            if (listaStudentow.get(i).getNr_indeksu() == nr_indeksu) {
                listaStudentow.remove(i);
                System.out.println("Usunięto studenta o numerze indeksu: " + nr_indeksu);
                return;
            }
        }

        System.out.println("Nie znaleziono studenta o numerze indeksu: " + nr_indeksu);
    }

    public void obliczSrednia(int nr_indeksu) {
        for (Student student : listaStudentow) {
            if (student.getNr_indeksu() == nr_indeksu) {
                List<Double> oceny = student.getOceny();

                if (oceny.isEmpty()) {
                    System.out.println("Brak ocen dla studenta o numerze indeksu: " + nr_indeksu);
                } else {
                    double suma = 0;
                    for (double ocena : oceny) {
                        suma += ocena;
                    }

                    double srednia = suma / oceny.size();
                    System.out.println("Średnia ocen studenta o numerze indeksu " + nr_indeksu + ": " + srednia);
                }

                return;
            }
        }

        System.out.println("Nie znaleziono studenta o numerze indeksu: " + nr_indeksu);
    }

    public void obliczSredniaAll() {
        if (listaStudentow.isEmpty()) {
            System.out.println("Brak studentów na liście.");
        } else {
            double suma = 0;
            int iloscOcen = 0;

            for (Student student : listaStudentow) {
                List<Double> oceny = student.getOceny();
                suma += oceny.stream().mapToDouble(Double::doubleValue).sum();
                iloscOcen += oceny.size();
            }

            if (iloscOcen == 0) {
                System.out.println("Brak ocen dla studentów.");
            } else {
                double srednia = suma / iloscOcen;
                System.out.println("Średnia ocen wszystkich studentów: " + srednia);
            }
        }
    }
}

public class zad2 {
    public static void main(String[] args) {
        Uni uni = new Uni();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Dodaj studenta");
            System.out.println("2. Usuń studenta");
            System.out.println("3. Oblicz średnią ocenę studenta");
            System.out.println("4. Oblicz średnią ocenę wszystkich studentów");
            System.out.println("0. Zakończ program");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    uni.dodajStudenta();
                    break;
                case 2:
                    System.out.println("Podaj numer indeksu studenta do usunięcia:");
                    int nr_indeksu = scanner.nextInt();
                    uni.usunStudenta(nr_indeksu);
                    break;
                case 3:
                    System.out.println("Podaj numer indeksu studenta, dla którego chcesz obliczyć średnią:");
                    int nr_indeksuS = scanner.nextInt();
                    uni.obliczSrednia(nr_indeksuS);
                    break;
                case 4:
                    uni.obliczSredniaAll();
                    break;
                case 0:
                    System.out.println("Program zakończony.");
                    return;
                default:
                    System.out.println("Nieprawidłowa opcja. Wybierz ponownie.");
            }
        }
    }
}