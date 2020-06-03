package Menu;

import Entity.BookCopyEntity;
import Service.BookCopyService;
import Service.BookService;

import java.io.IOException;
import java.text.ParseException;

public class BookCopyMenu extends AbstractMenu {

    private final BookCopyService bookCopyService = new BookCopyService();

    public BookCopyMenu() throws IOException, ParseException {

        String choiceCopy = actionSelection();

        boolean boolCopy = true;
        while (boolCopy) {
            switch (choiceCopy) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти все экземпляры");
                    System.out.println("2 - Найти экземпляр по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findCopy = in.next();
                    System.out.println();

                    boolean boolCopyFind = true;
                    while (boolCopyFind) {
                        switch (findCopy) {
                            case "1":
                                var listCopyFind = bookCopyService.findAll();
                                if (listCopyFind == null || listCopyFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookCopyEntity copy : listCopyFind) {
                                        System.out.print("\n" + copy + "\n");
                                    }
                                }
                                boolCopyFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID экземпляра: ");
                                int idCopyFind = in.nextInt();
                                System.out.println();
                                var copyIDFind = bookCopyService.find(idCopyFind);
                                if (copyIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + copyIDFind + "\n");
                                }
                                boolCopyFind = false;
                                break;

                            case "0":
                                boolCopyFind = false;
                                bookCopyMenu = new BookCopyMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolCopyFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить новый экземпляр");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveCopy = in.next();
                    System.out.println();

                    boolean boolCopySave = true;
                    while (boolCopySave) {
                        switch (saveCopy) {
                            case "1":
                                BookService bookService = new BookService();
                                BookCopyEntity copyEntitySave = new BookCopyEntity();
                                System.out.println("Введите данные для добавления экземпляра: \n");
                                System.out.print("ID связанной книги: ");
                                String inputBook = in.next();
                                System.out.println();
                                int idBook;
                                if (tryParseInt(inputBook)) {
                                    idBook = Integer.parseInt(inputBook);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    boolCopySave = false;
                                    break;
                                }
                                copyEntitySave.setBookForCopy(bookService.find(idBook));
                                System.out.println();

                                boolean actionCopySave = confirmationOfAction();
                                if (actionCopySave) {
                                    bookCopyService.save(copyEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolCopySave = false;
                                break;

                            case "0":
                                boolCopySave = false;
                                bookCopyMenu = new BookCopyMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolCopySave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить экземпляр по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteCopy = in.next();
                    System.out.println();

                    try {
                        switch (deleteCopy) {
                            case "1":
                                System.out.print("Введите ID экземпляра: ");
                                int idCopyDelete = in.nextInt();
                                System.out.println();
                                boolean actionCopyDelete = confirmationOfAction();
                                if (actionCopyDelete) {
                                    bookCopyService.delete(idCopyDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                bookCopyMenu = new BookCopyMenu();
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
                    System.out.println("1 - Обновить связанную книгу");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateAuth = in.next();
                    System.out.println();

                    try {
                        switch (updateAuth) {
                            case "1":
                                BookService bookService = new BookService();
                                var copyIDUpdate = getUpdateEntity(bookCopyService);
                                if (copyIDUpdate != null) {
                                    System.out.print("ID связанной книги: ");
                                    String inputBook = in.next();
                                    System.out.println();
                                    int idBook;
                                    if (tryParseInt(inputBook)) {
                                        idBook = Integer.parseInt(inputBook);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    copyIDUpdate.setBookForCopy(bookService.find(idBook));
                                    boolean actionCopyUpdate = confirmationOfAction();
                                    if (actionCopyUpdate) {
                                        bookCopyService.update(copyIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                bookCopyMenu = new BookCopyMenu();
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
                    boolCopy = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
