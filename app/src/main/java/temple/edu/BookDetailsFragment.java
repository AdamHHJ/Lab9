package temple.edu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class BookDetailsFragment extends Fragment {


    private TextView tvBook;
    private Button btPlay;
    private ImageView image;
    private Book book;

    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment bookDetailsFragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("book",book);
        bookDetailsFragment.setArguments(args);
        return bookDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        tvBook = view.findViewById(R.id.tv_title);
        image = view.findViewById(R.id.image);
        btPlay = view.findViewById(R.id.btPlay);
        if (getArguments()!=null) {
             book = (Book) getArguments().getSerializable("book");
            if (book != null) {
                displayBook(book);
            }
        }
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null) {
                    listener.play(book);
                }
            }
        });
        return view;
    }


    public void displayBook(Book book) {
        this.book = book;
        tvBook.setText(book.title);
        Picasso.get().load(book.cover_url).fit().into(image);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookDetailsFragment.PlayListener) {
            listener = (BookDetailsFragment.PlayListener) context;
        }
    }
    PlayListener listener;
    public interface PlayListener {
        void play(Book book);
    }
}
