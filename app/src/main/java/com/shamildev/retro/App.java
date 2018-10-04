package com.shamildev.retro;

import android.app.Activity;
import android.app.Application;
import android.util.Log;


import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.shamildev.retro.data.net.NetworkManager;
import com.shamildev.retro.domain.core.AppConfig;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by Shamil Lazar.
 */


public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Inject
    NetworkManager networkManager;

    @Inject
    AppConfig appConfig;

    @Override
    public void onCreate() {
        super.onCreate();



       // AppEventsLogger.activateApp(this);
        initialiseLogger();
        initCalligraphy();

        DaggerAppComponent.builder().create(this).inject(this);


       // boolean debug = BuildConfig.MOVIE_DB_API_TOKEN;
        networkManager.start();
        initialiseTwitter();
        initialiseFacebook();


    }

    private void initCalligraphy() {

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/ClanPro-Book.ttf")
                        .setFontAttrId(R.attr.fontPath)
                       // .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
                       // .addCustomStyle(TextField.class, R.attr.textFieldStyle)
                        .build());
    }



    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }


    private void initialiseLogger() {

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element)+" Methodname: "+element.getMethodName()+" | Line:"+element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }

    private void initialiseTwitter(){
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_API_TOKEN, BuildConfig.TWITTER_API_SECRET_TOKEN))
                .debug(BuildConfig.DEBUG)
                .build();
        Twitter.initialize(config);
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if(session!=null){
            TwitterAuthToken authToken = session.getAuthToken();
            if(!authToken.token.equals("") && !authToken.secret.equals("")){
                appConfig.setTwitterToken(authToken.token,authToken.secret);
            }else{
                appConfig.setTwitterToken(null,null);
            }
            Log.e("APP", "TW->toke: "+ appConfig.getTwitterToken().key+" secret: "+appConfig.getTwitterToken().value);
        }




    }
    private void initialiseFacebook() {

if(AccessToken.getCurrentAccessToken()!=null){
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    if (accessToken != null && !accessToken.isExpired()) {
        appConfig.setFacebookToken(accessToken.getToken());
    }else{
        appConfig.setFacebookToken(null);
    }

    Log.e("APP", "FB->token: "+ appConfig.getFacebookToken());
}

    }

}
