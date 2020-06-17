package il.co.hit;

import il.co.hit.view.StoreView;

public class Main {

    public static void main(String[] args) {
        try {
            StoreView storeView = new StoreView();
            storeView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
