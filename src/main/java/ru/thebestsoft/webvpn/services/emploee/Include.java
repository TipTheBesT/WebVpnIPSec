package ru.thebestsoft.webvpn.services.emploee;

public class Include {
    String name;
    String path;

    String values = "";

    public Include(String path, String name) {
        this.name = name;
        this.path = path;
    }
    public Include(String path, String name, String values) {
        this.name = name;
        this.path = path;
        this.values = values;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
