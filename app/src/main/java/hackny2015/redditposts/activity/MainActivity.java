package hackny2015.redditposts.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hackny2015.redditposts.R;
import hackny2015.redditposts.adapter.RedditPostAdapter;
import hackny2015.redditposts.model.RedditPost;
import hackny2015.redditposts.view.DividerItemDecoration;

// This is the main activity that is created when the application is started
public class MainActivity extends AppCompatActivity {

    // Tag used to log messages
    private static final String TAG = MainActivity.class.getSimpleName();

    // This method is called when the Activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Sets what view this class displays

        // Setup RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reddit_posts_recyclerview); // Find the recyclerview from the main activity layout file
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set the layout to be a linear layout
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); // Add an item decoration (this is the divider between each item)
        recyclerView.setAdapter(new RedditPostAdapter()); // Set the adapter that is used to populate the recyclerview with data

        // Setup Volley networking request
        RequestQueue queue = Volley.newRequestQueue(this); // Need to set up a queue that holds all Volley requests
        String url = "http://www.reddit.com/r/pics.json"; // The url we are getting data from

        // Set up the GET request
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Convert the string response to a json object
                            JSONObject responseJson = new JSONObject(response);

                            // Parse the json object
                            JSONArray postArray = responseJson.getJSONObject("data").getJSONArray("children");
                            RedditPost[] redditPosts = new RedditPost[10]; // Array of reddit posts
                            for (int i = 0; i < 10; i++) {
                                JSONObject post = ((JSONObject) postArray.get(i)).getJSONObject("data");
                                redditPosts[i] = new RedditPost(post.getString("title"),
                                        post.getString("thumbnail"), post.getString("url"));
                            }

                            // Set the recyclerview adapter with the updated information
                            recyclerView.setAdapter(new RedditPostAdapter(redditPosts, MainActivity.this));
                        } catch (JSONException e) {
                            Log.e(TAG, e.toString(), e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString(), error);
                    }
                });

        // Add the request to the Volley request queue
        queue.add(request);
    }

    // This method creates the items that you see on the right side of the action bar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // This method handles what happens when the items in the action bar are selected
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
}
