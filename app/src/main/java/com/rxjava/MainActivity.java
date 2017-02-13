package com.rxjava;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private CircleImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =(CircleImageView)findViewById(R.id.imageView);
    }

    public void picasso(View view)
    {
        String url = "http://e.hiphotos.baidu.com/image/pic/item/d043ad4bd11373f0d6acc29aa60f4bfbfbed0427.jpg";
        Picasso.with(this).load(url).into(imageView);
    }

    public void httpButton(View view)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cloud.ellabook.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<MyResponse> call =retrofit.create(httpGetMyBuyBooks.class).getString("5656","1","1000","android");
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                MyResponse res = response.body();
                Log.e("MyResponse",res.toString());
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.e("Throwable",t.toString());
            }
        });
    }

    public void run(View view)
    {
        final int drawableRes = R.drawable.timg;

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getResources().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
