package com.example.connectionsqlsever.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connectionsqlsever.R;
import com.example.connectionsqlsever.models.Movie;

import java.util.List;

public class AdapterMovie extends BaseAdapter
{
    private Context mContext;
    List<Movie> moviesList;

    public AdapterMovie(Context mContext, List<Movie> maskList) {
        this.mContext = mContext;
        this.moviesList = maskList;
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return moviesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return moviesList.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_movie,null); //Создание метода View

        TextView Title = view.findViewById(R.id.txtTitleMovie); //нахождение textView куда будет положено название
        TextView Genre = view.findViewById(R.id.txtGenreMovie); //нахождение textView куда будет положен тип жанра
        TextView Country = view.findViewById(R.id.txtCountry);  //нахождение textView куда будет положено наименование страны
        TextView YearPremiere = view.findViewById(R.id.txtYearPremiere); //нахождение textView куда будет положено год премьеры
        ImageView imageView = view.findViewById(R.id.imageView); //нахождение imageView куда будет положено изображение фильма
        Movie movie = moviesList.get(position);//получение объекта фильма
        Title.setText(movie.getTitle()); //присваиваем textView наименование фильма текущего объекта
        Genre.setText(movie.getGenre()); //присваиваем textView жанр фильма текущего объекта
        Country.setText(movie.getCountry()); //присваиваем textView страну фильма текущего объекта
        YearPremiere.setText(Integer.toString(movie.getYearPremiere())); //присваиваем textView год премьеры фильма текущего объекта
        imageView.setImageBitmap(getUserImage(movie.getImage())); //присваиваем imageView изображения фильма текущего объекта

        return view;
    }
    private Bitmap getUserImage(String encodedImg) //метод для рапарсивания строки в изображение
    {
        if(encodedImg!=null&& !encodedImg.equals("null")) {
            byte[] bytes = Base64.decode(encodedImg, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        else return BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_movie); //если в базе пусто будет выведена заглушка
    }
}
