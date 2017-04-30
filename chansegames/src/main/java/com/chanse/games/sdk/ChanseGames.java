package com.chanse.games.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chanse.games.sdk.common.ChanseConstants;
import com.chanse.games.sdk.model.Game;

/**
 * This class manages all the games completed or played in 3rd party game apps.
 *
 * Created by incnayak on 3/26/2017.
 */

public final class ChanseGames {

    /**
     * Should be called by each app when a new game is started. If the game is resumed,
     * then this should not be invoked by the user.
     */
    public static void onGameStart(Context context) {
        Intent intent = new Intent();
        intent.putExtra(ChanseConstants.KEY_EXTRA_PURPOSE, ChanseConstants.PURPOSE_GAME_START);
        intent.putExtra(ChanseConstants.KEY_EXTRA_CALLER_APP_PACKAGE, context.getPackageName());
        ComponentName component = new ComponentName(BuildConfig.HOST_APP_PACKAGE,
                BuildConfig.HOST_APP_SERVICE);
        intent.setComponent(component);
        context.startService(intent);
    }

    /**
     * Should be called by the user when a game got over. This method will take care
     * of uploading the game into server and sync with other apps.
     * @param context Android Context object
     * @param game Game object representing the game which got over
     */
    public static void onGameOver(Context context, Game game) {
        Intent intent = new Intent();
        intent.putExtra(ChanseConstants.KEY_EXTRA_PURPOSE, ChanseConstants.PURPOSE_GAME);
        intent.putExtra(ChanseConstants.KEY_EXTRA_GAME, game);
        intent.putExtra(ChanseConstants.KEY_EXTRA_CALLER_APP_PACKAGE, context.getPackageName());
        ComponentName component = new ComponentName(BuildConfig.HOST_APP_PACKAGE,
                BuildConfig.HOST_APP_SERVICE);
        intent.setComponent(component);
        context.startService(intent);
    }

    /**
     * Should be called by the user when a challenge got over. This method will take care
     * of uploading the game into server and sync with other apps.
     *
     * @param context Android Context
     * @param game Game object representing the game which got over
     * @param extra Should be the same bundle which got passed to you while starting your game.
     *              You can get this using {@code getIntent().getBundleExtra(ChanseConstants.KEY_EXTRA);}
     */
    public static void onChallengeOver(Context context, Game game, Bundle extra) {
        Intent intent = new Intent();
        intent.putExtra(ChanseConstants.KEY_EXTRA_PURPOSE, ChanseConstants.PURPOSE_CHALLENGE);
        // The game which got finished now
        intent.putExtra(ChanseConstants.KEY_EXTRA_GAME, game);
        // This bundle has another game with same KEY_EXTRA_GAME, but that represents the game
        // which got challenged to
        intent.putExtra(ChanseConstants.KEY_EXTRA, extra);
        intent.putExtra(ChanseConstants.KEY_EXTRA_CALLER_APP_PACKAGE, context.getPackageName());
        ComponentName component = new ComponentName(BuildConfig.HOST_APP_PACKAGE,
                BuildConfig.HOST_APP_SERVICE);
        intent.setComponent(component);
        context.startService(intent);
    }
}
