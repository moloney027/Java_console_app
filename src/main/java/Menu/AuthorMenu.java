package Menu;

import Entity.AuthorEntity;
import Service.AuthorService;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

public class AuthorMenu extends AbstractMenu {

    private final AuthorService authorService = new AuthorService();

    public AuthorMenu() throws ParseException, IOException {

        String choiceAuthor = actionSelection();

        boolean boolAuthor = true;
        while (boolAuthor) {
            switch (choiceAuthor) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти всех авторов");
                    System.out.println("2 - Найти по ID");
                    System.out.println("3 - Найти по ФИО");
                    System.out.println("4 - Найти по дате рождения");
                    System.out.println("5 - Найти по месту рождения");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findAuth = bf.readLine();
                    System.out.println();

                    boolean boolAuthorFind = true;
                    while (boolAuthorFind) {
                        switch (findAuth) {
                            case "1":
                                var listAuthFind = authorService.findAll();
                                if (listAuthFind == null || listAuthFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AuthorEntity auth : listAuthFind) {
                                        System.out.print("\n" + auth + "\n");
                                    }
                                }
                                boolAuthorFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID автора: ");
                                String idAuth = bf.readLine();
                                int idAuthFind;
                                if (tryParseInt(idAuth)) {
                                    idAuthFind = Integer.parseInt(idAuth);
                                } else {
                                    System.out.println("\n'" + idAuth + "' нельзя привести к int!\n");
                                    break;
                                }
                                var authIDFind = authorService.find(idAuthFind);

                                if (authIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + authIDFind + "\n");
                                }
                                boolAuthorFind = false;
                                break;

                            case "3":
                                System.out.print("Введите ФИО автора: ");
                                String nameAuthFind = bf.readLine();
                                var authNameFind = authorService.findByName(nameAuthFind);

                                if (authNameFind == null || authNameFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AuthorEntity auth : authNameFind) {
                                        System.out.print("\n" + auth + "\n");
                                    }
                                }
                                boolAuthorFind = false;
                                break;

                            case "4":
                                System.out.print("Введите дату рождения автора в форате yyyy-mm-dd: ");
                                String dateAuthFind = bf.readLine();
                                Date dateFind;
                                try {
                                    dateFind = Date.valueOf(dateAuthFind);
                                } catch (Exception e) {
                                    System.out.println("\n'" + dateAuthFind + "' нельзя привести к date!\n");
                                    break;
                                }
                                var authDateFind = authorService.findByDate(dateFind);

                                if (authDateFind == null || authDateFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AuthorEntity auth : authDateFind) {
                                        System.out.print("\n" + auth + "\n");
                                    }
                                }
                                boolAuthorFind = false;
                                break;

                            case "5":
                                System.out.print("Введите место рождения автора: ");
                                String placeAuthFind = bf.readLine();
                                var authPlaceFind = authorService.findByPlace(placeAuthFind);

                                if (authPlaceFind == null || authPlaceFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (AuthorEntity auth : authPlaceFind) {
                                        System.out.print("\n" + auth + "\n");
                                    }
                                }
                                boolAuthorFind = false;
                                break;

                            case "0":
                                boolAuthorFind = false;
                                authorMenu = new AuthorMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolAuthorFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить нового автора");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveAuth = bf.readLine();
                    System.out.println();

