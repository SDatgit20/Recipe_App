package com.example.cookbook

import com.example.cookbook.Model.CategoryModel
import com.example.cookbook.Model.MealModel
import com.example.cookbook.Model.MealWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Controller {
    @GET("list.php?c=list")
    suspend fun getCatagories(): Response<MealWrapper<CategoryModel>>

    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") categoryName: String?):Response<MealWrapper<MealModel>>

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") mealId: String?):Response<MealWrapper<MealModel>>
}