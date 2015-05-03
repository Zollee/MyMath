package com.howard.mymath;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {
    private SQLiteDatabase database;
    String title;
    CardView[] card = new CardView[3];

    TextView[] sub_definition = new TextView[3];
    TextView[] sub_display = new TextView[3];
    TextView[] sub_nature = new TextView[6];

    TextView[] text_definition = new TextView[3];
    TextView[] text_display = new TextView[3];
    TextView[] text_nature = new TextView[6];

    View[] divider_definition = new View[2];
    View[] divider_display = new View[2];
    View[] divider_nature = new View[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_detail);

        card[0] = (CardView)findViewById(R.id.card_definition);
        card[1] = (CardView)findViewById(R.id.card_display);
        card[2] = (CardView)findViewById(R.id.card_nature);

        sub_definition[0] = (TextView)findViewById(R.id.sub_definition_1);
        sub_definition[1] = (TextView)findViewById(R.id.sub_definition_2);
        sub_definition[2] = (TextView)findViewById(R.id.sub_definition_3);
        sub_display[0] = (TextView)findViewById(R.id.sub_display_1);
        sub_display[1] = (TextView)findViewById(R.id.sub_display_2);
        sub_display[2] = (TextView)findViewById(R.id.sub_display_3);
        sub_nature[0] = (TextView)findViewById(R.id.sub_nature_1);
        sub_nature[1] = (TextView)findViewById(R.id.sub_nature_2);
        sub_nature[2] = (TextView)findViewById(R.id.sub_nature_3);
        sub_nature[3] = (TextView)findViewById(R.id.sub_nature_4);
        sub_nature[4] = (TextView)findViewById(R.id.sub_nature_5);
        sub_nature[5] = (TextView)findViewById(R.id.sub_nature_6);


        text_definition[0] = (TextView)findViewById(R.id.text_definition_1);
        text_definition[1] = (TextView)findViewById(R.id.text_definition_2);
        text_definition[2] = (TextView)findViewById(R.id.text_definition_3);
        text_display[0] = (TextView)findViewById(R.id.text_display_1);
        text_display[1] = (TextView)findViewById(R.id.text_display_2);
        text_display[2] = (TextView)findViewById(R.id.text_display_3);
        text_nature[0] = (TextView)findViewById(R.id.text_nature_1);
        text_nature[1] = (TextView)findViewById(R.id.text_nature_2);
        text_nature[2] = (TextView)findViewById(R.id.text_nature_3);
        text_nature[3] = (TextView)findViewById(R.id.text_nature_4);
        text_nature[4] = (TextView)findViewById(R.id.text_nature_5);
        text_nature[5] = (TextView)findViewById(R.id.text_nature_6);

        divider_definition[0] = findViewById(R.id.divider_definition_1);
        divider_definition[1] = findViewById(R.id.divider_definition_2);
        divider_display[0] = findViewById(R.id.divider_display_1);
        divider_display[1] = findViewById(R.id.divider_display_2);
        divider_nature[0] = findViewById(R.id.divider_nature_1);
        divider_nature[1] = findViewById(R.id.divider_nature_2);
        divider_nature[2] = findViewById(R.id.divider_nature_3);
        divider_nature[3] = findViewById(R.id.divider_nature_4);
        divider_nature[4] = findViewById(R.id.divider_nature_5);

        View view = findViewById(R.id.view);
        if(android.os.Build.VERSION.SDK_INT<19)
            view.setVisibility(View.GONE);

        title = intent.getStringExtra("title");

        database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setTitle(title);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        definitionCreate();
        displayCreate();
        natureCreate();
    }

    final Html.ImageGetter imageGetter = new Html.ImageGetter() {

        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            int id = getResources().getIdentifier(source,"drawable", "com.howard.mymath");
            drawable = getResources().getDrawable(id);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight());
            return drawable;
        }
    };

    public void definitionCreate(){
        String where = "SELECT definition.name, definition.definition FROM definition JOIN book ON book.key_id = definition.main WHERE book.name = '"+ title + "'";
        Cursor cur = database.rawQuery(where, null);

        if (cur != null) {
            String subText;
            String mainText;

            int i = 1;

            if (cur.moveToFirst()) {
                subText = cur.getString(cur.getColumnIndex("name"));
                mainText = cur.getString(cur.getColumnIndex("definition"));

                if (subText != null){
                    card[0].setVisibility(View.VISIBLE);
                    sub_definition[0].setVisibility(View.VISIBLE);
                    text_definition[0].setVisibility(View.VISIBLE);

                    sub_definition[0].setText(Html.fromHtml(subText));
                    text_definition[0].setText(Html.fromHtml(mainText, imageGetter, null));
                }else if (mainText != null){
                    card[0].setVisibility(View.VISIBLE);
                    text_definition[0].setVisibility(View.VISIBLE);
                    text_definition[0].setText(Html.fromHtml(mainText, imageGetter, null));
                }
            }

            while(cur.moveToNext()){
                subText = cur.getString(cur.getColumnIndex("name"));
                mainText = cur.getString(cur.getColumnIndex("definition"));

                if (subText != null){
                    divider_definition[i-1].setVisibility(View.VISIBLE);
                    sub_definition[i].setVisibility(View.VISIBLE);
                    text_definition[i].setVisibility(View.VISIBLE);

                    sub_definition[i].setText(Html.fromHtml(subText));
                    text_definition[i].setText(Html.fromHtml(mainText, imageGetter, null));
                }else if (mainText != null){
                    divider_definition[i-1].setVisibility(View.VISIBLE);
                    text_definition[i].setVisibility(View.VISIBLE);
                    text_definition[i].setText(Html.fromHtml(mainText, imageGetter, null));
                }

                i++;
            }

            cur.close();
        }
    }

    public void displayCreate(){
        String where = "SELECT display.name, display.definition FROM display JOIN book ON book.key_id = display.main WHERE book.name = '"+ title + "'";
        Cursor cur = database.rawQuery(where, null);

        if (cur != null) {
            String subText;
            String mainText;
            int i = 1;

            if (cur.moveToFirst()) {
                subText = cur.getString(cur.getColumnIndex("name"));
                mainText = cur.getString(cur.getColumnIndex("definition"));

                if (subText != null){
                    card[1].setVisibility(View.VISIBLE);
                    sub_display[0].setVisibility(View.VISIBLE);
                    text_display[0].setVisibility(View.VISIBLE);

                    sub_display[0].setText(Html.fromHtml(subText));
                    text_display[0].setText(Html.fromHtml(mainText, imageGetter, null));
                }else if (mainText != null){
                    card[1].setVisibility(View.VISIBLE);
                    text_display[0].setVisibility(View.VISIBLE);
                    text_display[0].setText(Html.fromHtml(mainText, imageGetter, null));
                }
            }

            while(cur.moveToNext()){
                subText = cur.getString(cur.getColumnIndex("name"));
                mainText = cur.getString(cur.getColumnIndex("definition"));

                if (subText != null){
                    divider_display[i-1].setVisibility(View.VISIBLE);
                    sub_display[i].setVisibility(View.VISIBLE);
                    text_display[i].setVisibility(View.VISIBLE);

                    sub_display[i].setText(Html.fromHtml(subText));
                    text_display[i].setText(Html.fromHtml(mainText, imageGetter, null));
                }else if (mainText != null){
                    divider_display[i-1].setVisibility(View.VISIBLE);
                    text_display[i].setVisibility(View.VISIBLE);
                    text_display[i].setText(Html.fromHtml(mainText, imageGetter, null));
                }

                i++;
            }

            cur.close();
        }
    }

    public void natureCreate(){
        String where = "SELECT nature.name, nature.definition FROM nature JOIN book ON book.key_id = nature.main WHERE book.name = '"+ title + "'";
        Cursor cur = database.rawQuery(where, null);

        if (cur != null) {
            String subText;
            String mainText;
            int i = 1;

            if (cur.moveToFirst()) {
                subText = cur.getString(cur.getColumnIndex("name"));
                mainText = cur.getString(cur.getColumnIndex("definition"));

                if (subText != null){
                    card[2].setVisibility(View.VISIBLE);
                    sub_nature[0].setVisibility(View.VISIBLE);
                    text_nature[0].setVisibility(View.VISIBLE);

                    sub_nature[0].setText(Html.fromHtml(subText));
                    text_nature[0].setText(Html.fromHtml(mainText, imageGetter, null));
                }else if (mainText != null){
                    card[2].setVisibility(View.VISIBLE);
                    text_nature[0].setVisibility(View.VISIBLE);
                    text_nature[0].setText(Html.fromHtml(mainText, imageGetter, null));
                }
            }

            while(cur.moveToNext()){
                subText = cur.getString(cur.getColumnIndex("name"));
                mainText = cur.getString(cur.getColumnIndex("definition"));

                if (subText != null){
                    divider_nature[i-1].setVisibility(View.VISIBLE);
                    sub_nature[i].setVisibility(View.VISIBLE);
                    text_nature[i].setVisibility(View.VISIBLE);

                    sub_nature[i].setText(Html.fromHtml(subText));
                    text_nature[i].setText(Html.fromHtml(mainText, imageGetter, null));
                }else if (mainText != null){
                    divider_nature[i-1].setVisibility(View.VISIBLE);
                    text_nature[i].setVisibility(View.VISIBLE);
                    text_nature[i].setText(Html.fromHtml(mainText, imageGetter, null));
                }

                i++;
            }

            cur.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
