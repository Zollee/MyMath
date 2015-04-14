package com.howard.mymath;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class MainActivity extends ActionBarActivity implements ItemFragment.OnFragmentInteractionListener{

    private AccountHeader.Result headerResult = null;
    private Drawer.Result result = null;
    //public DBManager dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //dbHelper = new DBManager(this);
        //dbHelper.openDatabase();

        headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .build();

        result = new Drawer()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .withSelectedItem(-1)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.section_1).withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.section_2).withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.section_3).withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.section_4).withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withIdentifier(4),
                        new PrimaryDrawerItem().withName(R.string.section_5).withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withIdentifier(5),
                        new PrimaryDrawerItem().withName(R.string.section_6).withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withIdentifier(6),
                        new PrimaryDrawerItem().withName(R.string.section_7).withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withIdentifier(7),
                        new SectionDrawerItem().withName(R.string.drawer_section),
                        new SecondaryDrawerItem().withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withName(R.string.drawer_item_settings),
                        new SecondaryDrawerItem().withIcon(R.mipmap.ic_fullscreen_grey600_24dp).withName(R.string.drawer_item_settings)
                )
                .addStickyDrawerItems(
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(10)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            if (drawerItem.getIdentifier() == 1) {
                                toolbar.setTitle(R.string.section_1);

                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragment_main, new ItemFragment())
                                        .addToBackStack(null)
                                        .commit();
                            } else if (drawerItem.getIdentifier() == 2) {
                                toolbar.setTitle(R.string.section_2);

                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                //fragmentTransaction.replace(R.id.frag, new MainFragment());
                                //fragmentTransaction.commit();
                            } else if (drawerItem.getIdentifier() == 3) {
                                toolbar.setTitle(R.string.section_3);
                                //Intent intent = new Intent(SimpleHeaderDrawerActivity.this, MultiDrawerActivity.class);
                                //SimpleHeaderDrawerActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 4) {
                                toolbar.setTitle(R.string.section_4);
                                //Intent intent = new Intent(SimpleHeaderDrawerActivity.this, SimpleNonTranslucentDrawerActivity.class);
                                //SimpleHeaderDrawerActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 5) {
                                toolbar.setTitle(R.string.section_5);
                                //Intent intent = new Intent(SimpleHeaderDrawerActivity.this, ComplexHeaderDrawerActivity.class);
                                //SimpleHeaderDrawerActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 6) {
                                toolbar.setTitle(R.string.section_6);
                                //Intent intent = new Intent(SimpleHeaderDrawerActivity.this, SimpleFragmentDrawerActivity.class);
                                //SimpleHeaderDrawerActivity.this.startActivity(intent);
                            } else if (drawerItem.getIdentifier() == 7) {
                                toolbar.setTitle(R.string.section_7);
                                //Intent intent = new Intent(SimpleHeaderDrawerActivity.this, EmbeddedDrawerActivity.class);
                                //SimpleHeaderDrawerActivity.this.startActivity(intent);
                            }
                        }
                    }
                })
                .build();
        toolbar.setTitle("MyMath");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    public void onFragmentInteraction(String newItem){

    }
}
