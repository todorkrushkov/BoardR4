package com.company.oop.engine;

public enum Status {
    OPEN, TODO, IN_PROGRESS, DONE, VERIFIED;

    public String toString() {

        String print = "";
        switch (this) {

            case OPEN:
                print = "Open";
                break;
            case TODO:
                print = "To Do";
                break;
            case IN_PROGRESS:
                print = "In Progress";
                break;
            case DONE:
                print = "Done";
                break;
            case VERIFIED:
                print = "Verified";
                break;
        }

        return print;
    }
}
