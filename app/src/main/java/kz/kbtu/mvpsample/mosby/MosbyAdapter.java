package kz.kbtu.mvpsample.mosby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kz.kbtu.mvpsample.R;
import kz.kbtu.mvpsample.data.Post;

/**
 * Created by aibekkuralbaev on 23.10.17.
 */

public class MosbyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    Context mContext;
    List<Post> mPostList;


    public MosbyAdapter(Context context, List<Post> contactsList) {
        mContext = context;
        mPostList = contactsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("ViewHolder", "Create");
        View view;

        view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.e("ViewHolder", "Bind");


        MyViewHolder viewHolder = (MyViewHolder) holder;
        Post post = mPostList.get(position);
        viewHolder.setPosition(position);
        viewHolder.mName.setText(post.getTitle());
        viewHolder.mPhone.setText(post.getBody());

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mName;
        TextView mPhone;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mPhone = (TextView) itemView.findViewById(R.id.phone);

            itemView.setOnClickListener(this);
        }


        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(mContext, ContactDetailsActivity.class);
//            intent.putExtra("post", mPostList.get(position));
//            mContext.startActivity(intent);
        }
    }


    class SecondViewHolder extends RecyclerView.ViewHolder {


        public SecondViewHolder(View itemView) {
            super(itemView);
        }
    }

}