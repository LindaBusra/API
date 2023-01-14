package pojos;


public class TodosPojo {


        /*
    Given https://jsonplaceholder.typicode/todos/2
    When I send a Get request to the Url
    Then the actual data should be as following:
        {
        "userId":1,
        "id":2,
        "title": "quis ut nam facilis et officia qui",
        "completed":false
        }
     */



    // 1- create private variables

    private int userId;
    private int id;
    private String title;
    private boolean completed;



    // 2-Create constructors


    public TodosPojo() {

    }


    public TodosPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }



    public TodosPojo(int userId, String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }



    // 3- Getter and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }






    // 4- ToString()


    @Override
    public String toString() {
        return "TodosPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }



}