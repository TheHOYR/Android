package benjamin.lahouze.channelmessaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.HashMap;

import channelmessaging.R;

/**
 * Created by Benjamin
 */

public class ChannelListActivity extends AppCompatActivity implements OnDownloadCompleteListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<JsonChannel> recup = new ArrayList<JsonChannel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);
        listView = (ListView) findViewById(R.id.listView);
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        String token = settings.getString("AccessToken", "null");

        HashMap<String, String> ChannelParam = new HashMap<>();
        ChannelParam.put("accesstoken", token);

        ChannelAsync channel = new ChannelAsync(getApplicationContext(),ChannelParam);
        channel.setOnDownloadCompleteListener(this);
        channel.execute();
    }



    @Override
    public void onDownloadComplete(String result) {

        Gson gson = new Gson();
        JsonChannels token = gson.fromJson(result, JsonChannels.class);

        recup=token.getChannels();

        listView.setAdapter(new ArrayAdapterChannel(getApplicationContext(), token.getChannels()));
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        Intent i = new Intent(this, channelActivity.class);
        i.putExtra("ChannelID", recup.get(pos).getChannelID());
        startActivity(i);
    }


}
