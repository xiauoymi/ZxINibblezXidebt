package com.nibbledebt.nibble.security;

import com.nibbledebt.nibble.integration.model.NibblerData;

/**
 * Created by ralam on 10/13/15.
 */
public class RegisterObject implements SessionObject<NibblerData>{
    private NibblerData nibblerData;
    public RegisterObject(NibblerData nibblerData){
        this.nibblerData = nibblerData;
    }

    @Override
    public NibblerData getData(String key) {
        if(this.nibblerData==null) this.nibblerData = new NibblerData();
        return nibblerData;
    }
}
