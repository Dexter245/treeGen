package com.polymorphic_dissociation.treeGen.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.polymorphic_dissociation.treeGen.model.Tree;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UIView {

    private Viewport viewport;
    private final Tree tree;
    private Stage stage;
    private Skin skin;
    private List<LayerUI> layerUIs = new ArrayList<>();

    public UIView(Viewport viewport, final Tree tree){
        this.viewport = viewport;
        this.tree = tree;
        stage = new Stage(viewport);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        for(int i = 0; i < tree.getNumLayers(); i++){
            LayerUI layerUI = new LayerUI(i, tree, skin);
            layerUI.setPosition(0, -400 + 125*i);
            stage.addActor(layerUI);
            System.out.println("asdf");
        }
//        stage.setDebugAll(true);
    }

    private void addListeners(){

    }

    public void render(){
        viewport.apply(true);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }



}
