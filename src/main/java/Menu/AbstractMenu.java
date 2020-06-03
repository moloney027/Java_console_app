package Menu;

import Service.AbstractService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public abstract class AbstractMenu {

    StartMenu startMenu;
    LoginMenu loginMenu;
    MainMenu mainMenu;
    AdaptationsMenu adaptationsMenu;
    AuthorMenu authorMenu;
    BookCopyMenu bookCopyMenu;
    BookIssuanceMenu bookIssuanceMenu;
    BookMenu bookMenu;
    FineMenu fineMenu;
    GenreMenu genreMenu;
    PublishingHouseMenu publishingHouseMenu;
    ReadersMenu readersMenu;

    static Scanner in = new Scanner(System.in);
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static String actionSelection() {
        System.out.println("\nВыберите одно из следующих действий: ");
        System.out.println("1 - Найти");
        System.out.println("2 - Добавить");
        System.out.println("3 - Удалить");
        System.out.println("4 - Обновить");
        System.out.println("0 - Вернуться назад");

        System.out.print("\nНомер действия: ");
        String num = in.next();

        if (!tryParseInt(num)) {
            System.out.println("\nВведено неверное значение!");
            return actionSelection();
        }
        if (Integer.parseInt(num) >= 0 && Integer.parseInt(num) <= 4) {
            return num;
        } else {
            return actionSelection();
        }
    }

    public static boolean confirmationOfAction() {
        System.out.println("\nВы действительно хотите совершить данное действие? ");
        System.out.println("y - yes");
        System.out.println("n - no");
        System.out.print("\nВаш выбор: ");
        String ch = in.next();
        System.out.println();
        return ch.equals("y");
    }

    static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public <T> T getUpdateEntity(AbstractService<T> abstractService) {
        System.out.print("Введите ID объекта, информацию о котором нужно обновить: ");
        int idUpdateT = in.nextInt();
        var tIDUpdate = abstractService.find(idUpdateT);
        if (tIDUpdate != null) {
            System.out.println("\n" + tIDUpdate + "\n");
            return tIDUpdate;
        } else {
            System.out.println("\nПо запросу ничего не найдено\n");
            return null;
        }
    }
}
