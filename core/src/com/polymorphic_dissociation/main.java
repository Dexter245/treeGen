package com.polymorphic_dissociation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.polymorphic_dissociation.treeGen.model.Tree;
import com.polymorphic_dissociation.treeGen.view.TreeRenderer;

public class main extends ApplicationAdapter {

    private OrthographicCamera camera;
	private TreeRenderer treeRenderer;
	private Tree tree;

	@Override
	public void create () {
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		treeRenderer = new TreeRenderer(camera);
		tree = new Tree();
		tree.addLayer(150, 10, 1, 0);
        tree.addLayer(100, 5, 3, 40);
        tree.addLayer(50, 3, 5, 20);
        tree.addLayer(25, 2, 7, 20);
	}

	@Override
	public void render () {
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        treeRenderer.drawTree(tree, new Vector2(Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight()*0.5f));
	}
	
	@Override
	public void dispose () {
	    treeRenderer.dispose();
	}
}
