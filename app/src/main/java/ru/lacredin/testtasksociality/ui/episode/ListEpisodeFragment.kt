package ru.lacredin.testtasksociality.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.lacredin.testtasksociality.databinding.FragmentListEpisodeBinding

class ListEpisodeFragment : Fragment() {

    private lateinit var listEpisodeViewModel: ListEpisodeViewModel
    private var _binding: FragmentListEpisodeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listEpisodeViewModel = ViewModelProvider(this).get(ListEpisodeViewModel::class.java)

        _binding = FragmentListEpisodeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        listEpisodeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}