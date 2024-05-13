package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private TextView txtBookName , txtAuthorName , txtPages , txtDescription;
    private Button btnAddToAlreadyReadList , btnAddToCurrentlyReading , btnAddToWantToReadList , btnToAddToFavouritesList;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

//        Book book = new Book(1 , 1350 , "1Q84" , "Haruki Murakami" , "https://cdn.kobo.com/book-images/ea7638b8-a8e4-4b7b-9792-fbc88e248d9b/1200/1200/False/1q84-book-3.jpg"
//                , "A work of Maddening Brilliance" , "Long");

        Intent intent = getIntent();
        if(null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY , -1);
            if(bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if(null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleFavouriteBook(incomingBook);
                }
            }
        }
    }

    private void handleFavouriteBook(Book book) {
        ArrayList<Book> FavouriteBooks = Utils.getInstance().getFavouriteBooks();

        boolean existInFavouriteBooks = false;

        for(Book b : FavouriteBooks){
            if(b.getId() == book.getId()){
                existInFavouriteBooks = true;
            }
        }

        if(existInFavouriteBooks){
            btnToAddToFavouritesList.setEnabled(false);
        }
        else {
            btnToAddToFavouritesList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addToFavourites(book)){
                        Toast.makeText(BookActivity.this , "Book Added" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this , AddToFavouritesBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this  , "Something Wrong Happened, Try Again!!" , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBook(Book book) {
        ArrayList<Book> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for(Book b : currentlyReadingBooks){
            if(b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }

        if(existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }
        else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this , "Book Added" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this , CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this  , "Something Wrong Happened, Try Again!!" , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for(Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }
        }

        if(existInWantToReadBooks){
            btnAddToWantToReadList.setEnabled(false);
        }
        else {
            btnAddToWantToReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this , "Book Added" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this , WantToReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this  , "Something Wrong Happened, Try Again!!" , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for(Book b : alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if(existInAlreadyReadBooks){
            btnAddToAlreadyReadList.setEnabled(false);
        }
        else {
            btnAddToAlreadyReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this , "Book Added" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this , AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this  , "Something Wrong Happened, Try Again!!" , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews(){
        txtAuthorName = findViewById(R.id.txtAuthorName);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtdescription);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantToReadList = findViewById(R.id.btnAddToWantToReadList);
        btnToAddToFavouritesList = findViewById(R.id.btnToAddToFavouritesList);
        btnAddToAlreadyReadList = findViewById(R.id.btnToAlreadyReadList);

        bookImage = findViewById(R.id.bookImage);
    }
}