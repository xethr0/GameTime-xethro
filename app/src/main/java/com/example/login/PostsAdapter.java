package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    public Context mContext;
    private List<Post> mPost = new ArrayList<>();

    private FirebaseUser FirebaseUser;

    public PostsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPosts(List<Post> posts){
        this.mPost = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.game_feed_post, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        //Log.d(TAG, "onBindViewHolder: called.");
        FirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Post post = mPost.get(i);

        if(post.getSport().equals("")){
            holder.description.setVisibility(View.GONE);
        }else {
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(post.getSport());
        }
        holder.eventTitle.setText(post.getEventTitle());
        holder.eventDate.setText(post.getEventDate());
        holder.eventTime.setText(post.gettime());
        holder.eventLocation.setText(post.getAddress());

        publisherInfo(holder.postUsername, post.getUid());

    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventLocation, eventTitle, description, postUsername, datePosted, timePosted, eventDate, eventTime;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            eventTitle = itemView.findViewById(R.id.EventPostInfo_EventTitle);
            description = itemView.findViewById(R.id.postDescription);
            postUsername = itemView.findViewById(R.id.profileName);
            //datePosted = itemView.findViewById(R.id.feedPostDate);
            //timePosted = itemView.findViewById(R.id.feedPostTime);
            eventDate = itemView.findViewById(R.id.EventPostInfo_EventDate);
            eventTime = itemView.findViewById(R.id.EventPostInfo_EventTime);
            eventLocation = itemView.findViewById(R.id.EventPostInfo_EventLocation);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
    private void publisherInfo(TextView postUsername, String userid){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                postUsername.setText(users.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
