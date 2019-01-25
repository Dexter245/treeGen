package com.polymorphic_dissociation;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private List<Layer> layers = new ArrayList<Layer>();

    public Tree(){

    }

    public void addLayer(int length, int numBranches, int angle){
        layers.add(new Layer(length, numBranches, angle));
    }

    public Layer getLayer(int i){
        if(i < layers.size())
            return layers.get(i);
        else
            throw new IllegalArgumentException("There are only" + (layers.size()+1) + " layers. Can't get layer " + i + ".");
    }

    public int getNumLayers(){
        return layers.size();
    }

}
