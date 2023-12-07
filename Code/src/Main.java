import java.util.Scanner;

public class Main {
    private static final String SECRET_KEY = "wauyee";

    public static void main(String[] args) {
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
        Product mountDew = new Product("Mountain Dew", 3.50, "\u001B[92;1mH\u001B[0m", 3, 10);
        Product fuseTea = new Product("Fuse Tea", 2.90, "\u001B[33mH\u001B[0m", 3, 11);
        Product balisto = new Product("Balisto", 2.00, "\u001B[38;5;205m|\u001B[0m", 3, 12);
        Product milkyway = new Product("Milkyway", 2.30, "\u001B[34;2m=\u001B[0m", 3, 13);
        Product raffaello = new Product("Raffaello", 2.50, "\u001B[37m*\u001B[0m", 3, 14);
        Product knoppers = new Product("Knoppers", 1.90, "\u001B[36m#\u001B[0m", 3, 15);

        double[] balance = { 10.0 }; // Initial balance

        String choice;

        do {
            printVendingMachine(twix, mars, snickers, bounty, redbull, cola, pepsi, fanta, sprite, mountDew, fuseTea,
                    balisto, milkyway, raffaello, knoppers);

            System.out.print("Choose a product (1-15 / i / x / s): ");
            choice = scanner.nextLine();

            switch (choice) {
                case "i":
                    printProductList(twix, mars, snickers, bounty, redbull, cola, pepsi, fanta, sprite, mountDew,
                            fuseTea, balisto, milkyway, raffaello, knoppers);
                    break;

                case "x":
                    System.out.println("Exiting...");
                    break;

                case "s":
                    // Secret key function
                    System.out.print("Enter secret key: ");
                    String secretKeyInput = scanner.next();
                    if (validateSecretKey(secretKeyInput)) {
                        secretKeyFunction(scanner, twix, mars, snickers, bounty, redbull, cola, pepsi, fanta, sprite,
                                mountDew, fuseTea, balisto, milkyway, raffaello, knoppers);
                    } else {
                        System.out.println("Invalid secret key. Access denied.");
                    }
                    break;

                default:
                    try {
                        int productNumber = Integer.parseInt(String.valueOf(choice));
                        if (productNumber >= 1 && productNumber <= 15) {
                            processProductSelection(getProductByNumber(productNumber, twix, mars, snickers, bounty,
                                    redbull, cola, pepsi, fanta, sprite, mountDew, fuseTea, balisto, milkyway,
                                    raffaello, knoppers), balance, scanner);
                        } else {
                            System.out.println("Invalid product number. Please choose a number between 1 and 15.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid option.");
                    }
                    break;
            }

        } while (!choice.equals("x"));
    }

    private static void secretKeyFunction(Scanner scanner, Product... products) {
        System.out.println("Secret key function activated. Choose an option:");
        System.out.println("1. Refill stock");
        System.out.println("2. Change product");
        System.out.println("3. Change price");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                refillStock(scanner, products);
                break;

            case 2:
                changeProduct(scanner, products);
                break;

            case 3:
                changePrice(scanner, products);
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    private static void refillStock(Scanner scanner, Product... products) {
        System.out.print("Enter product number to refill stock: ");
        int productNumber = scanner.nextInt();
        Product product = getProductByNumber(productNumber, products);
        if (product != null) {
            System.out.print("Enter the amount to refill: ");
            int amount = scanner.nextInt();
            product.setAmount(product.getAmount() + amount);
            System.out.println("Stock refilled for " + product.getName() + ". New amount: " + product.getAmount());
        } else {
            System.out.println("Invalid product number.");
        }
    }

    private static void changeProduct(Scanner scanner, Product... products) {
        System.out.print("Enter product number to change: ");
        int productNumber = scanner.nextInt();
        Product product = getProductByNumber(productNumber, products);
        if (product != null) {
            System.out.print("Enter new product name: ");
            String newName = scanner.next();
            product.setName(newName);

            System.out.print("Enter new product symbol: ");
            String newSymbol = scanner.next();
            product.setDesign(newSymbol);

            System.out.println("Product changed successfully.");
            System.out.println("New name: " + product.getName());
            System.out.println("New symbol: " + product.getDesign());
        } else {
            System.out.println("Invalid product number.");
        }
    }

    private static void changePrice(Scanner scanner, Product... products) {
        System.out.print("Enter product number to change price: ");
        int productNumber = scanner.nextInt();
        Product product = getProductByNumber(productNumber, products);
        if (product != null) {
            System.out.print("Enter new product price: ");
            double newPrice = scanner.nextDouble();
            product.setPrice(newPrice);
            System.out.println("Price changed successfully. New price: $" + product.getPrice());
        } else {
            System.out.println("Invalid product number.");
        }
    }

    private static boolean validateSecretKey(String input) {
        return input.equals(SECRET_KEY);
    }

    private static void printProductList(Product... products) {
        System.out.println("Products available:");
        for (Product product : products) {
            System.out.println(product.getNumber() + "-" + product.getName());
        }
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
                balance[0] = balance[0] - selectedProduct.getPrice();
                selectedProduct.setAmount(selectedProduct.getAmount() - 1);
                System.out.println("\u001B[32mPurchase successful!\u001B[0m");
                System.out.println(
                        "Balance after purchase: \u001B[32m$" + String.format("%.2f", balance[0]) + "\u001B[0m");
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    @Override
    public String toString() {
        return "Product: " + name + ", Price: $" + price + ", Design: " + design + ", Amount: " + amount;
    }
}