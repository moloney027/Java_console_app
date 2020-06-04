package Menu;

import Entity.FineEntity;
import Service.BookIssuanceService;
import Service.FineService;

import java.io.IOException;
import java.text.ParseException;

public class FineMenu extends AbstractMenu {

    private final FineService fineService = new FineService();

    public FineMenu() throws IOException, ParseException {

        String choiceFine = actionSelection();

        boolean boolFine = true;
        while (boolFine) {
            switch (choiceFine) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти все штрафы");
                    System.out.println("2 - Найти по ID");
                    System.out.println("3 - Найти по сумме штрафа");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findFine = bf.readLine();
                    System.out.println();

                    boolean boolFineFind = true;
                    while (boolFineFind) {
                        switch (findFine) {
                            case "1":
                                var listFineFind = fineService.findAll();
                                if (listFineFind == null || listFineFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (FineEntity fine : listFineFind) {
                                        System.out.print("\n" + fine + "\n");
                                    }
                                }
                                boolFineFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID штрафа: ");
                                String idFine = bf.readLine();
                                int idFineFind;
                                if (tryParseInt(idFine)) {
                                    idFineFind = Integer.parseInt(idFine);
                                } else {
                                    System.out.println("\n'" + idFine + "' нельзя привести к int!\n");
                                    break;
                                }
                                var fineIDFind = fineService.find(idFineFind);

                                if (fineIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + fineIDFind + "\n");
                                }
                                boolFineFind = false;
                                break;

                            case "3":
                                System.out.print("Введите сумму штрафа: ");
                                String amountFine = bf.readLine();
                                int amountFineFind;
                                if (tryParseInt(amountFine)) {
                                    amountFineFind = Integer.parseInt(amountFine);
                                } else {
                                    System.out.println("\n'" + amountFine + "' нельзя привести к int!\n");
                                    break;
                                }
                                var bookNameFind = fineService.findByAmount(amountFineFind);

                                if (bookNameFind == null || bookNameFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (FineEntity fine : bookNameFind) {
                                        System.out.print("\n" + fine + "\n");
                                    }
                                }
                                boolFineFind = false;
                                break;

                            case "0":
                                boolFineFind = false;
                                fineMenu = new FineMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolFineFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить новый штраф");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveFine = bf.readLine();
                    System.out.println();

                    boolean boolFineSave = true;
                    while (boolFineSave) {
                        switch (saveFine) {
                            case "1":
                                FineEntity fineEntitySave = new FineEntity();
                                BookIssuanceService bookIssuanceService = new BookIssuanceService();
                                System.out.println("Введите данные для добавления штрафа: \n");

                                System.out.print("Сумма штрафа: ");
                                String inputAmount = bf.readLine();
                                int amount;
                                if (tryParseInt(inputAmount)) {
                                    amount = Integer.parseInt(inputAmount);
                                } else {
                                    System.out.println("\n'" + inputAmount + "' нельзя привести к int!\n");
                                    boolFineSave = false;
                                    break;
                                }
                                fineEntitySave.setAmount(amount);

                                System.out.print("ID связанной выдачи: ");
                                String inputIssue = bf.readLine();
                                System.out.println();
                                int idPH;
                                if (tryParseInt(inputIssue)) {
                                    idPH = Integer.parseInt(inputIssue);
                                } else {
                                    System.out.println("\n'" + inputIssue + "' нельзя привести к int!\n");
                                    boolFineSave = false;
                                    break;
                                }
                                fineEntitySave.setIssuanceForFine(bookIssuanceService.find(idPH));

                                boolean actionFineSave = confirmationOfAction();
                                if (actionFineSave) {
                                    fineService.save(fineEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolFineSave = false;
                                break;

                            case "0":
                                boolFineSave = false;
                                fineMenu = new FineMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolFineSave = false;
                                break;
                        }
                    }
                    break;

                case "3":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Удалить штраф по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteFine = bf.readLine();
                    System.out.println();

                    try {
                        switch (deleteFine) {
                            case "1":
                                System.out.print("Введите ID штрафа: ");
                                String idDelete = bf.readLine();
                                int idFineDelete;
                                if (tryParseInt(idDelete)) {
                                    idFineDelete = Integer.parseInt(idDelete);
                                } else {
                                    System.out.println("\n'" + idDelete + "' нельзя привести к int!\n");
                                    break;
                                }

                                boolean actionFineDelete = confirmationOfAction();
                                if (actionFineDelete) {
                                    fineService.delete(idFineDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                fineMenu = new FineMenu();
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
                    System.out.println("2 - Обновить сумму штрафа");
                    System.out.println("3 - Обновить выдачу");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updateFine = bf.readLine();
                    System.out.println();

                    try {
                        switch (updateFine) {
                            case "1":
                                BookIssuanceService bookIssuanceService = new BookIssuanceService();
                                var fineIDUpdate = getUpdateEntity(fineService);
                                if (fineIDUpdate != null) {
                                    System.out.print("Сумма штрафа: ");
                                    String inputAmount = bf.readLine();
                                    int amount;
                                    if (tryParseInt(inputAmount)) {
                                        amount = Integer.parseInt(inputAmount);
                                    } else {
                                        System.out.println("\n'" + inputAmount + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    fineIDUpdate.setAmount(amount);

                                    System.out.print("ID связанной выдачи: ");
                                    String inputIssue = bf.readLine();
                                    System.out.println();
                                    int idPH;
                                    if (tryParseInt(inputIssue)) {
                                        idPH = Integer.parseInt(inputIssue);
                                    } else {
                                        System.out.println("\n'" + inputIssue + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    fineIDUpdate.setIssuanceForFine(bookIssuanceService.find(idPH));

                                    boolean actionBookUpdateAll = confirmationOfAction();
                                    if (actionBookUpdateAll) {
                                        fineService.update(fineIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "2":
                                fineIDUpdate = getUpdateEntity(fineService);
                                if (fineIDUpdate != null) {
                                    System.out.print("Сумма штрафа: ");
                                    String inputAmount = bf.readLine();
                                    int amount;
                                    if (tryParseInt(inputAmount)) {
                                        amount = Integer.parseInt(inputAmount);
                                    } else {
                                        System.out.println("\n'" + inputAmount + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    fineIDUpdate.setAmount(amount);

                                    boolean actionAuthUpdateDate = confirmationOfAction();
                                    if (actionAuthUpdateDate) {
                                        fineService.update(fineIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "3":
                                bookIssuanceService = new BookIssuanceService();
                                fineIDUpdate = getUpdateEntity(fineService);
                                if (fineIDUpdate != null) {
                                    System.out.print("ID связанной выдачи: ");
                                    String inputIssue = bf.readLine();
                                    int idPH;
                                    if (tryParseInt(inputIssue)) {
                                        idPH = Integer.parseInt(inputIssue);
                                    } else {
                                        System.out.println("\n'" + inputIssue + "' нельзя привести к int!\n");
                                        break;
                                    }
                                    fineIDUpdate.setIssuanceForFine(bookIssuanceService.find(idPH));

                                    boolean actionAuthUpdatePlace = confirmationOfAction();
                                    if (actionAuthUpdatePlace) {
                                        fineService.update(fineIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                fineMenu = new FineMenu();
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
                    boolFine = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
