package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        final int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int seats = scanner.nextInt();

        CinemaHall cinema = CinemaHall.create(rows, seats);


        do {
            PrintingMenu.printMenu();
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    cinema.print();
                    break;
                case 2:
                    Ticket ticket = null;
                    do {
                        System.out.println("Enter a row number:");
                        int enteredRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int enteredSeat = scanner.nextInt();
                        ticket = cinema.buyTicket(enteredRow, enteredSeat);
                    } while (ticket == null);
                    System.out.println("Ticket price: $" + ticket.getPrice());
                    break;
                case 3:
                    cinema.printStatistics();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);


    }
}