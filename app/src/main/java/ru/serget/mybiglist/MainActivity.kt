package ru.serget.mybiglist

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.serget.mybiglist.databinding.ActivityMainBinding
import ru.serget.mybiglist.model.IImageLoader
import ru.serget.mybiglist.presenter.MainPresenter
import ru.serget.mybiglist.view.IMainActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainActivity {

    @Inject lateinit var imageLoader: IImageLoader<ImageView>

    private lateinit var binding: ActivityMainBinding

    private val presenter by lazy {
        MainPresenter<IMainActivity>().apply {
            App.instance.appComponent.inject(this)
        }
    }
    private val adapterRV by lazy {
        AwardingRVAdapter(presenter.awardingList, imageLoader)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        presenter.attachView(this)
        super.onStart()
    }

    override fun onStop() {
        presenter.detachView(this)
        super.onStop()
    }

    override fun init() {
        App.instance.appComponent.inject(this)
        binding.listItemAwarding.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterRV
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setList() {
        adapterRV.notifyDataSetChanged()
    }


}