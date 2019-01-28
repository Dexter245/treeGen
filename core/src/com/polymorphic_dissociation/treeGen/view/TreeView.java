package com.polymorphic_dissociation.treeGen.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.polymorphic_dissociation.treeGen.model.Layer;
import com.polymorphic_dissociation.treeGen.model.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeView {

    private Viewport viewport;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Tree tree;
    private Vector2 rootPos;

    public TreeView(Viewport viewport, Tree tree, Vector2 rootPos){
        this.viewport = viewport;
        this.tree = tree;
        this.rootPos = rootPos;

    }

    public void render(){

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        viewport.apply(true);
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rectLine(0f, rootPos.y, viewport.getScreenWidth(),
                rootPos.y, 1f);
        shapeRenderer.rectLine(viewport.getScreenWidth() *0.5f, 0, viewport.getScreenWidth() *0.5f,
                viewport.getScreenHeight(), 1f);
        shapeRenderer.setColor(Color.RED);

        List<Vector2> bottoms = new ArrayList<Vector2>();
        List<Vector2> tops = new ArrayList<Vector2>();
        Vector2 top = new Vector2();
        Layer layer;
        bottoms.add(rootPos);

        float cos, sin, width, angle = 0f;
//        System.out.println("num layers: " + tree.getNumLayers());

        int numLines = 0;

        for(int i = 0; i < tree.getNumLayers(); i++){//each layer

            shapeRenderer.setColor(0f, 0f, 0f, 1f - 0.25f*i);
//            shapeRenderer.getRenderer().tra

            layer = tree.getLayer(i);
            width = layer.getWidth();

//            System.out.println("current layer: " + layer + ", bottoms.size: " + bottoms.size());

            for(Vector2 bottom : bottoms){//each branching
                for(int k = 0; k < layer.getNumBranches(); k++){//each branch
                    if(k == 0)
                        if(layer.getNumBranches() >= 2)
                            angle = (layer.getNumBranches()-1)*layer.getAngle()*0.5f + 90;
                        else
                            angle = layer.getAngle() + 90;
                    else
                        angle -= layer.getAngle();
                    cos = (float) Math.cos(Math.toRadians(angle));
                    sin = (float) Math.sin(Math.toRadians(angle));
                    top.set(bottom.x + layer.getLength() * cos, bottom.y + layer.getLength() * sin);
                    tops.add(new Vector2(top));
//                    System.out.println("angle: " + angle + ", bottom: " + bottom + ", top: " + top +
//                            ", tops.size: " + tops.size());
                    shapeRenderer.rectLine(bottom, top, width);
                    numLines++;
                }

            }

            bottoms = new ArrayList<Vector2>(tops);
            tops = new ArrayList<Vector2>();

        }

        System.out.println("numLines: " + numLines);

        shapeRenderer.end();
//        System.out.println();

    }

    public void setRootPos(Vector2 pos){
        rootPos = pos;
    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
