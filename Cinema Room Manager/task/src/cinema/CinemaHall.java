package cinema;

import java.util.ArrayList;

public class CinemaHall {
    private int rows;
    private int seatsInRow;
    private ArrayList<Ticket> tickets;

    public CinemaHall(int rows, int seatsInRow, ArrayList<Ticket> tickets) {
        this.rows = rows;
        this.seatsInRow = seatsInRow;
        this.tickets = tickets;
    }

    public static CinemaHall create(int rows, int seatsInRow) {
        if (rows <= 0) {
            throw new IllegalArgumentException("Minimum number of rows should be 1");
        }

        if (seatsInRow <= 0) {
            throw new IllegalArgumentException("Minimum number of seats in a row should be 1");
        }

        return new CinemaHall(rows, seatsInRow, new ArrayList<>());
    }


    public void print() {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= seatsInRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int y = 1; y <= rows; y++) {
            System.out.print(y);
            for (int x = 1; x <= seatsInRow; x++) {
                Ticket ticket = searchTicket(y, x);
                if (ticket != null) {
                    System.out.print(" B");
                    continue;
                }
                System.out.print(" S");
            }
            System.out.println();
        }
    }

    public Ticket buyTicket(int row, int seatInRow) {
        if (row > rows || row < 0) {
            System.out.println("Wrong input!");
        }

        if (seatInRow > seatsInRow || seatInRow < 0) {
            System.out.println("Wrong input!");
        }

        Ticket existingTicket = searchTicket(row, seatInRow);

        if (existingTicket != null) {
            System.out.println("That ticket has already been purchased!");
            return null;
        }

        int ticketPrice = calculatePrice(row);

        Ticket newTicket = Ticket.create(seatInRow, row, ticketPrice);

        tickets.add(newTicket);

        return newTicket;
    }

    private int currentIncome() {
        int currentIncome = 0;
        for (Ticket ticket : tickets) {
            currentIncome += ticket.getPrice();
        }
        return currentIncome;
    }

    private Ticket searchTicket(int row, int seatInRow) {
        for (Ticket ticket : tickets) {
            if (ticket.getX() == seatInRow && ticket.getY() == row) {
                return ticket;
            }
        }
        return null;
    }

    private int calculatePrice(int chosenRow) {
        int numberOfSeats = rows * seatsInRow;
        int halfOfRows = rows / 2;

        return numberOfSeats < 60 ? 10 :
                chosenRow <= halfOfRows ? 10 : 8;
    }

    private double usageInPercentage() {
        double howManyTickets = 0;
        int howManySeats = rows * seatsInRow;
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                howManyTickets++;
            }
        }
        double usage = howManyTickets / howManySeats * 100;
        return usage;
    }

    private int numberOfPurchasedTickets() {
        int purchasedTickets = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null) {
                purchasedTickets++;
            }
        }
        return purchasedTickets;
    }

    /**
     * metoda possibleMaximumIncome wymaga przerobienia, ponieważ nie podaje możliwej kwoty...liczy tylko ilość miejsc
     * Tak jak pisze w zadaniu, cena za bilet może się różnić w zależności od ilości miejsc w sali
     * <p>
     * Trzeba też napisać metodę odnośnie "current income". Iteracja po ticketach? Tylko jak wtedy wyciągnąć cenę?
     */

    public static int sizeOfCinema(int rows, int seatsInRow) {
        int howManySeats = rows * seatsInRow;
        return howManySeats;
    }

    private int possibleMaximumIncome(int rows, int seatsInRow) {
        int possibleIncome;
        int howManySeats = rows * seatsInRow;

        if (howManySeats <= 60) {
            possibleIncome = howManySeats * 10;
        } else {
            int firstHalf = (rows / 2);
            int secondHalf = rows - firstHalf;
            int firstHalfIncome = firstHalf * seatsInRow * 10;
            int secondHalfIncome = secondHalf * seatsInRow * 8;
            possibleIncome = firstHalfIncome + secondHalfIncome;
        }
        return possibleIncome;
    }

    public void printStatistics() {
        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets());
        System.out.printf("Percentage: %.2f%% %n", usageInPercentage());
        System.out.println("Current income: $" + currentIncome());
        System.out.println("Total income: $" + possibleMaximumIncome(rows, seatsInRow));

    }

}