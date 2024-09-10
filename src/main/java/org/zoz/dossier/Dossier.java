package org.zoz.dossier;

import java.util.ArrayList;
import java.util.HashMap;

public class Dossier {


    private ArrayList<Aangifte> aangiftes;

    private String voortgang;

    private String id;


    public Dossier(){
        this.aangiftes = new ArrayList<>();
        this.voortgang = "";
    }

    public void addAangifte(){
        this.aangiftes.add(new Aangifte());
    }

    public Aangifte getAangifte(){
        return this.aangiftes.get(this.aangiftes.size()-1);
    }

    public Aangifte getAangifte(int i){
        return this.aangiftes.get(i);
    }

    public void removeAangifte(int i){
        this.aangiftes.remove(i);
    }

    public void setInfo(String info){
        voortgang = info;
    }

    public String getInfo(){
        return this.voortgang;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

}
