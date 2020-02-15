package com.lectopia.quinque.view.bean;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * ��Ű����(Quinque Stone) ���ӿ��� ���� �����ϰ� �ִ� �� Ÿ��(Map Tile)���� �����ϴ� Ŭ�����̴�.
 * <br>- �� Ÿ�ϵ鿡 ���� ��Ű���� ��(Marker)�� �̵� ���ɼ��� �����ϴ� �޼����
 *���� �̵� ������ �� Ÿ�ϵ��� �迭�� ��ȯ�Ͽ� �ִ� �޼��带 �����ϰ� �ִ�.
 * @author QuinqueBliss
 *
 */
public class Map {
	/**
	 * �� Ÿ�� ����Ʈ�� ����Ǿ� �ִ� �÷���(Collection)�� ���� ���� �����̴�.
	 */
	private ArrayList<MapTile> mapTiles;
	
	private int table[][] = new int[][]
	{
        {-1,-1,1,2,3,4,5},
        {1,25,2,3,4,5,6},
        {2,1,3,4,5,6,7},
        {3,2,4,5,6,7,8},
        {4,3,5,6,7,8,9},
        {4,3,16,17,18,19,20},
        {5,4,6,7,8,9,10},
        {6,5,7,8,9,10,11},
        {7,6,8,9,10,11,12},
        {8,7,9,10,11,12,13},
        {8,7,21,22,18,23,24},
        {9,8,10,11,12,13,14},
        {10,9,11,12,13,14,15},
        {11,10,12,13,14,15,16},
        {12,11,13,14,15,25,26},
        {13,12,14,15,25,26,26},
        {14,13,15,25,26,26,26},
        {15,14,25,26,26,26,26},
        {16,4,17,18,19,20,12},
        {17,16,18,19,20,12,13},
        {18,22,23,24,25,26,26},
        {19,18,20,12,13,14,15},
        {20,19,12,13,14,15,25},
        {21,8,22,18,23,24,25},
        {22,21,18,23,24,25,26},
        {23,18,24,25,26,26,26},
        {24,23,25,26,26,26,26},
        {25,15,26,26,26,26,26}
     };
	
	/**
	 * Map Ŭ������ null �Ķ���� �������̴�.
	 */
	public Map() {
		mapTiles = new ArrayList<MapTile>();
		
		mapTiles.add(new MapTile(25, "/playUI/blueBlock.png", new Rectangle(596, 504, 150, 160), false));
		mapTiles.add(new MapTile(24, "/playUI/blackBlock.png", new Rectangle(606, 437, 120, 120), false));
		mapTiles.add(new MapTile(23, "/playUI/blackBlock.png", new Rectangle(606, 381, 120, 120), false));
		mapTiles.add(new MapTile(18, "/playUI/blueBlock.png", new Rectangle(596, 306, 150, 160), false));
		mapTiles.add(new MapTile(22, "/playUI/blackBlock.png", new Rectangle(606, 238, 120, 120), false));
		mapTiles.add(new MapTile(21, "/playUI/blackBlock.png", new Rectangle(606, 175, 120, 120), false));
		mapTiles.add(new MapTile(5, "/playUI/blackBlock.png", new Rectangle(848, 238, 120, 120), false));
		mapTiles.add(new MapTile(6, "/playUI/blackBlock.png", new Rectangle(771, 192, 120, 120), false));
		mapTiles.add(new MapTile(7, "/playUI/blackBlock.png", new Rectangle(698, 149, 120, 120), false));	
		mapTiles.add(new MapTile(3, "/playUI/blackBlock.png", new Rectangle(848, 394, 120, 120), false));
		mapTiles.add(new MapTile(17, "/playUI/blackBlock.png", new Rectangle(717, 327, 120, 120), false));
		mapTiles.add(new MapTile(16, "/playUI/blackBlock.png", new Rectangle(806, 327, 120, 120), false));
		mapTiles.add(new MapTile(4, "/playUI/blueBlock.png", new Rectangle(912, 306, 150, 160), true));
		mapTiles.add(new MapTile(19, "/playUI/blackBlock.png", new Rectangle(489, 327, 120, 120), false));
		mapTiles.add(new MapTile(15, "/playUI/blackBlock.png", new Rectangle(504, 477, 120, 120), false));
		mapTiles.add(new MapTile(14, "/playUI/blackBlock.png", new Rectangle(432, 431, 120, 120), false));
		mapTiles.add(new MapTile(1, "/playUI/blackBlock.png", new Rectangle(698, 483, 120, 120), false));
		mapTiles.add(new MapTile(2, "/playUI/blackBlock.png", new Rectangle(771, 442, 120, 120), false));
		mapTiles.add(new MapTile(26, "/playUI/goalBlock.png", new Rectangle(800, 500, 120, 170), false)); // ��
		mapTiles.add(new MapTile(13, "/playUI/blackBlock.png", new Rectangle(363, 381, 120, 120), false));
		mapTiles.add(new MapTile(12, "/playUI/blueBlock.png", new Rectangle(274, 291, 150, 160), false));
		mapTiles.add(new MapTile(11, "/playUI/blackBlock.png", new Rectangle(363, 238, 120, 120), false));
		mapTiles.add(new MapTile(10, "/playUI/blackBlock.png", new Rectangle(436, 197, 120, 120), false));
		mapTiles.add(new MapTile(9, "/playUI/blackBlock.png", new Rectangle(513, 149, 120, 120), false));
		mapTiles.add(new MapTile(8, "/playUI/blueBlock.png", new Rectangle(596, 69, 150, 160), true));
		mapTiles.add(new MapTile(20, "/playUI/blackBlock.png", new Rectangle(396, 327, 120, 120), false));
	}
	
