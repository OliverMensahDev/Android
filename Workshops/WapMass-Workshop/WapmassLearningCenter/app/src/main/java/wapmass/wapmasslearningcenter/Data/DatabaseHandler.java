package wapmass.wapmasslearningcenter.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

import wapmass.wapmasslearningcenter.Model.Course;
import wapmass.wapmasslearningcenter.Util.Constants;

public class DatabaseHandler  extends SQLiteOpenHelper {
    private Context context;
    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String creat_table = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.COURSE_ID + " INTEGER PRIMARY KEY,"
                + Constants.COURSE_TITLE + " TEXT,"
                + Constants.COURSE_LINK + " TEXT,"
//                + Constants.COURSE_IMAGE + " BLOB,"
                + Constants.COURSE_DESCRIPTION + " TEXT,"
                + Constants.COURSE_ADDED_DATE + " LONG" +
                ");";
        sqLiteDatabase.execSQL(creat_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        //HashMap
        ContentValues values = new ContentValues();
        values.put(Constants.COURSE_LINK, course.getcLink());
        values.put(Constants.COURSE_TITLE, course.getcTitle());
        values.put(Constants.COURSE_DESCRIPTION, course.getcDesc());
//        values.put(Constants.COURSE_IMAGE, course.getcImage());
        values.put(Constants.COURSE_ADDED_DATE,System.currentTimeMillis());//get system time

        DatabaseAsyncHandler databaseAsyncHandler = new DatabaseAsyncHandler(context.getContentResolver());
        databaseAsyncHandler.startInsert(1, null, Constants.TABLE_NAME, values);
        boolean result = (db.insert(Constants.TABLE_NAME, null, values) != -1);
        db.close();
        return result;
    }

    public Course getCourse(int id){
        //get readable db, get cursor, set values and return object
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.COURSE_LINK,
                Constants.COURSE_ID,
                Constants.COURSE_TITLE,
//                Constants.COURSE_IMAGE,
                Constants.COURSE_DESCRIPTION,
                Constants.COURSE_ADDED_DATE
        }, Constants.COURSE_ID + "=?",new String[]{String.valueOf(id)}, null, null, null, null);
        Course course = new Course();
        if(cursor != null){
            cursor.moveToFirst();
            course.setcId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.COURSE_ID))));
            course.setcTitle(cursor.getString(cursor.getColumnIndex(Constants.COURSE_TITLE)));
            //convert time to readable format
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.COURSE_ADDED_DATE))).getTime());
            course.setcDateAdded(formatedDate);
            course.setcLink(cursor.getString(cursor.getColumnIndex(Constants.COURSE_LINK)));
            course.setcDesc(cursor.getString(cursor.getColumnIndex(Constants.COURSE_DESCRIPTION)));
//            course.setcImage(cursor.getString(cursor.getColumnIndex(Constants.COURSE_IMAGE)));
        }
        cursor.close();
        db.close();
        return course;
    }

    public ArrayList<Course> getAllCourses(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Course> courseLists = new ArrayList<>();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.COURSE_LINK,
                Constants.COURSE_ID,
                Constants.COURSE_TITLE,
//                Constants.COURSE_IMAGE,
                Constants.COURSE_DESCRIPTION,
                Constants.COURSE_ADDED_DATE}, null, null, null, null, Constants.COURSE_ADDED_DATE + " DESC");
        if(cursor.moveToFirst()){
            do{
                Course course = new Course();
                cursor.moveToFirst();
                course.setcId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.COURSE_ID))));
                course.setcTitle(cursor.getString(cursor.getColumnIndex(Constants.COURSE_TITLE)));
                //convert time to readable format
//                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
//                String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.COURSE_ADDED_DATE))).getTime());
//                course.setcDateAdded(formatedDate);
                course.setcLink(cursor.getString(cursor.getColumnIndex(Constants.COURSE_LINK)));
                course.setcDesc(cursor.getString(cursor.getColumnIndex(Constants.COURSE_DESCRIPTION)));
//                course.setcImage(cursor.getString(cursor.getColumnIndex(Constants.COURSE_IMAGE)));
                courseLists.add(course);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return courseLists;
    }

    public int updateCourse(Course course){
        SQLiteDatabase db = this.getWritableDatabase();
        //HashMap
        ContentValues values = new ContentValues();
        values.put(Constants.COURSE_LINK, course.getcLink());
        values.put(Constants.COURSE_TITLE, course.getcTitle());
        values.put(Constants.COURSE_DESCRIPTION, course.getcDesc());
//        values.put(Constants.COURSE_IMAGE, course.getcImage());
        values.put(Constants.COURSE_ADDED_DATE,System.currentTimeMillis());//get system time
       int result = db.update(Constants.TABLE_NAME, values, Constants.COURSE_ID + "=?", new String[]{String.valueOf(course.getcId())});
       db.close();
       return result;
    }

    public void deleteCourse(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.COURSE_ID+ "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    //Get count
    public int getCourseCounts() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int result = cursor.getCount();
        cursor.close();
        db.close();
        return result;
    }

}
