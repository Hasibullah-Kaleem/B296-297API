package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//If the response JSON and the POJO we created are not fully compatible, so, if the response has some
//extra (unknown) fields (properties), we can ignore such fields to prevent errors.
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoPayLoad {

     /*
     1 - Private fields (variables)
     2 - Parameterized & Non-Parameterized Constructors
     3 - Getters & Setters
     4 - toString() override

     If a class has this structure, we call it a POJO class.
     POJO -> Plain Old Java Object
      */

    private Integer userId;
    private String title;
    private Boolean completed;
    //An empty constructor is needed for Jackson to apply serialization/deserialization

    public TodoPayLoad(){

    }
    public TodoPayLoad(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "TodoPayload{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
