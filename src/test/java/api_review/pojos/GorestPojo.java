package api_review.pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import pojos.Data;

@JsonIgnoreProperties(ignoreUnknown = true) //Java will ignore the keys which does not exist inside the pojo.
public class GorestPojo {

    /*
                          {
            "meta": null,
            "data": {
                "id": 459514,
                "name": "Chiranjeev Joshi",
                "email": "joshi_chiranjeev@weimann.biz",
                "gender": "female",
                "status": "active"
            }
        }     */


    private Object meta;
    private GorestDataPojo data;


    public GorestPojo() {
    }

    public GorestPojo(Object meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GorestPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}

