package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    private long id;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "Задача: " + taskName + '\n';
    }
}
