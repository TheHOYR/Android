package benjamin.lahouze.channelmessaging;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import channelmessaging.R;

/**
 * Created by Benjamin
 */

public class channelActivity extends AppCompatActivity implements OnDownloadCompleteListener, View.OnClickListener {

    private ListView listView;
    private Button button;
    private EditText editTxt;
    final Handler handler = new Handler();
    private String varToken;
    private String varId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        String id = getIntent().getStringExtra("ChannelID");
        varId = id;
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String token = settings.getString("AccessToken", "null");
        varToken= token;

        listView = (ListView) findViewById(R.id.listView2);
        button = (Button) findViewById(R.id.button2);
        editTxt = (EditText) findViewById(R.id.editText3);


        HashMap<String, String> MessageParam = new HashMap<>();
        MessageParam.put("accesstoken", token);
        MessageParam.put("channelid",id);

        MessageAsync message = new MessageAsync(getApplicationContext(),MessageParam);
        message.setOnDownloadCompleteListener(this);
        message.execute();



        final Runnable r = new Runnable() {
            public void run() {

                HashMap<String, String> MessageParam = new HashMap<>();
                MessageParam.put("accesstoken", varToken);
                MessageParam.put("channelid",varId);

                MessageAsync message = new MessageAsync(getApplicationContext(),MessageParam);
                message.setOnDownloadCompleteListener(channelActivity.this);
                message.execute();
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(r, 1000);

    }

    @Override
    public void onDownloadComplete(String result) {

        Gson gson = new Gson();
        JsonMessages token = gson.fromJson(result, JsonMessages.class);
        // save index and top position
        int index = listView.getFirstVisiblePosition();

        View v = listView.getChildAt(0);
        int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());
        listView.setAdapter(new ArrayAdapterMessage(getApplicationContext(),token.getMessages()));

        // restore index and position
        listView.setSelectionFromTop(index, top);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(button.getId()==(v.getId())){
            Toast.makeText(channelActivity.this, "Message envoyé", Toast.LENGTH_SHORT).show();

            String message = editTxt.getText().toString();

            HashMap<String, String> MessageEnvoiParam = new HashMap<>();
            MessageEnvoiParam.put("accesstoken", varToken);
            MessageEnvoiParam.put("channelid",varId);
            MessageEnvoiParam.put("message",message);

            MessageSendAsync messageSend = new MessageSendAsync(getApplicationContext(),MessageEnvoiParam);
            messageSend.execute();

            editTxt.setText("");

        }
    }

}

