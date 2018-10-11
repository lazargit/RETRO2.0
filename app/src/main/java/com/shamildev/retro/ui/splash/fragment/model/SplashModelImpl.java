package com.shamildev.retro.ui.splash.fragment.model;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bumptech.glide.load.engine.GlideException;
import com.facebook.AccessToken;
import com.shamildev.retro.di.scope.PerFragment;
import com.shamildev.retro.domain.core.AppConfig;
import com.shamildev.retro.domain.core.DataConfig;
import com.shamildev.retro.domain.core.usecase.UseCaseHandler;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_LogoutUser;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_TestFirestore;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_TestFirestoreRead;
import com.shamildev.retro.domain.interactor.usecases.base.USECASE_authUser;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetGenre;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetNowPlayingMovies;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetNowPlayingTVShows;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetPopularPerson;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetTMDBConfiguration;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetTopRatedMovies;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_GetUpcomingMovies;
import com.shamildev.retro.domain.interactor.usecases.tmdb.USECASE_InitTables;
import com.shamildev.retro.domain.interactor.usecases.user.USECASE_GetUser;
import com.shamildev.retro.domain.interactor.usecases.user.USECASE_SaveUser;
import com.shamildev.retro.domain.models.AppUser;
import com.shamildev.retro.domain.models.Configuration;
import com.shamildev.retro.domain.models.Genre;
import com.shamildev.retro.domain.models.ResultWrapper;
import com.shamildev.retro.domain.models.User;
import com.shamildev.retro.retroimage.bitmap.BitmapConverter;
import com.shamildev.retro.retroimage.core.RetroImage;
import com.shamildev.retro.retroimage.core.RetroImageRequestListener;
import com.shamildev.retro.ui.splash.fragment.presenter.SplashPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by Shamil Lazar.
 *             //https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif
 //            ArrayList<String> gifUrl = new ArrayList<>();
 //            gifUrl.add("https://i.gifer.com/2Gr0.gif");
 //            gifUrl.add("https://media.giphy.com/media/87kcVw4PjxGr6/giphy.gif");
 //            gifUrl.add("https://media.giphy.com/media/lR8eXSeYUF5vO/giphy.gif");
 */
@PerFragment
public class SplashModelImpl extends SplashModel{


    private final USECASE_authUser usecase_authUser;
    private final USECASE_InitTables usecase_initTables;
    private final USECASE_GetTMDBConfiguration usecase_getTMDBConfiguration;
    private final USECASE_GetGenre usecase_getGenre;
    private final USECASE_GetNowPlayingMovies usecase_getNowPlayingMovies;
    private final USECASE_GetNowPlayingTVShows usecase_getNowPlayingTVShows;
    private final USECASE_GetUpcomingMovies usecase_getUpcomingMovies;
    private final USECASE_GetTopRatedMovies usecase_getTopRatedMovies;
    private final USECASE_GetPopularPerson usecase_getPopularPerson;
    private final USECASE_GetUser usecase_getUser;
    private final USECASE_LogoutUser usecase_logoutUser;
    private final USECASE_SaveUser usecase_saveUser;
    private final USECASE_TestFirestore usecase_testFirestore;
    private final USECASE_TestFirestoreRead usecase_testFirestoreRead;



    private  SplashPresenter pres;
    private HashMap<String,ResultWrapper> map = new HashMap<>();
    private final ArrayList<String> mListTopic = new ArrayList<String>();


    @Inject protected UseCaseHandler useCaseHandler;
    @Inject protected AppConfig appConfig;
    @Inject protected DataConfig dataConfig;
    @Inject protected AppUser appUser;
    @Inject protected RetroImage retroImage;

