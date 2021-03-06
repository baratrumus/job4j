package lsp;

import java.util.List;

public class ControllQuality {

    private List<Store> stores;

    public ControllQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(Food food) {
        for (Store str : stores) {
            if (str.accept(food)) {
                str.putToStore(food);
            }
        }
    }

    public List<Store> getStores() {
        return stores;
    }
}
