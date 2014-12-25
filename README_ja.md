WebView アプリケーションでAdcolonyの広告を表示する方法(Android)
=============================================================

WebView のネイティブ アプリケーションでAdcolonyの動画を表示する場合、ウェブページにあるリンクをクリックした後に動画を再生するため、下記のような修正が必要です。

Androidの場合、下記のように定義した動画再生のURLをクリックされたとき、処理するために、広告が表示されたWebViewのWebViewClientを設定してください。

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
    

注意：javascriptでも動画再生を呼び出すことも可能ですが、こちらではiOSと一致のため、urlリンク(adcolony://)を使ってます。
