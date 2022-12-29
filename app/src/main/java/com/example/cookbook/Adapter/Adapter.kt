package com.example.cookbook.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.View.CategoryDetail
import com.example.cookbook.Model.CategoryModel
import com.example.cookbook.R
import kotlinx.android.synthetic.main.categoryrow.view.*

class Adapter(var categories: List<CategoryModel>):RecyclerView.Adapter<Adapter.categoryHolder>() {

    class categoryHolder(v:View):RecyclerView.ViewHolder(v),View.OnClickListener{
       var view:View=v;
        var category:String = "";
        fun bindCategory(category:String){
            this.category=category;
            view.textView.text=category;
        }
        init {
            v.setOnClickListener(this);
        }
        override fun onClick(p0: View?) {
            val detailIntent= Intent(view.context, CategoryDetail::class.java)
            detailIntent.putExtra("category",category);
            ContextCompat.startActivity(view.context, detailIntent, null)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryHolder {
       return categoryHolder(LayoutInflater.from(parent.context).inflate(R.layout.categoryrow,parent,false))
    }

    override fun onBindViewHolder(holder: categoryHolder, position: Int) {
       val category=categories[position];
        holder.bindCategory(category.strCategory);
    }

    override fun getItemCount(): Int {
        return categories.size;
    }
}