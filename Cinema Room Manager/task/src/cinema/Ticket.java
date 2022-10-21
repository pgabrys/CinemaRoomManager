package cinema;

public class Ticket {
    private int x;
    private int y;
    private int price;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPrice() {
        return price;
    }

    private Ticket(int x, int y, int price) {
        this.x = x;
        this.y = y;
        this.price = price;
    }

    public static Ticket create(int x, int y, int price) {
        if (x < 0) {
            throw new IllegalArgumentException("Wrong input!");
        }

        if (y < 0) {
            throw new IllegalArgumentException("Wrong input!");
        }

        if (price <= 0) {
            throw new IllegalArgumentException("Ticket price can't be lower than 1$");
        }

        return new Ticket(x, y, price);
    }
}