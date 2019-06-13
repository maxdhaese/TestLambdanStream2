package be.intecbrussel.lambdatest.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

public class BookApp {
    public static void main(String[] args) {

        Person andyWeir = new Person("Andy","Weir", LocalDate.of(1972,6,16));
        Person sarahJMaas = new Person("Sarah","J.Maas", LocalDate.of(1986,3,5));
        Person kristinHannah = new Person("Kristin","Hannah", LocalDate.of(1960,9,25));
        Person suzanneCollins = new Person("Suzanne","Collins", LocalDate.of(1962,8,10));
        Person joanneRamos = new Person("Joanne","Ramos", LocalDate.of(1980,4,19));






        Book theMartian = new Book("Martian",andyWeir,LocalDate.of(2014,2,11),"ScienceFiction");
        Book empireOfStorms = new Book("Empire of Storms",sarahJMaas,LocalDate.of(2016,9,6),"Fiction");
        Book theGreatAlone = new Book("Great Alone",kristinHannah,LocalDate.of(2018,2,6),"Fiction");
        Book mockingJay = new Book("MockingJay", suzanneCollins,LocalDate.of(2010,8,24),"Adventure");
        Book theFarm = new Book("Farm", joanneRamos,LocalDate.of(2019,5,7),"Literair Fiction");

        Book [] books = new Book[]{theMartian,empireOfStorms,theGreatAlone,theFarm,mockingJay};
        System.out.println(getNewestBook(books));
        fancyLines();
        printYoungestWriter(books);
        fancyLines();
        printSortedByTitle(books);
        fancyLines();
        countBooksPerAuthor(books);
        fancyLines();
        printBooksReleasedIn2016(books);





    }

    public static Optional<Book> getNewestBook(Book[] books){
        return  Stream.of(books)
                .sorted(Comparator.comparing(Book::getReleaseDate))
                .reduce((a,b)->b);

    }

    public static void printYoungestWriter(Book[] books){
        System.out.println(Arrays.stream(books)
                .map(Book::getAuthor)
                .sorted(Comparator.comparing(Person::getDateOfBirth).reversed())
                .findFirst());


    }

    public static void printSortedByTitle(Book[] books){
        Stream.of(books)
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);

    }

    public static void countBooksPerAuthor(Book[] books){
        Map<Book, Integer> hm = new HashMap<>();

        for (Book i : books) {
            Integer j = hm.get(i);
            hm.put(i,(j==null)? 1: j+ 1);

        }
        for (Map.Entry<Book,Integer> val : hm.entrySet()){
            System.out.println(val.getKey().getAuthor().getFirstName() + " " + val.getKey().getAuthor().getLastName()+ ": " + val.getValue() );
        }

    }

    public static void printBooksReleasedIn2016(Book[] books){
        LocalDate dt = LocalDate.of(2016,5,5);
        for (Book book : books) {
            if (book.getReleaseDate().getYear() == dt.getYear()){
                System.out.println(book);
            }

        }

    }

    public static void fancyLines(){
        System.out.println("-----------------------------------------------------");
    }
}
