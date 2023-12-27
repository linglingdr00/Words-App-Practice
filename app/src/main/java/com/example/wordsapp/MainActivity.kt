/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.wordsapp.databinding.ActivityMainBinding

/**
 * Main Activity and entry point for the app. Displays a RecyclerView of letters.
 */
class MainActivity : AppCompatActivity() {

    // 建立 navController 屬性
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* 小筆記：LayoutInflater的意思
        Inflater是指打氣機的意思，比如把氣球充滿氣，所以LayoutInflater就是把layout充氣充滿的機制，
        可以想像成製作出一個layout的意思。 */

        // 將xml(activity_main.xml)實例化為相應的 view 物件，並使用 binding 存取 view 物件
        val binding = ActivityMainBinding.inflate(layoutInflater)
        // 設定 activity view:使用 binding.root 代替 resource ID(R.layout.activity_main)
        setContentView(binding.root)

        // 引用 nav_host_fragment，並指派給 navController 屬性
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 呼叫 setupActionBarWithNavController()，並傳入 navController
        // 確保畫面上會顯示動作列(action bar)按鈕可見
        setupActionBarWithNavController(navController)
    }

    // 導入 onSupportNavigateUp()
    // 除了在 XML 中將 defaultNavHost 設定為 true 之外，此方法也可用來處理 up 按鈕
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
