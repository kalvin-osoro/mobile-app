package com.example.firstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.databinding.ActivityThirdBinding
import com.example.firstapp.databinding.ItemTodoBinding

class TodoAdapter(
    var todos: List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>()  {

//    private lateinit var binding: ItemTodoBinding

//    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class TodoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(layoutInflater, parent, false)
        return TodoViewHolder(binding)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo,
//            parent, false)
//        return TodoViewHolder(view)

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = todos[position].title
            cbDone.isChecked = todos[position].isChecked

        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}