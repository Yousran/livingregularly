package gradle.models;

public class Proyek extends Model {
    private int projekId;
    private String myTeam;
    private String tanggal;
    private String namaProjek;
    private int pengluaran;

    public Proyek(int user_id, int projekId, String namaProjek, String myTeam, String tanggal, int pengluaran) {
        super(user_id);
        this.projekId = projekId;
        this.namaProjek = namaProjek;
        this.myTeam = myTeam;
        this.pengluaran = pengluaran;
        this.tanggal = tanggal;
    }

    public int getProjekId() {
        return projekId;
    }

    public void setProjekId(int projekId) {
        this.projekId = projekId;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMyTeam() {
        return myTeam;
    }

    public void setMyTeam(String myTeam) {
        this.myTeam = myTeam;
    }

    public int getPengluaran() {
        return pengluaran;
    }

    public void setPengluaran(int pengluaran) {
        this.pengluaran = pengluaran;
    }

}
