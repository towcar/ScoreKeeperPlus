package com.carsonskjerdal.app.scorekeeperplus.BaseClasses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.carsonskjerdal.app.scorekeeperplus.AboutPage.AboutActivity;
import com.carsonskjerdal.app.scorekeeperplus.GamePage.GameActivity;
import com.carsonskjerdal.app.scorekeeperplus.MainPage.MainActivity;
import com.carsonskjerdal.app.scorekeeperplus.R;
import com.carsonskjerdal.app.scorekeeperplus.Tools.ToolsActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.Sharer.Result;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by Carson on 2018-01-26.
 * <p>
 * Feel free to use code just give credit please :)
 */


public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        //swap this to a switch statement

        if (id == R.id.nav_newgame) {
            // Start new game
            Intent intent = new Intent(this, MainActivity.class);

            //keeps player from returning to a screen before new game
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            //pass in players list
            startActivity(intent);
            finish();

            Log.e("Base", "new Game");
        } else if (id == R.id.nav_players) {
            Toast.makeText(this, "Still working to add this feature!",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_history) {
            Toast.makeText(this, "Still working to add this feature!",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_tools) {
            Intent intent = new Intent(this, ToolsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            //share post to facebook
            final Context context = this;

            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://www.carsonskjerdal.com"))
                    .build();
            shareDialog.show(linkContent);

            //call to see if post successful
            shareDialog.registerCallback(callbackManager, new FacebookCallback<Result>() {
                @Override
                public void onSuccess(Result result) {
                    // successful so call the share async task
                    Toast.makeText(context, "Share Successful!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel() {
                    Toast.makeText(context, "Share Cancelled!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException error) {
                    Toast.makeText(context, "Share Error!", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (id == R.id.nav_about)

        {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode,
                                    final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
/*
//todo: List

*/

/*Advanced Ideas
Design for rotation
Allow for more players, Lock positions so bottom buttons don't fall off. (might make rotation easier)
Finalize share to facebook
Is History really a good feature?
Look at saving players names (auto-suggestions)
Perhaps a quick loading screen or first time use screen
 */
