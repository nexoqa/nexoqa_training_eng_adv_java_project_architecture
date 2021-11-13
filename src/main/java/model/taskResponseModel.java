package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import sun.font.TrueTypeFont;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"description", "id", "is_done", "title"})
public class taskResponseModel {

    private String description;
    private String id;
    private Boolean isDone;
    private String tittle;

    public taskResponseModel () {

    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("is_done")
    public Boolean getIsDone() {
        return isDone;
    }

    @JsonProperty("is_done")
    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    @JsonProperty("title")
    public String getTittle() {
        return tittle;
    }

    @JsonProperty("title")
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public boolean isNotNull(){

        if(this.tittle == null || this.tittle.trim().isEmpty()){
            return true;
        }
        if(this.isDone == null){
            return true;
        }
        if(this.description == null || this.description.trim().isEmpty()){
            return true;
        }
        if(this.id == null || this.id.trim().isEmpty()){
            return true;
        }
        return false;
    }
}
