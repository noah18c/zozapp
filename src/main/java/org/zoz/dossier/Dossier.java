package org.zoz.dossier;

import java.util.ArrayList;
import java.util.HashMap;

public class Dossier {


    private ArrayList<Aangifte> aangiftes;

    private HashMap<String, String> voortgang;


    public Dossier(){
        this.aangiftes = new ArrayList<>();
        this.voortgang = new HashMap<>();
    }

    public void addAangifte(){
        this.aangiftes.add(new Aangifte());
    }

    public Aangifte getAangifte(int i){
        return this.aangiftes.get(i);
    }

    public void removeAangifte(int i){
        this.aangiftes.remove(i);
    }

    public void addInfo(String key, String value){
        voortgang.put(key, value);
    }

    public void removeInfo(String key){
        voortgang.remove(key);
    }

    public HashMap<String,String> getInfo(){
        return voortgang;
    }

}
