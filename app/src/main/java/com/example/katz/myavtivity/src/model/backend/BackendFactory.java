package com.example.katz.myavtivity.src.model.backend;

import com.example.katz.myavtivity.src.model.datasource.DatabaseList;
import com.example.katz.myavtivity.src.model.datasource.DatabaseMySql;

/**
 * Created by katz on 24/11/2015.
 */
public final class BackendFactory {

    static Backend instance = null;
    public static String mode = "lists";


    public final static Backend getInstance() {
        if (mode == "lists") {
            if (instance == null)
                instance = new DatabaseList();
                return instance;
        }
        if (mode == "sql") {
            if (instance == null)
    //            instance = new  DatabaseSQLite();
                return instance;
        }
        if (mode == "Service") {
            if (instance == null)
                  instance = new DatabaseMySql();
                return instance;
        }
            return null;
    }
}
