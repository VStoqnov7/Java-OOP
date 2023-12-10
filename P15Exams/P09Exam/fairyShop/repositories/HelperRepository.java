package P15Exams.P09Exam.fairyShop.repositories;

import P15Exams.P09Exam.fairyShop.models.Helper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelperRepository implements Repository<Helper>{

    private Map<String,Helper> helpers;


    public HelperRepository() {
        this.helpers = new LinkedHashMap<>();
    }


    @Override
    public Collection<Helper> getModels() {
        return Collections.unmodifiableCollection(this.helpers.values());
    }

    @Override
    public void add(Helper helper) {
        helpers.putIfAbsent(helper.getName(),helper);
    }

    @Override
    public boolean remove(Helper helper) {
        if (helpers.containsKey(helper.getName())){
            helpers.remove(helper.getName());
            return true;
        }

        return false;
    }

    @Override
    public Helper findByName(String name) {
        return helpers.get(name);
    }
}
