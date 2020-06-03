package Menu;

import Entity.PublishingHouseEntity;
import Service.PublishingHouseService;

import java.io.IOException;
import java.text.ParseException;

public class PublishingHouseMenu extends AbstractMenu {

    private final PublishingHouseService pHService = new PublishingHouseService();

    public PublishingHouseMenu() throws IOException, ParseException {

        String choicePH = actionSelection();

        boolean boolPH = true;
        while (boolPH) {
            switch (choicePH) {
                case "1":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Найти все издательства");
                    System.out.println("2 - Найти по ID");
                    System.out.println("3 - Найти по названию");
                    System.out.println("4 - Найти по дате основания");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String findPH = in.next();
                    System.out.println();

                    boolean boolPHFind = true;
                    while (boolPHFind) {
                        switch (findPH) {
                            case "1":
                                var listPHFind = pHService.findAll();
                                if (listPHFind == null || listPHFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (PublishingHouseEntity pH : listPHFind) {
                                        System.out.print("\n" + pH + "\n");
                                    }
                                }
                                boolPHFind = false;
                                break;

                            case "2":
                                System.out.print("Введите ID издательства: ");
                                int idPHFind = in.nextInt();
                                System.out.println();
                                var pHIDFind = pHService.find(idPHFind);
                                if (pHIDFind == null) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    System.out.print("\n" + pHIDFind + "\n");
                                }
                                boolPHFind = false;
                                break;

                            case "3":
                                System.out.print("Введите название издательства: ");
                                String namePHFind = bf.readLine();
                                System.out.println();
                                var pHNameFind = pHService.findByTitle(namePHFind);
                                if (pHNameFind == null || pHNameFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (PublishingHouseEntity pH : pHNameFind) {
                                        System.out.print("\n" + pH + "\n");
                                    }
                                }
                                boolPHFind = false;
                                break;

                            case "4":
                                System.out.print("Введите год основания издательства: ");
                                String inputPH = in.next();
                                System.out.println();
                                int yearPHFind;
                                if (tryParseInt(inputPH)) {
                                    yearPHFind = Integer.parseInt(inputPH);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    break;
                                }
                                var pHYearFind = pHService.findByDate(yearPHFind);
                                if (pHYearFind == null || pHYearFind.isEmpty()) {
                                    System.out.println("\nПо запросу ничего не найдено\n");
                                } else {
                                    for (PublishingHouseEntity pH : pHYearFind) {
                                        System.out.print("\n" + pH + "\n");
                                    }
                                }
                                boolPHFind = false;
                                break;

                            case "0":
                                boolPHFind = false;
                                publishingHouseMenu = new PublishingHouseMenu();
                                break;

                            default:
                                System.out.println("\nВведено неверное значение\n");
                                boolPHFind = false;
                                break;
                        }
                    }
                    break;

                case "2":
                    System.out.println("\nВыберите действие: ");
                    System.out.println("1 - Добавить новое издательство");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String saveFine = in.next();
                    System.out.println();

                    boolean boolFineSave = true;
                    while (boolFineSave) {
                        switch (saveFine) {
                            case "1":
                                PublishingHouseEntity pHEntitySave = new PublishingHouseEntity();
                                System.out.println("Введите данные для добавления издательства: \n");

                                System.out.print("Название издательства: ");
                                pHEntitySave.setTitle(bf.readLine());

                                System.out.print("Год основания издательства: ");
                                String inputYear = in.next();
                                int year;
                                if (tryParseInt(inputYear)) {
                                    year = Integer.parseInt(inputYear);
                                } else {
                                    System.out.println("\nВведено неверное значение!\n");
                                    boolFineSave = false;
                                    break;
                                }
                                pHEntitySave.setDateOfEstablishment(year);
                                System.out.println();

                                boolean actionPHSave = confirmationOfAction();
                                if (actionPHSave) {
                                    pHService.save(pHEntitySave);
                                } else {
                                    System.out.println("\nОбъект НЕ добавлен!\n");
                                }
                                boolFineSave = false;
                                break;

                            case "0":
                                boolFineSave = false;
                                publishingHouseMenu = new PublishingHouseMenu();
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
                    System.out.println("1 - Удалить издательство по ID");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String deleteFine = in.next();
                    System.out.println();

                    try {
                        switch (deleteFine) {
                            case "1":
                                System.out.print("Введите ID издательства: ");
                                int idPHDelete = in.nextInt();
                                System.out.println();
                                boolean actionPHDelete = confirmationOfAction();
                                if (actionPHDelete) {
                                    pHService.delete(idPHDelete);
                                } else {
                                    System.out.println("\nОбъект НЕ удален!\n");
                                }
                                break;

                            case "0":
                                publishingHouseMenu = new PublishingHouseMenu();
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
                    System.out.println("2 - Обновить название издательства");
                    System.out.println("3 - Обновить дату основания");
                    System.out.println("0 - Вернуться назад\n");

                    System.out.print("Номер действия: ");
                    String updatePH = in.next();
                    System.out.println();

                    try {
                        switch (updatePH) {
                            case "1":
                                var pHIDUpdate = getUpdateEntity(pHService);
                                if (pHIDUpdate != null) {

                                    System.out.print("Название издательства: ");
                                    pHIDUpdate.setTitle(bf.readLine());

                                    System.out.print("Год основания издательства: ");
                                    String inputYear = in.next();
                                    int year;
                                    if (tryParseInt(inputYear)) {
                                        year = Integer.parseInt(inputYear);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    pHIDUpdate.setDateOfEstablishment(year);
                                    System.out.println();

                                    boolean actionBookUpdateAll = confirmationOfAction();
                                    if (actionBookUpdateAll) {
                                        pHService.update(pHIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "2":
                                pHIDUpdate = getUpdateEntity(pHService);
                                if (pHIDUpdate != null) {
                                    System.out.print("Название издательства: ");
                                    pHIDUpdate.setTitle(bf.readLine());
                                    System.out.println();
                                    boolean actionAuthUpdateDate = confirmationOfAction();
                                    if (actionAuthUpdateDate) {
                                        pHService.update(pHIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "3":
                                pHIDUpdate = getUpdateEntity(pHService);
                                if (pHIDUpdate != null) {
                                    System.out.print("Год основания издательства: ");
                                    String inputYear = in.next();
                                    int year;
                                    if (tryParseInt(inputYear)) {
                                        year = Integer.parseInt(inputYear);
                                    } else {
                                        System.out.println("\nВведено неверное значение!\n");
                                        break;
                                    }
                                    pHIDUpdate.setDateOfEstablishment(year);
                                    System.out.println();
                                    boolean actionAuthUpdatePlace = confirmationOfAction();
                                    if (actionAuthUpdatePlace) {
                                        pHService.update(pHIDUpdate);
                                    } else {
                                        System.out.println("\nОбъект НЕ обновлен!\n");
                                    }
                                }
                                break;

                            case "0":
                                publishingHouseMenu = new PublishingHouseMenu();
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
                    boolPH = false;
                    mainMenu = new MainMenu();
                    break;

                default:
                    System.out.println("\nВведено неверное значение\n");
                    break;
            }
        }
    }
}
