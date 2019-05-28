package com.example.myecomerceapp.Model;

public class Category {
    private String cid;
    private String cname;
    private String cdescription;
    private String cimageyrl;

    public Category(String cid, String cname, String cdescription, String cimageyrl) {
        this.cid = cid;
        this.cname = cname;
        this.cdescription = cdescription;
        this.cimageyrl = cimageyrl;
    }


    public Category(String cid, String cname, String cdescription) {
        this.cid = cid;
        this.cname = cname;
        this.cdescription = cdescription;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdescription() {
        return cdescription;
    }

    public void setCdescription(String cdescription) {
        this.cdescription = cdescription;
    }

    public String getCimageyrl() {
        return cimageyrl;
    }

    public void setCimageyrl(String cimageyrl) {
        this.cimageyrl = cimageyrl;
    }
}
