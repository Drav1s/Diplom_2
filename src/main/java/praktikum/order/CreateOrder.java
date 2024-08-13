package praktikum.order;

import java.util.ArrayList;
import java.util.List;

public class CreateOrder {

    private List<String> ingredients;

    public CreateOrder(List<String>  ingredients){

        this.ingredients = ingredients;

    }

    public CreateOrder(){

    }

    public List<String>  getHashOfIngredients() {
        return ingredients;
    }

    public void setHashOfIngredients(List<String>  ingredients) {
        this.ingredients = ingredients;
    }
}
