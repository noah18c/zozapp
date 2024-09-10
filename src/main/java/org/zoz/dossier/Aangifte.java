package org.zoz.dossier;

import java.util.ArrayList;
import java.util.HashMap;

public class Aangifte{
    private ArrayList<Verdachte> verdachtes;

    private HashMap<String, String> aangifteInfo;

    //private String info = "";

    public Aangifte(){
        this.verdachtes = new ArrayList<>();
        this.aangifteInfo = new HashMap<>();
    }

    public void addInfo(String key, String value){
        aangifteInfo.put(key, value);
    }

    public void removeInfo(String key){
        aangifteInfo.remove(key);
    }

    public void addVerdachte(){
        verdachtes.add(new Verdachte());
    }

    public Verdachte getVerdachte(int i){
        return this.verdachtes.get(i);
    }

    public void removeVerdachte(int i){
        this.verdachtes.remove(i);
    }
    /*
    public void setInfo(String info){
        this.info = info;
    }
    public String getInfo(){
        return this.info;
    }
        */
}