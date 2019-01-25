package com.polymorphic_dissociation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class treeGen extends ApplicationAdapter {

    private OrthographicCamera camera;
	private TreeRenderer treeRenderer;
	private Tree tree;

	@Override
	public void create () {
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//	    System.out.println("camera.width: " + camera.viewportWidth + ", camera.height: " + camera.viewportHeight);
		treeRenderer = new TreeRenderer(camera);
		tree = new Tree();
		tree.addLayer(200, 1, 0);
        tree.addLayer(100, 3, 40);
        tree.addLayer(50, 5, 20);
        tree.addLayer(25, 1, 90);
	}

	@Override
	public void render () {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
//        System.out.println("camera.width: " + camera.viewportWidth + ", camera.height: " + camera.viewportHeight);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        treeRenderer.drawTest();
//        treeRenderer.drawTree(tree, Gdx.graphics.getWidth()*0.5f, 100f);
        treeRenderer.drawTree(tree, new Vector2(Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight()*0.5f));
	}
	
	@Override
	public void dispose () {
	    treeRenderer.dispose();
	}
}
