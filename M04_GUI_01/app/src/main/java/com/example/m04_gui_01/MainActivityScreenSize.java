package com.example.m04_gui_01;

import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivityScreenSize extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_screen_size);
        TextView scrollable = (TextView)findViewById(R.id.textView);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_screen_size, menu);
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


    public void getMyVersion(View view) {

        // Play a sound effect!
        mediaPlayer = MediaPlayer.create(this, R.raw.boco_fanfare);
        if (mediaPlayer != null) {
            mediaPlayer.start();
            Log.d("Sounds", "Playing fanfare sound");
        }

        // Get version data
        String versionNum = Integer.toString(Build.VERSION.SDK_INT);
        Boolean afterKitKat = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT);

        // Put it on the screen
        TextView t = (TextView) findViewById(R.id.textView);
        t.setText(" Version Number is " + versionNum);
        t.append("\n afterKitKat = " + afterKitKat.toString());
        t.append("\n Build.VERSION.RELEASE = " + Build.VERSION.RELEASE);
        t.append("\n Build.VERSION.INCREMENTAL = " + Build.VERSION.INCREMENTAL);

        // dump build and display metrics
        t.append("\n Build.DISPLAY = " + Build.DISPLAY.toString());
        t.append("\n Screen Size = " + getSizeName(view.getContext()));
        t.append("\n getDisplayMetrics().densityDpi = " + getResources().getDisplayMetrics().densityDpi);
        t.append("\n getDisplayMetrics().density = " + getResources().getDisplayMetrics().density);
        t.append("\n getDisplayMetrics().xdpi = " + getResources().getDisplayMetrics().xdpi);
        t.append("\n getDisplayMetrics().ydpi = " + getResources().getDisplayMetrics().ydpi);
        t.append("\n getDisplayMetrics().heightPixels = " + getResources().getDisplayMetrics().heightPixels);
        t.append("\n getDisplayMetrics().widthPixels = " + getResources().getDisplayMetrics().widthPixels);

        // show what the masks look like for layout size
        t.append("\n\n screenLayout (HEX) = " + (Integer.toHexString(view.getContext().getResources().getConfiguration().screenLayout)));
        t.append("\n SCREENLAYOUT_SIZE_MASK (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_MASK)));
        t.append("\n SCREENLAYOUT_LONG_MASK  (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_MASK)));
        t.append("\n SCREENLAYOUT_SIZE_SMALL (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_SMALL)));
        t.append("\n SCREENLAYOUT_SIZE_NORMAL (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_NORMAL)));
        t.append("\n SCREENLAYOUT_SIZE_LARGE (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_SIZE_LARGE)));

        // Get the layout and mask with size
        int screenLayout = view.getContext().getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;
        t.append("\n screenLayout & size mask (HEX) = " + (Integer.toHexString((screenLayout))));

        // Get the layout (again) and mask with long bit of layout
        t.append("\n\n SCREENLAYOUT_LONG_MASK  (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_MASK)));
        t.append("\n SCREENLAYOUT_LONG_NO  (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_NO)));
        t.append("\n SCREENLAYOUT_LONG_UNDEFINED   (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_UNDEFINED)));
        t.append("\n  SCREENLAYOUT_LONG_YES   (HEX) = " + (Integer.toHexString(Configuration.SCREENLAYOUT_LONG_YES)));

        // Now get the layout (again) and mask with size
        screenLayout = view.getContext().getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_LONG_MASK;
        t.append("\n screenLayout & long mask (HEX) = " + (Integer.toHexString((screenLayout))));

    }

    // Not very OOP, I know, but fine for what we're doing here.
    public void playKramericaSound(View view) {
        // Play a sound effect!
        mediaPlayer = MediaPlayer.create(this, R.raw.kramerica);
        if (mediaPlayer != null) {
            mediaPlayer.start();
            Log.d("Sounds", "Playing kramerica sound");
        }
    }

    public void playNarcSound(View view) {
        // Play a sound effect!
        mediaPlayer = MediaPlayer.create(this, R.raw.narc);
        if (mediaPlayer != null) {
            mediaPlayer.start();
            Log.d("Sounds", "Playing narc sound");
        }
    }

    // Use bitmasks to determine screen size
    private static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case 4: // Configuration.SCREENLAYOUT_SIZE_XLARGE is API >= 9
                return "xlarge";
            default:
                return "undefined";
        }
    }

    // The method that is run when the image is clicked!
    public void add (View view) {
        Log.d("M01_Calculator ADD BUTTON", "User tapped the Add button");

        Double double1 = 0.0;
        Double double2 = 0.0;
        Double answer = 0.0;

        EditText textN1 = (EditText) findViewById(R.id.editTextN1);
        EditText textN2 = (EditText) findViewById(R.id.editTextN2);
        // we actually don't need to get ans from screen
        EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

        try {
            double1 = Double.parseDouble(textN1.getText().toString());
            double2 = Double.parseDouble(textN2.getText().toString());
            answer = double1 + double2;
        }
        catch (Exception e) {
            Log.w("M01_Calculator ADD BUTTON", "Add Selected with no inputs ... " + answer);
        }

        // Set the Answer into the the answer field
        textANS.setText(answer.toString());

        // log what we are doing
        Log.w("M01_Calculator ADD BUTTON", "Add Selected with => " + double1 + " + " + double2 + "=" + answer);
    }

    public void subtract (View view) {
        Log.d("M01_Calculator SUBTRACT BUTTON", "User tapped the Subtract button");

        Double double1 = 0.0;
        Double double2 = 0.0;
        Double answer = 0.0;

        EditText textN1 = (EditText) findViewById(R.id.editTextN1);
        EditText textN2 = (EditText) findViewById(R.id.editTextN2);
        // we actually don't need to get ans from screen
        EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

        try {
            double1 = Double.parseDouble(textN1.getText().toString());
            double2 = Double.parseDouble(textN2.getText().toString());
            answer = double1 - double2;
        }
        catch (Exception e) {
            Log.w("M01_Calculator SUBTRACT BUTTON", "Subtract Selected with no inputs ... " + answer);
        }

        // Set the Answer into the the answer field
        textANS.setText(answer.toString());

        // log what we are doing
        Log.w("M01_Calculator SUBTRACT BUTTON", "Subtract Selected with => " + double1 + " - " + double2 + "=" + answer);
    }

    public void multiply (View view) {
        Log.d("M01_Calculator MULTIPLY BUTTON", "User tapped the Multiply button");

        Double double1 = 0.0;
        Double double2 = 0.0;
        Double answer = 0.0;

        EditText textN1 = (EditText) findViewById(R.id.editTextN1);
        EditText textN2 = (EditText) findViewById(R.id.editTextN2);
        // we actually don't need to get ans from screen
        EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

        try {
            double1 = Double.parseDouble(textN1.getText().toString());
            double2 = Double.parseDouble(textN2.getText().toString());
            answer = double1 * double2;
        }
        catch (Exception e) {
            Log.w("M01_Calculator MULTIPLY BUTTON", "Multiply Selected with no inputs ... " + answer);
        }

        // Set the Answer into the the answer field
        textANS.setText(answer.toString());

        // log what we are doing
        Log.w("M01_Calculator MULTIPLY BUTTON", "Multiply Selected with => " + double1 + " * " + double2 + "=" + answer);
    }

    public void divide (View view) {
        Log.d("M01_Calculator DIVIDE BUTTON", "User tapped the Divide button");

        Double double1 = 0.0;
        Double double2 = 0.0;
        Double answer = 0.0;

        EditText textN1 = (EditText) findViewById(R.id.editTextN1);
        EditText textN2 = (EditText) findViewById(R.id.editTextN2);
        // we actually don't need to get ans from screen
        EditText textANS = (EditText) findViewById(R.id.editTextNumAns);

        try {
            double1 = Double.parseDouble(textN1.getText().toString());
            double2 = Double.parseDouble(textN2.getText().toString());
            answer = double1 / double2;
        }
        catch (Exception e) {
            Log.w("M01_Calculator DIVIDE BUTTON", "Divide Selected with no inputs ... " + answer);
        }

        // Set the Answer into the the answer field
        textANS.setText(answer.toString());

        // log what we are doing
        Log.w("M01_Calculator DIVIDE BUTTON", "Divide Selected with => " + double1 + " / " + double2 + "=" + answer);
    }

    // This is to avoid memory leaks
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
