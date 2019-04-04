/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.taobao.weex.ui.view;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.taobao.weappplus_sdk.R;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.PmsUtil;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

public class WXWebView implements IWebView {
    private String imgurl = "";

    private Context mContext;
    private WebView mWebView;
//    private ProgressBar mProgressBar;
    private AVLoadingIndicatorView avl;

    private boolean mShowLoading = true;
    private boolean mIsShowing = false;

    private OnErrorListener mOnErrorListener;
    private OnPageListener mOnPageListener;


    public WXWebView(Context context) {
        mContext = context;
    }

    @Override
    public View getView() {
        FrameLayout root = new FrameLayout(mContext);
        root.setBackgroundColor(Color.WHITE);

        mWebView = new WebView(mContext);//mContext.getApplicationContext();
        FrameLayout.LayoutParams wvLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
        wvLayoutParams.gravity = Gravity.CENTER;
        mWebView.setLayoutParams(wvLayoutParams);
        root.addView(mWebView);
        initWebView(mWebView);

        avl = new AVLoadingIndicatorView(mContext);
        avl.setVisibility(View.GONE);
        avl.setIndicatorColor(mContext.getResources().getColor(R.color.weexPg_bar));
        avl.setIndicator("LineScalePulseOutRapidIndicator");

//        mProgressBar = new ProgressBar(mContext);
        FrameLayout.LayoutParams pLayoutParams =
                new FrameLayout.LayoutParams(WXUtils.dp2px(mContext, 35),
                        WXUtils.dp2px(mContext, 36));
        avl.setLayoutParams(pLayoutParams);
        pLayoutParams.gravity = Gravity.CENTER;
        root.addView(avl);
        return root;
    }

    @Override
    public void destroy() {
        if (mContext != null && mBroadcastReceiver != null) {
            mContext.unregisterReceiver(mBroadcastReceiver);
        }
        if (getWebView() != null) {
            getWebView().removeAllViews();
            getWebView().destroy();
            mWebView = null;
        }
    }

    @Override
    public void loadUrl(String url) {
        if(getWebView() == null)
            return;

        try {
            if ((!url.startsWith("http") && url.contains("/assets")) || url.startsWith("http://localAssets")) {
                url = "file:///android_asset/" + url.substring(url.indexOf("assets"), url.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        getWebView().loadUrl(url);
    }

    @Override
    public void reload() {
        if(getWebView() == null)
            return;
        getWebView().reload();
    }

    @Override
    public void goBack() {
        if(getWebView() == null)
            return;
        getWebView().goBack();
    }

    @Override
    public void goForward() {
        if(getWebView() == null)
            return;
        getWebView().goForward();
    }

    /*@Override
    public void setVisibility(int visibility) {
        if (mRootView != null) {
            mRootView.setVisibility(visibility);
        }
    }*/

    @Override
    public void setShowLoading(boolean shown) {
        mShowLoading = shown;
    }

    @Override
    public void setOnErrorListener(OnErrorListener listener) {
        mOnErrorListener = listener;
    }

    @Override
    public void setOnPageListener(OnPageListener listener) {
        mOnPageListener = listener;
    }

    private void showProgressBar(boolean shown) {
        if (!Constants.canShowWebAvl) {
            return;
        }
        if (mShowLoading) {
//            mProgressBar.setVisibility(shown ? View.VISIBLE : View.GONE);
            if (shown && !mIsShowing) {
                mIsShowing = true;
                Constants.avlShowing = true;
                avl.smoothToShow();
            } else if (!shown && mIsShowing) {
                mIsShowing = false;
                Constants.avlShowing = false;
                avl.smoothToHide();
            }
        }
    }

    private void showWebView(boolean shown) {
        mWebView.setVisibility(shown ? View.VISIBLE : View.INVISIBLE);
    }

    private @Nullable WebView getWebView() {
        //TODO: remove this, duplicate with getView semantically.
        return mWebView;
    }


    public void customEdgeEffect(Context context, WebView wv) {
        try {
            //glow
            int glowDrawableId = context.getResources().getIdentifier("overscroll_glow", "drawable", "android");
            Drawable androidGlow = context.getResources().getDrawable(glowDrawableId);
            androidGlow.setColorFilter(context.getResources().getColor(R.color.weexPg_bar), PorterDuff.Mode.MULTIPLY);
            //edge
            int edgeDrawableId = context.getResources().getIdentifier("overscroll_edge", "drawable", "android");
            Drawable androidEdge = context.getResources().getDrawable(edgeDrawableId);
            androidEdge.setColorFilter(context.getResources().getColor(R.color.weexPg_bar), PorterDuff.Mode.MULTIPLY);
        } catch (Exception e) {
            wv.setOverScrollMode(View.OVER_SCROLL_NEVER);
            e.printStackTrace();
        }
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, final Intent intent) //onReceive函数不能做耗时的事情，参考值：10s以内
        {
            String action = intent.getAction();
            if ("com.fivelakes.app.android.callH5JsBC".equals(action))
            {
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String js = intent.getStringExtra("js");
                        callWebPageJs(js);
                    }
                });
            }
        }
    };

    private void callWebPageJs(final String js) {
        if (mWebView != null) {
            mWebView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl(js);
                }
            }, 0);
        }
    }

    private void initWebView(WebView wv) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.fivelakes.app.android.callH5JsBC");
        mContext.registerReceiver(mBroadcastReceiver, filter);

        customEdgeEffect(mContext, wv);
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);

        settings.setDatabaseEnabled(true);

        //设置定位的数据库路径
        String dir = mContext.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);
        //启用地理定位
        settings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        settings.setDomStorageEnabled(true);

        wv.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("line.naver.jp")) {
                    Intent iuri = null;
                    try {
                        iuri = Intent.parseUri(url, 0);
                    } catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    mContext.startActivity(iuri);
                } else if (url.startsWith("http:") || url.startsWith("https:") ) {
                    view.loadUrl(url);
                    WXLogUtils.v("tag", "onPageOverride " + url);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    if(intent.resolveActivity(mContext.getPackageManager()) != null) {
                        mContext.startActivity(intent);
                    }
                }

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                WXLogUtils.v("tag", "onPageStarted " + url);
                if (mOnPageListener != null) {
                    mOnPageListener.onPageStart(url);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                WXLogUtils.v("tag", "onPageFinished " + url);
                if (mOnPageListener != null) {
                    mOnPageListener.onPageFinish(url, view.canGoBack(), view.canGoForward());
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (mOnErrorListener != null) {
                    //mOnErrorListener.onError("error", "page error code:" + error.getErrorCode() + ", desc:" + error.getDescription() + ", url:" + request.getUrl());
                    mOnErrorListener.onError("error", "page error");
                }
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError("error", "http error");
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                if (error.getUrl().contains("e-kinmen")) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }
                Log.e("onReceivedSslError", error.getUrl() + error.toString());

//                if (mOnErrorListener != null) {
//                    mOnErrorListener.onError("error", "ssl error");
//                }
            }

        });
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                showWebView(newProgress == 100);
                String url = view.getUrl();
                showProgressBar(url != null && !url.contains("fivelakespage") && newProgress != 100);
                WXLogUtils.v("tag", "onPageProgressChanged " + newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (mOnPageListener != null) {
                    mOnPageListener.onReceivedTitle(view.getTitle());
                }
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,GeolocationPermissions.Callback callback) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION);
                    if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//                        Log.d("TTTT", "弹出提示");
                    }
                }
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);

            }

        });

        wv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult result = ((WebView)v).getHitTestResult();
                if (null == result)
                    return false;
                int type = result.getType();
                if (type == WebView.HitTestResult.UNKNOWN_TYPE)
                    return false;
                if (type == WebView.HitTestResult.EDIT_TEXT_TYPE) {
                    //let TextViewhandles context menu return true;
                }
