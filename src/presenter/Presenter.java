package presenter;

import model.Service;
import view.View;

public class Presenter {
    Service service = new Service();
    View view;
    public Presenter(View view){
       this.view = view;
    }
    public String viewGoods(){
        return this.service.viewGoods();
    }
    public void addToy(String name, int amount, double dropFrequency){
        this.service.addToy(name, amount, dropFrequency);
    }
    public void deleteGoods(int id){
        this.service.deleteGoods(id);
    }
    public boolean ruffleGoods(){
        return this.service.ruffleGoods();
    }
    public String viewPrizes(){
        return this.service.viewPrizes();
    }
    public boolean givePrizes() {
        return this.service.givePrize();
    }
    public void changeDropFrequency(int id, double dropFrequency){
        this.service.changeDropFrequency(id, dropFrequency);
    }
    public void changeGoodsAmount(int id, int amount){
        this.service.changeGoodsAmount(id, amount);
    }
    public boolean checkGoodsId(int id){
       return this.service.checkId(id);
    }
    public boolean loadShop() {
        return this.service.loadShop();
    }
    public boolean saveShop(){
        return this.service.saveShop();
    }
}
