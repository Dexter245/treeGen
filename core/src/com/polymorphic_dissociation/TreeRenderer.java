package com.polymorphic_dissociation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

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

        Layer layer = tree.getLayer(0);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(0f, viewport.y *0.5f, viewport.x, viewport.y *0.5f);
        shapeRenderer.line(viewport.x *0.5f, 0, viewport.x *0.5f, viewport.y);
        shapeRenderer.setColor(Color.BLACK);

        //first line
        Vector2 bottom = new Vector2(startPos);
        float angle = (layer.getNumBranches()-1)*layer.getAngle()*0.5f + 90;
        System.out.println("startAngle. " + angle);
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        Vector2 top = new Vector2(bottom.x + layer.getLength() * cos, bottom.y + layer.getLength() * sin);
        System.out.println("bottom: " + bottom + ", top: " + top);
        shapeRenderer.line(bottom.x, bottom.y, top.x, top.y);

        //other lines
        for(int i = 1; i < layer.getNumBranches(); i++){
            angle -= layer.getAngle();
            System.out.println("startAngle. " + angle);
            cos = (float) Math.cos(Math.toRadians(angle));
            sin = (float) Math.sin(Math.toRadians(angle));
            top.x = bottom.x + layer.getLength() * cos;
            top.y = bottom.y + layer.getLength() * sin;
            System.out.println("bottom: " + bottom + ", top: " + top);
            shapeRenderer.line(bottom.x, bottom.y, top.x, top.y);
        }

        shapeRenderer.end();

    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
