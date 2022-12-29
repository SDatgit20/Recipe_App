package com.example.cookbook.View

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cookbook.Controller
import com.example.cookbook.R
import com.example.cookbook.RetrofitHelper
import kotlinx.android.synthetic.main.activity_meal_detail.*
import kotlinx.android.synthetic.main.meal_row.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MealDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail);
        val id= intent.extras?.getString("id");
        lateinit var link:String
        GlobalScope.launch {
            var api=RetrofitHelper.getInstance().create(Controller::class.java);
            var result=api.getMealById(id);
            var meal=result.body()?.meals?.get(0);
            Log.d("Mealllllll", result.body()?.meals?.get(0).toString());
            withContext(Dispatchers.Main){
                mealDetailName.text= meal!!.strMeal;
                Glide.with(baseContext).load(meal.strMealThumb).into(recipeImg);
                recipe.text=meal.strInstructions;
                link=meal.strYoutube;
//                recipe.setMovementMethod(ScrollingMovementMethod())
            }
        }
        extended_fab.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd."+link));
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {

                // youtube is not installed.Will be opened in other available apps
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(i)
            }
        }
    }
}