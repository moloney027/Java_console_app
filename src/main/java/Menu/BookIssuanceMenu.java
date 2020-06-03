package Menu;

import Entity.BookIssuanceEntity;
import Service.BookCopyService;
import Service.BookIssuanceService;
import Service.ReadersService;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

public class BookIssuanceMenu extends AbstractMenu {

    private final BookIssuanceService bookIssueService = new BookIssuanceService();

    public BookIssuanceMenu() throws IOException, ParseException {

        String choiceIssue = actionSelection();

        boolean boolIssue = true;
        while (boolIssue) {
            switch (choiceIssue) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти все выдачи");
                    System.out.println("2 - Найти выдачу по ID");
                    System.out.println("3 - Найти по дате выдачи");
                    System.out.println("4 - Найти по дате сдачи");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findIssue = in.next();
                    System.out.println();

                    boolean boolIssueFind = true;
                    while (boolIssueFind) {
                        switch (findIssue) {
                            case "1":
                                var listIssueFind = bookIssueService.findAll();
                                if (listIssueFind == null || listIssueFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookIssuanceEntity issue : listIssueFind) {
                                        System.out.print("\n" + issue + "\n");
                                    }
                                }
                                boolIssueFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID выдачи: ");
                                int idIssueFind = in.nextInt();
                                System.out.println();
                                var issueIDFind = bookIssueService.find(idIssueFind);
                                if (issueIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + issueIDFind + "\n");
                                }
                                boolIssueFind = false;
                                break;

                            case "3":
                                System.out.print("Введите дату выдачи в форате yyyy-mm-dd: ");
                                String dateIssueFind = in.next();
                                Date dateFind;
                                try {
                                    dateFind = Date.valueOf(dateIssueFind);
                                } catch (Exception e) {
                                    System.out.println("\nНеверно введено значение\n");
                                    break;
                                }
                                System.out.println();
                                var issueDateFind = bookIssueService.findByDateOfIssue(dateFind);
                                if (issueDateFind == null || issueDateFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookIssuanceEntity issue : issueDateFind) {
                                        System.out.print("\n" + issue + "\n");
                                    }
                                }
                                boolIssueFind = false;
                                break;

                            case "4":
                                System.out.print("Введите дату выдачи в форате yyyy-mm-dd: ");
                                String dateCompFind = in.next();
                                Date dateFindComp;
                                try {
                                    dateFindComp = Date.valueOf(dateCompFind);
                                } catch (Exception e) {
                                    System.out.println("\nНеверно введено значение\n");
                                    break;
                                }
                                System.out.println();
                                var compDateFind = bookIssueService.findByDateOfComp(dateFindComp);
                                if (compDateFind == null || compDateFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (BookIssuanceEntity issue : compDateFind) {
                                        System.out.print("\n" + issue + "\n");
                                    }
                                }
                                boolIssueFind = false;
                                break;

                            case "0":
                                boolIssueFind = false;
                                bookIssuanceMenu = new BookIssuanceMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolIssueFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить новую выдачу");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveIssue = in.next();
                    System.out.println();

                    boolean boolIssueSave = true;
                    while (boolIssueSave) {
                        switch (saveIssue) {
                            case "1":
                                ReadersService readersService = new ReadersService();
                                BookCopyService bookCopyService = new BookCopyService();
                                BookIssuanceEntity issueEntity = new BookIssuanceEntity();

                                System.out.println("Введите данные для добавления выдачи: \n");
                                System.out.print("Дата выдачи (yyyy-mm-dd): ");
                                String issueInput = in.next();
                                Date issueSave;
                                try {
                                    issueSave = Date.valueOf(issueInput);
                                } catch (Exception e) {
                                    System.out.println("\nНеверно введено значение\n");
                                    break;
                                }
                                issueEntity.setDateOfIssue(issueSave);

                                System.out.print("Дата сдачи (yyyy-mm-dd): ");
                                String compInput = in.next();
                                Date compSave;
                                try {
                                    compSave = Date.valueOf(compInput);
                                } catch (Exception e) {
                                    System.out.println("\nНеверно введено значение\n");
                                    break;
                                }
                                issueEntity.setDateOfCompletion(compSave);

                                System.out.print("ID связанной копии книги: ");
                                String inputCopy = in.next();
                                System.out.println();
                                int idCopy;
                                if (tryParseInt(inputCopy)) {
                                    idCopy = Integer.parseInt(inputCopy);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    boolIssueSave = false;
                                    break;
                                }
                                issueEntity.setCopyForIssuance(bookCopyService.find(idCopy));
                                System.out.println();

                                System.out.print("ID связанного читателя: ");
                                String inputReader = in.next();
                                System.out.println();
                                int idReader;
                                if (tryParseInt(inputReader)) {
                                    idReader = Integer.parseInt(inputReader);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    boolIssueSave = false;
                                    break;
                                }
                                issueEntity.setReaderForIssuance(readersService.find(idReader));
                                System.out.println();

                                boolean actionAuthSave = confirmationOfAction();
                                if (actionAuthSave) {
                                    bookIssueService.save(issueEntity);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolIssueSave = false;
                                break;

                            case "0":
                                boolIssueSave = false;
                                bookIssuanceMenu = new BookIssuanceMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolIssueSave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить выдачу по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteAuth = in.next();
                    System.out.println();

                    try {
                        switch (deleteAuth) {
                            case "1":
                                System.out.print("Введите ID выдачи: ");
                                int idIssueDelete = in.nextInt();
                                System.out.println();
                                boolean actionIssueDelete = confirmationOfAction();
                                if (actionIssueDelete) {
                                    bookIssueService.delete(idIssueDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                bookIssuanceMenu = new BookIssuanceMenu();
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
                    System.out.println("2 - Обновить дату выдачи");
                    System.out.println("3 - Обновить дату сдачи");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateIssue = in.next();
                    System.out.println();

                    try {
                        switch (updateIssue) {
                            case "1":
                                var issueIDUpdate = getUpdateEntity(bookIssueService);
                                if (issueIDUpdate != null) {
                                    System.out.print("Дата выдачи (yyyy-mm-dd): ");
                                    String dateInput1 = in.next();
                                    Date dateUpdate1;
                                    try {
                                        dateUpdate1 = Date.valueOf(dateInput1);
                                    } catch (Exception e) {
                                        System.out.println("\nНеверно введено значение\n");
                                        break;
                                    }
                                    issueIDUpdate.setDateOfIssue(dateUpdate1);

                                    System.out.print("Дата сдачи (yyyy-mm-dd): ");
                                    String dateInput2 = in.next();
                                    Date dateUpdate2;
                                    try {
                                        dateUpdate2 = Date.valueOf(dateInput2);
                                    } catch (Exception e) {
                                        System.out.println("\nНеверно введено значение\n");
                                        break;
                                    }
                                    issueIDUpdate.setDateOfCompletion(dateUpdate2);


                                    boolean actionIssueUpdateAll = confirmationOfAction();
                                    if (actionIssueUpdateAll) {
                                        bookIssueService.update(issueIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "2":
                                issueIDUpdate = getUpdateEntity(bookIssueService);
                                if (issueIDUpdate != null) {
                                    System.out.print("Дата выдачи (yyyy-mm-dd): ");
                                    String dateInput1 = in.next();
                                    Date dateUpdate1;
                                    try {
                                        dateUpdate1 = Date.valueOf(dateInput1);
                                    } catch (Exception e) {
                                        System.out.println("\nНеверно введено значение\n");
                                        break;
                                    }
                                    issueIDUpdate.setDateOfIssue(dateUpdate1);

                                    boolean actionIssueUpdateDate = confirmationOfAction();
                                    if (actionIssueUpdateDate) {
                                        bookIssueService.update(issueIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "3":
                                issueIDUpdate = getUpdateEntity(bookIssueService);
                                if (issueIDUpdate != null) {
                                    System.out.print("Дата сдачи (yyyy-mm-dd): ");
                                    String dateInput2 = in.next();
                                    Date dateUpdate2;
                                    try {
                                        dateUpdate2 = Date.valueOf(dateInput2);
                                    } catch (Exception e) {
                                        System.out.println("\nНеверно введено значение\n");
                                        break;
                                    }
                                    issueIDUpdate.setDateOfCompletion(dateUpdate2);

                                    boolean actionIssueUpdateDate = confirmationOfAction();
                                    if (actionIssueUpdateDate) {
                                        bookIssueService.update(issueIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                bookIssuanceMenu = new BookIssuanceMenu();
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
                    boolIssue = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
