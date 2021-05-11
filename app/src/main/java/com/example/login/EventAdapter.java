package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.Viewholder>
{

    private Context context;
    private ArrayList<Event> eventArrayList;
    private OnItemClickListener itemClickListener;
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        itemClickListener = listener;
    }
    public EventAdapter(Context context, ArrayList<Event> courseModelArrayList)
    {
        this.context = context;
        this.eventArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public EventAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new Viewholder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.Viewholder holder, int position)
    {
        Event model = eventArrayList.get(position);
        holder.eventTitle.setText(model.getTitle());
        holder.evenType.setText(model.getSportType());
    }

    @Override
    public int getItemCount()
    {

        return eventArrayList.size();
    }


    public static class Viewholder extends RecyclerView.ViewHolder
    {
        private TextView eventTitle, evenType;

        public Viewholder(@NonNull View itemView, EventAdapter.OnItemClickListener listener)
        {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.idEventTitle);
            evenType = itemView.findViewById(R.id.idEventType);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }





}