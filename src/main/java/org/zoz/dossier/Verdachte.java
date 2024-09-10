package org.zoz.dossier;

public class Verdachte{

    private String infoVerdachte;
    
    public Verdachte(){
        this.infoVerdachte = "";
    }

    public void setInfo(String info){
        this.infoVerdachte = info;
    }
    public String getInfo(){
        return this.infoVerdachte;
    }

}