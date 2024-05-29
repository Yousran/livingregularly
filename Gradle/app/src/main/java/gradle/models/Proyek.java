package gradle.models;

public class Proyek extends Model {
    private int projekId;
    private String tanggal;
    private String namaProjek;
    private int pengeluaran;

    public Proyek(int projekId, int user_id, String namaProjek, String tanggal, int pengeluaran) {
        super(user_id);
        this.projekId = projekId;
        this.namaProjek = namaProjek;
        this.pengeluaran = pengeluaran;
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

    public int getPengeluaran() {
        return pengeluaran;
    }
    
    public void setPengeluaran(int pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

}
