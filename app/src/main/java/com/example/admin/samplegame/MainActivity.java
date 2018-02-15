package com.example.admin.samplegame;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String current_player;
    private int set_position_1;
    private int set_position_2;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=(TextView) findViewById(R.id.dialog_title);

        // start of the game
        current_player="player_1";
        displayAlertDialog("Player 1");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // for navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayAlertDialog(String player)
    {
        Context context = MainActivity.this;
        String title = "Hide Treasure";
        LayoutInflater li= LayoutInflater.from(context);
        View dialogView =li.inflate(R.layout.dialog_prompt,null);
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setView(dialogView);
        final TextView textView = (TextView) dialogView.findViewById(R.id.dialog_title);
        textView.setText(player+"( Hide Treasure )");
        final EditText etNameInput=(EditText) dialogView.findViewById(R.id.pos);
        ad.setPositiveButton(
                "SET",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        Log.d("Temp",current_player);
                        textView.setError(null);
                        String str=etNameInput.getText().toString();
                        Log.d("length:",textView.getText().toString().trim().length()+"");
                        if(etNameInput.getText().toString().trim().length()<=0)
                        {
                            etNameInput.setError("Please Enter position(1-18)");

                        }
                        if (current_player=="player_1")
                        {
                            set_position_1=Integer.parseInt(String.valueOf(etNameInput.getText()));
                            Log.d("pos",set_position_1+"");
                            // set current player to 2
                            current_player="player_2";
                            displayAlertDialog("Player 2");
                        }
                        else if (current_player=="player_2")
                        {
                            set_position_2=Integer.parseInt(String.valueOf(etNameInput.getText()));
                            Log.d("pos",set_position_2+"");
                            build_grid_view();
                            current_player="player_1";
                        }
                    }
                }
        );

//        ad.setNegativeButton(
//                "Cancel",
//                new DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialog, int arg1) {
//                        dialog.cancel();
//                        // do nothing
//                    }
//                }
//        );

        //
        ad.show();
    }

    private  void displayResultDialog(String player)
    {
        Context context = MainActivity.this;
        String title = "Hide Treasure";
        LayoutInflater li= LayoutInflater.from(context);
        View dialogView =li.inflate(R.layout.dialog_result,null);
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setView(dialogView);
        TextView textView = (TextView) dialogView.findViewById(R.id.result_text);
        textView.setText(player+" Won");
        ad.show();
        // finishing activity after 5 sec
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(getIntent());
            }
        },1000);
        // again start the activity

    }

    private void build_grid_view()
    {
        // setting player to 1
        current_player="player_1";
        linearLayout =(LinearLayout) findViewById(R.id.main_layout);
        linearLayout.setBackgroundColor(Color.parseColor("#B0BEC5"));
        ArrayList<Image> imageArrayList=Image.getImages();
        // for image grid to display
        GridView gridView=(GridView)findViewById(R.id.image_grid);
        gridView.setAdapter(new ImageAdapter(this,imageArrayList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if(current_player=="player_1") // player 1 chance
                {
                    if(position==set_position_2-1)
                    {
                        displayResultDialog("Player 1");
                        Log.d("result","player 1 won");
                    }
                }
                else // player 2 chance
                {
                    if(position==set_position_1-1)
                    {
                        displayResultDialog("Player 2");
                        Log.d("result","player 2 won");
                    }
                }

                // shift player chance
                if (current_player=="player_1")
                {
                    linearLayout.setBackgroundColor(Color.parseColor("#B0BEC5"));
                    current_player="player_2";

                }
                else if(current_player=="player_2")
                {
                    linearLayout.setBackgroundColor(Color.parseColor("#BCAAA4"));
                    current_player="player_1";
                }

            }
        });
    }

}
