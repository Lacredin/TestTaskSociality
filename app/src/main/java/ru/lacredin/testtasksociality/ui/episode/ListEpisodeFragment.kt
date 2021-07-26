package ru.lacredin.testtasksociality.ui.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.lacredin.testtasksociality.App
import ru.lacredin.testtasksociality.R
import ru.lacredin.testtasksociality.databinding.FragmentListLocationBinding
import ru.lacredin.testtasksociality.ui.base.BaseListFragment
import ru.lacredin.testtasksociality.utils.CustomRecycleAdapter
import javax.inject.Inject

class ListEpisodeFragment : BaseListFragment() {

    @Inject
    lateinit var viewModel: ListEpisodeViewModel
    private var _binding: FragmentListLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListLocationBinding.inflate(inflater, container, false)

        adapter = CustomRecycleAdapter(context)

        initListViewModel(viewModel, binding.swipeRefresheBox)

        return binding.root
    }

    override fun getIdDetailFragment() = R.id.detailEpisodeFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingRecycleView(binding.listElements, viewModel)

        binding.swipeRefresheBox.setOnRefreshListener {
            viewModel.loadBaseRequest()
        }

        viewModel.loadBaseRequest()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}