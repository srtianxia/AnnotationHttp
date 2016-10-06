package com.srtianxia.annotationhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.srtianxia.httplibs.ApiFactory;
import com.srtianxia.httplibs.Callback;
import com.srtianxia.httplibs.Response;

import static com.srtianxia.Logger.log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private HttpApi mApi = ApiFactory.create(HttpApi.class);;
    private Button mBtJianshu;
    private Button mBtZhihu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBtZhihu = (Button) findViewById(R.id.btn_zhihu);
        mBtJianshu = (Button) findViewById(R.id.btn_jianshu);
        mBtJianshu.setOnClickListener(this);
        mBtZhihu.setOnClickListener(this);
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


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_baidu:
                mApi.jianshu(new Callback() {
                    @Override public void onResponse(Response response) {
                        log(response.string());
                    }


                    @Override public void onFailure() {

                    }
                });
                break;
            case R.id.btn_zhihu:
                mApi.zhihu(new Callback() {
                    @Override public void onResponse(Response response) {
                        log(response.string());
                    }


                    @Override public void onFailure() {

                    }
                });
                break;
            default:
                break;
        }
    }
}
