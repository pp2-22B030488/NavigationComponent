package com.example.navigationcomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class CounterFragment : Fragment() {
    private var counter = 0
    private lateinit var counterText: TextView
    private lateinit var incrementButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        counterText = view.findViewById(R.id.counter_text)
        incrementButton = view.findViewById(R.id.button_increment)

        // Восстанавливаем значение счетчика после пересоздания фрагмента
        savedInstanceState?.let {
            counter = it.getInt("counter_value", 0)
        }
        counterText.text = counter.toString()

        incrementButton.setOnClickListener {
            counter++
            counterText.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter_value", counter)
    }
}
