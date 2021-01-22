package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Project {
    private long id;
    private String projectName;

    public Project(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Проект: " + projectName + '\n';
    }
}