    @Inject
    public SplashModelImpl(

                           USECASE_GetGenre usecase_getGenre,
                           USECASE_GetTMDBConfiguration getTMDBConfiguration,
                           USECASE_authUser usecase_authUser,
                           USECASE_LogoutUser usecase_logoutUser,
                           USECASE_GetUser usecase_getUser,
                           USECASE_SaveUser usecase_saveUser,
                           USECASE_TestFirestore usecase_testFirestore,
                           USECASE_TestFirestoreRead usecase_testFirestoreRead,
                           USECASE_InitTables usecase_initTables,
                           USECASE_GetNowPlayingMovies usecase_getNowPlayingMovies,
                           USECASE_GetNowPlayingTVShows usecase_getNowPlayingTVShows,
                           USECASE_GetUpcomingMovies usecase_getUpcomingMovies,
                           USECASE_GetTopRatedMovies usecase_getTopRatedMovies,
                           USECASE_GetPopularPerson usecase_getPopularPerson
                       ) {

        this.usecase_getGenre = usecase_getGenre;
        this.usecase_getTMDBConfiguration = getTMDBConfiguration;
        this.usecase_authUser = usecase_authUser;
        this.usecase_getUser = usecase_getUser;
        this.usecase_saveUser = usecase_saveUser;
        this.usecase_testFirestore = usecase_testFirestore;
        this.usecase_testFirestoreRead = usecase_testFirestoreRead;
        this.usecase_logoutUser = usecase_logoutUser;
        this.usecase_initTables = usecase_initTables;
        this.usecase_getNowPlayingMovies = usecase_getNowPlayingMovies;
        this.usecase_getNowPlayingTVShows = usecase_getNowPlayingTVShows;
        this.usecase_getUpcomingMovies = usecase_getUpcomingMovies;
        this.usecase_getTopRatedMovies = usecase_getTopRatedMovies;
        this.usecase_getPopularPerson = usecase_getPopularPerson;

        this.mListTopic.add(AppConfig.NOWPLAYINGKEY);
        this.mListTopic.add(AppConfig.NOWPLAYINGTVKEY);
        this.mListTopic.add(AppConfig.UPCOMMINGKEY);
        this.mListTopic.add(AppConfig.TOPRATEDKEY);
        this.mListTopic.add(AppConfig.POPULARPERSONKEY);
        //this.mListTopic.add(AppConfig.HOMEHEADERKEY);
    }









    @Override
    public void initConfiguration(){


//        useCaseHandler.execute(usecase_authUser, USECASE_authUser.Params.with(0), new DisposableSubscriber<AppUser>() {
//            @Override
//            public void onNext(AppUser appUser) {
//                Log.e("usecase_authUser","onNext>>: "+appUser.toString());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Log.e("usecase_authUser","error "+t);
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e("usecase_authUser","complete");
//            }
//        });

        useCaseHandler.execute(usecase_getTMDBConfiguration, USECASE_GetTMDBConfiguration.Params.withCacheTime(1),
        new DisposableSubscriber<Configuration>() {
            @Override
            public void onNext(Configuration configuration) {
                presenter.configRetroImage(configuration);
                Log.e("---->",">>"+configuration.baseUrl());

            }

            @Override
            public void onError(Throwable t) {
               // presenter.onError(t);
                Log.e("ERRROR ",">>"+t);

            }

            @Override
            public void onComplete() {

                initGenres();

            }
        });
    }

