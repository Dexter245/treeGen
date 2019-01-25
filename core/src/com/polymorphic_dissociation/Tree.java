package com.polymorphic_dissociation;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private List<Layer> layers = new ArrayList<Layer>();

    public Tree(){

    }

    public void addLayer(float length, float width, float numBranches, float angle){
        layers.add(new Layer(length, width, numBranches, angle));
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
