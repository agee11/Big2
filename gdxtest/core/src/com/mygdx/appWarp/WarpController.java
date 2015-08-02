package com.mygdx.appWarp;

import java.util.HashMap;

import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;

public class WarpController {

	public static String apiKey = "b1a7a437f5ed115d468db4a9bcdea9d6ee483b08d977f5be12092cbc540f20e2";
	public static String secretKey = "5028f7c75438983b0958f06685a56b204463f75338b534c61f2f8fe403ddbaf0";
	
	WarpClient warpClient;
	public boolean isUDPEnabled = false;
	private boolean isConnected = false;
	
	private String roomId;
	
	private int STATE;
	private WarpListener warpListener;
	private String localUser;
	
	public static final int WAITING = 1;
	public static final int STARTED = 2;
	public static final int COMPLETED = 3;
	public static final int FINISHED = 4;

	public static final int GAME_WIN = 5;
	public static final int GAME_LOOSE = 6;
	public static final int ENEMY_LEFT = 7;
	
	public WarpController(){
		initAppwarp();
		warpClient.addConnectionRequestListener(new ConnectionListener(this));
		warpClient.addChatRequestListener(new ChatListener(this));
		warpClient.addZoneRequestListener(new ZoneListener(this));
		warpClient.addRoomRequestListener(new RoomListener(this));
		warpClient.addNotificationListener(new NotificationListener(this));
		
	}
	
	public void setListener(WarpListener listener){
		this.warpListener = listener;
	}
	
	private void initAppwarp(){
		try {
			WarpClient.initialize(apiKey, secretKey);
			warpClient = WarpClient.getInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onConnectDone(boolean b) {
		// TODO Auto-generated method stub
		if(b){
			warpClient.initUDP();
			warpClient.joinRoomInRange(1, 1, false);
		}else{
			isConnected  = false;
			handleError();
		}
	}

	public void onRoomCreated(String id) {
		// TODO Auto-generated method stub
		
	}
	
	private void handleError(){
		if(roomId != null && roomId.length() > 0){
			warpClient.deleteRoom(roomId);
		}
		disconnect();
	}
	
	private void disconnect(){
		
		
	}

	public void onGetLiveRoomInfo(String[] joinedUsers) {
		// TODO Auto-generated method stub
		if(joinedUsers != null){
			if(joinedUsers.length == 4){
				startGame();
			}else{
				waitForOtherUsers();
			}
		}else{
			warpClient.disconnect();
			handleError();
		}
	}
	
	private void startGame(){
		STATE = STARTED;
		warpListener.onGameStarted("Start the Game");
	}
	public void waitForOtherUsers(){
		STATE = WAITING;
		warpListener.onWaitingStarted("Waiting for more players");
	}

	public void onJoinRoomDone(RoomEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getResult() == WarpResponseResultCode.SUCCESS){
			this.roomId = arg0.getData().getId();
			warpClient.subscribeRoom(roomId);
		}else if(arg0.getResult() == WarpResponseResultCode.RESOURCE_NOT_FOUND){
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("result", "");
			warpClient.createRoom("superjumper", "shephertz", 4, data);
		}else{
			warpClient.disconnect();
			handleError();
		}
	}

	public void onRoomSubscribed(String id) {
		// TODO Auto-generated method stub
		if(id != null){
			isConnected = true;
			warpClient.getLiveRoomInfo(id);
		}else{
			warpClient.disconnect();
			handleError();
		}
	}

	public void onGameUpdateReceived(String string) {
		// TODO Auto-generated method stub
		String userName = string.substring(0, string.indexOf("#@"));
		String data = string.substring(string.indexOf("#@") + 2, string.length());
		if(!localUser.equals(userName)){
			warpListener.onGameUpdateReceived(data);
		}
	}

	public void onResultUpdateReceived(String arg1, int code) {
		// TODO Auto-generated method stub
		if(localUser.equals(arg1)==false){
			STATE = FINISHED;
			warpListener.onGameFinished(code, true);
		}else{
			warpListener.onGameFinished(code, false);
		}
	}

	public void onUserJoinedRoom(String id, String arg1) {
		// TODO Auto-generated method stub
		if(localUser.equals(arg1)==false){
			startGame();
		}
	}

	public void onUserLeftRoom(String id, String arg1) {
		// TODO Auto-generated method stub
		if(STATE==STARTED && !localUser.equals(arg1)){// Game Started and other user left the room
			warpListener.onGameFinished(ENEMY_LEFT, true);
		}
	}

	public void onSendChatDone(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
