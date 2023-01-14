package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyApiResponsePojo2 {
    private String status;
    private DummyApiDataPojo2 data;
    private String message;


    public DummyApiResponsePojo2(String status, DummyApiDataPojo2 data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }



    public DummyApiResponsePojo2() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyApiDataPojo2 getData() {
        return data;
    }

    public void setData(DummyApiDataPojo2 data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyApiResponsePojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}