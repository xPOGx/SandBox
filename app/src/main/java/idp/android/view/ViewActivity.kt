package idp.android.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import com.example.sandbox.databinding.LayoutScreenViewBinding
import idp.ext.Mock

class ViewActivity : ComponentActivity() {
    private var _binding: LayoutScreenViewBinding? = null
    private val binding: LayoutScreenViewBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = LayoutScreenViewBinding.inflate(LayoutInflater.from(this))

        with(binding) {
            with(bottomBar) {
                items = Mock.items.toMutableList()
                onChange = {
                    tvScreenTitle.text = bottomBar.selectedItem?.text ?: "Empty"
                }
            }

            bReset.setOnClickListener {
                bottomBar.items = Mock.items.toMutableList()
                bottomBar.binding.rvBbItems.adapter?.notifyDataSetChanged()
                tvScreenTitle.text = bottomBar.selectedItem?.text ?: "Empty"
            }

            tvScreenTitle.text = bottomBar.selectedItem?.text ?: "Empty"
        }

        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}