//                final ItemLongClickedPopWindow itemLongClickedPopWindow = new ItemLongClickedPopWindow(mContext,ItemLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW, UIUtils.dip2px(mContext, 120f), UIUtils.dip2px(mContext, 45f));
                // Setup custom handlingdepending on the type
                switch (type) {
                    case WebView.HitTestResult.PHONE_TYPE: // 处理拨号
                        break;
                    case WebView.HitTestResult.EMAIL_TYPE: // 处理Email
                        break;
                    case WebView.HitTestResult.GEO_TYPE: // TODO
                        break;
                    case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接
                        // Log.d(DEG_TAG, "超链接");
                        break;
                    case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
//                        break;
                    case WebView.HitTestResult.IMAGE_TYPE: // 处理长按图片的菜单项
                        imgurl = result.getExtra();
                        if (TextUtils.isEmpty(imgurl) || !(imgurl.endsWith(".png") || imgurl.endsWith(".jpg") || imgurl.endsWith(".jpeg"))) {
                            return true;
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle(mContext.getResources().getString(R.string.weex_tip));
                        builder.setMessage(mContext.getResources().getString(R.string.weex_save_to_local));
                        builder.setPositiveButton(mContext.getResources().getString(R.string.weex_ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 下载图片到本地
                                new SaveImage().execute(); // Android 4.0以后要使用线程来访问网络

                            }
                        });
                        builder.setNegativeButton(mContext.getResources().getString(R.string.weex_cancel), new DialogInterface.OnClickListener() {
                            // 自动dismiss
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    /***
     * 功能：用线程保存图片
     *
     * @author wangyp
     */
    private class SaveImage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                String sdcard = Environment.getExternalStorageDirectory().toString();
                File file = new File(sdcard + "/Download");
                if (!file.exists()) {
                    file.mkdirs();
                }
                int idx = imgurl.lastIndexOf(".");
                String ext = imgurl.substring(idx);
                file = new File(sdcard + "/Download/" + new Date().getTime() + ext);
                InputStream inputStream = null;
                URL url = new URL(imgurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(20000);
                if (conn.getResponseCode() == 200) {
                    inputStream = conn.getInputStream();
                }
                byte[] buffer = new byte[4096];
                int len = 0;
                FileOutputStream outStream = new FileOutputStream(file);
                while ((len = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
                result = "";

                Message msg = Message.obtain();
                msg.obj= file.getAbsolutePath();
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                new PmsUtil().toPmsPage(mContext);
                result = mContext.getResources().getString(R.string.weex_save_fail) +
                        mContext.getResources().getString(R.string.weex_no_pms_place_pass);
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if (TextUtils.isEmpty(result)) {
                return;
            }
            Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
        }
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String picFile = (String) msg.obj;
            String[] split = picFile.split("/");
            String fileName = split[split.length-1];
            try {
                MediaStore.Images.Media.insertImage(mContext.getApplicationContext().getContentResolver(), picFile, fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            mContext.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + picFile)));
            Toast.makeText(mContext,mContext.getResources().getString(R.string.weex_save_seccess),Toast.LENGTH_LONG).show();
        }
    };

}
