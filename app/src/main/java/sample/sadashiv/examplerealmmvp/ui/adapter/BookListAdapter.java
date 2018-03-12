package sample.sadashiv.examplerealmmvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import sample.sadashiv.examplerealmmvp.R;
import sample.sadashiv.examplerealmmvp.model.Book;
import sample.sadashiv.examplerealmmvp.ui.books.BooksView;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> implements RealmChangeListener {

    private RealmResults<Book> mBooks;
    private BooksView mBooksView;

    public BookListAdapter(BooksView booksView) {
        mBooksView = booksView;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Book book = mBooks.get(position);

        holder.mTextTitle.setText(book.getTitle());
        holder.mTextDetails.setText(book.getDetails());
        holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mBooksView != null) {
                    mBooksView.onBookClick(book.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBooks != null ? mBooks.size() : 0;
    }

    public void setBooks(final RealmResults<Book> books) {
        mBooks = books;
        mBooks.addChangeListener(this);
        notifyDataSetChanged();
    }

    @Override
    public void onChange(Object o) {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_item_container)
        LinearLayout mLayoutItem;
        @BindView(R.id.text_title)
        TextView mTextTitle;
        @BindView(R.id.text_details)
        TextView mTextDetails;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
