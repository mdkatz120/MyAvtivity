package com.example.shorfamily.myavtivity.src.model.backend;

import android.content.Context;

/**
 * Created by katz on 24/11/2015.
 */
public final class BackendFactory {

    static Backend instance = null;
    public static String mode = "lists";


    public final static Backend getInstance() {
        if (mode == "lists") {
            if (instance == null)
                instance = new  com.example.shorfamily.myavtivity.src.model.datasource.DatabaseList();
                return instance;
        }
        if (mode == "sql") {
            if (instance == null)
    //            instance = new  com.example.shorfamily.myavtivity.src.model.datasource.DatabaseSQLite();
                return instance;
        }
        if (mode == "Service") {
            if (instance == null)
                  instance = new  com.example.shorfamily.myavtivity.src.model.datasource.DatabaseMySql();
                return instance;
        }
            return null;
    }
}
