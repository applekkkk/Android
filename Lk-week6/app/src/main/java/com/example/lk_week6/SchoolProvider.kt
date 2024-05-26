package com.example.lk_week6

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class SchoolProvider : ContentProvider() {
    private val authority="com.example.lk_week6.provider"
    private var dbHelper:StudentsDatabaseHelper_Lk?=null
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int =dbHelper?.let{
        val db=dbHelper?.writableDatabase
        val deleteRows=db?.delete("Student",selection,selectionArgs)
        deleteRows
    }?:0

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.com.example.lk_week6.provider.Student"
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri?=dbHelper?.let {
        val db=dbHelper?.writableDatabase
        val newStudent=db?.insert("Student",null,values)
        Uri.parse("content://$authority/Student/$newStudent")
    }

    override fun onCreate(): Boolean=context?.let {
        dbHelper=StudentsDatabaseHelper_Lk(it,"School.db",1)
        true
    }?:false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor?=dbHelper?.let {
        val db=dbHelper?.readableDatabase
        val cursor=db?.query("Student",projection,selection,selectionArgs,null,null,sortOrder)
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int=dbHelper?.let {
        val db=dbHelper?.writableDatabase
        val updateRows=db?.update("Student",values,selection,selectionArgs)
        updateRows
    }?:0
}