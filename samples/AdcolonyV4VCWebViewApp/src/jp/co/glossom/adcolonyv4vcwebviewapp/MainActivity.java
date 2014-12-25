package jp.co.glossom.adcolonyv4vcwebviewapp;

import com.jirbo.adcolony.AdColony;
import com.jirbo.adcolony.AdColonyAd;
import com.jirbo.adcolony.AdColonyAdAvailabilityListener;
import com.jirbo.adcolony.AdColonyAdListener;
import com.jirbo.adcolony.AdColonyV4VCAd;
import com.jirbo.adcolony.AdColonyV4VCListener;
import com.jirbo.adcolony.AdColonyV4VCReward;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends Activity implements AdColonyAdListener, AdColonyV4VCListener, AdColonyAdAvailabilityListener{

	  final static String APP_ID  = "app185a7e71e1714831a49ec7";
	  final static String ZONE_ID = "vz1fd5a8b2bf6841a0a4b826";

	  private WebView webView;
	  AdColonyV4VCAd v4vc_ad;
	  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAdcolony();

        setContentView(R.layout.activity_main);
        
        webView = (WebView) findViewById(R.id.webView1);
        
        webView.getSettings().setJavaScriptEnabled(true);
        //set this for showing alert from web page for debugging
        webView.setWebChromeClient(new WebChromeClient());
        
        webView.setWebViewClient(new WebViewClient(){
        	  @Override
        	  public boolean shouldOverrideUrlLoading(WebView view, String url) {

        	    if(showAdcolony(url)){

        	      return true;
        	    }
        	    return super.shouldOverrideUrlLoading(view, url);
        	  }

        	  protected boolean showAdcolony(String url) {
        	    if(url.startsWith("adcolony://")){
            		v4vc_ad = new AdColonyV4VCAd( ZONE_ID ).withListener( MainActivity.this ).withConfirmationDialog().withResultsDialog();
		            
            		// Debug pop-up showing the number of plays today and the playcap.
            		//Toast.makeText( V4VCDemo.this, ""+v4vc_ad.getRewardName(), Toast.LENGTH_SHORT ).show();

            		String status = "Available views: " + v4vc_ad.getAvailableViews();
            		Toast.makeText( MainActivity.this, status, Toast.LENGTH_SHORT ).show();

            		v4vc_ad.show();
        	    	return true;
        	    }else{
        	    	return false;
        	    }
        	  }
        	});
        webView.loadUrl("file:///android_asset/sample.html");
    }


    void setAdcolony(){
        // Configure ADC once early before any other ADC calls (except setCustomID/setDeviceID).
        AdColony.configure( this, "version:1.0,store:google", APP_ID, ZONE_ID );
        // version - arbitrary application version
        // store   - google or amazon

        // Disable rotation if not on a tablet-sized device (note: not
        // necessary to use AdColony).
        if ( !AdColony.isTablet() )
        {
          setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        }

        // Notify this object about confirmed virtual currency.
        AdColony.addV4VCListener( this );
        
        // Notify this object about ad availability changes.
        AdColony.addAdAvailabilityListener( this );
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onAdColonyAdAttemptFinished(AdColonyAd arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAdColonyAdStarted(AdColonyAd arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onAdColonyV4VCReward(AdColonyV4VCReward reward) {
	    if (reward.success())
	    {

	    	//notice web page video is finished
	    	webView.loadUrl("javascript:onAdcolonyCompleted()");
	    }

		
	}


	@Override
	public void onAdColonyAdAvailabilityChange(boolean arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
}
