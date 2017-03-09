package scorecard.project.com.booklistingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abhishek on 28/02/2017.
 */
public class BookAdapter extends ArrayAdapter<Books> {

    public BookAdapter(Context context, List<Books> book) {
        super(context, 0, book);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.books_item_list, parent, false);
        }
        Books currentBook = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentBook.getmBooktitle());
        TextView subTitle = (TextView) listItemView.findViewById(R.id.subTitle);
        subTitle.setText(currentBook.getmSubTitle());
        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(currentBook.getmAuthor());
        TextView publication = (TextView) listItemView.findViewById(R.id.numberOfPages);
        publication.setText(currentBook.getmPublisher());
        return listItemView;
    }
}

