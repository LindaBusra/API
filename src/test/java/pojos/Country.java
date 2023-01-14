package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    //https://json2csharp.com/code-converters/json-to-pojo  --easy way to create pojo

    //create variables
    public int id;
    public String name;
    public Object states;

    //create constructors
    public Country() {
    }

    public Country(int id, String name, Object states) {
        this.id = id;
        this.name = name;
        this.states = states;
    }


    //create getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getStates() {
        return states;
    }

    public void setStates(Object states) {
        this.states = states;
    }


    //toString()
    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}
