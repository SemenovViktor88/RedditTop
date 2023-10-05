package com.semenov.reddit.presentation.info

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.compose.AsyncImage
import com.semenov.reddit.data.model.domain.Reddit

class InfoFragment : Fragment() {
    private val viewModel: InfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = ComposeView(requireContext()).apply{
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                viewModel.getRedditById(getReddit())
                val redditVM = viewModel.reddit.collectAsState()
                RedditInfo(redditVM)
            }
        }
        return view
    }

    @Composable
    fun RedditInfo(reddit: State<Reddit>) {
        val unixTime = System.currentTimeMillis() / 1000
        val hours = ((unixTime - reddit.value.created) / 3600).toString()
        val items = listOf(
            reddit.value.thumbnail,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF455A64)),

            ) {
            item {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(reddit.value.subreddit)
                        }
                        append(" • Posted by u/${reddit.value.author} $hours hours ago ")
                    },
                    modifier = Modifier.padding(5.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = reddit.value.title,
                    modifier = Modifier.padding(5.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
            items(items) {
                AsyncImage(
                    model = it,
                    contentDescription = "thumbnail",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                ) {
                    Text(
                        buildAnnotatedString {
                            append("Comments: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(reddit.value.num_comments.toString())
                            }
                            append(" Rating: ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(reddit.value.ups.toString())
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterStart),
                        color = Color.White
                    )
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = { onSaveDeleteClicked(reddit.value) }

                    ) {
                        Icon(
                            Icons.Filled.Favorite,
                            "contentDescription",
                            tint = Color.Red.takeIf { reddit.value.saved } ?: Color.White
                        )
                    }
                }
            }
        }
    }



    private fun onSaveDeleteClicked(reddit: Reddit) {
        when (reddit.saved) {
            true -> viewModel.removeReddit(reddit)
            false -> viewModel.saveReddit(reddit)
        }
    }
    private fun getReddit() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_ID, Reddit::class.java) as Reddit
        } else {
            arguments?.getParcelable<Reddit>(ARG_ID) as Reddit
        }

    companion object {
        @JvmStatic
        private val ARG_ID = "ARG_ID"
        @JvmStatic
        fun newInstance(reddit: Reddit): InfoFragment {
            val arg = Bundle().apply{ putParcelable(ARG_ID, reddit) }
            val fragment = InfoFragment()
            fragment.arguments = arg
            return fragment
        }
    }
}