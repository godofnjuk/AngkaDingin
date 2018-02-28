package com.angka.dingin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class MenuScreen implements Screen {
	final AngkaDingin game;
	TextureAtlas Atlas;
	Skin skin;
	int tekan=0;
	int time;
	OrthographicCamera camera;
	public MenuScreen(final AngkaDingin e){
		this.game=e;
		Atlas = new TextureAtlas(Gdx.files.internal("angkadingin.pack"));
		skin =new Skin(Atlas);
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 480);
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		        
				
		        camera.update();
		        game.batch.setProjectionMatrix(camera.combined);

		        game.batch.begin();
		        //start rendering
		        skin.getDrawable("menu_bg").draw(game.batch, 0, 0, 320, 480);
		        time++;
		        if(time<60){
		        	skin.getDrawable("aurora").draw(game.batch, 0, 480-120, 320, 120);
		        }
		        else if(time<120){
		        	
		        }
		        else time=0;
		        
		        
		        
		        
		        if (Gdx.input.isTouched()) {
	    			Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
	    			  camera.unproject(input);
	    			  skin.getDrawable("play_btn2").draw(game.batch, 160-31, 142, 63, 72);
		    		if(input.x >=(160-31) && input.x<=(160-31+63)  &&  input.y>=142 && input.y<=142+72){
		    			tekan=1;
		    		}
		        }
		        else{
		        	if(tekan==1){
		        		tekan=0;//untuk animasi menekan tombol
		        		
		        		game.setScreen(new GameScreen(game));
		        		dispose();
		        	}
		        	skin.getDrawable("play_btn").draw(game.batch, 160-31, 142, 63, 72);
		        }
		        game.batch.end();
		        
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
