package gradle.models;

public class Anggaran extends Model {
    private String item;
    private int harga;
    private int projek_id;

    public Anggaran(int user_id, int projek_id, String item, int harga){
        super(user_id);
        this.item = item;
        this.harga = harga;
        this.projek_id = projek_id;
    }

    public int getProjek_id() {
        return projek_id;
    }

    public void setProjek_id(int projek_id) {
        this.projek_id = projek_id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
