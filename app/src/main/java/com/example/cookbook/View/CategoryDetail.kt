package com.example.cookbook.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cookbook.Adapter.MealAdapter
import com.example.cookbook.Controller
import com.example.cookbook.R
import com.example.cookbook.RetrofitHelper
import kotlinx.android.synthetic.main.activity_category_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryDetail : AppCompatActivity() {
    lateinit var layoutManager: GridLayoutManager;
    lateinit var adapter: MealAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
    }
    override fun onStart() {
        super.onStart()
        val category= intent.extras?.getString("category")
        CategoryName.text="Category: "+category;
        layoutManager=GridLayoutManager(this,2);
        mealRecyclerView.layoutManager=layoutManager;
        GlobalScope.launch {
            val apis= RetrofitHelper.getInstance().create(Controller::class.java);
            var result=apis.getMealByCategory(category);
            if (result != null)
                Log.d("Srashtiiiii: ", result.body()?.meals.toString());
            withContext(Dispatchers.Main) {
                adapter = MealAdapter(result?.body()!!.meals);
                mealRecyclerView.adapter = adapter
            }
        }
    }
}