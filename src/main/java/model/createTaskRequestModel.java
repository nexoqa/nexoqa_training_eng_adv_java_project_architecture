package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"description", "tittle"})
public class createTaskRequestModel {

    private String description;
    private String tittle;

    public createTaskRequestModel(String description, String tittle) {

        this.description = description;
        this.tittle = tittle;

    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("title")
    public String getTittle() {
        return tittle;
    }

    @JsonProperty("title")
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
