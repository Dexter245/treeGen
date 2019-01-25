package com.polymorphic_dissociation;

public class Layer {
    private float length = 0f;
    private float numBranches = 0f;
    private float angle = 0f;

    public Layer(float length, float numBranches, float angle){
        this.length = length;
        this.numBranches = numBranches;
        this.angle = angle;
    }

    public float getLength(){
        return length;
    }

    public float getNumBranches(){
        return numBranches;
    }

    public float getAngle(){
        return angle;
    }

    @Override
    public String toString() {
        return "length: " + length + ", numBranches: " + numBranches + ", angle: " + angle;
    }
}
