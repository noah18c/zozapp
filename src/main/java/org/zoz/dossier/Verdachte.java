package org.zoz.dossier;

import java.util.HashMap;

public class Verdachte{

    private HashMap<String, String> infoVerdachte;
    
    public Verdachte(){
        this.infoVerdachte = new HashMap<>();
    }

    public void addInfo(String key, String value){
        infoVerdachte.put(key, value);
    }

    public void removeInfo(String key){
        infoVerdachte.remove(key);
    }

}