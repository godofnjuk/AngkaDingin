package com.angka.dingin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GameScreen implements Screen {
	
	final AngkaDingin game;
	int cash=0;
	TextureAtlas Atlas;
	Skin skin;
	OrthographicCamera camera;
	int scale =1;
	int timeAnim;
	String tod="";
	int waktugame=30;
	int waktu;
	int totalnomor;
	int totalpoin;
	boolean tambahin=false;
	boolean kurangin=false;
	int sink=0;
	int creditx,credity,titelx=(int)((300-256)/2),titely;
	int p = 9;//simpan posisi player
	float[] posx={0,60,120,180,240};
	float[] posy={18+40,60+40,102+40,144+40,186+40,44+40,86+40,130+40,172+40};
	boolean[] blockjump = {false,false,false,false,true,true,false,false,true,false,true,false,false,true,true,false,false,false,false};
	ArrayList<Block> blok = new ArrayList<Block>();
	Player player;
	private State state = State.STOPPED;
	private long startTime,timelapse;
	Preferences prefs = Gdx.app.getPreferences("My Preferences");
	
	public GameScreen(final AngkaDingin angka) {
		// TODO Auto-generated constructor stub
		
		this.game=angka;
		player=new Player(this);
		if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=22 || Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<6 ){
        	player.color = "yellow";
        }
        else if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=6 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<14 ){
        	player.color = "blue";
        }
        else if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=14 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<22 ){
        	player.color = "pink";
        }
		Atlas = new TextureAtlas(Gdx.files.internal("angkadingin.pack"));
		skin =new Skin(Atlas);
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 300, 512);
        for(int i=0;i<19;i++){
            blok.add(new Block(this));
        }
       
        prefs.flush();
        random();
        blockposition();
        //player.setcolor("blue");//move this to level manager.
        player.setanim("idle1");
        player.setpos( (posx[2]), (posy[2]));
        
		
        
	}
	public void blockposition(){
		blok.get(0).setpos((posx[0]), posy[1]);
        blok.get(1).setpos((posx[0]), posy[2]);
        blok.get(2).setpos((posx[0]), posy[3]);
        
        blok.get(3).setpos((posx[1]), posy[5]);
        blok.get(4).setpos((posx[1]), posy[6]);
        blok.get(5).setpos((posx[1]), posy[7]);
        blok.get(6).setpos((posx[1]), posy[8]);
        
        blok.get(7).setpos((posx[2]), posy[0]);
        blok.get(8).setpos((posx[2]), posy[1]);
        blok.get(9).setpos((posx[2]), posy[2]);
        blok.get(10).setpos((posx[2]), posy[3]);
        blok.get(11).setpos((posx[2]), posy[4]);
        
        blok.get(12).setpos((posx[3]), posy[5]);
        blok.get(13).setpos((posx[3]), posy[6]);
        blok.get(14).setpos((posx[3]), posy[7]);
        blok.get(15).setpos((posx[3]), posy[8]);
        
        blok.get(16).setpos((posx[4]), posy[1]);
        blok.get(17).setpos((posx[4]), posy[2]);
        blok.get(18).setpos((posx[4]), posy[3]);
        
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void restart(){
		p=9;
		player.drown=false;
		random();
		update();
		//player.setcolor("blue");//move this to level manager.
        player.setanim("idle1");
        player.setpos( (posx[2]), (posy[2]));
        totalpoin=0;
        waktugame=15;
        waktu=0;
	}
	public void update(){
		switch (p) {
		case 0:
			boolean[] a={false,true,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			blockjump = a;
			break;
		case 1:
			boolean[] b={true,false,true,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false};
			blockjump = b;
			break;

		case 2:
			boolean[] c={false,true,false,false,false,true,true,false,false,false,false,false,false,false,false,false,false,false,false};
			blockjump = c;
			break;

		case 3:
			boolean[] d={true,false,false,false,true,false,false,true,true,false,false,false,false,false,false,false,false,false,false};
			blockjump = d;
			break;

		case 4:
			boolean[] e={true,true,false,true,false,true,false,false,true,true,false,false,false,false,false,false,false,false,false};
			blockjump = e;
			break;

		case 5:
			boolean[] f={false,true,true, false,true,false,true, false,false,true,true,false, false,false,false,false, false,false,false};
			blockjump = f;
			break;

		case 6:
			boolean[] g={false,false,true, false,false,true,false, false,false,false,true,true, false,false,false,false, false,false,false};
			blockjump = g;
			break;

		case 7:
			boolean[] h={false,false,false, true,false,false,false, false,true,false,false,false, true,false,false,false, false,false,false};
			blockjump = h;
			break;

		case 8:
			boolean[] i={false,false,false, true,true,false,false, true,false,true,false,false, true,true,false,false, false,false,false};
			blockjump = i;
			break;

		case 9:
			boolean[] j={false,false,false, false,true,true,false, false,true,false,true,false, false,true,true,false, false,false,false};
			blockjump = j;
			break;

		case 10:
			boolean[] k={false,false,false, false,false,true,true, false,false,true,false,true, false,false,true,true, false,false,false};
			blockjump = k;
			break;

		case 11:
			boolean[] l={false,false,false, false,false,false,true, false,false,false,true,false, false,false,false,true, false,false,false};
			blockjump = l;
			break;

		case 12:
			boolean[] m={false,false,false, false,false,false,false, true,true,false,false,false, false,true,false,false, true,false,false};
			blockjump = m;
			break;

		case 13:
			boolean[] n={false,false,false, false,false,false,false, false,true,true,false,false, true,false,true,false, true,true,false};
			blockjump = n;
			break;

		case 14:
			boolean[] o={false,false,false, false,false,false,false, false,false,true,true,false, false,true,false,true, false,true,true};
			blockjump = o;
			break;

		case 15:
			boolean[] p={false,false,false, false,false,false,false, false,false,false,true,true, false,false,true,false, false,false,true};
			blockjump = p;
			break;

		case 16:
			boolean[] q={false,false,false, false,false,false,false, false,false,false,false,false, true,true,false,false, false,true,false};
			blockjump = q;
			break;

		case 17:
			boolean[] r={false,false,false, false,false,false,false, false,false,false,false,false, false,true,true,false, true,false,true};
			blockjump = r;
			break;

		case 18:
			boolean[] s={false,false,false, false,false,false,false, false,false,false,false,false, false,false,true,true, false,true,false};
			blockjump = s;
			break;
		
		}
		for(int x=0;x<19;x++){
            blok.get(x).setjump(blockjump[x]);	
		}
		}
	public void random(){
		
		for(int i=0;i<19;i++){
            blok.get(i).setjump(blockjump[i]);
            blok.get(i).setanimal(0);
            Random rand = new Random();
            int value = rand.nextInt(100) + 1;
            if(value<=40){blok.get(i).setnumber(0);}
            else if(value<=70 ){blok.get(i).setnumber(1);}
            else if(value<=90 ){blok.get(i).setnumber(2);}
            else if(value<=100 ){blok.get(i).setanimal(rand.nextInt(3)+1);}
            if(i==p){
            	blok.get(p).setnumber(0);
            	blok.get(p).post=true;
            }
            }
		
	}
	public void sink(){
		//jalankan setiap saat, jalankan di setiap individu blok
		//timer nambah
		//kurangi jumlah total angka sesuai angka yang ada di blok
		
	}
	@Override
	public void render(float delta) {
		//long startTime = System.currentTimeMillis();
		
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.input.setInputProcessor(inputProcess);
        
		// tell the camera to update its matrices.
        camera.update();
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        
        
        if(prefs.getBoolean("sound")){
        	if (!game.bgm1.isPlaying()&&!game.bgm2.isPlaying()&&!game.bgm3.isPlaying()&&!game.bgm4.isPlaying()){
        	
        Random ran = new Random();
        int j=0;
        game.bgm1.setVolume(0.5f);
        switch (j) {
		case 0:
			game.bgm1.play();
			break;
		case 1:
			game.bgm2.play();
			break;
		case 2:
			game.bgm3.play();
			break;
		case 3:
			game.bgm4.play();
			break;
		
		}
        	
        	
        }
        	}
        else{
        	if(game.bgm1.isPlaying()){
        	game.bgm1.pause();}
        	else if(game.bgm2.isPlaying()){
            	game.bgm2.pause();}
        	else if(game.bgm3.isPlaying()){
            	game.bgm3.pause();}
        	else if(game.bgm4.isPlaying()){
            	game.bgm4.pause();}
        }
        //change the background based on time of the day.
        
        if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=22 || Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<6 ){
        	skin.getDrawable("game_bg_night").draw(game.batch, 0, 0, 300, 512);
        	tod="night";
        }
        else if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=6 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<14 ){
        	skin.getDrawable("game_bg_morning").draw(game.batch, 0, 0, 300, 512);
        	tod="morning";
        }
        else if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=14 && Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<22 ){
        	skin.getDrawable("game_bg_noon").draw(game.batch, 0, 0, 300, 512);
        	tod="noon";
        }
        
        
		totalnomor=0;
		for(int i=0;i<19;i++){
			blok.get(i).render(game.batch);
			totalnomor = totalnomor+ blok.get(i).number;	
		}
		//
		
		
		
		player.render(game.batch);
		//System.out.println("Time left in seconds = " + (waktugame - ((System.currentTimeMillis() - startTime) / 1000)));
		if(this.state!= state.GAMEOVER){
		if(waktu>=0){cetakwaktu(waktu);}else{this.state=State.GAMEOVER;simpanuang();}
		cetakskor(totalpoin);
		highscore(prefs.getInteger("highscore_"+tod));
		prefs.flush();
		}
		
        switch (state) {
        case RUN:
        	for(int i=0;i<19;i++){
        		blok.get(i).post=false;
        		
        	}
        	blok.get(p).post=true;
        	//do suff here
        	if(totalnomor==0){
    			random();//random ulang nomor
    			
    		}
        	waktu = waktugame - ((int)(System.currentTimeMillis() - startTime) / 1000);//hitung waktu
    		//game.font16.draw(game.batch, "abcdefg", 100, 312);
    		if (Gdx.input.justTouched()) {
    			Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    			  camera.unproject(input);
    			
    			for(int i=0;i<19;i++){//lompat ketika di klik
    				
    				if(blok.get(i).cekpos(input.x, input.y)){//mengecek blok yang di klik
    					p=i;//mengganti order array sesuai posisi player;
    					blok.get(p).post=true;
    					//System.out.println(blok.get(i).post);
    					player.jump(blok.get(i).posx, blok.get(i).posy);
    					if(blok.get(i).vanish==true){
    						player.drown=true;//animasi tenggelam
    						state=State.GAMEOVER;
    						simpanuang();
    					}
    					else{
    						player.drown=false;
    						
    					}
    					
    					player.juump=true;
    					if(blok.get(i).animal==0){
    					player.numb=blok.get(i).number;
    					if(blok.get(i).number>0){
    						game.coin.play();
    					}
    					totalpoin=totalpoin+blok.get(i).number;
    					simpanpoin(totalpoin);
    					}
    					else{
    					player.animal=blok.get(i).animal;
    					if(player.animal==1){
    					waktugame=waktugame+5;
    					tambahin=true;
						final long id = game.pegu.play();
						Timer.schedule(new Task(){
							   @Override
							   public void run(){
							      game.pegu.stop(id);
							      }
							   }, 1.0f);
						}
    					if(player.animal==2){
    					totalpoin=totalpoin-5;
    					if(totalpoin<0){
    						totalpoin=0;
    					}
    					final long id = game.bear.play();
						Timer.schedule(new Task(){
							   @Override
							   public void run(){
							      game.bear.stop(id);
							      }
							   }, 1.0f);
    					kurangin=true;
    					}
    					if(player.animal==3){
    						totalpoin=totalpoin+3;
        					
        					final long id = game.dog.play();
    						Timer.schedule(new Task(){
    							   @Override
    							   public void run(){
    							      game.dog.stop(id);
    							      }
    							   }, 1.0f);
    					}
    					
    					}
    					
    					blok.get(i).animal=0;
    					blok.get(i).number=0;
    					update();
    					blok.get(i).jumped=false;
    					
    					//blok.get(i).j++;
    					
    					//blok.get(i).setanim("crumble"+blok.get(i).j);
    					
    					
    				}
    				blok.get(i).post=false;
    				blok.get(p).post=true;
    			}
            }
    		else if (Gdx.input.isKeyPressed(Keys.BACK)){
                if(!game.backpressed) {
                    game.backpressed=true;
                } else if (game.backpressed) {
                    game.backpressed=false;
                    Gdx.app.exit();
            } 
        }
    		if(tambahin==true || kurangin==true){//animasi tambah waktu
    			timeAnim++;
    		}
    		if(timeAnim<50 && timeAnim>0){
    			if(tambahin==true){skin.getDrawable("timeplus").draw(game.batch, 10, 360, 56, 36);}
    			if(kurangin==true){skin.getDrawable("minpoint").draw(game.batch, 112, 440, 56, 36);}
    		}
    		else{timeAnim=0;
    		if(tambahin==true){tambahin=false;}
    		if(kurangin==true){kurangin=false;}}
    		
    		
    		
    		
        	        break;
        	    case PAUSE:
        	    	restart();
        	    	this.state=State.STOPPED;
        	        break;
        	    case RESUME:
        	    	restart();
        	    	this.state=State.STOPPED;
        	        break;
        	    case STOPPED:
        	    	for(int i =0;i<19;i++){
        	    		blok.get(i).post=true;
        	    		
        	    	}
        	    	//System.out.println(prefs.getBoolean("sound"));
        	    	//draw endgame scoring!
        	    	
        	    	if(prefs.getBoolean("sound")){
        	    	blok.get(0).setanim("soundon1");}
        	    	else{
        	    		blok.get(0).setanim("soundoff1");}
        	    	//blok.get(2).setanim("boards1");
        	    	//blok.get(16).setanim("sub1");
        	    	//blok.get(18).setanim("check1");
        	    	
        	    	
        	    	skin.getDrawable("tap2start").draw(game.batch, 150-63, 128*3-64, 63*2, 16);
        	    	
        	    	//animasi credit
        	    	
        	    	if(timeAnim<250*2){
        	    		timeAnim++;
        	    		//System.out.println(timeAnim);
        	    	}
        	    	if(timeAnim<50*2 && timeAnim>0){
        	    	skin.getDrawable("title").draw(game.batch,titelx , 15, 256, 33);
        	    	}
        	    	else if(timeAnim<100*2 && timeAnim>50*2){//4 1 6 2 5 3
            	    	skin.getDrawable("credit4").draw(game.batch,(int)((300-56*3)/2) , 20, 56*3, 8*3);
            	    	
            	    }
        	    	else if(timeAnim<150*2 && timeAnim>100*2){
            	    	skin.getDrawable("credit1").draw(game.batch,(int)((300-184)/2) , 35, 74*2, 8*2);
            	    	skin.getDrawable("credit6").draw(game.batch,(int)((300-184)/2)+74*2+10 , 35, 18*2, 8*2);
            	    	skin.getDrawable("credit2").draw(game.batch,(int)((300-170)/2) , 15, 85*2, 8*2);
            	    }
        	    	else if(timeAnim<200*2 && timeAnim>150*2){
        	    		skin.getDrawable("credit5").draw(game.batch,(int)((300-150)/2) , 20, 150, 24);
            	    }
        	    	else if(timeAnim<250*2 && timeAnim>200*2){
        	    		skin.getDrawable("credit3").draw(game.batch,(int)((300-150)/2) , 20, 150, 16);
            	    }
        	    	
        	    	else if(timeAnim>=250*2){
            	    	timeAnim=0;
            	    	
            	    }
        	    	
        	    	
        	    	
        	    	if (Gdx.input.justTouched()) {
            			Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            			  camera.unproject(input);
            			  
            			  if(blok.get(0).ceklik(input.x, input.y)){
            				  prefs.putBoolean("sound", !prefs.getBoolean("sound"));
            				  prefs.flush();
            				  blok.get(0).setanim("soundoff1");
            				  
            			  }
            			  else if(blok.get(2).ceklik(input.x, input.y)){
            				  
            			  }
            			  else if(blok.get(16).ceklik(input.x, input.y)){
            				  
            			  }
            			  else if(blok.get(18).ceklik(input.x, input.y)){
            				  
            			  }
            			  else{
            			  blok.get(0).setanim("0");
              	    	blok.get(2).setanim("0");
              	    	blok.get(16).setanim("0");
              	    	blok.get(18).setanim("0");
            			  this.state=State.RUN;
            			  startTime = System.currentTimeMillis();
            			  }
        	    	}
        	    	else if (Gdx.input.isKeyPressed(Keys.BACK)){
        	            if(!game.backpressed) {
        	                game.backpressed=true;
        	            } else if (game.backpressed) {
        	                game.backpressed=false;
        	                Gdx.app.exit();
        	        } 
        	    }
        	    	break;
        	    case GAMEOVER:
        	    	cetakpoin(totalpoin);
        	    	
        	    	skin.getDrawable("retry").draw(game.batch, 150-32, 128*3-42, 32*2, 13*2);
        	    	if (Gdx.input.justTouched()) {
            			Vector3 input = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            			  camera.unproject(input);
            			  if(input.x >=(150-32) && input.x<=(150-32+32*2)  &&  input.y>=128*3-42 && input.y<=128*3-42+13*2){
            				  restart();
            				  for(int i=0;i<19;i++){
            					  blok.get(i).vanish=false;
            					  blok.get(i).post=true;
            					  blok.get(i).anim="0";
            					  blok.get(i).timesink=0;
            				  }
            				  this.state=State.STOPPED;
      		    		}
        	    	}
        	    	else if (Gdx.input.isKeyPressed(Keys.BACK)){
        	            if(!game.backpressed) {
        	                game.backpressed=true;
        	            } else if (game.backpressed) {
        	                game.backpressed=false;
        	                Gdx.app.exit();
        	        } 
        	    }
        	        break;
        	    case STARTED:
        	    	
        	    	break;
        	    }
		
        
        
        
		
		//game.font.draw(game.batch, ""+ p+"" , 100, 100);
		game.batch.end();
		
		//game logic goes here
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		 this.state = State.PAUSE;
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		 this.state = State.RESUME;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		this.state = State.PAUSE;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		game.dispose();
	}
	
	public void cetakwaktu(int angka){
		skin.getDrawable("time (" + (int)Math.floor(angka/10) +")").draw(game.batch, 10, 400, 20, 36);
		skin.getDrawable("time (" + (int)(angka%10) +")").draw(game.batch, 34, 400, 20, 36);
		skin.getDrawable("sec").draw(game.batch, 58, 400+36-16, 82, 16);
	}
	public void cetakskor(int angka){
		skin.getDrawable("points (" + (int)Math.floor((angka%1000)/100) +")").draw(game.batch, 10, 440, 30, 60);
		skin.getDrawable("points (" + (int)Math.floor((angka%100)/10) +")").draw(game.batch, 44, 440, 30, 60);
		skin.getDrawable("points (" + (int)(angka%10) +")").draw(game.batch, 78, 440, 30, 60);
		skin.getDrawable("pts").draw(game.batch, 112, 440+60-16, 32, 16);
	}
	public void cetakpoin(int angka){
		skin.getDrawable("points (" + (int)Math.floor(angka/10) +")").draw(game.batch, 150-4-40, 400, 30, 60);
		skin.getDrawable("points (" + (int)(angka%10) +")").draw(game.batch, 150+4, 400, 30, 60);
		skin.getDrawable("endpoint").draw(game.batch,155-38-4 , 400+74, 76, 16);
	}
	public void highscore(int angka){
		skin.getDrawable("points (" + (int)Math.floor((angka%1000)/100) +")").draw(game.batch, 300-10-30-4-30-4-30, 440, 30, 60);
		skin.getDrawable("points (" + (int)Math.floor((angka%100)/10) +")").draw(game.batch, 300-10-30-4-30, 440, 30, 60);
		skin.getDrawable("points (" + (int)(angka%10) +")").draw(game.batch, 300-10-30, 440, 30, 60);
		skin.getDrawable("high").draw(game.batch, 300-118, 440-16-10, 108, 16);
	}
	
	public void simpanuang(){
		
		int high = prefs.getInteger("poin");
		
		high=high+totalpoin;
			prefs.putInteger("poin", high);
			prefs.flush();
			
		
	}
	public void simpanpoin(int angka){
		
		int high = prefs.getInteger("highscore_"+tod);
		if(high<angka){
			prefs.putInteger("highscore_"+tod, angka);
			prefs.flush();
			
		}
		
	}
	
	
}
