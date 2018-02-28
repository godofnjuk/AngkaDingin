package com.angka.dingin;

import java.util.Calendar;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	GameScreen gg;
	boolean drown=false;
	float posx,posy;
	String color,anim;
	float jumpx,jumpy;
	boolean juump=false;
	int numb=0;
	boolean flip=false;
	String direct="right";
	int timeAnim,timebreath;
	boolean getnum=false;
	int animal=0;
	public Player(GameScreen gk){
		this.gg=gk;
		
		
		anim = "idle1";
	}
	public void setpos(float x,float y){
		posx=x;
		posy=y;
	}
	public void setx(float x){
		posx=x+6;
	}
	public void sety(float y){
		posy=y+30;
	}
	
	public void jump(float x,float y){
		jumpx=x;
		jumpy=y;
	}
	
	public void setcolor(String col){
		color=col;
	}
	public void setanim(String ani){
		anim=ani;
	}
	
	public void animate(){
		//jumping to coordinated block
		//System.out.println(numb+" dan "+juump);
		if(drown==true){
			timeAnim++;
			if(timeAnim<10){
				anim="drown1";
			}
			else if(timeAnim<20){
				anim="drown2";
			}
			else{
				timeAnim=0;
			}
		}
		else {
			
		
		if(animal==0){
			if(numb!=0 && juump==false){
				
				//System.out.println("timeanim : "+timeAnim);
				
				anim="get"+numb;
				timeAnim++;
				
				if(timeAnim<10){					
					anim="get"+numb;}
				else if (timeAnim<20){
					anim="got"+numb;}
				else if(timeAnim<30){					
					anim="get"+numb;}
				else{
					numb=0;
					timeAnim=0;
					getnum=false;
				}
				}	
			
			if(numb==0){
				//timeAnim=0;
				anim="idle1";
					timebreath++;
				if(timebreath<30){
					anim="idle1";
				}
				else if(timebreath<60){
					anim="idle2";
				}
				else{
					timebreath=0;
					
				}
				}}
			else{
				if(juump==false){
					
					//System.out.println("timeanim : "+timeAnim);
					
					anim="get"+animal+"a";
					timeAnim++;
					
					if(timeAnim<10){					
						anim="get"+animal+"a";}
					else if (timeAnim<20){
						anim="got"+animal+"a";}
					else if(timeAnim<30){					
						anim="get"+animal+"a";}
					else{
						numb=0;
						animal=0;
						timeAnim=0;
						getnum=false;
					}
					}	
				
				
			}
			
		}		
	}
	
	public void render(SpriteBatch y){
		animate();
		if(juump==true){
			anim="jump";
			if(posx<jumpx){
				posx+=2;
				if(posx<jumpx){
				posx+=2;}
				direct="right";
			}
			if(posx>jumpx){
				posx-=2;
				if(posx>jumpx){
				posx-=2;}
				direct="left";
			}
			if(posy<jumpy){
				posy+=2;
				if(posy<jumpy){
				posy+=2;}
			}
			if(posy>jumpy){
				posy-=2;
				if(posy>jumpy){
				posy-=2;}
			}
			
		}
		if(posx==jumpx && posy==jumpy){
			juump=false;
			
		}
		if(animal==2){
			//bear positioning
			//System.out.println("bear");
			if(direct=="right"){
				gg.skin.getDrawable(color+"_"+anim+"_"+direct).draw(y, posx-3, posy+30, 64, 106);}
			else{
				gg.skin.getDrawable(color+"_"+anim+"_"+direct).draw(y, posx, posy+30, 64, 106);
			}
		}
		else{
			//direct="left";
			//anim="got2a";
			//gg.skin.getDrawable(color+"_"+anim+"_"+direct).draw(y, posx, posy+30, 64, 106);
			//gg.skin.getDrawable(color+"_"+anim+"_"+direct).draw(y, posx-3, posy+30, 64, 106);
		gg.skin.getDrawable(color+"_"+anim+"_"+direct).draw(y, posx+6, posy+30, 52, 112);
		}
	}

	
}