	/**
	 * �� Ÿ�� ����Ʈ�� ����Ǿ� �ִ� �÷���(Collection)�� �Ķ���ͷ� �޾Ƽ� Ÿ�� ����Ʈ�� �ʱ�ȭ �����ִ� Map Ŭ������ �������̴�.
	 * @param mapTiles �� Ÿ�� ����Ʈ�� ����Ǿ� �ִ� �÷��� (ArrayList<MapTile>)
	 */
	public Map(ArrayList<MapTile> mapTiles) {
		if (mapTiles == null) mapTiles = new ArrayList<MapTile>();
		this.mapTiles = mapTiles;
	}
	
	/**
	 * �� Ÿ�� ����Ʈ �÷����� �����ϰ� �ִ� mapTiles ���������� �������� ��ȯ�Ͽ� �ִ� �޼����̴�.
	 * @return ���� �����Ǿ� �ִ� �� Ÿ�� ����Ʈ �÷����� ������
	 */
	public ArrayList<MapTile> getMapTiles() {
		return mapTiles;
	}
	
	/**
	 * ���� �� Ÿ�� ����Ʈ �÷����� �����ϰ� �ִ� ������ ���� �Ķ���ͷ� ������ �� Ÿ�� ����Ʈ �÷����� �����ϵ��� �����ϴ� �޼����̴�.
	 * @param mapTiles ������ �� Ÿ�� ����Ʈ �÷��� (ArrayList<MapTile>)
	 */
	public void setMapTiles(ArrayList<MapTile> mapTiles) {
		this.mapTiles = mapTiles;
	}
	
	public ArrayList<ArrayList<Object>> getResource() {
		ArrayList<ArrayList<Object>> resources = new ArrayList<ArrayList<Object>>();
		int mapTilesSize = mapTiles.size();
		for (int i = 0; i < mapTilesSize; i++) {
			ArrayList<Object> resource = new ArrayList<Object>();
			resource.add(mapTiles.get(i).getTileNum());
			resource.add(mapTiles.get(i).getImage());
			resource.add(mapTiles.get(i).getRectangle());
			resources.add(resource);
		}
		return resources;
	}

	public ArrayList<Integer> calcDestination(int tileNum, ArrayList<Integer> quinqueStoneResultSet) {
	      ArrayList<Integer> resultList = new ArrayList<Integer>();
	      int sp = tileNum;
	      if (tileNum == -1) sp = 0;
	      for (int i = sp; i < table.length && table[i][0] <= tileNum; i++) {
	         if (table[i][0] == tileNum) {
	        	System.out.println("result Set size: " + quinqueStoneResultSet.size());
	        	System.out.println("input result dest");
	            for (int quinqueStoneResult : quinqueStoneResultSet) {
	               if (quinqueStoneResult == -1) 
	                  quinqueStoneResult = 0;
	               resultList.add(table[i][quinqueStoneResult + 1]);
	               System.out.println("result dest : " + table[i][quinqueStoneResult + 1]);
	            }
	         }
	      }
	      
	      return resultList;
	   }
		
	public ArrayList<Integer> getWay(int currentPos, int destPos) {
		ArrayList<Integer> way = new ArrayList<Integer>();
		int sp = currentPos;
		int[] wayRow = null;
		
		for (int i = 0; i < table.length; i++) {
			if (table[i][0] == currentPos) {
				sp = i;
				break;
			}
		}
		
		for (int i = sp; i < table.length && table[i][0] <= currentPos; i++) {
			for (int j = 1; j < table[i].length; j++) {
				if (table[i][j] == destPos) {
					if(j == 1) {
						way.add(destPos);
						return way;
					}
					wayRow = table[i];
					break;
				}
			}
			if (wayRow != null) break; 
		}
		
		if (wayRow[0] == destPos) {
			way.add(wayRow[0]);
		} else {
			for (int i = 2; i < wayRow.length; i++) {
				way.add(wayRow[i]);
				if (wayRow[i] == destPos)
					break;
			}
		}
		return way;
	}
}