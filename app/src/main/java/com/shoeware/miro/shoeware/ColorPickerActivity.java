package com.shoeware.miro.shoeware;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;


public class ColorPickerActivity extends ActionBarActivity {

    private ColorPicker mPicker;
    private Button mSelected;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        mPicker = (ColorPicker) findViewById(R.id.picker);
        mPicker.setShowOldCenterColor(false);

        SVBar svBar = (SVBar) findViewById(R.id.svbar);
        OpacityBar opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
        SaturationBar saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
        ValueBar valueBar = (ValueBar) findViewById(R.id.valuebar);

        mPicker.addSVBar(svBar);
        mPicker.addOpacityBar(opacityBar);
        mPicker.addSaturationBar(saturationBar);
        mPicker.addValueBar(valueBar);

        mSelected = (Button) findViewById(R.id.color_selected);

        mSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mColor = mPicker.getColor();
                Intent data = new Intent();
                data.setData(Uri.parse(Integer.toString(mColor)));
                setResult(1337, data);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
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
    public void onBackPressed() {
        Toast.makeText(this, "Please select color first!", Toast.LENGTH_SHORT).show();
    }
}
