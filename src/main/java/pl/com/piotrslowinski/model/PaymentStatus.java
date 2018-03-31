package pl.com.piotrslowinski.model;

public class PaymentStatus {

    private boolean success;

    private String message;

    public PaymentStatus() {
    }

    public boolean isSuccess(){
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
