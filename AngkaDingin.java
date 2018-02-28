package com.angka.dingin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AngkaDingin extends Game {
	SpriteBatch batch;
    Sound coin, pegu,bear,dog;
    Music bgm1,bgm2,bgm3,bgm4;
	boolean backpressed=false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		coin = Gdx.audio.newSound(Gdx.files.internal("coin.wav"));
	    pegu = Gdx.audio.newSound(Gdx.files.internal("pegu.wav"));
	    bear = Gdx.audio.newSound(Gdx.files.internal("bear(2).wav"));
	    dog = Gdx.audio.newSound(Gdx.files.internal("dog.mp3"));
	    bgm1 = Gdx.audio.newMusic(Gdx.files.internal("bgm1.mp3"));
	    bgm2 = Gdx.audio.newMusic(Gdx.files.internal("bgm2.mp3"));
	    bgm3 = Gdx.audio.newMusic(Gdx.files.internal("bgm3.mp3"));
	    bgm4 = Gdx.audio.newMusic(Gdx.files.internal("bgm4.mp3"));
		this.setScreen(new GameScreen(this));//ngeset layar game jadi mainmenu Drop Style
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void dispose() {
        batch.dispose();
        coin.dispose();
        pegu.dispose();
        bgm1.dispose();
        bgm2.dispose();
        bgm3.dispose();
        bgm4.dispose();
        
    }
	
}
