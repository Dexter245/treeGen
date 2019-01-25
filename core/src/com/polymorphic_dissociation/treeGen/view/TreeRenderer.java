package com.polymorphic_dissociation.treeGen.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.polymorphic_dissociation.treeGen.model.Layer;
import com.polymorphic_dissociation.treeGen.model.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeRenderer {

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Vector2 viewport;

    public TreeRenderer(OrthographicCamera camera){
        this.camera = camera;
        viewport = new Vector2(camera.viewportWidth, camera.viewportHeight);

    }

    public void drawTree(Tree tree, Vector2 startPos){

        viewport.set(camera.viewportWidth, camera.viewportHeight);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.line(0f, viewport.y *0.5f, viewport.x, viewport.y *0.5f);
        shapeRenderer.line(viewport.x *0.5f, 0, viewport.x *0.5f, viewport.y);
        shapeRenderer.setColor(Color.BLACK);

        List<Vector2> bottoms = new ArrayList<Vector2>();
        List<Vector2> tops = new ArrayList<Vector2>();
        Vector2 top = new Vector2();
        Layer layer;
        bottoms.add(startPos);

        float cos, sin, width, angle = 0f;
        System.out.println("num layers: " + tree.getNumLayers());

        for(int i = 0; i < tree.getNumLayers(); i++){//each layer

            layer = tree.getLayer(i);
            width = layer.getWidth();

            System.out.println("current layer: " + layer + ", bottoms.size: " + bottoms.size());

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
                    System.out.println("angle: " + angle + ", bottom: " + bottom + ", top: " + top +
                            ", tops.size: " + tops.size());
                    shapeRenderer.rectLine(bottom, top, width);
                }

            }

            bottoms = new ArrayList<Vector2>(tops);
            tops = new ArrayList<Vector2>();

        }


        shapeRenderer.end();
        System.out.println();

    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
