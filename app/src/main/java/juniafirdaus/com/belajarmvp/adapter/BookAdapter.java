package juniafirdaus.com.belajarmvp.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import android.content.Context;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;

import java.util.List;

import juniafirdaus.com.belajarmvp.R;
import juniafirdaus.com.belajarmvp.BuildConfig;
import juniafirdaus.com.belajarmvp.models.ResultsItem;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<ResultsItem> books;
    private int rowLayout;
    private Context context;

    public BookAdapter(List<ResultsItem> books, int rowLayout, Context context) {
        this.books = books;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public BookAdapter.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new BookViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, final int position) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.background);
        holder.title.setText(books.get(position).getTitle());
        holder.author.setText(books.get(position).getReleaseDate());
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(BuildConfig.IMAGE + books.get(position).getPosterPath())
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        ImageView thumbnail;

        BookViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            author = v.findViewById(R.id.author);
            thumbnail = v.findViewById(R.id.thumbnail);
        }
    }
}
