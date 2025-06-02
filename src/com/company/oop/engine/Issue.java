package com.company.oop.engine;

import java.time.LocalDate;

public class Issue extends BoardItem {
    private String description;

    public Issue (String title, String description, LocalDate dueDate) {
        super(title, dueDate);
        setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if (description.isEmpty()) {
            description = "No description";
        }

        this.description = description;
    }
}
