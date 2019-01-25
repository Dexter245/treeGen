package com.polymorphic_dissociation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

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

    public void drawTest(){

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(120, 120, 120, 255);
        shapeRenderer.line(0, 0, 100, 100);
        shapeRenderer.end();

    }

    public void drawTree(Tree tree, Vector2 startPos){

        viewport.set(camera.viewportWidth, camera.viewportHeight);
//        System.out.println("viewportWidth: " + viewportWidth + ", viewportHeight: " + viewportHeight);
//        System.out.println("startPosX: " + startPosX + ", startPosY: " + startPosY);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.line(0f, viewport.y *0.5f, viewport.x, viewport.y *0.5f);
        shapeRenderer.line(viewport.x *0.5f, 0, viewport.x *0.5f, viewport.y);
        shapeRenderer.setColor(Color.BLACK);

        List<Vector2> bottoms = new ArrayList<Vector2>();
        List<Vector2> tops = new ArrayList<Vector2>();
        Vector2 top = new Vector2();
        Layer layer = null;
        bottoms.add(startPos);

        float cos = 0f, sin = 0f, angle = 0f, width = 0f;
        System.out.println("num layers: " + tree.getNumLayers());

        for(int k = 0; k < tree.getNumLayers(); k++){//each layer

            layer = tree.getLayer(k);
            width = layer.getWidth();

            System.out.println("current layer: " + layer + ", bottoms.size: " + bottoms.size());

            for(int j = 0; j < bottoms.size(); j++){//each branching

                for(int i = 0; i < layer.getNumBranches(); i++){//each branch
                    if(i == 0)
                        if(layer.getNumBranches() >= 2)
                            angle = (layer.getNumBranches()-1)*layer.getAngle()*0.5f + 90;
                        else
                            angle = layer.getAngle() + 90;
                    else
                        angle -= layer.getAngle();
                    cos = (float) Math.cos(Math.toRadians(angle));
                    sin = (float) Math.sin(Math.toRadians(angle));
                    top.set(bottoms.get(j).x + layer.getLength() * cos, bottoms.get(j).y + layer.getLength() * sin);
                    tops.add(new Vector2(top));
                    System.out.println("angle: " + angle + ", bottom: " + bottoms.get(j) + ", top: " + top +
                            ", tops.size: " + tops.size());
//                    shapeRenderer.line(bottoms.get(j).x, bottoms.get(j).y, top.x, top.y);
                    shapeRenderer.rectLine(bottoms.get(j), top, width);
                }

            }

//            bottoms = tops;
            bottoms = new ArrayList<Vector2>(tops);
            tops = new ArrayList<Vector2>();

        }


        shapeRenderer.end();
        System.out.println("");

    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
