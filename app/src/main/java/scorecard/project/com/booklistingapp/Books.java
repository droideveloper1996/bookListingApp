package scorecard.project.com.booklistingapp;

/**
 * Created by Abhishek on 28/02/2017.
 */
public class Books {

    private long mISBN;
    private String mBooktitle;
    private String mAuthor;
    private String mDescription;
    private String mPublisher;
    private int mPage;
    String mSubTitle;
    public Books(String title,String subtitle,String author,String description,int page,String publisher){

        mAuthor=author;
        mSubTitle=subtitle;
        mDescription=description;
        mBooktitle=title;
        mPublisher=publisher;
        mPage=page;

    }
    public long getmISBN(){return mISBN;}
    public String getmBooktitle(){return mBooktitle;}
    public String getmAuthor(){return mAuthor;}
    public String getmDescription(){return mDescription;}
    public String getmPublisher(){return mPublisher;}
    public int getmPage(){return  mPage;}
    public String getmSubTitle(){ return  mSubTitle;}

}
