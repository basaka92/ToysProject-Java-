package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Service {
    Shop shop;
    FileHandler fileHandler = new FileHandler();
    public String viewGoods(){
        return this.shop.viewGoods();
    }
    public void addToy(String name, int amount, double dropFrequency){
        this.shop.addGoods(name, amount, dropFrequency);
    }
    public void deleteGoods(int id){
        this.shop.deleteGoods(id);
    }
    public boolean ruffleGoods(){
        return this.shop.ruffleGoods();
    }
    public boolean loadShop(){
        try{
            this.shop = (Shop) this.fileHandler.readShop("shop.txt");
        }
        catch(FileNotFoundException | ClassNotFoundException e){
            this.shop = new Shop();
            this.saveShop();
        }
        catch (IOException e){
            return false;
        }
        return true;
    }
    public boolean saveShop(){
        try{
            this.fileHandler.saveShop(this.shop, "shop.txt");
        }
        catch (IOException e){
           return false;
        }
        return true;
    }
    public boolean givePrize() {
        try{
            this.fileHandler.savePrize(this.shop.getPrizes().poll().toString(),"ruffleResults.txt");
        }
        catch (IOException e){
            return false;
        }
        return true;
    }
    public String viewPrizes(){
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iterator = this.shop.getPrizes().iterator();
        int i = 0;
        while(iterator.hasNext()) {
            stringBuilder.append(++i + ") " + iterator.next() + "\n");
        }
        return stringBuilder.toString();
    }
    public void changeDropFrequency(int id, double dropFrequency){
        this.shop.getGoodsById(id).changeDropFrequency(dropFrequency);
    }
    public void changeGoodsAmount(int id, int amount){
        this.shop.getGoodsById(id).changeGoodsAmount(amount);
    }
    public boolean checkId(int id){
        return this.shop.checkGoodsId(id);
    }
}
