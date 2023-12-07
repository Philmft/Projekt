package schule;

public class Ancestor {
    private String givenName;
    private String surName;
    private char gender;
    public Ancestor(String givenName, String surName, char gender){
        this.givenName = givenName;
        this.surName = surName;
        this.gender = gender;
    }
    public String getGivenName(){
        return givenName;
    }
    public String getSurName(){
        return surName;
    }
    public char getGender(){
        return gender;
    }
}
