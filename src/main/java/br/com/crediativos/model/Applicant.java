package br.com.crediativos.model;

public class Applicant implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private java.lang.Integer age;
    private java.lang.Integer money;
    private java.lang.Boolean approved;

    public Applicant() {
    }

    public java.lang.Integer getAge() {
        return this.age;
    }

    public void setAge(java.lang.Integer age) {
        this.age = age;
    }

    public java.lang.Integer getMoney() {
        return this.money;
    }

    public void setMoney(java.lang.Integer money) {
        this.money = money;
    }

    public java.lang.Boolean getApproved() {
        return this.approved;
    }

    public void setApproved(java.lang.Boolean approved) {
        this.approved = approved;
    }

    public Applicant(java.lang.Integer age, java.lang.Integer money,
            java.lang.Boolean approved) {
        this.age = age;
        this.money = money;
        this.approved = approved;
    }

}