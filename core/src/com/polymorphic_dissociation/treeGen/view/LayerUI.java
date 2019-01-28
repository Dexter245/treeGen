package com.polymorphic_dissociation.treeGen.view;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.polymorphic_dissociation.treeGen.model.Layer;
import com.polymorphic_dissociation.treeGen.model.Tree;

public class LayerUI extends Table {
    private static final int MAX_LENGTH = 500;
    private static final int MAX_WIDTH = 50;
    private static final int MAX_BRANCHES = 50;
    private static final int MAX_ANGLE = 360;
    private static final int LABEL_WIDTH = 175;
    private static final int SLIDER_WIDTH = 500;
    private Label labelLength;
    private Slider sliderLength;
    private Label labelWidth;
    private Slider sliderWidth;
    private Label labelNumBranches;
    private Slider sliderNumBranches;
    private Label labelAngle;
    private Slider sliderAngle;

    public LayerUI(final int index, Tree tree, Skin skin){
        Layer layer = tree.getLayer(index);

        //length
        float length = layer.getLength();
        labelLength = new Label("Length: " + length, skin);
        sliderLength = new Slider(0f, MAX_LENGTH, 1f, false, skin);
        sliderLength.setValue(length);
        sliderLength.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                float value = sliderLength.getValue();
                labelLength.setText("Length: " + value);
                layer.setLength(value);
                return false;
            }
        });
        setFillParent(true);
        add(labelLength).width(LABEL_WIDTH);
        add(sliderLength).width(SLIDER_WIDTH);
        row();

        //width
        float width = layer.getWidth();
        labelWidth = new Label("Width: " + width, skin);
        sliderWidth = new Slider(0f, MAX_WIDTH, 1f, false, skin);
        sliderWidth.setValue(width);
        sliderWidth.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                float value = sliderWidth.getValue();
                labelWidth.setText("Width: " + value);
                layer.setWidth(value);
                return false;
            }
        });
        add(labelWidth).width(LABEL_WIDTH);
        add(sliderWidth).width(SLIDER_WIDTH);
        row();

        //numBranches
        float numBranches = layer.getNumBranches();
        labelNumBranches = new Label("NumBranches: " + numBranches, skin);
        sliderNumBranches= new Slider(0f, MAX_BRANCHES, 1f, false, skin);
        sliderNumBranches.setValue(numBranches);
        sliderNumBranches.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                float value = sliderNumBranches.getValue();
                labelNumBranches.setText("NumBranches: " + value);
                layer.setNumBranches(value);
                return false;
            }
        });
        add(labelNumBranches).width(LABEL_WIDTH);
        add(sliderNumBranches).width(SLIDER_WIDTH);
        row();

        //angle
        float angle = layer.getAngle();
        labelAngle = new Label("Angle: " + angle, skin);
        sliderAngle= new Slider(0f, MAX_ANGLE, 1f, false, skin);
        sliderAngle.setValue(angle);
        sliderAngle.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                float value = sliderAngle.getValue();
                labelAngle.setText("Angle: " + value);
                layer.setAngle(value);
                return false;
            }
        });
        add(labelAngle).width(LABEL_WIDTH);
        add(sliderAngle).width(SLIDER_WIDTH);
        row();
    }
}
