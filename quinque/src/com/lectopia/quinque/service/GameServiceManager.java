package com.lectopia.quinque.service;

import java.util.ArrayList;

import com.lectopia.quinque.view.bean.Map;
import com.lectopia.quinque.view.bean.Marker;
import com.lectopia.quinque.view.bean.MarkerList;
import com.lectopia.quinque.view.bean.Player;
import com.lectopia.quinque.view.bean.QuinqueStone;

public class GameServiceManager {
	private static GameServiceManager instance;
	private Map map;
	private Player[] player;
	private QuinqueStone quinqueStone;
	private MarkerList markerList; 
	private int currentTurn;
	
	static {
		instance = new GameServiceManager(); 
	}
	
	private GameServiceManager() {
		map = new Map();
		player = new Player[4];
		player[0] = new Player(1, "player1");
		player[1] = new Player(2, "player2");
		
		quinqueStone = new QuinqueStone();
		markerList = new MarkerList();
		currentTurn = 1;
	}
	
	static public GameServiceManager getInstance() {
		return instance;
	}
	
	public int rollStone(int cp) {
		int quinqueStoneResult = quinqueStone.roll(cp);
		player[currentTurn - 1].addQuinqueStoneResultSet(quinqueStoneResult);
		
		boolean checkFlag = false;
		
		if (player[currentTurn - 1].getQuinqueStoneResultSet().size() == 1 && quinqueStoneResult == -1) {
			for (ArrayList<Marker> markers : markerList.getMarkerList()) {
				for (Marker marker : markers) {
					if (marker.getTurn() == currentTurn && marker.getMapTileNum() != -1)
						if (marker.getGoalFlag() == false)
							checkFlag =  true;
				}
			}
			if (checkFlag == false) {
				player[currentTurn - 1].getQuinqueStoneResultSet().remove(0);
				switch(currentTurn) {
					case 1 : currentTurn = 2; break;
					case 2 : currentTurn = 1; break;
					default : ;
				}
			}
		}
		return quinqueStoneResult;
	}
	
	public ArrayList<Integer> getQuinqueStoneResultSet() {
		return player[currentTurn - 1].getQuinqueStoneResultSet();
	}
	
