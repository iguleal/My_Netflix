package com.example.my_netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my_netflix.model.Category
import com.example.my_netflix.model.Movie

class CategoryAdapter(
    private val categoryList: MutableList<Category>,
    val onItemClickListener: (Int) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(category: Category){
            val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
            txtTitle.text = category.name

            val rvCategory: RecyclerView = itemView.findViewById(R.id.rv_category)
            rvCategory.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            rvCategory.adapter = MovieAdapter(category.movieList, R.layout.movie_item, onItemClickListener)


        }

    }
}