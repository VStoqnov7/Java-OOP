package P15Exams.P02Exam.vehicleShop.repositories;

import P15Exams.P02Exam.vehicleShop.models.worker.Worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WorkerRepository implements Repository<Worker>{

    private Collection<Worker> workers;

    public WorkerRepository() {
        this.workers = new ArrayList<>();
    }

    @Override
    public Collection<Worker> getWorkers() {
        return Collections.unmodifiableCollection(this.workers);
    }

    @Override
    public void add(Worker worker) {
        workers.add(worker);
    }

    @Override
    public boolean remove(Worker worker) {
        if (workers.contains(worker)){
            workers.remove(worker);
            return true;
        }
        return false;
    }

    @Override
    public Worker findByName(String name) {
        return workers.stream().filter(worker -> worker.getName().equals(name)).findFirst().orElse(null);
    }
}
