package com.polymorphic_dissociation;

public class Layer {
    private float length = 0f;
    private float width = 0f;
    private float numBranches = 0f;
    private float angle = 0f;

    public Layer(float length, float width, float numBranches, float angle){
        this.length = length;
        this.width = width;
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

    public float getWidth(){
        return width;
    }

    @Override
    public String toString() {
        return "length: " + length + ", width: " + width + ", numBranches: " + numBranches + ", angle: " + angle;
    }
}
