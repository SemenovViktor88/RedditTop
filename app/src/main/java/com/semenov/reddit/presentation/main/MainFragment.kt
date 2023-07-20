package com.semenov.reddit.presentation.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.semenov.reddit.R
import com.semenov.reddit.databinding.FragmentMainBinding
import com.semenov.reddit.presentation.adapter.ViewPagerAdapter
import com.semenov.reddit.presentation.news.NewsFragment
import com.semenov.reddit.presentation.save.SaveFragment

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val fragList: List<Fragment> = listOf(
        NewsFragment(),
        SaveFragment(),
    )
    lateinit var navView: BottomNavigationView
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    private val viewModel = MainViewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = ViewPagerAdapter(requireActivity(), fragList)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        navView = binding.bottomNavigationView
        viewPager = binding.viewPager2
        binding.viewPager2.adapter = adapter

        val toolbar = binding.toolbar
        val iconDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete)
            ?.let { DrawableCompat.wrap(it) }
        val menuItem = binding.toolbar.menu.add(R.string.remove_item)
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.icon = iconDrawable
        toolbar.title = "News"
        menuItem.isVisible = false
        menuItem.setOnMenuItemClickListener {
            showDialog()
            return@setOnMenuItemClickListener true
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                navView.menu.getItem(position).isChecked = true
                if(position == 0) {
                    toolbar.title = "News"
                    menuItem.isVisible = false
                }
                else {
                    toolbar.title = "Saved"
                    menuItem.isVisible = true
                }
            }
        })
        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.actionSearch -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.actionSaved -> {
                    viewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }
        return binding.root
    }


    private fun showDialog () {
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    viewModel.deleteAll()
                    showToast(R.string.delete_all)
                }
                DialogInterface.BUTTON_NEGATIVE -> showToast(R.string.cancel)
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setMessage(R.string.default_alert_message)
            .setPositiveButton(R.string.action_yes, listener)
            .setNegativeButton(R.string.action_no, listener)
            .create()

        dialog.show()
        }

    private fun showToast(messageRes: Int) {
        Toast.makeText(requireContext(), messageRes, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}