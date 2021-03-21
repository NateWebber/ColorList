package com.nwebber.colorlist.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nwebber.colorlist.R

class MainFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var detailTextView: TextView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recycler = view.findViewById(R.id.recyclerView)
        recycler.LayoutManager = LinearLayoutManager(context)

        detailTextView = view.findViewById(R.id.detail_textView)
        detailTextView.text = ""
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.colorVocab.observe(viewLifecycleOwner, {
            recycler.adapter = ColorAdapter(it)
        })
    }

    private inner class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var color: ColorDefinition
        private val nameTextView: TextView = itemView.findViewById(R.id.name_textView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            detailTextView.text = color.toString()
        }

        fun bind(color: ColorDefinition){
            this.color = color
            nameTextView.text = color.name
        }
    }

    private inner class ColorAdapter(private val list: List<ColorDefinition>) : RecyclerView.Adapter<ColorViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
            val view =
        }

    }

}