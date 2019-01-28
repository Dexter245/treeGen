package com.polymorphic_dissociation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.polymorphic_dissociation.treeGen.model.Tree;
import com.polymorphic_dissociation.treeGen.view.UIView;
import com.polymorphic_dissociation.treeGen.view.TreeView;

public class Main extends ApplicationAdapter {

    private Viewport viewportTree;
    private Viewport viewportUI;
	private TreeView treeView;
	private UIView UIView;
	private Tree tree;

	@Override
	public void create () {
        viewportTree = new ScreenViewport();
        viewportTree.setWorldSize(Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight());
		tree = new Tree();
		tree.addLayer(150, 10, 1, 0);
        tree.addLayer(100, 5, 3, 40);
        tree.addLayer(50, 3, 5, 20);
        tree.addLayer(25, 2, 7, 20);
        treeView = new TreeView(viewportTree, tree, new Vector2(Gdx.graphics.getWidth()*0.25f,
                Gdx.graphics.getHeight()*0.25f));

        viewportUI = new ScreenViewport();
        UIView = new UIView(viewportUI, tree);

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        treeView.render();
        UIView.render();
	}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewportTree.setScreenPosition(0, 0);
        viewportTree.setScreenSize(width/2, height);
        viewportTree.setWorldSize(width/2f, height);
        treeView.setRootPos(new Vector2(width*0.25f, height*0.1f));

        viewportUI.setScreenPosition(width/2, 0);
        viewportUI.setScreenSize(width/2, height);
        viewportUI.setWorldSize(width/2f, height);
    }

    @Override
	public void dispose () {
	    treeView.dispose();
	}
}
