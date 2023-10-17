package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        char[][] cinema = new char[rows][seats]; // Create the cinema array

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = 'S'; // Initialize all seats as 'S'
            }
        }

        int currentIncome = 0;
        int totalIncome = 0;
        int frontPrice = 10;
        int backPrice = 8;
        int ticketsSold = 0;
        int choice;
        int numberOfSeats = rows * seats;
        double percentSold;

        if (numberOfSeats <= 60) {
            totalIncome = numberOfSeats * frontPrice;
        } else {
            int frontRows = rows / 2;
            int backRows = rows - frontRows;
            int frontSeats = frontRows * seats;
            totalIncome = frontRows * seats * frontPrice + backRows * seats * backPrice;
        }

        do {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("\nCinema:");
                printCinema(cinema);
            } else if (choice == 2) {
                System.out.println("\nEnter a row number:");
                int rowSelect;
                int seatSelect;

                while (true) {
                    rowSelect = scanner.nextInt();
                    System.out.println("\nEnter a seat number in that row:");
                    seatSelect = scanner.nextInt();

                    if (rowSelect >= 1 && rowSelect <= rows && seatSelect >= 1 && seatSelect <= seats) {
                        if (cinema[rowSelect - 1][seatSelect - 1] == 'B') {
                            System.out.println("\nThat ticket has already been purchased!\n");
                            System.out.println("\nEnter a row number:");
                        } else {
                            cinema[rowSelect - 1][seatSelect - 1] = 'B';
                            ticketsSold++;
                            //If the total number of seats in the screen room is not more than 60, then the price of each ticket is 10 dollars.
                            //In a larger room, the tickets are 10 dollars for the front half of the rows and 8 dollars for the back half.
                            // Please note that the number of rows can be odd, for example, 9 rows.
                            // In this case, the first half is the first 4 rows, and the second half is the other 5 rows.
                            // Calculate the ticket price
                            if (numberOfSeats <= 60) {
                                System.out.println("\nTicket price: $" + frontPrice);
                                currentIncome += frontPrice;
                            } else {
                                if (rowSelect <= rows / 2) {
                                    System.out.println("\nTicket price: $" + frontPrice);
                                    currentIncome += frontPrice;
                                } else {
                                    System.out.println("\nTicket price: $" + backPrice);
                                    currentIncome += backPrice;
                                }
                            }
                            percentSold = (double) ticketsSold / numberOfSeats * 100;
                            System.out.printf("Percent sold: %.2f%%\n", percentSold);
                            break; // Exit the loop after a successful purchasexit the loop after a successful purchase
                        }
                    } else {
                        System.out.println("\nWrong input!\n");
                        System.out.println("\nEnter a row number.\n");

                    }
                }
            } else if (choice == 3) {
                System.out.println("\nNumber of purchased tickets: " + ticketsSold);
                percentSold = (double) ticketsSold / numberOfSeats * 100;
                System.out.printf("Percentage: %.2f%%\n", percentSold);
                System.out.println("Current income: $" + currentIncome);
                System.out.println("Total income: $" + totalIncome);
            } else if (choice > 3) {
                System.out.println("Invalid selection, try again!");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void printCinema(char[][] cinema) {
        System.out.print("  ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < cinema[0].length; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
}
