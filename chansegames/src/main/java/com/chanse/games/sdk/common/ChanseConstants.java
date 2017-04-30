/*
 * Copyright (c) 2016. Chanse Games - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.chanse.games.sdk.common;

/**
 * All constants related to Firebase database should be present here.
 *
 * Created by chansek on 24/05/16.
 */

public final class ChanseConstants {
    public static final int PURPOSE_GAME = 1;                       // 2 ^ 0 = 1
    public static final int PURPOSE_CHALLENGE = 2;                  // 2 ^ 1 = 2
    public static final int PURPOSE_GAME_START = 4;                 // 2 ^ 2 = 4

    public static final String GAME_ID = "gameId";
    public static final String ELAPSED_TIME = "elapsedTime";
    public static final String LIKES_COUNT = "likesCount";
    public static final String LIKES = "likes";
    public static final String CHALLENGES_COUNT = "challengesCount";
    public static final String CHALLENGES = "challenges";
    public static final String EXTRA = "extra";
    public static final String PROPERTIES = "properties";
    public static final String SCORE = "score";
    public static final String POINTS = "points";
    public static final String MOVES = "moves";
    public static final String PAUSES = "pauses";
    /**
     * Represents the action name of intent-filter used for a particular game
     */
    public static final String INTENT_ACTION = "intentAction";
    public static final String APP_PACKAGE = "packageName";

    public static final String PLAYER_ID = "playerId";
    public static final String PLAYER_NAME = "playerName";
    public static final String PLAYER_PIC = "playerPic";

    public static final String CHALLENGE = "challenge";

    public static final String KEY_EXTRA = "extra";
    // This tells the purpose of the service which syncs with server
    public static final String KEY_EXTRA_PURPOSE = "purpose";
    public static final String KEY_EXTRA_GAME = "game";
    public static final String KEY_EXTRA_FEED_ID = "feedId";
    public static final String KEY_EXTRA_CHALLENGE_ID = "challengeId";
    public static final String KEY_EXTRA_CHANSE_POINTS = "chansePoints";
    public static final String KEY_EXTRA_CALLER_APP_PACKAGE = "caller_app_package";
}