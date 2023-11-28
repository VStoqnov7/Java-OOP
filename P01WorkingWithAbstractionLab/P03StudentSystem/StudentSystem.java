package P01WorkingWithAbstractionLab.P03StudentSystem;

import java.util.HashMap;
import java.util.Map;


public class StudentSystem {
    private Map<String, Student> data;

    public StudentSystem() {
        this.data = new HashMap<>();
    }

    public Map<String, Student> getData() {
        return this.data;
    }

    public void ParseCommand(String[] command) {

        switch (command[0]) {
            case "Create":
                String nameCreate = command[1];
                int age = Integer.parseInt(command[2]);
                double grade = Double.parseDouble(command[3]);
                if (!data.containsKey(nameCreate)) {
                    Student student = new Student(nameCreate, age, grade);
                    data.put(nameCreate, student);
                }
                break;
            case "Show":
                String nameShow = command[1];
                if (data.containsKey(nameShow)) {
                    Student student = data.get(nameShow);
                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("%s is %s years old.", student.getName(), student.getAge()));

                    if (student.getGrade() >= 5.00) {
                        sb.append(" Excellent student.");
                    } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
                        sb.append(" Average student.");
                    } else {
                        sb.append(" Very nice person.");
                    }

                    System.out.println(sb.toString());
                break;
            }
        }
    }
}
