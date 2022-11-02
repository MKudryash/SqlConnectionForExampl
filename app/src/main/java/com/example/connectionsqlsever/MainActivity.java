package com.example.connectionsqlsever;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.connectionsqlsever.adapter.AdapterMovie;
import com.example.connectionsqlsever.models.Movie;
import com.example.connectionsqlsever.network.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    View viewMainActivity;
    Connection connection;
    List<Movie> data;
    ListView listView;
    AdapterMovie pAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewMainActivity = findViewById(com.google.android.material.R.id.ghost_view);
        GetTextFromSQL(viewMainActivity);
    }
    public void GetTextFromSQL(View v) {
        data = new ArrayList<Movie>();
        listView = findViewById(R.id.lvData);
        pAdapter = new AdapterMovie(
                MainActivity.this, data);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "SELECT ID,Title,YearPremiere,Country,Genre,Image\n" +
                        "FROM InformationMovies\n" +
                        "LEFT JOIN Country  on InformationMovies.ID_country = Country.ID_country\n" +
                        "LEFT JOIN Genre  on InformationMovies.ID_genre = Genre.ID_genre";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Movie tempMovie = new Movie
                            (   resultSet.getInt("ID"),
                                    resultSet.getString("Title"),
                                    resultSet.getString("Genre"),
                                    resultSet.getString("Country"),
                                    resultSet.getString("Image"),
                                    Integer.parseInt(resultSet.getString("YearPremiere"))
                            );
                    data.add(tempMovie);
                    pAdapter.notifyDataSetInvalidated();
                }
                connection.close();
            } else {
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        pAdapter.notifyDataSetInvalidated();
        listView.setAdapter(pAdapter);

    }
}