package com.aggarwals.wartube;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private Context mContext;
    private List<FriendlyMessage> friendlyMessageList;

    public MessageAdapter(Context context, List<FriendlyMessage> friendlyMessages){
        mContext = context;
        friendlyMessageList = friendlyMessages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_message, viewGroup, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder messageViewHolder, int i) {
        FriendlyMessage currentMessage = friendlyMessageList.get(i);

        boolean isPhoto = currentMessage.getPhotoUrl() != null;
        if (isPhoto){
            messageViewHolder.textView1.setVisibility(View.GONE);
            messageViewHolder.imageView.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(currentMessage.getPhotoUrl())
                    .into(messageViewHolder.imageView);
        } else {
            messageViewHolder.imageView.setVisibility(View.GONE);
            messageViewHolder.textView1.setVisibility(View.VISIBLE);
            messageViewHolder.textView1.setText(currentMessage.getText());
        }

        messageViewHolder.textView2.setText(currentMessage.getName());
    }

    @Override
    public int getItemCount() {
        return friendlyMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        public TextView textView1;
        public ImageView imageView;
        public TextView textView2;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.messageTextView);
            textView2 = itemView.findViewById(R.id.nameTextView);
            imageView = itemView.findViewById(R.id.photoImageView);
        }
    }
}