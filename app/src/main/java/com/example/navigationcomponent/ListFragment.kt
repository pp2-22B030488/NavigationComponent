package com.example.navigationcomponent

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var listState: Parcelable? = null  // Сохраняем позицию скролла

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = ListAdapter(generateData())
        recyclerView.adapter = adapter

        savedInstanceState?.let {
            listState = it.getParcelable("list_state")
        }
    }

    override fun onResume() {
        super.onResume()
        listState?.let {
            layoutManager.onRestoreInstanceState(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("list_state", layoutManager.onSaveInstanceState())
    }

    private fun generateData(): List<String> {
        return List(30) { "Item ${it + 1}" }
    }
}
