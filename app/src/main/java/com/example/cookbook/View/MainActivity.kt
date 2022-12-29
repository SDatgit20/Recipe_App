package com.example.cookbook.View

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.Adapter.Adapter
import com.example.cookbook.Adapter.MealAdapter
import com.example.cookbook.Controller
import com.example.cookbook.Model.MealModel
import com.example.cookbook.R
import com.example.cookbook.RetrofitHelper
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter
    lateinit var mealAdapter: MealAdapter
    lateinit var mealLayoutManager:GridLayoutManager
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutManager= LinearLayoutManager(getActivity(this),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.layoutManager=layoutManager;


        mealLayoutManager= GridLayoutManager(this,2);
        popularMeals.layoutManager=mealLayoutManager;
        GlobalScope.launch {
            val apis= RetrofitHelper.getInstance().create(Controller::class.java);
            var result=apis.getMealByCategory("Beef");
            if (result != null)
                Log.d("Srashtiiiii: ", result.body()?.meals.toString());
            withContext(Dispatchers.Main) {
                val arrayList = ArrayList<MealModel>()//Creating an empty arraylist
                for(i in 0..5 ) {
                    arrayList.add(result.body()!!.meals[i]);
                }
                println(".......print ArrayList.......")
                mealAdapter = MealAdapter(arrayList);
                popularMeals.adapter = mealAdapter
            }
        }

        GlobalScope.launch {
            val apis= RetrofitHelper.getInstance().create(Controller::class.java);
            var result=apis.getCatagories();
            if (result != null)
                Log.d("Srashtiiiii: ", result.body()?.meals.toString());
            withContext(Dispatchers.Main) {
                adapter = Adapter(result.body()?.meals!!);
                recyclerView.adapter = adapter
            }
        }
    }
}
