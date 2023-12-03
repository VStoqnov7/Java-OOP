package P05InheritanceLab.P04StackOfStrings;

import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {
    private List<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item){
        data.add(item);
    }
    public String pop(){
        String element = data.get(data.size() - 1);
        data.remove(element);
        return element;
    }
    public String peek(){
        return data.get(data.size() - 1);
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
}
