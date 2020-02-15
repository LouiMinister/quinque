package com.lectopia.quinque.view.bean;

import java.awt.Rectangle;



/**
 * ���� ���� ������ ������ �ִ� Ŭ������.
 * @author QuinqueBliss
 *
 */
public class Marker {
   /**
    * ����, ����, ��, ����ǥ, ���ϵ�ī�� ����, ���ϵ�ī�� ����, ���ϵ�ī�� ��, ���ϵ�ī�� ����ǥ��
    * ������ ������ �ִ�.
    */
   private Cane cane;
   /**
    * Ÿ�Ͽ� ������ ��ȣ��.
    */
   private int mapTileNum;
   /**
    * ��ư�� actionCommand�� ��ġ�ϴ� �̸��̴�.
    */
   private String name;
   /**
    * url
    */
   private String[] image;
   /**
    * ���� ���� �Ǿ����� ǥ�����ִ� ������ �Ѵ�.
    */
   private boolean selected;
   /**
    * ���� ������ �������� �˷��ִ� ������ �Ѵ�.
    */
   private int turn;
   /**
    * �����ߴ��� �˷��ִ� ������ �Ѵ�.
    */
   private boolean goalFlag;
   private boolean hiddenFlag;
   private Rectangle rectangle;
   
   /**
    * null parameter �����ڴ�.
    */
   public Marker() {}
   
   public Marker(Cane cane, String name) {
	   this.cane = cane;
	   this.name = name;
	   this.mapTileNum = -1;
   }
   
   public Marker(Cane cane, String name, String[] image) {
	   this.cane = cane;
	   this.name = name;
	   this.image = image;
	   this.mapTileNum = -1;
	   if(cane.getCane().equals("Questionmark"))
		   this.hiddenFlag = false;
	   else
		   this.hiddenFlag = true;
   }
   
   /**
    * 
    * @param cane ������ ����
    * @param name �̸�
    * @param image url
    * @param turn ������
    */
   public Marker(Cane cane, String character, String name, String[] image, Rectangle rectangle) {
	   this.cane = cane;
	   this.name = name; 
	   this.image = image;
	   this.turn = 1;
	   this.rectangle = rectangle;
	   this.mapTileNum = -1;
	   this.goalFlag = false;
	   if(cane.getCane().equals("Questionmark"))
		   this.hiddenFlag = false;
	   else
		   this.hiddenFlag = true;
	   this.selected = false;
   }

	public Marker(Marker obj) {
		this.cane = obj.getCane();
		this.name = obj.getName();
		this.image = obj.getImage();
		this.turn = obj.getTurn();
		this.rectangle = obj.getRectangle();
		this.mapTileNum = obj.getMapTileNum();
		this.goalFlag = obj.getGoalFlag();
		if (obj.getCane().getCane().equals("Questionmark"))
			this.hiddenFlag = false;
		else
			this.hiddenFlag = obj.getHiddenFlag();
		this.selected = obj.getSelected();
	}

	public Cane getCane() {
		return cane;
	}
   
   public void setCane(Cane cane) {
	   this.cane = cane;
   }
   
   public String getName() {
	   return name;
   }
   
   public void setName(String name) {
	   this.name = name;
   }
   
   
   /**
    * getter()
    * @return Ÿ�Ͽ� ������ ��ȣ�� �����Ѵ�.
    */
   public int getMapTileNum() {
      return mapTileNum;
   }
   /**
    * setter()
    * @param mapTitleNum Ÿ�Ͽ� ������ ��ȣ�� �����Ѵ�.
    */
   public void setMapTileNum(int mapTileNum) {
      this.mapTileNum = mapTileNum;
   }
   /**
    * getter()
    * @return url
    */
   public String[] getImage() {
      return image;
   }
   /**
    * setter()
    * @param image url
    */
   public void setImage(String[] image) {
      this.image = image;
   }
   /**
    * getter()
    * @return ���õǾ����� true, �ƴϸ� false�� �����Ѵ�.
    */
   public boolean getSelected() {
      return selected;
   }
   /**
    * setter()
    * @param selected ���ÿ��θ� �����Ѵ�.
    */
   public void setSelected(boolean selected) {
      this.selected = selected;
   }
   /**
    * getter()
    * @return �� ������ �����Ѵ�.
    */
   public int getTurn() {
      return turn;
   }
   /**
    * setter()
    * @param turn �� ����
    */
   public void setTurn(int turn) {
      this.turn = turn;
   }

   
   public Rectangle getRectangle() {
	   return rectangle;
   }
   
   public void setRectangle(Rectangle rectangle) {
	   this.rectangle = rectangle;
   }
   
   /**
    * getter()
    * @return �����ߴ��� ���θ� �����Ѵ�.
    */
   public boolean getGoalFlag() {
      return goalFlag;
   }
   
   /**
    * setter()
    * @param goalFlag ���� ����
    */
   public void setGoalFlag(boolean goalFlag) {
      this.goalFlag = goalFlag;
   }
   
   public boolean getHiddenFlag() {
	   return hiddenFlag;
   }
   
   public void setHiddenFlag(boolean hiddenFlag) {
	   this.hiddenFlag = hiddenFlag;
   }

}