import java.util.Scanner;

/**
 * Класс Main реализует консольное приложение для работы с коллекцией типа множество (SetCollection).
 * Программа предоставляет пользователю меню с возможностями добавления, удаления и проверки элементов,
 * а также поиска максимума, минимума, вывода всех элементов и получения элемента по индексу.
 *
 * Взаимодействие происходит через стандартный ввод/вывод (консоль).
 */

public class Main {
    public static void main(String[] args){

        /**
         * Главный метод запуска программы.
         * Отвечает за отображение меню, обработку пользовательского ввода и выполнение выбранных операций над коллекцией.
         *
         * Поддерживает следующие команды:
         * 1 - Добавить элемент в коллекцию
         * 2 - Удалить элемент из коллекции
         * 3 - Проверить наличие элемента в коллекции
         * 4 - Найти максимальный элемент в коллекции
         * 5 - Найти минимальный элемент в коллекции
         * 6 - Вывести все элементы коллекции
         * 7 - Получить элемент по индексу (адресу)
         * 0 - Выход из программы
         *
         * Ввод обрабатывается с проверкой на корректность типа данных.
         *
         * @param args аргументы командной строки (не используются)
         */
        
        SetCollection collection = new SetCollection();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add element");
            System.out.println("2. Remove element");
            System.out.println("3. Check element");
            System.out.println("4. Find max");
            System.out.println("5. Find min");
            System.out.println("6. Show all elements");
            System.out.println("7. Show address");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");


            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println(" Invalid input. Enter a number.");
                scanner.next();
                continue;
            }

            switch (choice){
                case 1:
                    System.out.print("Enter element: ");
                    collection.addElement(scanner.nextInt());
                    break;
                case 2:
                    System.out.print("Enter element: ");
                    collection.removeElement(scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Enter element: ");
                    System.out.println(collection.contains(scanner.nextInt()) ? "Yes" : "No");
                    break;
                case 4:
                    System.out.println("Maximum: " + collection.findMax());
                    break;
                case 5:
                    System.out.println("Minimum: " + collection.findMin());
                    break;
                case 6:
                    collection.iterate();
                    break;
                case 7:
                    System.out.println("Enter index: ");
                    int id = scanner.nextInt();
                    try{
                        System.out.println("Index:" + id);
                        System.out.println("Element: " + collection.getAddress(id));
                    } catch (IndexOutOfBoundsException e){
                        System.out.println("Invalid index");
                    }
                    break;
                case 0:
                    System.out.println("Exit.");
                    break;
                default:
                    System.out.println("Wrong choice");
            }

        } while (choice != 0);
    }
}
