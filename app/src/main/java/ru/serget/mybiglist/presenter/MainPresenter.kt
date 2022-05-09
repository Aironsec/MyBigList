package ru.serget.mybiglist.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import ru.serget.mybiglist.model.entity.AllAwarding
import ru.serget.mybiglist.model.repo.IRedditPosts
import ru.serget.mybiglist.presenter.list.IPresenterList
import ru.serget.mybiglist.view.IMainActivity
import ru.serget.mybiglist.view.list.IAwardingView

class MainPresenter<V : IMainActivity>(
    private val repo: IRedditPosts,
    private val uiScheduler: Scheduler,
) : IMainPresenter<V> {

    inner class AwardingListPresenter : IPresenterList<IAwardingView> {

        val allAwarding = mutableListOf<AllAwarding>()

        override fun getCount(): Int = allAwarding.size

        override fun bindView(view: IAwardingView) {
            if (view.pos == allAwarding.size - 1) {
                loadData()
            }
            val awarding = allAwarding[view.pos]
            view.setId(awarding.id)
            view.setCoinPrice(awarding.coinPrice)
            view.loadIcon(awarding.iconUrl)
        }
    }

    private var currentView: V? = null
    val awardingList = AwardingListPresenter()
    private var dsRX: Disposable? = null

    private fun loadData() {
        dsRX = repo.getBigList()
            .observeOn(uiScheduler)
            .subscribe { allAwarding ->
                loadPageData(allAwarding)
            }
    }

    private fun loadPageData(listData: List<AllAwarding>) {
        if (listData.isNotEmpty()) {
            awardingList.allAwarding.addAll(listData)
            currentView?.setList()
        }
    }

    override fun attachView(view: V) {
        if (view != currentView)
            currentView = view
        currentView?.init()
        awardingList.allAwarding.clear()
        loadData()
    }

    override fun detachView(view: V) {
        if (view == currentView)
            currentView = null
        dsRX?.dispose()
    }

}