package mvvm.app.dragerhill.ui.activity.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import mvvm.app.dragerhill.R

class PracticeFragment : Fragment() {

    private lateinit var titleTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_practice, container, false)
        titleTextView = rootView.findViewById(R.id.slug_text_view)

        val title = arguments?.getString("title", "")
        val cleanedTitle = title?.replace(
            Regex("[^a-zA-Z0-9\\s]"),
            ""
        )
        titleTextView.text = cleanedTitle

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String) =
            PracticeFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                }
            }
    }
}
