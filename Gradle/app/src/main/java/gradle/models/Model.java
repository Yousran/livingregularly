package gradle.models;

public class Model {
    private int user_id;

    public Model(){
        
    }

    public Model(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return user_id;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }
}
