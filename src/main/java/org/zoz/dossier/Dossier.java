package org.zoz.dossier;

import java.util.ArrayList;

public class Dossier {


    private ArrayList<Aangifte> aangiftes;

    private String voortgang1, voortgang2;

    private String status;
    private int behandelingsTermijn;

    private int id;


    public Dossier(){
        this.aangiftes = new ArrayList<>();
        this.voortgang1 = "";
        this.voortgang2 = "";
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

    public void setInfo1(String info){
        voortgang1 = info;
    }

    public String getInfo1(){
        return this.voortgang1;
    }

    public void setInfo2(String info){
        voortgang2 = info;
    }

    public String getInfo2(){
        return this.voortgang2;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

}
