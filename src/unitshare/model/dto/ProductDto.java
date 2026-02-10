package unitshare.model.dto;

public class ProductDto {

    private int pno;
    private String pname;
    private int pprice;
    private String pcontent;
    private String pdate;
    private String openchat;
    private int people;
    private int uno;
    public ProductDto(){}

    public ProductDto(int pno, String pname, int pprice, String pcontent, String pdate, String openchat, int people, int uno) {
        this.pno = pno;
        this.pname = pname;
        this.pprice = pprice;
        this.pcontent = pcontent;
        this.pdate = pdate;
        this.openchat = openchat;
        this.people = people;
        this.uno = uno;
    }

    public ProductDto(int pno, String pname, int pprice, String pcontent, String pdate, String openchat, int people) {
        this.pno = pno;
        this.pname = pname;
        this.pprice = pprice;
        this.pcontent = pcontent;
        this.pdate = pdate;
        this.openchat = openchat;
        this.people = people;
    }


    public int getPno() {return pno;}
    public void setPno(int pno) {this.pno = pno;}
    public String getPname() {return pname;}
    public void setPname(String pname) {this.pname = pname;}
    public int getPprice() {return pprice;}
    public void setPprice(int pprice) {this.pprice = pprice;}
    public String getPcontent() {return pcontent;}
    public void setPcontent(String pcontent) {this.pcontent = pcontent;}
    public String getPdate() {return pdate;}
    public void setPdate(String pdate) {this.pdate = pdate;}
    public String getOpenchat() {return openchat;}
    public void setOpenchat(String openchat) {this.openchat = openchat;}
    public int getPeople() {return people;}
    public void setPeople(int people) {this.people = people;}
    public int getUno() {return uno;}
    public void setUno(int uno) {this.uno = uno;}

    @Override
    public String toString() {
        return "ProductDto{" +
                "pno=" + pno +
                ", pname='" + pname + '\'' +
                ", pprice=" + pprice +
                ", pcontent='" + pcontent + '\'' +
                ", pdate='" + pdate + '\'' +
                ", openchat='" + openchat + '\'' +
                ", people=" + people +
                ", uno=" + uno +
                '}';
    }
}
