package ntu.lethanhtung_63132783.viewpager_fragment;

public class QuocGia {
    private String TenQuocGia;
    private String CoQuocGia;
    private int DanSo;

    public QuocGia(String tenQuocGia, String coQuocGia, int danSo) {
        TenQuocGia = tenQuocGia;
        CoQuocGia = coQuocGia;
        DanSo = danSo;
    }

    public String getTenQuocGia() {
        return TenQuocGia;
    }

    public void setTenQuocGia(String tenQuocGia) {
        TenQuocGia = tenQuocGia;
    }

    public String getCoQuocGia() {
        return CoQuocGia;
    }

    public void setCoQuocGia(String coQuocGia) {
        CoQuocGia = coQuocGia;
    }

    public int getDanSo() {
        return DanSo;
    }

    public void setDanSo(int danSo) {
        DanSo = danSo;
    }

}
