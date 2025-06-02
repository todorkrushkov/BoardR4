package com.company.oop.engine;

import java.time.LocalDate;

public class Task extends BoardItem {
    private static final int ASSIGNEE_MIN_LENGTH = 5;
    private static final int ASSIGNEE_MAX_LENGTH = 30;
    private static final String ASSIGNEE_ERROR_MESSAGE = "Assignee length should be between 5 and 30.";

    private String assignee;

    public Task (String title, String assignee, LocalDate dueDate) {
        super(title, dueDate, Status.TODO);
        setAssignee(assignee);
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        if (assignee.isEmpty() || assignee.length() < ASSIGNEE_MIN_LENGTH || assignee.length() > ASSIGNEE_MAX_LENGTH) {
            throw new IllegalArgumentException(ASSIGNEE_ERROR_MESSAGE);
        }

        if (this.assignee != null) {
            this.logAssigneeChange(this.assignee, assignee);
        }

        this.assignee = assignee;


    }

    private void logAssigneeChange(String previous, String updated) {
        super.logChange("Assignee", previous, updated);
    }
}
