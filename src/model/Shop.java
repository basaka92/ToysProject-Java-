package model;

import java.io.Serializable;
import java.util.*;

public class Shop implements Serializable {

    private TreeMap goods = new TreeMap<Integer,Goods>();
    private LinkedList prizes = new LinkedList<String>();
    private ArrayList<Integer> deletedGoodsId = new ArrayList<Integer>();

   public void addGoods(String name, int amount, double dropFrequency){
       if (deletedGoodsId.isEmpty()){
           this.goods.put(lastId(),new Goods(name, amount, dropFrequency));
       }else{
           this.goods.put(deletedGoodsId.get(0),new Goods(name, amount, dropFrequency));
           deletedGoodsId.remove(0);
       }
    }
    public void deleteGoods(int id){
       this.goods.remove(id);
       deletedGoodsId.add(id);
       Collections.sort(deletedGoodsId);
    }
   private int lastId(){
        if (this.goods.isEmpty()){
            return 1;
        }
        return (int) this.goods.lastKey() + 1;
    }
   boolean ruffleGoods() {
     double totalValue = 0;
     Iterator<Map.Entry<Integer,Goods>> iterator = this.goods.entrySet().iterator();
     Map.Entry<Integer,Goods> tempGoods;
     while(iterator.hasNext()){
         tempGoods = iterator.next();
         if (tempGoods.getValue().getAmount() != 0){
             totalValue += tempGoods.getValue().getDropFrequency();
         }
     }
     if (totalValue == 0){
         return false;
     }
     else{
         double randomNumber = Math.random() * totalValue;
         double currentValue = 0;
         iterator = this.goods.entrySet().iterator();
         while(iterator.hasNext()){
             tempGoods = iterator.next();
             if (tempGoods.getValue().getAmount() != 0){
                 currentValue += tempGoods.getValue().getDropFrequency();
                 if (currentValue>=randomNumber){
                     this.prizes.add(tempGoods.getValue().getName());
                     tempGoods.getValue().changeAmount();
                     return true;
                 }
             }
         }
     }
     return false;
    }
    String viewGoods(){
        Iterator<Map.Entry<Integer,Goods>> iterator = this.goods.entrySet().iterator();
        StringBuilder stringBuilder = new StringBuilder();
        boolean check = true;
        while(iterator.hasNext()){
            Map.Entry<Integer,Goods> tempGoods = iterator.next();
            if (check){
                stringBuilder.append("Каталог товаров магазина:\n");
                check = false;
            }
            stringBuilder.append("|\n|\n" + "ID: " + tempGoods.getKey() + "\n"
                                + "Имя игрушки: " + tempGoods.getValue().getName() + "\n"
            + "Количество в магазине: " + tempGoods.getValue().getAmount() + "\n"
            + "Частота выпадения: " + tempGoods.getValue().getDropFrequency() + "\n");
        }
        return  stringBuilder.toString();
    }
    LinkedList getPrizes(){
       return this.prizes;
    }
    Goods getGoodsById(int id){
      return (Goods) goods.get(id);
    }
    boolean checkGoodsId(int id){
       if (goods.containsKey(id)){
           return true;
       }
       return false;
    }
    }

