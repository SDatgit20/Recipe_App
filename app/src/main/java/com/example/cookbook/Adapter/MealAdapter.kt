package com.example.cookbook.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cookbook.Model.MealModel
import com.example.cookbook.R
import com.example.cookbook.View.MealDetailActivity
import kotlinx.android.synthetic.main.meal_row.view.*

class MealAdapter(var meals:List<MealModel>): RecyclerView.Adapter<MealAdapter.MealHolder>() {
    class MealHolder(v: View):RecyclerView.ViewHolder(v),View.OnClickListener{
        var view:View=v;
        var mealName="";
        var mealUrl="";
        var mealId="";
        fun bindMealName(mealName: String, mealIUrl: String, mealId: String){
            this.mealName=mealName;
            view.mealName.text=mealName;
            this.mealId=mealId;
            Glide.with(view).load(mealIUrl).apply(RequestOptions.circleCropTransform()).into(view.mealImage);
        }
        init {
            v.setOnClickListener(this);
        }
        override fun onClick(p0: View?) {
            var detailIntent=Intent(view.context,MealDetailActivity::class.java);
            detailIntent.putExtra("id",mealId);
            ContextCompat.startActivity(view.context, detailIntent, null);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder {
        return MealHolder(LayoutInflater.from(parent.context).inflate(R.layout.meal_row,parent,false));
    }

    override fun onBindViewHolder(holder: MealHolder, position: Int) {
        var meal=meals[position];
        var mealName=meal.strMeal;
        var mealId=meal.idMeal;
        val mString = mealName!!.split(" ").toTypedArray();
        if(mString.size>5){
            mealName=mString[0]+" "+mString[1]+" "+mString[2]+" "+mString[3]+" "+mString[4];
        }
        var mealImg=meal.strMealThumb;
        holder.bindMealName(mealName,mealImg,mealId);
    }

    override fun getItemCount(): Int {
        return meals.size;
    }
}