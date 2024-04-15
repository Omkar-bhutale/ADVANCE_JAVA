package in.omkar.dto;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer sid;
    private String sname;
    private Integer sage;
    private String saddress;


    public Integer getSid() {
        return sid;
    }

    @Override
    public String toString() {
        return "Studant{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", saddress='" + saddress + '\'' +
                '}';
    }

    public String getSname() {
        return sname;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public Integer getSage() {
        return sage;
    }

    public String getSaddress() {
        return saddress;
    }

}
