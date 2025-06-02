package com.company.oop.engine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BoardItem {
    private static final String CHANGE_MESSAGE = "%s changed from %s to %s";
    private static final String CHANGE_STATUS = "Status changed from %s to %s";
    private static final String CREATION_MESSAGE = "Item created: ";


    private String title;
    private LocalDate dueDate;
    private Status status;
    private final ArrayList<EventLog> logs;

    public BoardItem(String title, LocalDate dueDate) {
        this(title, dueDate, Status.OPEN);
    }

    protected BoardItem(String title, LocalDate dueDate, Status status) {
        setTitle(title);
        setDueDate(dueDate);
        this.status = status;

        logs = new ArrayList<>();
        logs.add(new EventLog(CREATION_MESSAGE + this.viewInfo()));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.length() < 5 || title.length() > 30) {
            throw new IllegalArgumentException("Please provide a title with length between 5 and 30 chars");
        }

        if (this.title != null) {
            logChange("Title", this.title, title);
        }

        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Please provide due date that is in the future");
        }

        if (this.dueDate != null) {
            logChange("DueDate",
                    this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }


        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void revertStatus() {
        String temp = this.status.toString();

        switch (this.status) {
            case TODO:
                this.status = Status.OPEN;
                break;
            case IN_PROGRESS:
                this.status = Status.TODO;
                break;
            case DONE:
                this.status = Status.IN_PROGRESS;
                break;
            case VERIFIED:
                this.status = Status.DONE;
                break;
        }

        if (temp.equals("Open")) {
            logStatusChange("Can't revert, already at Open");
        }
        else {
            logStatusChange(temp, this.status.toString());
        }
    }


    public void advanceStatus() {
        String temp = this.status.toString();

        switch (this.status) {
            case OPEN:
                this.status = Status.TODO;
                break;
            case TODO:
                this.status = Status.IN_PROGRESS;
                break;
            case IN_PROGRESS:
                this.status = Status.DONE;
                break;
            case DONE:
                this.status = Status.VERIFIED;
                break;
        }

        if (temp.equals("Verified")) {
            logStatusChange("Can't advance, already at Verified");
        }
        else {
            logStatusChange(temp, this.status.toString());
        }
    }

    public void displayHistory() {
        for (EventLog log : logs) {
            System.out.println(log.viewInfo());
        }
    }

    protected void logChange(String property, String previous, String updated) {
        logs.add(new EventLog(String.format(CHANGE_MESSAGE, property, previous, updated)));
    }

    private void logStatusChange(String previous, String updated) {
        logs.add(new EventLog(String.format(CHANGE_STATUS, previous, updated)));
    }

    private void logStatusChange(String message) {
        logs.add(new EventLog(message));
    }

    public String viewInfo() {
        return String.format("'%s', [%s | %s]", title, status, dueDate);
    }

}

