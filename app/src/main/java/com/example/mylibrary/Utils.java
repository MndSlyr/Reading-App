package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;


    private static ArrayList<Book> allBooks , alreadyReadBooks , wantToReadBooks , currentlyReadingBooks , favouriteBooks;

    private Utils() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if(null == favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        allBooks.add(new Book(1 , 1350 , "1Q84" , "Haruki Murakami" , "https://cdn.kobo.com/book-images/ea7638b8-a8e4-4b7b-9792-fbc88e248d9b/1200/1200/False/1q84-book-3.jpg"
                , "A work of Maddening Brilliance" , "Long"));
        allBooks.add(new Book(2 , 1350 , "1Q84" , "Haruki Murakami" , "https://cdn.kobo.com/book-images/ea7638b8-a8e4-4b7b-9792-fbc88e248d9b/1200/1200/False/1q84-book-3.jpg"
                , "A work of Maddening Brilliance" , "Long"));
    }

    public static Utils getInstance() {
        if(null != instance){
            return instance;
        }
        else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }
    public Book getBookById(int id){
        for(Book b : allBooks){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }
    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }
    public boolean addToFavourites(Book book){
        return favouriteBooks.add(book);
    }

    public boolean addToCurrentlyReadingBooks(Book book) {
        return currentlyReadingBooks.add(book);
    }
}
