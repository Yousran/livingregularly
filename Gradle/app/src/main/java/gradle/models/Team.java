package gradle.models;

public class Team extends Model {
    private String name;
    private int projek_id;

    public Team(int user_id, int projek_id, String name) {
        super(user_id);
        this.name = name;
        this.projek_id = projek_id;
    }

    public int getProjek_id() {
        return projek_id;
    }

    public void setProjek_id(int projek_id) {
        this.projek_id = projek_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
