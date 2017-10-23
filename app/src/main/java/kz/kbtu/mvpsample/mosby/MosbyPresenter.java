package kz.kbtu.mvpsample.mosby;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

import kz.kbtu.mvpsample.api.ApiService;
import kz.kbtu.mvpsample.data.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by aibekkuralbaev on 23.10.17.
 */

public class MosbyPresenter extends MvpBasePresenter<MosbyView>{


    ApiService mService;

    @Inject
    public MosbyPresenter(Retrofit retrofit){
        mService = retrofit.create(ApiService.class);
    }


    public void loadPosts(){

        Call<List<Post>> listCall = mService.getPosts();

        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                getView().setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                getView().showError(t, false);
            }
        });
    }
}