	public String[][] moveMarker(int selectedTileNum) throws IndexOutOfBoundsException{
		ArrayList<Marker> selectedMarker = markerList.getSelectedMarkers(); // ���� ���õ� �� ��������
		System.out.println("selectedMarkerSize = " + selectedMarker.size());
		ArrayList<Integer> way = null;
		way = map.getWay(selectedMarker.get(0).getMapTileNum(), selectedTileNum); // ������������ ���
		String[][] result = new String[way.size() + 3][3];
		System.out.print("way: ");
		
		for (int num : way)
			System.out.print(num +" ");
		
		boolean stopFlag = false; // ���ų� ���� ���߰�
		boolean winFlag = false;   // �� �� �̶� �̱�� ��Ű���� �� �� �� ������
		
		int i; // ��ο��� �� ��° ��ġ����
		
		for (i = 0; i < way.size() - 1; i++) {
			boolean battleFlag = false;
			ArrayList<Marker> hiddenMarker = markerList.getHiddenMarker(way.get(i)); // Ÿ�� ���� �ִ� hidden�� ��������
			if (hiddenMarker.size() > 0) {
				if (hiddenMarker.get(0).getTurn() != currentTurn) {   // ���� �ϰ� �ٸ��� �ο���
					battleFlag = true;
				}
			}
			
			if (!battleFlag) {   // ����ǥ�� ���� ������ �׳� �̵�
				markerList.moveMarker(selectedMarker, way.get(i));
				result[i][0] = way.get(i).toString();
				result[i][1] = "move";
			}
			else {   // ���� �����ϱ� �ο���
				for(int j = 0; j < selectedMarker.size(); j++)
				{
					selectedMarker.get(j).setHiddenFlag(false);
				}
				for(int j = 0; j < hiddenMarker.size(); j++) {
					hiddenMarker.get(j).setHiddenFlag(false);
				}
				
				switch(markerList.battle(way.get(i))) {  // �� ��ġ���� �ο� �ǵ�
					case -1 : // ���� ��
						result[i][0] = way.get(i).toString();
						result[i][1] = "lose";
						result[i][2] = hiddenMarker.get(0).getName(); 
						markerList.moveMarker(selectedMarker, -1);
						markerList.setMarkersHiddenFlag(selectedMarker);
						markerList.breakUp(selectedMarker);
						stopFlag = true;
						break;
					case 0 : // ���º�
						result[i][0] = way.get(i).toString();
						result[i][1] = "draw";
						result[i][2] = hiddenMarker.get(0).getName();
						markerList.moveMarker(selectedMarker, way.get(i));
						stopFlag = true;
						break;
					case 1 : // �̰��� ��
						result[i][0] = way.get(i).toString();
						result[i][1] = "win";
						result[i][2] = hiddenMarker.get(0).getName();
						markerList.moveMarker(hiddenMarker, -1);
						markerList.setMarkersHiddenFlag(hiddenMarker);
						markerList.moveMarker(selectedMarker, way.get(i));
						markerList.breakUp(hiddenMarker);
						winFlag = true;
						break;
					}
			}
			if(stopFlag)   // ���ų� ������� �� �̻� ���� ���ϰ� ��
				break;
		}
		// ���ų� ������� �߰��� �����ϰ� ����
		if(stopFlag) {
			markerList.deselectedAllMarkers();   // �̵� ������ ������ �� ����
			
			int removeIndex;
			for (removeIndex = 0; removeIndex < player[currentTurn - 1].getQuinqueStoneResultSet().size(); removeIndex++) {
				if (Math.abs(player[currentTurn - 1].getQuinqueStoneResultSet().get(removeIndex)) == way.size()) 
					break;
			}
			player[currentTurn - 1].getQuinqueStoneResultSet().remove(removeIndex);   // �ѹ� �̵� ������ �̵��� ��� ����Ʈ �¿��� ����
			System.out.println("removeIndex: " + removeIndex + ", result: " + player[currentTurn - 1].getQuinqueStoneResultSet().size() + ", way size : " + way.size());
			return result;
		}
		
		// ���ų� ����� ������ ������ �̵�
		
		// ��Ŀ�� ������� �����
		//0528
		//�¸� �߰�
		//20:01
		if (selectedTileNum == 26) {
			for (int in = 0; selectedMarker.size() > in; in++) {
				if(selectedMarker.get(0).getCane().getCane().equals("Questionmark"))
					break;
				selectedMarker.get(in).setGoalFlag(true);
			}
			int cnt = 0;
			if(!(selectedMarker.get(0).getCane().getCane().equals("Questionmark")))
			{
				System.out.println("���ϵ�ī�� ����");
				for (int x = 0; x < markerList.getMarkerList().size(); x++) {
					if (markerList.getMarkerList().get(x).get(0).getGoalFlag()) {
						if (markerList.getMarkerList().get(x).get(0).getTurn() == currentTurn)
						cnt++;
					}
				}
			}
			System.out.println("cnt : "+ cnt);
			if (cnt >= 2) {
				result[i + 3][0] = "26";
				result[i + 3][1] = "endgame";
				return result;
			}
			result[i + 3][0] = "26";
			result[i + 3][1] = "finish";

			markerList.deselectedAllMarkers(); // �̵� ������ ������ �� ����

			int removeIndex;
			boolean checkFlag = false;
			
			for (removeIndex = 0; removeIndex < player[currentTurn - 1].getQuinqueStoneResultSet().size(); removeIndex++) {
				if (Math.abs(player[currentTurn - 1].getQuinqueStoneResultSet().get(removeIndex)) == way.size()) {
					checkFlag = true;
					break;
				}
			}
			
			if (checkFlag) {
				player[currentTurn - 1].getQuinqueStoneResultSet().remove(removeIndex); // �ѹ� �̵� ������ �̵��� ��� ����Ʈ �¿��� ����
			}
			else {
				player[currentTurn - 1].getQuinqueStoneResultSet().remove(0);
			}
			
			System.out.println("removeIndex: " + removeIndex + ", result: " + player[currentTurn - 1].getQuinqueStoneResultSet().size() + ", way size : " + way.size());		
			return result;
		}
		
		ArrayList<Marker> enemy = markerList.searchEnemyMarker(selectedTileNum);
		ArrayList<Marker> friend = markerList.searchFriendMarker(selectedTileNum);
		
		if(enemy.size() != 0) {   // �������� ���� ���� ��
			for(int j=0; j<selectedMarker.size(); j++)
			{
				selectedMarker.get(j).setHiddenFlag(false);
			}
			for(int j = 0; j < enemy.size(); j++) {
				enemy.get(j).setHiddenFlag(false);
			}
			markerList.battle(way.get(i));
			switch(markerList.battle(way.get(i))) {
				case -1 : // ���� ��
					result[i][0] = way.get(i).toString();
					result[i][1] = "lose";
					result[i][2] = enemy.get(0).getName();
					markerList.moveMarker(selectedMarker, -1);
					markerList.setMarkersHiddenFlag(selectedMarker);
					markerList.breakUp(selectedMarker);
					break;
				case 0 : // ���º�
					result[i][0] = way.get(i).toString();
					result[i][1] = "draw";
					result[i][2] = enemy.get(0).getName();
					markerList.moveMarker(selectedMarker, way.get(i));
					break;
				
				case 1 : // �̰��� ��
					result[i][0] = way.get(i).toString();
					result[i][1] = "win";
					result[i][2] = enemy.get(0).getName();
					markerList.moveMarker(enemy, - 1);
					markerList.setMarkersHiddenFlag(enemy);
					markerList.moveMarker(selectedMarker, way.get(i));
					markerList.breakUp(enemy);
					winFlag = true;
					break;
			}
		}
//		else if(friend.size() != 0) { // ���� ���� ������ ��ġ��
//			result[i + 1][0] = way.get(i).toString();
//			result[i + 1][1] = "merge";
//			result[i + 1][2] = friend.get(0).getName();
//			markerList.moveMarker(selectedMarker, way.get(i));
//			markerList.merge(way.get(i));
//		}
		else if(enemy.size() == 0) {// �ο����� �ʾҰ� ��ĥ �͵� ������ �׳� �̵�
			result[i + 1][0] = way.get(i).toString();
			result[i + 1][1] = "move";
			markerList.moveMarker(selectedMarker, way.get(i));
		}
		if(winFlag) {// �������� �� �� �� �̶� �¸��� ���� ������ �� �� �� ���� ��ȸ ����
			result[i + 2][0] = way.get(i).toString(); result[i + 2][1] = "chance";
		}
		markerList.deselectedAllMarkers();   // �̵� ������ ������ �� ����
		
		int removeIndex;
		for (removeIndex = 0; removeIndex < player[currentTurn - 1].getQuinqueStoneResultSet().size(); removeIndex++) {
			if (Math.abs(player[currentTurn - 1].getQuinqueStoneResultSet().get(removeIndex)) == way.size())
				break;
		}
		
		
		
		System.out.println("removeIndex: " + removeIndex + ", result: " + player[currentTurn - 1].getQuinqueStoneResultSet().size() + ", way size : " + way.size());
		player[currentTurn - 1].getQuinqueStoneResultSet().remove(removeIndex); // �ѹ� �̵� ������ �̵��� ��� ����Ʈ �¿��� ����

		return result;
	}
	
