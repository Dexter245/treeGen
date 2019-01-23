package com.polymorphic_dissociation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TreeRenderer {

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private float viewportWidth = 0f;
    private float viewportHeight = 0f;

    public TreeRenderer(OrthographicCamera camera){
        this.camera = camera;
        viewportWidth = camera.viewportWidth;
        viewportHeight = camera.viewportHeight;

    }

    public void drawTest(){

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(120, 120, 120, 255);
        shapeRenderer.line(0, 0, 100, 100);
        shapeRenderer.end();

    }

    public void drawTree(Tree tree, float startPosX, float startPosY){

        viewportWidth = camera.viewportWidth;
        viewportHeight = camera.viewportHeight;
//        System.out.println("viewportWidth: " + viewportWidth + ", viewportHeight: " + viewportHeight);
//        System.out.println("startPosX: " + startPosX + ", startPosY: " + startPosY);

        Layer layer = tree.getLayer(0);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(0f, viewportHeight *0.5f, viewportWidth, viewportHeight *0.5f);
        shapeRenderer.line(viewportWidth *0.5f, 0, viewportWidth *0.5f, viewportHeight);
        shapeRenderer.setColor(Color.BLACK);

        //first line
        float bottomX = startPosX;
        float bottomY = startPosY;
        float angle = (layer.getNumBranches()-1)*layer.getAngle()*0.5f + 90;
        System.out.println("startAngle. " + angle);
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float topX = bottomX + layer.getLength() * cos;
        float topY = bottomY + layer.getLength() * sin;
        System.out.println("bottomX: " + bottomX + ", bottomY: " + bottomY + ", topX: " + topX + ", topY: " + topY);
        shapeRenderer.line(bottomX, bottomY, topX, topY);

        //other lines
        for(int i = 1; i < layer.getNumBranches(); i++){
            angle -= layer.getAngle();
            System.out.println("startAngle. " + angle);
            cos = (float) Math.cos(Math.toRadians(angle));
            sin = (float) Math.sin(Math.toRadians(angle));
            topX = bottomX + layer.getLength() * cos;
            topY = bottomY + layer.getLength() * sin;
            System.out.println("bottomX: " + bottomX + ", bottomY: " + bottomY + ", topX: " + topX + ", topY: " + topY);
            shapeRenderer.line(bottomX, bottomY, topX, topY);
        }

        shapeRenderer.end();

    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
