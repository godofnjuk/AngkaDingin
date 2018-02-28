package com.angka.dingin;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block {
	GameScreen gam;
	int number;
	boolean post=false;
	boolean vanish=false;
	float posx,posy;
	boolean jump=true;
	String anim="0";
	boolean jumped=false;
	int timesink=0;
	boolean sink;
	int j=0;
	int animal=0;
	boolean drop=false;
	private int status;
	Random rend = new Random();
	public Block(GameScreen gg){
		this.gam= gg;
		
		sink = rend.nextBoolean();
	}
	public void setanim(String e){
		this.anim=e;
	}
	public void setpos(float px,float py){
		posx=px;
		posy=py;
	}
	public void setjump(boolean woi){
		jump=woi;
	}
	public boolean getjump(){
		return jump;
	}
	public void setanimal(int e){
		animal=e;
	}
	public float getx(){
		return posx;
	}
	public float gety(){
		return posy;
	}
	
	public void setstatus(int stat){
		status=stat;
	}
	public void setnumber(int nomor){
		number=nomor;
	}
	public boolean cekpos(float px,float py){
		//System.out.println(px+","+py+", sedangkan limitnya adalah "+posx+","+posy );
		if(px >=posx && px<=posx+60  &&  py>=posy && py<=posy+42){
			if(jump==true){
				jumped=true;
			}
		}
		return jumped;
	}
	public boolean ceklik(float px,float py){
		boolean w=false;
		//System.out.println(px+","+py+", sedangkan limitnya adalah "+posx+","+posy );
		if(px >=posx && px<=posx+60  &&  py>=posy && py<=posy+42){
			w=true;
				
			
		}
		return w;
	}
	
	public void animate(){
		//random vanishing attribute
		
		if(post==false){
		Random rend = new Random();
		int value = rend.nextInt(500)+1;
		if(value<=1){
			vanish=true;
		}
		}
		if (post==true){
			vanish=false;
			
		}
		
		if(vanish==false){
		if(sink==true){timesink++;
		if(timesink<20){
			if(anim=="0"){anim="0";}
			else if(anim=="soundon1"){anim="soundon1";}
			else if(anim=="soundoff1"){anim="soundoff1";}
			else if(anim=="sub1"){anim="sub1";}
			else if(anim=="boards1"){anim="boards1";}
			else if(anim=="check1"){anim="check1";}
		}
		else if(timesink<40){
			if(anim=="0"){anim="1";}
			else if(anim=="soundon1"){anim="soundon2";}
			else if(anim=="soundoff1"){anim="soundoff2";}
			else if(anim=="sub1"){anim="sub2";}
			else if(anim=="boards1"){anim="boards2";}
			else if(anim=="check1"){anim="check2";}
		}
		else if(timesink<60){
			if(anim=="0"){anim="0";}
			else if(anim=="soundon1"){anim="soundon1";}
			else if(anim=="soundoff1"){anim="soundoff1";}
			else if(anim=="sub1"){anim="sub1";}
			else if(anim=="boards1"){anim="boards1";}
			else if(anim=="check1"){anim="check1";}
		}
		else{
			timesink=0;
			sink=false;
			
		}
		}
		else{
			sink=rend.nextBoolean();
			if(anim=="1"){anim="0";}
			else if(anim=="soundon1"){anim="soundon1";}
			else if(anim=="soundoff1"){anim="soundoff1";}
			else if(anim=="sub1"){anim="sub1";}
			else if(anim=="boards1"){anim="boards1";}
			else if(anim=="check1"){anim="check1";}
		}
		}
		else{
			number=0;
			animal=0;
			timesink++;
			if(timesink<5){
				anim="0";
			}else if(timesink<10){
				anim="1";
			}else if(timesink<15){
				anim="2";
			
			}else if(timesink<285){
				anim="3";
			}else if(timesink<290){
				anim="2";
			}else if(timesink<295){
				anim="1";
			}
			if(timesink>=300){
				vanish=false;
				anim="0";
				timesink=0;
			}
		}
		
	}
	public void crumble(){
		if(drop==true && anim!="crumble4"){
			timesink++;
			
		}
		if(timesink<15){
			anim="crumble1";
		}
		else if(timesink<30){
			anim="crumble2";
		}
		else if(timesink<45){
			anim="crumble3";
		}
		else if(timesink<60){
			anim="crumble4";
		}
		else{
			drop=false;
			timesink=0;
		}
		
		if(anim=="crumble4"){
			drop=false;
			anim="crumble4";
		}
	}
	
	public void render(SpriteBatch w){
		animate();
		
		gam.skin.getDrawable(anim).draw(w, posx, posy, 60, 42);//0
	}
	
}

