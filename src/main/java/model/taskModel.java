package model;

public class taskModel {

    private String tittle;
    private String description;

    public taskModel(String tittle, String description){

        this.tittle = tittle;
        this.description = description;
    }

    public String getTittle(){
        return this.tittle;
    }
    public String getDescription(){
        return this.description;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
