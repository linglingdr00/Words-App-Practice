package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {
    // 取得 FragmentLetterListBinding 的引用(reference)
    private var _binding: FragmentLetterListBinding? = null
    // 建立名為 binding 的屬性(property)
    private val binding get() = _binding!!
    // 建立 recycler view 的屬性(property)
    private lateinit var recyclerView: RecyclerView
    // 追蹤 RecyclerView 使用哪個 LayoutManager
    private var isLinearLayoutManager = true

    // 覆寫 onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 呼叫 setHasOptionsMenu() 並傳入 true
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 設定 _binding 的值
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        // 加載 view
        val view = binding.root
        // 傳回 root view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 設定 recyclerView 屬性的值
        recyclerView = binding.recyclerView
        // 呼叫 chooseLayout()
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 將 _binding 屬性重設為 null，因為 view 已不存在
        _binding = null
    }

    // 將 menu inflater 傳遞至 onCreateOptionsMenu() 中
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = LetterAdapter()
            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
                recyclerView.adapter = LetterAdapter()
            }
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}