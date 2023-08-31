package view;

import presenter.Presenter;
import java.util.Scanner;

public class View {
    Presenter presenter = new Presenter(this);
    Scanner sc = new Scanner(System.in);
    boolean working = true;
    public void runConsoleApp(){
        while(this.working){
            System.out.println("Введите команду (введите help для отображения списка доступных команд):");
            String command = sc.next();
            System.out.println();
            switch (command) {
                case "view" -> this.viewGoods();
                case "add" -> this.addToy();
                case "delete" -> this.deleteGoods();
                case "drop" -> this.changeDropFrequency();
                case "amount" -> this.changeGoodsAmount();
                case "ruffle" -> this.ruffleGoods();
                case "prizes" -> this.viewPrizes();
                case "give" -> this.givePrizes();
                case "help" -> this.getHelp();
                case "exit" -> this.exit();
                default -> System.out.println("Такой команды нет!\n");
            }
        }
    }
    void getHelp(){
        System.out.println("""
                Список доступных команд:
                view - просмотр каталога игрушек в магазине
                add - добавление новой игрушки в каталог магазина
                delete - удаление игрушки из каталога магазина
                drop - изменение шанса выпадения игрушки
                amount - изменение количества игрушек
                ruffle - проведение розыгрыша игрушек
                prizes - просмотр списка игрушек на выдачу
                give - выдача следующей игрушки из списка ожидания
                help - просмотр списка команд
                exit - завершение работы программы
                """);
    }
    void viewGoods(){
        this.loadShop();
        if (this.presenter.viewGoods().isEmpty()){
            System.out.println("В ассортименте магазина нет ни одного вида игрушек! Добавьте новые игрушки. \n");
        }else{
            System.out.println(this.presenter.viewGoods());
        }
    }
    void addToy(){
        this.loadShop();
        System.out.println("Введите название новой игрушки:");
        String name = new String();
        while (name.isEmpty()){
                name = sc.nextLine();
        }
        System.out.println();
        int amount = -1;
        while (amount < 0)
        {
            System.out.println("Введите количество (целое положительное число, включая ноль):");
            if (sc.hasNextInt()){
                amount = sc.nextInt();
                if (amount<0){
                    System.out.println("\nВведённое число является отрицательным! Попробуйте ввод снова.\n");
                }
            }else{
                System.out.println("\nВведены данные некорректного типа! Попробуйте ввод снова.\n");
                sc.next();
            }
        }
        System.out.println();
        double dropFrequency = -1;
        while (dropFrequency < 0 || dropFrequency > 99)
        {
            System.out.println("Введите частоту выпадения в процентах (целое либо дробное положительное число, от 0 до 99 включительно):");
            if (sc.hasNextDouble()){
                dropFrequency = sc.nextDouble();
                if (dropFrequency<0){
                    System.out.println("\nВведённое число является отрицательным! Попробуйте ввод снова.\n");
                }else if (dropFrequency>99){
                    System.out.println("\nВведённое число больше 99! Попробуйте ввод снова.\n");
                }
            }else{
                System.out.println("\nВведены данные некорректного типа! Попробуйте ввод снова.\n");
                sc.next();
            }
        }
        System.out.println();
        this.presenter.addToy(name, amount, dropFrequency/100);
        this.saveShop();
    }
    void deleteGoods(){
        this.loadShop();
        int id = -1;
        while (id < 0)
        {
            System.out.println("Введите ID игрушки (целое положительное число), которую хотите удалить:");
            if (sc.hasNextInt()){
                id = sc.nextInt();
                if (id<0){
                    System.out.println("\nВведённое число является отрицательным! Попробуйте ввод снова.\n");
                }else if(!this.presenter.checkGoodsId(id)){
                    System.out.println("\nИгрушек с таким ID нет в каталоге магазина! Попробуйте ввод снова.\n");
                }else{
                    this.presenter.deleteGoods(id);
                }
            }else{
                System.out.println("\nВведены данные некорректного типа! Попробуйте ввод снова.\n");
                sc.next();
            }
        }
        System.out.println();
        this.saveShop();
    }
    void changeDropFrequency(){
        this.loadShop();
        int id = -1;
        while (id < 0)
        {
            System.out.println("Введите ID игрушки (целое положительное число), шанс выпадения которой вы хотите изменить:");
            if (sc.hasNextInt()){
                id = sc.nextInt();
                if (id<0){
                    System.out.println("\nВведённое число является отрицательным! Попробуйте ввод снова.\n");
                }else if(!this.presenter.checkGoodsId(id)){
                    System.out.println("\nИгрушек с таким ID нет в каталоге магазина! Попробуйте ввод снова.\n");
                    id = -1;
                }
            }else{
                System.out.println("\nВведены данные некорректного типа! Попробуйте ввод снова.\n");
                sc.next();
            }
        }
        System.out.println();
        double dropFrequency = -1;
        while (dropFrequency < 0 || dropFrequency > 99)
        {
            System.out.println("Введите новый шанс выпадения (целое либо дробное положительное число, от 0 до 99 включительно):");
            if (sc.hasNextDouble()){
                dropFrequency = sc.nextDouble();
                if (dropFrequency<0){
                    System.out.println("\nВведённое число является отрицательным! Попробуйте ввод снова.\n");
                }else if (dropFrequency>99){
                    System.out.println("\nВведённое число больше 99! Попробуйте ввод снова.\n");
                }
            }else{
                System.out.println("\nВведены данные некорректного типа! Попробуйте ввод снова.\n");
                sc.next();
            }
        }
        System.out.println();
        this.presenter.changeDropFrequency(id, dropFrequency/100);
        this.saveShop();
    }
    void changeGoodsAmount(){
        this.loadShop();
        int id = -1;
        while (id < 0)
        {
            System.out.println("Введите ID игрушки (целое положительное число), количество которой вы хотите изменить:");
            if (sc.hasNextInt()){
                id = sc.nextInt();
                if (id<0){
                    System.out.println("\nВведённое число является отрицательным! Попробуйте ввод снова.\n");
                }else if(!this.presenter.checkGoodsId(id)){
                    System.out.println("\nИгрушек с таким ID нет в каталоге магазина! Попробуйте ввод снова.\n");
                    id = -1;
                }
            }else{
                System.out.println("\nВведены данные некорректного типа! Попробуйте ввод снова.\n");
                sc.next();
            }
        }
        System.out.println();
        int amount = -1;
        while (amount < 0)
        {
            System.out.println("Введите количество (целое положительное число, включая ноль):");
            if (sc.hasNextInt()){
                amount = sc.nextInt();
                if (amount<0){
                    System.out.println("\nВведённое число является отрицательным! Попробуйте ввод снова.\n");
                }
            }else{
                System.out.println("\nВведены данные некорректного типа! Попробуйте ввод снова.\n");
                sc.next();
            }
        }
        System.out.println();
        this.presenter.changeGoodsAmount(id, amount);
        this.saveShop();
    }
    void ruffleGoods(){
        this.loadShop();
        if (this.presenter.ruffleGoods()){
            System.out.println("Розыгрыш прошёл успешно!\n");
            this.saveShop();
        }else{
            System.out.println("В магазине нет игрушек для проведения розыгрыша!\n");
        }
    }
    void viewPrizes(){
        this.loadShop();
        if(this.presenter.viewPrizes().isEmpty()){
            System.out.println("Призов, ожидающих выдачи, нет в списке! Проведите розыгрыш.\n");
        }else{
            System.out.println("Список призов, ожидающих выдачи:\n");
            System.out.println(this.presenter.viewPrizes());
        }
    }
    void givePrizes(){
        this.loadShop();
        if(this.presenter.viewPrizes().isEmpty()){
            System.out.println("Призов, ожидающих выдачи, нет в списке! Проведите розыгрыш.\n");
        }else{
            if (this.presenter.givePrizes()){
                this.saveShop();
            }else{
                System.out.println("Файл со списком выданных игрушек недоступен для записи!\n");
            }
        }
    }
    void loadShop(){
        if (!this.presenter.loadShop()){
            System.out.println("Файл магазина недоступен для чтения!\n");
        }
    }
    void saveShop()  {
        if (!this.presenter.saveShop()){
            System.out.println("Файл магазина недоступен для записи!\n");
        }
    }
    void exit(){
        this.working = false;
    }
}
