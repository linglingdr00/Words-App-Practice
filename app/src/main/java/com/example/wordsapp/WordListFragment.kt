package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding

class WordListFragment : Fragment() {

    // 取得 FragmentWordListBinding 的參照(reference)
    private var _binding: FragmentWordListBinding? = null
    // 建立名為 binding 的屬性(property)
    private val binding get() = _binding!!
    // 建立 recycler view 的屬性(property)
    private lateinit var recyclerView: RecyclerView
    // 建立 letterId 屬性
    private lateinit var letterId: String

    /**
     * 透過 DetailActivity.<variable> 從應用程式中的任何位置提供對這些變數的全域訪問，
     * 而無需建立 DetailActivity instance。
     */
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 假設 arguments 不是空值(null)，並傳入 it 參數的非空值(null)參數
        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 設定 _binding 的值
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        // 傳回 root view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 取得 recycler view 的 reference
        recyclerView = binding.recyclerView

        // 設定 layout manager 和 adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(letterId, requireContext())

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        // 將 _binding 屬性重設為 null，因為 view 已不存在
        _binding = null
    }

}