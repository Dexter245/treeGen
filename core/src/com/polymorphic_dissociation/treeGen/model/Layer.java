package com.polymorphic_dissociation.treeGen.model;

public class Layer {
    private float length;
    private float width;
    private float numBranches;
    private float angle;

    public Layer(float length, float width, float numBranches, float angle){
        this.length = length;
        this.width = width;
        this.numBranches = numBranches;
        this.angle = angle;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getNumBranches() {
        return numBranches;
    }

    public void setNumBranches(float numBranches) {
        this.numBranches = numBranches;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "length: " + length + ", width: " + width + ", numBranches: " + numBranches + ", angle: " + angle;
    }
}
