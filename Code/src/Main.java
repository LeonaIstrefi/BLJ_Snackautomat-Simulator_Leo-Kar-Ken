import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char choice;

        Scanner scanner = new Scanner(System.in);

        Product twix = new Product("Twix", 2.30, "\u001B[38;5;220m=\u001B[0m", 3, 1);
        Product mars = new Product("Mars", 2.30, "\u001B[30m=\u001B[0m", 3, 2);
        Product snickers = new Product("Snickers", 2.30, "\u001B[38;5;94m=\u001B[0m", 3, 3);
        Product bounty = new Product("Bounty", 2.30, "\u001B[36m=\u001B[0m", 3, 4);
        Product redbull = new Product("Redbull", 3.90, "\u001B[34;2mU\u001B[0m", 3, 5);
        Product cola = new Product("Coca Cola", 3.50, "\u001B[31mH\u001B[0m", 3, 6);
        Product pepsi = new Product("Pepsi", 3.50, "\u001B[34mH\u001B[0m", 3, 7);
        Product fanta = new Product("Fanta", 3.50, "\u001B[38;5;208mH\u001B[0m", 3, 8);
        Product sprite = new Product("Sprite", 3.50, "\u001B[32mH\u001B[0m", 3, 9);
        Product mountDew = new Product("Mount Dew", 3.50, "\u001B[92;1mH\u001B[0m", 3, 10);
        Product fuseTea = new Product("Fuse Tea", 2.90, "\u001B[33mH\u001B[0m", 3, 11);
        Product balisto = new Product("Balisto", 2.00, "\u001B[38;5;205m|\u001B[0m", 3, 12);
        Product milkyway = new Product("Milkyway", 2.30, "\u001B[34;2m=\u001B[0m", 3, 13);
        Product raffaello = new Product("Raffaello", 2.50, "\u001B[37m*\u001B[0m", 3, 14);
        Product knoppers = new Product("Knoppers", 1.90, "\u001B[36m#\u001B[0m", 3, 15);

        double[] balance = {10.0};

        do {
            printVendingMachine(twix, mars, snickers, bounty, redbull, cola, pepsi, fanta, sprite, mountDew, fuseTea, balisto, milkyway, raffaello, knoppers);

            System.out.print("Choose a product (1-15 / i / x): ");
            choice = scanner.next().charAt(0);

            switch (choice) {
                case 'i':
                    printProductList();
                    break;

                case 'x':
                    System.out.println("Exiting...");
                    break;

                default:
                    try {
                        int productNumber = Integer.parseInt(String.valueOf(choice));
                        if (productNumber >= 1 && productNumber <= 15) {
                            processProductSelection(getProductByNumber(productNumber, twix, mars, snickers, bounty,
                                    redbull, cola, pepsi, fanta, sprite, mountDew, fuseTea, balisto, milkyway, raffaello,
                                    knoppers), balance, scanner);
                        } else {
                            System.out.println("Invalid product number. Please choose a number between 1 and 15.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid option.");
                    }
                    break;
            }

        } while (choice != 'x');
    }

    private static void printProductList() {
        System.out.println("Products available:");
        System.out.println("1-Twix");
        System.out.println("2-Mars");
        System.out.println("3-Snickers");
        System.out.println("4-Bounty");
        System.out.println("5-Redbull");
        System.out.println("6-Coca Cola");
        System.out.println("7-Pepsi");
        System.out.println("8-Fanta");
        System.out.println("9-Sprite");
        System.out.println("10-Mountain Dew");
        System.out.println("11-Fuse Tea");
        System.out.println("12-Balisto");
        System.out.println("13-Milkyway");
        System.out.println("14-Raffaello");
        System.out.println("15-Knoppers");
    }

    private static void printVendingMachine(Product... products) {
        System.out.println("\u001B[31m***********\u001B[0m");
        System.out.println("\u001B[31m***\u001B[37m|KLK|\u001B[31m***\u001B[0m");
        System.out.println("\u001B[31m***********\u001B[0m");
        System.out.print("\u001B[31m*\u001B[0m");

        for (int i = 0; i < 15; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("\u001B[31m*\u001B[0m");
                System.out.print("\u001B[31m*\u001B[0m");
            }

            if (i < products.length) {
                Product currentProduct = products[i];
                if (currentProduct.getAmount() > 0) {
                    System.out.print("\u001B[90m|\u001B[0m" + currentProduct.getDesign() + "\u001B[90m|\u001B[0m");
                } else {
                    System.out.print("\u001B[90m|\u001B[0m \u001B[90m|\u001B[0m");
                }
            }
        }

        System.out.println("\u001B[31m*\u001B[0m");
        System.out.println("\u001B[31m***********\u001B[0m");
        System.out.println("\u001B[31m*\u001B[37m|_____|\u001B[31m*\u001B[32m$\u001B[31m*\u001B[0m");
        System.out.println("\u001B[31m***********\u001B[0m");
    }

    private static Product getProductByNumber(int number, Product... products) {
        for (Product product : products) {
            if (product.getNumber() == number) {
                return product;
            }
        }
        return null;
    }

    private static void processProductSelection(Product selectedProduct, double[] balance, Scanner scanner) {
        if (selectedProduct != null && selectedProduct.getAmount() > 0 && selectedProduct.getPrice() <= balance[0]) {
            System.out.println("Selected product: " + selectedProduct.getName());
            System.out.println("Price: $" + selectedProduct.getPrice());
            System.out.print("Do you want to purchase this item? (y/n): ");
            char purchaseConfirmation = scanner.next().charAt(0);
            if (purchaseConfirmation == 'y') {
                balance[0] -= selectedProduct.getPrice();
                selectedProduct.setAmount(selectedProduct.getAmount() - 1);
                System.out.println("\u001B[32mPurchase successful!\u001B[0m");
                System.out.println("Balance after purchase: \u001B[32m$" + String.format("%.2f", balance[0]) + "\u001B[0m");
            } else if (purchaseConfirmation == 'n') {
                System.out.println("\u001B[31mPurchase canceled.\u001B[0m");
            } else {
                System.out.println("Invalid input. Purchase canceled.");
            }
        } else {
            if (selectedProduct == null) {
                System.out.println("Invalid product number. Please choose a number between 1 and 15.");
            } else {
                System.out.println("Sorry, the selected product is not available or you don't have enough balance.");
            }
        }
    }
}

class Product {
    private String name;
    private double price;
    private String design;
    private int amount;
    private int number;

    public Product(String name, double price, String design, int amount, int number) {
        this.name = name;
        this.price = price;
        this.design = design;
        this.amount = amount;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDesign() {
        return design;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Product: " + name + ", Price: $" + price + ", Design: " + design + ", Amount: " + amount;
    }
}