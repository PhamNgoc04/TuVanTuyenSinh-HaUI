package com.codewithngoc.haui.tuyensinh.ui.chat
import com.codewithngoc.haui.tuyensinh.*

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        addMessage(ChatMessage("Chào bạn, mình là AI Tư vấn tuyển sinh báo danh. Mình có thể giúp gì cho bạn?", false))

        setupObservers()

        binding.btnSend.setOnClickListener {
            val query = binding.etMessage.text.toString().trim()
            if (query.isNotEmpty()) {
                addMessage(ChatMessage(query, true))
                binding.etMessage.text.clear()
                viewModel.askAi(query)
            }
        }

        binding.btnMic.setOnClickListener {
            val intent = android.content.Intent(android.speech.RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(android.speech.RecognizerIntent.EXTRA_LANGUAGE_MODEL, android.speech.RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(android.speech.RecognizerIntent.EXTRA_PROMPT, "Xin mời nói câu hỏi...")
            }
            try {
                startActivityForResult(intent, 100)
            } catch (e: android.content.ActivityNotFoundException) {
                Toast.makeText(this, "Thiết bị không hỗ trợ nhận diện giọng nói", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupObservers() {
        viewModel.aiResponse.observe(this) { response ->
            if (response != null) {
                addMessage(ChatMessage(response.reply ?: "Xin lỗi, AI không có phản hồi.", false))
            }
        }
        viewModel.error.observe(this) { msg ->
            addMessage(ChatMessage(msg, false))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(android.speech.RecognizerIntent.EXTRA_RESULTS)
            val spokenText = result?.get(0)
            if (!spokenText.isNullOrEmpty()) {
                addMessage(ChatMessage(spokenText, true))
                viewModel.askAi(spokenText)
            }
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

        override fun getItemViewType(position: Int): Int {
            return if (messages[position].isUser) TYPE_USER else TYPE_BOT
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == TYPE_USER) {
                UserViewHolder(com.codewithngoc.haui.tuyensinh.databinding.ItemChatUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            } else {
                BotViewHolder(com.codewithngoc.haui.tuyensinh.databinding.ItemChatBotBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val msg = messages[position]
            if (holder is UserViewHolder) {
                holder.binding.tvMessage.text = msg.content
            } else if (holder is BotViewHolder) {
                holder.binding.tvMessage.text = msg.content
            }
        }

        override fun getItemCount() = messages.size

        inner class UserViewHolder(val binding: com.codewithngoc.haui.tuyensinh.databinding.ItemChatUserBinding) : RecyclerView.ViewHolder(binding.root)
        inner class BotViewHolder(val binding: com.codewithngoc.haui.tuyensinh.databinding.ItemChatBotBinding) : RecyclerView.ViewHolder(binding.root)
    }
}

