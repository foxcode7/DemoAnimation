package com.moushishe.demoanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.simple_transformation_btn).setOnClickListener(this);
        findViewById(R.id.frame_animation_btn).setOnClickListener(this);
        findViewById(R.id.button_fly_out_animation_btn).setOnClickListener(this);
        findViewById(R.id.height_change_animation_btn).setOnClickListener(this);
        findViewById(R.id.svg_animation_btn).setOnClickListener(this);
        findViewById(R.id.arc_progress_animation_btn).setOnClickListener(this);
        findViewById(R.id.loading_animation_btn).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.simple_transformation_btn:
                intent.setClass(MainActivity.this, SimpleTransformationActivity.class);
                break;
            case R.id.frame_animation_btn:
                intent.setClass(MainActivity.this, FrameAnimationActivity.class);
                break;
            case R.id.button_fly_out_animation_btn:
                intent.setClass(MainActivity.this, ButtonFlyOutAnimationActivity.class);
                break;
            case R.id.height_change_animation_btn:
                intent.setClass(MainActivity.this, HeightChangeAnimationActivity.class);
                break;
            case R.id.svg_animation_btn:
                intent.setClass(MainActivity.this, SVGAnimationActivity.class);
                break;
            case R.id.arc_progress_animation_btn:
                intent.setClass(MainActivity.this, ArcProgressAnimationActivity.class);
                break;
            case R.id.loading_animation_btn:
                intent.setClass(MainActivity.this, LoadingAnimationActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
    }
}
