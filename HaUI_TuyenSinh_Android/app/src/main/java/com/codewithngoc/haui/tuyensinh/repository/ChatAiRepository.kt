package com.codewithngoc.haui.tuyensinh.repository

import com.codewithngoc.haui.tuyensinh.network.AiChatRequest
import com.codewithngoc.haui.tuyensinh.network.ApiClient

class ChatAiRepository {
    fun askAi(request: AiChatRequest) = ApiClient.aiInstance.askAI(request)
}
