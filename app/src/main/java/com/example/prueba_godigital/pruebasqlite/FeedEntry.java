package com.example.prueba_godigital.pruebasqlite;

public class FeedEntry {

    public static final String TABLE_MOVIES = "movies";
    public static final String ID = "id";
    public static final String DATA = "data";

    public static final String CREATE_TABLE_MOVIES ="CREATE TABLE " + TABLE_MOVIES + " ("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ DATA +" TEXT)";

}
