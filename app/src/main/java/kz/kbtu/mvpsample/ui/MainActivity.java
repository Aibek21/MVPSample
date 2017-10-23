package kz.kbtu.mvpsample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import kz.kbtu.mvpsample.presenter.MainPresenter;
import kz.kbtu.mvpsample.MyApp;
import kz.kbtu.mvpsample.data.Post;
import kz.kbtu.mvpsample.R;
import kz.kbtu.mvpsample.dagger.NetComponent;

public class MainActivity extends AppCompatActivity implements MainView {


    MainPresenter mMainPresenter;

    private NetComponent mNetComponent;

    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetComponent = ((MyApp) getApplication()).getNetComponent();
        mNetComponent.inject(this);



        mMainPresenter = mNetComponent.mainPresenter();
        mMainPresenter.setView(this);
        mMainPresenter.loadPosts();
    }




    @Override
    public void refreshList(List<Post> posts) {
        Toast.makeText(this, "Count: "+posts.size(), Toast.LENGTH_SHORT).show();

    }


    public void setAdapter(){

    }
}
