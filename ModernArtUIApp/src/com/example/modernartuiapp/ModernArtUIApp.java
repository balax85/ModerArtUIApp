package com.example.modernartuiapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

/**
 * Builds the more information dialog.
 *
 * @author Andrea Balasso
 * @version 1.0.0
 * @since 1.0.0
 */
public class ModernArtUIApp extends Activity {

	private static final String TAG = ModernArtUIApp.class.getName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modern_art_uiapp);
		
		//I use DisplayMetrics to get the size of the screen.
		//I remove to the heigth the 10% of the height of the screen. This is for the Android's heigth menu 
		DisplayMetrics display = this.getResources().getDisplayMetrics();
        int width = display.widthPixels;
        int height = display.heightPixels - (display.heightPixels / 10);
		
        //I use the size calculated before to get variables support to calcute the size of geometrical shape that I have to use. 
		int heightLeft = height / 2;		
		int withLeft = (width / 5) * 2;		
		int heightRight = (height / 3);		
		
		//For the objects that represent the geometrical shape, I update the information about the layout params.
		//I can modify che size of the geometrical shape in according with the screen resolution. 
		
		//The shape in the left top part of the screen
		View viewtopleft = findViewById(R.id.viewtopleft);		
		viewtopleft.getLayoutParams().height = heightLeft;
		viewtopleft.getLayoutParams().width = withLeft;
		
		//The shape in the right top part of the screen
		View viewtopright = findViewById(R.id.viewtopright);		
		viewtopright.getLayoutParams().height = heightRight;
		viewtopright.getLayoutParams().width = width - withLeft;
		
		//The shape in the center of the screen
		View viewcenter = findViewById(R.id.viewcenter);		
		viewcenter.getLayoutParams().height = heightRight;
		viewcenter.getLayoutParams().width = width - withLeft;		
		
		//the shape in the left bottom  part of the screen
		View viewbottomleft = findViewById(R.id.viewbottomleft);		
		viewbottomleft.getLayoutParams().height = heightLeft;
		viewbottomleft.getLayoutParams().width = withLeft;
		
		//the shape in the rigth bottom part of the screen
		View viewbottomright = findViewById(R.id.viewbottomright);			
		viewbottomright.getLayoutParams().height = height - (heightRight * 2);
		viewbottomright.getLayoutParams().width = width - withLeft;
		
		//I set the background color of the geometrical shape. 
		//The center geometrical shape is white, well I don't need to modify it.
		//I use the RGB function to get the color to set.
		viewtopleft.setBackgroundColor(Color.rgb(106,90,205));
        viewtopright.setBackgroundColor(Color.rgb(140,23,23));
        viewbottomleft.setBackgroundColor(Color.rgb(204,50,153));
        viewbottomright.setBackgroundColor(Color.rgb(0, 0, 156));

		
        SeekBar seek = ( SeekBar ) findViewById( R.id.seekBar );

        //I set the behavior of the seek bar when it is used
        seek.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

        	//I need the geometrical shape 
    		final View viewtopleft = findViewById(R.id.viewtopleft);    		
    		final View viewtopright = findViewById(R.id.viewtopright);    		
    		final View viewbottomleft = findViewById(R.id.viewbottomleft);    		
    		final View viewbottomright = findViewById(R.id.viewbottomright);
        	
            /**
             * Notification that the seek bar progress level has changed.
             *
             * @param seekBar  The SeekBar whose progress has changed
             * @param progress The current progress level. This will be in the range 0..100
             * @param fromUser True if the progress change was initiated by the user.
             */
            @Override
            public void onProgressChanged ( SeekBar seekBar, int progress, boolean fromUser ) {

            	//When the progress bar changed, I need to modify the color of the geomtrical shape that it is not white.
            	//I redefine the background color using a simple algorithm to change the RGB color that I use to set the background color.    
            	viewtopleft.setBackgroundColor(Color.rgb(106 + progress, 90 + progress, 205 - progress));
            	viewtopright.setBackgroundColor(Color.rgb(140 + progress, 23 + progress, (23 - progress) >= 0 ? (23 - progress) : 0 ));
                viewbottomleft.setBackgroundColor(Color.rgb((204 + progress) > 255 ? 255 : (204 + progress), 50 + progress, 153 - progress));
                viewbottomright.setBackgroundColor(Color.rgb(0 + progress , 0 + progress, 156 - progress));
            }
            /**
             * Currently used for nothing.
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            /**
             * Currently used for nothing.
             *
             * @param seekBar The SeekBar in which the touch gesture began
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        } );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modern_art_uiapp, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.more_information) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Function called to view the menu item 
	 * @param item
	 */
	public void showDialog(MenuItem item) {

        new MoreInformationDialog().show(getFragmentManager(), TAG );
    }
	
}
