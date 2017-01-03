package com.snalopainen.rajava_example;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //outPutWords();
        //printStringArrays();
        // displayImageDrawable();
        //schedulePrintStringArraysTask();
        //schedulerDisplayImageDrawable();
        // mapStudentsToNames();
        // mapStudentsToScores();
        buildRetrofitRequestInstanceWithoutConvert();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void outPutWords() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
                subscriber.onNext("hello rxjava");
                subscriber.onNext("hello rxjava , I am coming ");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        });
    }

    private void printStringArrays() {
        String name[] = {"james", "jhon", "tom"};
        Observable.from(name).subscribe(new Observer<String>() {
                                            @Override
                                            public void onCompleted() {
                                                Log.i(TAG, "onCompleted");
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Log.i(TAG, "onError");
                                            }

                                            @Override
                                            public void onNext(String s) {
                                                Log.i(TAG, s);
                                            }
                                        }
        );
    }

    private void printStringArrays_2() {
        String names[] = {"james", "jhon", "tom"};
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                Log.d(TAG, name);
            }
        });
    }

    private void displayImageDrawable() {
        final int resDrawable = android.R.drawable.ic_dialog_email;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(resDrawable);
                subscriber.onNext(drawable);
            }
        }).subscribe(new Subscriber<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {
                ((ImageView) (findViewById(R.id.image))).setImageDrawable(drawable);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }
        });
    }

    private void schedulePrintStringArraysTask() {
        String names[] = {"james", "jhon", "tom"};
        Observable.from(names).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.i(TAG, name);
                        Log.i(TAG, Thread.currentThread().toString());
                    }
                });
    }

    private void schedulerDisplayImageDrawable() {
        final int resDrawable = android.R.drawable.ic_dialog_email;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Log.i(TAG, Thread.currentThread().toString());
                Drawable drawable = getTheme().getDrawable(resDrawable);
                subscriber.onNext(drawable);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {
                Log.i(TAG, Thread.currentThread().toString());
                ((ImageView) (findViewById(R.id.image))).setImageDrawable(drawable);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }
        });
    }

    private void mapStudentsToNames() {
        Students[] students = {new Students("tom", new String[]{"100", "200", "97"}),
                new Students("mike", new String[]{"60", "60", "60"}),
                new Students("zhangsan", new String[]{"50", "50", "50"})};


        Observable.from(students).map(new Func1<Students, String>() {
            @Override
            public String call(Students students) {
                return students.getName();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String name) {
                Log.i(TAG, name);
            }
        });
    }

    private void mapStudentsToScores() {
        Students[] students = {new Students("tom", new String[]{"100", "200", "97"}),
                new Students("mike", new String[]{"60", "60", "60"}),
                new Students("zhangsan", new String[]{"50", "50", "50"})};


        Observable.from(students).flatMap(new Func1<Students, Observable<String>>() {
            @Override
            public Observable<String> call(Students students) {
                return Observable.from(students.getScores());
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String scrore) {
                Log.i(TAG, scrore);
            }
        });
    }

    /**
     * 第二步,创建retrofit实例
     */
    private Retrofit buildRetrofitInstance() {
        //@GET("repos/{owner}/{repo}/contributors")
        return new Retrofit.Builder().baseUrl("https://api.github.com/").build();
    }

    /**
     * 第三步,根据第二步创建的retroit实例去创建一个retrofit请求实例
     */
    private void buildRetrofitRequestInstanceWithoutConvert(/*String u, String r*/) {

        GithubService repo = buildRetrofitInstance().create(GithubService.class);
        Call<ResponseBody> call = repo.contributorBySimpleGetCall("square", "retrofit");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Gson gson = new Gson();
                    ArrayList<Contributor> contributorsList = gson.fromJson(response.body().string(), new TypeToken<List<Contributor>>() {
                    }.getType());
                    for (Contributor contributor : contributorsList) {
                        Log.d("login", contributor.getLogin());
                        Log.d("contributions", contributor.getContributions() + "");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void buildRetrofitRequestInstanceWithConvert(){

    }
}
