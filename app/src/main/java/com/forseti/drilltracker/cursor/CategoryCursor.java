package com.forseti.drilltracker.cursor;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;

import com.forseti.drilltracker.Category;
import com.forseti.drilltracker.adapter.ExpandableDrillListAdapter;

import java.util.ArrayList;

public class CategoryCursor implements Cursor {
    ArrayList<Category> categories = new ArrayList<>();
    int position = 0;

    public CategoryCursor(ExpandableDrillListAdapter listAdapter) {
        int categoryCount = listAdapter.getGroupCount();
        for(int index = 0; index < categoryCount; index++) {
            categories.add((Category) listAdapter.getGroup(index));
        }
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public boolean move(int offset) {
        int newPosition = position + offset;
        if (newPosition >= 0 && newPosition < (getCount() - 1)) {
            position = newPosition;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveToPosition(int position) {
        if (position < getCount() && position >= 0) {
            this.position = position;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveToFirst() {
        return moveToPosition(0);
    }

    @Override
    public boolean moveToLast() {
        return moveToPosition(getCount() - 1);
    }

    @Override
    public boolean moveToNext() {
        return moveToPosition(position + 1);
    }

    @Override
    public boolean moveToPrevious() {
        return moveToPosition(position - 1);
    }

    @Override
    public boolean isFirst() {
        return position == 0;
    }

    @Override
    public boolean isLast() {
        return position == (getCount() - 1);
    }

    @Override
    public boolean isBeforeFirst() {
        return position < 0;
    }

    @Override
    public boolean isAfterLast() {
        return position >= getCount();
    }

    @Override
    public int getColumnIndex(String columnName) {
        int index = 0;
        for (index = 0; index < getCount(); index++) {
            if (categories.get(index).getName().equals(columnName)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
        int index = getColumnIndex(columnName);
        if (index == -1) {
            throw new IllegalArgumentException("Category " + columnName + " not found!");
        }
        return index;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return categories.get(columnIndex).getName();
    }

    @Override
    public String[] getColumnNames() {
        String[] columnNames = new String[getCount()];
        for(int index = 0; index < getCount(); index++) {
            columnNames[index] = getColumnName(index);
        }

        return columnNames;
    }

    @Override
    public int getColumnCount() {
        return categories.size();
    }

    @Override
    public byte[] getBlob(int columnIndex) {
        return new byte[0];
    }

    @Override
    public String getString(int columnIndex) {
        return getColumnName(columnIndex);
    }

    @Override
    public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {
        buffer.data = getString(columnIndex).toCharArray();
    }

    @Override
    public short getShort(int columnIndex) {
        return 0;
    }

    @Override
    public int getInt(int columnIndex) {
        return 0;
    }

    @Override
    public long getLong(int columnIndex) {
        return 0;
    }

    @Override
    public float getFloat(int columnIndex) {
        return 0;
    }

    @Override
    public double getDouble(int columnIndex) {
        return 0;
    }

    @Override
    public int getType(int columnIndex) {
        return 0;
    }

    @Override
    public boolean isNull(int columnIndex) {
        return getColumnName(columnIndex).isEmpty();
    }

    @Override
    public void deactivate() {

    }

    @Override
    public boolean requery() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void registerContentObserver(ContentObserver observer) {

    }

    @Override
    public void unregisterContentObserver(ContentObserver observer) {

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void setNotificationUri(ContentResolver cr, Uri uri) {

    }

    @Override
    public Uri getNotificationUri() {
        return null;
    }

    @Override
    public boolean getWantsAllOnMoveCalls() {
        return false;
    }

    @Override
    public void setExtras(Bundle extras) {

    }

    @Override
    public Bundle getExtras() {
        return null;
    }

    @Override
    public Bundle respond(Bundle extras) {
        return null;
    }
}
