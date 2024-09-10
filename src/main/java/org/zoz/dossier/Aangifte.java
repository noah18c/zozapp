package org.zoz.dossier;

import java.util.ArrayList;
import java.util.HashMap;

public class Aangifte{
    private ArrayList<Verdachte> verdachtes;

    private String aangifteInfo;

    //private String info = "";

    public Aangifte(){
        this.verdachtes = new ArrayList<>();
        this.aangifteInfo = "";
    }


    public void addVerdachte(){
        verdachtes.add(new Verdachte());
    }

    public Verdachte getVerdachte(){
        return this.verdachtes.get(this.verdachtes.size()-1);
    }

    public Verdachte getVerdachte(int i){
        return this.verdachtes.get(i);
    }

    public ArrayList<Verdachte> getVerdachtes(){
        return this.verdachtes;
    }
   
    public void removeVerdachte(int i){
        this.verdachtes.remove(i);
    }
    
    public void setInfo(String info){
        this.aangifteInfo = info;
    }
    public String getInfo(){
        return this.aangifteInfo;
    }
        
}