                    boolean boolAuthSave = true;
                    while (boolAuthSave) {
                        switch (saveAuth) {
                            case "1":
                                AuthorEntity authEntitySave = new AuthorEntity();
                                System.out.println("Введите данные для добавления автора: \n");
                                System.out.print("ФИО автора: ");
                                authEntitySave.setFullName(bf.readLine());

                                System.out.print("Дата рождения (yyyy-mm-dd): ");
                                String dateInput = bf.readLine();
                                Date dateSave;
                                try {
                                    dateSave = Date.valueOf(dateInput);
                                } catch (Exception e) {
                                    System.out.println("\n'" + dateInput + "' нельзя привести к date!\n");
                                    break;
                                }
                                authEntitySave.setDateOfBirth(dateSave);

                                System.out.print("Место рождения автора: ");
                                authEntitySave.setPlaceOfBirth(bf.readLine());

                                boolean actionAuthSave = confirmationOfAction();
                                if (actionAuthSave) {
                                    authorService.save(authEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolAuthSave = false;
                                break;

                            case "0":
                                boolAuthSave = false;
                                authorMenu = new AuthorMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolAuthSave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить автора по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteAuth = bf.readLine();
                    System.out.println();

                    try {
                        switch (deleteAuth) {
                            case "1":
                                System.out.print("Введите ID автора: ");
                                String idDelete = bf.readLine();
                                int idAuthDelete;
                                if (tryParseInt(idDelete)) {
                                    idAuthDelete = Integer.parseInt(idDelete);
                                } else {
                                    System.out.println("\n'" + idDelete + "' нельзя привести к int!\n");
                                    break;
                                }

                                boolean actionAuthDelete = confirmationOfAction();
                                if (actionAuthDelete) {
                                    authorService.delete(idAuthDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                authorMenu = new AuthorMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                break;

                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Обновить все поля");
                    System.out.println("2 - Обновить дату рождения автора");
                    System.out.println("3 - Обновить место рождения автора");
                    System.out.println("4 - Обновить ФИО автора");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateAuth = bf.readLine();
                    System.out.println();

                    try {
                        switch (updateAuth) {
                            case "1":
                                var authIDUpdate = getUpdateEntity(authorService);
                                if (authIDUpdate != null) {
                                    System.out.print("ФИО автора: ");
                                    authIDUpdate.setFullName(bf.readLine());

                                    System.out.print("Дата рождения автора (yyyy-mm-dd): ");
                                    String dateInput = bf.readLine();
                                    Date dateUpdate;
                                    try {
                                        dateUpdate = Date.valueOf(dateInput);
                                    } catch (Exception e) {
                                        System.out.println("\n'" + dateInput + "' нельзя привести к date!\n");
                                        break;
                                    }
                                    authIDUpdate.setDateOfBirth(dateUpdate);

                                    System.out.print("Место рождения автора: ");
                                    authIDUpdate.setPlaceOfBirth(bf.readLine());

                                    boolean actionAuthUpdateAll = confirmationOfAction();
                                    if (actionAuthUpdateAll) {
                                        authorService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "2":
                                authIDUpdate = getUpdateEntity(authorService);
                                if (authIDUpdate != null) {
                                    System.out.print("Дата рождения автора (yyyy-mm-dd): ");
                                    String dateInput = bf.readLine();
                                    Date dateUpdate;
                                    try {
                                        dateUpdate = Date.valueOf(dateInput);
                                    } catch (Exception e) {
                                        System.out.println("\n'" + dateInput + "' нельзя привести к date!\n");
                                        break;
                                    }
                                    authIDUpdate.setDateOfBirth(dateUpdate);

                                    boolean actionAuthUpdateDate = confirmationOfAction();
                                    if (actionAuthUpdateDate) {
                                        authorService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "3":
                                authIDUpdate = getUpdateEntity(authorService);
                                if (authIDUpdate != null) {
                                    System.out.print("Место рождения автора: ");
                                    String place = bf.readLine();
                                    authIDUpdate.setPlaceOfBirth(place);

                                    boolean actionAuthUpdatePlace = confirmationOfAction();
                                    if (actionAuthUpdatePlace) {
                                        authorService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "4":
                                authIDUpdate = getUpdateEntity(authorService);
                                if (authIDUpdate != null) {
                                    System.out.print("ФИО автора: ");
                                    authIDUpdate.setFullName(bf.readLine());

                                    boolean actionAuthUpdateName = confirmationOfAction();
                                    if (actionAuthUpdateName) {
                                        authorService.update(authIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                authorMenu = new AuthorMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "0":
                    boolAuthor = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}