/*
 * Copyright (c) 2016. Chanse Games - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.chanse.games.sdk.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.chanse.games.sdk.common.ChanseConstants;
import com.chanse.games.sdk.common.TimeUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is the model representing games in Firebase Database.
 *
 * Created by chansek on 27/05/16.
 */

public class Game implements Parcelable {

    private String gameId;
    // Number of likesCount a game got
    private int likesCount;
    // Number of friends the owner challenged
    private int challengesCount;

    // User details, who won this game
    // user id
    private String playerId;
    // user display name
    private String playerName;
    // user profile Picture (if exists)
    private String playerPic;

    // All like details should be stored here
    private Map<String, Boolean> likes = new HashMap<>();

    // All challenge details should be stored here
    private Map<String, Boolean> challenges = new HashMap<>();

    // All dynamic game attributes should be stored here. These attributes will shown in the content of feed
    private Map<String, Object> properties = new LinkedHashMap<>();

    // Any extra attributes user wants to store which need not be shown in the feed content should be here
    private Map<String, Object> extra = new HashMap<>();

    // Mandatory game attribute
    private long score;

    // Application details
    private String packageName;
    private String intentAction;

    // Default public constructor mandatory for firebase
    public Game() {}

    // Constructor used for converting HashMap to Game object
    @SuppressWarnings("unchecked")
    public Game(Map<String, Object> gameMap) {
        gameId = String.valueOf(gameMap.get(ChanseConstants.GAME_ID));
        likesCount = (int) (long) gameMap.get(ChanseConstants.LIKES_COUNT);
        challengesCount = (int) (long) gameMap.get(ChanseConstants.CHALLENGES_COUNT);
        packageName = String.valueOf(gameMap.get(ChanseConstants.APP_PACKAGE));
        intentAction = String.valueOf(gameMap.get(ChanseConstants.INTENT_ACTION));

        if (gameMap.containsKey(ChanseConstants.SCORE)) {
            score = (long) gameMap.get(ChanseConstants.SCORE);
        }
        if (gameMap.containsKey(ChanseConstants.LIKES)) {
            likes = (Map<String, Boolean>) gameMap.get(ChanseConstants.LIKES);
        }
        if (gameMap.containsKey(ChanseConstants.CHALLENGES)) {
            challenges = (Map<String, Boolean>) gameMap.get(ChanseConstants.CHALLENGES);
        }
        if (gameMap.containsKey(ChanseConstants.EXTRA)) {
            extra = (Map<String, Object>) gameMap.get(ChanseConstants.EXTRA);
        }
        if (gameMap.containsKey(ChanseConstants.PROPERTIES)) {
            properties = (Map<String, Object>) gameMap.get(ChanseConstants.PROPERTIES);
        } else {
            // Properties key is not there in the game map, this means the object represents the older version of Game,
            // So put the required fields manually to properties
            properties.put("Moves", gameMap.get(ChanseConstants.MOVES));
            properties.put("Time Taken", TimeUtil.formatElapsedTime((long) gameMap.get(ChanseConstants.ELAPSED_TIME)));
            properties.put("Pauses", gameMap.get(ChanseConstants.PAUSES));
        }
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getChallengesCount() {
        return challengesCount;
    }

    public void setChallengesCount(int challengesCount) {
        this.challengesCount = challengesCount;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerPic() {
        return playerPic;
    }

    public void setPlayerPic(String playerPic) {
        this.playerPic = playerPic;
    }

    public Map<String, Boolean> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, Boolean> likes) {
        this.likes = likes;
    }

    public Map<String, Boolean> getChallenges() {
        return challenges;
    }

    public void setChallenges(Map<String, Boolean> challenges) {
        this.challenges = challenges;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIntentAction() {
        return intentAction;
    }

    public void setIntentAction(String intentAction) {
        this.intentAction = intentAction;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Map<String, Object> toMap(boolean isForFeed) {
        HashMap<String, Object> result = new HashMap<>();
        result.put(ChanseConstants.GAME_ID, gameId);
        result.put(ChanseConstants.APP_PACKAGE, packageName);
        result.put(ChanseConstants.INTENT_ACTION, intentAction);
        result.put(ChanseConstants.LIKES_COUNT, likesCount);
        result.put(ChanseConstants.CHALLENGES_COUNT, challengesCount);
        result.put(ChanseConstants.LIKES, likes);
        result.put(ChanseConstants.CHALLENGES, challenges);
        result.put(ChanseConstants.SCORE, score);
        result.put(ChanseConstants.PROPERTIES, properties);
        result.put(ChanseConstants.EXTRA, extra);
        // If it is for feed, then we don't need player details to be inserted,
        // since these details will already be inserted with feed, in root attribute
        if (!isForFeed) {
            result.put(ChanseConstants.PLAYER_PIC, playerPic);
            result.put(ChanseConstants.PLAYER_ID, playerId);
            result.put(ChanseConstants.PLAYER_NAME, playerName);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (likesCount != game.likesCount) return false;
        if (challengesCount != game.challengesCount) return false;
        if (gameId != null ? !gameId.equals(game.gameId) : game.gameId != null) return false;
        if (playerId != null ? !playerId.equals(game.playerId) : game.playerId != null)
            return false;
        if (playerName != null ? !playerName.equals(game.playerName) : game.playerName != null)
            return false;
        if (playerPic != null ? !playerPic.equals(game.playerPic) : game.playerPic != null)
            return false;
        if (likes != null ? !likes.equals(game.likes) : game.likes != null) return false;
        return challenges != null ? challenges.equals(game.challenges) : game.challenges == null;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", likesCount=" + likesCount +
                ", challengesCount=" + challengesCount +
                ", playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gameId);
        dest.writeInt(this.likesCount);
        dest.writeInt(this.challengesCount);
        dest.writeString(this.playerId);
        dest.writeString(this.playerName);
        dest.writeString(this.playerPic);
        dest.writeInt(this.likes.size());
        for (Map.Entry<String, Boolean> entry : this.likes.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
        dest.writeInt(this.challenges.size());
        for (Map.Entry<String, Boolean> entry : this.challenges.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
        dest.writeInt(this.properties.size());
        for (Map.Entry<String, Object> entry : this.properties.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
        dest.writeString(this.packageName);
        dest.writeInt(this.extra.size());
        for (Map.Entry<String, Object> entry : this.extra.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
        dest.writeString(this.intentAction);
        dest.writeLong(this.score);
    }

    protected Game(Parcel in) {
        this.gameId = in.readString();
        this.likesCount = in.readInt();
        this.challengesCount = in.readInt();
        this.playerId = in.readString();
        this.playerName = in.readString();
        this.playerPic = in.readString();
        int likesSize = in.readInt();
        this.likes = new HashMap<>(likesSize);
        for (int i = 0; i < likesSize; i++) {
            String key = in.readString();
            Boolean value = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.likes.put(key, value);
        }
        int challengesSize = in.readInt();
        this.challenges = new HashMap<>(challengesSize);
        for (int i = 0; i < challengesSize; i++) {
            String key = in.readString();
            Boolean value = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.challenges.put(key, value);
        }
        int propertiesSize = in.readInt();
        this.properties = new HashMap<>(propertiesSize);
        for (int i = 0; i < propertiesSize; i++) {
            String key = in.readString();
            Object value = in.readValue(Object.class.getClassLoader());
            this.properties.put(key, value);
        }
        this.packageName = in.readString();
        int extraSize = in.readInt();
        this.extra = new HashMap<>(extraSize);
        for (int i = 0; i < extraSize; i++) {
            String key = in.readString();
            Object value = in.readValue(Object.class.getClassLoader());
            this.extra.put(key, value);
        }
        this.intentAction = in.readString();
        score = in.readLong();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}