package ru.serget.mybiglist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.serget.mybiglist.databinding.ActivityMainBinding
import ru.serget.mybiglist.model.api.ApiHolder
import ru.serget.mybiglist.model.repo.RedditPostsImpl
import ru.serget.mybiglist.presenter.MainPresenter
import ru.serget.mybiglist.view.IMainActivity

class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var binding: ActivityMainBinding

    private val presenter by lazy {
        MainPresenter<IMainActivity>(RedditPostsImpl(ApiHolder.api), AndroidSchedulers.mainThread())
    }
    private val adapterRV by lazy {
        AwardingRVAdapter(presenter.awardingList)
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