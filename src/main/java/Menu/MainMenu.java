package Menu;

import java.io.IOException;
import java.text.ParseException;

public class MainMenu extends AbstractMenu {

    public MainMenu() throws ParseException, IOException {

        boolean boolTable = true;
        while (boolTable) {
            System.out.println("\nВыберите одну из следующих таблиц: ");
            System.out.println("1 - Адаптации");
            System.out.println("2 - Авторы");
            System.out.println("3 - Экземпляры книги");
            System.out.println("4 - Книги");
            System.out.println("5 - Выдачи книг");
            System.out.println("6 - Штрафы");
            System.out.println("7 - Жанры");
            System.out.println("8 - Издательства");
            System.out.println("9 - Читатели");
            System.out.println("0 - Выход\n");

            System.out.print("Номер таблицы: ");
            String numTable = bf.readLine();

            switch (numTable) {
                case "1":
                    adaptationsMenu = new AdaptationsMenu();
                    break;

                case "2":
                    authorMenu = new AuthorMenu();
                    break;

                case "3":
                    bookCopyMenu = new BookCopyMenu();
                    break;

                case "4":
                    bookMenu = new BookMenu();
                    break;

                case "5":
                    bookIssuanceMenu = new BookIssuanceMenu();
                    break;

                case "6":
                    fineMenu = new FineMenu();
                    break;

                case "7":
                    genreMenu = new GenreMenu();
                    break;

                case "8":
                    publishingHouseMenu = new PublishingHouseMenu();
                    break;

                case "9":
                    readersMenu = new ReadersMenu();
                    break;

                case "0":
                    boolTable = false;
                    startMenu = new StartMenu();
                    break;

                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }
}
