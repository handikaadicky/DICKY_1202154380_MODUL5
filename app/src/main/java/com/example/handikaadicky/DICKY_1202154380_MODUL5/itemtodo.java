package com.example.handikaadicky.DICKY_1202154380_MODUL5;

public class itemtodo {
    //inisiasi awal variabel
    String name, description, priority;

    public itemtodo(String name, String description, String priority) {
        //melakukan inisiasi
        this.name = name;
        this.description = description;
        this.priority = priority;
    }
    //getName
    public String getName() {
        return name;
    }
    //setName
    public void setName(String name) {
        this.name = name;
    }

    //getDescription
    public String getDescription() {
        return description;
    }

    //setDescription
    public void setDescription(String description) {
        this.description = description;
    }

    //getPriority
    public String getPriority() {
        return priority;
    }

    //setPriority
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
