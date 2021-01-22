package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private String projectName;
    private String taskName;
    private String userFirstName;
    private String userLastName;

    @Override
    public String toString() {
        return "Проект: " + projectName + '\n' +
                "Задача: " + taskName + '\n' +
                "Назначена пользователю: " + userFirstName + ' ' + userLastName + '\n';
    }
}
