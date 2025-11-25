import java.util.ArrayList;

public class GameMap {
	ArrayList<Integer[][]> gameMaps = new ArrayList<>();
	private int tileSize;
	int mapIndex;
	private int screenWidth;
	private int screenHeight;

	public GameMap(int mapIndex, int screenWidth, int screenHeight) {
		this.mapIndex = mapIndex;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;

		initializeMaps();

		int tileSizeByWidth = screenWidth / 16;
		int tileSizeByHeight = screenHeight / 13;
		this.tileSize = Math.min(tileSizeByWidth, tileSizeByHeight);
	}

	private void initializeMaps() {
		// Map 1 - Arena Centrale
		Integer[][] map1 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		// Map 2 - Corridoi Stretti
		Integer[][] map2 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 5, 0 }, { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		// Map 3 - Stanze Multiple
		Integer[][] map3 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 5, 0, 0, 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		// Map 4 - Campo Aperto con Ostacoli
		Integer[][] map4 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		// Map 5 - Labirinto
		Integer[][] map5 = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 }, { 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 },
				{ 1, 1, 1, 0, 0, 0, 1, 0, 5, 1, 0, 0, 0, 1, 1, 1 }, { 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

		gameMaps.add(map1);
		gameMaps.add(map2);
		gameMaps.add(map3);
		gameMaps.add(map4);
		gameMaps.add(map5);
	}

	public boolean isSolid(int mapIndex, int x, int y) {
		if (mapIndex < 0 || mapIndex >= gameMaps.size()) {
			return true;
		}

		Integer[][] currentMap = gameMaps.get(mapIndex);
		int col = x / tileSize;
		int row = y / tileSize;

		if (row < 0 || row >= currentMap.length || col < 0 || col >= currentMap[0].length) {
			return true;
		}

		int tileType = currentMap[row][col];
		return tileType == 1 || tileType == 3;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getMapWidth(int mapIndex) {
		if (mapIndex < 0 || mapIndex >= gameMaps.size()) {
			return 0;
		}
		return gameMaps.get(mapIndex)[0].length * tileSize;
	}

	public int getMapHeight(int mapIndex) {
		if (mapIndex < 0 || mapIndex >= gameMaps.size()) {
			return 0;
		}
		return gameMaps.get(mapIndex).length * tileSize;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int[] getPlayerStartPosition() {
		if (mapIndex < 0 || mapIndex >= gameMaps.size()) {
			return null;
		}

		Integer[][] currentMap = gameMaps.get(mapIndex);
		
		int mapWidth = currentMap[0].length * tileSize;
		int mapHeight = currentMap.length * tileSize;
		int offsetX = (screenWidth - mapWidth) / 2;
		int offsetY = (screenHeight - mapHeight) / 2;

		for (int row = 0; row < currentMap.length; row++) {
			for (int col = 0; col < currentMap[row].length; col++) {
				if (currentMap[row][col] == 4) {
					int x = col * tileSize + tileSize/2 + offsetX;
					int y = row * tileSize + tileSize/2 + offsetY;
					return new int[] { x, y };
				}
			}
		}

		return null;
	}

	public int[] getEnemyStartPosition() {
		if (mapIndex < 0 || mapIndex >= gameMaps.size()) {
			return null;
		}

		Integer[][] currentMap = gameMaps.get(mapIndex);
		
		int mapWidth = currentMap[0].length * tileSize;
		int mapHeight = currentMap.length * tileSize;
		int offsetX = (screenWidth - mapWidth) / 2;
		int offsetY = (screenHeight - mapHeight) / 2;

		for (int row = 0; row < currentMap.length; row++) {
			for (int col = 0; col < currentMap[row].length; col++) {
				if (currentMap[row][col] == 5) {
					int x = col * tileSize + tileSize/2 + offsetX;
					int y = row * tileSize + tileSize/2 + offsetY;
					return new int[] { x, y };
				}
			}
		}

		return null;
	}
	
	public static boolean player1Spawned = false;
	public static boolean player2Spawned = false;

	public int[] getStartingPosition() {
	    if (mapIndex < 0 || mapIndex >= gameMaps.size()) {
	        return null;
	    }

	    Integer[][] currentMap = gameMaps.get(mapIndex);
	    
	    int mapWidth = currentMap[0].length * tileSize;
	    int mapHeight = currentMap.length * tileSize;
	    int offsetX = (screenWidth - mapWidth) / 2;
	    int offsetY = (screenHeight - mapHeight) / 2;
	    if (!player1Spawned) {
	        for (int row = 0; row < currentMap.length; row++) {
	            for (int col = 0; col < currentMap[row].length; col++) {
	                if (currentMap[row][col] == 4) {
	                    player1Spawned = true;
	                    int x = col * tileSize + tileSize/2 + offsetX;
	                    int y = row * tileSize + tileSize/2 + offsetY;
	                    return new int[] { x, y };
	                }
	            }
	        }
	    }
	    if (!player2Spawned) {
	        for (int row = 0; row < currentMap.length; row++) {
	            for (int col = 0; col < currentMap[row].length; col++) {
	                if (currentMap[row][col] == 5) {
	                    player2Spawned = true;
	                    int x = col * tileSize + tileSize/2 + offsetX;
	                    int y = row * tileSize + tileSize/2 + offsetY;
	                    return new int[] { x, y };
	                }
	            }
	        }
	    }
	    
	    return null;
	}

	public void resetSpawnPositions() {
	    player1Spawned = false;
	    player2Spawned = false;
	}
	
	
}