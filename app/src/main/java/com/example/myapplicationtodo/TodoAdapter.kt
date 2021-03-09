package com.example.myapplicationtodo

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }


    private fun toggleStrikeThrough(tvTitle: TextureView, isChecked: Boolean) {
        if (isChecked) {
            tvTitle.paintFlags = tvTitle.paintFlags or STRIKE_TEXT_FLAG
        } else {
            tvTitle.paintFlags = tvTitle.paintFlags and STRIKE_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {

        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        val curTodo = todos[position]
        holder.itemView.apply {
            tvTitle.text = curTodo.title
            cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(tvTitle, curTodo.isChecked)
            cbDone.setOnCheckedChangeListener {
                _,isChecked->
                toggleStrikeThrough(tvTitle, isChecked)
                curTodo.isChecked = !curTodo.isChecked

            }

        }

    }

}