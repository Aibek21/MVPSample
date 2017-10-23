package kz.kbtu.mvpsample.presenter;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import kz.kbtu.mvpsample.api.ApiService;
import kz.kbtu.mvpsample.ui.MainView;
import kz.kbtu.mvpsample.data.Post;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aibekkuralbaev on 23.10.17.
 */

public class MainPresenter {

    MainView mMainView;
    Context mContext;

    @Inject
    public MainPresenter(Context context, Retrofit retrofit){
        mContext = context;
    }


    public void setView(MainView mainView){
        mMainView = mainView;
    }


    public void loadPosts(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().
                readTimeout(60, TimeUnit.SECONDS).
                connectTimeout(60, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);


        Call<List<Post>> listCall = service.getPosts();

        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                mMainView.refreshList(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
