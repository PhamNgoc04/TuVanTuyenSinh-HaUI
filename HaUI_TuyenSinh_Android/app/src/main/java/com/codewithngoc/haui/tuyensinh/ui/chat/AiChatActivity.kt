package com.codewithngoc.haui.tuyensinh.ui.chat
import com.codewithngoc.haui.tuyensinh.*

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codewithngoc.haui.tuyensinh.databinding.ActivityAiChatBinding
import com.codewithngoc.haui.tuyensinh.viewmodel.ChatViewModel

data class ChatMessage(val content: String, val isUser: Boolean)

class AiChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAiChatBinding
    private val messages = mutableListOf<ChatMessage>()
    private lateinit var adapter: ChatAdapter
    private lateinit var viewModel: ChatViewModel
    // ✅ Bug #3 Fix: Track index của loading message, tránh thêm trung lặp sau rotation
    private var loadingMessageIndex = -1

    // ✅ Fix #2: Dùng ActivityResultLauncher thay vì startActivityForResult (deprecated)
    private val speechLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val spokenText = result.data
                ?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                ?.firstOrNull()
            if (!spokenText.isNullOrEmpty()) {
                addMessage(ChatMessage(spokenText, true))
                viewModel.askAi(spokenText)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAiChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.haui_red)
        binding.toolbar.setNavigationOnClickListener { finish() }

        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        adapter = ChatAdapter()
        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.adapter = adapter

        addMessage(ChatMessage("Chào bạn! Mình là AI Tư vấn tuyển sinh HaUI 🎓\nMình có thể giúp gì cho bạn?", false))

        setupObservers()

        binding.btnSend.setOnClickListener {
            val query = binding.etMessage.text.toString().trim()
            if (query.isNotEmpty()) {
                addMessage(ChatMessage(query, true))
                binding.etMessage.text.clear()
                binding.btnSend.isEnabled = false
                viewModel.askAi(query)
            }
        }

        binding.btnMic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "vi-VN")
                putExtra(RecognizerIntent.EXTRA_PROMPT, "Xin mời nói câu hỏi...")
            }
            try {
                speechLauncher.launch(intent)
            } catch (e: android.content.ActivityNotFoundException) {
                Toast.makeText(this, "Thiết bị không hỗ trợ nhận diện giọng nói", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.aiResponse.observe(this) { response ->
            // Xóa loading message khi có response
            removeLoadingMessage()
            binding.btnSend.isEnabled = true
            if (response != null) {
                addMessage(ChatMessage(response.reply ?: "Xin lỗi, AI không có phản hồi.", false))
            }
        }
        viewModel.isLoading.observe(this) { loading ->
            // ✅ Bug #3 Fix: Chỉ thêm loading message nếu chưa có, xóa khi xong
            if (loading && loadingMessageIndex == -1) {
                loadingMessageIndex = messages.size
                addMessage(ChatMessage("⏳ Đang suy nghĩ...", false))
            } else if (!loading) {
                removeLoadingMessage()
            }
            binding.btnSend.isEnabled = !loading
        }
        // ✅ Fix #3: Error hiển thị cho người dùng thay vì im lặng
        viewModel.error.observe(this) { msg ->
            if (!msg.isNullOrEmpty()) {
                removeLoadingMessage()
                binding.btnSend.isEnabled = true
                addMessage(ChatMessage("⚠️ $msg", false))
            }
        }
    }

    /** Xóa loading message khỏi list nếu đang hiển thị */
    private fun removeLoadingMessage() {
        if (loadingMessageIndex != -1 && loadingMessageIndex < messages.size) {
            messages.removeAt(loadingMessageIndex)
            adapter.notifyItemRemoved(loadingMessageIndex)
            loadingMessageIndex = -1
        }
    }

    private fun addMessage(msg: ChatMessage) {
        messages.add(msg)
        adapter.notifyItemInserted(messages.size - 1)
        binding.rvChat.scrollToPosition(messages.size - 1)
    }

    inner class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val TYPE_USER = 1
        private val TYPE_BOT = 2

        override fun getItemViewType(position: Int): Int =
            if (messages[position].isUser) TYPE_USER else TYPE_BOT

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            if (viewType == TYPE_USER) {
                UserViewHolder(com.codewithngoc.haui.tuyensinh.databinding.ItemChatUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            } else {
                BotViewHolder(com.codewithngoc.haui.tuyensinh.databinding.ItemChatBotBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val msg = messages[position]
            when (holder) {
                is UserViewHolder -> holder.binding.tvMessage.text = msg.content
                is BotViewHolder  -> holder.binding.tvMessage.text = msg.content
            }
        }

        override fun getItemCount() = messages.size

        inner class UserViewHolder(val binding: com.codewithngoc.haui.tuyensinh.databinding.ItemChatUserBinding) : RecyclerView.ViewHolder(binding.root)
        inner class BotViewHolder(val binding: com.codewithngoc.haui.tuyensinh.databinding.ItemChatBotBinding) : RecyclerView.ViewHolder(binding.root)
    }
}
