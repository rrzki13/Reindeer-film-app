package com.farazrizki13.tutorapp1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.adapter.UpcomingAdapter
import com.farazrizki13.tutorapp1.adapter.YouLikeAdapter
import com.farazrizki13.tutorapp1.model.UpcomingModel
import com.farazrizki13.tutorapp1.model.YouLikeModel
import com.luseen.spacenavigation.SpaceNavigationView
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private lateinit var upcomingAdapter : UpcomingAdapter
    private lateinit var youLikeAdapter: YouLikeAdapter

    private val upcomingSource = listOf(
        UpcomingModel("https://cdn-2.tstatic.net/jogja/foto/bank/images/trailer-film-fast-furious-9-dirilis-perlihatkan-musuh-baru-dan-kemunculan-han.jpg", "Fast and Furious 9"),
        UpcomingModel("https://i.ytimg.com/vi/RxAtuMu_ph4/maxresdefault.jpg", "Black Widow"),
        UpcomingModel("https://img.beritasatu.com/cache/beritasatu/910x580-2/1593246379.jpg", "Mulan"),
        UpcomingModel("https://cdn.sindonews.net/dyn/620/content/2019/07/24/158/1423383/doctor-strange-2-akan-menjadi-film-horor-pertama-marvel-3Wv-thumb.jpg", "Doctor Strange in the multiverse of madness"),
        UpcomingModel("https://www.slashfilm.com/wp/wp-content/images/wonder-woman-1984-poster-new.jpg", "Wonder-Woman II")
    )

    private val youLikeSource = listOf(
        YouLikeModel("https://images-na.ssl-images-amazon.com/images/I/91JNWWQKGgL._RI_.jpg", "tt0974015"),
        YouLikeModel("https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg", "tt4154796"),
        YouLikeModel("https://upload.wikimedia.org/wikipedia/id/0/00/Fast_%26_Furious_Presents_Hobbs_%26_Shaw_-_theatrical_poster.jpg", "tt6806448"),
        YouLikeModel("https://static.wikia.nocookie.net/spongebob/images/b/bf/Sponge_on_the_Run_Junior_Novelization.jpeg/revision/latest?cb=20200325001052", "tt4823776"),
        YouLikeModel("https://horrorfuel.com/wp-content/uploads/2019/01/zombieland-2.jpg", "tt1560220"),
        YouLikeModel("https://upload.wikimedia.org/wikipedia/en/thumb/4/4f/Frozen_2_poster.jpg/220px-Frozen_2_poster.jpg", "tt4520988"),
        YouLikeModel("https://images-na.ssl-images-amazon.com/images/I/51mTtUGvUCL._SY445_.jpg", "tt0120338"),
        YouLikeModel("https://images-na.ssl-images-amazon.com/images/I/91HPlz8j6XL._SL1500_.jpg", "tt0369610"),
        YouLikeModel("https://dlby.tmsimg.com/assets/p11714883_p_v7_ah.jpg", "tt3896198"),
        YouLikeModel("https://cdn.traileraddict.com/content/sony-pictures/jumanji-next-level-poster.jpg", "tt7975244"),
        YouLikeModel("https://posterspy.com/wp-content/uploads/2018/03/ShaunOfTheDeadPS-01.png", "tt0365748"),
        YouLikeModel("https://images-na.ssl-images-amazon.com/images/I/71y9wZqRF9L._AC_SL1053_.jpg", "tt0103064"),
        YouLikeModel("https://i.pinimg.com/originals/e6/57/e6/e657e6bc4a6a58e1397863850b876c9f.jpg", "tt1228705"),
        YouLikeModel("https://i.pinimg.com/originals/37/93/ce/3793ceb2327983a8046b8977a5f46d0f.jpg", "tt2975590"),
        YouLikeModel("https://m.media-amazon.com/images/M/MV5BOTk5ODg0OTU5M15BMl5BanBnXkFtZTgwMDQ3MDY3NjM@._V1_SX300.jpg", "tt1477834")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.home_fragment, container, false)
        val space : SpaceNavigationView = activity!!.findViewById(R.id.space)
        space.visibility = View.VISIBLE
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_home_upcoming.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            upcomingAdapter = UpcomingAdapter(upcomingSource)
            adapter = upcomingAdapter

            // snapping
            val snapping = LinearSnapHelper()
            snapping.attachToRecyclerView(rv_home_upcoming)

        }

        rv_home_you_like.apply {
            layoutManager = GridLayoutManager(view.context, 3, GridLayoutManager.VERTICAL, false)
            youLikeAdapter = YouLikeAdapter(youLikeSource)
            adapter = youLikeAdapter
        }
    }


}