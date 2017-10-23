package kz.kbtu.mvpsample.mosby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.kbtu.mvpsample.MyApp;
import kz.kbtu.mvpsample.R;
import kz.kbtu.mvpsample.dagger.NetComponent;
import kz.kbtu.mvpsample.data.Post;

public class MosbyActivity extends MvpLceActivity<SwipeRefreshLayout, List<Post>, MosbyView, MosbyPresenter> implements MosbyView, SwipeRefreshLayout.OnRefreshListener {


    List<Post> mPosts = new ArrayList<>();
    MosbyAdapter mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private NetComponent mNetComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosby);
        ButterKnife.bind(this);
        mNetComponent = ((MyApp) getApplication()).getNetComponent();
        mNetComponent.inject(this);

        contentView.setOnRefreshListener(this);

        mAdapter = new MosbyAdapter(this, mPosts);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



        loadData(false);
    }

    @NonNull
    @Override
    public MosbyPresenter createPresenter() {
        return mNetComponent.mosbyPresenter();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return "You don't have posts yet";
    }

    @Override
    public void setData(List<Post> data) {
        contentView.setRefreshing(false);
        loadingView.setVisibility(View.GONE);
        mPosts.clear();
        mPosts.addAll(data);
        mAdapter.notifyDataSetChanged();
        if (data.size() == 0) showError(null, false);

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadPosts();
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
