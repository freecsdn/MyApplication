package com.taole.common.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.sunday.universal.update.tools.UpdateChecker;
import com.taole.sunday.app.R;
import com.taole.universalimageloader.core.DisplayImageOptions;
import com.taole.universalimageloader.core.assist.ImageScaleType;

import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends SherlockFragmentActivity implements View.OnClickListener{

    private Context mContext;
    private String gifUrl = "http://attachment.angeeks.com/forum/month_1112/1112111959f47daee5e51c1dd4.gif";
    private GifImageView gib;
    private Button button1,button2,button3,button4;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.ic_launcher)
            .showImageOnFail(R.drawable.ic_launcher)
            .imageScaleType(ImageScaleType.NONE)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    private MenuItem menuItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_my);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

       // SimpleDialogFragment.createBuilder(this, getSupportFragmentManager()).setMessage("&&&&&&&&&&&&&").show();

    }

    @Override
    public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
        getSupportMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, com.actionbarsherlock.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(this, "Menu Item refresh selected",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "Menu Item about selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_edit:
                Toast.makeText(this, "Menu Item edit selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_search:
                Toast.makeText(this, "Menu Item search selected",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_help:
                Toast.makeText(this, "Menu Item  settings selected",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.button1:
                Intent intent = new Intent(mContext,ChooseImageActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(mContext,CropImageActivity.class);
                startActivity(intent1);
                break;
            case R.id.button3:
                UpdateChecker.checkForDialog(MainActivity.this);
                break;
            case R.id.button4:
                UpdateChecker.checkForNotification(MainActivity.this);
                break;
        }
    }
}
