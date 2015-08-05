package demo.abhi.mynotes.mynotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by AbhishekShetty on 8/4/2015.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {
    private LayoutInflater mInflater;
    private List<NotesContent> data = Collections.emptyList();

    NotesAdapter(Context context,List<NotesContent> data){
         mInflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_row,parent,false);
        MyViewHolder myViewHolder  = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NotesContent currentData = data.get(position);
        holder.Header.setText(currentData.Header);
        holder.Content.setText(currentData.Content);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView Header;
        private TextView Content;
        public MyViewHolder(View itemView) {
            super(itemView);
            Header = (TextView)itemView.findViewById(R.id.header);
            Content = (TextView)itemView.findViewById(R.id.content);;

        }
    }
}
