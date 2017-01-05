package com.snalopainen.rajava_example.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.snalopainen.rajava_example.ExampleApplication;
import com.snalopainen.rajava_example.R;
import com.snalopainen.rajava_example.rest.model.WeatherResponse;
import com.snalopainen.rajava_example.temp.Contributor;
import com.snalopainen.rajava_example.temp.GithubService;
import com.snalopainen.rajava_example.temp.GithubService_WithRxjava;
import com.snalopainen.rajava_example.temp.GithubService_withConvert;
import com.snalopainen.rajava_example.temp.Students;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @InjectView(R.id.activity_main_data)
    protected RelativeLayout dataLayout;

    @InjectView(R.id.activity_main_weather)
    protected RelativeLayout weatherLayout;

    @InjectView(R.id.activity_main_search_button)
    protected TextView searchButton;

    @InjectView(R.id.activity_main_search)
    protected EditText searchEditText;

    @InjectView(R.id.activity_main_sys_country_value)
    protected TextView countryTextView;

    @InjectView(R.id.activity_main_sys_sunrise_value)
    protected TextView sunriseTextView;

    @InjectView(R.id.activity_main_sys_sunset_value)
    protected TextView sunsetTextView;

    @InjectView(R.id.activity_main_weather_icon)
    protected ImageView iconImageView;

    @InjectView(R.id.activity_main_weather_text)
    protected TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.inject(this);
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
        //buildRetrofitRequestInstanceWithoutConvert();
        //buildRetrofitRequestInstanceWithConvert();
        buildRetrofitRequestInstanceWithRxjava();
        searchButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onSearchClick();
            }
        });
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


    private Retrofit buildRetrofitInstanceWithRxjava() {
        return new Retrofit.Builder().baseUrl("https://api.github.com/").addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    private void buildRetrofitRequestInstanceWithRxjava() {
        GithubService_WithRxjava repo = buildRetrofitInstanceWithRxjava().create(GithubService_WithRxjava.class);
        Observable<List<Contributor>> observable = repo.contributorsByRxJava("square", "retrofit");

        mSubscriptions.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Contributor>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Contributor> contributors) {
                        Log.d(TAG, "Rxjava");
                        for (Contributor c : contributors) {
                            Log.d("TAG", "login:" + c.getLogin() + "  contributions:" + c.getContributions());
                        }
                    }
                }));

    }

    private Retrofit buildRetrofitInstanceWithConvert() {
        //@GET("repos/{owner}/{repo}/contributors")
        return new Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build();
    }

    private void buildRetrofitRequestInstanceWithConvert() {
        GithubService_withConvert repo = buildRetrofitInstanceWithConvert().create(GithubService_withConvert.class);
        Call<List<Contributor>> call = repo.contributorsByAddConvertGetCall("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                for (Contributor contributor : response.body()) {
                    Log.d("login", contributor.getLogin());
                    Log.d("contributions", contributor.getContributions() + "");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });
    }
    private void onSearchClick() {
        if (!searchEditText.getText().toString().equals("")) {
            Observable<WeatherResponse> weaterQueryCall;
            weaterQueryCall = ExampleApplication.getApiClient().getApiService().getWeather(searchEditText.getText().toString(), "c2471417eea029b90209d9813d7b55ca");

            mSubscriptions.add(weaterQueryCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<WeatherResponse>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable error) {
                            Log.e(TAG, "Error : " + error.getMessage());
                            searchEditText.setText("");
                            dataLayout.setVisibility(View.GONE);
                            weatherLayout.setVisibility(View.GONE);
                        }

                        @Override
                        public void onNext(WeatherResponse response) {
                            //Log.d("TAG", "login:" + response.getLogin() + "  contributions:" + response.getContributions());
                            final Date sunriseDate = new Date(response.getSys().getSunriseTime() * 1000);
                            final Date sunsetDate = new Date(response.getSys().getSunsetTime() * 1000);
                            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh':'mm':'ss a");

                            //getActionBar().setTitle(response.getStrCityName());
                            countryTextView.setText(response.getSys().getStrCountry());

                            if (!response.getWeather().isEmpty()) {
                                Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + response.getWeather().get(0).getStrIconName() + ".png").into(iconImageView);
                                weatherTextView.setText(response.getWeather().get(0).getStrDesc());
                            }

                            sunsetTextView.setText(simpleDateFormat.format(sunsetDate));
                            sunriseTextView.setText(simpleDateFormat.format(sunriseDate));

                            searchEditText.setText("");
                            Log.e(TAG, "City name : " + response.getStrCityName());
                            dataLayout.setVisibility(View.VISIBLE);
                            weatherLayout.setVisibility(View.VISIBLE);
                        }
                    }));
        }
    }
}