	public String changeTurn() {
		String nickname = player[currentTurn - 1].getNickname();
		if (player[currentTurn - 1].getQuinqueStoneResultSet().size() == 0) {
			switch(currentTurn) {
				case 1 : 
					currentTurn = 2;
					nickname = player[1].getNickname();
					break;
				case 2 :
					currentTurn = 1;
					nickname = player[0].getNickname();
			}
		}
		return nickname;
	}
	
	public int checkLeftAction() {
		return 0;
	}
	
	public void resetGame() {
		player[0].setTurn(1);
		player[0].setNickname("player1");
		player[1].setTurn(2);
		player[1].setNickname("player2");
		currentTurn = 1;
		markerList.setMarkerList("empty","empty");
		markerList.resetMapTileNum();
	}
	
	public void setPlayerMarker(String name1, String name2) {
		player[0].setSelectedMarker(name1);
		player[1].setSelectedMarker(name2);
		
		markerList.setMarkerList(name1, name2);
	}
	
	public ArrayList<ArrayList<Object>> getCompSource() {
		return null;
	}
	
	public ArrayList<ArrayList<Object>> getMarkerResource() {
		return markerList.getResource();
	}
	
	public ArrayList<ArrayList<Object>> getMapResource() {
		return map.getResource();
	}
	
	public void selectMarker(String name) {
		markerList.setSelectedMarkers(name);
	}
	
	public ArrayList<Integer> searchDestination(String name) {
		ArrayList<Marker> searchMarkers =  markerList.searchMarkers(name);
		ArrayList<Integer> quinqueStoneResultSet = player[currentTurn - 1].getQuinqueStoneResultSet();
		System.out.println("currentPos: " + searchMarkers.get(0).getMapTileNum());
		ArrayList<Integer> destinationList = map.calcDestination(searchMarkers.get(0).getMapTileNum(), quinqueStoneResultSet);
		return destinationList;
	}
	
	public int characterTurn(String character) {
		int res = 0;
		for (int i = 0; i < player.length && player[i] != null; i++) {
			if (character.contains(player[i].getSelectedMarker())) {
				res = i;
			}
		}
		return res + 1;
	}
	
	public void deselectedMarker(String name) {
		markerList.deselectedMarker(name);
	}
	
	public int getCurrentTurn() {
		return currentTurn;
	}
	
	public String getChangeImageURL(String name) {
		System.out.println(name);
		ArrayList<Marker> markers = markerList.searchMarkers(name);
		if (markers.get(0).getHiddenFlag()) {
				return markers.get(0).getImage()[0];
			}
			else
				return markers.get(0).getImage()[1];
	}
	
	public void changePartyCane(String teamName, String selectCaneName) {
		markerList.changePartyCane(teamName, selectCaneName);
	}
	
	public ArrayList<String> getPartyMemberCanes(String name){   //�� ��Ƽ�� cane���� �������� �޼���
		
		ArrayList<String> partyMemberCanes= new ArrayList<String>();
		ArrayList<Marker> markers=markerList.searchMarkers(name);
		
		for(int i=0; i<markers.size(); i++) {   //�ش��ϴ� ��Ŀ(�̸�)�� ������ �ִ� ��Ŀ ��Ƽ�� �����Ͽ� ��� ��Ƽ���� Cane�� ������ �Է¹��� 
			partyMemberCanes.add(markers.get(i).getCane().getCane());
		}
		
		return partyMemberCanes;
	}

}