package Menu;

import Service.AbstractService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static String actionSelection() throws IOException {
        System.out.println("\nВыберите одно из следующих действий: ");
        System.out.println("1 - Найти");
        System.out.println("2 - Добавить");
        System.out.println("3 - Удалить");
        System.out.println("4 - Обновить");
        System.out.println("0 - Вернуться назад");

        System.out.print("\nНомер действия: ");
        String num = bf.readLine();

        if (!tryParseInt(num)) {
            System.out.println("\n'" + num + "' нельзя привести к int!\n");
            return actionSelection();
        }
        if (Integer.parseInt(num) >= 0 && Integer.parseInt(num) <= 4) {
            return num;
        } else {
            return actionSelection();
        }
    }

    public static boolean confirmationOfAction() throws IOException {
        System.out.println("\nВы действительно хотите совершить данное действие? ");
        System.out.println("y - yes");
        System.out.println("n - no");
        System.out.print("\nВаш выбор: ");
        String ch = bf.readLine();
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

    public <T> T getUpdateEntity(AbstractService<T> abstractService) throws IOException {
        System.out.print("Введите ID объекта, информацию о котором нужно обновить: ");
        String idUpdate = bf.readLine();
        int idUpdateT = 0;
        if (tryParseInt(idUpdate)) {
            idUpdateT = Integer.parseInt(idUpdate);
        } else {
            System.out.println("\n'" + idUpdate + "' нельзя привести к int!\n");
        }
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