    @Override
    public void initTables() {
        useCaseHandler.execute(usecase_initTables, USECASE_InitTables.Params.with(dataConfig.language()),
                new DisposableSubscriber<String>() {
                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable t) {
                        presenter.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        initGenres();
                    }

                });



    }

    @Override
    public void initGenres() {
        useCaseHandler.execute(usecase_getGenre, USECASE_GetGenre.Params.cachtime(1),
                new DisposableSubscriber<List<Genre>>() {
                    @Override
                    public void onNext(List<Genre> genres) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        presenter.toast("load genre");
                        initUser();
                    }
                });
    }

    @Override
    public void initUser() {


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        String token=null;
        if (accessToken != null) {
            token = accessToken.getToken();
        }
                useCaseHandler.execute(usecase_getUser,  USECASE_GetUser.Params.with(token), new DisposableSubscriber<AppUser>() {
                    @Override
                    public void onNext(AppUser user) {
                        Log.e("usecase_getUser","next "+user.toString());
                        saveUser(user);

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("usecase_getUser","error"+t);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("usecase_getUser","complete");
                      //  presenter.finishPreload(map);

                    }
                });

        //loadNextTopic();

    }

    private void saveUser(AppUser appUser){
        String path = "https://pbs.twimg.com/profile_images/853321507979882496/UMAwVI3j_normal.jpg";
        String pathfb = "https://graph.facebook.com//10215197067952270/picture?type=large";
        User user = appUser.getUser();
        retroImage.load(path)
                .preload(new RetroImageRequestListener() {
                    @Override
                    public GlideException onLoadFailed(GlideException e) {
                        return e;
                    }

                    @Override
                    public Drawable onResourceReady(Drawable resource) {
                       User us =  user.setPic(BitmapConverter.DrawableToByteArray(resource)).setName("Schamil");

                        useCaseHandler.execute(usecase_saveUser,  USECASE_SaveUser.Params.user(us), new DisposableSubscriber<AppUser>() {
                            @Override
                            public void onNext(AppUser user) {
                                Log.e("usecase_saveUser","next "+user.getUser().pic());
                                // presenter.setByteArrayPis(user.getUser().pic());
                                 presenter.setByteArray(user.getUser().pic());


                            }

                            @Override
                            public void onError(Throwable t) {
                                Log.e("usecase_saveUser","error"+t);
                            }

                            @Override
                            public void onComplete() {
                                Log.e("usecase_saveUser","complete");
                              //  testFireStore();
                                presenter.goHome();

                            }
                        });

                        return resource;
                    }
                });

    }


    private void testFireStore(){
        useCaseHandler.execute(usecase_testFirestore,
                 USECASE_TestFirestore.Params.withEmailAndPassword(AppUser.SignInType.twitter, "dfasf", "123456"),
                 new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.e("usecase_testFirestore","Success");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("usecase_testFirestore","error"+e);
                    }
                });

    }

    @Override
    public void testFireStoreRead(){
        useCaseHandler.execute(usecase_testFirestoreRead,
                USECASE_TestFirestoreRead.Params.withEmailAndPassword(AppUser.SignInType.twitter, "dfasf", "123456"),
                new DisposableObserver<AppUser>() {
                    @Override
                    public void onNext(AppUser appUser) {
                        Log.e("FirestoreRead ","user "+appUser.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FirestoreRead ","error "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("FirestoreRead ","onComplete ");
                    }
                });

    }

    @Override
    public void signout() {
        useCaseHandler.execute(usecase_logoutUser, USECASE_LogoutUser.Params.with(0),
                new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        Log.e("SIGNOUT ","user "+appUser.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("SIGNOUT ","onError "+e);
                    }
                }
       );
    }

    private void loadNextTopic() {
        Log.e("loadNextTopic","loadNextTopic."+this.mListTopic.size());
        if(this.mListTopic.size()>0) {
            fetchTopics(this.mListTopic);
        }else{
           // presenter.finishPreload(map);
        }
    }


    private void fetchTopics(ArrayList<String> mListTopic) {
        String topic = mListTopic.get(0);
        DisposableSubscriber subscriber = new DisposableSubscriber<ResultWrapper>() {
            @Override
            public void onNext(ResultWrapper wrapper) {
                map.put(mListTopic.get(0),wrapper);
                if(topic.equals(AppConfig.NOWPLAYINGKEY)){
                    presenter.setBgTeaser(wrapper);

                }
                if(topic.equals(AppConfig.POPULARPERSONKEY)){
                    presenter.setTestPerson(wrapper);

                }
            }

            @Override
            public void onError(Throwable t) {
                presenter.onError(t);
            }


            @Override
            public void onComplete() {
                mListTopic.remove(0);
                loadNextTopic();
            }
        };


        switch (topic){
            case AppConfig.NOWPLAYINGKEY:
                useCaseHandler.execute(this.usecase_getNowPlayingMovies, USECASE_GetNowPlayingMovies.Params.withPage(1), subscriber);
                break;
            case AppConfig.NOWPLAYINGTVKEY:
                useCaseHandler.execute(this.usecase_getNowPlayingTVShows, USECASE_GetNowPlayingTVShows.Params.withPage(1), subscriber);
                break;
            case AppConfig.UPCOMMINGKEY:
                useCaseHandler.execute(this.usecase_getUpcomingMovies, USECASE_GetUpcomingMovies.Params.withPage(1), subscriber);
                break;
            case AppConfig.TOPRATEDKEY:
                useCaseHandler.execute(this.usecase_getTopRatedMovies, USECASE_GetTopRatedMovies.Params.withPage(1), subscriber);
                break;
            case AppConfig.POPULARPERSONKEY:
                useCaseHandler.execute(this.usecase_getPopularPerson, USECASE_GetPopularPerson.Params.withPage(1), subscriber);
                break;
        }
    }

    @Override
    public UseCaseHandler getUseCaseHandler() {
        return useCaseHandler;
    }
}
