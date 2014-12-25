Supporting Adcolony ads with WebView applications for Android
=============================================================

In order to support Adcolony ads within a WebView in native mobile applications, changes need to be made to have the application call Adcolony SDK to show Ads from links in webview.

For Android, the `WebViewClient` of the webview needs to be set up to handle the ad URLs.

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
    

Note: you can also call native method by javascript from html page.this example just try to use same html source with iOS.
