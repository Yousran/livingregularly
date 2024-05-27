package gradle.models;

public class User extends Model {
    private String nama;
    private String email;
    private String password;
    private String profil_path;

    public User(int id, String nama, String email, String password, String profil_path) {
        super(id);
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.profil_path = profil_path;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfil_path() {
        return profil_path;
    }

    public void setProfil_path(String profil_path) {
        this.profil_path = profil_path;
    }
}
