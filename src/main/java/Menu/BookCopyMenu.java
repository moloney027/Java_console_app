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
                    String findCopy = bf.readLine();
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
                                String idCopy = bf.readLine();
                                int idCopyFind;
                                if (tryParseInt(idCopy)) {
                                    idCopyFind = Integer.parseInt(idCopy);
                                } else {
                                    System.out.println("\n'" + idCopy + "' нельзя привести к int!\n");
                                    break;
                                }
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
                    String saveCopy = bf.readLine();
                    System.out.println();

                    boolean boolCopySave = true;
                    while (boolCopySave) {
                        switch (saveCopy) {
                            case "1":
                                BookService bookService = new BookService();
                                BookCopyEntity copyEntitySave = new BookCopyEntity();
                                System.out.println("Введите данные для добавления экземпляра: \n");

                                System.out.print("ID связанной книги: ");
                                String inputBook = bf.readLine();
                                int idBook;
                                if (tryParseInt(inputBook)) {
                                    idBook = Integer.parseInt(inputBook);
                                } else {
                                    System.out.println("\n'" + inputBook + "' нельзя привести к int!\n");
                                    boolCopySave = false;
                                    break;
                                }
                                copyEntitySave.setBookForCopy(bookService.find(idBook));

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
                    String deleteCopy = bf.readLine();
                    System.out.println();

                    try {
                        switch (deleteCopy) {
                            case "1":
                                System.out.print("Введите ID экземпляра: ");
                                String idDelete = bf.readLine();
                                int idCopyDelete;
                                if (tryParseInt(idDelete)) {
                                    idCopyDelete = Integer.parseInt(idDelete);
                                } else {
                                    System.out.println("\n'" + idDelete + "' нельзя привести к int!\n");
                                    break;
                                }

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
                    String updateAuth = bf.readLine();
                    System.out.println();

                    try {
                        switch (updateAuth) {
                            case "1":
                                BookService bookService = new BookService();
                                var copyIDUpdate = getUpdateEntity(bookCopyService);
                                if (copyIDUpdate != null) {
                                    System.out.print("ID связанной книги: ");
                                    String inputBook = bf.readLine();
                                    int idBook;
                                    if (tryParseInt(inputBook)) {
                                        idBook = Integer.parseInt(inputBook);
                                    } else {
                                        System.out.println("\n'" + inputBook + "' нельзя привести к int!\n");
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
